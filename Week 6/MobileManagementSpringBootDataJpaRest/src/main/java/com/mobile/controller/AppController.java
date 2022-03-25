package com.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.entity.Mobile;
import com.mobile.service.MobileService;

@RestController
@RequestMapping("/vedika") //http://localhost:5436/vedika
public class AppController {
	@Autowired
	MobileService service;
	@PostMapping("/addMobile")   //http://localhost:5436/vedika/addMobile
	public Mobile addMobile(@RequestBody Mobile mb) {
		return service.addMobile(mb);
	}
	
	@PutMapping("/updateMobile")  //http://localhost:5436/vedika/updateMobile
	public Mobile updateMobile(@RequestBody Mobile mb) {
		return service.updateMobile(mb);
	}
	@GetMapping("/getMobile/{mid}")  //http://localhost:5436/vedika/getMobile/{mid}
	public Mobile getMobile(@PathVariable("mid")int mid) {
		return service.getMobile(mid);
	}
    @DeleteMapping("/deleteMobile/{mid}")  //http://localhost:5436/vedika/deleteMobile/{mid}
	public String deleteMobile(@PathVariable("mid")int mid) {
		return service.deleteMobile(mid);
	}  
    @GetMapping("/getAllMobile")       //http://localhost:5436/vedika/getAllMobile
    public List<Mobile> getAllMobile(){
    	return service.getAllMobile();
    }
}
