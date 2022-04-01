package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Post.PostComment;
import gritbus.hipchonbackend.Repository.custom.PostCommentRepositoryCustom;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> , PostCommentRepositoryCustom {

}
