package com.zdcf.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户t_file
 * @author Li
 *
 */

@Data
public class Files implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8151630850039344587L;
	
	private Integer id;
	
	private String title;

	private String url;
	
	private String discription;
	
	private Integer userId;
	
	private Date addTime;
	
	private String fileFormat;
	
	
	
}
