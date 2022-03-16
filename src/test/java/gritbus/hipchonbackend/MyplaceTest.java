package gritbus.hipchonbackend;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Service.MyplaceService;

@SpringBootTest
@Transactional(readOnly = true)
public class MyplaceTest {

	@Autowired
	MyplaceService myplaceService;

	@Test @Transactional
	@DisplayName("myplace add")
	public void myplaceAdd() throws Exception{

		//given
		Long userId=4L;
		Long placeId=15L;

		//when
		myplaceService.add(userId,placeId);
		Long myplaceCnt = myplaceService.getMyplaceCntByUser(userId);

		//then
		assertThat(myplaceCnt)
			.isEqualTo(3);
	}
}
