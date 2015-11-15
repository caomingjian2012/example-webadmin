<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<script type="text/javascript">
function del(id){
	var rn = Math.round(Math.random()*50000);
	var result = $.ajax({
		type:"get",
		url:"${ctx}/role/del",
		async:false,
		dataType:"json",
		data:"rn="+rn+"&id="+id
	});
	var data=result.responseText;
	if(data=="success"){
		alert("删除成功");
		window.location.reload(true);
	}else{
		alert("删除失败");
	}
}
</script>
	</head>
	<body>
			<div class="row-fluid">
				<div class="span12">
					<a href="role/add"  class="btn btn-primary">添加</a>
						
					
				</div>
			</div>
							<!-- 列表 -->	
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
								<th><input type="checkbox"></th>
								<th>序号</th>
								<th>ID</th>
								<th>角色名称（字母）</th>
								<th>角色显示名称</th>
								
								<th>操作</th>
							</tr>
							<c:if test="${!empty roles }">
								<c:forEach items="${roles }" var="role" varStatus="vs">
									
									<tr>
										<td><input type="checkbox"></td>
										<td>${vs.index+1}</td>
										<td>${role.id }</td>
										<td>${role.name }</td>
										<td>${role.content }</td>
										
										<td class="op">
										&nbsp;	<a
											href="role/edit?id=${role.id}">修改</a>
											
											&nbsp;<a href="javascript:del(${role.id })"  onclick="return confirm('确定要删除么?')"><i class="icon-remove"></i></a>
											
										&nbsp;	<a href="users.do?id=${role.id}">查看用户</a>
											&nbsp;<a href="role/editperms.do?id=${role.id}">配置权限</a>
															
										</td>
									</tr>				
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 分页 -->
			<div class="row-fluid"></div>			
								
			
	</body>
</html>