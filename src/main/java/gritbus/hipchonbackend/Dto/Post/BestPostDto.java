package gritbus.hipchonbackend.Dto.Post;

import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Domain.Post;
import gritbus.hipchonbackend.Dto.HashtagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BestPostDto {
	private Long postId;
	private String title;
	private HashtagDto hashtag;

	public static BestPostDto of(Post p) {
		Hashtag hashtag = p.getPlace().getPlaceHashtagList().get(0).getHashtag();

		return new BestPostDto(
			p.getId(),
			p.getTitle(),
			new HashtagDto(
				hashtag.getId(),
				hashtag.getName(),
				hashtag.getImage()
			)
		);
	}

}
