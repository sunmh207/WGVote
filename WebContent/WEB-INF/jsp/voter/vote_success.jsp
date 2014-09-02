<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<head>
<title>投票表决系统</title>
<style type="text/css">
#apDiv1 {
	position:absolute;
	left:190px;
	top:80px;
	width:750px;
	height:1140px;
	z-index:1;
	text-align: center;
}
#apDiv1 p {
	font-size: 36px;
}
.end01 {
	text-align: center;
	font-size: 24px;
}
</style>
</head>

<body>
<div class="end01">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p><img src="images/200782117178994_2.jpg" alt="" width="152" height="141" />已成功完成投票，感谢您的参与！
		<a class="ui_icon_person" href="${root}/toupiao.do"> 返回继续投票</a>&nbsp;&nbsp;
  </p>
  <p>&nbsp;</p>
</div>
</body>
</html>
