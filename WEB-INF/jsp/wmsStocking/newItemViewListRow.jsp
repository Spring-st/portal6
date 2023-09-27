<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<tr id="r${X_OBJECT.id}">
		<td>${X_OBJECT.partCode.id}</td>
		<td>${X_OBJECT.partCode.describe1}</td>
		<td>${X_OBJECT.partCode.describe2}</td>
	    <td>${X_OBJECT.storeroomId.address}</td>	 
	    <td>${X_OBJECT.number}</td>
        <td>${X_OBJECT.initialNumber}</td>
	   <td></td>
</tr>

