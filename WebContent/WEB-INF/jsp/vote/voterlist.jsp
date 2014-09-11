<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead >
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
</html:pageHead>
<body>
<s:actionerror/>
<html:pagetitle title="投票人" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">投票名称</td>
        <td class="td_edit" colspan="3">		
        	<s:property value="vote.name"/>
        </td>
    </tr>
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">投票时间</td>
        <td class="td_edit">		
			<s:property  value="vote.startDate" />
			<s:property  value="vote.startHour" />:
			<s:property  value="vote.startMinute"/> 
        </td>
        <td width="90" height="24" align="right" class="td_lable">类型</td>
        <td class="td_edit">		
			<s:property  value="vote.type" />
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable"> 要求 </td>
        <td class="td_edit" colspan="3">		
			<s:property value="vote.requirement"  />      
        </td>
    </tr> 
    <tr>
        <td width="90" height="24" align="right" class="td_lable"> 投票说明 </td>
        <td class="td_edit" colspan="3">		
			<s:property value="vote.note" />      
        </td>
    </tr> 
     <tr>
        <td width="90" height="24" align="right" class="td_lable">创建人</td>
        <td class="td_edit">		
			<s:property value="vote.creator"/>
        </td>
        <td width="90" height="24" align="right" class="td_lable">状态</td>
        <td class="td_edit">		
			<s:property value="vote.statusTXT"/>
        </td>
    </tr>  
</table>
<br>

<a class="return" onclick="javascript: window.history.go(-1)" >返回</a> 
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
       <td align="center" class="td_header">序号</td>
       <td class="td_header">IP</td>
       <td class="td_header">Host</td>
       <td class="td_header">最后在线时间</td>
       <td class="td_header">是否提交</td>
       <td class="td_header">提交时间</td>
       <td class="td_header">状态</td>
     </tr>
	
	<s:iterator value="objectList" status="status">
		<tr>
			<td align="center" class="td_header"> ${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'><s:property value="ip"/></td>
			<td align="left" class='td_body'><s:property value="host"/></td>
			<td align="left" class='td_body'><s:property value="readyTime"/></td>
			<td align="left" class='td_body'><s:property value="submitTXT"/></td>
			<td align="left" class='td_body'><s:property value="submitTime"/></td>
			<td align="left" class='td_body'><s:property value="dropTXT"/></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>