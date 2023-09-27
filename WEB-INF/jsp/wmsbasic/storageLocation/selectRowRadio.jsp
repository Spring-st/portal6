<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}">
	<td>
			<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
	</td>
	<td>
			${X_OBJECT.describe }
	</td>
	<td>
			${X_OBJECT.address }
	</td>
	<td>
			${X_OBJECT.max_inventory }
	</td>
	<td>
			${X_OBJECT.storeroom_id.describe }
	</td>
	<td>
		<span style="color:${X_OBJECT.enabled.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.enabled.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
    </span>
	</td>
  <td>
  <!--  --><a href='javascript:select("${X_OBJECT.id}","${X_OBJECT.code}", "${X_OBJECT.describe}", "${X_OBJECT.address}", "${X_OBJECT.storeroom_id.describe}");'><bean:message key="all.select"/></a>
  </td>
</tr>
