<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}" align="center" valign="middle">
	<td>
				<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.id}</a>
	</td>
	<td>
				${X_OBJECT.code}
	</td>
	<td>
				${X_OBJECT.description}
	</td>
	<td>
	<span style="color:${X_OBJECT.enabled.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.enabled.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
    </span>
  </td>
	
</tr>

