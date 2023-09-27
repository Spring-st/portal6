<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}" align="center">
<td> ${X_OBJECT.father_part.id}</td>
<td> ${X_OBJECT.father_part.describe1}</td>
<td> ${X_OBJECT.child_part.id}</td>
<td> ${X_OBJECT.child_part.describe1}</td>
<td>${X_OBJECT.child_part.productClass}</td>
<td> 
	<fmt:formatNumber value="${X_OBJECT.unit_qty}" maxFractionDigits="0" minFractionDigits="0"/>
</td>
<td> ${X_OBJECT.child_part.unit}</td>
</tr>

