package gritbus.hipchonbackend.Dto;

import gritbus.hipchonbackend.Domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	private Long id;
	private Long loginId;
	private String name;
	private String email;
	private String profileImage;
	private String loginType;
	private Boolean isMarketing;


	public static UserDto of (User user) {
		return new UserDto(
			user.getId(),
			user.getLoginId(),
			user.getName(),
			user.getEmail(),
			user.getProfileImage(),
			user.getLoginType().getName(),
			user.getIsMarketing()
		);
	}
}
