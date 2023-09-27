<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<td>${X_OBJECT.lot.id}</td>
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.part.dpiNo}</td>
<td>${X_OBJECT.part.oldCode}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.po_supp}</td>
<td>${X_OBJECT.po_supp_name}</td>
<td>${X_OBJECT.number}</td>
<td>${X_OBJECT.location.code}</td>
<td>${X_OBJECT.in_date}</td>
<td>
<c:if test="${X_OBJECT.status_rqc==null}">未质检</c:if>
<c:if test="${X_OBJECT.status_rqc!=null}">${X_OBJECT.status_rqc.chnShortDescription}</c:if>
</td>
<td>${X_OBJECT.status_freeze.chnShortDescription}</td>	
<td>${X_OBJECT.status.chnShortDescription}
<input type="hidden" value="${X_OBJECT.status}" id="${X_OBJECT.id}_status"/>
</td>	
</tr>

