<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead >
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">

</html:pageHead>
<body>
<s:actionerror/>
<html:pagetitle title="候选人" />
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

<a class="return" href="${root}/vote/ps_candidate.do?vote.id=<s:property value="vote.id"/>" >返回</a> 

<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
       <td rowspan="2" align="center" class="td_header">序号</td>
       <td class="td_header" rowspan="2">姓名</td>
       <td class="td_header" rowspan="2">性别</td>
       <td class="td_header" rowspan="2">出生年月</td>
       <td class="td_header" rowspan="2">推荐单位</td>
       <td class="td_header" rowspan="2">所在单位或部门</td>
       <td class="td_header" rowspan="2">行政职务</td>
       <td class="td_header" rowspan="2">级别</td>
       <td class="td_header" colspan="2">技术职务(资格)</td>
       <td class="td_header" rowspan="2">从事专业</td>
       <td class="td_header" colspan="4">毕业情况</td>
       <td class="td_header" rowspan="2">学位</td>
       <td class="td_header" colspan="3">投票表决</td>
       <td class="td_header" rowspan="2">投票时间</td>
     </tr>
	<tr>
       <td class="td_header">名称</td>
       <td class="td_header">首评(取得)年月</td>
       <td class="td_header">学历</td>
       <td class="td_header">毕业院校</td>
       <td class="td_header">所学专业</td>
       <td class="td_header">毕业年份</td>
       <td class="td_header">同意√	</td>
       <td class="td_header">弃权○</td>
       <td class="td_header">反对×</td>
     </tr>
	
	<s:iterator value="candidateList" status="status">
		<tr>
			<td align="center" class="td_header"> ${status.index+1}</td>
			<td align="left" class='td_body'><s:property value="name"/></td>
			<td align="left" class='td_body'><s:property value="gender"/></td>
			<td align="left" class='td_body'><s:property value="birth"/></td>
			<td align="left" class='td_body'><s:property value="recommendUnit"/></td>
			<td align="left" class='td_body'><s:property value="unit"/></td>
			<td align="left" class='td_body'><s:property value="xingzhengPost"/></td>
			<td align="left" class='td_body'><s:property value="xingzhengLevel"/></td>
			<td align="left" class='td_body'><s:property value="techPost"/></td>
			<td align="left" class='td_body'><s:property value="techPostObtainDate"/></td>
			<td align="left" class='td_body'><s:property value="workMajor"/></td>
			<td align="left" class='td_body'><s:property value="xueli"/></td>
			<td align="left" class='td_body'><s:property value="university"/></td>
			<td align="left" class='td_body'><s:property value="major"/></td>
			<td align="left" class='td_body'><s:property value="graduateYear"/></td>
			<td align="left" class='td_body'><s:property value="degree"/></td>
			<td align="left" class='td_body'><s:property value="agreementNumber"/></td>
			<td align="left" class='td_body'><s:property value="abstentionNumber"/></td>
			<td align="left" class='td_body'><s:property value="againstNumber"/></td>
			<td align="left" class='td_body'><s:property value="createTime"/></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>