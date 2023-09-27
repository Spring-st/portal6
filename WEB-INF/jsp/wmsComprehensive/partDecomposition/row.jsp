<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}">
	<td>
		<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.lot.id}</a>
	</td>
	<td>${X_OBJECT.part.id}</td>
	<td>${X_OBJECT.psoItem.poipItem.poip_number.supplier.code}</td>
	<td>${X_OBJECT.part.describe1}</td>
	<td>${X_OBJECT.part.describe2}</td>
	<td>${X_OBJECT.in_date}</td>
	<td>${X_OBJECT.number}</td>
	<td>${X_OBJECT.location.code}</td>
	<td>${X_OBJECT.number}</td>
	<td>${X_OBJECT.status.chnShortDescription}</td>
	<td>
		<c:if test="${X_OBJECT.status=='1' || X_OBJECT.status=='2' || X_OBJECT.status =='4'}"><a href="#" style="color: #ccc">分解</a></c:if>
		<c:if test="${X_OBJECT.status=='3'}"><a href="#" onclick="decomposition('${X_OBJECT.lot.id}');">分解</a></c:if>
	</td>
</tr>

