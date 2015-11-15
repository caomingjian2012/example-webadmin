<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>添加移除的内置包</title>
<script type="text/javascript" language="javascript">
function ajaxHandle(){
	if($("#packageName").val() == ""){
		alert("请输入packageName");
		return ;
	}
	if($("#name").val() == ""){
		alert("请输入name");
		return ;
	}
	$.ajax({
		"url":"${basePath }/rmBuiltInPackage/ajaxadd",
		type: 'post',  
		data:"packageName="+$("#packageName").val()+"&name="+$("#name").val(),
		dataType: "text",
		success: function (context){
			if(context != null){
				if(context=='success'){
					alert("成功");
				}else{
					alert(context);
				}
				
			}
		}
	});
}
</script>

</head>
<body>

<form id="pageForm" action="" method="post">
<!-- 防止表单重复提交 -->

	<table>
	
		<tbody>
			<tr>
				<td>
					packageName：
				</td>
				<td>
					<input type="text" id="packageName" name="packageName" value="${entity.packageName}"> 
				</td>
			</tr>
			<tr>
				<td>
					Name：
				</td>
				<td>
					<input type="text" id="name" name="name" value="${entity.name}"> 
				</td>
			</tr>
			
			
			
		
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					<input type="button" id="saveBtn" name="saveBtn" value="添加" onclick="ajaxHandle()" >
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>

