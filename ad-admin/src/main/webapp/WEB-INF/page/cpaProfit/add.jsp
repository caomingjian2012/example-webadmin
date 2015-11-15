<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${basePath }">
<title>添加CPA推广渠道收益</title>
<script type="text/javascript" src="js/jquery.js"></script>





<script type="text/javascript" language="javascript">
function getChannelInfo(){

	$("#price").val("");
	$("#marketProfit").val("");
	$("#statis").html("");
	var calendar =$("#calendar").val();
	var apk =$("#apk").val();	
	if(apk==''){
		return ;
	};
	
	$.post("${basePath}apk/ajaxGetapkInfo?apkCode=" +apk+"&calendar="+calendar ,function(data){
	
		if(data != null){
			
			if("error" == data){
				alert("找不到APK："+apk);
				return ;
			}
			var map = JSON.parse(data);;
			var apk = map.apk;
			var statis = map.statis;
			
			$("#price").val(apk.unitPrice/100);
			
			$("#statis").html("新增："+statis.increaments);
			
			
		}
	});

}
</script>
		
	<script type="text/javascript" language="javascript">
function calProfit(){
	var price = $("#price").val();
	var actives =$("#actives").val();

	var marketProfit = price * actives ;
	marketProfit =marketProfit.toFixed(2);
	$("#marketProfit").val(marketProfit);


}
</script>	

<style type="text/css">
td {word-wrap:break-word;word-break:normal;}
.label1 {text-align: right;}
.input1 {text-align: right;}
</style>
</head>
<body >

		

		<form action="" method="post" name="addForm">
			
			
			<table>
			
			<tr>
			<td class="label1">
			日期：
			</td>
			<td>
			 
			<input type="text" name="calendar" id="calendar" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${entity.calendar}" pattern="yyyy-MM-dd"/>" class="input-medium" onchange="getChannelInfo()">  收益日期，默认是昨天
			 
			 </td>
			</tr>
			<tr>
			<td class="label1">
			  APK包：
			</td>
			<td>
				
					 			
			<input type="text" id="apk" name="apk"  value="${entity.apk}"  onchange="getChannelInfo()" />
			 *		
			<span id="statis">推广渠道信息</span>
			
			 </td>
			</tr>
			
			
			<tr>
			<td class="label1">
			激活人数：
			</td>
			<td>
			<input type="text" id="actives" name="actives" value="${entity.actives }"  /> * 
			 </td>
			</tr>
			<tr>
			<td class="label1">
			单价：
			</td>
			<td>
			<input type="text" id="price" name="priceByYuan" value="${entity.priceByYuan }"  /> * 金额 单位元
			 </td>
			</tr>
			<tr>
			<td class="label1">
			收益：
			</td>
			<td>
			<input type="text" id="marketProfit" name="marketProfitByYuan" value="${entity.marketProfitByYuan }"  /> * 金额单位元
			 </td>
			</tr>
			<tr>
			<td>
			<input type="button" value="计算" onclick="calProfit()" />
			</td>
			<td>
			  <input type="submit"  />
			 </td>
			</tr>
				
		
		
			
			</table>
		</form>
</body>
</html>

