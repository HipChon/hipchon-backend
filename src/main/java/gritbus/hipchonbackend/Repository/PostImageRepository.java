package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Post.PostImage;
import gritbus.hipchonbackend.Domain.Post.PostKeywordReview;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

}
