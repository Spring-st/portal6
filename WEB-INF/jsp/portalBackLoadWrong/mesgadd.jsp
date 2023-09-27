<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<logic:iterate id="X_OBJECT" name="msg">
<span style="font-size: 18px;"><bean:message key="systemMessage.approve.${X_OBJECT.key}" /><td>：${X_OBJECT.num}</td></span>
</logic:iterate>
<table>
<input type="button"  onclick="history.go(-1)" value="返回"/>
</table>
