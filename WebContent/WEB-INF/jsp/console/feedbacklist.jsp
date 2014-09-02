<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<center>

<form id="searchForm" action="${root}/console/feedback.do">

是否解决<s:select  name="qryFixed" list="fixedList"/>&nbsp; 
重要性<s:select  name="qrySev" list="sevList"/>&nbsp; 
<input type="submit" value="搜索"> 
<html:pagination exportExcel="true"/>

</form>

<table width="101%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		
		<td class="td_header">严重程度</td>
		<td class="td_header">问题标题</td>
		<td class="td_header">问题描述</td>
		<td class="td_header">时间</td>
		<td class="td_header">反馈人</td>
		<td class="td_header">电话</td>
		<td class="td_header">邮件</td>
		<td class="td_header">是否解决</td>
		<td class="td_header">解决方案</td>
		<td class="td_header">解决者</td>
		<td class="td_header">操作</td>
	</tr>
	<c:forEach items="${objectList}" var="fb" varStatus="idx">
		<tr>
			<td align="center" class="td_header">${idx.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${fb.sev}</td>
			<td align="left" class='td_body'>${fb.title}</td>
			<td align="left" class='td_body'>${fb.desc}</td>
			<td align="left" class='td_body'>${fb.time}</td>
			<td align="left" class='td_body'><html:personName id="${fb.respondentId}"/></td>
			<td align="left" class='td_body'>${fb.phoneNumber}</td>
			<td align="left" class='td_body'>${fb.email}</td>
			
			<td align="left" class='td_body'>${fb.isFixedTXT}</td>
			<td align="left" class='td_body'>${fb.solution}</td>
			<td align="left" class='td_body'><html:personName id="${fb.operatorId}"/></td>
			<td align="left" class='td_body'>
			<a href="${root}/console/feedback!edit.do?feedbackid=${fb.id}">编辑</a>
			</td>
		</tr>
	</c:forEach>
</table>
</center>
</body>
</html>