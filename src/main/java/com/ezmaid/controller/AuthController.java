package com.ezmaid.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.AuthResponse;
import com.ezmaid.dto.LoginRequest;
import com.ezmaid.dto.SignUpRequest;
import com.ezmaid.entity.Customer;
import com.ezmaid.entity.Maid;
import com.ezmaid.entity.User;
import com.ezmaid.exception.DuplicatedUserInfoException;
import com.ezmaid.security.TokenProvider;
import com.ezmaid.service.CustomerService;
import com.ezmaid.service.MaidService;
import com.ezmaid.service.UserService;
import com.ezmaid.util.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;
    private final MaidService maidService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

	public AuthController(UserService userService, MaidService maidService, CustomerService customerService,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
		super();
		this.userService = userService;
		this.maidService = maidService;
		this.customerService = customerService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping("/authenticate")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
        return new AuthResponse(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public String signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithUserName(signUpRequest.getUserName())) {
            throw new DuplicatedUserInfoException(String.format("Username %s already been used", signUpRequest.getUserName()));
        }
        
        String savedId = "";

        if (signUpRequest.getIsCustomer()) {
			// map details to Customer
        	Customer customerToBeSaved = new Customer();
        	customerToBeSaved.setfName(signUpRequest.getfName());
        	customerToBeSaved.setmName(signUpRequest.getmName());
        	customerToBeSaved.setlName(signUpRequest.getlName());
        	customerToBeSaved.setEmail(signUpRequest.getEmail());
        	customerToBeSaved.setContactNumber(signUpRequest.getContactNumber());
        	customerToBeSaved.setAdharCardNumber(signUpRequest.getAdharCardNumber());
        	customerToBeSaved.setPanCardNumber(signUpRequest.getPanCardNumber());
        	customerToBeSaved.setAddress(signUpRequest.getAddress());
        	
        	User userToBeSaved = new User();
        	userToBeSaved.setUserName(signUpRequest.getUserName());
        	userToBeSaved.setRole(AppConstants.CUSTOMER);
        	userToBeSaved.setPassword(passwordEncoder.encode("password"));

        	customerToBeSaved.setUser(userToBeSaved);
        	userToBeSaved.setCustomer(customerToBeSaved);
        	
        	// call service to save customer
        	savedId = customerService.saveCustomer(customerToBeSaved);
        	
		} else {
			// map details to Maid
			Maid maidToBeSaved = new Maid();
			
        	maidToBeSaved.setfName(signUpRequest.getfName());
        	maidToBeSaved.setmName(signUpRequest.getmName());
        	maidToBeSaved.setlName(signUpRequest.getlName());
        	maidToBeSaved.setEmail(signUpRequest.getEmail());
        	maidToBeSaved.setContactNumber(signUpRequest.getContactNumber());
        	maidToBeSaved.setAdharCardNumber(signUpRequest.getAdharCardNumber());
        	maidToBeSaved.setPanCardNumber(signUpRequest.getPanCardNumber());
        	maidToBeSaved.setAddress(signUpRequest.getAddress());
        	
        	User userToBeSaved = new User();
        	userToBeSaved.setUserName(signUpRequest.getUserName());
        	userToBeSaved.setRole(AppConstants.MAID);
        	userToBeSaved.setPassword(passwordEncoder.encode("password"));

        	maidToBeSaved.setUser(userToBeSaved);
        	userToBeSaved.setMaid(maidToBeSaved);
        	
        	// call service to save maid
        	savedId = maidService.saveMaid(maidToBeSaved);
		}
        
        return savedId;
    }

    private String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generate(authentication);
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