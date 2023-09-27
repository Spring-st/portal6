<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<tr id="r${X_OBJECT.user.id}">
  <td align="center"><a href="javascript:editApprover(${X_OBJECT.user.id})">${X_OBJECT.seq}</a></td>
  <td>${X_OBJECT.user.name}</td>
  <td>
    <span style="color:${X_OBJECT.canModify.color}"><bean:write name="X_OBJECT" property="canModify.${x_lang}ShortDescription"/></span>
  </td>
  <td>${X_OBJECT.superior.name}</td>
  <td>
    <a href="javascript:deleteApprover(${X_OBJECT.user.id})"><bean:message key="all.delete"/></a>
  </td>
</tr>
