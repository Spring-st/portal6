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
	function printSPList(crId) {
		
		window.location.href="printCustomerreturnsByItem.do?id="+crId;
	}
	function goTo(id){
		var url = "listCustomerreturnsAction.do";
		window.location.href = url;
	}
</script>
<html:form action="/viewCustomerreturnsByItem" method="post">
	<table width="100%">
		<tr>
			 <td width="15%" class="bluetext">退货单号:</td>
			 <td width="35%">
				${x_customerreturns.returnNumber}
			 </td>
		</tr>
		<tr>
			<td class="bluetext">客户编码:</td>
			<td width="35%">
				${x_customerreturns.basicCustomer.code}
			</td>
		</tr>
		<%--<tr>
			<td width="15%" class="bluetext">退货地点:</td>
			<td width="35%">
				${x_customerreturns.returnLocation}
			</td>
		</tr>
		--%><tr>
			<td class="bluetext">退货接收库位:</td>
			<td width="35%">
				${x_customerreturns.returnStorage}
			</td>
		</tr>
		<tr>
			<td class="bluetext" >客户退货数量:</td>
			<td width="35%">
				<fmt:formatNumber value="${x_customerreturns.returnQuantity}" maxFractionDigits="0"/>
			</td>
		</tr>
		<tr>
			<td width="15%" class="bluetext">是否为开票退货:</td>
			<td width="35%">
				${x_customerreturns.invoiceStatus.chnShortDescription}
			</td>
		</tr>
		<tr>
			<td class="bluetext" >退货日期:</td>
			<td width="35%">
				${x_customerreturns.returnDate}
			</td>
		</tr>
		<tr>
			<td class="bluetext" >退货人:</td>
			<td width="35%">
				${x_customerreturns.returnPerson}
			</td>
		</tr>
		<tr>
			<td class="bluetext" >退货人联系方式:</td>
			<td width="35%">
				${x_customerreturns.returnPersonContact}
			</td>
		</tr>
		<tr>
			<td class="bluetext" >退货原因:</td>
			<td width="35%">
				${x_customerreturns.returnReasons}
			</td>
		</tr>	
	</table>
</html:form>
<page:form action="/viewCustomerreturnsByItem">	
<input type="hidden" name="id" value="${x_Id}"/>	
	<h3 style="color:blue">退货单明细 </h3>
	<jsp:include page="../pageHead.jsp"/>
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
							<th>批次号</th>
							<th>物料编号</th>
							<th>物料描述</th>
							<th>DPI</th>
							<th>原厂编号</th>
							<th>退货接收库位</th>
							<th>数量</th>
							<th>销售出库时间</th>
						</tr>
					</thead>
				<tbody id="datatable3">
					<c:forEach items="${x_customerReturnItemList}" var="X_OBJECT">
					<c:set var="X_OBJECT"  scope="request" value="X_OBJECT"/>
						<tr align="center">
							<td>${X_OBJECT.batchNumber}</td>
							<td>${X_OBJECT.part.id}</td>
							<td>${X_OBJECT.part.describe1}</td>
							<td>${X_OBJECT.part.dpiNo}</td>
							<td>${X_OBJECT.part.oldCode}</td>
							<td>${X_OBJECT.returnStorage}</td>
							<td><fmt:formatNumber value="${X_OBJECT.qty}" maxFractionDigits="0"/></td>
							<td>${X_OBJECT.salesDeliveryDate}</td>
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
			<input type="button" value="打印" onclick="printSPList('${x_customerreturns.id}')"/>
			<input type="button" value="返回" onclick="goTo();"/>
			</td>
		</tr>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable3'));
    
</script>

