<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>


<html>
<head>
<title>left</title>
<link href="${ctx}/css/left.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
</head>
<body>
<div class="lefttop"><img src="${ctx}/images/admin/left_top.gif" width="182" height="30" /></div>

<div  class="left clearfix">
	
	<ul id="nav">
		<li>
			<a href="#Menu=ChildMenu4" onClick="DoMenu('ChildMenu4')">统计</a>
			<ul id="ChildMenu4" class="collapsed">
					
					<li><a target="main" href="${ctx}/stat/stApkDaily">apk日统计</a></li>
					<li><a target="main" href="${ctx}/stat/stAdDaily">ad日统计</a></li>
					<li><a target="main" href="${ctx}/stat/stPushAppDaily">pushApp日统计</a></li>
					<li><a target="main" href="${ctx}/stat/stPushApp">pushApp统计</a></li>
					<li><a target="main" href="${ctx}/stat/stAd">ad统计</a></li>
				
					
			</ul>
		</li>

		<li>
			
			<a href="#Menu=ChildMenu1" onClick="DoMenu('ChildMenu1')">黑白名单管理</a>
			<ul id="ChildMenu1" class="collapsed">
					<li><a target="main" href="${ctx}/whiteList">手机白名单</a></li>
					<li><a target="main" href="${ctx}/blackList">手机设备黑名单</a></li>
					<li><a target="main" href="${ctx}/apkWhiteList">包白名单</a></li>
					<li><a target="main" href="${ctx}/apkBlackList">包黑名单</a></li>
					
					
			</ul>
		</li>
		
		
	<li>
			<a href="#Menu=ChildMenu2" onClick="DoMenu('ChildMenu2')">内容管理</a>
			<ul id="ChildMenu2" class="collapsed">
					<li><a target="main" href="${ctx}/devApp">开发中应用</a></li>
					<li><a target="main" href="${ctx}/pushApp">推广内容</a></li>
					<li><a target="main" href="${ctx}/ad?type=1">插屏管理</a></li>
					<li><a target="main" href="${ctx}/ad?type=2">PUSH管理</a></li>
					<li><a target="main" href="${ctx}/ad?type=3">BANNER管理</a></li>
					<li><a target="main" href="${ctx}/ad?type=5">静默管理</a></li>
					<li><a target="main" href="${ctx}/ad?type=4">内置管理</a></li>
					<li><a target="main" href="${ctx}/rmBuiltInPackage">移除内置应用管理</a></li>
					
			</ul>
		</li>
		<li>
			<a href="#Menu=ChildMenu6" onClick="DoMenu('ChildMenu6')">APK中心</a>
			<ul id="ChildMenu6" class="collapsed">
					<li><a target="main" href="${ctx}/apk">APK</a></li>
					<li><a target="main" href="${ctx}/cpaProfit">CPA收益</a></li>
					<li><a target="main" href="${ctx}/cpsProfit">CPS收益</a></li>
				
					
			</ul>
		</li>
		<li>
			<a href="#Menu=ChildMenu3" onClick="DoMenu('ChildMenu3')">客户端行为管理</a>
			<ul id="ChildMenu3" class="collapsed">
					<li><a target="main" href="${ctx}/launch">启动记录</a></li>
					<li><a target="main" href="${ctx}/adRequest">拉取广告记录</a></li>
 
					<li><a target="main" href="${ctx}/adShow">广告展示记录</a></li>
					<li><a target="main" href="${ctx}/adClick">广告点击记录</a></li>
					<li><a target="main" href="${ctx}/adDownload">广告下载记录</a></li>
					<li><a target="main" href="${ctx}/adInstall">广告安装记录</a></li>
					<li><a target="main" href="${ctx}/adActive">广告激活记录</a></li>
					 
					
					<li><a target="main" href="${ctx}/adClient">客户端管理</a></li>
					<li><a target="main" href="${ctx}/mobile">手机管理</a></li>
					<li><a target="main" href="${ctx}/adBuiltIn">内置记录</a></li>
					<li><a target="main" href="${ctx}/rmMsg">移除内置记录</a></li>
				
					
			</ul>
		</li>
		
		<li>
			<a href="#Menu=ChildMenu5" onClick="DoMenu('ChildMenu5')">用户管理</a>
			<ul id="ChildMenu5" class="collapsed">
				<shiro:hasPermission name="user:menu">
				<li><a target="main" href="${ctx}/user">用户管理</a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="role:menu">
				<li><a target="main" href="${ctx}/role">角色管理</a></li>
				</shiro:hasPermission>
				
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
		
		DoMenu($menuid);
		menuFix();
	}
});
</script>


</body>

</html>

