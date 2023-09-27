<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'viewBySupplierItem.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript" src="includes/table.js"></script>
<script language="javascript" type="text/javascript">
	function isConfirmViewItem(id){
		var url = "confirmPurchaseOrderItem.do?id=" + id;
		window.location.href = url;
	}
	function tBack(){
		var url="listSupplierPurchaseOrder.do";
		window.location.href = url;
	}
	
	function qtyStdUpdate(id){
		var qtyStd=document.getElementById(id+"_qty_std").value;
		if(qtyStd<0){
			alert("包装箱容量不能为负数！");
			return false;
		}
		if(qtyStd==""){
			alert("包装箱容量不能为空！");
			return false;
		}
		if(!isNaN(qtyStd)){
		   var url = "updatePurchaseOrderItemByQtyStd.do?id=" + id+"&qtyStd="+qtyStd;
		   window.location.href = url;
		}else{
		   alert(qtyStd+"不是数字");
		}
	}
	
</script>
  </head>
  
  <body>
<table width=100% border=0 cellpadding=4 cellspacing=0>
		<%--<tr>
 		 <td width="15%" class="bluetext"><bean:message key="purchaseOrder.site"/>:</td>
 		 <td width="35%">${x_purchaseOrderInspectionPending.site.name}</td>
		 <td width="15%"  class="bluetext"><bean:message key="purchaseOrder.department"/>:</td>
 		 <td width="35%">${x_purchaseOrderInspectionPending.department.name}</td>
		</tr>
		--%><tr>
		<td class="bluetext"><bean:message key="purchaseOrder.id" />:</td>
			<td>${item.poip_number.po_number}</td>
			<td class="bluetext"><bean:message key="purchaseOrder.poDate" />:</td>
			<td>${item.poip_number.createDate}</td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrder.supplierCode" />:</td>
		<td>${item.poip_number.supplier.code}</td>
		<td class="bluetext"><bean:message key="purchaseOrder.supplierName" />:</td>
		<Td>${item.poip_number.supplier.name}</Td>
		</tr>
		
		<tr>
		<td>
		<c:if test="${x_purchaseOrderInspectionPending.pdfmd5!=null}">
	<A href="javascript:viewPDF('${x_purchaseOrderInspectionPending.pdfmd5}')">PDF<img src="images/pdf.gif" border=0/></A>
		</c:if>
		</td>
		</tr>
		
	</table>
		<hr width="100%"/>
<h3 style="color:blue"><bean:message key="purchaseOrder.item"/></h3>
	<table class="data">
	<thead>
		<tr bgcolor="#9999ff" align="center">
			<th><bean:message key="purchaseOrder.id" /></th>	
			<th><bean:message key="purchaseOrder.line" /></th>
			<th><bean:message key="purchaseOrder.itemCode" /></th>
			<th><bean:message key="purchaseOrder.itemName" /></th>
			<th><bean:message key="purchaseOrder.um" /></th>
			<th><bean:message key="purchaseOrder.dueDate" /></th>
			<th><bean:message key="purchaseOrder.qty" /></th>
			<th>未发货数量</th>
			<th>已收数量</th>	
					
			<!--<th>包装量</th>
			<th>包装量调整</th>
		--></tr>
	</thead>
	<tbody id="item_datatable">
	
		<tr align="center">
			<td align="center">
		     ${item.poip_number.po_number}
			</td>
			<td align="center">${item.line}</td>
			<td align="center">${item.itemNumber.id}</td>
			<td align="center">${item.itemNumber.name}</td>
			<td align="center">${item.itemNumber.unit}</td>
			<td align="center">${item.dueDate}</td>
			
			<td align="center"><input size="5" type="hidden"" value="${item.qty}" class="waitQuantity" />
			   <fmt:formatNumber value="${item.qty}" maxFractionDigits="0" /> 
			</td>
			<td id="qty" align="center"><fmt:formatNumber value="${item.qtyOpen}" maxFractionDigits="0" /></td>
			<%--<td align="center"><input size="5" type="hidden" value="${item.qty-item.qtyOpen}" class="receiptQty" />${item.inventoryNumber+item.returnNumber}</td>
			
			--%>
			<td align="center">
				<c:if test="${item.receiptQty==null}">0</c:if>
				<c:if test="${item.receiptQty!=null}"><fmt:formatNumber value="${item.receiptQty}" maxFractionDigits="0" /></c:if>
			</td>																	  
			
			<!--<td align="center">${item.qty_std}</td>
					<td>
						<input type="text" size="6" id="${item.id}_qty_std"/><input type="button" value="确认" onclick="qtyStdUpdate('${item.id}')" size="6"/>
					</td>
		--></tr>
		
	</tbody>
</table>
<hr width="100%"/>
<table>
	<tr>
		<%--<td>
		<c:if test="${X_OBJECT.isViewItem.enumCode==1}">
			<input type="button" value="<bean:message key="isConfirm.viewItem"/>" onclick='isConfirmViewItem("${X_OBJECT.id}")'/>
		</c:if>
		</td>
		--%><td><input type="button" value="<bean:message key="all.back"/>" onclick="history.go(-1)"/></td>
	</tr>
</table>
  </body>
<script type="text/javascript">
    applyRowStyle(document.all('item_datatable'));
</script>
</html>