﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>投票表决系统</title>
	<script type="text/javascript" src="${root}/js/jquery/jquery-latest.js"></script>
	<script type="text/javascript" src="${root}/js/jquery/jquery-ui-latest.js"></script>
	<script type="text/javascript" src="${root}/js/jquery/jquery.layout-latest.js"></script>
	<script type="text/javascript">
	var myLayout; // a var is required because this page utilizes: myLayout.allowOverflow() method
	$(document).ready(function () {
		myLayout = $('body').layout({
			west__size:					150
		,	west__spacing_closed:		20
		,	west__togglerLength_closed:	100
		,	west__togglerAlign_closed:	"top"
		,	west__togglerContent_closed:"菜<BR>单"
		,	west__togglerTip_closed:	"Open & Pin Menu"
		,	west__sliderTip:			"Slide Open Menu"
		,	west__slideTrigger_open:	"mouseover"
		,	center__maskContents:		true // IMPORTANT - enable iframe masking
		});

 	});

	</script>
  <style type="text/css">
  	a{text-decoration: none;}
  </style>

	<style type="text/css">
	/**
	 *	Basic Layout Theme
	 */
	.ui-layout-pane { /* all 'panes' */ 
		border: 1px solid #BBB; 
	} 
	.ui-layout-pane-center { /* IFRAME pane */ 
		padding: 0;
		margin:  0;
	} 
	.ui-layout-pane-west { /* west pane */ 
		padding: 0 0px; 
		background-color: #EEE !important;
		overflow: auto;
	} 

	.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: #DDD; 
		} 
		.ui-layout-resizer-open:hover { /* mouse-over */
			background: #9D9; 
		}

	.ui-layout-toggler { /* all 'toggler-buttons' */ 
		background: #AAA; 
		} 
		.ui-layout-toggler-closed { /* closed toggler-button */ 
			background: #CCC; 
			border-bottom: 1px solid #BBB; 
		} 
		.ui-layout-toggler .content { /* toggler-text */ 
			font: 14px bold Verdana, Verdana, Arial, Helvetica, sans-serif;
		}
		.ui-layout-toggler:hover { /* mouse-over */ 
			background: #DCA; 
			} 
			.ui-layout-toggler:hover .content { /* mouse-over */ 
				color: #009; 
				}

		/* masks are usually transparent - make them visible (must 'override' default) */
		.ui-layout-mask {
			background:	#C00 !important;
			opacity:	.20 !important;
			filter:		alpha(opacity=20) !important;
		}

	ul { /* basic menu styling */
		margin:		1ex 0;
		padding:	0;
		list-style:	none;
		position:	relative;
	}
	li {
		padding: 0.15em 1em 0.3em 5px;
	}

	</style>

  <script>
  $(function() {
    $( "#menu" ).menu();
  });
  </script>
<style>
  .ui-menu { width: 100%; }
  </style>
</head>
<body>

<iframe id="mainFrame" name="mainFrame" class="ui-layout-center" width="100%" height="600" frameborder="0" scrolling="auto"
	src="${root}/frame-mid.html">
</iframe>


<div class="ui-layout-north ui-widget-header" >
	<table width="100%" class="table_portalet" align="center" cellpadding="8" cellspacing="0" style="background-image:url(${root}/images/title.png);">
		<tr height="90">				
			<td align="right" valign="bottom">		
			<a href="${root}/admin.do">首页</a>&nbsp;<a href="#">|</a>&nbsp;
			<%-- <a target="mainFrame" href="${root}/console/feedback!input.do">意见反馈</a>&nbsp;<a href="#">|</a>&nbsp;
			<a target="mainFrame" href="${root}/console/changepassword.do">修改密码</a>&nbsp;<a href="#">|</a>&nbsp; --%>
			<a href="${root}/logout.do">注销</a>&nbsp;&nbsp;</td>
		</tr>
	</table>
</div>



<div class="ui-layout-west">
<ul id="menu">
<li></li>

   <li><a target="mainFrame" href="${root}/vote/tj_vote.do"><span class="ui-icon ui-icon-circle-triangle-e"></span>推荐表决</a></li>
  <li></li>
  
  <li><a target="mainFrame" href="${root}/vote/ps_vote.do"><span class="ui-icon ui-icon-circle-triangle-e"></span>评审表决</a></li>
  <li></li>
 
  <li><a target="mainFrame" href="${root}/vote/voter.do"><span class="ui-icon ui-icon-circle-triangle-e"></span>在线用户</a></li>
  <li></li>

 </ul>
</div>


</body>
</html>