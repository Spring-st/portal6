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
<html:form action="/updateSalesOrderItem" onsubmit="return validatesalesOrderItemForm(this)">
	<html:hidden property="id" />
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4">
				<html:messages id="x_message" message="true">${x_messages}<br></html:messages>
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.id" />:</td>
			<td><bean:write name="salesOrderForm" property="id" /></td>
			<td><bean:message key="salesOrderItem.soipnumber" />:</td>
			<td><html:text property="soipNumber" value="${X_OBJECT.soipNumber}" /></td>
		</tr>
		<tr>
			<td><bean:message key="salesOrderItem.line" />:</td>
			<td><html:text property="line" value="${X_OBJECT.line}" /></td>
			<td><bean:message key="salesOrderItem.itemnumber" />:</td>
			<td><html:text property="itemNumber" value="${X_OBJECT.itemNumber}" /></td>
		</tr>
		<tr>
			<td><bean:message key="salesOrderItem.supplieritemnumber" />:</td>
			<td><html:text property="supplierItemNumber" value="${X_OBJECT.supplierItemNumber}" /></td>
			<td><bean:message key="salesOrderItem.um" />:</td>
			<td><html:text property="um" value="${X_OBJECT.um}" /></td>
		</tr>
		<tr>
			<%--<td class="bluetext"><bean:message key="salesorder.lineno" />:</td>
			<td><html:text property="lineNo" onkeyup="this.value=(this.value.match(/\d+(\d{0,0})?/)||[''])[0]" /></td>
		--%>
			<td><bean:message key="salesOrderItem.rum" />:</td>
			<td><html:text property="rum" value="${X_OBJECT.rum}" /> </td>
			<td><bean:message key="salesOrderItem.qty" />:</td>
			<td><html:text property="qty" value="${X_OBJECT.qty}" /> </td>
		</tr>
		<tr>
			<td><bean:message key="salesOrderItem.status" />:</td>
			<td><html:text property="status" value="${X_OBJECT.status}" /> 
				<%--<html:select property="status" >
				<html:option value="0">打开</html:option>
				<html:option value="1">关闭</html:option>
				</html:select>
			--%></td>
			<td><bean:message key="salesOrderItem.duedate" />:</td>
			<td><html:text property="dueDate" value="${X_OBJECT.dueDate}" /> </td>
		</tr>
		<tr>
			<td><bean:message key="salesOrderItem.receiptqty" />:</td>
			<td><html:text property="receiptQty" value="${X_OBJECT.receiptQty}" /> </td>
			<td><bean:message key="salesOrderItem.isprintlabels" />:</td>
			<td><html:text property="isPrintLabels" value="${X_OBJECT.isPrintLabels}" /> </td>
		</tr>
		<tr>
			<td><bean:message key="salesOrderItem.boxcount" />:</td>
			<td><html:text property="boxcount" value="${X_OBJECT.boxcount}" /> </td>
			<td><bean:message key="salesOrderItem.describe" />:</td>
			<td><html:text property="describe" value="${X_OBJECT.describe}" /> </td>
		</tr>
		<tr>
			<td><bean:message key="salesOrderItem.returnqtysum" />:</td>
			<td><html:text property="returnQtySum" value="${X_OBJECT.returnQtySum}" /> </td>
			<td><bean:message key="salesOrderItem.conversionratio" />:</td>
			<td><html:text property="conversionRatio" value="${X_OBJECT.conversionRatio}" /> </td>
		</tr>
		<tr>
			<td><bean:message key="salesOrderItem.parttype" />:</td>
			<td><html:text property="partType" value="${X_OBJECT.partType}" /> </td>
			<td><bean:message key="salesOrderItem.factory" />:</td>
			<td><html:text property="factory" value="${X_OBJECT.factory}" /> </td>
		</tr>
		<tr>
			<%--<td class="bluetext"><bean:message key="salesorder.outerpackingqty" />:</td>
			<td>
				<html:text property="outerPackingQty" onkeyup="this.value=(this.value.match(/\d+(\.\d{0,4})?/)||[''])[0]" />
			</td>
		--%>
			<td><bean:message key="salesOrderItem.podomain" />:</td>
			<td><html:text property="poDomain" value="${X_OBJECT.poDomain}" /> </td>
			<td><bean:message key="salesOrderItem.site" />:</td>
			<td><html:text property="site" value="${X_OBJECT.site.id}" /> 
			</td>
		</tr>
		<tr>
			<td><bean:message key="salesOrderItem.issync" />:</td>
			<td><html:select property="issync">
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
					<html:options collection="X_YESNOLIST"
						property="enumCode" labelProperty="chnShortDescription" />
				</c:if>
				<c:if test="${sessionScope.LOGIN_USER.locale!='zh'}">
					<html:options collection="X_YESNOLIST"
						property="enumCode" labelProperty="engShortDescription" />
				</c:if>
			</html:select></td>
			<td><bean:message key="salesOrderItem.istxt" />:</td>
			<td><html:select property="istxt">
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
					<html:options collection="X_YESNOLIST"
						property="enumCode" labelProperty="chnShortDescription" />
				</c:if>
				<c:if test="${sessionScope.LOGIN_USER.locale!='zh'}">
					<html:options collection="X_YESNOLIST"
						property="enumCode" labelProperty="engShortDescription" />
				</c:if>
			</html:select></td>
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
