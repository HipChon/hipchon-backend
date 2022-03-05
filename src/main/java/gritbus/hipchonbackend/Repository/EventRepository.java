package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Announce;
import gritbus.hipchonbackend.Domain.Event;

public interface EventRepository extends JpaRepository<Event,Long> {
}
