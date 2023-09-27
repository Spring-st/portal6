<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	function printCodeList(psoId) {
						var size = parseFloat(document.getElementById('size').value);
						if(size != 0){
							//alert(size);
							//var list =${x_portalShopOrderBoxList};
							var supplierBatchStr ="";	
							var poipBoxIds ="";
							for(var i =0;i<size;i++){
									if(document.getElementsByName("supplierBatchs")[i]!=null&&document.getElementsByName("supplierBatchs")[i].value!=null){
										supplierBatchStr = supplierBatchStr+document.getElementsByName("supplierBatchs")[i].value+",";
										poipBoxIds =poipBoxIds+document.getElementsByName('poipBoxId')[i].value+",";
								
									}
						    }
					}
					window.location.href="printPortalShipOrderPartCodeList.do?supplierBatchStr="+supplierBatchStr+"&id="+psoId+"&poipBoxIds="+poipBoxIds;		
                
		
	}
		
		//alert(supplierBatchStr+":"+poipBoxIds);
		//alert("aa");
	//	window.location.href="printPortalShipOrderPartCodeList.do?supplierBatchStr="+supplierBatchStr+"&id="+psoId+"&poipBoxIds="+poipBoxIds;
	//}
	function printSPList(psoId) {
		window.location.href="printPortalShipOrderView.do?id="+psoId;
	}
	function podelete(psoId){
		//alert(psoId);
		var str="";
		<c:forEach items="${x_portalShipOrderItemList}" var="item">
			str=str+${item.id}+",";
		</c:forEach>
		//alert(str);
		var flag = window.confirm("您确定要删除此发货单及其明细吗？");
		if(flag){
			$.ajax({         
                type:"POST", //请求方式        
                url:"ajaxValidateCanDelete.do", //请求路径        
                cache: false,           
                data:"array="+str,  //传参        
                dataType: 'json',   //返回值类型        
                async:false,
                success:function(json){
					if(json =="true"){
						alert("有包装箱已收货，不能进行删除!")
						return false;
					}else{
						var url = "podeletePortalShipOrderTwo.do?id=" + psoId;
						window.location.href = url;
					}
			    }
            });
		}
		 
	}
	
	function podeleteItem(psoId){
		var str="";
		<c:forEach items="${x_portalShipOrderItemList}" var="item">
			str=str+${item.id}+",";
		</c:forEach>
		
		var flag = window.confirm("您确定要删除此采购单及其发货明细吗？");
		if(flag){
			$.ajax({         
                type:"POST", //请求方式        
                url:"ajaxValidateCanDelete.do", //请求路径        
                cache: false,           
                data:"array="+str,  //传参        
                dataType: 'json',   //返回值类型        
                async:false,
                success:function(json){
                	//alert(json)
					if(json =="true"){
						alert("有包装箱已收货，不能进行删除!")
						return false;
					}else{
						
						var url = "podeletePortalShipOrderTwoItem.do?id=" + psoId;
						window.location.href = url;
					}
			    }
            });
		}
		 
	}
	
	function withdraw(psoId){
		var url = "withdrawPortalShipOrderTwo.do?id=" + psoId;
		window.location.href = url;
	}
	function goTo(id){
		var url = "listShippingOrder.do";
		window.location.href = url;
	}
	function createPortalShipOrderIpTwo(psoId) {
		
		window.location.href="createPortalShipOrderIpTwo.do?id="+psoId;
	}
	
	function closeEnabled(id){
		window.location.href="updatePoCloseIsEnabled.do?id="+id;
	}
	function openEnabled(id){
		window.location.href="updatePoOpenIsEnabled.do?id="+id;
	}
</script>
<html:form action="/viewPortalShipOrderTwo" method="post">
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
		<tr>
		<td class="bluetext" >发货时间:</td>
		<td >${x_portalShipOrder.arrivalDate}</td>
		</tr>
	</table>
	
	<h3 style="color:blue"><bean:message key="purchaseOrderReceipts.item"/></h3>
	
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
						   <th>
						         <bean:message key="PO.No1"/>
						   </th>
						   <th>
						         <bean:message key="purchaseOrder.line"/>
							</th>
							 <th>
						         <bean:message key="purchaseOrder.supplierCode1"/>
							</th>
							<th>
						         <bean:message key="purchaseOrder.supplierName"/>
							</th>
							<th>
						    	<bean:message key="purchaseOrder.itemCode1"/>
							</th>
							<th>
						    	<bean:message key="purchaseOrder.um"/>
							</th>
							<th>
						    	采购数量
							</th>
							<th>
						    	交货时间
							</th>
							<th>
						    	未发货数量
							</th>
							<th>
						    	订单下达日期
							</th>
							<th>
						    	本次发货数量
							</th>
							<%-- 
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
						         订单数量
						   </th>
						   <th>
						         发货数量
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
							
							<th>
								操作
							</th>
							--%>
						</tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_portalShipOrderItemList">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr  align="center">
								<td>${X_OBJECT.poipItem.poip_number.po_number}</td>
								<td>${X_OBJECT.poipItem.line}</td>
								<td>${X_OBJECT.poipItem.poip_number.supplier.code}</td>
								<td>${X_OBJECT.poipItem.poip_number.supplier.name}</td>
								<td>${X_OBJECT.poipItem.itemNumber.id}</td>
								<td>${X_OBJECT.poipItem.um}</td>
								<td><fmt:formatNumber value="${X_OBJECT.poipItem.qty}" maxFractionDigits="0" minFractionDigits="0"></fmt:formatNumber></td>
								<td>${X_OBJECT.poipItem.dueDate}</td>
								<td><fmt:formatNumber value="${X_OBJECT.poipItem.qtyOpen}" maxFractionDigits="0" minFractionDigits="0"></fmt:formatNumber></td>
								<td>${X_OBJECT.poipItem.poip_number.createDate}</td>
								<td><fmt:formatNumber value="${X_OBJECT.deliveryNumber}" maxFractionDigits="0" minFractionDigits="0"></fmt:formatNumber></td>
								<%--<td>${X_OBJECT.poipItem.itemNumber.dpiNo}</td>
								<td>${X_OBJECT.poipItem.itemNumber.oldCode}</td>
								<td>${X_OBJECT.poipItem.itemNumber.name}</td>
								<td>${X_OBJECT.poipItem.supplierItemNumber}</td>
								<td>${X_OBJECT.poipItem.um}</td>
								<td>${X_OBJECT.poipItem.qty}</td>
								
								<td>${X_OBJECT.deliveryNumber}</td>
								 <td></td>
								<td></td>
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
								
								</td>--%>
								<%--
								<td>
									<input type="button" value="删除" onclick="podeleteItem('${X_OBJECT.id}')"/>
								</td>
								 --%>
						</tr>
					</logic:iterate>
			    </tbody>
	</table>
	</html:form>
<page:form action="/viewPortalShipOrderTwo">		
	<h3 style="color:blue"><bean:message key="purchaseOrderReceipts.lot"/></h3>
	<jsp:include page="../pageHead.jsp"/>
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
			<%--<th><bean:message key="purchaseOrderBox.lot" /></th>	--%>
			<th>箱单号</th>	
			<!--<th>供应商批次</th>-->
			<th>组件/物料编码</th>	
			<%--<th>原厂编号</th>	--%>
			<th>包装箱容量</th>
			<th>批次数量</th>
			<!-- <th><bean:message key="purchaseOrderBox.receiveCount" /></th>
			<th><bean:message key="purchaseOrderBox.receiveUnqualifiedCount" /></th>
			
			<th><bean:message key="purchaseOrderBox.InStorageCount" /></th>
			<th><bean:message key="purchaseOrderBox.qcUnqualifiedCount" /></th>		-->
			<th>打印状态</th>		
						</tr>
					</thead>
				<tbody id="datatable3">
				<c:forEach items="${x_portalShopOrderBoxList}" var="X_OBJECT">
				<c:set var="X_OBJECT"  scope="request" value="X_OBJECT"/>
					<tr align="center">	<td style="display: none"><input type="text" value="${sessionScope.size}" id="size"/></td>
								<td>${X_OBJECT.lot.id}</td>
								<!-- <td id="supp" >
									<c:if test="${(X_OBJECT.supplierBatch ==''||X_OBJECT.supplierBatch ==null)&&x_portalShipOrder.status.enumCode==0}">
									<input type="text" id="supplierBatch" name="supplierBatchs" size="15" /> 
									<input type="hidden" id="lotSerId" value="${X_OBJECT.lot.id}" />
									<input type="hidden" id="poipBoxId" name="poipBoxId" value="${X_OBJECT.id}" />
									</c:if>
									<c:if test="${X_OBJECT.supplierBatch !=''}">
									${X_OBJECT.supplierBatch}
									</c:if>
								</td> -->
								<!--<td>${X_OBJECT.supplierBatch}</td>  -->
								<td>${X_OBJECT.psoItem.poipItem.itemNumber.id}</td>
								<%--<td>${X_OBJECT.psoItem.poipItem.itemNumber.oldCode}</td>--%>
								<%--<td>${X_OBJECT.psoItem.qty_std}</td>
								<td>${X_OBJECT.part.ord_mult}</td>--%>
								<td><fmt:formatNumber value="${X_OBJECT.psoItem.part.ord_mult}" maxFractionDigits="0" minFractionDigits="0"></fmt:formatNumber></td>
								<td><fmt:formatNumber value="${X_OBJECT.number}" maxFractionDigits="0" minFractionDigits="0"></fmt:formatNumber></td>
								<td>${X_OBJECT.isPrint.chnShortDescription}</td>
						</tr>
						<bean:define id="sumNumber" value="${sumNumber+(X_OBJECT.number)}"/>
				</c:forEach>
					<%--<logic:iterate id="X_OBJECT" name="x_portalShopOrderBoxList" indexId="list">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr>	<td style="display: none"><input type="text" value="${sessionScope.size}" id="size"/></td>
								<td>${X_OBJECT.lotSer.id}</td>
								<td id="supp" >
									<c:if test="${X_OBJECT.supplierBatch ==''||X_OBJECT.supplierBatch ==null}">
									<input type="text" id="supplierBatch" name="supplierBatchs" size="15" /> 
									<input type="hidden" id="lotSerId" value="${X_OBJECT.lotSer.id}" />
									<input type="hidden" id="poipBoxId" name="poipBoxId" value="${X_OBJECT.id}" />
									</c:if>
									<c:if test="${X_OBJECT.supplierBatch !=''}">
									${X_OBJECT.supplierBatch}
									</c:if>
								</td>
								<!--<td>${X_OBJECT.supplierBatch}</td>  -->
								<td>${X_OBJECT.psoItem.poipItem.itemNumber.id}</td>
								<td>${X_OBJECT.psoItem.poipItem.itemNumber.capacity}</td>
								<td>${X_OBJECT.count}</td>
								<!-- 
								<td></td>
								<td></td>
								<td></td>
								<td></td>-->
								<td>${X_OBJECT.isPrint.chnShortDescription}</td>
						</tr>
					</logic:iterate>
			    --%></tbody>
	</table>
	<table width=100% border=0 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		<tr align="right">
			<td class="bluetext">合计数量:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumNumber}" maxFractionDigits="4" minFractionDigits="4"/></td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td width="100%" colspan="4"><hr/></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="3" align="right">
			<%--
			<c:if test="${x_portalShipOrder.status.enumCode==0}">
			<input type="button" value="删除" onclick="podelete('${x_portalShipOrder.id}')"/>
			</c:if>
			 --%>
			  <c:if test="${x_portalShipOrder.enabled.enumCode==0}">
			 	<input type="button" value="关闭" onclick="closeEnabled(${x_portalShipOrder.id})"/>
			 </c:if>
			 <c:if test="${x_portalShipOrder.enabled.enumCode==1}">
			 	<input type="button" value="打开" onclick="openEnabled(${x_portalShipOrder.id})"/>
			 </c:if>
			<c:if test="${x_portalShipOrder.status.enumCode==1}">
			<input type="button" value="撤回" onclick="withdraw('${x_portalShipOrder.id}')"/>
			</c:if>
			<%-- style="display: none;"--%>
			<input type="button" value="重新生成箱单号" style="display: none;" onclick="createPortalShipOrderIpTwo('${x_portalShipOrder.id}')"/>
			<input type="button" value="打印箱单" onclick="printCodeList('${x_portalShipOrder.id}')"/>
			<c:if test="${x_portalShipOrder.status.enumCode==0}">
			<input type="button" value="打印发货单" onclick="printSPList('${x_portalShipOrder.id}')"/></c:if>
			<input type="button" value="返回" onclick="goTo('${x_portalShipOrder.id}')"/>
			</td>
		</tr>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
    applyRowStyle(document.all('datatable3'));
    
</script>

