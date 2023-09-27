<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmts"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<tr id="r${X_OBJECT.id}" align="center" valign="middle">
	<td>
		<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.models}</a>
	</td>
	<td>${X_OBJECT.des}</td>
	<td>
		${X_OBJECT.qadPart.id}
	</td>
	<td><fmt:formatDate value="${X_OBJECT.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
	<td>
		<fmts:formatNumber value="${X_OBJECT.qty}" maxFractionDigits="0" minFractionDigits="0"/>
	</td>
	<td>
		<a href='javascript:remove("${X_OBJECT.id}")'><bean:message key="all.delete" /></a>
	</td>
	
</tr>

