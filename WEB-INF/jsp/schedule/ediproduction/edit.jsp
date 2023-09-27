<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script>
	function validateForm(form)
	{
		if(!validateEdiProductionForm(form))
		{
			return false;
		}
		return true;
	}
</script>
<html:javascript formName="ediProductionForm" staticJavascript="false" />
<html:form action="/updateEdiProduction" styleId="ediProduction" onsubmit="return validateForm(this)">
	<html:hidden property="id" />
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="ediproduction.asnno" />:</td>
			<td><html:text property="asnNo" /><html:hidden property="enabled" value="0"/><span class="required">*</span></td>
		</tr>
		<%--<tr>
			<td class="bluetext"><bean:message key="ediproduction.models" />:</td>
			<td><html:text property="models" /><span class="required">*</span></td>
		</tr>--%>
		<tr>
			<td class="bluetext"><bean:message key="ediproduction.productlinecode" />:</td>
			<td><html:text property="productlinecode" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="ediproduction.shiftcode" />:</td>
			<td><html:text property="shiftcode" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="ediproduction.staffcode" />:</td>
			<td><html:text property="staffcode" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="ediproduction.number" />:</td>
			<td><html:text property="number" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="ediproduction.taskdate" />:</td>
			<td>
			<html:text property="taskDate" maxlength="10"/>
				<a onclick="event.cancelBubble=true;" 
					href="javascript:showCalendar('dimg1',false,'taskDate',null,null,'ediProductionForm')">
					<img align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif"/></a>
			<span class="required">*</span>
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="ediproduction.time" />:</td>
			<td><html:text property="time" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="ediproduction.qty" />:</td>
			<td><html:text property="qty" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><html:submit>
				<bean:message key="all.save" />
			</html:submit></td>
		</tr>
	</table>
</html:form>
