<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}">
	<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a></td>
	<td>${X_OBJECT.name1}</td>
	<td>${X_OBJECT.address}</td>
	
	<td>${X_OBJECT.country}</td>
	<td>${X_OBJECT.city}</td>
	<td>${X_OBJECT.site}</td>
	<td>${X_OBJECT.domain}</td>
	<td>
   		<a href='javascript:select("${X_OBJECT.id}","${X_OBJECT.code}", "${X_OBJECT.name1}","${X_OBJECT.address}");'><bean:message key="all.select"/></a>  
  </td>
</tr>

