<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>




<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
*{margin:0; padding:0;}
.main_top{
	
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
.toptar .top_button2 { margin-left:0px; color: #135294;  height:26px; border:0; }

table {
	font-size: 12px;
	font-family: tahoma, 宋体, fantasy;
}

td {
	font-size: 12px;
	font-family: tahoma, 宋体, fantasy;
}
</style>
<script>

		function loginout(){
			if(confirm("确定要退出系统吗？")){   
				window.parent.location.href="${ctx}/logout";
	  		}
		}	
	
	
</script>
</head>

<body >
<table border=0 cellpadding=0 cellspacing=0 height="100%" width="100%">
	<tr class="main_top" >
		<td height="80" colspan="3">
		
		<div class="system_logo" style=""><img alt="掌心科技" src="${ctx}/images/logo.png"  ></div>
		
		 
		<div class="toptar">
		<div class="top_button"><a target="main" href="${ctx}/welcome"><img src="${ctx}/images/admin/home.gif" alt="" width="90" height="26" border="0" /></a></div>   
		
		<!--
		<div class="top_button"><a  style="cursor:pointer" href="${ctx}/system/user/editLoginPwd.do" target="main"><img src="${ctx}/images/admin/editpwd.gif" alt="" width="90" height="26" border="0" /></a></div>
		  -->
		<div class="top_button"><a onclick="loginout()" style="cursor:pointer" ><img src="${ctx}/images/admin/tuichu.gif" alt="" width="90" height="26" border="0" /></a></div>
		<div class="top_button2"><font color="#135294"><shiro:principal/>，您已经成功登录！ </font></div>   
		</div>
		</td>
	</tr>
	<tr>
		
	</tr>
</table>
</body>
</html>
