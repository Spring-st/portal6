<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr align="center">
	<td>${X_OBJECT.lotSer.id }</td>
	<td>${X_OBJECT.part.id }</td>
	<td>${X_OBJECT.part.dpiNo }</td>
	<td>${X_OBJECT.part.oldCode }</td>
	<td>${X_OBJECT.part.describe1 }</td>
	<td>${X_OBJECT.part.describe2 }</td>
	<td>${X_OBJECT.old_location.code }</td>
	<td>${X_OBJECT.new_location.code }</td>
	<td>${X_OBJECT.qty }</td>
	<td>${X_OBJECT.part.unit }</td>
	<td>${X_OBJECT.date }</td>
	<td>
		<c:if test="${X_OBJECT.is_sync == '1'}">未同步</c:if>
		<c:if test="${X_OBJECT.is_sync == '0'}">已同步</c:if>
	</td>
	<td>${X_OBJECT.is_sync_date }</td>
	<td>${X_OBJECT.remark }</td>
</tr>

