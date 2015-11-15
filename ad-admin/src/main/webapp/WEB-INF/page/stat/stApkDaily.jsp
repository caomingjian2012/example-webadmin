<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apk每日统计</title>
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
					APK <input type="text" name="kw" id="kw"  value="${query.kw }" class="input-small">
							日期：	<input type="text" name="calendarBegin" id="calendarBegin" onClick="WdatePicker()" value="<fmt:formatDate value="${query.calendarBegin}" pattern="yyyy-MM-dd" />" class="input-medium"> 至
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
								<th>日期</th>
								<th>apkName</th>
								<th>名称</th>
								<th>新增client数</th>
								<th>新增人数</th>
								<th>启动人数</th>
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
										<td><fmt:formatDate value="${item.calendar}" pattern="yyyy-MM-dd"/>   </td>
										
										<td>${item.apkName}</td>
										<td>${apkMap[item.apkName].market}</td>
										<td>${item.clients}</td>
										<td>${item.mobiles}</td>
										<td>${item.launchmans}</td>										
										<td>${item.responses} (${item.responsemans})</td>
										<td>${item.shows}(${item.showmans})</td>
										<td>${item.clicks}(${item.clickmans})</td>
										<td>${item.downloads}(${item.downloadmans})</td>
										<td>${item.installs}(${item.installmans}) </td>
										<td>${item.actives}(${item.activemans})</td>
										
										
									</tr>				
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			
				
								

	</body>
</html>