package com.pj.service.redis.constant;

public final class RedisConfig {
	
	/**
	 * 后期需要做本地化存储,短信模板具体详情,敏感词详情
	 */
	/*--商户信息模块公用参数 begin(考虑到对象存储存取值都要序列化,分开存储)--*/
	//key
	public static final String MER_ACCOUNT_INFO = "merAccountInfo";//商户账户对应主键信息["key","val"]
	//key+"商户主键"
    public static final String MER_INFO = "merInfo_";//商户个人信息[obj]
    public static final String MER_KEYWORD_WHITELIST = "merKeywordWhitelist";//商户白名单关键字["key1,"key2"],
    public static final String MER_TEMPLATE_LIST = "merTemplateList_";//短信模板内容["obj1_主键","obj2_主键"]
    public static final String MER_SIGNATURE_LIST = "merSignatureList_";//短信签名内容加上主键["obj1_主键","obj2_主键"]
    public static final String CONS_NUMBER_USER_LIST = "consNumberUserList_";//码号和用户之间的关系
    /*--商户信息模块公用参数 end--*/
    
    /*---规则信息 begin---*/
    // key+"运营商"+"业务格式(1-通知,2-营销,3-签名免审)"
    public static final String CONS_PRODUCT = "consProduct_";//产品主键
    //key+产品主键
    public static final String CONS_CHANNEL_LIST = "consChannelList_";//路由有序list["121","13","12"]
    //key+产品主键+_"+用户
    public static final String CONS_USER_CHANNEL_LIST = "consUserChannelList_";//用户指定路由有序list["121","13","12"]
    //key+通道主键
    public static final String CONS_MODULE_LIST = "consModuleList_";//端口有序list["121","13","12"]
    //key+路由主键
    public static final String CONS_ROUTE = "consRoute_";//路由详情[obj]
    //key+模块主键
    public static final String CONS_MODULE = "consModule_";//端口详情[obj]
    //key+模板主键
    public static final String CONS_TEMPLATE = "template_";//模板详情[obj]
    //key+签名主键
    public static final String CONS_SIGNATURE = "signature_";//签名详情[obj]
    //key
    public static final String CONS_MODULE_NUMBER_LIST = "consModuleNumberList";//端口配置码号头开头List["1069","1079"]
    /*---规则信息 end---*/
    
    /*-其他 begin--*/
    //key+手机号码
    public static final String CONS_PHONE = "consPhone_";//手机发送时间控制[obj]
    //key+手机号码+他方订单号/我方订单号
    public static final String CONS_ORDER = "consOrder_";// 我方订单号|商户id
    /*-其他 end--*/
}