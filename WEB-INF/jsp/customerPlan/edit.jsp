<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
<!--
	function validatecustomerPlanForm(from){
		return true;
	}
//-->
</script>
<html:javascript formName="customerPlanForm" staticJavascript="true"/>
<html:form action="/updateCustomerPlan" onsubmit="return validatecustomerPlanForm(this)">
	<html:hidden property="id" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="2">
				<html:messages id="x_message" message="true">${x_messages}<br></html:messages>
			</td>
		</tr>
				<tr>
			<td class="bluetext"><bean:message key="customer_plan.shipmentdate" />:</td>
			<td>
				<html:text property="shipmentDate" readonly="true" size="15" />
				<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg1',false,'shipmentDate',null,null,'customerPlanForm')"> 
				<img align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /> </a>
			</td>
		</tr>
			<tr>
			<td class="bluetext"><bean:message key="customer_plan.entrydate" />:</td>
			<td>
				<bean:write name="customerPlanForm" property="entryDate"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<html:submit><bean:message key="all.save" /></html:submit>
			</td>
		</tr>
	</table>
</html:form>
