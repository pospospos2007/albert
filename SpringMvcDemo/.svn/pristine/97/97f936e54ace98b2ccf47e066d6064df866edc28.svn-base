package com.zdcf.mapper;

import java.util.HashMap;
import java.util.Map;

public class AirticleSqlProvider {

	
public String getAirticleList(){
		
		StringBuilder sb = new StringBuilder("");
		
		sb.append("select airticle_id as airticleId,title as title," +
				"content as content,insert_time as insertTime,update_time as updateTime " +
				"from airticle order by insert_time desc");
		
		/*Map<String, Object> parameter = (Map<String, Object>) parameters.get("parameters");
		
		if (parameter.get("myStatus") != null
				&& !("").equals(parameter.get("myStatus"))) {
			sb.append(" where status = " + parameter.get("myStatus") );
		}
		if (parameter.get("myUnitId") != null
				&& !("").equals(parameter.get("myUnitId"))) {
			sb.append(" and unit_id =" + parameter.get("myUnitId") );
		}
		if (parameter.get("myConferenceName") != null
				&& !("").equals(parameter.get("myConferenceName"))) {
			sb.append(" and Conference_name like '%" + parameter.get("myConferenceName") + "%'");
		}
		if (parameter.get("myStartDate") != null
				&& !("").equals(parameter.get("myStartDate"))) {
			sb.append(" and start_date >='").append(parameter.get("start_date")+"'");
		}
		if (parameter.get("myEndDate") != null
				&& !("").equals(parameter.get("myEndDate"))) {
			sb.append(" and end_date <= '").append(parameter.get("myEndDate")).append(" 23:59:59'");
		}*/
//		System.out.println(sb);
		
		return sb.toString();
	}

}
