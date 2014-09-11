<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<html:pageHead />
<body>
<form id="searchForm">
<div style="float: left; padding-bottom: 0px;">
角色名 <input name="qryRoleName" value='${qryRoleName}' size="10">
创建人 <input  name="qryCreatorName" value='${qryCreatorName}' size="10">
<input type="button" class="button" value="搜索" onclick="javascript:goPage(1)"> 

	<input type="button" class="button" onclick="location.href='${root}/console/roleinput!input.do'" value="新增角色">
</div>
<html:pagination exportExcel="true"/>
</form>

		
<table width="101%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">所属单位</td>
		<td class="td_header">角色名</td>
		<td class="td_header">创建人</td>
		<td class="td_header">创建时间</td>		
		<td class="td_header">操作</td>    
	</tr>	
	<c:forEach items="${objectList}" var="role" varStatus="status">
	<tr>
		<td align="center" class="td_header">${status.index+pager.startRecord}</td>
		<td align="left" class='td_body'><html:personUnit id="${role.creatorId}"/></td>
		<td align="left" class='td_body'>${role.roleName}</td>
		<td align="left" class='td_body'>${role.creatorName}</td>
		<td align="left" class='td_body'>${role.createTime}</td>
		<td align="left" class='td_body'>	
		<a href="${root}/console/roleinput!input.do?role.roleId=${role.roleId}">编辑</a>&nbsp;&nbsp;
		<a href="${root}/console/userrole!listUsers.do?role.roleId=${role.roleId}">管理员</a>&nbsp;&nbsp; 
		<a href="${root}/console/rolemenu!listRole.do?role.roleId=${role.roleId}">菜单权限</a>&nbsp;&nbsp; 
		<a href="${root}/console/roleperson!listPersons.do?role.roleId=${role.roleId}">成员</a>&nbsp;&nbsp; 
		<a href="${root}/console/role!delete.do?role.roleId=${role.roleId}" onclick="return fDelCheck()">删除</a>               
        </td>
	</tr>
	</c:forEach>
</table>
注：<br>
<strong>管理员</strong>是指拥有该角色的用户<br>
<strong>成员</strong>是指该角色能够对哪些人员进行操作（比如查看，修改）<br>
超级管理员Admin能看到所有角色，普通管理员只能看到自己创建的角色<br>
</body>
</html>