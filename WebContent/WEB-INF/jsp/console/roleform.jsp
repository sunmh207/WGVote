<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<center>

<s:actionerror />
<s:actionmessage />
<s:form action="roleinput!save.do" method="post">
<table width="101%" class="table" cellpadding="0" cellspacing="0" align="center">

<s:hidden name="user.id" value="%{user.id}" />
 <s:hidden name="role.roleId" value="%{role.roleId}"/>
    <s:hidden name="role.createTime" value="%{role.createTime}"/>
    <s:hidden name="role.creatorId" value="%{role.creatorId}"/>
    <s:hidden name="role.creatorName" value="%{role.creatorName}"/>    
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">角色名</td>
        <td class="td_edit">
		    <s:textfield name="role.roleName" value="%{role.roleName}" label="角色名称" size="40"/>
		  	<s:fielderror ><s:param>role.roleName</s:param></s:fielderror>  
  		</td>
    </tr>
    
    <tr>
        <td width="90" height="24" align="right" class="td_lable">操作</td>
        <td class="td_edit">
		    <s:submit value="确定"/>
		    <s:submit value="取消" name="redirectAction:role"/>
   		 </td>
    </tr>
 </table>   
</s:form>
</center>
</body>
</html>