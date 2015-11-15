<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>推广应用</title>
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
			<div class="row-fluid">
				<div class="span12">
					<a href="pushApp/add?type=apk"  class="btn btn-primary">添加APK</a>
					<a href="pushApp/add?type=gp"  class="btn btn-primary">添加GP</a>	
					
				</div>
			</div>
			<!-- 列表 -->	
			 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">
			<div class="row-fluid">
				<div class="span12">
				
				&nbsp;<input type="text" name="kw" id="kw" value="${query.kw }"  class="input-medium" style="width: 100px">
				 <input type="submit">	<br>		
							

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
							
								
								<th>代码</th>
								<th>图标</th>
								<th>名称</th>
								<th>应用信息</th>
								 
								<th>下载地址</th>
								<th>状态</th>
								<th>类型</th>
								<th>国家</th>
								<th>上线时间</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="entity" varStatus="vs">
									
									<tr>
										<td>${entity.code }</td>
										<td><img alt="${entity.name }"  src="${entity.icon }"> </td>
										<td>${entity.name }</td>
										 <td>${entity.intro }</td>
										<td><a href="${entity.url }" >下载</a></td>	
										<c:if test="${entity.state==1}">
											<td>上架</td>
										</c:if>
										<c:if test="${entity.state!=1}">
											<td>下架</td>
										</c:if>
										 
										  <td>${entity.type }</td>	
										  <td style="width:300px;">${entity.countrys}</td>								
										<td><fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd"/> </td>
										<td class="op">
										<a href="pushApp/edit?code=${entity.code}">修改</a>
															
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