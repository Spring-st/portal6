<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.workOrderNo}</a></td>
	<td>${X_OBJECT.productNo}</td>
	<td>${X_OBJECT.partNo}</td>
	<td>${X_OBJECT.allNeedQty}</td>
	<td>${X_OBJECT.workingOrder}</td>
	<td>${X_OBJECT.workingPosition}</td>
	<td>${X_OBJECT.singleNeedQty}</td>
	<td>${X_OBJECT.site}</td>
	<td>${X_OBJECT.domain}</td>
</tr>

