package com.zdcf.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * t_images
 * @author Li
 *
 */

@Data
public class Images implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8151630850039344587L;
	
	private Integer id;
	
	private String name;

	private String url;
	
	private Integer userId;
	
	private Date addTime;
	
	private String imageFormat;

}
