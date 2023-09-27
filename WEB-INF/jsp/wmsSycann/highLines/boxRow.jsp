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
	<input type="checkbox" name="ids" value="${X_OBJECT.lot.id}" onclick="productbox_click();"/>
	</c:otherwise>
</c:choose>
</td>
<td>${X_OBJECT.lot.id}</td>
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.po_supp}</td>
<td>${X_OBJECT.po_supp_name}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.location.code}</td>
<td>${X_OBJECT.number}</td>
<td>${X_OBJECT.part.unit}</td>
<td>${X_OBJECT.in_date}</td>
<td>${X_OBJECT.in_date_line}</td>
<td>
<c:if test="${X_OBJECT.status_rqc==null}">未质检</c:if>
<c:if test="${X_OBJECT.status_rqc!=null}">${X_OBJECT.status_rqc.chnShortDescription}</c:if>
</td>
<td>${X_OBJECT.status_freeze.chnShortDescription}</td>	
<td>${X_OBJECT.status.chnShortDescription}
<input type="hidden" value="${X_OBJECT.status}" id="${X_OBJECT.id}_status"/>
</td>	
</tr>

