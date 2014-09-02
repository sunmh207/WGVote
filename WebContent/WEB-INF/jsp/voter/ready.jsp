<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<title>投票表决系统</title>
<script language="JavaScript" type="text/javascript">
<!--
function refresh(){
	var frm = document.forms["voteform"];
	frm.submit();
}
var id=window.setTimeout(refresh,5000);
//-->
</script>
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

<form id="voteform" action="${root}/toupiao.do" method="post">
<s:hidden name="vote.id"/>
<%-- <s:hidden name="voter.id"/> --%>
    <div class="bg">
    	
    
   	<div class="message">
   	<s:if test="vote==null||vote.name== null">
   		暂时没有投票信息！
   	</s:if>
   	<s:else>
   		投票名称: <s:property value="vote.name"/><br>   		
   		<s:property value="message"/>
   	</s:else>	
 	</div>
   	
   	
    </div>
</form>

</body>
</html>
