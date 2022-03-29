package gritbus.hipchonbackend;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.user.LoginType;
import gritbus.hipchonbackend.Domain.user.User;
import gritbus.hipchonbackend.Repository.UserRepository;
import gritbus.hipchonbackend.Service.UserService;

@SpringBootTest
@Transactional(readOnly = true)
public class UserTest {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@Test
	@Transactional
	@DisplayName("kakao 로그인")
	public void getKakaoUserInfo() throws Exception {
		//
		// //given
		// User user = User.builder().id(87L).isMarketing(true).name("문예완").loginType(LoginType.kakao).loginId(1233L).build();
		// userRepository.save(user);
		// System.out.println(user.toString());
		// //when
		//
		// Long login = userService.login("kakao", 1233L);
		// User byId = userRepository.findById(login).get();
		//
		//
		// //then
		// assertThat(byId.getLoginId())
		// 	.isEqualTo(user.getLoginId());
		// assertThat(byId.getName())
		// 	.isEqualTo(user.getName());
	}
}
