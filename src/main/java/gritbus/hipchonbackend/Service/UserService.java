package gritbus.hipchonbackend.Service;

import java.net.UnknownServiceException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.user.LoginType;
import gritbus.hipchonbackend.Domain.user.User;
import gritbus.hipchonbackend.Dto.UserDto;
import gritbus.hipchonbackend.Repository.UserRepository;
import gritbus.hipchonbackend.error.ErrorCode;
import gritbus.hipchonbackend.exception.NoUserException;
import gritbus.hipchonbackend.exception.UserDuplicatedException;
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
	@Transactional
	public Long save(UserDto userDto){
		User user1 = userRepository.findByLoginTypeAndLoginId(LoginType.valueOf(userDto.getLoginType()), userDto.getLoginId())
			.orElse(null);
		if (user1!=null){
			ErrorCode errorCode = ErrorCode.USER_DUPLICATION;
			throw new UserDuplicatedException(errorCode.getMessage(),errorCode);
		}

		User user = User.builder()
			.loginId(userDto.getLoginId())
			.loginType(LoginType.valueOf(userDto.getLoginType()))
			.name(userDto.getName())
			.email(userDto.getEmail())
			.profileImage(userDto.getProfileImage())
			.isMarketing(userDto.getIsMarketing())
			.build();

		return userRepository.save(user).getId();
	}

}
