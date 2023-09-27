<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<c:set var="yesNo_yes" value="<%=com.aof.model.metadata.YesNo.YES.getEnumCode()%>"></c:set>
<tr id="r${X_OBJECT.lot.id}"  align="center">
<%--<td><a href='javascript:editItem("${X_OBJECT.id}")'>${X_OBJECT.poip_item_id.poip_number.poip_number}</a></td>--%>
	<td><input type="checkbox" name="ids" value="${X_OBJECT.lot.id}" onclick="setCount();"/>
	 <input type="hidden" id="item${X_OBJECT.lot.id}" value="${X_OBJECT.id}"/>
	<c:if test="${X_OBJECT.isPrint.enumCode==yesNo_yes}">
	<font color="red"><bean:message key='Printed'/>
	</font></c:if>
	</td>
	<td>${X_OBJECT.lot.id}</td>
	<td>${X_OBJECT.po_number}</td>
	<td>${X_OBJECT.psoItem.poipItem.line}</td>
	<td>${X_OBJECT.part.id}</td>
	<td>${X_OBJECT.number}</td>
	<td>${X_OBJECT.isPrint.chnShortDescription}</td>
 
 
</tr>
