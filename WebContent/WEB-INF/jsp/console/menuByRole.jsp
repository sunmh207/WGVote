<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.jitong.JitongConstants" %>
<%@ page import="com.jitong.console.domain.User" %>
<%@ page import="com.jitong.console.domain.RoleMenu" %>
<%@ page import="com.jitong.console.domain.Menu" %>
<%@ page import="com.jitong.ContentItemManager" %>
<%@ page import="com.jitong.common.domain.CategoryItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Map" %>

<%@include file="/WEB-INF/jsp/include.jsp"%>
<%
Hashtable<String,RoleMenu> menuhash = (Hashtable<String,RoleMenu>)request.getAttribute("menuHash");
Map<String, List<Menu>> userModuleMenuMap = (Map<String, List<Menu>>)request.getAttribute("userModuleMenuMap");
%>


<html:pageHead />
<script type="text/javascript">
<!--
function forUpdate() {
	var frm =document.getElementById('roleForm');
	frm.action="${root}/console/rolemenu!updateMenus.do";
 	frm.submit();
}
function forCancel() {
	var frm =document.getElementById('roleForm');
	frm.action="${root}/console/role.do";
 	frm.submit();
}
//-->
</script>
<body>

<table width="101%"  border="0" cellspacing="2" cellpadding="0" align="center">
  <tr>
    <td class="page_title">角色信息</td>    
  </tr>
</table>
<s:form id="roleForm" action="userrole.do" method="post">
<s:hidden name="role.roleId" value="%{role.roleId}"/>
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
	<tr>
        <td width="90" height="24" align="right" class="td_lable">角色名称</td>
        <td class="td_edit" align="left">
			<s:label value="%{role.roleName}"/>
        </td>
    </tr>
    <tr>
        <td width="90" height="24" align="right" class="td_lable">创建人</td>
        <td class="td_edit" align="left">		
			<s:label value="%{role.creatorName}"/>		 
        </td>
    </tr>
	<tr>
        <td width="90" height="24" align="right" class="td_lable">创建日期</td>
        <td class="td_edit" align="left">		
			<s:label value="%{role.createTime}"/>			 
        </td>
    </tr>
</table>	

<table width="100%"  border="0" cellspacing="2" cellpadding="0" align="center">
  <tr>
    <td class="page_title">菜单权限</td>    
    <td class="page_title">
    <input name="add" type="button" class="button" value="保存" onClick="forUpdate()">
    <input name="add" type="button" class="button" value="取消" onClick="forCancel()">
    </td> 
  </tr>
</table>

<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">				
	<tr>
		<td width="40" align="center" class="td_header">选择</td>
		<td class="td_header">菜单名称</td>									  
	</tr>

<%
//List<CategoryItem> topCategoryItems = ContentItemManager.getTopCategoryItems();
//for(CategoryItem topItem: topCategoryItems){
for(String[] module:com.jitong.console.domain.Menu.MODULES){
	if(userModuleMenuMap.containsKey(module[0])){
%>
	<tr>
		<td align="center" class='td_header'>
		 <!-- <input type="checkbox" name="chk_module" value=""> -->
		 <img src="${root}/images/<%=module[0]%>.gif" border="0" width="16"  />
		 </td>
		<td align="left" class='td_body'> <strong><%=module[1]%></strong> </td>					         
	</tr>
<%
List<Menu> menuL = userModuleMenuMap.get(module[0]);
	for(Menu menu: menuL){
%>
	<tr>
		<td align="center" class='td_header'> &nbsp;</td>
		<td align="left" class='td_body'> 
		<%
		if(menuhash.containsKey(menu.getMenuId())){
		%>
			<input type="checkbox" name="chk" value="<%=menu.getMenuId()%>" checked>
			<%}else {%>
		 	<input type="checkbox" name="chk" value="<%=menu.getMenuId()%>">
			<%}%>
		
			<%=menu.getName()%> 
		</td>					         
	</tr>
<%
	}//end for:
	
}//end if
	}//end for
%>

</table>
</s:form>
</body>
</html>