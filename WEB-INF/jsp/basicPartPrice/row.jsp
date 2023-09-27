<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.customer}</a></td>
	<td align="center">${X_OBJECT.sotaxc }</td>
	<td align="center">${X_OBJECT.partId }</td>
	<td align="center">${X_OBJECT.solist }</td>
	<td align="center">${X_OBJECT.pcDesc }</td>
	<td align="center">${X_OBJECT.curr }</td>
	<td align="center">${X_OBJECT.pcUm }</td>
	<td align="center">${X_OBJECT.startDate }</td>
	<td align="center">${X_OBJECT.expireDate }</td>
	<td align="center">${X_OBJECT.amtType }</td>
	<td align="center">${X_OBJECT.amt }</td>
	<td align="center">${X_OBJECT.rmks }</td>
</tr>

