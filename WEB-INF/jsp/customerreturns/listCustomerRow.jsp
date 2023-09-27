<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<%-- <td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a></td> --%>
	<td>${X_OBJECT.code}</td>
	<td>${X_OBJECT.name1}</td>
	<td>${X_OBJECT.name2}</td>
	<td>${X_OBJECT.address}</td>
	
	<td>${X_OBJECT.address2}</td>
	<td>${X_OBJECT.address3}</td>
	<td>${X_OBJECT.country}</td>
	<td>${X_OBJECT.city}</td>
	<td>${X_OBJECT.fax}</td>
	<td>${X_OBJECT.postId}</td>
	<td>${X_OBJECT.currencyType}</td>
	
	<td>${X_OBJECT.contacts}</td>
	<td>${X_OBJECT.phone}</td>
	<td>${X_OBJECT.email}</td>
	<td>${X_OBJECT.product}</td>
	<td>${X_OBJECT.site}</td>
	<td>${X_OBJECT.domain}</td>
	<td>${X_OBJECT.remarks}</td>
	<td>
		<c:if test="${x_lang eq 'chn'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
		<c:if test="${x_lang eq 'eng'}">${X_OBJECT.enabled.engShortDescription}</c:if>
	</td>
	<td><a href='javascript:select("${X_OBJECT.id}","${X_OBJECT.code}","${X_OBJECT.name1}", "${X_OBJECT.name2}","${X_OBJECT.address}",
	"${X_OBJECT.address2}","${X_OBJECT.address3}","${X_OBJECT.country}","${X_OBJECT.city}","${X_OBJECT.fax}","${X_OBJECT.postId}",
	"${X_OBJECT.currencyType}","${X_OBJECT.contacts}","${X_OBJECT.phone}","${X_OBJECT.email}","${X_OBJECT.product}","${X_OBJECT.site}",
	"${X_OBJECT.domain}","${X_OBJECT.remarks}","${X_OBJECT.enabled.chnShortDescription}");'>
 	<bean:message key="all.select"/></a></td>
</tr>
