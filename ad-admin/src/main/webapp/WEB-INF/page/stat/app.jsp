<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APP推广效果统计</title>
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
									<input type="text" name="calendarBegin" id="calendarBegin" onClick="WdatePicker()" value="<fmt:formatDate value="${query.calendarBegin}" pattern="yyyy-MM-dd" />" class="input-medium"> 至
							<input type="text" name="calendarEnd" id="calendarEnd" onClick="WdatePicker()" value="<fmt:formatDate value="${query.calendarEnd}" pattern="yyyy-MM-dd" />" class="input-medium">
							
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
								
								<th>APPCD</th>
								<th>app名称</th>
								<th>拉取次数</th>
								<th>拉取人数</th>
								<th>拉取成功次数</th>
								<th>拉取成功人数</th>
								<th>点击次数</th>
								<th>点击人数</th>
								<th>下载人数</th>
								<th>安装人数</th>
								<th>激活人数</th>
	
								
								
							</tr>
							<c:if test="${!empty list }">
								<c:forEach items="${list }" var="item" varStatus="vs">
									
									<tr>
										
										
										<td>${item.appcd}</td>
										<td>${item.appname}</td>
										<td>${item.pulltimes}</td>
										<td>${item.pullmans}</td>
										<td>${item.pullOktimes} </td>
										<td>${item.pullOkmans}</td>
										<td>${item.clicktimes}</td>

										<td>${item.clickmans}</td>
										<td>${item.downloads}</td>
										<td>${item.installs} </td>
										<td>${item.actives}</td>
										
										
										
										
									</tr>				
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			
				
								

	</body>
</html>