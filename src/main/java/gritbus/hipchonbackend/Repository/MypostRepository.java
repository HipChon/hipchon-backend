package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.Mypost;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Repository.custom.MyplaceRepositoryCustom;
import gritbus.hipchonbackend.Repository.custom.MypostRepositoryCustom;

public interface MypostRepository extends JpaRepository<Mypost,Long>, MypostRepositoryCustom {

}
