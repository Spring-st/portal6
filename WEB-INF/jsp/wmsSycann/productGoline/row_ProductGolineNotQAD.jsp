<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr>
	<td>${X_OBJECT.xxsh_worc_item }</td>
	<td>${X_OBJECT.xxsh_worc_qty }</td>
	<fmt:formatDate  var="xxsh_worc_date" value="${X_OBJECT.xxsh_worc_date}" pattern="yyyy-MM-dd"/>
	<td>${xxsh_worc_date }</td>
	<td>${X_OBJECT.xxsh_worc_up_date }</td>
	<td>${X_OBJECT.xxsh_worc_rmks }</td>
</tr>

