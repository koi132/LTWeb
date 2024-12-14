package vn.iotstar.utescore.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.iotstar.utescore.entity.User;
import vn.iotstar.utescore.services.UserService;

@Controller
@RequestMapping("/")
public class AdminController {

	
	private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        
     // In dữ liệu vào console
	    System.out.println("user: " + users);
        model.addAttribute("users", users);
        return "admin/index";
    }

    @GetMapping("/admin/user/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {
	    try {
	    	userService.deleteUser(userId);  // Gọi phương thức delete từ service
	        return ResponseEntity.ok("Xóa tài khoản thành công!");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Không thể xóa tài khoản. Vui lòng thử lại.");
	    }
	}
    
    @GetMapping("/admin/edituser/{id}")
    public String editUserForm(@PathVariable("id") int userId, Model model) {
        // Lấy thông tin người dùng từ CSDL
        User user = userService.getUserById(userId);
        if (user == null) {
            // Nếu không tìm thấy người dùng, chuyển hướng về trang lỗi
            return "redirect:/admin/usernotfound";
        }

        // Đưa thông tin người dùng vào model để hiển thị trên trang
        model.addAttribute("user", user);
        return "admin/edituser"; // Trả về trang edituser.html
    }

    @PostMapping("/admin/updateuser/{id}")
    public String updateUser(
            @PathVariable("id") int userId,
            @ModelAttribute("user") User updatedUser,
            RedirectAttributes redirectAttributes) {
        try {
            User existingUser = userService.getUserById(userId);
            if (existingUser == null) {
                redirectAttributes.addFlashAttribute("error", "Người dùng không tồn tại.");
                return "redirect:/admin/usernotfound";
            }

            // Cập nhật thông tin người dùng
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setRole(updatedUser.getRole());

            userService.updateUser(existingUser);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công.");
            return "redirect:/admin/users"; // Chuyển hướng về trang danh sách người dùng
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cập nhật thất bại. Vui lòng thử lại.");
            return "redirect:/admin"; // Quay lại trang chỉnh sửa
        }
    }
    
 // Hiển thị form thêm tài khoản
    @GetMapping("/adduser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User()); // Tạo đối tượng User trống để điền vào form
        return "admin/adduser"; // Tên của file HTML
    }

    // Xử lý form thêm tài khoản
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute User user) {
        try {
            // Kiểm tra nếu email đã tồn tại
            if (userService.isEmailExists(user.getEmail())) {
                return "redirect:/admin/adduser?error=email"; // Thêm thông báo lỗi nếu email đã tồn tại
            }

            // Lưu tài khoản mới vào CSDL
            userService.addUser(user);
            return "redirect:/admin"; // Quay lại trang quản lý người dùng sau khi thêm
        } catch (Exception e) {
            return "redirect:/admin/adduser?error=failed"; // Thông báo lỗi nếu gặp vấn đề khi thêm tài khoản
        }
    }

}
