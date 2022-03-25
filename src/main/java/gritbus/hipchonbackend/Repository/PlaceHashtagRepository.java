package gritbus.hipchonbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.PlaceHashtag;

public interface PlaceHashtagRepository extends JpaRepository<PlaceHashtag, Long> {
	Optional<PlaceHashtag> findById(Long id);

	PlaceHashtag findByPlaceAndHashtag(Place place, Hashtag hashtag);

	boolean existsByPlaceAndHashtag(Place place, Hashtag hashtag);
}
