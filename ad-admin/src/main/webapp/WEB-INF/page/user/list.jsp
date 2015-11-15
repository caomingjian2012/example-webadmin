<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'','page');
	});

</script>

<script type="text/javascript">
function del(id){
	var rn = Math.round(Math.random()*50000);
	var result = $.ajax({
		type:"get",
		url:"${ctx}/user/del",
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
	</head>
	<body>
			<div class="row-fluid">
				<div class="span12">
					<a href="user/add"  class="btn btn-primary">添加</a>
						
					
				</div>
			</div>
			<!-- 列表 -->	
			 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">
			<div class="row-fluid">
				<div class="span12">
				
				&nbsp;用户：<input type="text" name="wordLike" id="wordLike" value="${query.wordLike }"  class="input-medium" style="width: 100px">
				 <input type="submit">	<br>		
							

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
							
								<th>ID</th>
								<th>用户名</th>
								<th>姓名</th>
								<th>角色</th>
								<th>关联包</th>
								
								
								<th>操作</th>
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="user" varStatus="vs">
									
									<tr>
										
										<td>${user.id }</td>
										<td>${user.username }</td>
										<td>${user.name }</td>
										<td>${userRolesMap[user.id]==null?'未设置':userRolesMap[user.id]}</td>

										<td>
										<c:set var="apknames" value="${userApksMap[user.username]==null?'未设置':userApksMap[user.username]}"/>
											<c:choose> 
											     <c:when test="${fn:length(apknames) > 20}"> 
											    ${fn:substring(apknames, 0, 20)}......
											   </c:when> 
											   <c:otherwise> 
											    	${apknames}
											   </c:otherwise>
											  </c:choose>
										</td>
									
										<td class="op">
										&nbsp;	<a href="user/edit?id=${user.id}">修改资料</a>
										&nbsp;	<a href="user/editpw?id=${user.id}">修改密码</a>
											
											&nbsp;<a href="javascript:del(${user.id })"  onclick="return confirm('确定要删除么?')"><i class="icon-remove"></i></a>
											
										&nbsp;<a href="user/editroles?id=${user.id}" >分配角色</a>
									&nbsp;<a href="userApk?username=${user.username}" >关联包</a>
															
										</td>
									</tr>				
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 分页 -->
			<div class="row-fluid">
				<simpletable:pageToolbar page="${page }">
		
				</simpletable:pageToolbar>
			
			</div>			
					 </form>			
			
	</body>
</html>