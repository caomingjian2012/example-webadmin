<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>修改密码</title>
</head>
<body>

<form action="user/editpw" method="post">
	<input type="hidden" name="id" value="${user.id}">
	<table>
	
		<tbody>
			
			<tr>
				<td>
					密码:
				</td>
				<td>
					<input type="password" name="password" value=""> 
				</td>
			</tr>
			<tr>
				<td>
					确认密码:
				</td>
				<td>
					<input type="password" name="password1" value=""> 
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

