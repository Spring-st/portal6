<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<tr id="r${X_OBJECT.id}" align="center">
	<td>
	<c:if test="${X_OBJECT.status=='1' }"><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a></c:if>
	<c:if test="${X_OBJECT.status!='1' }"><a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a></c:if>
	</td>
	<td>${X_OBJECT.applicant.name}</td>
	<td><fmt:formatDate value="${X_OBJECT.date}" pattern="yyyy-MM-dd"/></td>
	<td>
		<c:if test="${X_OBJECT.qty==null}">0</c:if>
		<c:if test="${X_OBJECT.qty!=null}">${X_OBJECT.qty}</c:if>
	</td>
	<td>
		<c:if test="${X_OBJECT.actual_qty==null}">0</c:if>
		<c:if test="${X_OBJECT.actual_qty!=null}">${X_OBJECT.actual_qty}</c:if>
	</td>
	<td>${X_OBJECT.reason_code.instructions}</td>
	<td>${X_OBJECT.reason_code.expenses_course}</td>
	<td>${X_OBJECT.reason_code.department_cost}</td>
	<td>${X_OBJECT.status.chnShortDescription}</td>
	<td>${X_OBJECT.remark}</td>
</tr>
