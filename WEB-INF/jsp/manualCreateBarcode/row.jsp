<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<tr id="s${X_OBJECT.id}" align="center">
	<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="setCount();"/>
		<input type="hidden" id="item${X_OBJECT.id}" value="${X_OBJECT.id}"/>
	</td>
	<td>
		${X_OBJECT.part}
	</td>
	<td>
		${X_OBJECT.partName}
	</td>
	<td>
		${X_OBJECT.partUnit}
	</td>
	<td>
		${X_OBJECT.model}
	</td>
	<td>
		${X_OBJECT.supplierName}
	</td>
	<td>
		<fmt:formatDate value="${X_OBJECT.serialDate}" pattern="yyMMdd"/> 
	</td>
	<td>
		${X_OBJECT.serialNumber}
	</td>
	<td>
		${X_OBJECT.qty}
	</td>
	<td>
		<c:if test="${X_OBJECT.printStatus==0}">
			未打印
		</c:if>
		<c:if test="${X_OBJECT.printStatus==1}">
			已打印
		</c:if>
	</td>
</tr>
