package com.zdcf.service;

import java.util.List;

import com.zdcf.dto.ThemeDTO;
import com.zdcf.model.Airticle;
import com.zdcf.model.Message;
import com.zdcf.model.Theme;
import com.zdcf.model.User;

public interface RobotService {

	public String getAnswerFromRobot(String question,int userId);
	
	public String getZhiHuAirticleList();
	
	public String getZhiHuAirticleDetail(String id);

	String getZhiHuAirticleListBefore(String date);
	
}
