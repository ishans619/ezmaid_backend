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

import com.ezmaid.dto.CustomerDTO;
import com.ezmaid.entity.Customer;
import com.ezmaid.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;

	}


	@PostMapping(path = "/customers")
	public String save(@RequestBody CustomerDTO customerDTO) {
		System.out.println("customerDTO = "+ customerDTO);
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		System.out.println("Copied values to customer: " + customer);
		String custId = customerService.saveCustomer(customer);
		return custId;

	}

	@PutMapping(path = "/customers")
	public String update(@RequestBody CustomerDTO customerDTO) {

		System.out.println("customerDTO = "+ customerDTO);
		Customer existingCustomer = customerService.fetchOne(customerDTO.getCustId());
		BeanUtils.copyProperties(customerDTO, existingCustomer);
		System.out.println("Copied values to customer: " + existingCustomer);
		String custId = customerService.updateCustomer(existingCustomer);
		return custId;


	}

	@GetMapping(path = "/customers/{custId}")
	public Customer fetchOne(@PathVariable("custId") String custId) {
		return customerService.fetchOne(custId); 

	}

	@GetMapping(path = "/customers")
	public List<Customer> fetchAll() {
		return customerService.fetchAll(); 

	}

	@DeleteMapping(path = "/customers/{custId}")
	public String delete(@PathVariable("custId") String custId) {
		customerService.deleteOne(custId); 
		return "deleted";
	}

}


