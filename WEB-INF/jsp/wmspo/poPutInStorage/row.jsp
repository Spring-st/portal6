<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td>${X_OBJECT.po_number}</td>
	<td>${X_OBJECT.line}</td>
	<td>${X_OBJECT.lotSer.id}</td>
	<td>${X_OBJECT.part.id}</td>
	<td>${X_OBJECT.part.dpiNo}</td>
	<td>${X_OBJECT.part.oldCode}</td>
	<td>${X_OBJECT.part.describe1}</td>
	<td>${X_OBJECT.part.describe2}</td>
	<td>${X_OBJECT.supper.code}</td>
	<td>${X_OBJECT.location.code}</td>
	<td>
		<c:if test="${X_OBJECT.single==null}">${X_OBJECT.poipItem.capacity}</c:if>
		<c:if test="${X_OBJECT.single!=null}">${X_OBJECT.single.po_detial_id.capacity}</c:if>
	</td>
	<td>${X_OBJECT.date}</td>
	<td>${X_OBJECT.receipts_qty}</td>
	<td> 
		<c:if test="${X_OBJECT.poipItem.receiptQty==null}">0</c:if>
		<c:if test="${X_OBJECT.poipItem.receiptQty!=null}">${X_OBJECT.poipItem.receiptQty}</c:if>
	</td>
	<td>
		<c:if test="${X_OBJECT.qty==null}">0</c:if>
		<c:if test="${X_OBJECT.qty!=null}">${X_OBJECT.qty}</c:if>
	</td>
	<td>
		<c:if test="${X_OBJECT.is_sync == '1'}">未同步</c:if>
		<c:if test="${X_OBJECT.is_sync == '0'}">已同步</c:if>
	</td>
	<%--<td>${X_OBJECT.status.chnShortDescription}</td>
--%></tr>

