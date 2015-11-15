<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CPA收益列表</title>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${query.sortColumns}','page');
		$(".ajax").colorbox({iframe:true, width:"50%", height:"70%",
			onClosed:function(){ window.location.reload(true); }
			});

	
		});

</script>


	</head>
	<body>
	<div class="row-fluid">
				<div class="span12">
					<a href="cpaProfit/add"  class="btn btn-primary">添加</a>
						
				</div>
			</div>
	 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">			
			<div class="row-fluid">
				<div class="span12">
					 APK：	<input type="text" name="kw" value="${query.kw }">
					 		
					 				
					 			
							收益日期：<input type="text" name="calendarBegin" id="calendarBegin" onClick="WdatePicker()" value="<fmt:formatDate value="${query.calendarBegin}" timeStyle="date"/>"  class="input-medium"> 至
							<input type="text" name="calendarEnd" id="calendarEnd" onClick="WdatePicker()" value="<fmt:formatDate value="${query.calendarEnd}" timeStyle="date"/>" class="input-medium">
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
								<th  width="6%" >apk</th>								
								
								<th width="6%">激活数</th>
								<th width="8%" >单价</th>
								
								<th width="6%">收入</th>
								<th  width="10%" sortColumn="createTime">创建时间</th>
								<th  width="10%" sortColumn="lastEditTime">最后修改时间</th>
								<th width="10%">最后操作人</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="item" varStatus="vs">
									
									<tr>
										
										<td><fmt:formatDate value="${item.calendar }" pattern="yyyy-MM-dd"/></td>
										<td>${item.apk }</td>
										
										<td >${item.actives }</td>
										<td >${item.price /100}</td>
										<td >${item.marketProfit/100 }</td>																
										<td><fmt:formatDate value="${item.createTime }" pattern="yy-MM-dd HH:mm"/></td>
										<td><fmt:formatDate value="${item.lastEditTime }" pattern="yy-MM-dd HH:mm"/></td>
										<td>${item.lastEditor }</td>
										
										<td class="op">
										&nbsp;	<a  href="cpaProfit/edit?id=${item.id}">修改</a>
															
										</td>
									</tr>				
								</c:forEach>
							</c:if>
							<tr bgcolor="#ccc">
										
										<td>总计：</td>
										<td></td>
										
										<td>${total.actives }</td>
										<td >
										</td>
										<td > <fmt:formatNumber pattern="##.##" value="${(total.marketProfit+0.00001)/100 }"> </fmt:formatNumber>  </td>
										<td >
										</td>
										<td >
										</td>
										<td >
										</td>
										<td >
										
															
										</td>
									</tr>
						</tbody>
					</table>
				</div>
			</div>
		
			<!-- 分页 -->
			<div class="row-fluid">
				<simpletable:pageToolbar page="${page }">
		
				</simpletable:pageToolbar>
			 </form>
			</div>		
			<div class="row-fluid">
				<div class="span12">
				NOTE:<br>
			1.记录不可删除，如果要删除请将金额设置成0。
				</div>
			</div>	
								
			
	</body>
</html>