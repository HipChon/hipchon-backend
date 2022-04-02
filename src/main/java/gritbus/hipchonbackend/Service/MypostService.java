package gritbus.hipchonbackend.Service;

import static gritbus.hipchonbackend.error.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.Post.Mypost;
import gritbus.hipchonbackend.Domain.Post.Post;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Repository.MypostRepository;
import gritbus.hipchonbackend.Repository.PlaceRepository;
import gritbus.hipchonbackend.Repository.PostRepository;
import gritbus.hipchonbackend.Repository.UserRepository;
import gritbus.hipchonbackend.exception.ElementDuplicatedException;
import gritbus.hipchonbackend.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MypostService {
	private final MypostRepository mypostRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public List<MypostDto> findMypost(Long userId) {
		return mypostRepository.findMypost(userId);
	}

	@Transactional
	public Long add(Long userId, Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(()-> new NoSuchElementException(
			POST_NOT_FOUND.getErrorCode(), POST_NOT_FOUND));

		User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException(
			UNAUTHORIZED_USER.getErrorCode(), UNAUTHORIZED_USER
		));

		if (mypostRepository.existsByUserAndPost(user, post)) {
			throw new ElementDuplicatedException(ELEMENT_DUPLICATION.getErrorCode(), ELEMENT_DUPLICATION);
		}

		return mypostRepository.save(Mypost.createMypost(user, post)).getId();
	}

	@Transactional
	public void delete(Long userId, Long postId) {
		Mypost mypost = mypostRepository.findByUserIdAndPostId(userId, postId)
			.orElseThrow(() -> new NoSuchElementException(ELEMENT_NOT_FOUND.getErrorCode(), ELEMENT_NOT_FOUND));

		mypostRepository.delete(mypost);
	}
}
