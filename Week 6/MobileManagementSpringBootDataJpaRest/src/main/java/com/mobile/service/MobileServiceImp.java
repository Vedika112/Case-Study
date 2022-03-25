package com.mobile.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.dao.Mobiledao;
import com.mobile.entity.Mobile;
@Service
@Transactional
public class MobileServiceImp implements MobileService {

    @Autowired
	Mobiledao dao;
	
	@Override
	public Mobile addMobile(Mobile mb) {
		
		return dao.addMobile(mb);
	}

	@Override
	public Mobile updateMobile(Mobile mb) {
		// TODO Auto-generated method stub
		return dao.updateMobile(mb);
	}

	@Override
	public String deleteMobile(int mid) {
		// TODO Auto-generated method stub
		return dao.deleteMobile(mid);
	}

	@Override
	public Mobile getMobile(int mid) {
		// TODO Auto-generated method stub
		return dao.getMobile(mid);
	}

	@Override
	public List<Mobile> getAllMobile() {
		// TODO Auto-generated method stub
		return dao.getAllMobile();
	}

}
