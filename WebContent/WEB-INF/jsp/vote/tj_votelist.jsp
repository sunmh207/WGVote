<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead >
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
<script>
function fReadyCheck(){
	return window.confirm("确定进入准备状态?"); 
} 
function fStartCheck(){
	return window.confirm("确定开始投票?"); 
} 
function fStopCheck(){
	return window.confirm("确定结束投票?"); 
} 
</script>
</html:pageHead>
<body>
<html:pagetitle title="推荐表决" />
<form id="searchForm" action="${root}/vote/tj_vote.do">
<div style="float: left; padding-bottom: 0px;">
		&nbsp;投票名称       <input name="qryName" value='${qryName}' size="20">
	  <button class="user-search" onclick="javascript:goPage(1)">查询</button>
	  <a class="ui_icon_circle_plus" href="${root}/vote/tj_voteinput!input.do" >新建</a> 
</div>
<html:pagination exportExcel="false"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
       <td width="40" align="center" class="td_header">序号</td>
       <td class="td_header">投票名称</td>
       <td class="td_header">开始时间</td>
       <td class="td_header">要求</td>
       <td class="td_header">投票说明</td>
       <td class="td_header">创建人</td>
       <td class="td_header">创建时间</td>
       <td class="td_header">类型</td>
       <td class="td_header">状态</td>
       <td class="td_header">操作</td>
     </tr>
	
	 <s:iterator value="objectList" status="st">
	 	<s:if test="status == \"1.new\"">
			<tr bgcolor="#FFFF00">
				<td align="center"><s:property value="%{#st.index+1}" /></td> 
				<td align="left"><s:property value="name"/></td>
				<td align="left"><s:property value="startDate"/> <s:property value="startHour"/>:<s:property value="startMinute"/></td>
				<td align="left"><s:property value="requirement"/></td>
				<td align="left"><s:property value="note"/></td>
				<td align="left"><s:property value="creator"/></td>
				<td align="left"><s:property value="createTime"/></td>
				<td align="left"><s:property value="type"/></td>
				<td align="left"><s:property value="statusTXT"/></td>
				<td align="center">
					<a class="user-edit" href="${root}/vote/tj_voteinput!input.do?vote.id=<s:property value="id"/>">编辑</a>&nbsp;&nbsp;
					<a class="ui_icon_person" href="${root}/vote/tj_candidate.do?vote.id=<s:property value="id"/>" >候选人</a>&nbsp;&nbsp;
					<a class="statics" href="${root}/vote/tj_vote!ready.do?vote.id=<s:property value="id"/>"  onclick="return fReadyCheck()">准备投票</a>&nbsp;&nbsp;
					<a class="user-delete" href="${root}/vote/tj_vote!delete.do?vote.id=<s:property value="id"/>" onclick="return fDelCheck()">删除</a>&nbsp;&nbsp;
				</td>
			</tr>
		</s:if>
		
	 	<s:if test="status == \"2.ready\"">
		<tr bgcolor="#00FF00">
			<td align="center"><s:property value="%{#st.index+1}" /></td> 
			<td align="left"><s:property value="name"/></td>
			<td align="left"><s:property value="startDate"/> <s:property value="startHour"/>:<s:property value="startMinute"/></td>
			<td align="left"><s:property value="requirement"/></td>
			<td align="left"><s:property value="note"/></td>
			<td align="left"><s:property value="creator"/></td>
			<td align="left"><s:property value="createTime"/></td>
			<td align="left"><s:property value="type"/></td>
			<td align="left"><s:property value="statusTXT"/></td>
			<td align="center">
				<a class="ui_icon_person" href="${root}/vote/tj_candidate.do?vote.id=<s:property value="id"/>" >候选人</a>&nbsp;&nbsp;
				<a class="ui_icon_person" href="${root}/vote/voter.do?vote.id=<s:property value="id"/>" >投票人</a>&nbsp;&nbsp;
				<a class="statics" href="${root}/vote/tj_vote!start.do?vote.id=<s:property value="id"/>"  onclick="return fStartCheck()">开始</a>&nbsp;&nbsp;
			</td>
		</tr>
		</s:if>
	 	<s:if test="status == \"3.inprogress\"">
		<tr bgcolor="#FF0000">
			<td align="center"><s:property value="%{#st.index+1}" /></td> 
			<td align="left"><s:property value="name"/></td>
			<td align="left"><s:property value="startDate"/> <s:property value="startHour"/>:<s:property value="startMinute"/></td>
			<td align="left"><s:property value="requirement"/></td>
			<td align="left"><s:property value="note"/></td>
			<td align="left"><s:property value="creator"/></td>
			<td align="left"><s:property value="createTime"/></td>
			<td align="left"><s:property value="type"/></td>
			<td align="left"><s:property value="statusTXT"/></td>
			<td align="center">
				<a class="ui_icon_person" href="${root}/vote/tj_candidate.do?vote.id=<s:property value="id"/>" >候选人</a>&nbsp;&nbsp;
				<a class="ui_icon_person" href="${root}/vote/voter.do?vote.id=<s:property value="id"/>" >投票人</a>&nbsp;&nbsp;
				<a class="statics" href="${root}/vote/tj_vote!stop.do?vote.id=<s:property value="id"/>"  onclick="return fStopCheck()">结束投票</a>&nbsp;&nbsp;
			</td>
		</tr>
		</s:if>
	 	<s:if test="status == \"4.done\"">
		<tr>
			<td align="center"  class='td_body'><s:property value="%{#st.index+1}" /></td> 
			<td align="left" class='td_body'><s:property value="name"/></td>
			<td align="left" class='td_body'><s:property value="startDate"/> <s:property value="startHour"/>:<s:property value="startMinute"/></td>
			<td align="left" class='td_body'><s:property value="requirement"/></td>
			<td align="left" class='td_body'><s:property value="note"/></td>
			<td align="left" class='td_body'><s:property value="creator"/></td>
			<td align="left" class='td_body'><s:property value="createTime"/></td>
			<td align="left" class='td_body'><s:property value="type"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="center" class='td_body'>
				<a class="ui_icon_person" href="${root}/vote/tj_candidate.do?vote.id=<s:property value="id"/>" >统计显示</a>&nbsp;&nbsp;
				<a class="ui_icon_person" href="${root}/vote/voter.do?vote.id=<s:property value="id"/>" >投票人</a>&nbsp;&nbsp;
				<a class="statics" href="${root}/vote/tj_vote.do?primaryVote.id=<s:property value="id"/>" >二次投票</a>&nbsp;&nbsp;
			</td>
		</tr>
		</s:if>
		<s:if test="status == \"x.ready\"">
		<tr  bgcolor="#00FF00">
			<td align="center"><s:property value="%{#st.index+1}" /></td> 
			<td align="left"><s:property value="name"/></td>
			<td align="left"><s:property value="startDate"/> <s:property value="startHour"/>:<s:property value="startMinute"/></td>
			<td align="left"><s:property value="requirement"/></td>
			<td align="left"><s:property value="note"/></td>
			<td align="left"><s:property value="creator"/></td>
			<td align="left"><s:property value="createTime"/></td>
			<td align="left"><s:property value="type"/></td>
			<td align="left"><s:property value="statusTXT"/></td>
			<td align="center">
				<a class="ui_icon_person" href="${root}/vote/tj_candidate.do?vote.id=<s:property value="id"/>" >统计显示</a>&nbsp;&nbsp;
				<a class="ui_icon_person" href="${root}/vote/voter.do?vote.id=<s:property value="id"/>" >投票人</a>&nbsp;&nbsp;
				<a class="statics" href="${root}/vote/tj_vote.do?primaryVote.id=<s:property value="id"/>" >二次投票</a>&nbsp;&nbsp;
			</td>
		</tr>
		</s:if>
		<s:if  test="status == \"x.inprogress\"">
		<tr  bgcolor="#FF0000">
			<td align="center"><s:property value="%{#st.index+1}" /></td> 
			<td align="left"><s:property value="name"/></td>
			<td align="left"><s:property value="startDate"/> <s:property value="startHour"/>:<s:property value="startMinute"/></td>
			<td align="left"><s:property value="requirement"/></td>
			<td align="left"><s:property value="note"/></td>
			<td align="left"><s:property value="creator"/></td>
			<td align="left"><s:property value="createTime"/></td>
			<td align="left"><s:property value="type"/></td>
			<td align="left"><s:property value="statusTXT"/></td>
			<td align="center">
				<a class="ui_icon_person" href="${root}/vote/tj_candidate.do?vote.id=<s:property value="id"/>" >统计显示</a>&nbsp;&nbsp;
				<a class="ui_icon_person" href="${root}/vote/voter.do?vote.id=<s:property value="id"/>" >投票人</a>&nbsp;&nbsp;
				<a class="statics" href="${root}/vote/tj_vote.do?primaryVote.id=<s:property value="id"/>" >二次投票</a>&nbsp;&nbsp;
			</td>
		</tr>
		</s:if>
	</s:iterator> 
	
</table>
</body>
</html>