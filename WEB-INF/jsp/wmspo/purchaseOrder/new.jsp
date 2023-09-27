<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
function validatePurchaseOrderForm(){
     var poOrderTxt =document.getElementById('poOrder').value;
	 var poDateTxt =document.getElementById('poDate').value;
	 var supplierIdTxt =document.getElementById('supplier_id').value;
	 var remarkTxt =document.getElementById('remark').value;
	 if(poOrderTxt==""){
		 alert("采购单编号不能为空！");
		 return false;
	 }if(poDateTxt==""){
		 alert("订单日期不能为空！");
		 return false;
	 }if(supplierIdTxt==""){
		 alert("请选择供应商！");
		 return false;
	 }if(remarkTxt==""){
		 alert("请填写备注！");
		 return false;
	 }
	 return true;
}
</script>
<html:form action="/insertPurchaseOrder" onsubmit="return validatePurchaseOrderForm(this)">
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<html:hidden property="createType" value="0" />
		<tr>
 		 <td width="15%" class="bluetext"><bean:message key="purchaseOrder.site"/>:</td>
 		 <td width="35%"><html:select property="site_id" onchange="cascadeSiteDepartment(this.value,'department_id')"><html:options collection="X_SITELIST" property="id" labelProperty="name"/></html:select></td>
		 <td width="15%"  class="bluetext"><bean:message key="purchaseOrder.department"/>:</td>
 		 <td width="35%"><html:select property="department_id" >
 		                   <html:options collection="x_listDept" labelProperty="name" property="id"/>
 		                 </html:select></td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrder.id" />:</td>
		<td><html:text property="poOrder" /></td>
			
		<td class="bluetext"><bean:message key="purchaseOrder.poDate" />:</td>
		<td><html:text property="poDate" size="6" maxlength="10" /><span class="required">*</span>				
			<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg1',false,'poDate',null,null,'purchaseOrderForm')"><img
				align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
		</td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrder.supplierCode" />:</td>
		<td><html:hidden property="supplier_id"/><span id="supplierCode"></span><a href="javascript:selectSupplier();"><img src="images/select.gif" border="0"/></a></td>
		<td class="bluetext"><bean:message key="purchaseOrder.supplierName" />:</td>
		<Td><span id="supplierName"></span></Td>
		</tr>
		<tr>
		<td class="bluetext">
				<bean:message key="purchaseOrder.status" />
			</td>
			<td class="bluetext">
				<bean:message key="purchaseOrder.unclosed" />
			</td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrder.remark" /></td>
		<td  colspan="3">
			<html:textarea property="remark"  cols="60" rows="3"/>
		</td>
		</tr>
		<tr>
		</tr>
	</table>
	<hr width="100%"/>
	<table>
  <tr>
   <td>
   <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="返回"  onclick="history.go(-1)"/></c:if>
   <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Return"  onclick="history.go(-1)"/></c:if>
   </td> 
   <td colspan="0" align="right"><html:submit>
				<bean:message key="all.save" />
			</html:submit></td>
  </tr>
</table>
</html:form>
<script type="text/javascript">
<!--
  
function selectSupplier(){
	var site_id=document.getElementById("site_id").value;
	var v = window.showModalDialog(
			'showDialog.do?title=supplier.selectSupplier.title&selectSupplier.do?site_id='+site_id+'&includeGlobal=true', 
			null, 'dialogWidth:800px;dialogHeight:650px;status:no;help:no;scroll:no');
	if (v) {
			document.getElementById("supplier_id").value=v.id;
			document.getElementById("supplierCode").innerHTML=v.code;
			document.getElementById("supplierName").innerHTML=v.name;
			
		};
}
//-->
</script>