<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}" align="center">
	<td>
	<c:if test="${X_OBJECT.status=='1'}">
	<input type="checkbox" name="ids"  value="${X_OBJECT.id}"  onclick="productbox_click();" />
	</c:if>
	<c:if test="${X_OBJECT.status=='0'}">
	<input type="checkbox" disabled="disabled" />
	</c:if>
	</td>
	<td>
	<c:if test="${X_OBJECT.status=='1'}">
		<c:if test="${X_OBJECT.inventoryType!='2'}"><a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a></c:if>
		<c:if test="${X_OBJECT.inventoryType=='2'}"><a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a></c:if>
	</c:if>
	<c:if test="${X_OBJECT.status=='0'}">
		<c:if test="${X_OBJECT.start_date==null }">
			<a href='javascript:editAll("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
		</c:if>
		<c:if test="${X_OBJECT.start_date != null }">
			<c:if test="${X_OBJECT.inventoryType!='2'}"><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a></c:if>
			<c:if test="${X_OBJECT.inventoryType=='2'}"><a href='javascript:editByPart("${X_OBJECT.id}")'>${X_OBJECT.code}</a></c:if>
			
		</c:if>
	
	</c:if>
	</td>
	<Td> ${X_OBJECT.operation.name } </Td>
	<Td>${X_OBJECT.start_date }  </Td>
	<Td> ${X_OBJECT.end_date } </Td>
	<Td> <fmt:formatNumber value="${X_OBJECT.plan_sumQty }" maxFractionDigits="0" minFractionDigits="0"/> </Td>
	<td><fmt:formatNumber value="${X_OBJECT.actual_sumQty }" maxFractionDigits="0" minFractionDigits="0"/></td>
	<td> <fmt:formatNumber value="${X_OBJECT.differences_sumQty }" maxFractionDigits="0" minFractionDigits="0"/></td>
	<td>
	<span style="color:${X_OBJECT.status.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.status.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.status.chnShortDescription}</c:if>
    </span>
	</td>
	<td>
      <c:if test="${X_OBJECT.inventoryType=='2'}">物料</c:if>
      <c:if test="${X_OBJECT.inventoryType!='2'}">库位</c:if>
	</td>
	<Td>${X_OBJECT.name }</Td>
	<td>
	<c:if test="${X_OBJECT.status=='1'}">
	</c:if>
	<c:if test="${X_OBJECT.status=='0'}">
		<a href='javascript:deleteAll("${X_OBJECT.id}")'>删除盘库计划</a>
	</c:if>
	</td>
</tr>

