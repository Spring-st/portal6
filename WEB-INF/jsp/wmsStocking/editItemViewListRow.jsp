<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<tr id="r${X_OBJECT.id}">
		<td>${X_OBJECT.inventory.partCode.id}</td>
		<td>${X_OBJECT.inventory.partCode.describe1}</td>
		<td>${X_OBJECT.inventory.partCode.describe2}</td>
	    <td>${X_OBJECT.inventory.storeroomId.address}</td>	 
	    <td>${X_OBJECT.inventory.number}</td>
        <td>${X_OBJECT.inventory.initialNumber}</td>
        <td>${X_OBJECT.planCount}</td>
</tr>

