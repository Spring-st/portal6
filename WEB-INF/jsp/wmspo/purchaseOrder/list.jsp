<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>

<script type="text/javascript">
<!--
	 function changeCheck(val,idVal){   
		 
		if(val=="poitem.poip_number.status"){
			document.getElementById("fieldValues_"+idVal).readOnly=true;
	 		document.getElementById("fieldValues_"+idVal).style.background="#CCC";
	 		
	 		document.getElementById("checkStatus_"+idVal).style.display="inline";
		}else{
			document.getElementById("fieldValues_"+idVal).readOnly=false;
	 		document.getElementById("fieldValues_"+idVal).style.background="white";
	 		
	 		document.getElementById("checkStatus_"+idVal).style.display="none";
		}
	}

$(document).ready(function () {
	for(i = 20; i < id; i++){
			var checkStatusTxt = document.getElementById("checkStatus_"+i);
		} 
})

function changeCheckStatus(val,idVal){ 
	document.getElementById("fieldValues_"+idVal).value=val;
}
//-->
</script>
<html:form action="/listPurchaseOrder.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="poitem.poip_number.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="poitem.line" desc="行号"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.id" desc="物料号"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="poitem.poip_number.supplier.code" desc="供应商代码" />
	<input type="hidden" name="fields" value="poitem.poip_number.supplier.name" desc="供应商名称"/>
	<input type="hidden" name="fields" value="poitem.poip_number.createDate" desc="订单时间"/>
	<input type="hidden" name="fields" value="poitem.qty" desc="订单数量"/>
	<input type="hidden" name="fields" value="poitem.receiptQty" desc="收货数量"/>
	<input type="hidden" name="fields" value="poitem.inventoryNumber" desc="入库数量" />
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<input type="hidden" id="statusCheck" name="statusCheck"/>

	<table width=100% border="0" cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="listPurchaseOrder.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%"><page:order order="id" style="text-decoration:none">
					 采购单号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order></th>
				    <th>行号</th>
				    <th>物料号</th>
				    <th>DPI</th>
				    <th>原厂编号</th>
				    <th>描述一</th>
				    <th>描述二</th>
				    <th>供应商代码</th>
				    <th>供应商名称</th>
				    <th>订单时间</th>
				    <th>订单数量</th>
				    <th>收货数量</th>
				    <th>入库数量</th>
				    <th>状态</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

