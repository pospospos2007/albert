package com.zdcf.tool;

import org.springframework.context.ApplicationContext;

public class Const {
	public static final String SESSION_IMAGE_CODE = "sessionImageCode";//图形验证码
	public static final String SESSION_MOBILE_CODE = "sessionSecCode";//手机验证码
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_COMPANY = "sessionCompany";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_HOTELS = "sessionHotels";
	public static final String SESSION_MEETING = "sessionMeeting";
	public static final String SESSION_BEHALF = "sessionBehalf";
	public static final String SESSION_MEETINGHOTEL = "sessionmeetingHotel";
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化

	//用户角色(100系统管理员  101系统审核员   200服务公司管理员 201员工    300 负责人 )
	public static final short ADMIN = 100;
	public static final short SYSTEM_ASSESSOR = 101;
	public static final short COMPANY_ADMIN = 200;
	public static final short METTING_MAGOR = 201;
	public static final short METTING_HEAD = 300;
	
	//会议进行状态(0未开始  1 进行中  2结束 3删除的会议)
	public static final short METTING_NOT_START = 0;
	public static final short METTING_ASSESSOR = 1;
	public static final short METTING_END = 2;
	public static final short METTING_DELETE = 3;
	
	//费用名称
	public static final String ROOM_FEE = "房费总额";
	public static final String MEAL_FEE = "用餐总额";
	public static final String TRIP_FEE = "考察总费";
	
}
