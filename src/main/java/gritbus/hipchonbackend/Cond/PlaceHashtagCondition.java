package gritbus.hipchonbackend.Cond;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceHashtagCondition {
	Long userId;
	Long hashtagId;
}
