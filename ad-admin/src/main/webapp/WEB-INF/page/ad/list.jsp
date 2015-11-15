<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
<c:choose>
	<c:when test="${query.type==1 }">
		插屏
	</c:when>
	<c:when test="${query.type==2 }">
		PUSH
	</c:when>
	<c:when test="${query.type==3 }">
		BANNER
	</c:when>
	<c:when test="${query.type==4 }">
		ROOT内置
	</c:when>
	<c:when test="${query.type==5 }">
		静默
	</c:when>
	<c:otherwise>
		全部列表
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${query.state==1 }">
		上线
	</c:when>
	<c:when test="${query.state==0 }">
		下线
	</c:when>
	<c:otherwise>
		全部列表
	</c:otherwise>
</c:choose>
管理

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
function up(code){
	var rn = Math.round(Math.random()*50000);
	var result = $.ajax({
		type:"get",
		url:"${basePath}/ad/ajaxValid",
		async:false,
		dataType:"json",
		data:"rn="+rn+"&code="+code
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
function down(code){
	var rn = Math.round(Math.random()*50000);
	var result = $.ajax({
		type:"get",
		url:"${basePath}/ad/ajaxInvalid",
		async:false,
		dataType:"json",
		data:"rn="+rn+"&code="+code
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

<style type="text/css">
td {word-wrap:break-word;word-break:normal;}

</style>
	</head>
	<body>
			<div class="row-fluid">
				<div class="span12">
					<a href="ad?type=${query.type }&state=1"  class="btn btn-primary ">上线管理</a>
					<a href="ad?type=${query.type }&state=0"  class="btn btn-primary ">下线管理</a>
						
					
				</div>
			</div><br/>
			<div class="row-fluid">
				<div class="span12">
					
					<a href="ad/add?type=${query.type }"  class="btn btn-primary ">添加</a>
				</div>
			</div>
			<!-- 列表 -->	
			 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">
			<div class="row-fluid">
				<div class="span12">
				<input type="hidden" name="state"  value="${query.state }" >
				<input type="hidden" name="type"  value="${query.type }" >
				<input type="text" name="kw" id="kw" value="${query.kw }"  class="input-medium" style="width: 100px">
				 <input type="submit">	<br>		
							

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
					<table id="contentTable" style="TABLE-LAYOUT: fixed" class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
							
								 
								<th width="40px">CD</th>
								<th width="60px">名称(标题)</th>
								<th width="80px">广告语</th>
								<th  width="80px">icon</th>
								<th  width="80px">广告图片</th>
								<th  width="50px">下载地址</th>
								
								<th width="80px" >创建时间</th>
								<th width="80px">有效时间</th>
								<th  width="80px" >目标渠道</th>
								<th  width="80px" >目标国家</th>
								<th  width="80px" >上限</th>
								<th width="60px">操作</th>
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="entity" varStatus="vs">
									
									<tr>
										
										 
										<td>${entity.code }</td>
										<td>${entity.adname }</td>
										<td>${entity.slogan }</td>
										<td>
										  <img width="60px" alt="" src="${pushAppMap[entity.appCd].icon }">
										  	</td>
										  	<td>
										  <img width="60px" alt="" src="${entity.img }">
										  	</td>
										<td><a href="${pushAppMap[entity.appCd].url }">下载</a></td>
																			
										<td> <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd HH:mm:dd"/> </td>
										
									<td><fmt:formatDate value="${entity.showTime }" pattern="yyyy-MM-dd HH:mm:dd"/>至<fmt:formatDate value="${entity.endTime }" pattern="yyyy-MM-dd HH:mm:dd"/></td>
									<td>${entity.channels}</td>
									<td>${pushAppMap[entity.appCd].countrys}</td>
									<td>${entity.maxStr}</td>
									
										
										
										
										<td class="op">
											<a href="ad/edit?code=${entity.code}">修改</a>
											<c:if test="${entity.state ==0  }">
											&nbsp;<a href="javascript:up('${entity.code }')"  onclick="return confirm('确定要 ${entity.adname} 上架么?')">上架</a>
										</c:if>
										<c:if test="${entity.state ==1  }">
											&nbsp;<a href="javascript:down('${entity.code }')"  onclick="return confirm('确定要 ${entity.adname} 下架么?')">下架</a>
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