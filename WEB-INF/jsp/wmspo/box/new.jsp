<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">

</script>
<html:form action="/insertPurchaseOrderBox" >
	<table width=90% border=0 cellpadding=4 cellspacing=0>
<tr>
			<td class="bluetext"><bean:message key="storageLocation.wmsTool" />:</td>
			<td><html:text property="wmsTool" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td colspan="4" align="right"><html:submit>
				<bean:message key="all.save" />
			</html:submit></td>
		</tr>
	</table>
</html:form>
<script type="text/javascript">
<!--

//-->
</script>