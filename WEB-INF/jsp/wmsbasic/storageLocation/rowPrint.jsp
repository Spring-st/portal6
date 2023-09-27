<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}">
	<td><img width=220  src='${path}/CreateBarCode?msg=${X_OBJECT.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=8&width=220&moduleWidth=220'></td>
	<td>${X_OBJECT.code}</td>
	<td>${X_OBJECT.describe }</td>
	<td>${X_OBJECT.address }</td>

</tr>

