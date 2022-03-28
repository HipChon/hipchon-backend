package gritbus.hipchonbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
