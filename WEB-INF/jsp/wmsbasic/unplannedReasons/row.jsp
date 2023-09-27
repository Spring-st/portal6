<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center" valign="middle">
	<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.instructions}</a></td>
	<td>${X_OBJECT.describe}</td>
	<td>${X_OBJECT.expenses_course}</td>
	<td>${X_OBJECT.department_cost}</td>
	<td>${X_OBJECT.date}</td>
</tr>

