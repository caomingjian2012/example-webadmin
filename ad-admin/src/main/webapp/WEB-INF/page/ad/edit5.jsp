<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${basePath }">
<title>修改广告</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>

<script type="text/javascript" language="javascript">
function iconUpload(){
	var uplist = $("#file");  
	var arrId = []; 
	for (var i=0; i< uplist.length; i++){  
	        if(uplist[i].value){  
	            arrId[i] = uplist[i].id;  
	        }  
	}
	$.ajaxFileUpload({  
		url:'${basePath }/upload/pic', //需要链接到服务器地址  
		secureuri:false,  
		fileElementId:arrId, //文件选择框的id属性  
		dataType: 'json',
		data:{},
		success: function (data)  //服务器成功响应处理函数
        {
			$("#img").val(data.url);
			$("#iconshow").attr("src", $("#img").val());
			$("#iconDiv").show();
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
            alert(e);
        }
	});  
}

</script>





</head>
<body>
<form action="" method="post" name="addForm">
		
			<table>
			<tr>
			<td>
			广告名称：
			</td>
			<td>
			<input type="text" id="adname" name="adname"  value="${entity.adname}"   />
			<input type="hidden" id="type" name="type"  value="${type}"   />
			
			 </td>
			</tr>
			
			<tr>
			<td>
			推广应用：
			</td>
			<td>
				<select id="appCd" name="appCd" disabled="disabled">
					<c:forEach items="${availPushApps }" var="item">
						<c:choose>
							<c:when test="${item.code eq  entity.appCd }">
								<option value="${item.code }" selected="selected" >${item.name }</option>
							</c:when>
							<c:otherwise>
								<option value="${item.code }" >${item.name }</option>
							</c:otherwise>
						</c:choose>
						
					</c:forEach>
					
				
				</select>
			 </td>
			</tr>
			<tr><td>
				推广时间：
			</td>
			<td>
			<input type="text" name="showTime" id="showTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value="${entity.showTime}" pattern="yyyy-MM-dd HH:mm:ss" />" class="input-medium"> 至
							<input type="text" name="endTime" id="endTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value="${entity.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />" class="input-medium">
			</td>
			</tr>
			<tr>
			<td>
			广告语：
			</td>
			<td>
			<input type="text" id="slogan" name="slogan"  value="${entity.slogan}"   />
			 </td>
			</tr>
			<tr>
			<td>
				推广APKNAME包
			</td>
			<td>
				<textarea id="channelArr"  name="channelArr" rows="20" cols="30">
				
					${entity.channelArr }
				</textarea>
			 </td>
			</tr>
			<tr>
			<td>
				投放人数上限
			</td>
			<td>
				<input type="text" id="responsemansMax" name="responsemansMax"  value="${entity.responsemansMax}"   />
			 </td>
			</tr>
			<tr>
			<td>
				展示人数上限
			</td>
			<td>
				<input type="text" id="showmansMax" name="showmansMax"  value="${entity.showmansMax}"   />
			 </td>
			</tr>
			<tr>
			<td>
				安装人数上限
			</td>
			<td>
				<input type="text" id="installmansMax" name="installmansMax"  value="${entity.installmansMax}"   />
			 </td>
			</tr>
			<tr>
			<td>
				激活人数上限
			</td>
			<td>
				<input type="text" id="activemansMax" name="activemansMax"  value="${entity.activemansMax}"   />
			 </td>
			</tr>
			<tr>
			<td>
			
			</td>
			<td>
			<input type="submit" value="修改"  />
			 </td>
			</tr>
				
		
		
			
			</table>
		</form>
		
		
		
</body>
</html>

