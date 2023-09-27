<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<tr id="r${X_OBJECT.id}" align="center">
	<td>${X_OBJECT.part.id}</td>
	<td>${X_OBJECT.part.dpiNo}</td>
	<td>${X_OBJECT.part.oldCode}</td>
	<td>${X_OBJECT.part.describe1 }</td>
	<td>${X_OBJECT.part.describe2}</td>
	<td>${X_OBJECT.month}月</td>
	<td>${X_OBJECT.start_date}</td>
	<td>${X_OBJECT.end_date}</td>
	<td>${X_OBJECT.initial_qty}</td>
	<td>${X_OBJECT.balance_qty}</td>
</tr>

