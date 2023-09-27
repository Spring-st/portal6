<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}">
	<td>
				<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.saiheCode}</a>
	</td>
	<td>
		${X_OBJECT.finishedCode }
	</td>
	<td>
		${X_OBJECT.finishedProductDesc }
	</td>
	<td>
		<span style="color:${X_OBJECT.status.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.status.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.status.chnShortDescription}</c:if>
    </span>
	</td>
</tr>

