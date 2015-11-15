<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath }">
	<title>关联包</title>
	<script type="text/javascript">
function del(id){
	var rn = Math.round(Math.random()*50000);
	var result = $.ajax({
		type:"get",
		url:"${ctx}/userApk/del",
		async:false,
		dataType:"json",
		data:"rn="+rn+"&id="+id
	});
	var data=result.responseText;
	if(data=="success"){
		alert("删除成功");
		window.location.reload(true);
	}else{
		alert(data);
	}
}
</script>
	<script type="text/javascript">
function add(){
	var rn = Math.round(Math.random()*50000);
	var username =$("#username").val();
	var apk = $("#apk").val();
	
	var result = $.ajax({
		type:"get",
		url:"${ctx}/userApk/ajaxAdd",
		async:false,
		dataType:"text",
		data:"rn="+rn+"&username="+username+"&apkCode="+apk
	});
	var data=result.responseText;
	if(data=="success"){
		alert("添加成功");
		window.location.reload(true);
	}else{
		alert(data);
		window.location.reload(true);
	}
}
</script>
</head>
<body>
<div class="row-fluid">
				<div class="span12">
					<a href="user"  class="btn btn-primary">返回</a>
						
					
				</div>
			</div>
<form action="userChannel/ajaxAdd" method="post">
	<input type="hidden" id ="username" name="username" value="${user.username}">
	<table>
	
		<tbody>
			
			
			
			<tr>
				<td >
					包代码：<input style="width: 80px" type="text" id="apk" name="apk"  >  
				</td>
				<td style="padding-bottom : 8px;" >
					<input type="button" onclick="add()" value="快速关联">
				</td>
			</tr>
		
			
			
		</tbody>
	</table>
	</form>
	<table>
	
		<tbody>
			<tr>
				<td >
					用户 ${user.username}(${user.name}) 关联包列表:
				</td>
				<td >
					
				</td>
			</tr>
			
			<c:forEach items="${list}" var="item" varStatus="vs">
			<tr>
				
				<td>
					${item.apk }<br>
					
				</td>
				<td>
					&nbsp;<a href="javascript:del(${item.id })"  onclick="return confirm('确定要删除么?')"><i class="icon-remove"></i></a>
				</td>
			</tr>
			
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>

