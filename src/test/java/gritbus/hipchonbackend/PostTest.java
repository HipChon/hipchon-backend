package gritbus.hipchonbackend;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Dto.Post.PostDto;
import gritbus.hipchonbackend.Repository.PostRepository;
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
	public void findByPlace() throws Exception {
		//given
		Long placeId = 1L;
		//when
		List<PostDto> postDtoList = postService.findByPlace(placeId);
		//then

		for (PostDto postDto : postDtoList) {
			System.out.println(postDto.toString());
		}
		PostDto post = postDtoList.get(3);
		assertThat(postDtoList.size())
			.isEqualTo(4);
		assertThat(post.getImageList().size())
			.isEqualTo(3);
		assertThat(post.getCommentCnt())
			.isEqualTo(5);
	}

	@Test
	@DisplayName("전체 post 보여주기")
	public void findAll() throws Exception {
		//given
		Long userId = 1L;
		String order = "recent";
		//when
		List<PostDto> postDtoList = postService.findAll(userId, order);
		//then
		for (PostDto postDto : postDtoList) {
			System.out.println(postDto.toString());
		}
		PostDto post = postDtoList.get(3);
		// assertThat(postDtoList.size())
		// 	.isEqualTo(4);
		// assertThat(post.getUserPostCnt()) // n번째 리뷰 확인
		// 	.isEqualTo(4);
		// assertThat(post.getImageList().size())
		// 	.isEqualTo(3);
		// assertThat(post.getCommentCnt())
		// 	.isEqualTo(5);
	}

}
