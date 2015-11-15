<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
	<title>广告插件管理后台</title>
	<meta http-equiv=Content-Type content=text/html;charset=UTF-8 />
	<link type="image/x-icon" href="${ctx}/assets/images/favicon.ico" rel="shortcut icon">
  </head>
  
<frameset rows="100,*"  frameborder="NO" border="0" framespacing="0">
	<frame src="top" noresize="noresize" frameborder="NO" name="top" scrolling="no" marginwidth="0" marginheight="0"  />
  <frameset cols="182,*"  rows="100%,*" id="frame">
	<frame src="left" name="left" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto"  />
	<frame src="welcome" id="main" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto"  />
  </frameset>
 
<noframes>
  <body></body>
</noframes>
</html>
