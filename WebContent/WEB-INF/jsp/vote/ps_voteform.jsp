<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<script>
function forSubmit(){
	if(window.confirm("确定要保存?")){
		
	} 
} 
</script>
</html:pageHead>
<body>
<s:actionerror />
<s:actionmessage />
<html:pagetitle title="编辑投票" />
<s:form action="ps_voteinput!save.do" method="post">
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
<s:hidden name="primaryVote.id" value="%{primaryVote.id}" />
<s:hidden name="vote.id" value="%{vote.id}" />
<s:hidden name="vote.type" value="%{vote.type}" />
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">投票名称</td>
        <td class="td_edit" colspan="3">		
			<s:textfield name="vote.name" value="%{vote.name}" size="40"/>
			<s:fielderror><s:param>vote.name</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">投票时间</td>
        <td class="td_edit" colspan="3">		
			<s:textfield cssClass="datepicker" name="vote.startDate" value="%{vote.startDate}" />
			<s:fielderror ><s:param>vote.startDate</s:param></s:fielderror>
			&nbsp;
			<s:select  name="vote.startHour" list="hourlist"/>:
			<s:fielderror ><s:param>vote.startHour</s:param></s:fielderror>
			<s:select  name="vote.startMinute" list="minutelist"/> 
			<s:fielderror ><s:param>vote.startMinute</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable"> 要求 </td>
        <td class="td_edit" colspan="3">		
			<s:textarea name="vote.requirement" value="%{vote.requirement}"  rows="4" cols="100"/>      
			<s:fielderror ><s:param>vote.requirement</s:param></s:fielderror>    		 
        </td>
    </tr> 
    <tr>
        <td width="90" height="24" align="right" class="td_lable"> 投票说明 </td>
        <td class="td_edit" colspan="3">		
			<s:textarea name="vote.note" value="%{vote.note}"  rows="4" cols="100"/>      
			<s:fielderror ><s:param>vote.note</s:param></s:fielderror>    		 
        </td>
    </tr> 
  
     
     <tr>
        <td width="90" height="24" align="right" class="td_lable">创建人</td>
        <td class="td_edit" colspan="3">		
			<s:textfield name="vote.creator" value="%{vote.creator}" size="20"/>
			<s:fielderror ><s:param>vote.creator</s:param></s:fielderror>
        </td>
    </tr>  
    
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
        <input type="submit" class="statics" value="确定"/>
		<a class="return" href="${root}/vote/ps_vote.do" >取消</a> 
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>