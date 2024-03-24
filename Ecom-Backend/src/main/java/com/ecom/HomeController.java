package com.ecom;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/test")
	@ResponseBody
	public HashMap<String, String> test() {
		HashMap<String, String>ob= new HashMap<>();
		ob.put("StudentName", "Sameer");
		ob.put("StudentClass", "final year");
		ob.put("StudentRollNo.", "0901CS191106");
		System.out.println("testing.....");
		return ob;  
		
	}

}
