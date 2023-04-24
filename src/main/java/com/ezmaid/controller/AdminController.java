package com.ezmaid.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.AdminDTO;
import com.ezmaid.entity.Admin;
import com.ezmaid.service.AdminService;

@RestController
@CrossOrigin
public class AdminController {

	private AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;

	}


	@PostMapping(path = "/admins")
	public String save(@RequestBody AdminDTO adminDTO) {
		System.out.println("adminDTO = "+ adminDTO);
		Admin admin = new Admin();
		BeanUtils.copyProperties(adminDTO, admin);
		System.out.println("Copied values to admin: " + admin);
		String adminId = adminService.saveAdmin(admin);
		return adminId;

	}

	@PutMapping(path = "/admins")
	public String update(@RequestBody AdminDTO adminDTO) {

		System.out.println("adminDTO = "+ adminDTO);
		Admin existingAdmin = adminService.fetchOne(adminDTO.getAdminId());
		BeanUtils.copyProperties(adminDTO, existingAdmin);
		System.out.println("Copied values to admin: " + existingAdmin);
		String adminId = adminService.updateAdmin(existingAdmin);
		return adminId;


	}

	@GetMapping(path = "/admins/{adminId}")
	public Admin fetchOne(@PathVariable("adminId") String adminId) {
		return adminService.fetchOne(adminId); 

	}

	@GetMapping(path = "/admins")
	public List<Admin> fetchAll() {
		return adminService.fetchAll(); 

	}

	@DeleteMapping(path = "/admins/{adminId}")
	public String delete(@PathVariable("adminId") String adminId) {
		return adminService.deleteOne(adminId); 
		
	}

}


