package gritbus.hipchonbackend.Repository.custom;

import java.util.List;
import java.util.Optional;

import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.Post.Mypost;
import gritbus.hipchonbackend.Domain.Post.Post;
import gritbus.hipchonbackend.Dto.MypostDto;

public interface MypostRepositoryCustom {
	List<MypostDto> findMypost(Long userId);
	Optional<Mypost> findByUserIdAndPostId(Long userId, Long postId);
}
