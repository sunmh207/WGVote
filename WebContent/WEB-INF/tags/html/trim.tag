<%@ attribute name="value" type="java.lang.String"%>
<%@ attribute name="len" type="java.lang.Integer"%>
<%@ attribute name="to" type="java.lang.String" required="false"%><%
	if(value==null)return;
	int nHalf = 0;
	int width=0;
	int pos = 0;
	for(int i=0;i<value.length();i++){
		if(value.charAt(i)<256){
			width++;
		}else {
			width+=2;
		}
		if(width>len*2){
			pos=i;
			break;
		}
	}
	value=value.substring(0,pos);
	if(to!=null){
		int idxTo = value.indexOf(to);
		if(idxTo>=0)value=value.substring(0,idxTo);
	}
%><%=value%>
