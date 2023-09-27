<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.partId}" align="center">
	<td nowrap="nowrap">
		${njitNpoPlanQueryForm.pageNo*njitNpoPlanQueryForm.pageSize+index+1} 
	</td>
	<td nowrap="nowrap">
		${X_OBJECT.partId}
	</td>
	<td nowrap="nowrap" >
		${X_OBJECT.name}
	</td>
	<td nowrap="nowrap">
		<fmt:formatNumber value="${X_OBJECT.current_qty}" pattern="#"/>
	</td >
	<td nowrap="nowrap">
		<fmt:formatNumber value="${X_OBJECT.b}" pattern="#"/>
	</td>
	<td nowrap="nowrap">
		<fmt:formatNumber value="${X_OBJECT.c}" pattern="#"/>
	</td>
	<td nowrap="nowrap">
		<fmt:formatNumber value="${X_OBJECT.d}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		${X_OBJECT.e}
	</td>
	<td nowrap="nowrap" >
		${X_OBJECT.f}
	</td>
	<td nowrap="nowrap" >
		${X_OBJECT.g}
	</td>
	<td nowrap="nowrap" >
		${X_OBJECT.h}
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d1}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d2}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d3}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d4}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d5}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d6}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d7}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d8}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d9}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d10}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d11}" pattern="#"/>
	</td>
	
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d12}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d13}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d14}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d15}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d16}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d17}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d18}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d19}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d20}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d21}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d22}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d23}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d24}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d25}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d26}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d27}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d28}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d29}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d30}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d31}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d32}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d33}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d34}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d35}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d36}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d37}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d38}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d39}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d40}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d41}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d42}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d43}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d44}" pattern="#"/>
	</td>
	<td nowrap="nowrap" >
		<fmt:formatNumber value="${X_OBJECT.d45}" pattern="#"/>
	</td>
</tr>

