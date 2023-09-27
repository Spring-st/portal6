<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="s${X_OBJECT.id}" align="center">
	<td width="5%">${X_OBJECT.num}</td>
	<td >${X_OBJECT.productionPlanningNumber}</td>
	<td >
	<fmt:formatDate value="${X_OBJECT.uploadDate}"  pattern="yyyy-MM-dd HH:mm:ss" /></td>
	<td>${X_OBJECT.uploadUser.loginName}</td>
</tr>
