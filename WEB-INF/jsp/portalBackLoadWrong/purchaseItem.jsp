<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
 <style type="text/css">
        td
        {
            white-space: nowrap;
        }
         th
        {
            white-space: nowrap;
        }
    </style>

<script type="text/javascript">

function view(id) {
	var url = "viewBySupplierPurchaseOrderItemWrong.do?id=" + id;
	window.location.href = url;
}

 	function validateForm(){
		var myFile = document.getElementsByName('fileContent')[0].value;
		if(myFile==""){
			alert("请填写导入文件路径!");
			return false;
		} 
		return true;
	} 
 	function printExcel(){
		window.location.href = "listPurchase.do?isExcel=true";
	}
 	function dcexcel(){
	//alert("1");
		window.location.href = "drexcelmobanPO.do";
	}
</script>
<html:form action="/listPurchaseItemWrong" >
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="poitem.poip_number.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="poitem.line" desc="行号"/>
	<input type="hidden" name="fields" value="poitem.poip_number.supplier.code" desc="供应商编号"/>
	<input type="hidden" name="fields" value="poitem.poip_number.supplier.name" desc="供应商名称"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.id" desc="物料编号"/>
	<%--<input type="hidden" name="fields" value="poitem.itemNumber.name" desc="物料名称"/>--%>
	<%--<input type="hidden" name="fields" value="poitem.itemNumber.supplierPartCode" desc=" 供应商物料号"/>--%>
	<input type="hidden" name="fields" value="poitem.itemNumber.unit" desc="单位"/>
	<input type="hidden" name="fields" value="poitem.qty" desc="需求数量"/>
	<input type="hidden" name="fields" value="poitem.dueDate" desc="需求日期"/>
	<%--
	<input type="hidden" name="fields" value="poitem.inventoryNumber" desc="已结数量"/>
	<input type="hidden" name="fields" value="poitem.receiptQty" desc=" 已收数量"/>
	<input type="hidden" name="fields" value="poitem.inventoryNumber" desc=" 入库数量"/>
	<input type="hidden" name="fields" value="poitem.returnNumber" desc="退货数量"/>
	            
	--%><html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
<!-- 
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
		
		<td class="bluetext"><bean:message key="purchaseOrder.itemCode"/>:
			</td>
			<td><html:text property="itemNumber" size="15"/></td>
		<%--
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
		 --%>								
		<%--<tr>
			
			<td colspan="3">
				<html:text property="poNumber" size="15"/>
			</td>
		</tr>
		--%>
		</tr>
		<tr>
			<%--<td class="bluetext"><bean:message key="start.time"/>:
			</td>
			<td>
				<html:text property="poDate" size="15" />
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg1',false,'poDate',null,null,'purchaseOrderInspectionPendingItemQueryForm')"><img
				align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /> </a>
			</td>
			--%>
			
			<td class="bluetext">
				<bean:message key="dueDate"/>:
			</td> 
			<td>
			<html:text property="dueDate" size="15" />
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg2',false,'dueDate',null,null,'purchaseOrderInspectionPendingItemQueryForm')"><img
				align="absmiddle" border="0" id="dimg2"
			src="images/datebtn.gif" /> </a>
			</td>
											
		</tr>
		<tr align="center">
			
			<td align="center">
			<input type="submit" value="<bean:message key="all.query"/>" />
			</td>
			
		</tr>
		<tr><td colspan="4"><hr/></td></tr>
	</table>
 -->
</html:form>
<%--<html:form action="drexcel" enctype="multipart/form-data" onsubmit="return validateForm()">
	<table  border=0 cellpadding=4 cellspacing=0%  width="98%">
		<tr>
			<td width="10%" class="bluetext">
				<bean:message key="purchase.feiNumber" />:
			</td>
			<td width="25%" class="bluetext">
				<html:file property="fileContent" size="18" />
			</td>
			<td colspan="1"   class="bluetext" align="left"">
				<html:submit>
					<bean:message key="all.import" />
				</html:submit>
				&nbsp;&nbsp;&nbsp;<input type="button" value="<bean:message key="template.import"/>"
					onClick="dcexcel()" />
			</td>
		</tr>
	</table>
</html:form>
--%><page:form action="listPurchaseItemWrong.do" method="post">

	<table class="data" >
		<thead>
			<tr  bgcolor="#9999ff" align="center">
			<th><bean:message key="purchaseOrder.itemCode1" /></th>
			<th>物料描述</th>
			<th><bean:message key="supplier.code1"/></th>
				<th><bean:message key="purchaseOrder.qty" /></th>
			<th><bean:message key="purchaseOrder.um" /></th>
		
			<th><bean:message key="purchaseOrder.dueDate1" /></th>
			<th width="5%"><bean:message key="purchaseOrder.xiadaDate1" /></th>
			<th>类型</th>
			<%--
				<th>原厂编号</th>
				<th><bean:message key="purchaseOrder.itemName" /></th>
				<th><bean:message key="purchaseOrder.supplierItemNumber" /></th>
				
				
				
				<th width="5%"><bean:message key="purchaseOrder.qtyOpen" /></th>
				<th width="5%"><bean:message key="purchaseOrder.receiptQty" /></th>
				
				<th width="5%">在途数量</th>
				<th width="5%"><bean:message key="purchaseOrder.QuantityNumber" /></th>	
						
				<th width="5%">入库数量</th>
				<th width="5%">退货数量</th>	
				 
				<th width="5%">类型</th>
			--%>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<c:if test="${X_OBJECT.qty-(X_OBJECT.inventoryNumber+X_OBJECT.returnNumber)>0}">
				<tr align="center">
					<td width="5%">
						<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.poip_number.po_number}</a>
					</td>
					<td>${X_OBJECT.line}</td>
					<td >${X_OBJECT.poip_number.supplier.code}</td>
					<td >${X_OBJECT.poip_number.supplier.name}</td>
					<td>${X_OBJECT.itemNumber.id}</td>
					<td></td>
					<td>${X_OBJECT.itemNumber.unit}</td>
					<td>${X_OBJECT.qty}</td>
					<td>${X_OBJECT.dueDate}</td>
					<td>${X_OBJECT.qty-X_OBJECT.inventoryNumber}</td>
					<td></td>
					<td>
						<c:if test="${X_OBJECT.poip_number.createType == '1'}">JIT</c:if>
						<c:if test="${X_OBJECT.poip_number.createType == '2'}">NJIT_PO</c:if>
						<c:if test="${X_OBJECT.poip_number.createType == '3'}">NJIT_NPO</c:if>
					</td>
					<%--<td>${X_OBJECT.itemNumber.dpiNo}</td>
					<td>${X_OBJECT.itemNumber.oldCode}</td>
					<td>${X_OBJECT.itemNumber.name}</td>
					<td>${X_OBJECT.itemNumber.supplierPartCode}</td>
					<td>${X_OBJECT.inventoryNumber==null?0:X_OBJECT.inventoryNumber}</td>
					<td>${X_OBJECT.qty-X_OBJECT.qtyOpen-X_OBJECT.receiptQty}</td>
					<td>${X_OBJECT.receiptQty==null?10:X_OBJECT.receiptQty}</td>
					<td>${X_OBJECT.inventoryNumber==null?0:X_OBJECT.inventoryNumber}</td>
					<td>${X_OBJECT.returnNumber==null?0:X_OBJECT.returnNumber}</td>
 <c:if test="${X_OBJECT.vd_promo=='T'}">
					<td>中转库</td>
</c:if>	 
 <c:if test="${X_OBJECT.vd_promo!='T'}">
					<td>厂区</td>
</c:if>	 			
					--%></tr>
				</c:if>
			</logic:iterate>
		</tbody>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



