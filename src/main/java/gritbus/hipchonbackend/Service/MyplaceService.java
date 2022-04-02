package gritbus.hipchonbackend.Service;

import static gritbus.hipchonbackend.error.ErrorCode.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Cond.MyplaceCondition;
import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Dto.MyplaceDto;
import gritbus.hipchonbackend.Repository.MyplaceRepository;
import gritbus.hipchonbackend.Repository.PlaceRepository;
import gritbus.hipchonbackend.Repository.UserRepository;
import gritbus.hipchonbackend.error.ErrorCode;
import gritbus.hipchonbackend.exception.ElementDuplicatedException;
import gritbus.hipchonbackend.exception.NoSuchElementException;
import gritbus.hipchonbackend.exception.UserDuplicatedException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyplaceService {
	private final MyplaceRepository myplaceRepository;
	private final PlaceRepository placeRepository;
	private final UserRepository userRepository;

	@Transactional
	public Long add(Long userId, Long placeId) {
		Place place = placeRepository.findById(placeId).orElseThrow(()-> new NoSuchElementException(
			PLACE_NOT_FOUND.getErrorCode(), PLACE_NOT_FOUND));
		User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException(
			UNAUTHORIZED_USER.getErrorCode(), UNAUTHORIZED_USER
		));

		if (myplaceRepository.existsByUserAndPlace(user, place)) {
			throw new ElementDuplicatedException(ELEMENT_DUPLICATION.getErrorCode(), ELEMENT_DUPLICATION);
		}

		return myplaceRepository.save(Myplace.createMyplace(user, place)).getId();
	}

	@Transactional
	public void delete(Long userId, Long placeId) {
		Myplace myplace = myplaceRepository.findByUserIdAndPlaceId(userId, placeId)
			.orElseThrow(() -> new NoSuchElementException(ELEMENT_NOT_FOUND.getErrorCode(), ELEMENT_NOT_FOUND));

		myplaceRepository.delete(myplace);
	}

	public List<MyplaceDto> findAllMyplace(Long userId) {
		return myplaceRepository.findAllMyplace(userId);
	}
	public List<MyplaceDto> findAllMyplaceByCategoryId(Long userId, Long categoryId){
		return myplaceRepository.findAllMyplaceByCategoryId(userId,categoryId);
	}
	public Long getMyplaceCntByUser(Long userId) {
		return Long.valueOf(myplaceRepository.findAllBy(new MyplaceCondition(userId, null)).size());
	}

}
