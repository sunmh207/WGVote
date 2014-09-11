<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<s:actionerror />
<s:actionmessage />
<s:form action="changepassword!save.do" method="post">
<table width="101%" class="table" cellpadding="0" cellspacing="0" align="center">
 <tr>
        <td width="90" height="24" align="right" class="td_lable">新密码</td>
        <td class="td_edit">		
			<s:password name="newPassword" value="%{newPassword}" size="40"/>
			<s:fielderror ><s:param>newPassword</s:param></s:fielderror>
        </td>
        
    </tr>
     <tr>
        <td width="90" height="24" align="right" class="td_lable">新密码确认</td>
        <td class="td_edit">		
			<s:password name="newPasswordConfirm" value="%{newPasswordConfirm}" size="40"/>
			<s:fielderror ><s:param>newPasswordConfirm</s:param></s:fielderror>     		 
        </td>
    </tr> 
    
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>