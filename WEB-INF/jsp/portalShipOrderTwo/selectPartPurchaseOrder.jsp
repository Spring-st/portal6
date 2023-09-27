<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script type="text/javascript">
<!--
	 

	function savePurchaseOrder(){
				var result = [];
				
	  <c:forEach var="X_OBJECT" items="${X_RESULTLIST}"  varStatus="status">
			var fahuoNumber = document.getElementById("fahuoNumber_${X_OBJECT.id}").value;  
			var qtyOpen = ${X_OBJECT.qtyOpen};//未结
			if(fahuoNumber >qtyOpen){
				alert("发货数量不能大于未结数量，请重新输入!");
				document.getElementById("fahuoNumber_${X_OBJECT.id}").value ="";
				document.getElementById("fahuoNumber_${X_OBJECT.id}").focus();
				return false;
			}else{
				result[${status.index}]=[];
				result[${status.index}]['poipItemId'] = "${X_OBJECT.id}";
				result[${status.index}]['poipNumber'] = "${X_OBJECT.poip_number.poip_number}";
				result[${status.index}]['line'] = "${X_OBJECT.line}";
				result[${status.index}]['itemNumberId'] = "${X_OBJECT.itemNumber.id}";
				result[${status.index}]['itemNumberName'] = "${X_OBJECT.itemNumber.name}";
				result[${status.index}]['supplierItemNumber'] = "${X_OBJECT.supplierItemNumber}";
				result[${status.index}]['um'] = "${X_OBJECT.um}";
				result[${status.index}]['qtyOpen'] = "${X_OBJECT.qtyOpen}";
				result[${status.index}]['fahuoNumber'] = fahuoNumber;
				result[${status.index}]['hege'] = "${X_OBJECT.um}";
				result[${status.index}]['tuihuo'] = "${X_OBJECT.um}";
				result[${status.index}]['duDate'] = "${X_OBJECT.dueDate}";
				result[${status.index}]['pici'] = "${X_OBJECT.um}";
			}
				
	  </c:forEach>
	  //alert(result[0]['id']);
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
		<c:forEach var="X_OBJECT" items="${X_RESULTLIST}">
			<c:set var="X_OBJECT" scope="request" value="${X_OBJECT}"/>
			<jsp:include page="itemRow.jsp" />
		</c:forEach>
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
