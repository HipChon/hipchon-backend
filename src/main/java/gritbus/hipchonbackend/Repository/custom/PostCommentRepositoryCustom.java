package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Dto.KeywordDto;
import gritbus.hipchonbackend.Dto.Post.CommentDto;

public interface PostCommentRepositoryCustom {
	List<CommentDto> findAllByPostId(Long postId);
}
