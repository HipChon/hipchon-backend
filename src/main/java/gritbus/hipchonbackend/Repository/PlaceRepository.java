package gritbus.hipchonbackend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Category;
import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Repository.custom.PlaceRepositoryCustom;
import lombok.RequiredArgsConstructor;

public interface PlaceRepository extends JpaRepository<Place,Long>, PlaceRepositoryCustom {
	Optional<Place> findById(Long id);
	Optional<Place> findByName(String name);

	// List<Place> findByCategoryAndCity(Category category, City city); 되긴하는데 성능 최적화 안됨 postList같은거 다 불러온다
	List<Place> findAllByHiple(Boolean is);



}
