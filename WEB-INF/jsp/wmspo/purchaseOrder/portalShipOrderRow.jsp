<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<tr id="r${X_OBJECT.id}">
	<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="checkBoxs(this);"/></td>
	<td>
		<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.portalShipOrder.code}</a>
	</td>
	<td>
	${X_OBJECT.poipItem.poip_number.po_number }
	</td>
	<td>
	${X_OBJECT.poipItem.line }
	</td>
	<td>
	<input type="hidden" value="${X_OBJECT.poipItem.itemNumber.id}" id="singlecode_${X_OBJECT.id}" />
	${X_OBJECT.poipItem.itemNumber.id }
	</td>
	<td>
	${X_OBJECT.poipItem.itemNumber.describe1 }
	</td>
	<td>
	${X_OBJECT.poipItem.poip_number.supplier.code}
	</td>
	<td> ${X_OBJECT.portalShipOrder.createDate} </td>
	<td>${X_OBJECT.poipItem.capacity}</td>
	<td>
		<fmt:formatNumber value="${X_OBJECT.poipItem.qty}" maxFractionDigits="2" minFractionDigits="2"/>
		<input type="hidden" value="${X_OBJECT.poipItem.qty}" id="poipItemQty_${X_OBJECT.id}" /> 
		<input type="hidden" value="${X_OBJECT.poipItem.capacity}" id="poipItemCapacity_${X_OBJECT.id}" /> 
	</td>
	<td>
		<c:if test="${X_OBJECT.already_season_qty == null}">
			0.00
			<input type="hidden" value="0" id="qty_${X_OBJECT.id}" />
		</c:if>
		<c:if test="${X_OBJECT.already_season_qty != null}">
			<fmt:formatNumber value="${X_OBJECT.already_season_qty}" maxFractionDigits="2" minFractionDigits="2"/> 
			<input type="hidden" value="${X_OBJECT.already_season_qty}" id="qty_${X_OBJECT.id}" />
		</c:if>
	</td>
	<td>
	<c:if test="${X_OBJECT.received_qty!=null}">
	<input type="hidden"" value="${X_OBJECT.received_qty}"  id="a${X_OBJECT.id}" size="4" />
	</c:if>
	
	<c:if test="${X_OBJECT.received_qty!=null}">
	<input type="hidden" value="${X_OBJECT.received_qty-X_OBJECT.already_season_qty}"  id="already_season${X_OBJECT.id}" size="4" />
	</c:if>
	<c:if test="${X_OBJECT.received_qty!=null}">
	<input type="text" value="${X_OBJECT.received_qty-X_OBJECT.already_season_qty}"  id="already_season_${X_OBJECT.id}" size="4" />
	</c:if>
	</td>
	<td> ${X_OBJECT.portalShipOrder.status.chnShortDescription} </td>
</tr>

