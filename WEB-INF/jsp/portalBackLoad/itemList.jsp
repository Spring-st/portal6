<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script type="text/javascript">
<!--
	function addItem() {
		v = window.showModalDialog(
			'showDialog.do?title=PurchaseOrderInspectionPendingItem.new.title&newPurchaseOrderInspectionPendingItem.do?poid=${x_purchaseOrderInspectionPending.id}' , 
			null, 'dialogWidth:450px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('item_datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function editItem(id) {
		v = window.showModalDialog(
			'showDialog.do?title=PurchaseOrderInspectionPendingItem.edit.title&editPurchaseOrderInspectionPendingItem.do?id=' + id , 
			null, 'dialogWidth:450px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
		};
	}
	
	function deleteItem(id) {
		v = window.showModalDialog(
			'showDialog.do?title=PurchaseOrderInspectionPendingItem.edit.title&deletePurchaseOrderInspectionPendingItem.do?id=' + id , 
			null, 'dialogWidth:400px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('item_datatable');
			deleteRow(document.all('r' + id));
			applyRowStyle(table);
		};
	}
	
	function save(){
	  document.getElementById("types").value=0;
      purchaseOrderInspectionPendingForm.submit();
    }
	
	function affirm(){
	 document.getElementById("types").value=1;
	 purchaseOrderInspectionPendingForm.submit();
	}
	function produce(){
    	var poipItemText = $(".boxNumber");
		var waitQuantityText = $(".waitQuantity");
		var receiptQtyText = $(".receiptQty");
		for(var i = 0; i < poipItemText.length; i++){
			var poipItemTexts=parseInt(poipItemText[i].value);
			var waitQuantityTexts=parseInt(waitQuantityText[i].value);
			var receiptQtyTexts=parseInt(receiptQtyText[i].value);
			//alert((poipItemTexts+receiptQtyTexts)+"|"+waitQuantityTexts);
       	 if((poipItemTexts+receiptQtyTexts)>waitQuantityTexts){
        	alert("接收数量已超出范围！");
        	return false; 
       	 }
	  	}	  
			var r=confirm("确认生成收货单?") 		
	  	 if (r==true) 
     	 { 
   			 purchaseOrderInspectionPendingForm.submit();
      	} 
			return true;
	 
    }
	function back(){
		
		window.location.href="listPurchaseOrderInspectionPending.do";
		
	 
    }
	function check(){
		
		if(document.getElementById("qty").value<document.getElementById("id").value){
        alert("请输入数字！");
        document.getElementById("id").focus();
        return false;
    }
    return true;
	}
//-->
</script>
<h3 style="color:blue"><bean:message key="purchaseOrder.item"/></h3>
<table class="data">
	<thead>
		<tr bgcolor="#9999ff">
			<th width="10%"><bean:message key="purchaseOrder.id" /></th>	
			<th width="2%"><bean:message key="purchaseOrder.line" /></th>
			<th width="7%"><bean:message key="purchaseOrder.itemCode" /></th>
			<th width="10%"><bean:message key="purchaseOrder.itemName" /></th>
			<th width="5%"><bean:message key="purchaseOrder.supplierItemNumber" /></th>
			<th width="2%"><bean:message key="purchaseOrder.um" /></th>
			<th width="7%"><bean:message key="purchaseOrder.dueDate" /></th>
			<th width="5%"><bean:message key="purchaseOrder.qty" /></th>
			<th width="5%"><bean:message key="purchaseOrder.qtyOpen" /></th>
			<th width="5%"><bean:message key="purchaseOrder.receiptQty" /></th>
			
			<th width="5%"><bean:message key="purchaseOrder.transitNumber" /></th>
			<th width="5%"><bean:message key="purchaseOrder.QuantityNumber" /></th>			
			
			<th width="5%"><bean:message key="purchaseOrder.returnNumber" /></th>	
			<th width="5%"><bean:message key="purchaseOrder.replacementNumber" /></th>	
		</tr>
	</thead>
	<tbody id="item_datatable">
		<c:forEach var="X_OBJECT" items="${x_purchaseOrderInspectionPendingItemList}">
			<c:set var="X_OBJECT" scope="request" value="${X_OBJECT}"/>
			<jsp:include page="itemRow.jsp" />
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript">
<!--
    applyRowStyle(document.all('item_datatable'));
//-->
</script>
