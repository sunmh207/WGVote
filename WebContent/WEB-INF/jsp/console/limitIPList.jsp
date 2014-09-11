<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<script type="text/javascript">
<!--
function forAdd(){
	var ip = document.getElementById("newIP").value;
	var caption = document.getElementById("newCaption").value;
	document.location.href="{root}/console/limitip!add.do?newIP="+ip+"&newCaption="+caption;
}

function forQry(){
	var searchIP = document.getElementById("searchIP").value;
	document.location.href="{root}/console/limitip!list.do?searchIP="+searchIP;
}
//-->
</script>
<body>
<s:actionerror/>
<form id="searchForm">

<div style="float: left; padding-bottom: 0px;">
	IP <input name="newIP" id="newIP" value='${newIP}'>
	终端名 <input name="newCaption" id="newCaption" value='${newCaption}'>
	<input type="button" value="增加" onclick="forAdd()">   &nbsp; IP <input id="searchIP" name="searchIP" value='${searchIP}'><input type="button" value="查询" onclick="forQry()">
</div>
<html:pagination exportExcel="true"/>
</form>
<table width="101%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">IP</td>
		<td class="td_header">终端名</td>
		<td class="td_header">创建者</td>
		<td class="td_header">操作</td>
	</tr>
	<c:forEach items="${objectList}" var="ip" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${ip.ip}</td>
			<td align="left" class='td_body'>${ip.caption}</td>
			<td align="left" class='td_body'><html:personName id="${ip.creatorId}" /> <html:personUnit id="${ip.creatorId}" /> <html:personDept id="${ip.creatorId}" /></td>
			<td align="left" class='td_body'>
			<a href="${root}/console/limitip!delete.do?id=${ip.id}"  onclick="return fDelCheck()">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>