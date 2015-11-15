<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线用户</title>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		
		
	
	});

</script>
<script type="text/javascript">
function blacklist(id){
	var rn = Math.round(Math.random()*50000);
	var result = $.ajax({
		type:"post",
		url:"${ctx}/blackList/ajaxadd",
		async:false,
		dataType:"json",
		data:"rn="+rn+"&uuid="+id
	});
	var data=result.responseText;
	if(data=="success"){
		alert("添加成功");
		//window.location.reload(true);
	}else{
		alert(data);
	}
}
</script>


	</head>
	<body>
			<div class="row-fluid">
				<div class="span12">
				
						
					
				</div>
			</div>
			<!-- 列表 -->	
			 <form class="form-inline" id="queryForm" name="queryForm" action="" method="get">
			<div class="row-fluid">
				<div class="span12">
				
					<input type="text" id="kw" name ="kw" value="${kw }">
							<input type="submit">
							<a href="online/an">分析</a>

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
				<table id="contentTable"  class="gridBody table table-striped table-bordered table-condensed ">
						<tbody>
							
							
								<c:forEach items="${userSet }" var="user" varStatus="vs" >
									
									<tr>
										
										<td>${user }</td>
										<td>
											
											&nbsp;<a href="javascript:blacklist('<c:forTokens var="uuid" items="${user}" delims="@" begin="1" step="3">${uuid }</c:forTokens>')"  >加入黑名单</a>
										
										
										</td>
										
									</tr>				
								</c:forEach>
							
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