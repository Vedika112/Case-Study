package com.mobile.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import com.mobile.entity.Mobile;

@Repository
public class MobileDaoImp implements Mobiledao {
    @PersistenceContext
	EntityManager entity;

	@Override
	public Mobile addMobile(Mobile mb) {
				entity.persist(mb);
				return entity.find(Mobile.class, mb.getMobileId());
	}

	@Override
	public Mobile updateMobile(Mobile mb) {
	return entity.merge(mb);
	
	}

	@Override
	public String deleteMobile(int mid) {
		Mobile mb=entity.find(Mobile.class, mid);
		if(mb!=null) {
			entity.remove(mb);
			return "Mobile deleted";
	}
		else return "Mobile not found";
	}
	@Override
	public Mobile getMobile(int mid) {
		return entity.find(Mobile.class, mid);
	}

	@Override
	public List<Mobile> getAllMobile() {
		TypedQuery<Mobile> res=entity.createQuery("Select mb from Mobile mb",Mobile.class);
		return res.getResultList();
	}
	
	
	}


