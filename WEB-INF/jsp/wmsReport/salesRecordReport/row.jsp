<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td>${X_OBJECT.shipId.code}</td>
	<td>${X_OBJECT.shipItemId.customerPlanId.customer.name1}</td>
	<td>${X_OBJECT.shipItemId.customerPlanId.operation}</td>
	<td>${X_OBJECT.lotSer.id}</td>
	<td>${X_OBJECT.part.id}</td>
	<td>${X_OBJECT.part.oldCode}</td>
	<td><fmt:formatNumber value="${X_OBJECT.count}" minFractionDigits="2" maxFractionDigits="2"/></td>
	<td><fmt:formatNumber value="${X_OBJECT.shipItemId.customerPlanId.unitPrice}" minFractionDigits="2" maxFractionDigits="2"/>
	</td>
	<td>${X_OBJECT.shipItemId.customerPlanId.sotaxc}</td>
	<td>${X_OBJECT.shipItemId.customerPlanId.curr}</td>
</tr>

