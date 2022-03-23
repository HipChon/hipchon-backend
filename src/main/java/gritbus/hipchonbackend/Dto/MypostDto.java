package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MypostDto {
    private Long id;
    private String placeName;
    private String postImage;

    @QueryProjection
    public MypostDto(Long id, String placeName){
        this.id = id;
        this.placeName = placeName;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}
