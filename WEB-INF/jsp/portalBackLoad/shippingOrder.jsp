<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shippingOrder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	a{
	text-decoration: none;
	}	
</style>
<script type="text/javascript">

function view(id) {
	var url = "viewItemByShipOrderId.do?id=" + id;
	window.location.href = url;
}

function withdraw(id){
	var url = "withdrawByShipOrderId.do?id=" + id;
	window.location.href = url;
}
</script>
  </head>
  
  <body>
   <table width="100%">
					<tr>
						<td>
							<div>
								<html:form action="listShippingOrder">
									<%--<table style="width: 100%">
										<tr>
											<td>
												Purchase - Overview
											</td>
										</tr>
									</table>
									--%><table style="width: 100%;" border="0" align="left">
										<tr>
											<td class="bluetext">
												<bean:message key="start.time"/>:
											</td>
											<td>
												<html:text property="createDate1" size="15" /> 
												<a onclick="event.cancelBubble=true;"
												href="javascript:showCalendar('dimg1',false,'createDate1',null,null,'portalShipOrderMainQueryForm')"><img
												align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
											</td>
											<td class="bluetext">
												<bean:message key="end.time"/>:
											</td> 
											<td>
												<html:text property="createDate2" size="15" /> 
												<a onclick="event.cancelBubble=true;"
												href="javascript:showCalendar('dimg2',false,'createDate2',null,null,'portalShipOrderMainQueryForm')"><img
												align="absmiddle" border="0" id="dimg2" src="images/datebtn.gif" /></a>
											</td>
										</tr>
										<tr>
											<td class="bluetext">
												<bean:message key="PO.No"/>:
											</td>
											<td>
												<html:text property="code" size="15" />
											</td>
											<td class="bluetext">
												<bean:message key="IsEnabled"/>:
											</td>
											<td>
												<html:select property="enabled">
												<html:option value=""><bean:message key="all.all"/></html:option>
												<c:if test="${sessionScope.LOGIN_USER.locale=='en'}">
													<html:options collection="X_ENABLEDDISABLEDLIST"
														property="enumCode" labelProperty="engShortDescription" />
												</c:if>
												<c:if test="${sessionScope.LOGIN_USER.locale!='en'}">
													<html:options collection="X_ENABLEDDISABLEDLIST"
														property="enumCode" labelProperty="chnShortDescription" />
												</c:if>
												</html:select>
											</td>
										</tr>
										<tr>
											<td colspan="2"></td>
											<td align="center">
												<input type="button" value="<bean:message key="all.query"/>" />
											</td>
											<td align="left">
<%--												<input type="button" value="Excel" />--%>
											</td>
										</tr>
									</table>
								</html:form>
							</div>
							</td>
							</tr>
							<tr><td>
							<!-- start blog -->
							<div>
								<page:form action="listShippingOrder.do" method="post">
									<table width="100%" class="data">
										<c:choose>
											<c:when test="${x_portalShipOrder == null}">
												<h3>
													<bean:message key="there.is.no.invoice.information"/>
												</h3>
											</c:when>
											<c:otherwise>
												<thead>
													<tr bgcolor="#9999ff">
														<th width="5%">#</th>
														<th width="5%">
															<input type="checkbox" />
														</th>
														<th width="20%">
															<bean:message key="PO.No"/>
														</th>
														<%--<th width="8%">
															Pending PO No
														</th>
														
														--%><th width="20%">
															<bean:message key="order.date"/>
														</th>
														<th width="20%">
															<bean:message key="IsEnabled"/>
														</th>
														<th width="20%">
															<bean:message key="Remark"/> 
														</th>
														<th width="10%">
															<bean:message key="all.operate"/> 
														</th>
													</tr>
												</thead>
													<tr>
														<td width="5%" height="27px;">
														</td>
														<td width="5%">
																<input type="checkbox" />
														</td>
														<%--<td width="6%">
																${str.id}
														</td>
														--%><td width="20%">
															<a href='javascript:view("${x_portalShipOrder.id}")'>${x_portalShipOrder.code}</a>
														</td>
														<td width="20%">
															<c:if test="${x_portalShipOrder.createDate!= null }">
																<bean:write name="x_portalShipOrder" property="createDate"
																	format="yyyy/MM/dd" />
															</c:if>
														</td>
														<td width="20%">
															${x_portalShipOrder.enabled.chnShortDescription}
														</td>
														<td width="20%">
															${x_portalShipOrder.remark}
														</td>
														<td width="10%">
															<a href='javascript:withdraw("${x_portalShipOrder.id}")'>撤消</a>
														</td>
														</tr>
											</c:otherwise>
										</c:choose>
									</table>
									
								</page:form>

							</div>
							
						</td>
					</tr>
				</table>
  </body>
</html>
