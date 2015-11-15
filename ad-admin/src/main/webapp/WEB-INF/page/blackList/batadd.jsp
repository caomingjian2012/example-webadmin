<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
<title>批量添加黑名单</title>
<script type="text/javascript" language="javascript">
function ajaxHandle(){
	if($("#uuids").val() == ""){
		alert("请输入UUIDs");
		return ;
	}
	if($("#remark").val() == ""){
		alert("请输入备注");
		return ;
	}

	$.ajax({
		"url":"${basePath }/blackList/ajaxbatadd",
		type: 'post',  
		data:"uuids="+$("#uuids").val()+"&remark="+$("#remark").val(),
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
					
				</td>
				<td>
					<input type="hidden" id="uuids" name="uuids" value="${uuids}"> 
				</td>
			</tr>
			
			<tr>
				<td>
					批次备注：
				</td>
				<td>
					<input type="text" id="remark" name="remark" value="${remark}"> 
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
			
			<tr>
				<td>
					UUID:
				</td>
				<td>
					<c:forTokens items="${uuids }" delims="," var="uuid">
						${uuid }<br>
					</c:forTokens>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>

