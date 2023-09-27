<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
	<td>${X_OBJECT.lot.id}</td>
	<td>${X_OBJECT.po_number}</td>
	<td>${X_OBJECT.po_line}</td>
	<td>${X_OBJECT.part.id}
		<input type="hidden" id="partids${X_OBJECT.id}" value="${X_OBJECT.part.id}" />
	</td>
	<td>${X_OBJECT.part.dpiNo}</td>
	<td>${X_OBJECT.part.oldCode}</td>
	<td>${X_OBJECT.part.describe1}</td>
	<td>${X_OBJECT.part.describe2}</td>
	<td>${X_OBJECT.number}</td>
	<td>${X_OBJECT.location.code}
		<input type="hidden" id="locationids${X_OBJECT.id}" value="${X_OBJECT.location.id}" />
	</td>
	<td>${X_OBJECT.in_date}</td>
	<td>${X_OBJECT.out_date}</td>
	<td>${X_OBJECT.status.chnShortDescription}</td>
</tr>

