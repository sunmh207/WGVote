<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jitong.JitongConstants" %>
<%@ page import=" com.jitong.common.util.DBtools" %>
<%@ page import=" com.jitong.common.service.DefaultBaseService" %>
<%@ page import="com.jitong.common.util.FileUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%
	 String url = "";
     List<?> list = (List<?>) session.getAttribute(JitongConstants.SESSION_OBJECT);
     String path = application.getRealPath("/") + File.separator + "file" + File.separator + "tmp";
     String fileName = FileUtil.exportFile(path, list, true);
     url = request.getContextPath() + "/file/tmp/" + fileName;
     response.sendRedirect(url);

%>