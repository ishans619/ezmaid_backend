package com.ezmaid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.AdminDTO;
import com.ezmaid.entity.Admin;
import com.ezmaid.exception.ErrorDetails;
import com.ezmaid.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import static com.ezmaid.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@CrossOrigin
public class AdminController {

	private AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@PostMapping(path = "/admins")
	public ResponseEntity<String> save(@Valid @RequestBody AdminDTO adminDTO) {
		System.out.println("adminDTO = "+ adminDTO);
		Admin admin = new Admin();
		BeanUtils.copyProperties(adminDTO, admin);
		System.out.println("Copied values to admin: " + admin);
		String adminId = adminService.saveAdmin(admin);
		return ResponseEntity.ok(adminId);
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/admins")
	public String update(@Valid @RequestBody AdminDTO adminDTO) {

		System.out.println("adminDTO = "+ adminDTO);
		Admin existingAdmin = adminService.fetchOne(adminDTO.getAdminId());
		BeanUtils.copyProperties(adminDTO, existingAdmin);
		System.out.println("Copied values to admin: " + existingAdmin);
		String adminId = adminService.updateAdmin(existingAdmin);
		return adminId;
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/admins/{adminId}")
	public Admin fetchOne(@PathVariable("adminId") String adminId) {
		return adminService.fetchOne(adminId); 
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/admins")
	public List<Admin> fetchAll() {
		return adminService.fetchAll(); 
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@DeleteMapping(path = "/admins/{adminId}")
	public String delete(@PathVariable("adminId") String adminId) {
		return adminService.deleteOne(adminId); 
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
	}
}


