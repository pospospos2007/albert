package com.zdcf.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.mapper.AirticleMapper;
import com.zdcf.model.Airticle;
import com.zdcf.service.AirticleService;

@Service
@Transactional
public class AirticleServiceImpl implements  AirticleService {

	
	@Resource
	private AirticleMapper airticleMapper;
	
	

	/* (non-Javadoc)
	 * @see com.zdcf.service.Impl.AirticleService#getAirticleList()
	 */
	public List<Airticle> getAirticleList(){
		
		List<Airticle> list = airticleMapper.getAirticleList();
		
		return list;
	}



	@Override
	public void addAirticle(Airticle airticle) {

		airticleMapper.addAirticle(airticle);

	}



	@Override
	public Airticle getAirticleDetail(int airticleId) {

		Airticle airticle = airticleMapper.getAirticleDetail(airticleId);
		
		return airticle;
	}
	
}
