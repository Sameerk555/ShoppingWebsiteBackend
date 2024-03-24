package com.ecom.payload;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class UserDao {
	
	private int userId;
	private String name;
	private String email;
	private String password;
	private String address;
	private String about;
	private String gender;
	private String phone;
	private Date date;
	private boolean active;  
	

}
