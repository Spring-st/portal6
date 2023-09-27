<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}">
	<td align="center"><input type="checkbox" name="ids" value="${X_OBJECT.id}" id="cbox${X_OBJECT.id}" onclick="check()" /></td>
	<td align="center">${X_OBJECT.box.po_supp}
		<input type="hidden" id="s${X_OBJECT.id}" value="${X_OBJECT.box.po_supp}" />
	</td>
	<td align="center">${X_OBJECT.box.lot.id}</td>	
	<td align="center">${X_OBJECT.box.part.id}</td>
	<td align="center">${X_OBJECT.box.part.oldCode}</td>
	<td align="center">${X_OBJECT.date}</td>
	<td align="center">${X_OBJECT.createUser.loginName}</td>
	<td align="center">${X_OBJECT.qty}</td>
	<td align="center">${X_OBJECT.location.code}</td>
	<td align="center">
		<c:if test="${X_OBJECT.box.status_rqc==null}">待检</c:if>
		<c:if test="${X_OBJECT.box.status_rqc!=null}">${X_OBJECT.box.status_rqc.chnShortDescription}</c:if>
	</td>
	<td align="center">
		<table>
			<c:forEach items="${X_OBJECT.box.unqualifiedList}" var="item" varStatus="status">
				<tr>
					<td>${item.reasons.describe}</td>
				</tr>
			</c:forEach>
		</table>
	</td>
	<td align="center">${X_OBJECT.box.remark}</td>
	<td align="center">
		<c:if test="${X_OBJECT.isPrint == '0'}">
			<span style="color: red;">已打印</span>
		</c:if>
		<c:if test="${X_OBJECT.isPrint != '0'}">
			未打印
		</c:if>
	</td>	
</tr>
