<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户端列表</title>
<script type="text/javascript" src="<c:url value="/js/application.js"/>"></script>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${query.sortColumns}','page');
		
	
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

<script type="text/javascript">
function batchBlacklist(){
	
	var uuids ='';
	var elms = document.getElementsByName("items");
	
	for(var i = 0; i < elms.length; i++) {
	
		if(elms[i].checked){
			
			uuids=uuids+","+elms[i].value;
		}
	}
	   $.colorbox({
		   width:"50%", height:"70%",opacity:0.95, scrolling:false,open:true, href:'${ctx}/blackList/batadd?uuids='+uuids
		 });    
	
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
				
				&nbsp;关键字：<input type="text" name="kw" id="kw" value="${query.kw }"  class="input-medium" style="width: 100px">
				 <input type="submit">	
				 
			
				 
				 <input type="button" onclick=" batchBlacklist();" value="批量加入黑名单">
				
				 
				 <br>	
							

				
				</div>
			 
			</div>	
			<div class="row-fluid">
				<div class="span12">
				<table id="contentTable"  class="gridBody table table-striped table-bordered table-condensed ">
						<tbody>
							<tr>
								<td><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></td>
								
								<th>用户代码</th>
								<th>apkname</th>
								<th>设备代码</th>
								
								<th>手机型号</th>
								
								<th>网络</th>
								<th>运营商</th>
								<th>地区</th>
								<th>国家</th>
								<th sortColumn="createTime">创建时间</th>
								<th>操作</th>
								
								
							</tr>
							<c:if test="${!empty page.result }">
								<c:forEach items="${page.result }" var="entity" varStatus="vs">
									
									<tr>
										<td><input type="checkbox" name="items" value="${entity.mobile.uuid }" ></td>
										<td>${entity.clientId }</td>
										<td>${entity.apkName }</td>
										<td>${entity.mobile.uuid }</td>
										
										<td>${entity.mobile.model }</td>
										<td>${entity.network }</td>
										
										<td>${entity.mobile.carrier }</td>
										<td>${entity.mobile.region }</td>
										<td>${entity.mobile.country }</td>
										<td>${entity.createTime }</td>
										<td>
										
										&nbsp;<a href="javascript:blacklist('${entity.mobile.uuid }')"  >加入黑名单</a>
										
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