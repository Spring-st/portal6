<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td>${X_OBJECT.part.id }</td>
	<td>${X_OBJECT.part.name }</td>
	<td>${X_OBJECT.part.vend }</td>
	<td><fmt:formatNumber value="${X_OBJECT.part.highQty}" pattern="#"/></td>
	<td><fmt:formatNumber value="${X_OBJECT.part.lowQty}" pattern="#"/></td>
	<td><fmt:formatNumber value="${X_OBJECT.part.securityQty}" pattern="#"/></td>
	<%--<td>${X_OBJECT.location }</td>--%>
	<td 
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.currentQty) && (X_OBJECT.currentQty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.currentQty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${X_OBJECT.currentQty > X_OBJECT.part.highQty}"> bgcolor="orange"</c:if>>
		<fmt:formatNumber value="${X_OBJECT.currentQty}" pattern="#"/>
	</td>
	<td>${X_OBJECT.part.unit }</td>
</tr>

