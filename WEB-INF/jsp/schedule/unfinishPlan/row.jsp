<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}" align="center" valign="middle">
	<td>
		${X_OBJECT.models}
	</td>
	<td>
		${X_OBJECT.describe}
	</td>
	<td>
		${X_OBJECT.qty}
	</td>
	<td>
		${X_OBJECT.taskDate}
	</td>
	<td>
		${X_OBJECT.time}
	</td>
	<td>
		${X_OBJECT.unit}
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
	<td>
		<a href='javascript:listView("${X_OBJECT.id}")'>查看详细</a>
	</td>
	<td>
		<c:if test="${X_OBJECT.status=='0'}">未分解</c:if>
		<c:if test="${X_OBJECT.status=='1'}">已分解</c:if>
		<c:if test="${X_OBJECT.status=='2'}">失败</c:if>
	</td>
</tr>

