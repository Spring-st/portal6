<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<c:if test="${other ==1 }">
	<td>${X_OBJECT.tool.code}</td>
</c:if>
<td>${X_OBJECT.part.id}</td>
<td id ="desc1_${X_OBJECT.id}">${X_OBJECT.part.describe1}</td>
<td id ="desc2_${X_OBJECT.id}">${X_OBJECT.part.describe2}</td>
<td id ="loc_${X_OBJECT.id}">${X_OBJECT.location.code}</td>
<c:if test="${other ==1 }">
	<td><input type="hidden" id ="qty_${X_OBJECT.id}" value="${X_OBJECT.qty}"/>${X_OBJECT.qty }</td>
</c:if>
<c:if test="${other !=1 }">
<td><input type ="text" value="${X_OBJECT.qty}" id ="qty_${X_OBJECT.id}" size="5"></td></c:if>
<td><a href ="javascript:choose('${X_OBJECT.id}','${X_OBJECT.part.id }')">选择</a></td>
</tr>
