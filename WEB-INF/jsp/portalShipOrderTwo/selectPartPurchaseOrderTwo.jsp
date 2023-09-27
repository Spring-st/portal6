<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script type="text/javascript">
<!--
	 

	function savePurchaseOrder(){
		    var result = [];
	        var fahuoNumber = document.getElementById('fahuoNumber').value;
			var qtyOpen = ${X_OBJECT.qtyOpen};//未结
			if(fahuoNumber >qtyOpen){
				alert("发货数量不能大于未结数量，请重新输入!");
				document.getElementById("fahuoNumber_${X_OBJECT.id}").value ="";
				document.getElementById("fahuoNumber_${X_OBJECT.id}").focus();
				return false;
			}else{
				 
				result['poipItemId'] = "${X_OBJECT.id}";
				result['poipNumber'] = "${X_OBJECT.poip_number.poip_number}";
				result['line'] = "${X_OBJECT.line}";
				result['itemNumberId'] = "${X_OBJECT.itemNumber.id}";
				result['itemNumberName'] = "${X_OBJECT.itemNumber.name}";
				result['supplierItemNumber'] = "${X_OBJECT.supplierItemNumber}";
				result['um'] = "${X_OBJECT.um}";
				result['qtyOpen'] = "${X_OBJECT.qtyOpen}";
				result['fahuoNumber'] = fahuoNumber;
				result['hege'] = "${X_OBJECT.um}";
				result['tuihuo'] = "${X_OBJECT.um}";
				result['duDate'] = "${X_OBJECT.dueDate}";
				result['pici'] = "${X_OBJECT.um}";
			}
				
 
 
	 window.parent.returnValue = result;
	 window.parent.close();
	}
//-->
</script>
<h3 style="color:blue"><bean:message key="purchaseOrder.item"/></h3>

<table border="1" width="100%" cellpadding="2" cellspacing="0">
	<thead>
		<tr bgcolor="#9999ff">
			<th width="9%"><bean:message key="PO.No" /></th>
			<th width="5%"><bean:message key="purchaseOrder.line" /></th>
			<th width="9%"><bean:message key="purchaseOrder.itemCode" /></th>
			<th width="8%"><bean:message key="purchaseOrder.itemName" /></th>
			<th width="8%"><bean:message key="purchaseOrder.supplierItemNumber" /></th>
			<th width="8%"><bean:message key="purchaseOrder.um" /></th>
			<th width="8%"><bean:message key="purchaseOrder.dingdan" /></th>
			<th width="8%"><bean:message key="purchaseOrder.qtyOpen" /></th>
			<th width="8%"><bean:message key="purchaseOrder.receiptQty" /></th>
			<th width="8%"><bean:message key="purchaseOrder.QuantityNumber" /></th>
			<%--<th width="3%"><bean:message key="purchaseOrder.boxCount" /></th>
			<th width="7%"><bean:message key="purchaseOrder.dueDate" /></th>
			<th width="9%"><bean:message key="purchaseOrder.isPrintLabels" /></th>--%>
			<th width="8%"><bean:message key="purchaseOrder.fahuo" /></th>
			<th width="10%"><bean:message key="purchaseOrder.duDate" /></th>
		</tr>
	</thead>
	<tbody id="item_datatable">
		<%--<c:forEach var="X_OBJECT" items="${X_RESULTLIST}">
			<c:set var="X_OBJECT" scope="request" value="${X_OBJECT}"/>
			<jsp:include page="itemRow.jsp" />
		</c:forEach>
	--%>
		<tr>
			<td>${X_OBJECT.poip_number.po_number.poOrder}</td>
			<td>${X_OBJECT.line}</td>
			<td>${X_OBJECT.itemNumber.id}</td>
			<td>${X_OBJECT.itemNumber.name}</td>
			<td>${X_OBJECT.supplierItemNumber}</td>
			<td>${X_OBJECT.um}</td>
			<td><input size="5" type="hidden" value="${X_OBJECT.qty}" class="waitQuantity" />
			    ${X_OBJECT.qty}
			</td>
			<td>${X_OBJECT.qtyOpen}</td>
			<td><input size="5" type="hidden" value="${X_OBJECT.qty-X_OBJECT.qtyOpen}" class="receiptQty" />${X_OBJECT.qty-X_OBJECT.qtyOpen}</td>
		
			<td>${X_OBJECT.receiptQty}</td>
		
		    <%--<td>${X_OBJECT.boxCount}</td>--%>
			<td><input type="text" size="6" id='fahuoNumber'/></td>
			<td>${X_OBJECT.dueDate}</td>

		</tr>
	</tbody>
</table>
<table width="100%">
	<tr><td></td></tr>
	<tr>
		<td align="center">
			<input type="button" value="<bean:message key="all.save" />" onclick="savePurchaseOrder()"/>
		</td>
	</tr>
</table>
<script type="text/javascript">
    applyRowStyle(document.all('item_datatable'));
</script>
