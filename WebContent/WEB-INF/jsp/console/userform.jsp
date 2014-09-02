<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<s:actionerror />
<s:actionmessage />
<s:form action="userinput!save.do" method="post">
<table width="101%" class="table" cellpadding="0" cellspacing="0" align="center">
<s:hidden name="user.id" value="%{user.id}" />
 <tr>
        <td width="90" height="24" align="right" class="td_lable">姓名</td>
        <td class="td_edit">		
			<s:textfield name="user.name" value="%{user.name}" size="40"/>
			<s:fielderror ><s:param>user.name</s:param></s:fielderror>
        </td>
        
    </tr>
     <tr>
        <td width="90" height="24" align="right" class="td_lable">工号</td>
        <td class="td_edit">		
			<s:textfield name="user.GH" value="%{user.GH}" size="20"/>      		 
        </td>
    </tr> 
    <tr>
        <td width="90" height="24" align="right" class="td_lable">工种</td>
        <td class="td_edit">		
			<s:textfield name="user.gongzhong" value="%{user.gongzhong}" size="20"/>    
			<s:fielderror ><s:param>user.gongzhong</s:param></s:fielderror>    		 
        </td>
    </tr> 
   <!--    
    <tr>
        <td width="90" height="24" align="right" class="td_lable">车间</td>
        <td class="td_edit">		
			<s:textfield name="user.unit" value="%{user.unit}" size="20"/> 
			<s:fielderror ><s:param>user.unit</s:param></s:fielderror>       		 
        </td>
    </tr>  
    -->
     <tr>
        <td width="90" height="24" align="right" class="td_lable">车间/部门</td>
        <td class="td_edit">		
			<s:select  name="user.dept" list="deptList"/> 
			<s:fielderror ><s:param>user.dept</s:param></s:fielderror>      		 
        </td>
    </tr>
     <tr>
        <td width="90" height="24" align="right" class="td_lable">生产奖系数</td>
        <td class="td_edit">		
			<s:textfield name="user.evaRatio" value="%{user.evaRatio}" size="10"/>    
			<s:fielderror ><s:param>user.evaRatio</s:param></s:fielderror>    		 
        </td>
    </tr>  
     <tr>
        <td width="90" height="24" align="right" class="td_lable">每考核分对应金额</td>
        <td class="td_edit">		
			<s:textfield name="user.amountPerScore" value="%{user.amountPerScore}" size="10"/>   默认为100(元/分) 
			<s:fielderror ><s:param>user.amountPerScore</s:param></s:fielderror>    		 
        </td>
    </tr>  
	<tr>
        <td width="90" height="24" align="right" class="td_lable">登录名</td>
        <td class="td_edit">		
			 <s:textfield name="user.loginName" value="%{user.loginName}" size="40"/>  
			 <s:fielderror ><s:param>user.loginName</s:param></s:fielderror>   		 
        </td>
    </tr>
	<tr>
        <td width="90" height="24" align="right" class="td_lable">密码</td>
        <td class="td_edit">		
			<s:password name="user.password" value="%{user.password}" size="20"/> 
			 <s:fielderror ><s:param>user.password</s:param></s:fielderror>      		 
        </td>
    </tr>  
    <tr>
        <td width="90" height="24" align="right" class="td_lable">确认密码</td>
        <td class="td_edit">		
			<s:password name="confirmpassword" value="%{confirmpassword}" size="20"/> 
			 <s:fielderror ><s:param>confirmpassword</s:param></s:fielderror>      		 
        </td>
    </tr> 
   
    <tr>
        <td width="90" height="24" align="right" class="td_lable">手机号</td>
        <td class="td_edit">		
			<s:textfield name="user.phoneNumber" value="%{user.phoneNumber}" size="20"/>    
			<s:fielderror ><s:param>user.phoneNumber</s:param></s:fielderror>    		 
        </td>
    </tr>  
    <%--  
    <tr>
        <td width="90" height="24" align="right" class="td_lable">是否管理员</td>
        <td class="td_edit">		
			 <s:checkbox name="user.isAdmin" value="%{user.isAdmin}"/>    		 
        </td>
    </tr> 
     --%>
     <tr>
        <td width="90" height="24" align="right" class="td_lable">性别</td>
        <td class="td_edit">		
			<s:select  name="user.gender" list="genderList"/> 
			<s:fielderror ><s:param>user.gender</s:param></s:fielderror>        		 
        </td>
    </tr>  
    <tr>
        <td width="90" height="24" align="right" class="td_lable">生日</td>
        <td class="td_edit">		
			<s:textfield  cssClass="date"  name="user.birthday" value="%{user.birthday}"/>     
			<s:fielderror ><s:param>user.birthday</s:param></s:fielderror>    		 
        </td>
    </tr>  
    
    <tr>
        <td width="90" height="24" align="right" class="td_lable">岗位</td>
        <td class="td_edit">		
			<s:textfield name="user.gangwei" value="%{user.gangwei}" size="20"/>   
			<s:fielderror ><s:param>user.gangwei</s:param></s:fielderror>    		 
        </td>
    </tr> 
    <tr>
        <td width="90" height="24" align="right" class="td_lable">职务</td>
        <td class="td_edit">		
			<s:textfield name="user.zhiwu" value="%{user.zhiwu}" size="20"/>   
			<s:fielderror ><s:param>user.zhiwu</s:param></s:fielderror>    		 
        </td>
    </tr> 
    <tr>
        <td width="90" height="24" align="right" class="td_lable">兼职</td>
        <td class="td_edit">		
			<s:textfield name="user.jianzhi" value="%{user.jianzhi}" size="20"/>   
			<s:fielderror ><s:param>user.jianzhi</s:param></s:fielderror>    		 
        </td>
    </tr> 
    <tr>
        <td width="90" height="24" align="right" class="td_lable">角色</td>
        <td class="td_edit">		
        <s:select list="roles" listValue="roleName" label="Roles" name="myRoles" value="myRoles" multiple="true"  size="10"/>
        </td>
    </tr> 
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   		 <s:submit value="取消" name="redirectAction:user"/>

      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>