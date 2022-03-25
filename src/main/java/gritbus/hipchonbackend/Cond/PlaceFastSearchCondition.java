package gritbus.hipchonbackend.Cond;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceFastSearchCondition {
	Long userId;
	Long cityId;
	Long categoryId;

}
