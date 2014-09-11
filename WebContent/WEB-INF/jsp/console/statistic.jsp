<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<s:form action="statistic.do" method="post">
<div style="float: left; padding-bottom: 0px;">
发送时间 <s:textfield cssClass="date" name="startDate" value="%{startDate}"/>
 -  <s:textfield cssClass="date" name="endDate" value="%{endDate}"/>
	<s:submit value="统计"/> 
</div>
</s:form>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">类别</td>
		<td class="td_header">状态</td>
		<td class="td_header">数量</td>
		<td class="td_header">合计</td>
	</tr>
	<tr>
		<td align="center" class='td_body' rowspan=3>短信</td>
		<td align="center" class='td_body'>待发送</td>
		<td align="center" class='td_body'>	<a href="${root}/UserSMSList.do?status=0&requestTime_start=${startDate}&requestTime_end=${endDate}">${smsStatus0}</a>&nbsp;	</td>
		<td align="center" class='td_body' rowspan=3><a href="${root}/UserSMSList.do?requestTime_start=${startDate}&requestTime_end=${endDate}">${smstotal}</a></td>
	</tr>
	<tr>
		<td align="center" class='td_body'>成功</td>
		<td align="center" class='td_body'>		<a href="${root}/UserSMSList.do?status=1&requestTime_start=${startDate}&requestTime_end=${endDate}">${smsStatus1}</a>&nbsp;	</td>
		</td>
	</tr>
	<tr>
		<td align="center" class='td_body'>失败</td>
		<td align="center" class='td_body'><a href="${root}/UserSMSList.do?status=2&requestTime_start=${startDate}&requestTime_end=${endDate}">${smsStatus2}</a>&nbsp;
		</td></td>
	</tr>
	<tr>
		<td align="center" class='td_body'  rowspan=3>彩信</td>
		<td align="center" class='td_body'>待发送</td>
		<td align="center" class='td_body'><a href="${root}/UserMMSList.do?status=0&requestTime_start=${startDate}&requestTime_end=${endDate}">${mmsStatus0}</a></td>
		<td align="center" class='td_body' rowspan=3><a href="${root}/UserMMSList.do?requestTime_start=${startDate}&requestTime_end=${endDate}">${mmstotal}</a></td>
	</tr>
	<tr>
		<td align="center" class='td_body'>成功</td>
		<td align="center" class='td_body'><a href="${root}/UserMMSList.do?status=1&requestTime_start=${startDate}&requestTime_end=${endDate}">${mmsStatus1}</a></td>
	</tr>
	<tr>
		<td align="center" class='td_body'>失败</td>
		<td align="center" class='td_body'><a href="${root}/UserMMSList.do?status=2&requestTime_start=${startDate}&requestTime_end=${endDate}">${mmsStatus2}</a></td>
	</tr>
	<tr>
		<td align="center" class='td_body'  rowspan=3>语音</td>
		<td align="center" class='td_body'>新</td>
		<td align="center" class='td_body'><a href="${root}/UserDialList.do?finger=0&dtCreate_start=${startDate}&dtCreate_end=${endDate}">${dialStatus0}</a></td>
		<td align="center" class='td_body' rowspan=3><a href="${root}/UserDialList.do?dtCreate_start=${startDate}&dtCreate_end=${endDate}">${dialtotal}</a></td>
	</tr>
	<tr>
		<td align="center" class='td_body'>成功</td>
		<td align="center" class='td_body'><a href="${root}/UserDialList.do?finger=1&dtCreate_start=${startDate}&dtCreate_end=${endDate}">${dialStatus1}</a></td>
	</tr>
	<tr>
		<td align="center" class='td_body'>失败</td>
		<td align="center" class='td_body'><a href="${root}/UserDialList.do?finger=2&dtCreate_start=${startDate}&dtCreate_end=${endDate}">${dialStatus2}</a></td>
	</tr>
</table>

</body>
</html>