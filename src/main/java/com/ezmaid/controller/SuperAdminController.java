package com.ezmaid.controller;

import static com.ezmaid.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.entity.Admin;
import com.ezmaid.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/super")
public class SuperAdminController {

	private AdminService adminService;

	public SuperAdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/admins")
	public List<Admin> getAdmins() {
		return adminService.fetchAll().stream().filter(admin -> !admin.getIsSuperAdmin()).collect(Collectors.toList());
	}
}
