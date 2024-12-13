package vn.iotstar.utescore.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.iotstar.utescore.entity.FootballField;
import vn.iotstar.utescore.services.FootBallFieldService;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private FootBallFieldService footBallFieldService;

	@RequestMapping("/user")
	public String dangNhap() {
		return "user/home";
	}

	@GetMapping("/cacsan")
	public String loadCacSanSanCo() {
		return "user/sanbong-list";
	}
	
    @GetMapping("/api/cacsan") 
    @ResponseBody
    public List<FootballField> getAllFields() {
        return footBallFieldService.getAllFields();
    }
}
