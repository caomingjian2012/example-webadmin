<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>


<html>
<head>
<title>left</title>
<link href="${ctx}/css/left.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
</head>
<body>

<%
Subject currentUser = SecurityUtils.getSubject();

%>
<div class="lefttop"><img src="${ctx}/images/admin/left_top.gif" width="182" height="30" /></div>

<div  class="left clearfix">
	
	<ul id="nav">
		<li>
			<a href="#Menu=ChildMenu1" onClick="DoMenu('ChildMenu1')">渠道管理</a>
			<ul id="ChildMenu1" class="collapsed">
				
				
						<li><a target="main" href="${ctx}/cps/profit">CPS收益记录</a></li>
				
						<li><a target="main" href="${ctx}/userProfile/editpw">修改密码</a></li>							
				
				
			</ul>
		</li>
	
		
		
		
	</ul>
</div>
<script type="text/javascript">
<!--
var LastLeftID = "";
function menuFix() {
var obj = document.getElementById("nav").getElementsByTagName("li");
for (var i=0; i<obj.length; i++) {
   obj[i].onmouseover=function() {
    this.className+=(this.className.length>0? " ": "") + "sfhover";
   }
   obj[i].onMouseDown=function() {
    this.className+=(this.className.length>0? " ": "") + "sfhover";
   }
   obj[i].onMouseUp=function() {
    this.className+=(this.className.length>0? " ": "") + "sfhover";
   }
   obj[i].onmouseout=function() {
    this.className=this.className.replace(new RegExp("( ?|^)sfhover\\b"), "");
   }
}
}
function DoMenu(emid)
{
var obj = document.getElementById(emid);
obj.className = (obj.className.toLowerCase() == "expanded"?"collapsed":"expanded");
if((LastLeftID!="")&&(emid!=LastLeftID)) //关闭上一个Menu
{
   document.getElementById(LastLeftID).className = "collapsed";
}
LastLeftID = emid;
}
function GetMenuID()
{
var MenuID="";
var _paramStr = new String(window.location.href);
var _sharpPos = _paramStr.indexOf("#");
if (_sharpPos >= 0 && _sharpPos < _paramStr.length - 1)
{
   _paramStr = _paramStr.substring(_sharpPos + 1, _paramStr.length);
}
else
{
   _paramStr = "";
}
if (_paramStr.length > 0)
{
   var _paramArr = _paramStr.split("&");
   if (_paramArr.length>0)
   {
    var _paramKeyVal = _paramArr[0].split("=");
    if (_paramKeyVal.length>0)
    {
     MenuID = _paramKeyVal[1];
    }
   }
   
}

if(MenuID!="")

{

   DoMenu(MenuID)
   
}

}
GetMenuID(); //*这两个function的顺序要注意一下，不然在Firefox里GetMenuID()不起效果
menuFix();
--></script>
<script type="text/javascript">
$(function(){
	var $menuid=$("#menuid").val();
	if($menuid!=""){
		//alert($menuid);
		DoMenu($menuid);
		menuFix();
	}
});
</script>


</body>

</html>

