<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}" align="center" style="word-wrap:break-word; word-break:break-all;">
<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
	<td>${X_OBJECT.productSpecifications}</td>
	<td>
		<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.id}</a>
	</td>
	<td > ${X_OBJECT.describe1}
	</td>
	<td> ${X_OBJECT.describe2}
	</td>
	<td>${X_OBJECT.drwgLoc}</td>
	<td> ${X_OBJECT.vend}
	</td>
	<td>  ${X_OBJECT.productClass}
	</td>
	<td> 
		<fmt:formatNumber value="${X_OBJECT.ord_mult}" maxFractionDigits="0" minFractionDigits="0"/>
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.highQty}" maxFractionDigits="0" minFractionDigits="0"/>  
	</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.lowQty}" maxFractionDigits="0" minFractionDigits="0"/> 
	</td>
	<td> 
		<fmt:formatNumber value="${X_OBJECT.securityQty}" maxFractionDigits="0" minFractionDigits="0"/>
	</td>
	<td > ${X_OBJECT.unit}
	</td>
	<td>
		<span style="color:${X_OBJECT.enabled.color}">
	      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.freeze_status.engShortDescription}</c:if>
	      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.freeze_status.chnShortDescription}</c:if>
	    </span>
	</td>
	<td>
		<span style="color:${X_OBJECT.enabled.color}">
	      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.enabled.engShortDescription}</c:if>
	      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
	    </span>
	</td>
</tr>

