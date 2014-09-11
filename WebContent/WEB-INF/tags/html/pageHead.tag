<%@ attribute name="title" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" content="no-cache"/>
<link rel="stylesheet" href="${root}/css/style.css" />
<link rel="stylesheet" href="${root}/css/blitzer/jquery-ui-1.10.4.custom.css" />
<link rel="stylesheet" href="${root}/css/vote.css" />

<script  src="${root}/js/jquery-1.10.1.min.js"></script>
<script  src="${root}/js/jquery-ui-1.10.3.custom.js"></script>
<script  src="${root}/js/vote.js"></script>
<script  src="${root}/js/jtmobile.js"></script> 

<script>
$(function() {   
    $( ".selector" ).datepicker({ dateFormat: "yy-mm-dd" });
  });
</script>

<!-- <link href="/css/style.css" rel="stylesheet" type="text/css"></link>
<link href="/css/tab.css" rel="stylesheet" type="text/css"></link>
<link href="/css/jquery-ui-1.8.7.custom.css" rel="stylesheet" type="text/css"></link>

<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="/js/PopupCalendar.js"></script> -->



<title>
<c:choose>
	<c:when test="${empty title}">${category.display}</c:when>
	<c:otherwise>${title}</c:otherwise>
</c:choose>
</title>

<jsp:doBody/>
</head>