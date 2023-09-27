<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
	<tr id="r${X_OBJECT.id}">
	<td> <a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.id}</a></td>
	<td> ${X_OBJECT.type} </td>
	<td> ${X_OBJECT.sleepTime} </td>
	<td>
		<a href='javascript:remove("${X_OBJECT.id}")'><bean:message key="all.delete" /></a>
	</td>
</tr>

