package gritbus.hipchonbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.LoginType;
import gritbus.hipchonbackend.Domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByLoginTypeAndLoginId(LoginType loginType, String loginId);
	Optional<User> findByLoginId(String loginId);
	Optional<User> findByName(String name);
}
