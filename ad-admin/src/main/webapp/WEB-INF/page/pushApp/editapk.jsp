<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${basePath }">
<title>修改推广APK</title>
<script type="text/javascript" src="js/jquery.js"></script>






</head>
<body>

		

		
			
		
		
		
		<form action="" method="post" name="addForm">
		
			<table>
			<tr>
			<td>
			应用名称：
			</td>
			<td>
			<input type="text" id="name" name="name"  value="${entity.name}" readonly="readonly"   />
			 </td>
			</tr>
			
	
			
			
		
			
			
			
			
		
			
			
		
			
			
			<tr>
			<td>
				推广国家
			</td>
			<td>
				<textarea id="countryArr"  name="countryArr" rows="20" cols="30">
				
					${entity.countryArr }
				</textarea>
			 </td>
			</tr>
			
			<tr>
			<td>
			
			</td>
			<td>
			<input type="submit"  />
			 </td>
			</tr>
				
		
		
			
			</table>
		</form>
</body>
</html>

