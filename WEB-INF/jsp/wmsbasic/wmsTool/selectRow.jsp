<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<c:set var="yesNo_yes"
	value="<%=com.aof.model.metadata.YesNo.YES.getEnumCode()%>"></c:set>
<c:set var="x_hotel_promoteStatus_GLOBAL"
	value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>" />
<tr id="r${X_OBJECT.id}">
	<td>
		<input type="checkbox" name="ids" value="${X_OBJECT.id}"
			onclick="setCount()">
		<c:if test="${X_OBJECT.isPrint.enumCode==yesNo_yes}">
			<font color="red"><bean:message key='Printed' /> </font>
		</c:if>
	</td>
	<td>
		${X_OBJECT.code}
	</td>

</tr>

