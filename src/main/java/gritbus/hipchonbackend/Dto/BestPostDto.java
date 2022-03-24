package gritbus.hipchonbackend.Dto;

import gritbus.hipchonbackend.Domain.Event;
import gritbus.hipchonbackend.Domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BestPostDto {
	private Long postId;
	private String title;
	private Long hashtagId;
	private String hashtagName;
	private String hashtagImage;

	public static BestPostDto of (Post p){
		return new BestPostDto(
			p.getId(),
			p.getTitle(),
			p.getPlace().getPlaceHashtagList().get(0).getHashtag().getId(),
			p.getPlace().getPlaceHashtagList().get(0).getHashtag().getName(),
			p.getPlace().getPlaceHashtagList().get(0).getHashtag().getImage()
		);
	}


}
