package com.nokia.security.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bytecode.assign.reference.GenericTypeAwareAssigner;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_TBL")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String uname;
	private String password;
	private String emailId;

}
