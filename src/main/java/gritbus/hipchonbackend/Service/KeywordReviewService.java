package gritbus.hipchonbackend.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Dto.KeywordDto;
import gritbus.hipchonbackend.Repository.KeywordReviewRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeywordReviewService {
	private final KeywordReviewRepository keywordReviewRepository;

	public List<KeywordDto> getTop3(Long placeId) {
		return keywordReviewRepository.getTop3(placeId);
	}

	public List<KeywordDto> getTop1(Long placeId) {
		return keywordReviewRepository.getTop1(placeId);
	}
}
