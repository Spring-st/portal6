<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<c:set var="x_hotel_promoteStatus_GLOBAL" value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>"/>
<tr id="r${X_OBJECT.id}">
	<td><a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a></td>
	<Td>
	${X_OBJECT.name }
	</Td>
	<Td>
	${X_OBJECT.createDate }
	</Td>
	<Td>
	${X_OBJECT.createUser.name }
	</Td>
	<Td>
	${X_OBJECT.count }
	</Td>
	<td>
	<span style="color:${X_OBJECT.enabled.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.enabled.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
    </span>
	</td>
	<td align="center"><input type="button" value="查看信息" onclick="lookOver('${X_OBJECT.id}');"></td>
</tr>

