<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}" align="center" valign="middle">
	<td>
		${X_OBJECT.asnNo}
	</td>
	<%--<td>
		${X_OBJECT.models}
	</td>	
	--%><td>
		${X_OBJECT.productlinecode}
	</td>
	<td>
		${X_OBJECT.shiftcode}
	</td>
	<td>
		${X_OBJECT.staffcode}
	</td>
	<td>
		${X_OBJECT.number}
	</td>
	<td>
		${X_OBJECT.taskDate}
	</td>
	<td>
		${X_OBJECT.time}
	</td>
	<td>
		${X_OBJECT.qty}
	</td>
	<td>
		${X_OBJECT.syncTime}
	</td>
	<td>
		${X_OBJECT.errorInfo}
	</td>
	<%--<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	--%>
</tr>

