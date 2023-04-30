package com.ezmaid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
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

import com.ezmaid.dto.MaidDTO;
import com.ezmaid.entity.Maid;
import com.ezmaid.service.MaidService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class MaidController {
	
	private MaidService maidService;
	
	public MaidController(MaidService maidService) {
		super();
		this.maidService = maidService;
	}

	@PostMapping(path = "/maids")
	public ResponseEntity<String> save(@Valid @RequestBody MaidDTO maidDTO) {
		System.out.println("maidDTO = "+ maidDTO);
		Maid maid = new Maid();
		BeanUtils.copyProperties(maidDTO, maid);
		System.out.println("Copied values to maid: " + maid);
		String maidId = maidService.saveMaid(maid);
		return ResponseEntity.ok(maidId);
		
	}
	
	@PutMapping(path = "/maids")
	public String update(@RequestBody MaidDTO maidDTO) {
		
		System.out.println("maidDTO = "+ maidDTO);
		Maid existingMaid = maidService.fetchOne(maidDTO.getMaidId());
		BeanUtils.copyProperties(maidDTO, existingMaid);
		System.out.println("Copied values to maid: " + existingMaid);
		String maidId = maidService.updateMaid(existingMaid);
		return maidId;
		
	}
	
	@GetMapping(path = "/maids/{maidId}")
	public Maid fetchOne(@PathVariable("maidId") String maidId) {
		return maidService.fetchOne(maidId); 
		
	}
	
	@GetMapping(path = "/maids")
	public List<Maid> fetchAll() {
		return maidService.fetchAll(); 
		
	}
	
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
