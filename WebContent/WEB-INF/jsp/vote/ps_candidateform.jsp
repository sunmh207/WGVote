<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<s:actionerror />
<s:actionmessage />
<html:pagetitle title="候选人维护" />
<s:form action="ps_candidateinput!save.do" method="post">
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
<s:hidden name="candidate.id" value="%{candidate.id}" />
<s:hidden name="vote.id" value="%{vote.id}" />
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">姓名</td>
        <td class="td_edit">		
			<s:textfield name="candidate.name" value="%{candidate.name}" size="40"/>
			<s:fielderror ><s:param>candidate.name</s:param></s:fielderror>
        </td>
        <td width="90" height="24" align="right" class="td_lable">单位</td>
        <td class="td_edit">	
        	<s:textfield name="candidate.recommendUnit" value="%{candidate.recommendUnit}" size="40"/>
			<s:fielderror ><s:param>candidate.recommendUnit</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">部门</td>
        <td class="td_edit">		
			<s:textfield name="candidate.dept" value="%{candidate.dept}" size="40"/>
			<s:fielderror ><s:param>candidate.dept</s:param></s:fielderror>
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
        <td width="90" height="24" align="right" class="td_lable">参评学历</td>
        <td class="td_edit">		
			<s:textfield name="candidate.xueli" value="%{candidate.xueli}" size="40"/>
			<s:fielderror ><s:param>candidate.xueli</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable">专业工作年限</td>
        <td class="td_edit">		
			<s:textfield name="candidate.workYears" value="%{candidate.workYears}" size="40"/>
			<s:fielderror ><s:param>candidate.workYears</s:param></s:fielderror>
        </td>
        <td width="90" height="24" align="right" class="td_lable"></td>
        <td class="td_edit">	
        	
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