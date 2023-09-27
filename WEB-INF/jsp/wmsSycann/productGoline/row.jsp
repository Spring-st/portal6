<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="s${X_OBJECT.id}" align="center">
	<td>
		<input type="checkbox" name="cid" onclick="cidCheck(this)" value="${X_OBJECT.id}"/>
		<input type="hidden" id="qty${X_OBJECT.id}" value="${X_OBJECT.qty}"/>
	</td>
	<td>
		${X_OBJECT.totalNumber}
	</td>
	<td>
		${X_OBJECT.hncCode}
	</td>
	<td>
		${X_OBJECT.partHubCode}
	</td>
	<td>
		${X_OBJECT.partTireCode}
	</td>
	<td>
		${X_OBJECT.partValvestemCode}
	</td>
	<td>
		${X_OBJECT.qty}
	</td>
	<%--<td>
		
	</td>--%>
</tr>

