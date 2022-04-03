package gritbus.hipchonbackend.Service;

import static gritbus.hipchonbackend.error.ErrorCode.*;
import static java.util.Comparator.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gritbus.hipchonbackend.Domain.KeywordReview;
import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.Post.Post;
import gritbus.hipchonbackend.Domain.Post.PostImage;
import gritbus.hipchonbackend.Domain.Post.PostKeywordReview;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Dto.Post.BestPostDto;
import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Dto.Post.PostDto;
import gritbus.hipchonbackend.Dto.Requset.PostRequest;
import gritbus.hipchonbackend.Repository.KeywordReviewRepository;
import gritbus.hipchonbackend.Repository.PlaceRepository;
import gritbus.hipchonbackend.Repository.PostImageRepository;
import gritbus.hipchonbackend.Repository.PostKeywordReviewRepository;
import gritbus.hipchonbackend.Repository.PostRepository;
import gritbus.hipchonbackend.Repository.UserRepository;
import gritbus.hipchonbackend.error.ErrorCode;
import gritbus.hipchonbackend.exception.ElementDuplicatedException;
import gritbus.hipchonbackend.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final PlaceRepository placeRepository;
	private final PostKeywordReviewRepository postKeywordReviewRepository;
	private final KeywordReviewRepository keywordReviewRepository;
	private final PostImageRepository postImageRepository;
	private final S3Service s3Service;

	public List<MypostDto> findByUser(Long userId) {
		return postRepository.findByUser(userId);
	}

	public List<BestPostDto> findAllByIsBest() {
		return postRepository.findAllByIsBest(true).stream()
			.map(post -> BestPostDto.of(post))
			.collect(Collectors.toList());
	}

	public List<PostDto> findByPlace(Long placeID) {
		List<PostDto> postDtoList = findAllOrByPlace(-1L, placeID);
		return postDtoList;
	}

	public List<PostDto> findAll(Long userId, String order) {
		List<PostDto> postDtoList = findAllOrByPlace(userId, -1L);
		return orderPlaceList(postDtoList, order);
	}

	public PostDto findOneById(Long userId, Long postId){
		List<PostDto> oneById = postRepository.findOneById(userId, postId);
		if(oneById==null || oneById.size()!=1){
			throw new NoSuchElementException(ELEMENT_NOT_FOUND.getErrorCode(), ELEMENT_NOT_FOUND);
		}
		return oneById.get(0);

	}

	@Transactional
	public Long add(PostRequest request,List<MultipartFile> multipartFileList) {
		Long userId = request.getUserId();
		User user = validateUser(userId);
		Place place = validatePlace(request.getPlaceId());

		Post post = postRepository.save(Post.createPost(user, place, request.getDetail()));
		Long id = post.getId();

		// 혹시모를 중복 제거
		for (Long keywordId : Set.copyOf(request.getKeywordIdList())) {
			KeywordReview keywordReview = validateKeywordReview(keywordId);
			postKeywordReviewRepository.save(PostKeywordReview.createPostKeywordReview(post,keywordReview));
		}
		List<String> imageList = s3Service.uploadFileList("postImage/" + id, userId.toString(), multipartFileList);
		for (String image : imageList) {
			if (image!=null){
				postImageRepository.save(PostImage.createPostImage(post,image));
			}
		}
		return id;
	}

	private KeywordReview validateKeywordReview(Long keywordId) {
		return keywordReviewRepository.findById(keywordId).orElseThrow(() ->
			new NoSuchElementException(KEYWORDREVIEW_NOT_FOUND.getErrorCode(), KEYWORDREVIEW_NOT_FOUND));
	}

	private Place validatePlace(Long placeId) {
		return placeRepository.findById(placeId).orElseThrow(() -> new NoSuchElementException(
			PLACE_NOT_FOUND.getErrorCode(), PLACE_NOT_FOUND));
	}

	private User validateUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(
			UNAUTHORIZED_USER.getErrorCode(), UNAUTHORIZED_USER));
	}

	private List<PostDto> orderPlaceList(List<PostDto> postDtoList, String order) {
		if (order.equals("like")) {
			return postDtoList.stream()
				.sorted(comparing(PostDto::getLikeCnt).reversed())
				.collect(Collectors.toList());
		}
		return postDtoList;
	}

	private List<PostDto> findAllOrByPlace(Long userId, Long placeID) {
		List<PostDto> postDtoList = postRepository.findAllOrByPlace(userId, placeID);
		return postDtoList;
	}

}
