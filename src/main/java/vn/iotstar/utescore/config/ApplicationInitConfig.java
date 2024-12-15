package vn.iotstar.utescore.config;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.iotstar.utescore.entity.User;
import vn.iotstar.utescore.repository.UserRepository;
import lombok.Data;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Builder
public class ApplicationInitConfig {

	PasswordEncoder passwordEncoder;

	@Bean
	ApplicationRunner applicationRunner(UserRepository userRepository) {
		return args -> {
			if (userRepository.findUserByEmail("admin@admin.com").isEmpty()) {
				User ad = User.builder()
						.email("admin@admin.com")
						.password(passwordEncoder.encode("admin"))
						.role("ADMIN")
						.build();

				userRepository.save(ad);
				log.warn("Người dùng Admin đã được tạo với mật khẩu mặc định, vui lòng đổi mật khẩu!");
			}
		};
	}
}
