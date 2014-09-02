
<%@ tag pageEncoding="utf-8" %>
<%@tag import="com.jitong.common.util.SysCache"%>
<%@tag import="com.jitong.console.domain.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="creatorId" type="java.lang.String" required="true"%>
<%
	java.util.List<Role> myRoles = new java.util.ArrayList<Role>();
	if (creatorId != null && !"".equals(creatorId.trim())) {
		java.util.Iterator it = SysCache.roleMap.values().iterator();
		while(it.hasNext()){
			Role r = (Role)it.next();
			if(creatorId.equals(r.getCreatorId())){
				myRoles.add(r);
			}
		}
	}
	if (!myRoles.isEmpty()) {
		int i=0;
		for(Role r:myRoles){
			if(i>0){out.print(",");}
			out.print(r.getRoleName());
			i++;
		}
	}
%>