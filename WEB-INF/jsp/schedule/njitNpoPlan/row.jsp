<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}" align="center" valign="middle">
	<%--<td>
		${X_OBJECT.dataset}
	</td>
	--%><td>
		${X_OBJECT.partId.id}
	</td>
	<td>
		${X_OBJECT.partId.describe1}
	</td>
	<td>
		${X_OBJECT.partId.unit}
	</td>
	<td>
		${X_OBJECT.partId.vend}
	</td>
	<%--<td>
		${X_OBJECT.nbr}
	</td>
	<td>
		${X_OBJECT.line}
	</td>
	<td>
		${X_OBJECT.relDate}
	</td>
	--%><td>
		${X_OBJECT.needDate}
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.qty}" pattern="#"/>
	</td>
	<%--<td>
		${X_OBJECT.time}
	</td>
	
	<td>
		${X_OBJECT.type}
	</td>
	<td>
		${X_OBJECT.detail}
	</td>
	--%><td>
		${X_OBJECT.version}
	</td>
	
</tr>

