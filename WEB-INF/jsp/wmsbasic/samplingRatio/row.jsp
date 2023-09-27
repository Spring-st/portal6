<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}">
<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
	<td>
				<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.id}</a>
	</td>
	<td> ${X_OBJECT.name}
	</td>
	<td> ${X_OBJECT.describe1}
	</td>
	<td> ${X_OBJECT.describe2}
	</td>
	<td>  ${X_OBJECT.unit}
	</td>
	<td> ${X_OBJECT.productClass}
	</td>
	<td> 
		<c:if test="${X_OBJECT.freeze_status=='0'}">已冻结</c:if>
		<c:if test="${X_OBJECT.freeze_status!='0'}">未冻结</c:if>
	</td>
	<td> ${X_OBJECT.recommend_date}</td>
	<td>
	<span style="color:${X_OBJECT.enabled.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.enabled.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
    </span>
  </td>
</tr>

