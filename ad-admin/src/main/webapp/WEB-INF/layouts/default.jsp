<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ include file="/WEB-INF/page/taglibs.jsp"%>


<!DOCTYPE html>
<html>
	<head>
		<base href="${basePath}">
		<title><sitemesh:title/></title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />	
		<link type="image/x-icon" href="${ctx}/assets/images/favicon.ico" rel="shortcut icon">
		<link href="${ctx}/assets/plugins/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
		
		<link href="${ctx}/assets/css/default.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/assets/css/fix.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/colorbox.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/js/jquery-1.10.1.js" type="text/javascript"></script>
		<script src="${ctx}/js/jquery.colorbox.js" type="text/javascript"></script>

		<script src="${ctx}/assets/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		
		
	
		<sitemesh:head/>
		<style type="text/css">
		.error,.success,.tips {
	font-weight: bold;
	background-repeat: no-repeat;
	padding: 0px 40px;
	margin: 10px;
	margin: 10px\9 10px\9 0px\9;
	border: 1px solid black;
	background-color: #ffffcc;
	color: #000000;
}

.success table td span.picSUCCESS {
	background: url("${ctx}/images/tips/success.png") -4px -4px
		no-repeat;
	width: 40px;
	height: 40px;
	display: block;
}

.error table td span.picERROR {
	background: url("${ctx}/images/tips/error.png") -4px -4px no-repeat;
	width: 40px;
	height: 40px;
	display: block;
}

.pad {
	padding-left: 5px;
	font-size: 13px;
}
		
		
		</style>
	</head>

	
	<body class="page-header-fixed">
	
		<!-- 
		page-header
		-->
		
		
		<div class="clearfix"></div>
		<div class="page-linebar"></div>
		<div class="page-container">
			
			<div class="page-content">
				<div class="row-fluid">
				<div class="span12" style="margin-left: 10px ;color: red;">
					<sitemesh:title/>
						
					
					</div>
				</div>
				<%@ include file="/WEB-INF/layouts/message.jsp"%>
				<sitemesh:body/>
			</div>
		</div>
		
		<div class="footer">
			<div class="footer-inner">
				<%@ include file="/WEB-INF/layouts/footer.jsp"%>
			</div>		
		</div>
	
	
</body>
</html>