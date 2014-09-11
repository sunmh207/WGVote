<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<html:pagetitle title="推荐投票:Excel导入候选人"/>			
<s:actionerror />
<s:actionmessage />
<s:form action="tj_candidate!uploadCandidates.do" method="post" enctype="multipart/form-data">
<s:hidden name="vote.id"/>
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td class="td_lable">文件要求</td>
        <td class="td_edit">	
	        Excel文件必须为15列,请参考模板: <a href="${root}/file/tj_candidate_template.xls">模板文件</a>
        </td>
	</tr> 
    <tr>
        <td class="td_lable">请选择要导入的Excel文件</td>
        <td class="td_edit">	
	        <s:file name="uploadfile"></s:file>
	        <br>
	        <font color=red>1.文件大小不能超过2M.<br> 2.支持Excel 95, 97, 2000, XP, and 2003<br>3.姓名不能为空，否则不于导入</font>
        </td>
	</tr> 
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
      <s:if test="candidateList==null">
        <input class="setting-save" type="submit" id="submit"  value="上传">
      </s:if> 
      <s:else>
        <a class="setting-save"  onclick="location.href='${root}/vote/tj_candidate!importCandidates.do?vote.id=<s:property value="vote.id"/>'" >确认导入</a>
      </s:else>
       <a class="return" href="${root}/vote/tj_candidate.do?vote.id=<s:property value="vote.id"/>" >返回</a> 
       
      </td>
    </tr>	
</table>
<s:if test="candidateList!=null">
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
     </tr>
	<tr>
       <td class="td_header">名称</td>
       <td class="td_header">首评(取得)年月</td>
       <td class="td_header">学历</td>
       <td class="td_header">毕业院校</td>
       <td class="td_header">所学专业</td>
       <td class="td_header">毕业年份</td>
     </tr>
	<s:iterator value="candidateList" status="status">
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
		</tr>
	</s:iterator>
	<tr>
		<td colspan="4" align="center">
		
		</td>
	</tr>
</table>
</s:if>	
</s:form>
</body>
</html>