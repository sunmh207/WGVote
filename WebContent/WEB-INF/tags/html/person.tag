<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="idName" type="java.lang.String" required="true"%>
<%@ attribute name="nameName" type="java.lang.String" required="true"%>
<%@ attribute name="idValue" type="java.lang.String" required="false"%>
<%@ attribute name="nameValue" type="java.lang.String" required="false"%>
<%@ attribute name="phoneValue" type="java.lang.String" required="false"%>
<%@ attribute name="height" type="java.lang.Integer" required="false"%>
<%@ attribute name="rows" type="java.lang.Integer" required="false"%>
<%@ attribute name="single" type="java.lang.String" required="false"%>
<div>
<table>
	<tr>
		<td><div style="border: 1px solid #CCC;width: 402px;height:<%=height%>px; overflow-x: hidden;overflow-y: auto;">
		<table class="userlist" id="userlist_<%=idName%>" style="width: 400px" width="400px">
			<%if(rows==null||rows<=0){rows=5;} %>
			<%for(int i=1;i<=rows;i++){ %>
				<tr>
					<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
				</tr>
			<%} %>
		</table>
		</div></td>
		<td valign="top">
		<input type="hidden" name="<%=idName%>" id="hidden_id">
		<input type="hidden" name="<%=nameName%>" id="hidden_name">
		<input name="popUserDlg" type="button"
			class="button_select"
			<%if("true".equalsIgnoreCase(single)){ %>
			onClick="fPopUpUserDlg2('sendToNames','sendToIds',true)">
			<%}else{ %>
				onClick="fPopUpUserDlg2('sendToNames','sendToIds')">
			<%} %>
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	var users = {};
	var phones = {};
	var idx = 0;
	function addUsers(ids, names,_phones){
		if(!ids)return;
		ids = ids.split(",");
		names = names.split(",");
		_phones = _phones.split(",");
		for(var i=0;i<ids.length;i++){
			users[ids[i]] = names[i];
			phones[ids[i]] = _phones[i];
		}
		render();
	}
	function addUser(){
		users["id"+idx] = userNames.split(",")[idx%7];
		idx++;
		render();
	}
	function deleteUser(){
		delete users[this.id];
		render();
	}
	function render(){
		var table = document.getElementById("userlist_<%=idName%>");	
		while(table.rows.length>0){
			table.deleteRow(0);
		}
		var i=-1;
		var idStr = "";
		var nameStr = "";
		for(id in users){
			i++;
			if(i!=0){
				idStr += ",";
				nameStr +=",";
			}
			idStr += id;
			nameStr +=users[id];
			
			tr = table.insertRow(i);
			tr.insertCell(0);
			tr.insertCell(0);
			tr.insertCell(0);
			
			tr.cells[0].innerHTML = users[id];
			tr.cells[0].width = "120px";
			tr.cells[1].innerHTML = phones[id];
			
			var label = document.createElement("img");
			label.src = "${root}/images/delete-small.gif";
			label.id = id;
			label.className="deleteIcon";
			label.onclick = deleteUser;
			
			tr.cells[2].appendChild(label);
		}
		$("#hidden_id").val(idStr);
		$("#hidden_name").val(nameStr);
		for(var j=table.rows.length;j<5;j++){
			tr = table.insertRow(j);
			tr.insertCell(0);tr.insertCell(0);tr.insertCell(0);
			tr.cells[0].innerHTML="&nbsp;";
			tr.cells[1].innerHTML="&nbsp;";
			tr.cells[2].innerHTML="&nbsp;";
		}
	}
	addUsers("${idValue}","${nameValue}","${phoneValue}");
</script>