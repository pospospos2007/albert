package com.zdcf.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 文章
 * @author Li
 *
 */

@Data
public class Airticle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4594642554359915857L;



	/**
	 * 文章id
	 */
	private Integer airticleId;

	/**
	 * 标题
	 */
	private String title;
	
	
	/**
	 * 内容
	 */
	private String content;
	
	private Date updateTime;
	
	private Date insertTime;
	

	

}
