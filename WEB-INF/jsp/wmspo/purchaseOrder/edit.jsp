<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
 
<html:form action="/updatePurchaseOrder" onsubmit="return validatePurchaseOrderForm(this)">
	<html:hidden property="id" />
	<html:hidden property="department_id" />
	<html:hidden property="createType" />
	<html:hidden property="supplier_id" />
	<html:hidden property="status" />
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
 		 <td width="15%" class="bluetext"><bean:message key="purchaseOrder.site"/>:</td>
 		 <td width="35%">${x_purchaseOrder.site.name}</td>
		 <td width="15%"  class="bluetext"><bean:message key="purchaseOrder.department"/>:</td>
 		 <td width="35%">${x_purchaseOrder.department.name}</td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrder.id" />:</td>
			<td>${x_purchaseOrder.poOrder}</td>
			<td class="bluetext"><bean:message key="purchaseOrder.poDate" />:</td>
			<td>${x_purchaseOrder.poDate}</td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrder.supplierCode" />:</td>
		<td>${x_purchaseOrder.supplier.code}</td>
		<td class="bluetext"><bean:message key="purchaseOrder.supplierName" />:</td>
		<Td>${x_purchaseOrder.supplier.name}</Td>
		</tr>
		<tr>
		<td class="bluetext">
				<bean:message key="purchaseOrder.status" />
			</td>
			<td >
				${x_purchaseOrder.status.chnShortDescription}
			</td>
		<td class="bluetext"><bean:message key="purchaseOrder.qadStatus" /></td>
		<td  >
		${x_purchaseOrder.describe}
		</td>
		</tr>
		<tr >
		<td class="bluetext"><bean:message key="purchaseOrder.remark" /></td>
		<td  colspan="3">
		${x_purchaseOrder.remark}
	     </td>
		</tr>
		<tr>
			<td class="bluetext">
				是否委外加工
			</td>
			<Td>
				${x_purchaseOrder.isEntrust.chnShortDescription}
			</Td>
		</tr>
		<tr>
			<td colspan="4" align="right">
			<c:if test="${x_edit}">
            <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="确认" onclick="submitAFFIRM()"/></c:if>
  			<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="confirm" onclick="submitAFFIRM()"/></c:if>
			<!--
			<input type="button" value="<bean:message key="purchaseOrder.closed" />" onclick="closeds2()"/>-->
			</c:if>
			</td>
		</tr>
	</table>
	<hr width="100%"/>
	 
</html:form>
<script type="text/javascript">
	function submitAFFIRM(){
		window.location.href="closedsPurchaseOrder.do?id=${x_purchaseOrder.id}";
	}

</script>