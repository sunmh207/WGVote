<%@ tag pageEncoding="utf-8" %>
<%@tag import="com.jitong.common.util.SysCache"%>
<%@tag import="com.jitong.console.domain.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="phoneNumber" type="java.lang.String" required="false"%>
<%
	User user = null;
	if (id != null && !"".equals(id.trim())) {
		user = SysCache.interpertUser(id);
	} else if (phoneNumber != null && !"".equals(phoneNumber.trim())) {
		user = SysCache.interpertUserByPhone(phoneNumber);
	}

	if (user != null) {
		String unit = user.getUnit();
		if (unit != null) {
			out.print(unit);
		}
	}
%>