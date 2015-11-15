<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广告统计</title>
<script src="${ctx }/js/highcharts.js"></script>
<script src="${ctx }/js/exporting.js"></script>
<script type="text/javascript">

function submitonce(){
	 $('#searchBtn').attr("disabled","disabled");
	 $('#form').submit();
}
</script>
	</head>
	<body>
	 
	
			<div class="row-fluid">
				<div class="span12">
					 <form id ="form" class="form-inline" action="" method="get">
					ad: <input type="text" name="kw" id="kw"  value="${query.kw }" class="input-small">
							
							<input type="button" id="searchBtn" name="searchBtn" value="查询" onclick="submitonce()">
							
						</form> 
						
					
				</div>
			</div>
			
		
							<!-- 列表 -->	
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
								
								<th>AD</th>
								<td>名称</td>	
								<th>投放次数(人)</th>
								<th>展示次数(人)</th>
								<th>点击次数(人)</th>
								<th>下载成功次数(人)</th>
								<th>安装成功次数(人)</th>
								<th>激活次数(人)</th>
								 
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="item" varStatus="vs">
									
									<tr>
										
										
										<td>${item.ad}</td>
										<td>${adMap[item.ad].adname}</td>										
										<td>${item.responses} (-)</td>
										<td>${item.shows}(-)</td>
										<td>${item.clicks}(-)</td>
										<td>${item.downloads}(-)</td>
										<td>${item.installs}(-}) </td>
										<td>${item.actives}(-})</td>
										
										
									</tr>				
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			
				
								

	</body>
</html>