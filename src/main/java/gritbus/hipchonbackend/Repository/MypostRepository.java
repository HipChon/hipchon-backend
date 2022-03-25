package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Mypost;
import gritbus.hipchonbackend.Repository.custom.MypostRepositoryCustom;

public interface MypostRepository extends JpaRepository<Mypost, Long>, MypostRepositoryCustom {

}
