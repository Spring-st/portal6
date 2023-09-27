<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmtD" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center" >
	<td>
		<c:choose>
			<c:when test="${X_OBJECT.status=='2'}">
				<input type="checkbox" disabled="disabled" />
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="ids" id="${X_OBJECT.id}" value="${X_OBJECT.id}" onclick="productbox_click();"/>
			</c:otherwise>
		</c:choose>
	</td>
	<%--<td>
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
		${X_OBJECT.childPart.unit}
	</td>
	<td>
		${X_OBJECT.childPart.productClass}
	</td>
	<td>
		${X_OBJECT.currentQty}
	</td>
	<td>
	<fmtD:formatDate value="${X_OBJECT.date}" pattern="yyyy-MM-dd"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour8DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour10DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour12DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour14DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour16DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour18DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour20DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour22DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour24DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour2DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour4DemandQty}" maxFractionDigits="0" />
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.hour6DemandQty}" maxFractionDigits="0" />
	</td>
</tr>

