<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<td>${X_OBJECT.tool.code}</td>
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.part.describe2}</td>
<td>${X_OBJECT.qty}</td>
<td>${X_OBJECT.part.unit}</td>
<td>${X_OBJECT.date}</td>
<td>${X_OBJECT.userId.name}</td>	
</tr>

