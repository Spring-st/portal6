<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}">
	<td>${X_OBJECT.assembly.id }</td>
	<td>${X_OBJECT.assembly.describe1 }</td>
	<td>${X_OBJECT.part.id }</td>
	<td>${X_OBJECT.part.describe1 }</td>
	<td>${X_OBJECT.location.code}</td>	
	<td>${X_OBJECT.qty}</td>
	<td>${X_OBJECT.date}</td>
	<td>
		<c:if test="${X_OBJECT.is_sync == '0'}"><span style="color: green;">已同步</span></c:if>
		<c:if test="${X_OBJECT.is_sync ne '0'}"><span style="color: red;">未同步</span></c:if>
	</td>
	<td>${X_OBJECT.is_sync_date}</td>
</tr>

