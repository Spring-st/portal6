<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center" ><%--
	<td>
		${X_OBJECT.productionId.asnNo}
	</td>
	--%><td>
		${X_OBJECT.childPart.id}
	</td>
	<td>
		${X_OBJECT.childPart.describe1}
	</td>
	<%--<td>
		${X_OBJECT.qty}
	</td>
	--%>
	<td>
		<fmt:formatNumber value="${X_OBJECT.currentQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.childPart.securityQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.childPart.highQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.childPart.lowQty}" pattern="#"/>
	</td>
	<td>
	<fmt:formatDate value="${X_OBJECT.date}" pattern="yyyy-MM-dd"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour8DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour10DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour12DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour14DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour16DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour18DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour20DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour22DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour24DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour2DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour4DemandQty}" pattern="#"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour6DemandQty}" pattern="#"/>
	</td>
</tr>

