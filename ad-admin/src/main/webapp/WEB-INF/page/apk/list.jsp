<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
APK管理

</title>
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
function up(id){
	var rn = Math.round(Math.random()*50000);
	var result = $.ajax({
		type:"get",
		url:"${basePath}/apk/ajaxValid",
		async:false,
		dataType:"json",
		data:"rn="+rn+"&code="+id
	});
	var data=result.responseText;
	if(data=="success"){
		alert("操作成功");
		window.location.reload(true);
	}else{
		alert(data);
	}
}
</script>
<script type="text/javascript">
function down(id){
	var rn = Math.round(Math.random()*50000);
	var result = $.ajax({
		type:"get",
		url:"${basePath}/apk/ajaxInvalid",
		async:false,
		dataType:"json",
		data:"rn="+rn+"&code="+id
	});
	var data=result.responseText;
	if(data=="success"){
		alert("操作成功");
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
					
					<a href="apk/add"  class="btn btn-primary ">添加</a>
				</div>
			</div>
			<!-- 列表 -->	
			 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">
			<div class="row-fluid">
				<div class="span12">
			
				<input type="text" name="code" id="code" value="${query.code }"  class="input-medium" style="width: 100px">
				 <input type="submit">	<br>		
							

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
							
								<th>APKNAME</th>
								<th>渠道</th>
								<th>游戏</th>
								<th >合作类型</th>
								<th >单价</th>
								<th >CPA比列</th>
								<th>创建时间</th>
								<th>状态</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="entity" varStatus="vs">
									
									<tr>
										
										<td>${entity.code }</td>
										<td>${entity.market }</td>
										<td>${entity.app }</td>	
										<td>${entity.type }</td>
										<td>  ${entity.unitPrice/100 }</td>
										<td>${entity.rate }</td>								
										<td> <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd HH:mm:dd"/> </td>
										
									
									<td>${entity.statusStr}</td>
									<td>${entity.remark}</td>
									
										<td class="op">
											<a href="apk/edit?code=${entity.code}">修改</a>
											<c:if test="${entity.status ==1  }">
											&nbsp;<a href="javascript:up('${entity.code }')"  onclick="return confirm('确定要 ${entity.code} 启用么?')">启用</a>
										</c:if>
										<c:if test="${entity.status ==0  }">
											&nbsp;<a href="javascript:down('${entity.code }')"  onclick="return confirm('确定要 ${entity.code} 暂停么?')">暂停</a>
										</c:if>
										
									
															
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