package gritbus.hipchonbackend.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gritbus.hipchonbackend.Repository.HashtagRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HashtagService {
	private final HashtagRepository hashtagRepository;

}
