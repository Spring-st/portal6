<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}">
	<td><input type="checkbox" name="ids" value="${X_OBJECT.po_detial_id.id}" onclick="productbox_click();"/></td>
	<td>
	<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.po_order}</a>
	</td>
	<td>${X_OBJECT.line}</td>
	<td>${X_OBJECT.po_detial_id.itemNumber.id}</td>
	<td>${X_OBJECT.po_detial_id.itemNumber.describe1}</td>
	<td>${X_OBJECT.po_detial_id.itemNumber.describe2}</td>
	<td>${X_OBJECT.po_detial_id.qty_std}</td>
	<td>${X_OBJECT.po_detial_id.poip_number.supplier.code}</td>
	<td>${X_OBJECT.date}</td>
	<td>${X_OBJECT.plan_number}</td>
	<td>${X_OBJECT.actual_number}</td>
	<td>${X_OBJECT.status.chnShortDescription}</td>
</tr>

