package gritbus.hipchonbackend.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.user.LoginType;
import gritbus.hipchonbackend.Domain.user.User;
import gritbus.hipchonbackend.Repository.UserRepository;
import gritbus.hipchonbackend.error.ErrorCode;
import gritbus.hipchonbackend.exception.NoUserException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public Long login(String loginType, Long loginId){
		return userRepository.findByLoginTypeAndLoginId(LoginType.valueOf(loginType), loginId)
			.orElseThrow(()-> new NoUserException(ErrorCode.UNAUTHORIZED_USER.getMessage(), ErrorCode.UNAUTHORIZED_USER))
			.getId();

	}
}
