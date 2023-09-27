<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<tr id="r${X_OBJECT.id}">
	<td>${X_OBJECT.poip_number.po_number.poOrder}</td>
	<td>${X_OBJECT.line}</td>
	<td>${X_OBJECT.itemNumber.id}</td>
	<td>${X_OBJECT.itemNumber.name}</td>
	<td>${X_OBJECT.supplierItemNumber}</td>
	<%--<td>${X_OBJECT.um}</td>--%>
	<td><input size="5" type="hidden" value="${X_OBJECT.qty}" class="waitQuantity" />
	    ${X_OBJECT.qty}
	</td>
	<td>${X_OBJECT.qtyOpen}</td>
	<td><input size="5" type="hidden" value="${X_OBJECT.qty-X_OBJECT.qtyOpen}" class="receiptQty" />${X_OBJECT.qty-X_OBJECT.qtyOpen}</td>

	<td>${X_OBJECT.receiptQty}</td>

    <%--<td>${X_OBJECT.boxCount}</td>--%>

	<td>${X_OBJECT.dueDate}</td>

	<td>${X_OBJECT.isPrintLabels.chnShortDescription}</td>
	<td><input style="width: 20px;" type="text" id="${X_OBJECT.itemNumber.id}_number"/><input type="button" value="<bean:message key="printCode" />" onclick="printPartCode('${X_OBJECT.id}','${X_OBJECT.itemNumber.id}_number','${X_OBJECT.qty<=X_OBJECT.receiptQty}')"/></td>
</tr>
