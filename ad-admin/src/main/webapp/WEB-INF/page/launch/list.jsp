<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>启动记录</title>
<script type="text/javascript" src="<c:url value="/js/application.js"/>"></script>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${query.sortColumns}','page');
		
	
	});

</script>
 



	</head>
	<body>
			<div class="row-fluid">
				<div class="span12">
				
						
					
				</div>
			</div>
			<!-- 列表 -->	
			 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">
			<div class="row-fluid">
				<div class="span12">
				
				客户端：<input type="text" name="clientId" id="clientId" value="${query.clientId }"  class="input-medium" style="width: 100px">
				 <input type="submit">	
				 
			
				 
				 
				
				 
				 <br>	
							

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
				<table id="contentTable"  class="gridBody table table-striped table-bordered table-condensed ">
						<tbody>
							<tr>
								<td> ID</td>
								
								<th>clientId</th>
								
								<th sortColumn="createTime">创建时间</th>
								<th>操作</th>
								
								
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="entity" varStatus="vs">
									
									<tr>
										<td> ${entity.id }</td>
										<td>${entity.clientId }</td>
										
										<td>${entity.createTime }</td>
										<td>
										
										 
										
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