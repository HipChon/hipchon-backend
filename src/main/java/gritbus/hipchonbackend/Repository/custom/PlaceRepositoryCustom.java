package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Domain.Place;

public interface PlaceRepositoryCustom {
	List<Place> fastSearch(PlaceFastSearchCondition condition);
}
