<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}">
	<td><a href='javascript:devanning("${X_OBJECT.location.id}")'>${X_OBJECT.location.code}</a></td>
	<td>${X_OBJECT.lotSer.id}</td>
	<td>${X_OBJECT.wmsPart.id}</td>
	<td>${X_OBJECT.blanketMark}</td>
	<td>${X_OBJECT.wmsPart.describe2}</td>
	<td>${X_OBJECT.poritem.poip_item_id.poip_number.po_number.supplier.code}</td>
	<td>${X_OBJECT.count}</td>
	<td>${X_OBJECT.wmsPart.unit}</td>
</tr>

