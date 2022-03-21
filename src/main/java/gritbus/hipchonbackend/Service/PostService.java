package gritbus.hipchonbackend.Service;

import static java.util.Comparator.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Dto.BestPostDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Dto.PostDto;
import gritbus.hipchonbackend.Repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	public List<BestPostDto> findAllByIsBest(){
		return postRepository.findAllByIsBest(true).stream()
			.map(post -> BestPostDto.of(post))
			.collect(Collectors.toList());
	}

	public List<PostDto> findByPlace(Long placeID){
		List<PostDto> postDtoList = findAllOrByPlace(placeID);
		return postDtoList;
	}

	public List<PostDto> findAll(String order){
		List<PostDto> postDtoList = findAllOrByPlace(-1L);
		return orderPlaceList(postDtoList,order);
	}

	private List<PostDto> orderPlaceList(List<PostDto> postDtoList, String order) {
		if (order.equals("like")){
			return postDtoList.stream()
				.sorted(comparing(PostDto::getLikeCnt).reversed())
				.collect(Collectors.toList());
		}
		return postDtoList;
	}

	private List<PostDto> findAllOrByPlace(Long placeID) {
		List<PostDto> postDtoList = postRepository.findAllOrByPlace(placeID);
		for (PostDto postDto : postDtoList) {
			postDto.setImageList(postRepository.getImageList(postDto.getId()));
		}
		return postDtoList;
	}


}
