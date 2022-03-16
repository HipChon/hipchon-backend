package gritbus.hipchonbackend.Cond;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class PlaceFastSearchCondition {
	Long userId;
	Long cityId;
	Long categoryId;


}
