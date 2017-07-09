package com.zdcf.model;

import lombok.Data;

@Data
public abstract class Scorable extends Cacheable {
    private int totalScore;

    private Integer scoreNum;

    private Double avgScore;
	

	public void setScoreNum(Integer scoreNum) {
		this.scoreNum = scoreNum;
	}
    
    
}
