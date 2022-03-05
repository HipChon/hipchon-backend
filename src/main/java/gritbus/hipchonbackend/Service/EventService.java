package gritbus.hipchonbackend.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Announce;
import gritbus.hipchonbackend.Domain.Event;
import gritbus.hipchonbackend.Dto.AnnounceDto;
import gritbus.hipchonbackend.Dto.EventDto;
import gritbus.hipchonbackend.Repository.EventRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {
	private final EventRepository eventRepository;

	public List<EventDto> findAll(){
		List<Event> all = eventRepository.findAll();

		return all.stream()
			.map(event -> EventDto.of(event))
			.collect(Collectors.toList());
	}

}
