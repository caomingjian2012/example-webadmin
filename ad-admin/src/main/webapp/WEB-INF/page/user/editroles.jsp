<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
	<title>分配角色</title>
</head>
<body>

<form action="user/editroles" method="post">
	<input type="hidden" name="id" value="${user.id}">
	<table>
	
		<tbody>
			
			<tr>
				<td >
					给用户${user.username}(${user.name})配置角色
				</td>
				
			</tr>
			<tr>
				
				<td>
				
					
					<c:forEach items="${userRoleVos}" var="role" varStatus="vs">
					<c:if test="${role.checked }">
						<input type="checkbox"  name="roleIds" value="${role.id}" checked  >${role.name }<br/>
					</c:if>
					<c:if test="${!role.checked }">
						<input type="checkbox"  name="roleIds" value="${role.id}"   >${role.name }<br/>
					</c:if>
					
					</c:forEach>
				</td>
			</tr>
			
			
			
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					<input type="submit">
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>

