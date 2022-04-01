package gritbus.hipchonbackend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Post;
import gritbus.hipchonbackend.Domain.PostComment;
import gritbus.hipchonbackend.Repository.custom.PostCommentRepositoryCustom;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> , PostCommentRepositoryCustom {

}
