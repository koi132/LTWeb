package vn.iotstar.utescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iotstar.utescore.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public boolean existsByEmail(String emai);

	public User findByEmail(String email);

	public User findByVerificationCode(String verificationCode);

	public User findByUserId(Integer userId);

	public User findByPhone(String phone);

	public List<User> findByRole(String role);

	public Optional<User> findUserByEmail(String email);

}