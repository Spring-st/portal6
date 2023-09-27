<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}">
 	<td><input type="checkbox" name="ids" value="${X_OBJECT.lot.id}" onclick="productbox_click(this.value);"/></td>
	<td><a href='javascript:viewBoxByLotSer("${X_OBJECT.lot.id}")'>${X_OBJECT.lot.id}</a></td>
	<td>${X_OBJECT.location.code}</td>
	<td>${X_OBJECT.part.id}</td>
	<td>${X_OBJECT.part.describe1}</td>
	<td>${X_OBJECT.part.describe2}</td>
	<td>${X_OBJECT.number}</td>
	<td> <fmt:formatDate value="${X_OBJECT.date}" pattern="yyyy-MM-dd"/> </td>
</tr>

