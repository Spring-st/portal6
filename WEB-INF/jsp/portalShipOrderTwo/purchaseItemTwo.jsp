<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>

<script type="text/javascript">

function view(id) {
	var url = "viewBySupplierPurchaseOrder.do?id=" + id;
	window.location.href = url;
}

 	function generate(code){
 		//var fahuoNumber = document.getElementById('fahuoNumber_${X_OBJECT.id}').value;
 		var check = document.getElementsByName('ids');
		var result = new Array();
		var j=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked == true){
				check[i].disabled = true;
				//alert(check[i].value+":"+check[i].checked);
				var id=check[i].value;
				var deliverNumber = document.getElementById('td_fahuoNumber'+id).value;
				var qtyOpen = document.getElementById('td_qtyOpen'+id).innerHTML;
				var map = [];
				map['id'] = id;
				map['code'] = code;
				map['poipItemId'] = document.getElementById('td_poId'+id).value;
				map['poipNumber'] = document.getElementById('td_poOrder'+id).innerHTML;
				map['line'] = document.getElementById('td_line'+id).innerHTML;
				map['itemNumberId'] = document.getElementById('td_itemNumberId'+id).innerHTML;
				map['itemNumberName'] = document.getElementById('td_itemNumberName'+id).innerHTML;
				map['supplierItemNumber'] = document.getElementById('td_supplierPartCode'+id).innerHTML;
				map['um'] = document.getElementById('td_unit'+id).innerHTML;
				map['qtyOpen'] = qtyOpen;
				map['fahuoNumber'] = deliverNumber;
				map['hege'] = "";
				map['tuihuo'] = "";
				map['duDate'] = document.getElementById('td_dueDate'+id).innerHTML;
				//map['pici'] = "";
				result[j]=map;
				j++;
				//alert(deliverNumber);
				//alert(deliverNumber+":"+qtyOpen);
				if(parseFloat(deliverNumber) >qtyOpen){
					alert("发货数量不能大于未结数量，请重新输入!");
					document.getElementById("td_fahuoNumber"+id).value ="";
					document.getElementById("td_fahuoNumber"+id).focus();
					return false;
				}
				//alert(document.getElementsByName('cbox"+id+"').length+"123");
				//$("input[name='cbox"+id+"']").attr('disabled','disabled');
			}
			
		}
		var ss  = document.getElementsByName("poipItemIds");
			
			for(var i=0;i<ss.length;i++){
				$("input[name='cbox"+ss[i]+"']").attr('disabled','disabled');
				
			}
		//alert(result.length);
		if(j==0){
				alert("请选择采购单!");
		  		return false;
		}
		
		//result['list'] = list;
		
		
		//result['poOrder'] = poOrder;
		window.parent.returnValue = result;
		window.parent.close();
		 
	 }

 	function printExcel(){
		window.location.href = "listPurchase.do?isExcel=true";
	}
</script>
<html:form action="listPurchaseItemTwo" >

	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
		<%--<td class="bluetext">
			<bean:message key="supplier"/>:
		</td>
		<td>
			<html:text property="supplier" size="15" />
		</td>--%>
		<td class="bluetext">
					<bean:message key="order.number"/>:
		</td>
		<td><html:text property="poip_number" size="15" /></td>
		<td class="bluetext">
			<bean:message key="Status"/>:
		</td>
		<td>
			<html:select property="status">
			<html:option value=""><bean:message key="all.all"/></html:option>
			<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><html:options collection = "X_WmsPurchaseOrderStatusLIST" property = "enumCode" labelProperty = "engShortDescription"/></c:if>
			<c:if test="${sessionScope.LOGIN_USER.locale!='en'}"><html:options collection = "X_WmsPurchaseOrderStatusLIST" property = "enumCode" labelProperty = "chnShortDescription"/></c:if>
			</html:select>
			</td>
		</tr>
		 <tr>
			<td class="bluetext"><bean:message key="purchaseOrder.itemCode"/>:
			</td>
			<td><html:text property="itemNumber" size="15"/></td>
			<td class="bluetext">
				<bean:message key="due.time"/>:
			</td> 
			<td>
			<html:text property="dueDate" size="15" />
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg2',false,'dueDate',null,null,'purchaseOrderInspectionPendingItemQueryForm')"><img
				align="absmiddle" border="0" id="dimg2"
			src="images/datebtn.gif" /> </a>
			</td>
		</tr>
		<%--<tr>
			<td class="bluetext">
				<bean:message key="create.time"/>:
			</td> 
			<td>
			<html:text property="createDate" size="15" />
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg3',false,'createDate',null,null,'purchaseOrderInspectionPendingItemQueryForm')"><img
				align="absmiddle" border="0" id="dimg3"
			src="images/datebtn.gif" /> </a>
			</td>
			<td colspan="2"></td>
		</tr>
		--%><tr>
			<td colspan="2"></td>
			<td align="right">
			<input type="submit" value="<bean:message key="all.query"/>" />
			<%--<input type="button" onclick="printExcel();" value="<bean:message key="page.export.excel"/>" />
			--%></td>
			<td align="center">
				<%--<input type="button" onclick="generate()" value="<bean:message key="to.generate.the.invoice"/>" />
			--%></td>
		</tr>
		<tr><td colspan="4"><hr/></td></tr>
	</table>

</html:form>
<page:form action="listPurchaseItemTwo.do" method="post">

	<jsp:include page="../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr  bgcolor="#9999ff">
			<th width="4%"></th>
			<th width="10%"><bean:message key="PO.No"/></th>
			<th width="10%"><bean:message key="supplier"/></th>
			<th width="8%"><bean:message key="supplier.code"/></th>
			<th width="2%"><bean:message key="purchaseOrder.line" /></th>
			<th width="10%"><bean:message key="purchaseOrder.itemCode" /></th>
			<th width="10%"><bean:message key="purchaseOrder.itemName" /></th>
			<th width="5%"><bean:message key="purchaseOrder.supplierItemNumber" /></th>
			<th width="2%"><bean:message key="purchaseOrder.um" /></th>
			<th width="12%"><bean:message key="purchaseOrder.dueDate" /></th>
			<th width="7%"><bean:message key="purchaseOrder.qty" /></th>
			<th width="7%"><bean:message key="purchaseOrder.qtyOpen" /></th>
			<th><bean:message key="purchaseOrder.fahuo"/></th>
			<%--<th width="5%"><bean:message key="purchaseOrder.receiptQty" /></th>
			
			<th width="5%"><bean:message key="purchaseOrder.transitNumber" /></th>
			<th width="5%"><bean:message key="purchaseOrder.QuantityNumber" /></th>			
			
			<th width="5%"><bean:message key="purchaseOrder.returnNumber" /></th>	
			<th width="5%"><bean:message key="purchaseOrder.replacementNumber" /></th>	
			--%></tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
					<td width="4%">
					
						<input type="hidden"  id="td_poId${X_OBJECT.id}" value="${X_OBJECT.id}"/>
						<c:if test="${X_OBJECT.checked=='noChecked'||X_OBJECT.checked==''}">
						<input type="checkbox" id="ids" value="${X_OBJECT.id}" name="cbox${X_OBJECT.id}"/>
					</c:if>
					<c:if test="${X_OBJECT.checked=='checked'}">
						<input type="checkbox" id="ids" value="${X_OBJECT.id}" name="cbox${X_OBJECT.id}" disabled="disabled"/>
					</c:if>
					</td>
					<td id="td_poOrder${X_OBJECT.id}">
						${X_OBJECT.poip_number.po_number.poOrder}
					</td>
					<td id="td_supplierName${X_OBJECT.id}">${X_OBJECT.poip_number.supplier.name}</td>
					<td id="td_supplierCode${X_OBJECT.id}">${X_OBJECT.poip_number.supplier.code}</td>
					<td id="td_line${X_OBJECT.id}">${X_OBJECT.line}</td>
					<td id="td_itemNumberId${X_OBJECT.id}">${X_OBJECT.itemNumber.id}</td>
					<td id="td_itemNumberName${X_OBJECT.id}">${X_OBJECT.itemNumber.name}</td>
					<td id="td_supplierPartCode${X_OBJECT.id}">${X_OBJECT.itemNumber.supplierPartCode}</td>
					<td id="td_unit${X_OBJECT.id}">${X_OBJECT.itemNumber.unit}</td>
					<td id="td_dueDate${X_OBJECT.id}">${X_OBJECT.dueDate}</td>
					
					<td><input size="5" type="hidden" value="${X_OBJECT.qty}" class="waitQuantity" />
					    ${X_OBJECT.qty}
					</td>
					<td id="td_qtyOpen${X_OBJECT.id}">${X_OBJECT.qtyOpen}</td>
					<td ><input size="10" type="text" id='td_fahuoNumber${X_OBJECT.id}'/></td>
					<%--<td><input size="5" type="hidden" value="${X_OBJECT.qty-X_OBJECT.qtyOpen}" class="receiptQty" />${X_OBJECT.qty-X_OBJECT.qtyOpen}</td>
					
					<td>${X_OBJECT.qty-X_OBJECT.qtyOpen-X_OBJECT.receiptQty}</td>
					<td>${X_OBJECT.receiptQty}</td>
					<td></td>
					<td></td>
					--%></tr>
			</logic:iterate>
		</tbody>
	</table>
	<table>
		<tr>
			<td align="right">
				<input type="button" onclick="generate('${X_OBJECT.poip_number.poip_number}')" value="<bean:message key="select.purchaseOrder"/>" />
			</td>
		</tr>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



