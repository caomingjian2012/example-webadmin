<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>添加角色</title>
</head>
<body>
<form id="pageForm" action="role/add" method="post">
<!-- 防止表单重复提交 -->

	<table>
	
		<tbody>
			<tr>
				<td>
					角色名称：
				</td>
				<td>
					<input type="text" name="name" value="${role.name}"> 全字母和数字
				</td>
			</tr>
			<tr>
				<td>
					显示名称:
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
					<input type="submit" id="saveBtn" name="saveBtn"  >
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>

