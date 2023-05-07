package com.ezmaid.controller;

import static com.ezmaid.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.ChangePassDTO;
import com.ezmaid.entity.User;
import com.ezmaid.exception.PasswordMismatchException;
import com.ezmaid.exception.UserNotFoundException;
import com.ezmaid.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class UserController {

	private UserService userService;
	private final PasswordEncoder passwordEncoder;

	public UserController(UserService userService, PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/changepass")
	public Boolean changePassword(@RequestBody ChangePassDTO changePassDTO) {
		
		System.out.println(changePassDTO);
		
		User existingUser = userService.getUserByUsername(changePassDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException(String.format("Username %s not found", changePassDTO.getUsername())));
		
		if (!passwordEncoder.matches(changePassDTO.getCurrpassword(), existingUser.getPassword())) {
			throw new PasswordMismatchException("Current password entered is not correct!");
		}
		
		existingUser.setPassword(passwordEncoder.encode(changePassDTO.getNewpassword()));
		userService.saveUser(existingUser);
		
		return true;
	}
}
