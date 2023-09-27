<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}">
	<td>${X_OBJECT.id}</td>
	<td>${X_OBJECT.dpiNo}</td>
	<td>${X_OBJECT.name}</td>
	<td>${X_OBJECT.describe1}</td>
	<td>${X_OBJECT.describe2}</td>
	<td>${X_OBJECT.unit}</td>
	<td>
	<span style="color:${X_OBJECT.enabled.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.enabled.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
    </span>
  </td>
</tr>
