package vn.iotstar.utescore.controller.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.iotstar.utescore.entity.Thongtinsan;
import vn.iotstar.utescore.services.ThongTinSanService;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class AvailableFootballFieldController {
	@Autowired
	private ThongTinSanService thongTinSanService;

	@RequestMapping("/user")
	public String dangNhap() {
		return "user/home";
	}

	@RequestMapping("/cacsan")
	public String loadCacSanSanCo() {
		return "user/sanbong-list";
	}

	@GetMapping("/api/cacsan")
	public ResponseEntity<List<Thongtinsan>> getAllFields() {
		List<Thongtinsan> fields = thongTinSanService.getAllFields();
		if (fields.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(fields, HttpStatus.OK);
	}
}
