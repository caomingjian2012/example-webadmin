// JavaScript Document
var menuNum;

//===========主窗体链接跳转
function ChangeMainTo(url){
	window.parent.frames('mainFrame').window.location.href = url;
}

//页面菜单生成.
function getMenuButton(url,fstate,userid){
	var HttpRequest;
	var btList;
	var id = Math.round(Math.random()*50000);
	HttpRequest = $.ajax({
								type: "GET",
								url: "/IAPPROVAL/comm/getMenuButton.jsp",
								data: "url="+url+"&fstate="+fstate+"&userid="+userid+"&id="+id,
								success: function(){
									btList = HttpRequest.responseText.split("|$|");
									CreateButton(btList);
								},
								async: true
	});	
}

function CreateButton(btlist){
	
	var html = "<div id=\"BT_Menu\">"	
	for(var i=0; i<btlist.length-1;i+=2){
		
    	html+= "<div class=\"Menu_Bt3\"><input type=\"button\" id=\"\" value=\""+$.trim(btlist[i])+"\" onclick = \""+$.trim(btlist[i+1])+"\" /></div>";
    	
	}
	html +="</div>";
	$("body").append(html);
	
}

function MenuClick(num){
	for(var i=1;i<=menuNum;i++){
		if(i==num){
			$("#menu"+i).removeClass("Menu_Bt2");
			$("#menu"+i).addClass("Menu_Bt1");
		}else{
			$("#menu"+i).removeClass("Menu_Bt1");
			$("#menu"+i).addClass("Menu_Bt2");
		}
	}
}

function getUserMenuTips(userid){
	var HttpRequest;
	var tipsList;
	var id = Math.round(Math.random()*50000);
	HttpRequest = $.ajax({
								type: "GET",
								url: "getUserMenuTips.jsp",
								data: "userid="+userid+"&id="+id,
								success: function(){
									tipsList = HttpRequest.responseText.split("|$|");
									showUserMenuTips(tipsList);
								},
								async: true
	});	
}

function showUserMenuTips(data){//表单状态提示信息输出
	for(var i=0;i<data.length-1;i+=4){
		if($.trim(data[i+1])>0){
			$("#"+$.trim(data[i])+"_tips").html("(<font color='red'>待处理:"+$.trim(data[i+1])+"</font> 草稿:"+$.trim(data[i+2])+" 已申请:"+$.trim(data[i+3])+")");
		}else{
			$("#"+$.trim(data[i])+"_tips").html("(待处理:"+$.trim(data[i+1])+" 草稿:"+$.trim(data[i+2])+" 已申请:"+$.trim(data[i+3])+")");
		}
	}	
}

function getUserMenuApp(userid,ftypeid){//获取待处理数据
	var HttpRequest;
	var menuList;
	var id = Math.round(Math.random()*50000);
	HttpRequest = $.ajax({
								type: "GET",
								url: "getUserMenuInfo.jsp",
								data: "userid="+userid+"&ftypeid="+ftypeid+"&type=1&id="+id,
								success: function(){
									menuList = HttpRequest.responseText.split("|$|");
									showUserMenuApp(menuList,ftypeid,"app");
								},
								async: true
	});	
}

function getUserMenuDraf(userid,ftypeid){//获取草稿数据
	var HttpRequest;
	var menuList;
	var id = Math.round(Math.random()*50000);
	HttpRequest = $.ajax({
								type: "GET",
								url: "getUserMenuInfo.jsp",
								data: "userid="+userid+"&ftypeid="+ftypeid+"&type=3&id="+id,
								success: function(){
									menuList = HttpRequest.responseText.split("|$|");
									showUserMenuApp(menuList,ftypeid,"draf");
								},
								async: true
	});	
}

function getUserMenuMydoc(userid,ftypeid){//获取已完成数据
	var HttpRequest;
	var menuList;
	var id = Math.round(Math.random()*50000);
	HttpRequest = $.ajax({
								type: "GET",
								url: "getUserMenuInfo.jsp",
								data: "userid="+userid+"&ftypeid="+ftypeid+"&type=2&id="+id,
								success: function(){
									menuList = HttpRequest.responseText.split("|$|");
									showUserMenuApp(menuList,ftypeid,"mydoc");
								},
								async: true
	});	
}

function showUserMenuApp(data,ftypeid,type){//输出数据方法
	var html = "";
	$("#"+ftypeid+"_"+type+"").html("");
	if($.trim(data[0])==""){
		html += "<div class=\"MenuInfoItem_None\">";
		html += "<div style=\"width: 200px;\"> 无该项目记录</div>";
		html += "<div style=\"width: 100px;\"></div>";
		html += "<div style=\"width: 80px;\"></div>";
		html += "<div style=\"width: 140px;\"></div>";
		html += "<div style=\"width: 80px;\"></div>";
		html += "</div>";
	}else{
		for(var i=0;i<data.length-1;i+=7){
			html += "<div class=\"MenuInfoItem\" onclick=\"ChangeMainTo('doForm.jsp?fcid="+$.trim(data[i])+"')\">";
			html += "<div style=\"width: 200px;\">提交人:"+$.trim(data[i+2])+"</div>";
			html += "<div style=\"width: 100px;\">"+$.trim(data[i+3])+"</div>";
			html += "<div style=\"width: 80px;\">"+$.trim(data[i+4])+"</div>";
			html += "<div style=\"width: 140px;\">"+$.trim(data[i+5])+"</div>";
			html += "<div style=\"width: 80px;\">"+$.trim(data[i+6])+"</div>";
			html += "</div>";
		}
	}
	$("#"+ftypeid+"_"+type+"").html(html);
}

function getUserMenuInfo(userid,ftypeid){//获取并填充个人用户表单列表数据
	getUserMenuApp(userid,ftypeid);
	getUserMenuDraf(userid,ftypeid);
	getUserMenuMydoc(userid,ftypeid);
}

//表单查询方法
function getDeptMenu(userid,ftypeid){
	var HttpRequest;
	var menuList;
	var id = Math.round(Math.random()*50000);
	HttpRequest = $.ajax({
								type: "GET",
								url: "getDeptMenu.jsp",
								data: "userid="+userid+"&ftypeid="+ftypeid+"&id="+id,
								success: function(){
									menuList = HttpRequest.responseText.split("|$|");
									showDeptMenu(menuList,ftypeid);
								},
								async: true
	});	
}

function showDeptMenu(data,ftypeid){
	var html = "";
	if($.trim(data[0])==""){
		html += "<div class=\"MenuInfoTitle\" style=\"border: none;\">";
		html += "<div style=\"width: 200px;\"> 无该项目记录</div>";
		html += "</div>";
	}else{
		for(var i=0;i<data.length-1;i+=3){
			html += "<div class=\"MenuInfoTitle\" style=\"border: none;\">";
			html += "<div id=\""+$.trim(data[i])+"_dept_arrow\" style=\"margin-top:4px;\" class=\"rightArrow\" onclick=\"showDeptFormMenu("+$.trim(data[i])+","+ftypeid+")\"></div>";
			html += "<div style=\"width: 200px;\"><a href=\"javascript:void(0);\" onclick = \"showDeptFormMenu("+$.trim(data[i])+","+ftypeid+")\"<font style=\"color: #2B2F42;font-weight:bold;\">"+$.trim(data[i+1])+"</font></a>&nbsp;("+$.trim(data[i+2])+")</div>";
			html += "</div>";
			html += "<div id=\""+$.trim(data[i])+"_dept_info\" class=\"MenuInfo\"></div>";
			
		}
	}
	//$("#testTxt").val(html);
	$("#"+ftypeid+"_info").html(html);
}

function getDeptMenuTips(userid){
	var HttpRequest;
	var tipsList;
	var id = Math.round(Math.random()*50000);
	HttpRequest = $.ajax({
								type: "GET",
								url: "getDeptMenuTips.jsp",
								data: "userid="+userid+"&id="+id,
								success: function(){
									tipsList = HttpRequest.responseText.split("|$|");
									showDeptMenuTips(tipsList);
								},
								async: true
	});	
}

function showDeptMenuTips(data){//表单状态提示信息输出
	for(var i=0;i<data.length-1;i+=2){
		$("#"+$.trim(data[i])+"_tips").html("("+$.trim(data[i+1])+")");
	}	
}

function getFormMenu(ftypeid,deptid){//获取form数据
	var HttpRequest;
	var menuList;
	var id = Math.round(Math.random()*50000);
	HttpRequest = $.ajax({
								type: "GET",
								url: "getFormMenuInfo.jsp",
								data: "ftypeid="+ftypeid+"&deptid="+deptid+"&id="+id,
								success: function(){
									menuList = HttpRequest.responseText.split("|$|");
									showFormMenu(menuList,ftypeid,deptid);
								},
								async: true
	});	
}

function showFormMenu(data,ftypeid,deptid){//输出数据方法
	var html = "";
	$("#"+deptid+"_"+ftypeid+"_form_info").html("");
	if($.trim(data[0])==""){
		html += "<div class=\"MenuInfoItem_None\">";
		html += "<div style=\"width: 200px;\"> 无该项目记录</div>";
		html += "<div style=\"width: 100px;\"></div>";
		html += "<div style=\"width: 80px;\"></div>";
		html += "<div style=\"width: 140px;\"></div>";
		html += "<div style=\"width: 80px;\"></div>";
		html += "</div>";
	}else{
		for(var i=0;i<data.length-1;i+=7){
			html += "<div class=\"MenuInfoItem\" onclick=\"ChangeMainTo('doForm.jsp?fcid="+$.trim(data[i])+"')\">";
			html += "<div style=\"width: 200px;\">提交人:"+$.trim(data[i+2])+"</div>";
			html += "<div style=\"width: 100px;\">"+$.trim(data[i+3])+"</div>";
			html += "<div style=\"width: 80px;\">"+$.trim(data[i+4])+"</div>";
			html += "<div style=\"width: 140px;\">"+$.trim(data[i+5])+"</div>";
			html += "<div style=\"width: 80px;\">"+$.trim(data[i+6])+"</div>";
			html += "</div>";
		}
	}
	$("#"+deptid+"_"+ftypeid+"_form_info").html(html);
}

function doClose(){
	var id = Math.round(Math.random()*50000);
	var HttpRequest;
	var fcid = $("#fcid").val();
	HttpRequest = $.ajax({
						type: "GET",
						url: "../form/doClose.jsp",
						data:"fcid="+fcid+"&id="+id,
						success: function(){
							res = HttpRequest.responseText;
							//alert(res.length);
							if($.trim(res)=="OK"){
								alert("申请已关闭.");
								ChangeMainTo("formList.jsp");
							}else{
								alert("操作出现错误,请联系IT.");
								ChangeMainTo("formList.jsp");
							}
						},
						async: true
					}); 
}

//---------------------------------------------------------------------------------
function createNewForm(){
	if($("#ftypeid").val()=="undefined"||$("#ftypeid").val()=="0"){
		alert("请从上面列表中选择需要新建的申请表单."+$("#ftypeid").val());
	}else{
		var url = "doForm.jsp?formType="+$("#ftypeid").val();
		//window.location.href = url;
		ChangeMainTo(url);
	}
}	

function showApproveForm(type){
	if($("#approveForm").css("display")=='none'){
		$("#approveForm").css("display","block");
		$("#temp_type").val(type);
	}else{
		$("#approveForm").css("display","none");
		$("#temp_type").val("");
	}
}