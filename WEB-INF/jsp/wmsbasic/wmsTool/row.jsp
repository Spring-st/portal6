<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
	<tr id="r${X_OBJECT.id}">
	<td>
		<input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="setCount()">
	</td>
	<td> <a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a></td>
	<td> ${X_OBJECT.capacity} </td>
	<td> ${X_OBJECT.date} </td>
	<td> ${X_OBJECT.enabled.chnShortDescription} </td>
</tr>

