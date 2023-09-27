<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script type="text/javascript">
<!--
	function printPartCode(id,numbers,isprint){
		//alert(numbers);
		
		var number = document.getElementById(numbers).value;
		if(number.length>0){
			
			if(isprint){
				var r=confirm("已完成打印,继续补打?") 		
			 	if (r==true) 
		     	{ 
				   window.location="prtinPurchasePartCode.do?id="+id+"&number="+number;
		    	}
			}else{
				window.location="prtinPurchasePartCode.do?id="+id+"&number="+number;
			}
			
		}else{
			alert("请输入打印标签的数量!");
			//document.getElementById("printnum").focus();
		}
	
}
//-->
</script>
<h3 style="color:blue"><bean:message key="purchaseOrder.item"/></h3>
<table border="1" width="100%" cellpadding="2" cellspacing="0">
	<thead>
		<tr bgcolor="#9999ff">
			<%--<th width="7%"><bean:message key="purchaseOrder.id" /></th>	--%>
			<th width="9%"><bean:message key="serial.number" /></th>
			<th width="5%"><bean:message key="purchaseOrder.line" /></th>
			<th width="7%"><bean:message key="purchaseOrder.itemCode" /></th>
			<th width="10%"><bean:message key="purchaseOrder.itemName" /></th>
			<th width="10%"><bean:message key="purchaseOrder.supplierItemNumber" /></th>
			<%--<th width="2%"><bean:message key="purchaseOrder.um" /></th>--%>
			<th width="6%"><bean:message key="purchaseOrder.qty" /></th>
			<th width="6%"><bean:message key="purchaseOrder.qtyOpen" /></th>
			<th width="6%"><bean:message key="purchaseOrder.receiptQty" /></th>
			<th width="6%"><bean:message key="purchaseOrder.QuantityNumber" /></th>
			<%--<th width="3%"><bean:message key="purchaseOrder.boxCount" /></th>
			<th width="7%"><bean:message key="purchaseOrder.dueDate" /></th>
			<th width="9%"><bean:message key="purchaseOrder.isPrintLabels" /></th>--%>
			<th width="9%"><bean:message key="the.delivery.date" /></th>
			<th width="5%"><bean:message key="print" /></th>
			<th width="10%"></th>
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
    applyRowStyle(document.all('item_datatable'));
</script>
