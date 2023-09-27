<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<tr id="r${X_OBJECT.id}">
		<td width="30%" align="center">
				<%--<a href="viewPortalStatusSurveyAttach.do?id=${X_OBJECT.id}">${X_OBJECT.fileName}</a>
				--%><c:if test="${X_OBJECT.editable}">
				<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.fileName}</a>
				</c:if>
				<c:if test="${!X_OBJECT.editable}">
							<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.fileName}</a>
				</c:if>
		</td>
			<td width="30%">
				${X_OBJECT.createUser.name}
			</td>
			<td width="30%">
				<c:if test="${X_OBJECT.uploadDate!= null }">
				<bean:write name="X_OBJECT" property="uploadDate" format="yyyy/MM/dd" />
				</c:if>
			</td>
			<td width="30%">
				<c:if test="${X_OBJECT.status!=null}">
				<span style="color:${X_OBJECT.status.color}">
				<bean:write name="X_OBJECT" property="status.${x_lang}ShortDescription" /> </span>
				</c:if>
			</td>
</tr>
