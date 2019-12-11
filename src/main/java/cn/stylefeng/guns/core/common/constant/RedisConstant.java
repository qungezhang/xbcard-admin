package cn.stylefeng.guns.core.common.constant;

/**
 * redis缓存
 * 
 * @author qunge

 */
public class RedisConstant {

	// token存活时间
	public static final Long TOKNE_TIMEOUT = 7L;
	public static final Long SYS_TOKNE_TIMEOUT = 12L;

	// 用于存储userId
	public static final String USER_ID = "wxuser:userId:";

	// 用于存储token
	public static final String USER_TOKEN = "wxuser:token:";


}
