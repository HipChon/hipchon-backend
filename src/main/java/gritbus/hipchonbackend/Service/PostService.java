package gritbus.hipchonbackend.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Post;
import gritbus.hipchonbackend.Dto.BestPostDto;
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
		List<PostDto> postDtoList = postRepository.findByPlace(placeID);
		for (PostDto postDto : postDtoList) {
			postDto.setImageList(postRepository.getImageList(postDto.getId()));
		}
		return postDtoList;

	}
}
