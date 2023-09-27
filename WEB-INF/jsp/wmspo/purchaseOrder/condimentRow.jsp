<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}">
	<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="checkBoxs(this);"/></td>
	<td>${X_OBJECT.code}</td>
	<td>
		${X_OBJECT.po_detial_id.poip_number.po_number}
	</td>
	<td>${X_OBJECT.po_detial_id.line }</td>
	<td>${X_OBJECT.part.id }</td>
	<td>${X_OBJECT.part.describe1 }</td>
	<td>${X_OBJECT.supplier.code}</td>
	<td>${X_OBJECT.po_detial_id.poip_number.createDate}</td>
	<td>${X_OBJECT.date}</td>
	<td>${X_OBJECT.po_detial_id.qty}</td>
	<td>${X_OBJECT.number}</td>
	<td>
		<c:if test="${X_OBJECT.delivery_qty == null}">0.00</c:if>
		<c:if test="${X_OBJECT.delivery_qty != null}">${X_OBJECT.delivery_qty}</c:if>
	</td>
	<td>
		<c:if test="${X_OBJECT.putIn_qty == null}">0.00</c:if>
		<c:if test="${X_OBJECT.putIn_qty != null}">${X_OBJECT.putIn_qty}</c:if>
	</td>
	<td>${X_OBJECT.status.chnShortDescription}</td>
	<c:if test="${X_OBJECT.isPrint==0}">
		<td>
			<span style="color: red;">未打印</span>		
		</td>
	</c:if>
	<c:if test="${X_OBJECT.isPrint==1}">
		<td><span style="color: green;">已打印</span></td>
	</c:if>
</tr>
