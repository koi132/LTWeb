package vn.iotstar.utescore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.utescore.entity.User;
import vn.iotstar.utescore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// Phương thức lấy tất cả người dùng
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void deleteUser(int userId) {
		userRepository.deleteById(userId); // Sử dụng trực tiếp phương thức deleteById
	}

	public User getUserById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public void updateUser(User user) {
		userRepository.save(user);
	}

	// Kiểm tra nếu email đã tồn tại
	public boolean isEmailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	// Thêm người dùng mới vào CSDL
	public void addUser(User user) {
		userRepository.save(user);
	}

	public Optional<User> getUserById1(int userId) {
		return userRepository.findById(userId);
	}
}
