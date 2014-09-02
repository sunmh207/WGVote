<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<script type="text/javascript">
function fEmptyPasswordCheck(){
	return window.confirm('确实要清空该用户的密码？'); 
} 
</script>
<form id="searchForm" action="${root}/console/user.do">
<div style="float: left; padding-bottom: 0px;">
	姓名 <input name="qryName" value='${qryName}' size="10">
	手机 <input name="qryNumber" value='${qryNumber}' size="11">
	车间<s:select  name="qryDeptName" list="deptList"/> 
	
	职务 <input name="qryZhiwu" value='${qryZhiwu}' size="11">
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
	<input type="button" onclick="location.href='${root}/console/userinput!input.do'" value="新增用户">
</div>
<html:pagination exportExcel="true"/>
</form>
<s:actionerror/>
<table width="101%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">姓名</td>
		<td class="td_header">性别</td>
		<td class="td_header">工号</td>
		<td class="td_header">工种</td>
		<td class="td_header">车间/部门</td>
		<td class="td_header">系数</td>
		<td class="td_header">每考核分对应金额</td>
		
		<td class="td_header">登录名</td>
		<!-- <td class="td_header">单位</td> -->
		
		<td class="td_header">手机号</td>
		<!--<td class="td_header">是否管理员</td>-->
		
		<td class="td_header">岗位</td>
		<td class="td_header">职务</td>
		<td class="td_header">兼职</td>
		<td class="td_header">管理角色</td>
		<td class="td_header">所属角色</td>
		<td class="td_header">操作</td>
	</tr>
	<c:forEach items="${objectList}" var="user" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${user.name}</td>
			<td align="left" class='td_body'>${user.gender}</td>
			<td align="left" class='td_body'>${user.GH}</td>
			<td align="left" class='td_body'>${user.gongzhong}</td>
			<td align="left" class='td_body'>${user.dept}</td>
			<td align="left" class='td_body'>${user.evaRatio}</td>
			<td align="left" class='td_body'>${user.amountPerScore}</td>
			
			<td align="left" class='td_body'>${user.loginName}</td>
			<!-- <td align="left" class='td_body'>${user.unit}</td> -->
			
			<td align="left" class='td_body'>${user.phoneNumber}</td>
			
			<td align="left" class='td_body'>${user.gangwei}</td>
			<td align="left" class='td_body'>${user.zhiwu}</td>
			<td align="left" class='td_body'>${user.jianzhi}</td>
			<td align="left" class='td_body'><html:userRoles userId="${user.id}" /></td>
			<td align="left" class='td_body'><html:personRoles personId="${user.id}" /></td>
			<td align="left" class='td_body'>
			
			<a href="${root}/console/userinput!input.do?user.id=${user.id}">编辑</a>&nbsp;&nbsp;
			<a href="${root}/console/user!delete.do?user.id=${user.id}" onclick="return fDelCheck()">删除</a>&nbsp;&nbsp;
			<a href="${root}/console/user!emptyPassword.do?user.id=${user.id}" onclick="return fEmptyPasswordCheck()"> 清空密码</a>&nbsp;
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>