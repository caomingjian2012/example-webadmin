<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>添加开发者应用</title>
<script type="text/javascript" language="javascript">
function ajaxHandle(){
	if($("#name").val() == ""){
		alert("请输入渠道代码");
		return ;
	}

	$.ajax({
		"url":"${basePath }/devApp/ajaxadd",
		type: 'post',  
		data:"name="+$("#name").val(),
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
					应用名称：
				</td>
				<td>
					<input type="text" id="name" name="name" value="${entity.name}"> 最大长度20
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

