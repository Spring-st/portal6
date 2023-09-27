<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td> <a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a> </td>
	<td>${X_OBJECT.applicant.name}</td>
	<td><fmt:formatDate value="${X_OBJECT.date}" pattern="yyyy-MM-dd"/></td>
	<td>${X_OBJECT.qty}</td>
	<td>${X_OBJECT.actual_qty}</td>
	<td>${X_OBJECT.reason_code.instructions}</td>
	<td>${X_OBJECT.reason_code.expenses_course}</td>
	<td>${X_OBJECT.reason_code.department_cost}</td>
	<td>${X_OBJECT.status.chnShortDescription}</td>
	<td>${X_OBJECT.remark}</td>
</tr>
