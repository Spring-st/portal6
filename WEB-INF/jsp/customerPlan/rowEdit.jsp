<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
    
	<td>${X_OBJECT.planNumbers}</td>
	<td>${X_OBJECT.line}</td>
	<td>${X_OBJECT.customer.code}</td>
	<td>${X_OBJECT.customerAddress}</td>
	<td>${X_OBJECT.wmspart.id}</td>
	<td>${X_OBJECT.wmspart.dpiNo}</td>
	<td>${X_OBJECT.wmspart.oldCode}</td>
	<td>${X_OBJECT.wmspart.describe1}</td>
	<td>${X_OBJECT.um}</td>
	<fmt:formatDate value="${X_OBJECT.shipmentDate}" var="shipmentDate" pattern="yyyy-MM-dd" />
	<td>${shipmentDate}</td>
	<td><fmt:formatNumber value="${X_OBJECT.qty}" type="number"/></td>
	<td><fmt:formatNumber value="${X_OBJECT.receiptQty}" type="number"></fmt:formatNumber></td>
	<td><fmt:formatNumber value="${X_OBJECT.qtyOpen}" type="number"></fmt:formatNumber></td>
	<td><fmt:formatNumber value="${X_OBJECT.returnQtySum}" type="number"></fmt:formatNumber></td>
	<fmt:formatDate value="${X_OBJECT.entryDate}" var="date" pattern="yyyy-MM-dd HH:mm" />
	<td>${date}</td>
	<td>
		<c:if test="${X_OBJECT.status==1}">关闭</c:if>
		<c:if test="${X_OBJECT.status!=1}">打开</c:if>
	</td>
	<td>
		<%--<c:if test="${X_OBJECT.status==1}"><a href='javascript:openCustomerPlan("${X_OBJECT.id}")'>打开</a></c:if>
		<c:if test="${X_OBJECT.status!=1}"><a href='javascript:closeCustomerPlan("${X_OBJECT.id}")'>关闭</a></c:if>--%>
		<c:if test="${X_OBJECT.status==1}"><input type="button"   value="打开" onclick="openCustomerPlan(${X_OBJECT.id});" /></c:if>
		<c:if test="${X_OBJECT.status!=1}"><input type="button"  value="关闭" onclick="closeCustomerPlan(${X_OBJECT.id});" /></c:if>
	</td>
</tr>

