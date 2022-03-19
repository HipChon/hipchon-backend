package gritbus.hipchonbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Post;
import gritbus.hipchonbackend.Domain.User;

public interface PostRepository extends JpaRepository<Post,Long> {

	List<Post> findAllByIsBest(Boolean is);
}
