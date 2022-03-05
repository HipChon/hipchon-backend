package gritbus.hipchonbackend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.PlaceHashtag;

public interface PlaceHashtagRepository extends JpaRepository<PlaceHashtag,Long> {
	Optional<PlaceHashtag> findById(Long id);
	List<PlaceHashtag> findAllByHashtag(Hashtag hashtag);
	PlaceHashtag findByPlaceAndHashtag(Place place, Hashtag hashtag);
	boolean existsByPlaceAndHashtag(Place place,Hashtag hashtag);
}
