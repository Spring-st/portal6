<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<c:if test="${X_OBJECT.isPrint=='0'}">
 <td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();" disabled="disabled"/></td>
 </c:if>
 
 <c:if test="${X_OBJECT.isPrint=='1' || X_OBJECT.isPrint==null}">
 
 <td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
 </c:if>
<td>${X_OBJECT.part.id}</td>
<td>${X_OBJECT.part.describe1}</td>
<td>${X_OBJECT.part.describe2}</td>
<td>${X_OBJECT.location.code}</td>
<td>${X_OBJECT.qty}</td>
<td>${X_OBJECT.userId.loginName}</td>
<td>${X_OBJECT.date}</td>
<c:if test="${X_OBJECT.isPrint =='0'}">
	<td><font color="red">已打印</font></td>
</c:if>
<c:if test="${X_OBJECT.isPrint =='1' || X_OBJECT.isPrint == null}">
	<td><font color="blue">未打印</font></td>
</c:if>

</tr>

