package com.ezmaid.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezmaid.entity.Customer;
import com.ezmaid.repository.CustomerDao;
import com.ezmaid.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;
	

	public CustomerServiceImpl(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	public String saveCustomer(Customer customer) {
		customer.setPassword("password");
		customer.setIsFirstLogin(true);
		customer.setIsVerified(false);
		customer.setCrtdDate(LocalDate.now());
		customer.setLstUpdtDate(LocalDate.now());
		customer.setLstUpdtBy("system");
		
		customer = customerDao.save(customer);
		return customer.getCustomerId();
	}
	
	
	@Override
	public Customer fetchOne(String customerId) {
		Optional<Customer> customer =  customerDao.findById(customerId);
		return customer.get();
	}


	@Override
	public void deleteOne(String customerId) {
		customerDao.deleteById(customerId);
	}


	@Override
	public List<Customer> fetchAll() {
		return customerDao.findAll();
	}


	@Override
	public String updateCustomer(Customer existingCustomer) {
		existingCustomer = customerDao.save(existingCustomer);
		return existingCustomer.getCustomerId();
	}

	
}
