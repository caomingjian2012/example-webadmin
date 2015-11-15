<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>添加用户</title>
</head>
<body>

<form id="pageForm" action="user/add" method="post">
<!-- 防止表单重复提交 -->

	<table>
	
		<tbody>
			<tr>
				<td>
					用户名：
				</td>
				<td>
					<input type="text" name="username" value="${user.username}"> 全字母和数字
				</td>
			</tr>
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
					<input type="submit" id="saveBtn" name="saveBtn"  >
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>

