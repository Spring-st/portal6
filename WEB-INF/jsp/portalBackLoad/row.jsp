<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center" >
	<%--<td>
		${X_OBJECT.poip_number.po_number}
	</td>--%>
	<td>
		${X_OBJECT.poip_number.supplier.code}
	</td>
	<td>
		${X_OBJECT.itemNumber.id}
	</td>
	<td>
		${X_OBJECT.itemNumber.describe1}
	</td>
	<td>
		${X_OBJECT.qty}
	</td>
	<td>
		${X_OBJECT.itemNumber.unit}
	</td>
	<td>
		<c:if test="${X_OBJECT.poip_number.createType == '1'}">JIT</c:if>
		<c:if test="${X_OBJECT.poip_number.createType == '2'}">NJIT_PO</c:if>
		<c:if test="${X_OBJECT.poip_number.createType == '3'}">NJIT_NPO</c:if>
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
	<td>
		
	</td>
</tr>