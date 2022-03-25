package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
