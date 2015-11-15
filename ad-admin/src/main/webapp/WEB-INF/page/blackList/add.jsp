<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>添加黑名单</title>
<script type="text/javascript" language="javascript">
function ajaxHandle(){
	if($("#uuid").val() == ""){
		alert("请输入UUID");
		return ;
	}

	$.ajax({
		"url":"${basePath }/blackList/ajaxadd",
		type: 'post',  
		data:"uuid="+$("#uuid").val()+"&remark="+$("#remark").val(),
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
					UUID：
				</td>
				<td>
					<input type="text" id="uuid" name="uuid" value="${entity.uuid}"> 36位
				</td>
			</tr>
			
			<tr>
				<td>
					备注：
				</td>
				<td>
					<input type="text" id="remark" name="remark" value="${entity.remark}"> 
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

