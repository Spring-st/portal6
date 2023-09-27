<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<c:forEach var="str" items="${msg}">
<option value="${str}">
<span style="font-size: 18px;">${str}</span>
</option>
</c:forEach>
<table>
<input type="button"  onclick="history.go(-1)" value="返回"/>
</table>

