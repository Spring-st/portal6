<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<tr id="r${X_OBJECT.type.enumCode}">
  <td>
    <span style="color:${X_OBJECT.type.color}"><bean:write name="X_OBJECT" property="type.${x_lang}Description"/></span>
    <span style="color:${X_OBJECT.compareType.color}"><bean:write name="X_OBJECT" property="compareType.${x_lang}Description"/></span>
    ${X_OBJECT.displayValue}
  </td>
  <td>
    <a href="javascript:editCondition(${X_OBJECT.type.enumCode})"><bean:message key="all.modify"/></a>
  </td>
  <td>
    <a href="javascript:deleteCondition(${X_OBJECT.type.enumCode})"><bean:message key="all.delete"/></a>
  </td>
</tr>
