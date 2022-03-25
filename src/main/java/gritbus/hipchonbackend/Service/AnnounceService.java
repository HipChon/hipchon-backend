package gritbus.hipchonbackend.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.Announce;
import gritbus.hipchonbackend.Dto.AnnounceDto;
import gritbus.hipchonbackend.Repository.AnnounceRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnnounceService {
	private final AnnounceRepository announceRepository;

	public List<AnnounceDto> findAll() {
		List<Announce> all = announceRepository.findAll();
		return all.stream()
			.map(announce -> AnnounceDto.of(announce))
			.collect(Collectors.toList());
	}
}
