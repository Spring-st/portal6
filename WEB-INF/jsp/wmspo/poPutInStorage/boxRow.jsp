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
<td>${X_OBJECT.lot.id}</td>
<td>${X_OBJECT.po_number}</td>
<td>${X_OBJECT.po_line}</td>
<td>
<c:if test="${X_OBJECT.psoItem!=null}">${X_OBJECT.psoItem.portalShipOrder.code}</c:if>
<c:if test="${X_OBJECT.single!=null}">${X_OBJECT.single.code}</c:if>
</td>
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.part.dpiNo}</td>
<td>${X_OBJECT.part.oldCode}</td>
<td>${X_OBJECT.po_supp}</td>
<td>${X_OBJECT.po_supp_name}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.location.code}</td>
<td>${X_OBJECT.number}</td>
<td>${X_OBJECT.po_date}</td>
<td>
<c:if test="${X_OBJECT.status_rqc==null}">未质检</c:if>
<c:if test="${X_OBJECT.status_rqc!=null}">${X_OBJECT.status_rqc.chnShortDescription}</c:if>
</td>
<td>
<c:if test="${X_OBJECT.status_freeze == '0'}"><span style="color: red;">已冻结</span></c:if>
<c:if test="${X_OBJECT.status_freeze == '1'}"><span style="color: green;">未冻结</span></c:if>
</td>	
<td style="display: none;"> 
<input type="hidden" value="${X_OBJECT.status}" id="${X_OBJECT.id}_status"/>
</td>	
</tr>

