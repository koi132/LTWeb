package vn.iotstar.utescore.services;

import org.springframework.security.access.prepost.PreAuthorize;
import vn.iotstar.utescore.entity.User;
import vn.iotstar.utescore.request.UserRequest;

import java.util.List;

public interface IUserService {
	public User createUser(User user, String url);
	
	public boolean checkEmail(String email);
	
	User getUserById(int id);
	
	User getUserByEmail(String email);

	boolean checkPassword(String rawPassword, String encodedPassword);
	
	public boolean verifyAccount(String code);

	public String login(UserRequest request);
	
	public List<User> getUserDtls();

	public User getUserByUserId(int id);

	public User getUserByPhone(String phone);

	public void updateUser(User user);

	public User getUserCurentLogged();
	
	public List<User> getUserByRole(String role);

}
