<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}">
	<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
	<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.poip_number.po_number}</a></td>
	<td>${X_OBJECT.line }</td>
	<td>${X_OBJECT.itemNumber.id }</td>
	<td>${X_OBJECT.itemNumber.describe1 }</td>
	<td>${X_OBJECT.itemNumber.describe2 }</td>
	<td>${X_OBJECT.poip_number.supplier.code}</td>
	<td>${X_OBJECT.poip_number.supplier.name}</td>
	<td>${X_OBJECT.poip_number.createDate}</td>
	<td>
		<c:if test="${X_OBJECT.qty==null}">0.00</c:if>
		<c:if test="${X_OBJECT.qty!=null}"><fmt:formatNumber value="${X_OBJECT.qty}" maxFractionDigits="2" minFractionDigits="2"/></c:if>
	</td>
	<td>
		<c:if test="${X_OBJECT.receiptQty==null}">0.00</c:if>
		<c:if test="${X_OBJECT.receiptQty!=null}"><fmt:formatNumber value="${X_OBJECT.receiptQty}" maxFractionDigits="2" minFractionDigits="2"/></c:if></td>
	<td><c:if test="${X_OBJECT.inventoryNumber==null}">0.00</c:if>
		<c:if test="${X_OBJECT.inventoryNumber!=null}"><fmt:formatNumber value="${X_OBJECT.inventoryNumber}" maxFractionDigits="2" minFractionDigits="2"/></c:if></td>
	<td>
		<input type="text" value="${X_OBJECT.capacity}" id="capacity_${X_OBJECT.id}" size="5"/>
	</td>
	<td>${X_OBJECT.poip_number.status.chnShortDescription}</td>
</tr>

