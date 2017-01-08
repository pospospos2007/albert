package com.zdcf.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageSqlProvider {

	private static final Log log = LogFactory.getLog(MessageSqlProvider.class);
	
	public String getThemeList(){
		
		StringBuilder sb = new StringBuilder("");
		
		sb.append("select t.id as id,t.theme as theme," +
				"t.content as content,t.add_time as addTime,t.user_id as userId,u.username as username " +
				"from t_theme t left join t_user u on u.id=t.user_id order by t.update_time desc");
		
//		log.info(sb);
		
		return sb.toString();
	}
	
	
	public String getMessagesByThemeId(int id){
		
		StringBuilder sb = new StringBuilder("");
		
		sb.append("select m.id as id,m.message as message," +
				"m.add_time as addTime,t.theme as theme,u.ip as ip " +
				"from t_message m left join t_theme t on t.id=m.theme_id left join t_user u on u.id=m.user_id "
				+ "where m.theme_id="+id
				+ " order by m.add_time asc");
		
//		log.info(sb);
		
		return sb.toString();
	}
	

}
