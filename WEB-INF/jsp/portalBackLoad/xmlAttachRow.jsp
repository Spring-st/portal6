<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<tr id="r${X_OBJECT.id}">
		<%--<td width="10%" align="center">
			${X_OBJECT.id }
		</td>
		--%><td width="30%" align="center">
				<a href="downloadXmlAttach.do?id=${X_OBJECT.id}">${X_OBJECT.fileName}</a>
		</td>
			<td width="30%">
				${X_OBJECT.createUser.name}
			</td>
			<td width="30%">
				<c:if test="${X_OBJECT.uploadDate!= null }">
				<bean:write name="X_OBJECT" property="uploadDate" format="yyyy/MM/dd" />
				</c:if>
			</td>
</tr>
