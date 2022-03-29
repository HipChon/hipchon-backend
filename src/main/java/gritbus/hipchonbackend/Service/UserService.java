package gritbus.hipchonbackend.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.LoginType;
import gritbus.hipchonbackend.Domain.User;
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
		return getUser(loginType, loginId).getLoginId();
	}

	private User getUser(String loginType, Long loginId) {
		return userRepository.findByLoginTypeAndLoginId(LoginType.valueOf(loginType), loginId)
			.orElseThrow(
				() -> new NoUserException(ErrorCode.UNAUTHORIZED_USER.getMessage(), ErrorCode.UNAUTHORIZED_USER));
	}

	@Transactional
	public Long save(UserDto userDto){
		if ((userDto.getLoginType()) == null){
			throw new RuntimeException("로그인 타입 정보가 존재하지 않습니다");
		}
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

		return userRepository.save(user).getLoginId();
	}

	@Transactional
	public Long updateProfile(UserDto userDto){
		String newName = userDto.getName();
		String newImage = userDto.getProfileImage();

		User user = userRepository.findByLoginId(userDto.getLoginId())
			.orElseThrow(
				() -> new NoUserException(ErrorCode.UNAUTHORIZED_USER.getMessage(), ErrorCode.UNAUTHORIZED_USER));

		if (newImage !=null){
			user.setProfileImage(newImage);
		}
		updateName(newName, user);

		return userRepository.save(user).getLoginId();
	}

	private void updateName(String newName, User user) {
		if (newName !=null){
			validateName(user.getName(),newName);
			user.setName(newName);
		}
	}

	private void validateName(String originalName,String newName) {
		User user = userRepository.findByName(newName).orElse(null);
		if (user!= null && !(originalName.equals(newName))){
			ErrorCode errorCode = ErrorCode.USER_NAME_DUPLICATION;
			throw new UserDuplicatedException(errorCode.getMessage(), errorCode);
		}
	}

	public UserDto findByLoginTypeAndLoginId(String loginType,Long loginId){
		return UserDto.of(getUser(loginType, loginId));
	}

}
