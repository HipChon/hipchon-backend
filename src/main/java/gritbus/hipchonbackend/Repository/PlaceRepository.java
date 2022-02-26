package gritbus.hipchonbackend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Place;
import lombok.RequiredArgsConstructor;

public interface PlaceRepository extends JpaRepository<Place,Long> {
	Optional<Place> findById(Long id);
	Optional<Place> findByName(String name);
	List<Place> findByPeopleGreaterThanEqual(Long people);


}
