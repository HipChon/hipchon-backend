package gritbus.hipchonbackend.Service;

import static gritbus.hipchonbackend.error.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Post.Post;
import gritbus.hipchonbackend.Dto.Post.CommentDto;
import gritbus.hipchonbackend.Repository.PostCommentRepository;
import gritbus.hipchonbackend.Repository.PostRepository;
import gritbus.hipchonbackend.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostCommentService {
	private final PostRepository postRepository;
	private final PostCommentRepository postCommentRepository;

	public List<CommentDto> findALlByPostId(Long postId){
		Post post = postRepository.findById(postId).orElseThrow(
			()->new NoSuchElementException(POST_NOT_FOUND.getMessage(), POST_NOT_FOUND));

		return postCommentRepository.findAllByPostId(postId);
	}


}
