package com.zdcf.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户t_user
 * @author Li
 *
 */

@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8579496906223476997L;
	
	private Integer id;
	
	private String username;

	private String password;
	
	private String ip;
	
	private Date registerTime;
	
	private String email;
	
	private String avatar;
	
	private String autoLoginCode;//加密自动登录码
	
	
	
}
