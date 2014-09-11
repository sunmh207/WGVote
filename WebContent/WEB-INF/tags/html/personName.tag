<%@ tag pageEncoding="utf-8"%>
<%@tag import="com.jitong.common.util.SysCache"%>
<%@tag import="com.jitong.common.util.StringUtil"%>
<%@tag import="com.jitong.console.domain.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="phoneNumber" type="java.lang.String"
	required="false"%>
<%
	if (id != null && !"".equals(id.trim())) {
		out.print(StringUtil.trim(SysCache.interpertUserName(id)));
	} else if (phoneNumber != null && !"".equals(phoneNumber.trim())) {
		out.print(StringUtil.trim(SysCache.interpertUserNameByPhone(phoneNumber)));
	}
%>