<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.part.describe2}</td>
<td>${X_OBJECT.tool.code}</td>
<td>${X_OBJECT.location.code}</td>
<td>${X_OBJECT.tool.capacity}</td>
<td>${X_OBJECT.qty}</td>
<td>${X_OBJECT.user.name}</td>
<td>${X_OBJECT.date}</td>
<td>${X_OBJECT.status.chnShortDescription}</td>	
<td>${X_OBJECT.test3}</td>	
</tr>

