<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<%--<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.soipNumber}</a></td>
	--%>
	<td>${X_OBJECT.soipNumber}</td>
	<td>${X_OBJECT.soId.custName}</td>
	<td>${X_OBJECT.soId.custCode}</td>
	<td>${X_OBJECT.soId.custAddress}</td>
	<td>${X_OBJECT.line}</td>
	<td>${X_OBJECT.itemNumber.id}</td>
	<td>${X_OBJECT.itemNumber.dpiNo}</td>
	<td>${X_OBJECT.itemNumber.oldCode}</td>
	<td>${X_OBJECT.um}</td>
	<td><fmt:formatNumber value="${X_OBJECT.qty}" type="number"/></td>
	<td>${X_OBJECT.dueDate}</td>
	<td><fmt:formatNumber value="${X_OBJECT.receiptQty}" type="number"/></td>
	<td><fmt:formatNumber value="${X_OBJECT.boxcount}" type="number"/></td>
	<td>${X_OBJECT.describe}</td>
	<%--<td>${X_OBJECT.returnQtySum}</td>
	<td>${X_OBJECT.factory}</td>
	--%>
	<td>${X_OBJECT.poDomain}</td>
	<td>${X_OBJECT.site.name}</td>
	<%--<td>
		<c:if test="${X_OBJECT.istxt == '1'}">否</c:if>
		<c:if test="${X_OBJECT.istxt == '0'}">是</c:if>
	</td>
	<td>${X_OBJECT.sotype}</td>
--%>
	<td>
		<c:if test="${X_OBJECT.status==1}">关闭</c:if>
		<c:if test="${X_OBJECT.status!=1}">打开</c:if>
	</td>
</tr>

