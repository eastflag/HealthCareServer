package com.healthcare.common;

/**
 * 서로다른 JSP 에서 공통의 정보를 저장하고 참조해오는
 * 키를 저장한다.
 * 주로 Session 객체의 키를 정의하는 인터페이스 역할을 수행
 */
public interface WebKeys
{
    public static final String ScreenManagerKey 	= "screenManager";
    public static final String CurrentScreen 		= "currentScreen";
    public static final String PreviousScreen 		= "previousScreen";
    public static final String LanguageKey 			= "language";
    public static final String URLMappingsKey 		= "urlMappings";
    public static final String UserKey 				= "loginSession";
    public static final String LoginNameKey 		= "loginName";
    public static final String RoleIdKey 			= "roleId";
    public static final String SchoolKey 			= "schoolId";
    public static final String DeptNameKey 			= "deptName";
    public static final String SigninTargetURL 		= "current_menu";
    public static final String AccessTimes			= "accessTimes";
    public static final String MessagesKey 			= "messages";
    public static final String AdSvrMessagesKey 	= "adSvrMessages";
    public static final String RequestIdKey 		= "requestId";
    public static final String pageNoKey 			= "pageNo";
    public static final String searchKey 			= "searchKey";
    public static final String subOneSearchKey 		= "subOneSearchKey";
    public static final String indexPointKey 		= "indexPointKey";
    public static final String requestKey 			= "requestKey";
    public static final String request2Key 			= "request2Key";
    public static final String requestModifyKey 	= "requestModifyKey";
    public static final String ParameterListenerKey = "ParameterListenerKey";
    public static final String MenuDefinitionsKey 	= "MenuDefinitionsKey";
    public static final String SelectedURL			= "SelectedURL";
    public static final String SelectedMasterKey    = "SelectedURL";
}