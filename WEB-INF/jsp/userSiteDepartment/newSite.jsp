<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script type="text/javascript" src="includes/selection.js"></script>
<script type="text/javascript">
<!--
	var siteArray = [];
	var travelGroupArray = [];
	
<logic:iterate id="s" name="X_SITELIST">
	siteArray[siteArray.length] = new Option("${s.name}", "${s.id}");

</logic:iterate>
//-->

	function validateForm(form) {
		
		
		 if (!validateUserSiteForm(form)) return false;
		
	}

	
</script>
<html:javascript formName="userSiteForm" staticJavascript="false" />
<html:form action="insertUserSite.do"  >
<html:hidden property="user_id"/>
<html:hidden property="user_loginName"/>
<table width="100%" border="0" cellpadding="4" cellspacing="0">
<tr>
  <td colspan="2" class="warningMsg"><html:errors/></td>
</tr>
<tr>
  <td class="bluetext"><bean:message key="user.loginName"/>:</td>
  <td><bean:write name="userSiteForm" property="user_loginName"/></td>
</tr>
<tr>
  <td class="bluetext"><bean:message key="userSite.site"/>:</td>
  <td><html:select property="site_id" ></html:select></td>
</tr>
</table>
<hr/>
<table width="90%" border="0" cellpadding="4" cellspacing="0">
<tr>
  <td align="right">
  	<html:submit><bean:message key="all.save"/></html:submit>
  	<input type="button" value="<bean:message key="all.cancel"/>" onclick="window.parent.close();"/>
  </td>
</tr>
</table>
</html:form>
<script type="text/javascript">
	initSelect(userSiteForm.site_id, siteArray,  '<bean:write name="userSiteForm" property="site_id"/>');
</script>