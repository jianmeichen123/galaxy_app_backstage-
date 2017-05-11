package com.galaxy.im.common;

import org.springframework.context.ApplicationContext;

public class StaticConst {
	public static ApplicationContext ctx;
	
	public static final String CONST_SESSION_ID_KEY = "sessionId";		//用户
	public static final String CONST_USER_ID_KEY = "guserId";			//用户ID对应的Key
	public static final String CONST_SYSTEM_TYPE_KEY = "gt";			//请求数据的系统类型（ios,android,pc）
}
