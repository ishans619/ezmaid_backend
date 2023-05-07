package com.ezmaid.controller;

import static com.ezmaid.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.SignUpRequest;
import com.ezmaid.entity.Customer;
import com.ezmaid.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/customers")
	public String update(@RequestBody SignUpRequest customerDTO) {
		log.info("customer data passed = "+ customerDTO);
		Customer existingCustomer = customerService.fetchOne(customerDTO.getId());
		BeanUtils.copyProperties(customerDTO, existingCustomer);
		log.info("Copied values to customer: {}", existingCustomer);
		String custId = customerService.updateCustomer(existingCustomer);
		return custId;
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/customers/{custId}")
	public Customer fetchOne(@PathVariable("custId") String custId) {
		return customerService.fetchOne(custId); 
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/customers")
	public List<Customer> fetchAll() {
		return customerService.fetchAll(); 
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@DeleteMapping(path = "/customers/{custId}")
	public String delete(@PathVariable("custId") String custId) {
		customerService.deleteOne(custId); 
		return "deleted";
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final  Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}


