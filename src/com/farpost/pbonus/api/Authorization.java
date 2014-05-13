package com.farpost.pbonus.api;
/*
 * Authorization class for REST Client Spring
 * 
 */
public class Authorization {

	    private String login; // Login 
	    private String password; // Password 
	    private String type_login; // Type Authorization ( default, oAuth: google,vk, facebook)
	    
	    public void setLogin(String login) {
	        this.login = login;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    public void setTypeLogin(String type_login) {
	        this.type_login = type_login;
	    }
	    public String getLogin() {
	        return login;
	    }
	    public String getPassword() {
	        return password;
	    }

	    public String getTypeLogin() {
	        return type_login;
	    }
}
