package gritbus.hipchonbackend.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Repository.MypostRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MypostService {
	private final MypostRepository mypostRepository;

	public List<MypostDto> findMypost(Long userId) {
		return mypostRepository.findMypost(userId);
	}

}
