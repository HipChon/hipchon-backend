package gritbus.hipchonbackend.Dto;

import lombok.Data;

@Data
public class UserDto {
	private Long loginId;
	private String name;
	private String email;
	private String profileImage;
	private String loginType;
	private Boolean isMarketing;
}
