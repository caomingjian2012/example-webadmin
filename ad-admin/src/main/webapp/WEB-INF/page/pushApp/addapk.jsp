<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${basePath }">
<title>添加推广APK</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" language="javascript">
function apkUpload(){
	var uplist = $("input[name^=apkFile]");
	var str = uplist[0].value;
	if(str.substr(str.lastIndexOf(".")+1) != "apk"){
		alert("请上传正确的应用格式！");
		return ;
	}
	var arrId = []; 
	for (var i=0; i< uplist.length; i++){  
		if(uplist[i].value){  
            arrId[i] = uplist[i].id;  
        }  
	}
	$.ajaxFileUpload({  
		url:'${basePath }/upload/apk', //需要链接到服务器地址  
		secureuri:false,  
		fileElementId:arrId, //文件选择框的id属性  
		dataType: 'json',
		data:{},
		success: function (data)  //服务器成功响应处理函数
        {
			
			$("#size").val(data.size);
			$("#url").val(data.url);			
			$("#url").attr("href",data.url);		
			$("#version").val(data.versionName);
			$("#packageName").val(data.packageName);
			
			
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
            alert(e);
        }
	});  
}
</script>
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
		url:'${basePath }/upload/icon', //需要链接到服务器地址  
		secureuri:false,  
		fileElementId:arrId, //文件选择框的id属性  
		dataType: 'json',
		data:{},
		success: function (data)  //服务器成功响应处理函数
        {
			$("#icon").val(data.url);
			$("#iconshow").attr("src", $("#icon").val());
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
			应用名称：
			</td>
			<td>
			<input type="text" id="name" name="name"  value="${entity.name}"   />
			 </td>
			</tr>
			<tr>
			<td>
			<br>
			应用ICON：
			
			</td>
			<td><input type="file" id="file" name="file"/>
				<input type="button" value="上传图片" onclick="iconUpload()"/><br>
				<input type="text" name="icon" id="icon" value="${entity.icon }" style="width: 400px !important" readonly="readonly"/>
					
			 </td>
			</tr>
		<tr>
			<td>
			
			</td>
			<td>
			<div id="iconDiv" ><img id="iconshow" name="iconshow" src="${entity.icon }"/></div>
			 </td>
			</tr>
		
			
			
		
			<tr>
			<td>
			APK安装包：
			</td>
			<td>
				<input type="file" id="apkFile" name="apkFile"/><input type="button" value="上传文件" onclick="apkUpload()"/>
			 </td>
			</tr>
			<tr>
			<td>
			包名：
			</td>
			<td>
		<input type="text" id="packageName" name="packageName"  value="${entity.packageName}"  readonly="readonly" />
			 </td>
			</tr>
			
			<tr>
			<td>
			VERSIONNAME：
			</td>
			<td>
		<input type="text" id="version" name="version"  value="${entity.version}" readonly="readonly" />
			 </td>
			</tr>
		
			
			
			<tr>
			<td>
			文件大小：
			</td>
			<td>
		<input type="text" id="size" name="size"  value="${entity.size}" readonly="readonly" />
			 </td>
			</tr>
			<tr>
			</tr>
			<tr>
			<td>
			下载路径 ：
			</td>
			<td>
		<input type="text" id="url" name="url"  value="${entity.url}"  readonly="readonly" /><a id="url" href="${entity.url}">下载</a>
			 </td>
			</tr>
			<tr>
			<td>
				推广国家
			</td>
			<td>
				<textarea id="countryArr"  name="countryArr" rows="20" cols="30">
				
					${entity.countryArr }
				</textarea>
			 </td>
			</tr>
			
			<tr>
			<td>
			
			</td>
			<td>
			<input type="submit"  />
			 </td>
			</tr>
				
		
		
			
			</table>
		</form>
</body>
</html>

