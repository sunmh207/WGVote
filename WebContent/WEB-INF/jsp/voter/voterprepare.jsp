<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<title>投票表决系统-管理员登陆</title>
<style type="text/css">
*{margin:0;padding:0}

.bg {
	background-image: url(images/login.jpg);
	repeat: none;
	position: absolute;
	width: 1022px;
	height: 509px;
	left: 514px;
	top: 50%;
	margin-left: -400px;
	margin-top: -267px;
}

 .message {
	position:absolute;
	left:50%;
  	top:50%; 
	margin-left:55px;
  	margin-top:35px;
}

.button {
	position:absolute; 
	left:50%;
  	top:50%; 
	margin-left:50px;
  	margin-top:70px;
} 
</style>
</head>

<body  bgcolor="#C0C0C0">

<form id="login" action="${root}/toupiao!ready.do" method="post">
<s:hidden name="vote.id"/>
<s:hidden name="voter.id"/>
    <div class="bg">
    	
    
   	<div class="message">
   		<strong><s:property value="message"/></strong><br>
 	</div>
   	
   	<s:if test="message == null">
  	<div class="button">
  		投票名称: <s:property value="vote.name"/> <br>
  		如果准备就绪，请点击下面按钮‘准备好了’<br>
       	<input type="submit" id="submit" value=" 准备好了">
    </div>
    </s:if>
    </div>
</form>

</body>
</html>
