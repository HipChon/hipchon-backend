package gritbus.hipchonbackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.user.LoginType;
import gritbus.hipchonbackend.Domain.user.User;

@SpringBootTest
@Transactional(readOnly = true)
public class UserTest {


	@Test
	@DisplayName("kakao 정보 가져오기")
	public void getKakaoUserInfo() throws Exception {

		//given
		User user = User.builder().isMarketing(true).name("as").loginType(LoginType.kakao).build();
		System.out.println(user.toString());
		//when

		//then
		// assertThat(top3.size())
		// 	.isEqualTo(3);
	}


}
