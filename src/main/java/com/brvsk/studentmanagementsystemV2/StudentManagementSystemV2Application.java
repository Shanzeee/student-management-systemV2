package com.brvsk.studentmanagementsystemV2;

import com.brvsk.studentmanagementsystemV2.auth.AppUserService;
import com.brvsk.studentmanagementsystemV2.model.entity.AppUser;
import com.brvsk.studentmanagementsystemV2.model.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class StudentManagementSystemV2Application {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemV2Application.class, args);
	}


	@Bean
	CommandLineRunner runner(AppUserService appUserService){
		return args -> {
			appUserService.saveRole(new Role(null, "ROLE_USER"));
			appUserService.saveRole(new Role(null, "ROLE_STUDENT"));
			appUserService.saveRole(new Role(null, "ROLE_TEACHER"));
			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));

			appUserService.saveUser(new AppUser(1L,"Admin","admin","admin",new ArrayList<>()));

			appUserService.addRoleToUser("admin", "ROLE_ADMIN");
			appUserService.addRoleToUser("admin", "ROLE_USER");
		};
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
