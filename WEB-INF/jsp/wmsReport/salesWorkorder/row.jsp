<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<td>${X_OBJECT.shipId.code}</td>
<td>${X_OBJECT.lotSer.id}</td>
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.part.dpiNo}</td>
<td>${X_OBJECT.part.oldCode}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.outDate}</td>
<td>
	<c:if test="${X_OBJECT.isSync=='0'}">
		<span style="color: red;">已同步</span>
	</c:if>
	<c:if test="${X_OBJECT.isSync!='0'}">
		未同步
	</c:if>
</td>
</tr>

