<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}">
	<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
	<td>${X_OBJECT.code}</td>
	<td>${X_OBJECT.po_detial_id.poip_number.po_number}</td>
	<td>${X_OBJECT.po_detial_id.line }</td>
	<td>${X_OBJECT.part.id }</td>
	<td>${X_OBJECT.part.describe1 }</td>
	<td>${X_OBJECT.part.describe2 }</td>
	<td>${X_OBJECT.supplier.code}</td>
	<td>${X_OBJECT.po_detial_id.poip_number.createDate}</td>
	<td>${X_OBJECT.date}</td>
	<td>${X_OBJECT.po_detial_id.qty}</td>
	<td>${X_OBJECT.number}</td>
	<td>
		<c:if test="${X_OBJECT.po_detial_id.receiptQty == null}">0.00</c:if>
		<c:if test="${X_OBJECT.po_detial_id.receiptQty != null}">${X_OBJECT.po_detial_id.receiptQty}</c:if>
	</td>
	<td>
	<c:if test="${X_OBJECT.po_detial_id.inventoryNumber == null}">0.00</c:if>
    <c:if test="${X_OBJECT.po_detial_id.inventoryNumber != null}">${X_OBJECT.po_detial_id.inventoryNumber}</c:if>
	</td>
	<td>${X_OBJECT.status.chnShortDescription}</td>
</tr>

