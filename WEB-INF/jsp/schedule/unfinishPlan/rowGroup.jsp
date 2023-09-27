<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td>
		${X_OBJECT.asnNo}
	</td>
	<td>
		${X_OBJECT.supplier}
	</td>
	<td>
		<a href='javascript:listView("${X_OBJECT.id}")'>${X_OBJECT.uploadFileName}</a>
	</td>	
	<td>
		${X_OBJECT.uploadTime}
	</td>
	<td>
		${X_OBJECT.uploader}
	</td>
</tr>

