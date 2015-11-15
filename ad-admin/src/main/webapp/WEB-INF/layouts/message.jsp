
<%@ page language="java" pageEncoding="UTF-8" %>

<div style = "text-align: center;">

<c:if  test="${success_message != null && success_message != ''}">
						<div class="success">
							<table cellpadding="0" cellspacing="0">
								<tr>
									<td class="RL"><span class="picSUCCESS"></span></td>
									<td class="TL pad">${success_message}</td>
								</tr>
							</table>
						</div>
					</c:if>
					
 <c:if test="${error_message != null && error_message != ''}">
						<div class="error">
							<table cellpadding="0" cellspacing="0">
								<tr>
									<td class="RL"><span class="picERROR"></span></td>
									<td class="TL pad">${error_message}</td>
								</tr>
							</table>
						</div>
					</c:if>
					
 <c:if test="${tips_message != null && tips_message != ''}">
						<div class="tips">
							${tips_message}<br />
						</div>
					</c:if> 
					
<!-- 	<div align="right" style="margin-right: 20px;"><a href="#" onclick="history.back(-1)">返回</a>&nbsp;&nbsp;<a href="#" onclick="window.location.reload()">刷新</a></div> -->
</div>

<%
 	request.getSession().removeAttribute("success_message");
 	request.getSession().removeAttribute("error_message");
 	request.getSession().removeAttribute("tips_message");
%>
