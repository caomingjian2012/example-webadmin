<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>移除内置信息</title>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'','page');
		$(".ajax").colorbox({iframe:true, width:"50%", height:"70%",
			onClosed:function(){ window.location.reload(true); }
			});
	
	});

</script>


	</head>
	<body>
			
			<!-- 列表 -->	
			 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">
			<div class="row-fluid">
				<div class="span12">
				
				
				 <input type="submit">	<br>		
							

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
							
								<th>ID</th>
								<th>usercd</th>
								<th>packageName</th>
								<th>status</th>
								<th>时间</th>
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="entity" varStatus="vs">
									
									<tr>
										
										<td>${entity.id }</td>
										<td>${entity.userCd }</td>
										<td>${entity.packageName }</td>
										<td>${entity.msg }</td>
										<td>${entity.createTime }</td>
										
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