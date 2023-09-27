<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<tr id="r${X_OBJECT.id}">
	<td>
		<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.toolCode}</a>
	</td>
	<td>
		${X_OBJECT.putNumber }
	</td>
	<td>
		<span style="color:${X_OBJECT.status.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.status.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.status.chnShortDescription}</c:if>
    </span>
	</td>
	<td>
		 <a href='javascript:select("${X_OBJECT.id}","${X_OBJECT.toolCode}");'>
		 	<bean:message key="all.select"/>
		 </a>
	</td>
</tr>

