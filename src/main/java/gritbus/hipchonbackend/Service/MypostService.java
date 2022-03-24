package gritbus.hipchonbackend.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Cond.MyplaceCondition;
import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.Mypost;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Dto.MyplaceDto;
import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Repository.MyplaceRepository;
import gritbus.hipchonbackend.Repository.MypostRepository;
import gritbus.hipchonbackend.Repository.PlaceRepository;
import gritbus.hipchonbackend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MypostService {
	private final MypostRepository mypostRepository;

	public List<MypostDto> findMypost(Long userId){
		return mypostRepository.findMypost(userId);
	}


}
