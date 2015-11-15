<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>修改用户资料</title>
</head>
<body>

<form action="user/edit" method="post">
	<input type="hidden" name="id" value="${user.id}">
	<table>
	
		<tbody>
			<tr>
				<td>
					角色名称：
				</td>
				<td>
					<input type="text" name="username" value="${user.username}" disabled="disabled">
				</td>
			</tr>
			
			
			<tr>
				<td>
					姓名：
				</td>
				<td>
					<input type="text" name="name" value="${user.name}"> 
				</td>
			</tr>
			<tr>
				<td>
					邮箱
				</td>
				<td>
					<input type="text" name="mail" value="${user.mail}"> 
				</td>
			</tr>
			<tr>
				<td>
					电话
				</td>
				<td>
					<input type="text" name="telephone" value="${user.telephone}"> 
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

