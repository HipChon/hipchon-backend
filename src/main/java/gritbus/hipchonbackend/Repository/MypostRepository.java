package gritbus.hipchonbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Post.Mypost;
import gritbus.hipchonbackend.Domain.Post.Post;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Repository.custom.MypostRepositoryCustom;

public interface MypostRepository extends JpaRepository<Mypost, Long>, MypostRepositoryCustom {
	Boolean existsByUserAndPost(User user, Post post);
}
