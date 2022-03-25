package com.mobile.dao;

import java.util.List;

import com.mobile.entity.Mobile;

public interface Mobiledao {

	Mobile addMobile(Mobile e);
	Mobile updateMobile(Mobile e);
	String deleteMobile(int eid);
	Mobile getMobile(int eid);
	List<Mobile> getAllMobile();

}
