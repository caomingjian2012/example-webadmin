<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>配置权限</title>
</head>
<body>
<form action="role/editperms" method="post">

	<table>
	
		<tbody>
			<tr>
				<td>
					
				</td>
				<td>
					给角色${role.name}设置权限
					<input type="hidden" name="id" value="${role.id}"> 
					
					
				</td>
			</tr>
			<tr>
				<td>
					菜单权限
				</td>
				<td>
				

<c:forEach items="${rolePermissionVos}" var="item" varStatus="vs">
					<c:if test="${item.flag }">
						<input type="checkbox"  name="permissionIds" value="${item.id}" checked  >${item.content }<br/>
					</c:if>
					<c:if test="${!item.flag }">
						<input type="checkbox"  name="permissionIds" value="${item.id}"  >${item.content }<br/>
					</c:if>
					
					</c:forEach>
				
			
				</td>
			</tr>
			<tr>
				<td>
					
				</td>
				<td>
					<input type="submit" value="保存">

					
					
				</td>
			</tr>
			
			
			
		</tbody>
	</table>
</form>
</body>
</html>
