<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
	<td>${X_OBJECT.po_number}</td>
	<td>${X_OBJECT.supplier.code}</td>
	<td>${X_OBJECT.supplier.name}</td>
	<td>${X_OBJECT.createDate}</td>
	<td>${X_OBJECT.qty}</td>
	<td> 
		<c:if test="${X_OBJECT.receiptQty==null}">0</c:if>
		<c:if test="${X_OBJECT.receiptQty!=null}">${X_OBJECT.receiptQty}</c:if>
	</td>
	<td>
		<c:if test="${X_OBJECT.inventoryNumber==null}">0</c:if>
		<c:if test="${X_OBJECT.inventoryNumber!=null}">${X_OBJECT.inventoryNumber}</c:if>
	</td>
	<td>${X_OBJECT.status.chnShortDescription}</td>
</tr>
