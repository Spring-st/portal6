<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<tr id="r${X_OBJECT.id}" align="center">
	<td>${X_OBJECT.customerreturns.returnNumber}</td>
	<td>${X_OBJECT.batchNumber}</td>
	<td>${X_OBJECT.part.id}</td>
	<td>${X_OBJECT.part.describe1}</td>
	<td>${X_OBJECT.part.oldCode}</td>
	<td>${X_OBJECT.returnStorage}</td>
	<td><fmt:formatNumber value="${X_OBJECT.qty}" maxFractionDigits="0"/></td>
	<td>${X_OBJECT.salesDeliveryDate}</td>
</tr>

