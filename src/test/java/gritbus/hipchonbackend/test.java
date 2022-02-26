package gritbus.hipchonbackend;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Repository.PlaceRepository;

@SpringBootTest
public class test {

	@Autowired
	PlaceRepository placeRepository;

	@Test
	@Transactional
	@DisplayName("place repo test")
	public void test() throws Exception{
		Place place = new Place();
		place.setName("seoul");

		placeRepository.save(place);
		Optional<Place> finded = placeRepository.findByName("seoul");

		assertThat(finded.get().getName())
			.isEqualTo("seoul");
		System.out.println(finded.get().getId());
		assertThat(finded.get().getId())
			.isEqualTo(1);
	}
}
