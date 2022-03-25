package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Announce;

public interface AnnounceRepository extends JpaRepository<Announce, Long> {
}
