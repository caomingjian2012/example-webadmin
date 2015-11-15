<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>广告插件登陆页面</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<STYLE type="text/css">
A: link{COLOR: #ffffff;FONT-SIZE: 12px;TEXT-DECORATION: none}
A: visited{COLOR: #ffffff;FONT-SIZE: 12px;TEXT-DECORATION: none}
A: hover{COLOR: #ffffff}
BODY input{MARGIN-TOP: 2px;FONT: 12px "微软雅黑";}
TD{LINE-HEIGHT: 15px;FONT-SIZE: 12px}
</STYLE>
</HEAD>
<BODY onload="refresh();">
<DIV align=center><BR><BR><BR><BR><BR>
<TABLE id=__01 border=0 cellSpacing=1 cellPadding=0 width=351 align=center>
  <TBODY>
  <TR>
    <TD>
      <TABLE border=0  cellSpacing=1 cellPadding=0 width="350"    height="120">
        <TBODY>
        <TR>
          <TD >
          	
            	<img alt="" style="height: 68px; padding-left: 50px;"  src="images/logo.png  ">
           
           
           
			</TD>
		</TR>
		</TBODY>
		</TABLE>
		 <TABLE border=0 cellSpacing=0 cellPadding=2 width=351>
              <FORM method=post name=form1 action="login">
              <TBODY>
              <TR>
                <TD height=25 width="25%">
                  <DIV align=right>用户名：</DIV></TD>
                <TD width="75%">
                  <DIV align=left><INPUT style="WIDTH: 200px" id=username 
                  size=25 name=username></DIV></TD></TR>
              <TR>
                <TD height=25>
                  <DIV align=right>密码：</DIV></TD>
                <TD>
                  <DIV align=left><INPUT style="WIDTH: 200px" id=password 
                  size=25 type=password name=password></DIV></TD></TR>
              <TR>
                <TD height=37>
                  <DIV align=left></DIV></TD>
                <TD>
                  <DIV align=left><P><INPUT value=登陆进入系统 type=submit name=B1></P></DIV>
				</TD>
			</TR>
			</FORM>
		</TBODY>
	</TABLE>
</TD>
</TR>
</TBODY>
</TABLE>
</DIV>
</BODY>
</HTML>

<script type="text/javascript">
function refresh()
{
	if(window.parent != window){
		parent.location.reload();
	}
}
</script>