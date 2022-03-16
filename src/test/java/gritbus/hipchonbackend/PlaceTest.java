package gritbus.hipchonbackend;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;

import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.QPlace;
import gritbus.hipchonbackend.Domain.QPost;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Repository.PlaceRepository;
import gritbus.hipchonbackend.Service.PlaceService;

@SpringBootTest
@Transactional(readOnly = true)
public class PlaceTest {

	@Autowired
	PlaceRepository placeRepository;
	@Autowired
	PlaceService placeService;

	@Test
	@DisplayName("place repo test")
	public void test() throws Exception{
		//given
		String name="조양방직";
		//when
		Optional<Place> found = placeRepository.findByName(name);

		//then
		System.out.println("found.get().getCity().getName() = " + found.get().getCity().getName());
		assertThat(found.get().getName())
			.isEqualTo("조양방직");
		System.out.println(found.get().getId());
		assertThat(found.get().getId())
			.isEqualTo(5);
	}
	@Test
	@DisplayName("all hiple")
	public void findAllByHipleTest() throws Exception{
		//given
		String is="true";
		Long userId = 1L;
		//when
		List<HipleDto> allByHiple = placeService.findAllByHiple(userId);
		for (HipleDto hipleDto : allByHiple) {
			System.out.println("hipleDto.getName() = " + hipleDto.getName());
		}
		//then
		assertThat(allByHiple.size())
			.isEqualTo(8);
		assertThat(allByHiple.stream()
			.filter(h->h.getIsMyplace())
			.count())
			.isEqualTo(2);
	}
	@Test
	@DisplayName("fastSearch")
	public void fastSearchTest() throws Exception{

		//given
		Long cityId=2L;
		Long categoryId=1L;
		Long userId = 1L;
		//when
		List<PlaceListDto> placeListDtos = placeService.fastSearch(userId, cityId, categoryId);
		for (PlaceListDto place : placeListDtos) {
			System.out.print("place.getName() = " + place.getName());
			System.out.print(" postCnt = " + place.getPostCnt());
			System.out.print(" place.getIsMyplace() = " + place.getIsMyplace());
			System.out.println(" place.getMyplaceCnt() = " + place.getMyplaceCnt());;

		}
		//then
		//then
		assertThat(placeListDtos.get(0).getMyplaceCnt())
			.isEqualTo(1);
		assertThat(placeListDtos.size())
			.isEqualTo(3);
		assertThat(placeListDtos.stream()
			.filter(p->p.getIsMyplace())
			.count())
			.isEqualTo(2);

	}
}
