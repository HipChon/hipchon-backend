package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Repository.custom.MyplaceRepositoryCustom;

public interface MyplaceRepository extends JpaRepository<Myplace, Long>, MyplaceRepositoryCustom {

	boolean existsByUserAndPlace(User user, Place place);
}
