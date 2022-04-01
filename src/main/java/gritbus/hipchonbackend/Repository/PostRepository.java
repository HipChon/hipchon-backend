package gritbus.hipchonbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Post.Post;
import gritbus.hipchonbackend.Repository.custom.PostRepositoryCustom;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

	List<Post> findAllByIsBest(Boolean is);

}
