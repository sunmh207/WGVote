<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<s:actionerror />
<s:actionmessage />
<s:form action="feedback!save.do" method="post">
<s:hidden name="feedback.id" value="%{feedback.id}" />

<s:hidden name="feedback.respondentId" value="%{feedback.respondentId}" />
<s:hidden name="feedback.email" value="%{feedback.email}" />
<s:hidden name="feedback.title" value="%{feedback.title}" />
<s:hidden name="feedback.desc" value="%{feedback.desc}" />
<s:hidden name="feedback.time" value="%{feedback.time}" />
<s:hidden name="feedback.phoneNumber" value="%{feedback.phoneNumber}" />

<s:hidden name="feedback.operatorId" value="%{feedback.operatorId}" />
	
<table width="101%" class="table" cellpadding="0" cellspacing="0" align="center">
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">电话</td>
        <td class="td_edit">		
			<s:label value="%{feedback.phoneNumber}" />
        </td>
    </tr>
	<tr>
        <td width="90" height="24" align="right" class="td_lable">邮件地址</td>
        <td class="td_edit">		
			<s:label value="%{feedback.email}" /> 		 
        </td>
    </tr>
	<tr>
        <td width="90" height="24" align="right" class="td_lable">标题</td>
        <td class="td_edit">		
			<s:label  value="%{feedback.title}" />      		 
        </td>
    </tr>  
    <tr>
        <td width="90" height="24" align="right" class="td_lable">问题描述</td>
        <td class="td_edit">		
			<s:label value="%{feedback.desc}"  />     		 
        </td>
    </tr> 
    <tr>
        <td width="90" height="24" align="right" class="td_lable">是否已解决</td>
        <td class="td_edit">		
			<s:select  name="feedback.isFixed" list="fixedList"/>  		 
        </td>
    </tr> 
    <tr>
        <td width="90" height="24" align="right" class="td_lable">重要性</td>
        <td class="td_edit">		
			<s:select  name="feedback.sev" list="sevList"/>  		 
        </td>
    </tr> 
     <tr>
        <td width="90" height="24" align="right" class="td_lable">解决方案</td>
        <td class="td_edit">		
			<s:textarea name="feedback.solution" value="%{feedback.solution}"  rows="8" cols="100"/>     		 
        </td>
    </tr>
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   		 <s:submit value="取消" name="redirectAction:feedback"/>
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>