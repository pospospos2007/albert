package com.zdcf.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Li
 *
 */

public class FaceAttributeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7700427778203223954L;

	private Integer id;
	
	private String gender;

	private String glass;
	
	private Integer age;
	
	private String race;
	
	private Integer face_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGlass() {
		return glass;
	}

	public void setGlass(String glass) {
		this.glass = glass;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Integer getFace_id() {
		return face_id;
	}

	public void setFace_id(Integer face_id) {
		this.face_id = face_id;
	}
	
	
	
	

	
	

}
