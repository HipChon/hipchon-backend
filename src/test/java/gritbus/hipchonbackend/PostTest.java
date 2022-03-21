package gritbus.hipchonbackend;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Dto.PostDto;
import gritbus.hipchonbackend.Repository.PlaceRepository;
import gritbus.hipchonbackend.Repository.PostRepository;
import gritbus.hipchonbackend.Service.PlaceService;
import gritbus.hipchonbackend.Service.PostService;

@SpringBootTest
@Transactional(readOnly = true)
public class PostTest {

	@Autowired
	PostService postService;
	@Autowired
	PostRepository postRepository;

	@Test
	@DisplayName("상세페이지 속 피드")
	public void findByPlace() throws Exception{
		//given
		Long placeId = 1L;
		//when
		List<PostDto> postDtoList = postService.findByPlace(placeId);
		//then

		for (PostDto postDto : postDtoList) {
			System.out.println(postDto.toString());
		}
		PostDto post=postDtoList.get(3);
		assertThat(postDtoList.size())
			.isEqualTo(4);
		assertThat(post.getUserPostCnt()) // n번째 리뷰 확인
			.isEqualTo(4);
		assertThat(post.getImageList().size())
			.isEqualTo(3);
		assertThat(post.getCommentCnt())
			.isEqualTo(5);
	}

}