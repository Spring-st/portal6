<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td>
				<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
	</td>
	
	<td>
		${X_OBJECT.name }
	</td>
	<td>
		${X_OBJECT.address }
	</td>
	<td>
		${X_OBJECT.type}
	</td>
	<td>
		${X_OBJECT.date}
	</td>
	<td>
		${X_OBJECT.largest_inventory}
	</td>
	<td>
		<span style="color:${X_OBJECT.status.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.status.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.status.chnShortDescription}</c:if>
    </span>
	</td>
	<td>
		${X_OBJECT.remark}
	</td>
</tr>

