package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Menu;
import gritbus.hipchonbackend.Domain.Post.Mypost;
import gritbus.hipchonbackend.Domain.Post.Post;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Repository.custom.MenuRepositoryCustom;
import gritbus.hipchonbackend.Repository.custom.MypostRepositoryCustom;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom {

}
