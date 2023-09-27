<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
    
	<td><a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.planNumbers}</a></td>
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
	<%--<td><fmt:formatNumber value="${X_OBJECT.returnQtySum}" type="number"></fmt:formatNumber></td>--%>
	<td>${X_OBJECT.operation}</td>
	<fmt:formatDate value="${X_OBJECT.entryDate}" var="date" pattern="yyyy-MM-dd HH:mm" />
	<td>${date}</td>
	<%--<td>
	<c:if test="${X_OBJECT.isprint=='0'}">
		<span style="color: red;">已打印</span>
	</c:if>
	<c:if test="${X_OBJECT.isprint!='0'}">
		未打印
	</c:if>
	</td>
--%></tr>

