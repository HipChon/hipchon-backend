package gritbus.hipchonbackend.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Dto.HashtagDto;
import gritbus.hipchonbackend.Repository.CityRepository;
import gritbus.hipchonbackend.Repository.HashtagRepository;
import gritbus.hipchonbackend.Repository.PlaceHashtagRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HashtagService {
	private HashtagRepository hashtagRepository;

	public List<HashtagDto> findAll(){
		return hashtagRepository.findAll().stream()
			.map(hashtag -> HashtagDto.of(hashtag))
			.collect(Collectors.toList());
	}
}
