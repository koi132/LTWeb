package vn.iotstar.utescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.utescore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    // JpaRepository đã hỗ trợ sẵn phương thức `findAll()`
	void deleteById(int userId);
	
	 User findByEmail(String email);
}
