package gritbus.hipchonbackend;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Dto.Post.PostDto;
import gritbus.hipchonbackend.Repository.PostRepository;
import gritbus.hipchonbackend.Service.PostService;
import gritbus.hipchonbackend.Service.S3Service;

@SpringBootTest
@Transactional(readOnly = true)
public class PostTest {

	@Autowired
	PostService postService;
	@Autowired
	PostRepository postRepository;
	@Autowired
	S3Service s3Service;

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
	@Transactional()
	@Rollback(value = false)
	@DisplayName("전체 post 보여주기")
	public void del() throws Exception {
		//given

		List<String> imageList = new ArrayList<>();
		imageList.add("https://hipchon-bucket.s3.ap-northeast-2.amazonaws.com/postImage/31/2_%EB%8B%AC%ED%95%98%EB%A7%A5%EC%A3%BC1_2022-04-04T04%3A06%3A26.296628500.png");
		imageList.add("https://hipchon-bucket.s3.ap-northeast-2.amazonaws.com/postImage/31/2_%EC%96%B4%EB%8B%88%EC%8A%A4%ED%8A%B8%EB%B0%80%ED%81%AC1_2022-04-04T04%3A06%3A26.136836.png");

		//when
		imageList = imageList.stream()
			.map(image -> image.split(".com/", 2)[1])
			.collect(Collectors.toList());
		//then
		for (String s : imageList) {
			System.out.println(s);
		}
		s3Service.deleteFileList(imageList);
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
