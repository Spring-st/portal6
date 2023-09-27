<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td>${X_OBJECT.code}</td>
	<td>
		<table>
		 	<c:set var="customerPlanId" value="0"></c:set>
			<c:forEach items="${X_OBJECT.customerPlanList}" var="item" varStatus="status">
			<c:if test="${item.customerPlanId.planNumbers ne customerPlanId}">
				<tr>
					<td>${item.customerPlanId.planNumbers}</td>
				</tr>
			</c:if>
				 <c:set var="customerPlanId" value="${item.customerPlanId.planNumbers}"></c:set>
			</c:forEach>
		</table>
		
	</td>
	<td>${X_OBJECT.customerCode}</td>
	<td>${X_OBJECT.shPrintDate}</td>
	<td>
		<table>
			<c:forEach items="${X_OBJECT.customerPlanList}" var="item" varStatus="status">
				<bean:define id="sumamount" value="${sumamount+(item.shipQty)}"/>
			</c:forEach>
		</table>
		<fmt:formatNumber value="${sumamount}" maxFractionDigits="0" minFractionDigits="0"/>
	</td>
	<td>
		<table>
			<c:forEach items="${X_OBJECT.customerPlanList}" var="item" varStatus="status">
				<fmt:formatNumber value="${item.customerPlanId.unitPrice * item.shipQty}" maxFractionDigits="2" pattern="#0.00#" minFractionDigits="2" var="price"/>
				<fmt:formatNumber var="sumPrice" value="${sumPrice + price}" pattern="#0.00#" maxFractionDigits="2" minFractionDigits="2" />
			</c:forEach>
		</table>
		<fmt:formatNumber value="${sumPrice}" maxFractionDigits="2" minFractionDigits="2"/>			
	</td>
	<td> 
		<c:if test="${X_OBJECT.matchStatus=='0'}">
			<span style="color: red;">已匹配</span>
		</c:if>
		<c:if test="${X_OBJECT.matchStatus!='0'}">
			未匹配
		</c:if>
	</td>
</tr>

