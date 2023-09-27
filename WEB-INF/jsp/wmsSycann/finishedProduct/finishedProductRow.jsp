<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<c:if test="${X_OBJECT.saiheCode==null}">
<td><input type="checkbox" disabled="disabled"/></td>
</c:if>
<c:if test="${X_OBJECT.saiheCode!=null}">
<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="getCheckedNum(this);"/></td>
<%--<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>--%>
</c:if>
<td>${X_OBJECT.saiheCode}</td>
<td>${X_OBJECT.hncCode}</td>
<td>${X_OBJECT.description}</td>
<td>${X_OBJECT.qty}</td>
<td>${X_OBJECT.outDate}</td>
<td>${X_OBJECT.deliverDate}</td>
<td>
	<c:if test="${X_OBJECT.status==0}">
		未发货
	</c:if>
	<c:if test="${X_OBJECT.status==1}">
		已发货
	</c:if>
</td>
</tr>

