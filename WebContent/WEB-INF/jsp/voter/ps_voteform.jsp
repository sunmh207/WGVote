<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<style type="text/css">
 .even{ background-color: #FFFFFF;}
 .odd {background-color: #F0F0F0;     }
 .selected{ background-color: Yellow;  }
 </style>
<script>
function fSubmitCheck(){
	var item = $(":radio:checked");
	var len=item.length;
	if(len< <s:property value="objectList.size" /> ){
		alert("没有为所有候选人投票，不能提交!");
	  return false;
	}
	return window.confirm("确定要提交投票?");
}

$(function () {
	$("tbody>tr:even").addClass("even");  
	$("tbody>tr:odd").addClass("odd"); 
         //根据type获取了所有的radio框，并且给radio添加事件  
         $("input[type='radio']").click(function (){  
         //如果radio被选中则给选中的改行增加样式  
         $("input[type='radio']:checked").parents("tr").addClass("selected");   
       
          });  
     });  
</script>
</html:pageHead>

<body>
<s:actionerror/>
<html:pagetitle title="投票页面" />
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

<form id="voteform" name="voteform" action="${root}/toupiao!submit.do" method="post">
	<s:hidden name="vote.id"/>
	<s:hidden name="voter.id"/>
	<input class="return" type="submit" id="submit" onclick="return fSubmitCheck()" value="  提交   ">




<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
       <td rowspan="2" align="center" class="td_header">序号</td>
       <td class="td_header" rowspan="2">姓名</td>
       <td class="td_header" rowspan="2">单位</td>
       <td class="td_header" rowspan="2">部门</td>
       <td class="td_header" rowspan="2">毕业学校</td>
       <td class="td_header" rowspan="2">所学专业</td>
       <td class="td_header" rowspan="2">参评学历</td>
       <td class="td_header" rowspan="2">专业工作年限</td> 
       <td class="td_header" colspan="3">投票表决</td>
     </tr>
	<tr>
       <td class="td_header">同意√	</td>
       <td class="td_header">弃权○</td>
       <td class="td_header">反对×</td>
     </tr>
	
	<s:iterator value="objectList" status="st">
		<tr >
			<td align="center" class="td_header"> <s:property value="%{#st.index+1}" /></td>
			<td align="left" class='td_body'><s:property value="name"/></td>
			<td align="left" class='td_body'><s:property value="recommendUnit"/></td>
			<td align="left" class='td_body'><s:property value="dept"/></td>
			<td align="left" class='td_body'><s:property value="university"/></td>
			<td align="left" class='td_body'><s:property value="major"/></td>
			<td align="left" class='td_body'><s:property value="xueli"/></td>
			<td align="left" class='td_body'><s:property value="workYears"/></td>
			<td align="left"><input name="ticket_<s:property value="id"/>" type="radio" value="agreement"></td>
			<td align="left"><input name="ticket_<s:property value="id"/>" type="radio" value="abstention" ></td>
			<td align="left"><input name="ticket_<s:property value="id"/>" type="radio" value="against"></td>
		</tr>
	</s:iterator>
</table>
</form>
</body>
</html>