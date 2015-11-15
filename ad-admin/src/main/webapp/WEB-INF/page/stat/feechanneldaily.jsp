<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>渠道每日统计</title>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
	
	
	});

</script>

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
								
								<input type="text" name="code" value="${query.code }" placeholder="渠道代码">
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
								<th width="80">日期</th>
								<th width="150">渠道</th>
								<th width="80">新增用户</th>
								<th width="80">累计用户</th>
								<th width="50">状态</th>
								<th width="80">屏蔽用户数</th>
								<th width="280" >备注</th>
							</tr>
							
								<c:forEach items="${list }" var="entity" varStatus="vs">
									
									<tr>
										<td>${entity.calendar }</td>
										<td>${entity.feechannel }</td>
										<td>${entity.mans }</td>
										<td>${leijiMap[entity.feechannel]==null?0:leijiMap[entity.feechannel] }</td>
											<td>${feechannelMap[entity.feechannel]==null?"未添加":feechannelMap[entity.feechannel].statusStr }</td>
											<td>${blackMap[entity.feechannel]==null?0:blackMap[entity.feechannel] }</td>
											<td>${feechannelMap[entity.feechannel]==null?"":feechannelMap[entity.feechannel].remark }</td>
										
									</tr>				
								</c:forEach>
						
						</tbody>
					</table>
				</div>
			</div>
					
			
	</body>
</html>