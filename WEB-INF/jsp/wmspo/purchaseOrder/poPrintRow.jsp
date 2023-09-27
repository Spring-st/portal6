<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}">
	<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
	<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.poip_number.po_number}</a></td>
	<td>${X_OBJECT.line }</td>
	<td>${X_OBJECT.itemNumber.id }</td>
	<td>${X_OBJECT.itemNumber.describe1 }</td>
	<td>${X_OBJECT.itemNumber.describe2 }</td>
	<td>${X_OBJECT.poip_number.supplier.code}</td>
	<td>${X_OBJECT.poip_number.createDate}</td>
	<td><c:if test="${X_OBJECT.qty==null}">
		0.00
	</c:if>
	<c:if test="${X_OBJECT.qty!=null}">
		${X_OBJECT.qty}
	</c:if>
	</td>
	<td><c:if test="${X_OBJECT.receiptQty==null}">
		0.00
	</c:if>
	<c:if test="${X_OBJECT.receiptQty!=null}">
		${X_OBJECT.receiptQty}
	</c:if></td>
	<td><c:if test="${X_OBJECT.inventoryNumber==null}">
		0.00
	</c:if>
	<c:if test="${X_OBJECT.inventoryNumber!=null}">
		${X_OBJECT.inventoryNumber}
	</c:if></td>
	<td>${X_OBJECT.poip_number.status.chnShortDescription}</td>
</tr>

