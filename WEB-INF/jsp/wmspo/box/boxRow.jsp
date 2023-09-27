<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
<td>${X_OBJECT.lot.id}</td>
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.number}</td>
<td>${X_OBJECT.part.unit}</td>
<td>${X_OBJECT.date}</td>
<td>${X_OBJECT.status_freeze.chnShortDescription}</td>	
<td>${X_OBJECT.status_print.chnShortDescription}</td>	
<td>${X_OBJECT.status.chnShortDescription}</td>	
</tr>

