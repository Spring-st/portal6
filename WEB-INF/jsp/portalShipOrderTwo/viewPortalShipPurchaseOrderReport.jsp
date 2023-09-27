<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	function view(id) {
		var url = "viewPortalShipOrderTwoReportSite.do?id=" + id;
		window.location.href = url;
	}

</script>
<html:form action="/viewPortalShipOrderTwoReportSite" method="post">
	<table width="100%">
	<tr>
 		 <td width="15%" class="bluetext"><bean:message key="SO.No"/>:</td>
 		 <td width="35%">
			${x_portalShipOrder.code}
 		 </td>
		</tr>
	
		<tr>
 		 <td width="15%" class="bluetext"><bean:message key="purchaseOrder.site"/>:</td>
 		 <td width="35%">
			${user.loginName}
 		 </td>
		</tr>
		
		<tr>
	<td class="bluetext" ><bean:message key="portalShipOrder.createDate" />:</td>
	<td >${x_portalShipOrder.createDate}</td>
		</tr>
	</table>
	
	<h3 style="color:blue"><bean:message key="purchaseOrderReceipts.item"/></h3>
	
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
						   <th>
						         <bean:message key="PO.No"/>
						   </th>
						   <th>
						         <bean:message key="purchaseOrder.line"/>
							</th>
							<th>
						    	<bean:message key="purchaseOrder.itemCode"/>
							</th>
							<th>
						    	DPI
							</th>
							<th>
						    	原厂编号
							</th>
							<th>
						         <bean:message key="purchaseOrder.itemName"/>
						   </th>
						   <th>
						         <bean:message key="purchaseOrder.supplierItemNumber"/>
							</th>
							<th>
						    	<bean:message key="purchaseOrder.um"/>
							</th>
							<th>
						         订单数量
						   </th>
						   <th>
						        发货数量
							</th>
							<!--  <th>
						    	<bean:message key="purchaseOrder.hege"/>
							</th>
							<th>
						         <bean:message key="purchaseOrder.tuihuo"/>
						   </th>-->
							<th>到货时间</th>
						</tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_portalShipOrderItemList">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr align="center">
								<td>${X_OBJECT.poipItem.poip_number.po_number}</td>
								<td>${X_OBJECT.poipItem.line}</td>
								<td>${X_OBJECT.poipItem.itemNumber.id}</td>
								<td>${X_OBJECT.poipItem.itemNumber.dpiNo}</td>
								<td>${X_OBJECT.poipItem.itemNumber.oldCode}</td>
								<td>${X_OBJECT.poipItem.itemNumber.name}</td>
								<td>${X_OBJECT.poipItem.supplierItemNumber}</td>
								<td>${X_OBJECT.poipItem.um}</td>
								<td>${X_OBJECT.poipItem.qty}</td>
								<td>${X_OBJECT.deliveryNumber}</td>
								<!--  <td></td>
								<td></td>-->
								<td>
									${X_OBJECT.poipItem.dueDate }
								</td>
								
						</tr>
					</logic:iterate>
			    </tbody>
	</table>
</html:form>
<page:form action="/viewPortalShipOrderTwoReportSite.do" method="post">		
	<h3 style="color:blue"><bean:message key="purchaseOrderReceipts.lot"/></h3>
	<jsp:include page="../pageHead.jsp"/>
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
			<th><bean:message key="purchaseOrderBox.lotSer" /></th>	
			<th>物料号</th>	
			<th>DPI</th>	
			<th>原厂编号</th>	
			<th>包装箱容量</th>
			<th>发货数量</th>
			
			<th>接收数量</th>
			<th>接收不合格数量</th>
			
			<th>入库数量</th>
			<th>质检不合格数量</th>	
			<th>是否打印</th>		
						</tr>
					</thead>
				<tbody id="datatable3">
					<logic:iterate id="X_OBJECT" name="x_portalShopOrderBoxList">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr align="center">
								<td>${X_OBJECT.lot.id}</td>
								<td>${X_OBJECT.psoItem.poipItem.itemNumber.id}</td>
								<td>${X_OBJECT.psoItem.poipItem.itemNumber.dpiNo}</td>
								<td>${X_OBJECT.psoItem.poipItem.itemNumber.oldCode}</td>
								<td>${X_OBJECT.psoItem.qty_std}</td>
								<td>${X_OBJECT.number}</td>
								 
								<td>${X_OBJECT.receivedNumber}</td>
								<td>${X_OBJECT.vetoReceivedNumber}</td>
								<td>${X_OBJECT.inStorageNumber}</td>
								<td>${X_OBJECT.vetoQCnumber}</td>
								<td>${X_OBJECT.isPrint.chnShortDescription}</td>
						</tr>
					</logic:iterate>
			    </tbody>
	</table>
	<%--<table width="100%">
		<tr>
			<td width="100%" colspan="4"><hr/></td>
		</tr>
		<tr>
			<td colspan="4" align="right">

			</td>
		</tr>
	</table>
	--%><jsp:include page="../pageTail.jsp"/>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
    applyRowStyle(document.all('datatable3'));
    
</script>

