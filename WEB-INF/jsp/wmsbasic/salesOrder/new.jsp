<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
<!--
	function validatesalesOrderItemForm(from){
		return true;
	}
//-->
</script>
<html:javascript formName="salesOrderItemForm" staticJavascript="true"/>
<html:form action="/insertSalesOrderItem" onsubmit="return validatesalesOrderItemForm(this)">
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
			<td class="bluetext"><bean:message key="salesorder.id" />:</td>
			<td><html:text property="customerCode" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="salesorder.id" />:</td>
			<td><html:text property="customerCode" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="salesorder.customername" />:</td>
			<td><html:text property="customerName" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="salesorder.lineno" />:</td>
			<td><html:text property="lineNo" onkeyup="this.value=(this.value.match(/\d+(\d{0,0})?/)||[''])[0]" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="salesorder.productno" />:</td>
			<td><html:text property="productNo" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="salesorder.qty" />:</td>
			<td><html:text property="qty" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="salesorder.addresscode" />:</td>
			<td><html:text property="addressCode" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="salesorder.addressdesc" />:</td>
			<td><html:text property="addressDesc" /></td>
		</tr>
	<tr>
			<td class="bluetext"><bean:message key="salesorder.site" />:</td>
			<td>
				<html:text property="site"  />
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="salesorder.outerpackingqty" />:</td>
			<td>
				<html:text property="outerPackingQty"  onkeyup="this.value=(this.value.match(/\d+(\.\d{0,4})?/)||[''])[0]" />
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="salesorder.domain" />:</td>
			<td>
				<html:text property="domain"  />
			</td>
		</tr>
			<tr>
			<td class="bluetext"><bean:message key="salesorder.seqno" />:</td>
			<td>
				<html:text property="seqNo"  />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<html:submit><bean:message key="all.save" /></html:submit>
			</td>
		</tr>
	</table>
</html:form>
