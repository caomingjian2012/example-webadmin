<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>修改角色</title>
</head>
<body>
<form action="role/edit" method="post">
	<input type="hidden" name="id" value="${role.id}">
	<table>
	
		<tbody>
			<tr>
				<td>
					角色名称：
				</td>
				<td>
					<input type="text" name="name" value="${role.name}">
				</td>
			</tr>
			<tr>
				<td>
					备注(显示名称):
				</td>
				<td>
					<input type="text" name="content" value="${role.content}">
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

