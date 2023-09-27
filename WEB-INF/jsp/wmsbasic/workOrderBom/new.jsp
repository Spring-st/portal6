<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<html:javascript formName="workOrderBomForm" staticJavascript="true"/>
<script type="text/javascript">
<!--
	function validateworkOrderBomForm(from){
		return true;
	}
//-->
</script>
<html:form action="/insertWorkOrderBom" onsubmit="return validateworkOrderBomForm(this)">
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.id" />:</td>
			<td><html:text property="workOrderId" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.workorderno" />:</td>
			<td><html:text property="workOrderNo" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.productno" />:</td>
			<td><html:text property="productNo" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.partno" />:</td>
			<td><html:text property="partNo" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.allneedqty" />:</td>
			<td><html:text property="allNeedQty" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.workingorder" />:</td>
			<td><html:text property="workingOrder" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.workingposition" />:</td>
			<td><html:text property="workingPosition" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.singleneedqty" />:</td>
			<td>
				<html:text property="singleNeedQty" onkeyup="this.value=(this.value.match(/\d+(\.\d{0,4})?/)||[''])[0]" />
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.site" />:</td>
			<td>
				<html:text property="site" />
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.domain" />:</td>
			<td>
				<html:text property="domain" />
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="workorderbom.seqno" />:</td>
			<td>
				<html:text property="seqNo" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<html:submit><bean:message key="all.save" /></html:submit>
			</td>
		</tr>
	</table>
</html:form>
