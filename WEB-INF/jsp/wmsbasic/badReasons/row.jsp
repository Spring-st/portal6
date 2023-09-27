<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.describe }</a></td>
	<td>${X_OBJECT.type.chnShortDescription}</td>
	<td>${X_OBJECT.user.name}</td>
	<td>${X_OBJECT.status.chnShortDescription}</td>
	<td>${X_OBJECT.remark}</td>
</tr>

