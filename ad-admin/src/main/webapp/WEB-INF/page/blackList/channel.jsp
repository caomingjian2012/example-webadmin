<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>渠道列表</title>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
	
	
	});

</script>


	</head>
	<body>
			<div class="row-fluid">
				<div class="span12">
					
				</div>
			</div>
			<!-- 列表 -->	
			
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
							
								<th>渠道</th>
								<th>累计用户</th>
								
							</tr>
							
								<c:forEach items="${list }" var="entity" varStatus="vs">
									
									<tr>
										
										<td>${entity.channel }</td>
										<td>${entity.mans }</td>
										
									</tr>				
								</c:forEach>
						
						</tbody>
					</table>
				</div>
			</div>
					
			
	</body>
</html>