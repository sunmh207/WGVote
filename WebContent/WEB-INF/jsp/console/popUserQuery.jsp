<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<html:pageHead >
<style>
html {
	overflow-x: hidden;
	overflow-y: auto;
}
</style>
</html:pageHead>
<script type="text/javascript">

    <%
    String act=request.getParameter("act");
    if("select".equals(act)){
        String idStr= (String)request.getAttribute("idStr");
        String nameStr= (String)request.getAttribute("nameStr");
    %>    
    window.returnValue = "<%=nameStr%>||<%=idStr%>";
    window.close();
    <% } %>
</script>
<% 
	boolean single = "true".equals(request.getParameter("single"));
%>

<script type="text/javascript">
	<%String cul = request.getParameter("checkedUserList");
	  cul = cul==null?"":cul.trim();
	%>
var checkedUserList = "<%=cul%>";
function initialCheckers(){
	if(!checkedUserList)return;
	var ids = checkedUserList.split("||")[1].split(",");
	for(var i=0;i<ids.length;i++){
		var chk=$("#chk_"+ids[i]);
		if(chk.length>0){
			chk.attr("checked",true);
		}
	}
}

function saveCheck(){
	var ids = "";
	var names = "";
	var phones ="";
	if(checkedUserList){
		var arr = checkedUserList.split("||"); 
		ids = arr[1];
		names = arr[0];
		phones = arr[2];
	}
	$("input[name='chk']").each(function (){
		if(this.checked){
			if(ids.indexOf(this.value)>=0){
				return;
			}
			if(ids!=""){
				ids+=",";
				names+=",";
				phones+=",";
			}
			ids += this.value;
			names +=$("#lb_"+this.value).text();
			phones+=$("#lbPhone_"+this.value).text();
		}
	});
	var cul = names+"||"+ids+"||"+phones;
	$("#checkedUserList").val(cul);
}

function forSel() {
	saveCheck();
	var ids = ($("#checkedUserList").val()+"").replace(/(^[\\s]*)|([\\s]*$)/g, "");
	if(ids.replace(/\|/g,"")){
		window.returnValue = ids;
		if(opener)opener.selectedStr = ids;
		window.close();
	} else {
		alert("请选择人员");
		return false;
	}
	
}

function forSelectAll(){
	var frm = document.getElementById("searchForm");
	selectAll(frm.all,frm.chk);
}
</script>
<base target="_self">
<body style="overflow:hidden;" rightmargin="0px" onload="initialCheckers();">
<form id="searchForm" method="post" onsubmit="saveCheck()">
 <input type="hidden" name="act"/>
<div  style="float: left; padding:0px 20px;top:1px;position:fixed;background-color:#F3F3F3;">
	姓名 <input name="qryName" value='${qryName}' size="5">
	工号 <input name="qryGH" value='${qryGH}' size="5">
	车间<s:select  name="qryDeptName" list="deptList"/> 
	<select name="gender">
		<option value="">性别</option>
		<option value="男" <c:if test='${"男" eq  gender}'>selected</c:if>>男</option>
		<option value="女" <c:if test='${"女" eq  gender}'>selected</c:if>>女</option>
	</select>
  	兼职 <input name="jianzhi" value='${jianzhi}' size="5">
	<input type="submit" value="搜索" class="button">  <input type="button" value=" 选择 " onclick="forSel()" class="button"/>

<html:pagination large="true"/>
</div>
<input type="hidden" name="checkedUserList" id="checkedUserList" value="<%=cul%>">

<br>
<br>
<table width="95%" class="table" align="center" cellpadding="1"
	cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">
			<input type="checkbox" name="all" onClick="forSelectAll()">
		</td>
		<td class="td_header">姓名</td>
		<td class="td_header">性别</td>
		<td class="td_header">工号</td>
		<td class="td_header">部门</td>
		<td class="td_header">系数</td>
		<td class="td_header">手机号</td>
		<td class="td_header">工种</td>
		<td class="td_header">岗位</td>
		<td class="td_header">职务</td>
		<td class="td_header">兼职</td>
	</tr>
	<c:forEach items="${objectList}" var="user" varStatus="status">
		<tr>
			<td align="center" class="td_header">
			<input type="<%=single?"radio":"checkbox"%>" name="chk" value="${user.id}" id="chk_${user.id}"
				<%if(single)out.print("onchange='forSel()'");%>>
			</td>
			<td align="left" class='td_body' id="td_${user.id}">
				<label for="chk_${user.id}" id="lb_${user.id}">${user.name}</label>
			</td>
			<td align="left" class='td_body'>${user.gender}</td>
			<td align="left" class='td_body'>${user.GH}</td>
			
			<td align="left" class='td_body'>${user.dept}</td>
			<td align="left" class='td_body'>${user.evaRatio}</td>
			<td align="left" class='td_body'>
				<label id="lbPhone_${user.id}">${user.phoneNumber}</label>
			</td>
			<td align="left" class='td_body'>${user.gongzhong}</td>
			<td align="left" class='td_body'>${user.gangwei}</td>
			<td align="left" class='td_body'>${user.zhiwu}</td>
			<td align="left" class='td_body'>${user.jianzhi}</td>
		</tr>
	</c:forEach>
</table>
</form>
</body>
</html>