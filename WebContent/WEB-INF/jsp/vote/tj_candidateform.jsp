<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<s:actionerror />
<s:actionmessage />
<html:pagetitle title="候选人维护" />
<s:form action="tj_candidateinput!save.do" method="post">
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
<s:hidden name="candidate.id" value="%{candidate.id}" />
<s:hidden name="vote.id" value="%{vote.id}" />
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">姓名</td>
        <td class="td_edit">		
			<s:textfield name="candidate.name" value="%{candidate.name}" size="40"/>
			<s:fielderror ><s:param>candidate.name</s:param></s:fielderror>
        </td>
        <td width="90" height="24" align="right" class="td_lable">性别</td>
        <td class="td_edit">	
        	<s:select name="candidate.gender" list="{'男','女'}" headerKey="" headerValue=""/>	
			<s:fielderror ><s:param>candidate.gender</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">出生年月</td>
        <td class="td_edit">		
			<s:textfield name="candidate.birth" value="%{candidate.birth}" size="40"/>
			<s:fielderror ><s:param>candidate.birth</s:param></s:fielderror>
        </td>
        <td width="90" height="24" align="right" class="td_lable">推荐单位</td>
        <td class="td_edit">	
        	<s:textfield name="candidate.recommendUnit" value="%{candidate.recommendUnit}" size="40"/>	 
			<s:fielderror ><s:param>candidate.recommendUnit</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable">所在单位或部门</td>
        <td class="td_edit">		
			<s:textfield name="candidate.unit" value="%{candidate.unit}" size="40"/>
			<s:fielderror ><s:param>candidate.unit</s:param></s:fielderror>
        </td>
        <td width="90" height="24" align="right" class="td_lable">行政职务/级别</td>
        <td class="td_edit">	
        	<s:textfield name="candidate.xingzhengPost" value="%{candidate.xingzhengPost}" size="40"/>	 
			<s:fielderror ><s:param>candidate.xingzhengPost</s:param></s:fielderror>
			<s:textfield name="candidate.xingzhengLevel" value="%{candidate.xingzhengLevel}" size="20"/>	 
			<s:fielderror ><s:param>candidate.xingzhengLevel</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable">技术职务(资格)</td>
        <td class="td_edit">		
			<s:textfield name="candidate.techPost" value="%{candidate.techPost}" size="40"/>
			<s:fielderror ><s:param>candidate.techPost</s:param></s:fielderror>
        </td>
        <td width="90" height="24" align="right" class="td_lable">首评(取得)年月</td>
        <td class="td_edit">	
        	<s:textfield name="candidate.techPostObtainDate" value="%{candidate.techPostObtainDate}" size="40"/>	 
			<s:fielderror ><s:param>candidate.techPostObtainDate</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable">从事专业</td>
        <td class="td_edit" colspan="3">		
			<s:textfield name="candidate.workMajor" value="%{candidate.workMajor}" size="40"/>
			<s:fielderror ><s:param>candidate.workMajor</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable">学历</td>
        <td class="td_edit">		
			<s:textfield name="candidate.xueli" value="%{candidate.xueli}" size="40"/>
			<s:fielderror ><s:param>candidate.xueli</s:param></s:fielderror>
        </td>
        <td width="90" height="24" align="right" class="td_lable">毕业院校</td>
        <td class="td_edit">	
        	<s:textfield name="candidate.university" value="%{candidate.university}" size="40"/>	 
			<s:fielderror ><s:param>candidate.university</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable">所学专业</td>
        <td class="td_edit">		
			<s:textfield name="candidate.major" value="%{candidate.major}" size="40"/>
			<s:fielderror ><s:param>candidate.major</s:param></s:fielderror>
        </td>
        <td width="90" height="24" align="right" class="td_lable">毕业年份</td>
        <td class="td_edit">	
        	<s:textfield name="candidate.graduateYear" value="%{candidate.graduateYear}" size="10"/>	 
			<s:fielderror ><s:param>candidate.graduateYear</s:param></s:fielderror>
        </td>
    </tr>
     <tr>
        <td width="90" height="24" align="right" class="td_lable">学位</td>
        <td class="td_edit" colspan="3">		
			<s:textfield name="candidate.degree" value="%{candidate.degree}" size="20"/>
			<s:fielderror ><s:param>candidate.degree</s:param></s:fielderror>
        </td>
    </tr>
    
    
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   		 <s:submit value="取消" name="redirectAction:vote"/>

      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>