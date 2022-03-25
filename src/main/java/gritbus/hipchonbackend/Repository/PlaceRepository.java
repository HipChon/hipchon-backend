package gritbus.hipchonbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Repository.custom.PlaceRepositoryCustom;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
	Optional<Place> findById(Long id);

	Optional<Place> findByName(String name);

	// List<Place> findByCategoryAndCity(Category category, City city); 되긴하는데 성능 최적화 안됨 postList같은거 다 불러온다

}
