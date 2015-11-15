<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>黑名单列表</title>
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

<script type="text/javascript">
function del(id){
	var rn = Math.round(Math.random()*50000);
	var result = $.ajax({
		type:"get",
		url:"${ctx}/blackList/del",
		async:false,
		dataType:"json",
		data:"rn="+rn+"&id="+id
	});
	var data=result.responseText;
	if(data=="success"){
		alert("删除成功");
		window.location.reload(true);
	}else{
		alert(data);
	}
}
</script>
	</head>
	<body>
			<div class="row-fluid">
				<div class="span12">
					<a href="blackList/add"  class="btn btn-primary ajax">添加</a>
						
					
				</div>
			</div>
			<!-- 列表 -->	
			 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">
			<div class="row-fluid">
				<div class="span12">
				
				&nbsp;UUID：<input type="text" name="kw" id="kw" value="${query.kw }"  class="input-medium" style="width: 100px">
				 <input type="submit">	<br>		
							

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
							
								<th>ID</th>
								<th>UUID</th>
								<th>时间</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="entity" varStatus="vs">
									
									<tr>
										
										<td>${entity.id }</td>
										<td>${entity.uuid }</td>
										<td>${entity.createTime }</td>
										<td>${entity.remark }</td>


									
										<td class="op">
										
											
											&nbsp;<a href="javascript:del(${entity.id })"  onclick="return confirm('确定要删除么?')"><i class="icon-remove"></i></a>
											
										
									
															
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