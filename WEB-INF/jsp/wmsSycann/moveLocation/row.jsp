<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<td>
<c:choose>
	<c:when test="${X_OBJECT.status_rqc=='3'}">
	<input type="checkbox" disabled="disabled" />
	</c:when>
	<c:otherwise>
	<c:if test="${X_OBJECT.status_freeze!='0'}"><input type="checkbox" name="ids" value="${X_OBJECT.lot.id}" onclick="productbox_click();"/></c:if>
	<c:if test="${X_OBJECT.status_freeze=='0'}"><input type="checkbox" disabled="disabled" /></c:if>
	</c:otherwise>
</c:choose>
</td>
<%--<td><input type="checkbox" name="ids" value="${X_OBJECT.lot.id}" onclick="productbox_click();"/></td>
--%><td>${X_OBJECT.lot.id}</td>
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.part.dpiNo}</td>
<td>${X_OBJECT.part.oldCode}</td>
<td>${X_OBJECT.po_supp}</td>
<td>${X_OBJECT.po_supp_name}</td>
<td>${X_OBJECT.location.code}</td>
<td>${X_OBJECT.number}</td>
<td>${X_OBJECT.part.unit}</td>
<td>${X_OBJECT.in_date}</td>
<td>
<c:if test="${X_OBJECT.status_rqc==null}">待检</c:if>
<c:if test="${X_OBJECT.status_rqc!=null}">${X_OBJECT.status_rqc.chnShortDescription}</c:if>
</td>
<td>
<c:if test="${X_OBJECT.status_freeze == '0'}"><span style="color: red;">已冻结</span></c:if>
<c:if test="${X_OBJECT.status_freeze == '1'}"><span style="color: green;">未冻结</span></c:if>
</td>	
<td>${X_OBJECT.status.chnShortDescription}</td>	
</tr>

