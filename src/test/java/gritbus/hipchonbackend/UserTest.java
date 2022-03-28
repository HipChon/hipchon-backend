package gritbus.hipchonbackend;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Dto.KeywordDto;
import gritbus.hipchonbackend.Repository.KeywordReviewRepository;

@SpringBootTest
@Transactional(readOnly = true)
public class UserTest {


	@Test
	@DisplayName("kakao 정보 가져오기")
	public void getKakaoUserInfo() throws Exception {

		//given
		String token = "NhFN254WhMXQnaJHfMYTesuq3d7hfF7MckgVfgo9cxcAAAF_yzYrww";
		// String token = "CuiRUuLr1P97anN569ZkZN4J4hWd3acqwvhALQopyV4AAAF_y0PvFw";
		//when
		User.createKakaoUser(token);
		//then
		// assertThat(top3.size())
		// 	.isEqualTo(3);
	}


}
