package vn.iotstar.utescore.userController;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
	
	@RequestMapping("/user")
	public String dangNhap() {
		return "user/home";
	}
	
}
