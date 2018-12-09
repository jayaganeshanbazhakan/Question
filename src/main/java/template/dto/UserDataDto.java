package main.java.template.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import main.java.template.encrypt.PasswordEncryptor;

public class UserDataDto {
	
	
	@JsonCreator
	UserDataDto(@JsonProperty("fname")String fname,@JsonProperty("lname")String lname,
	@JsonProperty("mailid")String mailid,@JsonProperty("pwd")String passwd) throws Exception{
    	this.first_name = fname;
    	this.last_name = lname;
    	this.email_id = mailid;
    	this.password = encrypt(passwd);
    	this.user_id = UUID.randomUUID();
    	this.confirmKey = UUID.randomUUID();
    }
	
	private String encrypt(String passwrd) throws Exception {
		PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
		return passwordEncryptor.encrypt(passwrd);
	}
	
	UUID user_id;
	
	String first_name;
	
	String last_name;
	
	String email_id;
	
	String password;
	
	UUID confirmKey;
	
	public UUID getUser_id() {
		return user_id;
	}

	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}

	public UUID getConfirmKey() {
		return confirmKey;
	}

	public void setConfirmKey(UUID confirmKey) {
		this.confirmKey = confirmKey;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
