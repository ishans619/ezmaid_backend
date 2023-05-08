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
import com.ezmaid.entity.Maid;
import com.ezmaid.service.MaidService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class MaidController {

	private static final Logger log = LoggerFactory.getLogger(MaidController.class);

	private MaidService maidService;

	public MaidController(MaidService maidService) {
		super();
		this.maidService = maidService;
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/maids")
	public String update(@RequestBody SignUpRequest maidDTO) {
		log.info("maid data passed = "+ maidDTO);
		Maid existingMaid = maidService.fetchOne(maidDTO.getId());
		BeanUtils.copyProperties(maidDTO, existingMaid);
		log.info("Copied values to maid: {}", existingMaid);
		String maidId = maidService.updateMaid(existingMaid);
		return maidId;
	}
	
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/maids/verify")
	public String verify(@RequestBody SignUpRequest maidDTO) {
		log.info("maid data passed = "+ maidDTO);
		Maid existingMaid = maidService.fetchOne(maidDTO.getId());
		
		existingMaid.setIsVerified(true);
		
		String maidId = maidService.updateMaid(existingMaid);
		return maidId;
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/maids/{maidId}")
	public Maid fetchOne(@PathVariable("maidId") String maidId) {
		return maidService.fetchOne(maidId); 
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/maids")
	public List<Maid> fetchAll() {
		return maidService.fetchAll(); 
	}

	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@DeleteMapping(path = "/maids/{maidId}")
	public String delete(@PathVariable("maidId") String maidId) {
		maidService.deleteOne(maidId); 
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
