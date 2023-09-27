<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	function printSPList(psoId,type) {
		
		window.location.href="printSalesPickingOrderBatch.do?id="+psoId+"&type="+type;
	}
	function goTo(id){
		var url = "listSalesPickingOrder.do";
		window.location.href = url;
	}
</script>
<html:form action="/viewalesPickingOrderBatch" method="post">
	<table width="100%">
	<tr>
 		 <td width="15%" class="bluetext">检料单号:</td>
 		 <td width="35%">
			${x_salesShipOrder.code}
 		 </td>
		</tr>
		<tr>
			<%--<td class="bluetext">供应商号</td>
			<td >${x_salesShipOrder.site.name}</td>--%>
		</tr>
		<tr>
			<td class="bluetext" >创建时间:</td>
			<td> <fmt:formatDate value="${x_salesShipOrder.createDate}" pattern="yyyy-MM-dd"/> </td>
			<%---<td >${x_salesShipOrder.createDate}</td>--%>
		</tr>
		<tr>
			<%--<td class="bluetext" >到货时间:</td>
			<td> <fmt:formatDate value="${x_salesShipOrder.arrivaldate}" pattern="yyyy-MM-dd"/> </td>
			<td >${x_salesShipOrder.arrivaldate}</td>--%>
		</tr>
	</table>
	<c:if test="${x_salesShipOrder.type eq '1'}">
		<h3 style="color:blue">销售订单发货明细</h3>
		<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
						  	<th>销售订单号</th>
							<th>客户</th>
							<th>客户编码</th>
							<th>送货地址</th>
							<th>行号</th>
							<th>物料编码</th>
							<th>物料名称</th>
							<th>DPI</th>
							<th>原厂编号 </th>
							<th>单位</th>
							<th>要求交货日期</th>
							<th>销售订单数量</th>
							<%--<th>未发货数量</th>--%>
							<th>预发货数量</th>
							<th>实际发货数量</th>
							<th>类型</th>
							<th>备注(需要优先发货)</th>
						</tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_salesPreshiporderItemList">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr align="center">
								<td align="center">${X_OBJECT.soipitemId.soId.soNumber}</td>
								<td align="center">${X_OBJECT.soipitemId.soId.custName}</td>
								<td align="center">${X_OBJECT.soipitemId.soId.custCode}</td>
								<td align="center">${X_OBJECT.soipitemId.soId.custAddress}</td>
								<td align="center">${X_OBJECT.soipitemId.line}</td>
								<td align="center">${X_OBJECT.soipitemId.itemNumber.id}</td>
								<td align="center">${X_OBJECT.soipitemId.itemNumber.name}</td>
								<td align="center">${X_OBJECT.soipitemId.itemNumber.dpiNo}</td>
								<td align="center">${X_OBJECT.soipitemId.itemNumber.oldCode}</td>
								<td align="center">${X_OBJECT.soipitemId.itemNumber.unit}</td>
								<td align="center">${X_OBJECT.soipitemId.dueDate}</td>
								<td><fmt:formatNumber value="${X_OBJECT.soipitemId.qty}" maxFractionDigits="0"/></td>
								<%--<td>${X_OBJECT.soipitemId.qtyopen}</td>--%>
								<td><fmt:formatNumber value="${X_OBJECT.deliverynumber}" maxFractionDigits="0"/></td>
								<td><fmt:formatNumber value="${X_OBJECT.shipQty}" maxFractionDigits="0"/></td>
								<td align="center">${X_OBJECT.soipitemId.sotype}</td>
								<td>${X_OBJECT.soRemark}</td>
						</tr>
					</logic:iterate>
			    </tbody>
		</table>
	</c:if>
	<c:if test="${x_salesShipOrder.type ne '1'}">
		<h3 style="color:blue">客户需求计划发货信息</h3>
		<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
							<th>客户需求计划单号</th>
							<th>客户</th>
							<th>客户编码</th>
							<th>送货地址</th>
							<th>行号</th>
							<th>物料编码</th>
							<th>物料名称</th>
							<th>DPI</th>
							<th>原厂编号</th>
							<th>单位</th>
							<th>要求交货日期</th>
							<th>客户需求数量</th>
							<%--<th>未发货数量</th>--%>
							<th>预发货数量</th>
							<th>实际发货数量</th>
							<%--<th>类型</th>
							--%><th>备注(需要优先发货)</th>
						</tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_salesPreshiporderItemList">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr align="center">
								<td align="center">${X_OBJECT.customerPlanId.planNumbers}</td>
								<td align="center">${X_OBJECT.customerPlanId.customer.name1}</td>
								<td align="center">${X_OBJECT.customerPlanId.customer.code}</td>
								<td align="center">${X_OBJECT.customerPlanId.customerAddress}</td>
								<td align="center">${X_OBJECT.customerPlanId.line}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.id}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.name}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.dpiNo}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.oldCode}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.unit}</td>
								<td align="center">
									<bean:write name="X_OBJECT" property="customerPlanId.shipmentDate" format="yyyy-MM-dd" />
									<%--${X_OBJECT.customerPlanId.shipmentDate}--%>
								</td>
								<td><fmt:formatNumber value="${X_OBJECT.customerPlanId.qty}" maxFractionDigits="0"/></td>
								<%--<td>${X_OBJECT.customerPlanId.qtyopen}</td>--%>
								<td><fmt:formatNumber value="${X_OBJECT.deliverynumber}" maxFractionDigits="0"/></td>
								<td><fmt:formatNumber value="${X_OBJECT.shipQty}" maxFractionDigits="0"/></td>
								<%--<td align="center">${X_OBJECT.customerPlanId.sotype}</td>--%>
								<td>${X_OBJECT.soRemark}</td>
						</tr>
					</logic:iterate>
			    </tbody>
		</table>
	</c:if>
	
	</html:form>
<page:form action="/viewalesPickingOrderBatch">	
<input type="hidden" name="id" value="${x_shipOrderId}"/>	
	<h3 style="color:blue">检料单批次信息</h3>
	<jsp:include page="../pageHead.jsp"/>
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
							<th>批次号</th>
							<th>物料编号</th>
							<th>物料描述</th>
							<th>DPI</th>
							<th>原厂编号</th>
							<th>库位代码</th>
							<th>数量</th>
							<th>状态</th>
						</tr>
					</thead>
				<tbody id="datatable3">
				<c:forEach items="${x_salesWorkorderList}" var="X_OBJECT">
				<c:set var="X_OBJECT"  scope="request" value="X_OBJECT"/>
					<tr align="center">
						<td align="center" width="100px">${X_OBJECT.lotSer.id}</td>
						<td align="center" width="100px">${X_OBJECT.part.id}</td>
						<td align="center" width="100px">${X_OBJECT.part.describe1}</td>
						<td align="center" width="100px">${X_OBJECT.part.dpiNo}</td>
						<td align="center" width="100px">${X_OBJECT.part.oldCode}</td>
						<td align="center" width="100px">${X_OBJECT.location.code}</td>
						<td align="center" width="100px"><fmt:formatNumber value="${X_OBJECT.count}" maxFractionDigits="0"/></td>
						<td align="center" width="100px">${X_OBJECT.status1.chnShortDescription}</td>
				</tr>
				</c:forEach>
				</tbody>
	</table>
	<table width="100%">
		<tr>
			<td width="100%" colspan="4"><hr/></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="3" align="right">
			<input type="button" value="打印(库位)" onclick="printSPList('${x_salesShipOrder.id}',2)"/>
			<input type="button" value="打印(物料)" onclick="printSPList('${x_salesShipOrder.id}',1)"/>
			<input type="button" value="返回" onclick="goTo('${x_salesShipOrder.id}')"/>
			</td>
		</tr>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
    applyRowStyle(document.all('datatable3'));
    
</script>

