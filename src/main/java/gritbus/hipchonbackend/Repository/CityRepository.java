package gritbus.hipchonbackend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Domain.Place;

public interface CityRepository extends JpaRepository<City,Long> {
	Optional<City> findById(Long id);
}
