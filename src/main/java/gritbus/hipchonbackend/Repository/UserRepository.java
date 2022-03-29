package gritbus.hipchonbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gritbus.hipchonbackend.Domain.user.LoginType;
import gritbus.hipchonbackend.Domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByLoginTypeAndLoginId(LoginType loginType, Long loginId);
	Optional<User> findByLoginId(Long loginId);
	Optional<User> findByName(String name);
}
