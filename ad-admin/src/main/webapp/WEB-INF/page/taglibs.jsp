

<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath",basePath);
%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
