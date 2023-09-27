<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/>
	<input type="hidden" id="cb${X_OBJECT.id}" value="${X_OBJECT.lot.id}" />
</td>
<td>${X_OBJECT.lot.id}</td>
<td>${X_OBJECT.po_number}</td>
<td>${X_OBJECT.po_line}</td>
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.po_supp}</td>
<td>${X_OBJECT.po_supp_name}</td>
<td>${X_OBJECT.location.code}</td>
<td>${X_OBJECT.number}</td>
<td>${X_OBJECT.part.unit}</td>
<td>${X_OBJECT.po_date}</td>
<td>
<c:if test="${X_OBJECT.status_rqc==null}">待检</c:if>
<c:if test="${X_OBJECT.status_rqc!=null}">${X_OBJECT.status_rqc.chnShortDescription}</c:if>
</td>
<td>
	<table>
		<c:forEach items="${X_OBJECT.unqualifiedList}" var="item" varStatus="status">
				<tr>
					<td>${item.reasons.describe}</td>
				</tr>
		</c:forEach>
	</table>
</td>
<td><input type="text"  maxlength="20" value="${X_OBJECT.remark}" onblur="updateRemark('${X_OBJECT.id}',this.value)"/></td>
<td> 
<c:if test="${X_OBJECT.status_freeze == '0'}"><span style="color: red;">已冻结</span></c:if>
<c:if test="${X_OBJECT.status_freeze == '1'}"><span style="color: green;">未冻结</span></c:if>
</td>	
<td>${X_OBJECT.status.chnShortDescription}
<input type="hidden" value="${X_OBJECT.status}" id="${X_OBJECT.id}_status"/>
</td>	
</tr>

