package gritbus.hipchonbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.User;

public interface MyplaceRepository extends JpaRepository<Myplace,Long> {
	Optional<Myplace> findByUserAndPlace(User user, Place place);
	boolean existsByUserAndPlace(User user,Place place);
}
