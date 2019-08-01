package com.controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerTest {
	
	@GetMapping("/get")
	public HashMap<String,?> getTest(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "ravi Narayan");
		map.put("age", 30);
		
		return map;
	}

}
