<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'viewBySupplier.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript" type="text/javascript">
function pdfConfirmBySupplier(){
	var md5s=document.getElementById("md5s").value;
	var id="${x_purchaseOrderInspectionPending.id}";
	window.location.href="pdfConfirmBySupplierPurchaseOrder.do?id=" + id+"&md5="+md5s;
}

function printCode(){	
	window.location.href = "printPurchaseOrderReceiptsLotList.do?id=${x_purchaseOrderInspectionPending.poip_number}";
}

</script>
  </head>
  
  <body>
<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
 		 <td width="15%" class="bluetext"><bean:message key="purchaseOrder.site"/>:</td>
 		 <td width="35%">${x_purchaseOrderInspectionPending.site.name}</td>
		 <td width="15%"  class="bluetext"><bean:message key="purchaseOrder.department"/>:</td>
 		 <td width="35%">${x_purchaseOrderInspectionPending.department.name}</td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrder.id" />:</td>
			<td>${x_purchaseOrderInspectionPending.po_number.poOrder}</td>
			<td class="bluetext"><bean:message key="purchaseOrder.poDate" />:</td>
			<td>${x_purchaseOrderInspectionPending.po_number.poDate}</td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrder.supplierCode" />:</td>
		<td>${x_purchaseOrderInspectionPending.supplier.code}</td>
		<td class="bluetext"><bean:message key="purchaseOrder.supplierName" />:</td>
		<Td>${x_purchaseOrderInspectionPending.supplier.name}</Td>
		</tr>
		<tr>
		<td class="bluetext">
				<bean:message key="purchaseOrder.status" />
			</td>
			<td >
				${x_purchaseOrderInspectionPending.status.chnShortDescription}
			</td>
			<td class="bluetext"><bean:message key="purchaseOrder.qadStatus" /></td>
		    <td >
		    ${x_purchaseOrderInspectionPending.describe}
		   </td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrder.remark" /></td>
		<td  colspan="3">
		${x_purchaseOrderInspectionPending.remark}
		</td>
			</tr><tr>
		<td>
		<c:if test="${x_purchaseOrderInspectionPending.pdfmd5!=null}">
	<A href="javascript:viewPDF('${x_purchaseOrderInspectionPending.pdfmd5}')">PDF<img src="images/pdf.gif" border=0/></A>
		</c:if>
		</td>
		</tr>
		
	</table>
		<hr width="100%"/>
	<jsp:include page="itemList.jsp" />
  </body>
</html>
