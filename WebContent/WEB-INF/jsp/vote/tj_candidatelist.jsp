<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead >
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
<script>
function fPopUpCandidateDlg(voteId) {
    var time =new Date();
    var url = "/vote/popCandidateQuery.do?voteId="+voteId+"&time="+time;
    retval = window.showModalDialog(url, "", "dialogWidth:750px; dialogHeight:420px;status:1;resizable:yes;center:true");
    //var nameComp = document.getElementById("newCandidateIds")||document.all("newCandidateIds");
    var idComp = document.getElementById("newCandidateIds")||document.all("newCandidateIds");
    if (retval != null) {          //name1,name2,name3..||id1,id2,id3..
        rs = retval.split("||");
        	//nameComp.value = rs[0];
        	idComp.value = rs[1];  
        	return true;
    } 
	return false;
}


</script>

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
<form id="searchForm" action="${root}/vote/tj_candidate.do">
	<s:hidden name="vote.id"/>
		所在单位<input name="qryUnit" value='${qryUnit}' size="15">
		行政职务<input name="qryXingzhengPost" value='${qryXingzhengPost}' size="10">
		学历<input name="qryXueli" value='${qryXueli}' size="10">
	   <button class="user-search" type ="submit" >查询</button>

<s:if test="vote.status == \"1.new\"&&vote.primaryVote==null">
	<a class="upload" href="${root}/vote/tj_candidate!importForm.do?vote.id=<s:property value="vote.id"/>" >Excel导入</a> 
	<a class="ui_icon_circle_plus" href="${root}/vote/tj_candidateinput!input.do?vote.id=<s:property value="vote.id"/>" >新增候选人</a>  
</s:if>
<s:if test="vote.status == \"4.done\"&&vote.primaryVote==null">
<a class="statics" href="${root}/vote/tj_vote.do?primaryVote.id=<s:property value="vote.id"/>" >二次投票</a>&nbsp;&nbsp;
</s:if>
<a class="return" href="${root}/vote/tj_vote.do?primaryVote.id=<s:property value="vote.primaryVote.id"/>" >返回</a> 
<html:pagination exportExcel="true"/>
</form>

<s:if test="vote.status == \"1.new\"&&vote.primaryVote!=null">
<form id="newCandidateForm" action="${root}/vote/tj_candidate!createSecondaryCandidates.do">
<s:hidden name="vote.id"/>
<s:hidden name="newCandidateIds"  value=""/>
<%-- <s:hidden name="newCandidateNames" id="newCandidateNames" value=""/> --%>
<button class="user-search" type="submit" onclick="fPopUpCandidateDlg('<s:property value="vote.primaryVote.id"/>')">选择候选人</button>
</form>
</s:if>

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
       <s:if  test="vote.primaryVote==null">
       <td class="td_header" colspan="4">投票表决</td>
       </s:if>
       <s:else>
       <td class="td_header" colspan="3">投票表决</td>
       </s:else>
       <td class="td_header" rowspan="2">操作</td>
     </tr>
	<tr>
       <td class="td_header">名称</td>
       <td class="td_header">首评(取得)年月</td>
       <td class="td_header">学历</td>
       <td class="td_header">毕业院校</td>
       <td class="td_header">所学专业</td>
       <td class="td_header">毕业年份</td>
       <s:if  test="vote.primaryVote==null">
       <td class="td_header">2次投票</td>
       </s:if>
       <td class="td_header">同意√	</td>
       <td class="td_header">弃权○</td>
       <td class="td_header">反对×</td>
     </tr>
	
	<s:iterator value="objectList" status="status">
		<tr>
			<td align="center" class="td_header"> ${status.index+pager.startRecord}</td>
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
			<s:if  test="vote.primaryVote==null">
			<td align="left" class='td_body'><a href="${root}/vote/tj_candidate!show2ndaryCandidates.do?vote.id=<s:property value="vote.id"/>&candidate.id=<s:property value="id"/>"><s:property value="contain2ndCandidateTXT"/></a></td>
			</s:if>
			<td align="left" class='td_body'><s:property value="latestCandidate.agreementNumber"/></td>
			<td align="left" class='td_body'><s:property value="latestCandidate.abstentionNumber"/></td>
			<td align="left" class='td_body'><s:property value="latestCandidate.againstNumber"/></td>
			<td align="center" class='td_body'>
			
			<s:if test="vote.status == \"1.new\"">
				<a class="user-edit" href="${root}/vote/tj_candidateinput!input.do?vote.id=<s:property value="vote.id"/>&candidate.id=<s:property value="id"/>">编辑</a>&nbsp;&nbsp;				
				<a class="user-delete" href="${root}/vote/tj_candidate!delete.do?vote.id=<s:property value="vote.id"/>&candidate.id=<s:property value="id"/>" onclick="return fDelCheck()">删除</a>&nbsp;&nbsp;
			</s:if>	
			<s:if test="vote.status == \"2.ready\"">
				<a class="ui_icon_person" href="${root}/vote/tj_candidate.do?vote.id=<s:property value="id"/>" >投票详情</a>&nbsp;&nbsp;
			</s:if>
			</td>
		</tr>
	</s:iterator>
</table>
</body>
</html>