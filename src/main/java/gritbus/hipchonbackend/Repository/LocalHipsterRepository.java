package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.LocalHipster.LocalHipster;
import gritbus.hipchonbackend.Repository.custom.LocalHipsterRepositoryCustom;

public interface LocalHipsterRepository extends JpaRepository<LocalHipster, Long>, LocalHipsterRepositoryCustom {

}
