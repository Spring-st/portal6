<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<tr id="r${X_OBJECT.id}">
		<td width="10%" align="center">
				${X_OBJECT.id}
				
		</td>
			<td width="25%">
				${X_OBJECT.name}
			</td>
			<td align="center"><c:if test="${X_OBJECT.month1==true}">√</c:if></td>
			<td align="center"><c:if test="${X_OBJECT.month2==true}">√</c:if></td>
			<td align="center"><c:if test="${X_OBJECT.month3==true}">√</c:if></td>
			<td align="center"><c:if test="${X_OBJECT.month4==true}">√</c:if></td>
			<td align="center"><c:if test="${X_OBJECT.month5==true}">√</c:if></td>
			
			<td align="center"><c:if test="${X_OBJECT.month6==true}">√</c:if></td>
			<td align="center"><c:if test="${X_OBJECT.month7==true}">√</c:if></td>
			<td align="center"><c:if test="${X_OBJECT.month8==true}">√</c:if></td>
			<td align="center"><c:if test="${X_OBJECT.month9==true}">√</c:if></td>
			<td align="center"><c:if test="${X_OBJECT.month10==true}">√</c:if></td>
			
			<td align="center"><c:if test="${X_OBJECT.month11==true}">√</c:if></td>
			<td align="center"><c:if test="${X_OBJECT.month12==true}">√</c:if></td>
			
</tr>
