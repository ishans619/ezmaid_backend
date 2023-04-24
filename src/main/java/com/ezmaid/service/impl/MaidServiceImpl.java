package com.ezmaid.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezmaid.entity.Maid;
import com.ezmaid.repository.MaidDao;
import com.ezmaid.service.MaidService;

@Service
public class MaidServiceImpl implements MaidService {
	
	private MaidDao maidDao;
		

	public MaidServiceImpl(MaidDao maidDao) {
		super();
		this.maidDao = maidDao;
	}


	@Override
	public String saveMaid(Maid maid) {
		maid.setPassword("password");
		maid.setIsFirstLogin(true);
		maid.setIsVerified(false);
		maid.setCrtdDate(LocalDate.now());
		maid.setLstUpdtDate(LocalDate.now());
		maid.setLstUpdtBy("system");
		
		maid = maidDao.save(maid);
		return maid.getMaidId();
	}


	@Override
	public Maid fetchOne(String maidId) {
		Optional<Maid> maid =  maidDao.findById(maidId);
		return maid.get();
	}


	@Override
	public void deleteOne(String maidId) {
		maidDao.deleteById(maidId);
	}


	@Override
	public List<Maid> fetchAll() {
		return maidDao.findAll();
	}


	@Override
	public String updateMaid(Maid existingMaid) {
		existingMaid = maidDao.save(existingMaid);
		return existingMaid.getMaidId();
	}

}
