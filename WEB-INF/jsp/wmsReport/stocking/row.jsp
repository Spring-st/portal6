<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<tr id="r${X_OBJECT.id}" align="center">
	<td>${X_OBJECT.stocking_id.code}</td>
	<td>${X_OBJECT.location.code} </td>
	<td>${X_OBJECT.box.lot.id} </td>
	<td>${X_OBJECT.box.part.id}</td>
	<td>${X_OBJECT.box.part.dpiNo}</td>
	<td>${X_OBJECT.box.part.oldCode}</td>
	<td>${X_OBJECT.box.part.describe1}</td>
	<td>${X_OBJECT.inventory_qty}  </td>
	<td>${X_OBJECT.stocking_qty}  </td>
	<td>${X_OBJECT.differences}  </td>
	<td>${X_OBJECT.stocking_id.operation.name } </td>
	<td>${X_OBJECT.stocking_id.start_date }</td>
	<td>
	<span style="color:${X_OBJECT.stocking_id.status.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.stocking_id.status.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.stocking_id.status.chnShortDescription}</c:if>
    </span>
	</td>
	<td>${X_OBJECT.stocking_id.name }</td>
</tr>

