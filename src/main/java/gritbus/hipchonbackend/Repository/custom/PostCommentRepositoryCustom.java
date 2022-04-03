package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Dto.Post.CommentDto;
import gritbus.hipchonbackend.Dto.Post.MyCommentDto;

public interface PostCommentRepositoryCustom {
	List<CommentDto> findAllByPostId(Long postId);
	List<MyCommentDto> findAllMyComment(Long userId);
}
