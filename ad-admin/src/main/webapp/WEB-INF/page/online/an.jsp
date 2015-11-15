<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线用户分析</title>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		
		
	
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
				
					
							

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
				<table id="contentTable" style="width: 200px;"  class="gridBody table table-striped table-bordered table-condensed ">
						<tbody>
							
							
								<c:forEach items="${channelMap }" var="entry" >
									
									<tr>
										
										<td>${entry.key }</td><td>${entry.value }</td>
										
									</tr>				
								</c:forEach>
							
						</tbody>
					</table>
					
					
					
					
					
				</div>
			</div>
			<!-- 分页 -->
			<div class="row-fluid">
				
			
			</div>			
					 </form>			
			
	</body>
</html>