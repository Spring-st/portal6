<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<tr align="center">
    <td><input type="checkbox" name="ids" value="" onclick="productbox_click();"/></td>
	<td>${X_OBJECT.supplierCode}</td>
	<td>${X_OBJECT.supplierName}</td>
	<td>${X_OBJECT.wmsPart}</td>
	<td>${X_OBJECT.enterTime}</td>
	<td>${X_OBJECT.blanket}</td>
	<td>${X_OBJECT.amount}</td>
	<td>${X_OBJECT.unit}</td>
</tr>
 

