<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>




<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<style type="text/css">
*{margin:0; padding:0;}
.main_top{
	 background-image:url("${ctx}/images/admin/top_bg.gif");
	}

div { margin:0px; padding:0px; }
.system_logo { width:350px; height:80px; float:left; text-align:left;}
/*- Menu Tabs 6--------------------------- */
#tabs {float: none; width:auto; text-align:right; line-height:normal;}
#tabs ul { float:left; margin-right:30px; margin-top:0; list-style:none;}
#tabs li {display:inline; margin:0; padding:0; }
#tabs a {
  float:left;
  background:url("${ctx}/images/admin/tableft6.gif") no-repeat left top;
  margin:0;
  padding:0 0 0 4px;
  text-decoration:none;
}
#tabs a span {
  float:left;
  display:block;
  background:url("${ctx}/images/admin/tabright6.gif") no-repeat right top;
  padding:8px 8px 6px 6px;
  color:#E9F4FF;
}
/* Commented Backslash Hack hides rule from IE5-Mac \*/
#tabs a span {float:none;}
/* End IE5-Mac hack */
#tabs a:hover span {color:#fff; }
#tabs a:hover { background-position:0% -42px; }
#tabs a:hover span {background-position:100% -42px; color:#222;}

.toptar{float: none; width:auto; float:right; height:55px; text-align:right; padding-right:15px; padding-top:25px; line-height:normal;}
.toptar .top_button { float:left; margin-left:10px; width:90px; height:26px; border:0;}
.toptar .top_button2 { margin-left:5px; color: #135294; width:58px; height:26px; border:0; background:url(${ctx}/images/admin/topsubmit2.jpg);}

table {
	font-size: 12px;
	font-family: tahoma, 宋体, fantasy;
}

td {
	font-size: 12px;
	font-family: tahoma, 宋体, fantasy;
}
</style>

</head>

<body >
<table border=0 cellpadding=0 cellspacing=0 height="100%" width="100%">
	<tr class="main_top">
		优先级：白名单>黑名单
		
	</tr>
	<tr>
		
	</tr>
</table>
</body>
</html>
