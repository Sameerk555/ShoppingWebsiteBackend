package com.ecom.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@AllArgsConstructor
public class Roles {
	@Id
	@Column(name="Role_ID", nullable=false)
	private int roleId;
	@Column(name="Role_name", nullable=false)
	private String roleName;
	
	@ManyToMany(mappedBy="role")
	Set<User>user= new HashSet<>();
}
