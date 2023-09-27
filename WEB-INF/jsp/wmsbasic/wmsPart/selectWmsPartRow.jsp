<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.part.id}" align="center">
	<td>
		<input type="hidden"  id="td_id${X_OBJECT.part.id}" value="${X_OBJECT.part.id}"/>
		<input type="checkbox" name="ids" value="${X_OBJECT.part.id}" id="cbox${X_OBJECT.part.id}" onclick="check()" />
	</td>
	<td id="td_id${X_OBJECT.part.id}">${X_OBJECT.part.id}</td>
	<td id="td_oldCode${X_OBJECT.part.id}">${X_OBJECT.part.oldCode}</td>
	<td id="td_name${X_OBJECT.part.id}">${X_OBJECT.part.name}</td>
	<td id="td_describe1${X_OBJECT.part.id}">${X_OBJECT.part.describe1}</td>
	<td id="td_describe2${X_OBJECT.part.id}">${X_OBJECT.part.describe2}</td>
	<td><fmt:formatNumber value="${X_OBJECT.sumNumber}" maxFractionDigits="0" minFractionDigits="0"/> </td>
	<td id="td_unit${X_OBJECT.part.id}">${X_OBJECT.part.unit}</td>
</tr>
