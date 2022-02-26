package gritbus.hipchonbackend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Domain.Place;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {

	Optional<Hashtag> findById(Long id);

}
