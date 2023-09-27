<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
<!--
	function validatedailyProductPlanForm(from){
		return true;
	}
//-->
</script>
<html:javascript formName="dailyProductPlanForm" staticJavascript="true"/>
<html:form action="/insertDailyProductPlan" onsubmit="return validatedailyProductPlanForm(this)">
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
		</tr>
		<tr>
			<td class="bluetext" width="100px"><bean:message key="dailyproductplan.id" />:</td>
			<td width="100px"><html:text property="workOrderNo"/></td>
			<td class="bluetext" width="100px"><bean:message key="dailyproductplan.workorderid" />:</td>
			<td><html:text property="workOrderId"/></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="dailyproductplan.part" />:</td>
			<td><html:text property="part" /></td>
			<td class="bluetext"><bean:message key="dailyproductplan.ordertype" />:</td>
			<td><html:text property="orderType" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="dailyproductplan.orderattribute" />:</td>
			<td><html:text property="orderAttribute" /></td>
			<td class="bluetext"><bean:message key="dailyproductplan.site" />:</td>
			<td><html:text property="site" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="dailyproductplan.lineno" />:</td>
			<td><html:text property="lineNo" /></td>
			<td class="bluetext"><bean:message key="dailyproductplan.qty" />:</td>
			<td><html:text property="qty" onkeyup="this.value=(this.value.match(/\d+(\.\d{0,4})?/)||[''])[0]" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="dailyproductplan.golinedate" />:</td>
			<td>
				<html:text property="golineDate" readonly="true" size="15" />
				<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg1',false,'golineDate',null,null,'dailyProductPlanForm')"> 
				<img align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /> </a>
			</td>
			<td class="bluetext"><bean:message key="dailyproductplan.offlinedate" />:</td>
			<td>
				<html:text property="offlineDate" readonly="true" size="15" />
				<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg2',false,'offlineDate',null,null,'dailyProductPlanForm')"> 
				<img align="absmiddle" border="0" id="dimg2" src="images/datebtn.gif" /> </a>
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="dailyproductplan.procedurecode" />:</td>
			<td><html:text property="procedureCode" /></td>
			<td class="bluetext"><bean:message key="dailyproductplan.bomcode" />:</td>
			<td><html:text property="bomCode" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="dailyproductplan.shift" />:</td>
			<td><html:text property="shift" /></td>
			<td class="bluetext"><bean:message key="dailyproductplan.bomoutfinish" />:</td>
			<td><html:text property="bomOutFinish" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="dailyproductplan.domain" />:</td>
			<td><html:text property="domain" /></td>
			<td class="bluetext"><bean:message key="dailyproductplan.seqno" />:</td>
			<td><html:text property="seqNo" /></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td colspan="2" align="right">
				<html:submit><bean:message key="all.save" /></html:submit>
			</td>
		</tr>
	</table>
</html:form>
