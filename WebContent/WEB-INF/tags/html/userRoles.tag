
<%@ tag pageEncoding="utf-8" %>
<%@tag import="com.jitong.console.service.UserRoleService"%>
<%@tag import="com.jitong.common.util.SysCache"%>
<%@tag import="com.jitong.common.util.DBtools"%>
<%@tag import="org.hibernate.Session"%>
<%@tag import="java.util.List"%>
<%@tag import="com.jitong.console.domain.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="userId" type="java.lang.String" required="true"%>
<%!
public String getRolesName(String userId){
	String names="";
	try{
	//Session session = DBtools.getExclusiveSession();
	UserRoleService urs=  new UserRoleService();
	java.util.List<Role> userRoles = urs.queryRoleByUserId(userId);
	System.out.println(userRoles.size());
	if(userRoles!=null&&!userRoles.isEmpty()){
		for(Role r:userRoles){
			if(names!=null&&!names.equals("")){
				names +=","+r.getRoleName();
			}else{
				names = r.getRoleName();
			}
		}
	}else{
		names ="";
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	return names;
}

%>
<%
	out.print(getRolesName(userId));
%>