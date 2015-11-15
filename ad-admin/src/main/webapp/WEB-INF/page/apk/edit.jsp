<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${basePath }">
<title>修改渠道</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>

</head>
<body>
		

	<form action="apk/edit" method="post" name="addForm">
		
			<table>
			<tr>
			<td>
			代码：
			</td>
			<td>
			<input type="text" id="code" name="code"  readonly="readonly" value="${entity.code}"   />
			
			 </td>
			</tr>
			
			<tr>
			<td>
			名称：
			</td>
			<td>
				<input type="text" id="market" name="market"  value="${entity.market}"   />
			 </td>
			</tr>
			 
			
			<tr>
			<tr>
			<td>
			应用：
			</td>
			<td>
				<input type="text" id="app" name="app"  value="${entity.app}"   />
			 </td>
			</tr>
			<tr>
				<td class="label1">
					合作类型：
				</td>
				<td>
					<input type="text"  class="input1" name="type" value="${entity.type}"> cpa ,cps 
				</td>
			</tr>
			<tr>
				<td class="label1">
					单价：
				</td>
				<td>
					<input type="text"  class="input1" name="unitPrice" value="${entity.unitPrice}"> 单位分
				</td>
			</tr>
			<tr>
				<td class="label1">
					CPA比列：
				</td>
				<td>
					<input type="text"  class="input1" name="rate" value="${entity.rate}"> 0~100
				</td>
			</tr>
			<td>
				备注
			</td>
			<td>
				<textarea id="remark"  name="remark" rows="10" cols="10">${entity.remark }</textarea>
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

