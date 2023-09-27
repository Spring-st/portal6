<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td>${X_OBJECT.spsoId.code}</td>
	<td>${X_OBJECT.customerPlanId.planNumbers}</td>
	<td>${X_OBJECT.customerPlanId.customer.code}</td>
	<%--<td>${X_OBJECT.customerPlanId.line}</td>
	--%><td>${X_OBJECT.spsoId.shPrintDate}</td>
	<td>${X_OBJECT.customerPlanId.wmspart.id}</td>
	<td>${X_OBJECT.customerPlanId.wmspart.describe1}</td>
	<td><fmt:formatNumber value="${X_OBJECT.shipQty}" maxFractionDigits="0" minFractionDigits="0"/></td>
	<td><fmt:formatNumber value="${X_OBJECT.customerPlanId.unitPrice}" maxFractionDigits="2" minFractionDigits="2"/></td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.customerPlanId.unitPrice * X_OBJECT.shipQty}" maxFractionDigits="2" pattern="#0.00#" minFractionDigits="2" var="price"/>
		${price}
	</td>
	<td> 
		<c:if test="${X_OBJECT.spsoId.matchStatus=='0'}">
			<span style="color: red;">已匹配</span>
		</c:if>
		<c:if test="${X_OBJECT.spsoId.matchStatus!='0'}">
			未匹配
		</c:if>
	</td>
</tr>

