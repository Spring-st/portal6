<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	function printSPList(psoId) {
		var itemId = document.getElementsByName('itemId');
		var item="";
		var count=0;
  		for(var i=0; i<itemId.length; i++){   
  			var id=itemId[i].value;
  			 var actual =document.getElementById('actual'+id).value;
  			 if(actual==""){
  				 alert("实收数量不能为空!");
  				 document.getElementById('actual'+id).focus();
  				 return ;
  			 }
  			 var deliveryNumber=document.getElementById('delivery'+id).value;
  			 count =parseInt(deliveryNumber)-parseInt(actual);
  			 if(count<0){
  				 alert("实收数量不能大于发货数量!");
  				 return false;
  			 }
  			 if(actual!=""){
  				item=item+id+","+actual+";" 
  			 }
  		}
  		
		window.location.href="printPurchaseOrderActualReceipts.do?id="+psoId+"&item="+item;
	}
	function withdraw(psoId){
		var url = "withdrawPortalShipOrderTwo.do?id=" + psoId;
		window.location.href = url;
	}
	function goTo(id){
		var url = "listPurchaseOrderActualReceipts.do";
		window.location.href = url;
	}
</script>
<html:form action="/viewPurchaseOrderActualReceipts" method="post">
	<table width="100%">
	<tr>
 		 <td width="15%" class="bluetext">发货单号:</td>
 		 <td width="35%">
			${x_portalShipOrder.code}
 		 </td>
		</tr><%--

		<tr>
 		 <td width="15%" class="bluetext">供应商号:</td>
 		 <td width="35%">
			${x_portalShipOrder.site.name}
 		 </td>
		</tr>
		
		--%><tr>
		<td class="bluetext" >创建时间:</td>
		<td >${x_portalShipOrder.createDate}</td>
		</tr>
		<%--<tr>
		<td class="bluetext" >到货时间:</td>
		<td >${x_portalShipOrder.arrivalDate}</td>
		</tr>
	--%></table>
	
	<h3 style="color:blue"><bean:message key="purchaseOrderReceipts.item"/></h3>
	
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff">
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
							<th>
						       实收数量
							</th>
							<th>包装箱容量</th>
							<!--  <th>
						    	<bean:message key="purchaseOrder.hege"/>
							</th>
							<th>
						         <bean:message key="purchaseOrder.tuihuo"/>
						   </th>-->
							<th>到货日期</th>
							
							<th>
						    	类型
							</th>
							<th>
						    	工厂
							</th>
							<%-- 
							<th>
								操作
							</th>
							--%>
						</tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_portalShipOrderItemList">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr align="center">
								<td>
									${X_OBJECT.poipItem.poip_number.po_number}
									<input type="hidden" name="itemId" value="${X_OBJECT.id}">
								</td>
									
								<td>${X_OBJECT.poipItem.line}</td>
								<td>${X_OBJECT.poipItem.itemNumber.id}</td>
								<td>${X_OBJECT.poipItem.itemNumber.dpiNo}</td>
								<td>${X_OBJECT.poipItem.itemNumber.oldCode}</td>
								<td>${X_OBJECT.poipItem.itemNumber.name}</td>
								<td>${X_OBJECT.poipItem.supplierItemNumber}</td>
								<td>${X_OBJECT.poipItem.um}</td>
								<td>${X_OBJECT.poipItem.qty}</td>
								
								<td>
									${X_OBJECT.deliveryNumber}
									<input type="hidden" id="delivery${X_OBJECT.id}" value="${X_OBJECT.deliveryNumber}"/>
								</td>
								<!-- <td></td>-->
								<td>
								<c:if test="${X_OBJECT.printStatus.enumCode !='0'}">
									<input size="1" type="text" id="actual${X_OBJECT.id}" value="${X_OBJECT.actual_qty}"/>
								</c:if>
								<c:if test="${X_OBJECT.printStatus.enumCode =='0'}">
									<input size="1" type="hidden" id="actual${X_OBJECT.id}" value="${X_OBJECT.actual_qty}"/>
									${X_OBJECT.actual_qty}
								</c:if>
									<%--<c:if test="${X_OBJECT.actual_qty==null}">
										<input size="1" type="text" id="actual${X_OBJECT.id}" value=""/>
									</c:if>
									<c:if test="${X_OBJECT.actual_qty!=null}">
										<input type="hidden" id="actual${X_OBJECT.id}" value=""/>
										${X_OBJECT.actual_qty}
									</c:if>
								--%></td>
								<td>${X_OBJECT.qty_std}</td>
								<td>
									${X_OBJECT.poipItem.dueDate }
								</td>
								
								<c:if test="${X_OBJECT.poipItem.vd_promo=='T' }">
									<td>中转库</td>
								</c:if>
								
								<c:if test="${X_OBJECT.poipItem.vd_promo!='T' }">
									<td>厂区</td>
								</c:if>
								
								<td>
								${X_OBJECT.poipItem.factory }
								
								</td>
								<%--
								<td>
									<input type="button" value="删除" onclick="podeleteItem('${X_OBJECT.id}')"/>
								</td>
								 --%>
						</tr>
					</logic:iterate>
			    </tbody>
	</table>
	<table width="100%">
		<tr>
			<td width="100%"><hr/></td>
		</tr>
		<tr>
			<td  align="right">
			<input type="button" value="打印实收单" onclick="printSPList('${x_portalShipOrder.id}')"/>
			<input type="button" value="返回" onclick="goTo('${x_portalShipOrder.id}')"/>
			</td>
		</tr>
	</table>
	</html:form>
<%--<page:form action="/viewPurchaseOrderActualReceipts">		
	<h3 style="color:blue"><bean:message key="purchaseOrderReceipts.lot"/></h3>
	<jsp:include page="../../pageHead.jsp"/>
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff">
			<th>箱号</th>	
			<th>供应商批次</th>
			<th>物料号</th>	
			<th>DPI</th>	
			<th>包装箱容量</th>
			<th>发货量</th>
			<th>是否打印</th>		
			</tr>
			</thead>
			<tbody id="datatable3">
			<c:forEach items="${x_portalShopOrderBoxList}" var="X_OBJECT">
			<c:set var="X_OBJECT"  scope="request" value="X_OBJECT"/>
					<tr>	
						<td style="display: none"><input type="text" value="${sessionScope.size}" id="size"/></td>
						<td>${X_OBJECT.lot.id}</td>
						
						<td>${X_OBJECT.psoItem.poipItem.itemNumber.id}</td>
						<td>${X_OBJECT.psoItem.poipItem.itemNumber.dpiNo}</td>
						<td>${X_OBJECT.psoItem.qty_std}</td>
						<td>${X_OBJECT.number}</td>
						<td>${X_OBJECT.isPrint.chnShortDescription}</td>
					</tr>
				</c:forEach>
			</tbody>
	</table>
	<table width="100%">
		<tr>
			<td width="100%"><hr/></td>
		</tr>
		<tr>
			<td  align="right">
			<input type="button" value="打印发货单" onclick="printSPList('${x_portalShipOrder.id}')"/>
			<input type="button" value="返回" onclick="goTo('${x_portalShipOrder.id}')"/>
			</td>
		</tr>
	</table>
</page:form>
--%><script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
   // applyRowStyle(document.all('datatable3'));
</script>

