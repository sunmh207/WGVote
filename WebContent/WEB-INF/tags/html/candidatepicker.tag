<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="idName" type="java.lang.String" required="true"%>
<%@ attribute name="nameName" type="java.lang.String" required="true"%>
<%@ attribute name="idValue" type="java.lang.String" required="false"%>
<%@ attribute name="nameValue" type="java.lang.String" required="false"%>
<%@ attribute name="voteId" type="java.lang.String" required="true"%>
<div>
<input type="hidden" name="<%=idName%>" id="hidden_id" value="<%=idValue%>">
<input type="text" name="<%=nameName%>" id="hidden_name" value="<%= com.jitong.common.util.StringUtil.trim(nameValue)%>" readonly size="20">

<input name="popCandidateDlg" type="button"	class="button_select" onClick="fPopUpCandidateDlg('<%=nameName%>','<%=idName%>',false,'<%=voteId%>')">
</div>
