package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.KeywordReview;
import gritbus.hipchonbackend.Repository.custom.KeywordReviewRepositoryCustom;

public interface KeywordReviewRepository extends JpaRepository<KeywordReview, Long>, KeywordReviewRepositoryCustom {
}
