<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CPS收益记录</title>
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
	
	 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">

			<div class="row-fluid">
				<div class="span12" >
					
					 			APK包：<select name="apk">
					 				<option value="">全部</option>
					 			<c:forEach items="${userApks }" var="item" varStatus="vs">
					 				<c:if test="${!(item.apk eq apk) }">
					 					<option value="${item.apk }">${item.apk}</option>
					 				</c:if>
					 				<c:if test="${ (item.apk eq apk) }">
					 					<option value="${item.apk }" selected="selected" >${item.apk}</option>
					 				</c:if>
					 				
					 			</c:forEach>
					 			</select>
					 		
					 				
					 			
							收益日期：<input type="text" name="calendarBegin" id="calendarBegin" onClick="WdatePicker()" value="<fmt:formatDate value="${calendarBegin}" timeStyle="date"/>"  class="input-medium"> 至
							<input type="text" name="calendarEnd" id="calendarEnd" onClick="WdatePicker()" value="<fmt:formatDate value="${calendarEnd}" timeStyle="date"/>" class="input-medium">
							<input type="submit">
				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
					
							
				</div>
			</div>
							<!-- 列表 -->	
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable"  class="gridBody table table-striped table-bordered table-condensed ">
						<tbody>
							<tr>
								<th width="8%" sortColumn="calendar">收益日期</th>
								<th  width="10%" sortColumn="apk">包</th>								
								
								
								
								
								
								<th width="10%" >新增用户</th>
								<th width="10%" >推广收入</th>
								
								
								
							
								
							</tr>
							<c:if test="${!empty list }">
								<c:forEach items="${list }" var="item" varStatus="vs">
									
									<tr>
										
										<td><fmt:formatDate value="${item.calendar }" pattern="yyyy-MM-dd"/></td>
										<td>${item.apk}</td>										
										<td>${item.mobiles}</td>
										
									
									<td ><fmt:formatNumber pattern="##.##" value="${(item.marketProfit+0.00001)/100 }"> </fmt:formatNumber></td>	
									
									</tr>				
								</c:forEach>
							</c:if>
							<tr bgcolor="#ccc">
										
										<td>总计：</td>
										<td></td>
										
										 <td >${mobielSum }</td>
										<td ><fmt:formatNumber pattern="##.##" value="${(marketProfitSum+0.00001)/100 }"> </fmt:formatNumber></td>
										
								
									
									</tr>
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