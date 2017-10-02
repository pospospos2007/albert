package com.zdcf.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户t_message
 * @author Li
 *
 */

@Data
public class Message implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8736721416496463128L;

	private Integer id;
	
	private String message;
	
	private int themeId;
	
	private int userId;
	
	private Date addTime;

	

	
	
	

}
