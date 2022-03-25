package com.mobile.service;

import java.util.List;

import com.mobile.entity.Mobile;


public interface MobileService {

	Mobile addMobile(Mobile mb);
	Mobile updateMobile(Mobile mb);
	String deleteMobile(int mid);
	Mobile getMobile(int mid);
	List<Mobile> getAllMobile();
	
}
