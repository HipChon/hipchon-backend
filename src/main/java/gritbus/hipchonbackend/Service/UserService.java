package gritbus.hipchonbackend.Service;

import static gritbus.hipchonbackend.error.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	private final S3Service s3Service;

	public String login(String loginType, String loginId){
		return validateUser(loginType, loginId).getLoginId();
	}

	@Transactional
	public String save(UserDto userDto,MultipartFile multipartFile){
		if ((userDto.getLoginType()) == null){
			throw new RuntimeException("로그인 타입 정보가 존재하지 않습니다");
		}

		User check = userRepository.findByLoginTypeAndLoginId(LoginType.valueOf(userDto.getLoginType()), userDto.getLoginId())
			.orElse(null);
		if (check!=null){
			throw new UserDuplicatedException(USER_DUPLICATION.getMessage(), USER_DUPLICATION);
		}

		validateName(userDto.getName()+"for fake comparison",userDto.getName());

		if (userDto.getEmail()!=null){
			validateEmail(userDto.getEmail()+"for fake comparison",userDto.getEmail());
		}

		User user = createUser(userDto,multipartFile);

		return userRepository.save(user).getLoginId();
	}

	private User createUser(UserDto userDto,MultipartFile multipartFile) {

		if (!multipartFile.isEmpty()){
			userDto.setImage(s3Service.uploadFile("profileImage",userDto.getLoginId(),multipartFile));
		}

		return User.builder()
			.loginId(userDto.getLoginId())
			.loginType(LoginType.valueOf(userDto.getLoginType()))
			.name(userDto.getName())
			.email(userDto.getEmail())
			.profileImage(userDto.getImage())
			.isMarketing(userDto.getIsMarketing())
			.build();
	}

	@Transactional
	public String updateProfile(UserDto userDto, MultipartFile multipartFile){
		User user = validateUser(userDto.getLoginType(),userDto.getLoginId());

		if (userDto.getName()!=null){
			updateName(userDto.getName(), user);
		}

		if (userDto.getEmail()!=null){
			updateEmail(userDto.getEmail(), user);
		}

		if (!multipartFile.isEmpty()){
			String image = s3Service.uploadFile("profileImage",userDto.getLoginId(), multipartFile);
			user.setProfileImage(image);
		}

		return userRepository.save(user).getLoginId();
	}

	private User validateUser(String loginType, String loginId) {
		try{
			LoginType.valueOf(loginType);
		} catch (Exception e){
			throw new NoUserException(ErrorCode.UNAUTHORIZED_USER.getMessage(), ErrorCode.UNAUTHORIZED_USER);
		}

		User user = userRepository.findByLoginTypeAndLoginId(LoginType.valueOf(loginType), loginId)
			.orElseThrow(
				() -> new NoUserException(ErrorCode.UNAUTHORIZED_USER.getMessage(), ErrorCode.UNAUTHORIZED_USER));
		return user;
	}

	private void updateName(String newName, User user) {
		if (newName !=null){
			validateName(user.getName(),newName);
			user.setName(newName);
		}
	}

	private void updateEmail(String newEmail, User user) {
		if (newEmail !=null){
			validateName(user.getName(),newEmail);
			user.setEmail(newEmail);
		}
	}

	private void validateName(String originalName,String newName) {
		List<User> user = userRepository.findAllByName(newName);
		if (user.size()!=0 && !(originalName.equals(newName))){
			throw new UserDuplicatedException(USER_NAME_DUPLICATION.getMessage(), USER_NAME_DUPLICATION);
		}
	}

	private void validateEmail(String originalEmail,String newEmail) {
		List<User> user = userRepository.findAllByEmail(newEmail);
		if (user.size()!=0 && !(originalEmail.equals(newEmail))){
			throw new UserDuplicatedException(USER_EMAIL_DUPLICATION.getMessage(), USER_EMAIL_DUPLICATION);
		}
	}

	public UserDto findByLoginTypeAndLoginId(String loginType,String loginId){
		return UserDto.of(validateUser(loginType, loginId));
	}

	@Transactional
	public void deleteUser(String loginType,String loginId){
		User user = validateUser(loginType, loginId);
		userRepository.delete(user);
	}

}
