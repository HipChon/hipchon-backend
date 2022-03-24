package gritbus.hipchonbackend.Service;

import java.util.List;

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
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyplaceService {
	private final MyplaceRepository myplaceRepository;
	private final PlaceRepository placeRepository;
	private final UserRepository userRepository;

	@Transactional
	public Long add(Long userId, Long placeId){
		Place place = placeRepository.findById(placeId).get();
		User user = userRepository.findById(userId).get();
		if (myplaceRepository.existsByUserAndPlace(user, place)){
			// throw new DatabaseException("이미 저장되어 있는 MYPLACE입니다!");
		}
		Myplace save = myplaceRepository.save(Myplace.createMyplace(user, place));
		return save.getId();
	}

	public List<MyplaceDto> findAllMyplace(Long userId){
		return myplaceRepository.findAllMyplace(userId);
	}

	public Long getMyplaceCntByUser(Long userId){
		return Long.valueOf(myplaceRepository.findAllBy(new MyplaceCondition(userId, null)).size());
	}

}
