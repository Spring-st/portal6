<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<td>${X_OBJECT.box.lot.id}</td>
<td>${X_OBJECT.assembly_part.id}</td>
<td>${X_OBJECT.child_part.id}</td>
<td>${X_OBJECT.child_part.describe1}</td>
<td>${X_OBJECT.box.location.code}</td>
<td>${X_OBJECT.box.number}</td>
<td>${X_OBJECT.serial_number}</td>
<td>${X_OBJECT.date}</td>
<td>${X_OBJECT.test3}</td>
<td><a href='javascript:select("${X_OBJECT.assembly_part.id}", "${X_OBJECT.test3}");'>
  <bean:message key="all.select"/></a></td>
</tr>

