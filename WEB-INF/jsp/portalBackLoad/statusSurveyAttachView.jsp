<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
function download(id){
	window.location.href="downloadAffirmPortalStatusSurveyAttachSupplier.do?id="+id;
}
//-->
</script>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'xmlAttachView.jsp' starting page</title>
    
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
  </head>
  
  <body>
  <table width=100% border=0 cellpadding=4 cellspacing=0>
  <tr><td style="color: green">${requestScope.msg}</td></tr>
		<tr>
		
			<td  class="bluetext" width="20%">
					<bean:message key="supplier.code" />
				</td>
				<td  width="30%">
					${X_SUPPLIER.code}
				</td>
				<td class="bluetext" width="20%">
					<bean:message key="supplier.name" />
				</td>
				<td width="30%">
					${X_SUPPLIER.name}
				</td></tr><tr>
				<td class="bluetext" width="20%">
					<bean:message key="supplier.date" />
				</td>
				<td  width="30%">
					<c:if test="${X_OBJECT.uploadDate != null }">
						<bean:write name="X_OBJECT" property="uploadDate" format="yyyy/MM/dd" />
						</c:if>
				</td>
				<td class="bluetext" width="20%">
					<bean:message key="supplier.mark" />
				</td>
				<td width="30%">
				${X_OBJECT.mark  }

				</td>
				
				 
		</tr>
	</table>
    <table width="100%">
    				<tr><td style="color: green">${requestScope.msg}</td></tr>
					<tr>
						<td class="bluetext" width="20%">
						<bean:message key="psoa.fileName" />:
						</td>
						<td width="30%">
						${X_OBJECT.fileName}
						</td>
						<td class="bluetext" width="20%">
						<bean:message key="psoa.uploadDate" />:
						</td>
						<td width="30%">
						<bean:write name="X_OBJECT" property="uploadDate" format="yyyy/MM/dd" />
						</td>
					</tr>
					<tr>
						<td class="bluetext">
						<bean:message key="psoa.creater" />:
						</td>
						<td>${X_OBJECT.createUser.name}
						</td>
						
						<td class="bluetext">
						<bean:message key="psoa.fileSize" />:
						</td>
						<td>${X_OBJECT.fileSize}
						</td>
					</tr>
					<tr>
						<td class="bluetext">
						<bean:message key="status" />:
						</td>
						<td>
							<c:if test="${X_OBJECT.status!=null}">
							<span style="color:${X_OBJECT.status.color}">
							<bean:write name="X_OBJECT" property="status.${x_lang}ShortDescription" /> </span>
							</c:if>
						</td>
						<td class="bluetext">
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td colspan="4">
						<hr/>
						</td>
					</tr>
					<tr>
						
						<td align="left">
						<c:if test="${affirm}">
						<input type="button" value="下载" onclick="download('${X_OBJECT.id}')"/>
						
						</c:if>
							<c:if test="${!affirm}">
						<input type="button" value="下载" onclick="download('${X_OBJECT.id}')"/>
						
						</c:if>
							<input type="button" value='<bean:message key="return"/>' onclick="history.go(-1)"/>
						</td>
						<td align="left"></td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td colspan="4">
							<!-- start blog -->
							<div>
									<table width="100%">
										<c:choose>
											<c:when test="${X_RESULTLIST == null}">
												<h3>
													<bean:message key="there.is.no.invoice.information" />
												</h3>
											</c:when>
											<c:otherwise>
												<thead>
													<tr>
														<th></th>
														<c:forEach items="${X_tablerow}" var="row">
															<th>${row}</th>
														</c:forEach>
													</tr>
												</thead>
												<c:forEach items="${X_RESULTLIST}" var="str" varStatus="s">
													<tr <c:if test="${s.count%2==0}">class="a1"</c:if>>
														<td width="5%" height="27px;" align="center">
															${s.count}
														</td>
														
														<c:forEach items="${X_tablerow}" var="row">
															<td><bean:write name="str" property="${row}"/></td>
														</c:forEach>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</table>
							</div>

						</td>
					</tr>
				</table>
  </body>
</html>
