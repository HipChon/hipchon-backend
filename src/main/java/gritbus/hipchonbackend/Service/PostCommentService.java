package gritbus.hipchonbackend.Service;

import static gritbus.hipchonbackend.error.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Post.Mypost;
import gritbus.hipchonbackend.Domain.Post.Post;
import gritbus.hipchonbackend.Domain.Post.PostComment;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Dto.Post.CommentDto;
import gritbus.hipchonbackend.Dto.Requset.CommentRequest;
import gritbus.hipchonbackend.Repository.PostCommentRepository;
import gritbus.hipchonbackend.Repository.PostRepository;
import gritbus.hipchonbackend.Repository.UserRepository;
import gritbus.hipchonbackend.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostCommentService {
	private final PostRepository postRepository;
	private final PostCommentRepository postCommentRepository;
	private final UserRepository userRepository;
	public List<CommentDto> findALlByPostId(Long postId){
		postRepository.findById(postId).orElseThrow(
			()->new NoSuchElementException(POST_NOT_FOUND.getMessage(), POST_NOT_FOUND));

		return postCommentRepository.findAllByPostId(postId);
	}

	@Transactional
	public Long add(CommentRequest request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new NoSuchElementException(
			UNAUTHORIZED_USER.getErrorCode(), UNAUTHORIZED_USER
		));

		Post post = postRepository.findById(request.getPostId()).orElseThrow(()-> new NoSuchElementException(
			POST_NOT_FOUND.getErrorCode(), POST_NOT_FOUND));

		return postCommentRepository.save(PostComment.createPostComment(user, post,request.getDetail())).getId();
	}

	@Transactional
	public void delete(Long commentId) {
		PostComment postComment = postCommentRepository.findById(commentId)
			.orElseThrow(() -> new NoSuchElementException(ELEMENT_NOT_FOUND.getErrorCode(), ELEMENT_NOT_FOUND));

		postCommentRepository.delete(postComment);
	}

}
