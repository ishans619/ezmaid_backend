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

import com.ezmaid.dto.MaidDTO;
import com.ezmaid.entity.Maid;
import com.ezmaid.service.MaidService;

@RestController
@CrossOrigin
public class MaidController {
	
	private MaidService maidService;
	
	public MaidController(MaidService maidService) {
		super();
		this.maidService = maidService;
	}

	@PostMapping(path = "/maids")
	public String save(@RequestBody MaidDTO maidDTO) {
		System.out.println("maidDTO = "+ maidDTO);
		Maid maid = new Maid();
		BeanUtils.copyProperties(maidDTO, maid);
		System.out.println("Copied values to maid: " + maid);
		String maidId = maidService.saveMaid(maid);
		return maidId;
		
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
	

}
