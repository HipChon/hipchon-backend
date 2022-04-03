package gritbus.hipchonbackend.Service;

import static gritbus.hipchonbackend.error.ErrorCode.*;
import static java.util.Comparator.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Dto.Post.BestPostDto;
import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Dto.Post.PostDto;
import gritbus.hipchonbackend.Repository.PostRepository;
import gritbus.hipchonbackend.error.ErrorCode;
import gritbus.hipchonbackend.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

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
