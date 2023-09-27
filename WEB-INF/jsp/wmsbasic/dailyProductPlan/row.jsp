<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.workOrderNo}</a></td>
	<td>${X_OBJECT.part}</td>
	<td>${X_OBJECT.orderType}</td>
	<td>${X_OBJECT.orderAttribute}</td>
	<td>${X_OBJECT.site}</td>
	<td>${X_OBJECT.lineNo}</td>
	<td>${X_OBJECT.qty}</td>
	<td>${X_OBJECT.golineDate}</td>
	<td>${X_OBJECT.offlineDate}</td>
	<td>${X_OBJECT.procedureCode}</td>
	<td>${X_OBJECT.bomCode}</td>
	<td>${X_OBJECT.shift}</td>
	<td>${X_OBJECT.bomOutFinish}</td>
	<td>${X_OBJECT.domain}</td>
</tr>

