<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<tr id="" align="center">
	<td>${X_OBJECT.po_number}</td>
	<td>${X_OBJECT.line}</td>
	<td>${X_OBJECT.boxId.lot.id}</td>
	<td>${X_OBJECT.part.id}</td>
	<td>${X_OBJECT.part.dpiNo}</td>
	<td>${X_OBJECT.part.oldCode}</td>
	<td>${X_OBJECT.part.describe1}</td>
	<td>${X_OBJECT.supper.code}</td>
	<td>${X_OBJECT.supper.name}</td>
	<td>${X_OBJECT.po_date}</td>
	<td>${X_OBJECT.create_date}</td>
	<td>${X_OBJECT.po_qty}</td>
	<td>${X_OBJECT.need_qty_rqc}</td>
	<td>${X_OBJECT.actual_qty_rqc}</td>
  	<td>${X_OBJECT.qualified_qty}</td>
  	<td>
  		<c:if test="${X_OBJECT.unqualified_qty==null}">0</c:if>
  		<c:if test="${X_OBJECT.unqualified_qty!=null}">${X_OBJECT.unqualified_qty}</c:if>
  	</td>
  	<td>${X_OBJECT.boxId.status.chnShortDescription}</td>
</tr>

