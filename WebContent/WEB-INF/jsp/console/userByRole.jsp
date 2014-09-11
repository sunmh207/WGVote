<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<script type="text/javascript">
<!--
function forAdd() {
	//var frm = document.forms(0);
	var frm =document.getElementById('userForm');
	if(frm.useridStr.value==null||frm.useridStr.value==""){
		alert("请选择人员！");
		return false;
	}
	frm.action="${root}/console/userrole!addUsers.do";  
 	frm.submit();
}
function forCancel() {
	//var frm = document.forms(0);
	var frm =document.getElementById('userForm');
	frm.action="${root}/console/role.do";
 	frm.submit();
}
function forDel() {
   // var form = document.forms(0);
   var form =document.getElementById('userForm');
    if (checkMutilSelect(form.chk)) {
        if (confirm("确认删除？")) {
        	form.action="${root}/console/userrole!deleteUsers.do";
            form.submit();
        }
    } else {
        alert("请选择需要删除的记录！")
    }
    return false;
}
//-->
</script>
<body>

<table width="101%"  border="0" cellspacing="2" cellpadding="0" align="center">
  <tr>
    <td class="page_title">角色信息</td>    
  </tr>
</table>
<s:actionerror/>
<s:form id="userForm" action="userrole.do" method="post">
<s:hidden name="role.roleId" value="%{role.roleId}"/>
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
	<tr>
        <td width="90" height="24" align="right" class="td_lable">角色名称</td>
        <td class="td_edit" align="left">
			<s:label value="%{role.roleName}"/>
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable">创建人</td>
        <td class="td_edit" align="left">		
			<s:label value="%{role.creatorName}"/>		 
        </td>
    </tr>
	<tr>
        <td width="90" height="24" align="right" class="td_lable">创建日期</td>
        <td class="td_edit" align="left">		
			<s:label value="%{role.createTime}"/>			 
        </td>
    </tr>
	<tr>
        <td width="90" height="24" align="right" class="td_lable">选择用户</td>
        <td class="td_edit" align="left">	
			<html:person idName="useridStr"  nameName="userNameStr"/>
			<input name="add" type="button" class="button" value="加入本角色" onClick="forAdd()">
			<input name="add" type="button" class="button" value="返回" onClick="forCancel()">
			<a href="${root}/console/userrole!addUsers.do?user.id=${user.id}"></a>
		</td>
    </tr>
</table>	

<table width="100%"  border="0" cellspacing="2" cellpadding="0" align="center">
  <tr>
    <td class="page_title">角色成员 
    <input name="del" type="button" class="button" value="删除选中成员" onClick="javascript:forDel()">
    </td>    
  </tr>
</table>
<table width="100%" class="table" align="center" cellpadding="1"
	cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">
			 <input type=checkbox name=all onClick="selectAll(form.all,form.chk)">
		</td>
		<td class="td_header">姓名</td>
		<td class="td_header">登录名</td>
		<td class="td_header">是否管理员</td>
		<td class="td_header">操作</td>
	</tr>
	
	<s:iterator value="users" status="status">
		<tr>
			<td align="center" class="td_header">
			 <input type="checkbox" name="chk" value='<s:property value="id"/>'>
			</td>
			<td align="left" class='td_body'><s:property value="name"/></td>
			<td align="left" class='td_body'><s:property value="loginName"/></td>
			<td align="left" class='td_body'>
			<s:if test="isAdmin=='true'">
				<font color="red">是</font>
			</s:if>
			<s:else>
			否
			</s:else>
			</td>
			<td align="left" class='td_body'>
				<a href="${root}/console/userrole!deleteUser.do?userId=<s:property value="id"/>&role.roleId=${role.roleId}&id=${id}" onclick="return fDelCheck()">删除</a>
			</td>
		</tr>
	</s:iterator>
</table>
</s:form>
<br>
</body>
</html>