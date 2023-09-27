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
<table width="100%">
<tr><td align="center">
<h3 style="color: blue">
	PO NO.${x_purchaseOrderInspectionPending.po_number.poOrder}
</h3>

</td></tr><tr><td>
    <table width=100% border=0 cellpadding=4 cellspacing=0 >
	<tr>
		<td width="15%" class="bluetext">
			<bean:message key="purchaseOrder.site" />
			:
		</td>
		<td width="35%">
			${x_site.name}
		</td>
		<td width="15%" class="bluetext">
			<bean:message key="purchaseOrder.department" />
			:
		</td>
		<td width="35%">
			${x_purchaseOrderInspectionPending.department.name}
		</td>
	</tr>
	<tr>
		<td class="bluetext">
			<bean:message key="purchaseOrder.id" />
			:
		</td>
		<td>
			${x_purchaseOrderInspectionPending.po_number.poOrder}
		</td>
		<td class="bluetext">
			<bean:message key="purchaseOrder.poDate" />
			:
		</td>
		<td>
			${x_purchaseOrderInspectionPending.po_number.poDate}
		</td>
	</tr>
	<tr>
		<td class="bluetext">
			<bean:message key="purchaseOrder.supplierCode" />
			:
		</td>
		<td>
			${x_purchaseOrderInspectionPending.supplier.code}
		</td>
		<td class="bluetext">
			<bean:message key="purchaseOrder.supplierName" />
			:
		</td>
		<Td>
			${x_purchaseOrderInspectionPending.supplier.name}
		</Td>
	</tr>
	<tr>
		<td class="bluetext">
			<bean:message key="purchaseOrder.status" />
		</td>
		<td>
			${x_purchaseOrderInspectionPending.status.chnShortDescription}
		</td>
		<td class="bluetext">
			<bean:message key="purchaseOrder.qadStatus" />
		</td>
		<td>
			${x_purchaseOrderInspectionPending.describe}
		</td>
	</tr>
	<tr>
		<td class="bluetext">
			<bean:message key="purchaseOrder.remark" />
		</td>
		<td colspan="3">
			${x_purchaseOrderInspectionPending.remark}
		</td>


	</tr>
<tr>
<td colspan="4">

<hr width="100%" />
<jsp:include page="itemListTest.jsp" />
<hr />
</td>
</tr>
<tr>
	<td align="center">
     <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印装箱单"  onclick="printCode()"/></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Print label"  onclick="printCode()"/></c:if>
    </td>
    <td align="left" colspan="3"><input type="button" value="<bean:message key="return" />"  onclick="history.go(-1)"/></td>
</tr>
<tr><td  colspan="4">
<h3 style="color: blue">
	QC PDF
</h3>
<c:if test="${x_purchaseOrderInspectionPending.pdfmd5!=null}">
	<bean:message key="Has.been.imported.successfully"/>
</c:if>

<c:if test="${x_purchaseOrderInspectionPending.pdfmd5==null}">
<table width="100%">
	<tr>
		<td>
			<div id="FilePanel"></div>
			<div id="upPnl"></div>

		</td>
	</tr>
	<tr>
		<td colspan="2">
			<hr />
		</td>
	</tr>
	<tr id="upoperation">
		<td colspan="2" align="left">
			 <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="浏览" id="btnSel" /></c:if>
            <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="browse" id="btnSel" /></c:if>
		</td>
	</tr>
	<tr id="upoperation2" style="display: none;">
		<td colspan="2" align="left">
		    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="确认导入完成"  onclick="pdfConfirmBySupplier()"/></c:if>
            <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Confirm the import is complete"  onclick="pdfConfirmBySupplier()"/></c:if>
		</td>
	</tr>
</table>
</c:if>

</td></tr>
</table>
<input  type="hidden" id="md5s" />
<c:if test="${x_purchaseOrderInspectionPending.pdfmd5==null}">
<script language="javascript" type="text/javascript">
var upMgr = new HttpUploaderMgr();

	upMgr.LoadInControl("FilePanel");
	upMgr.Init();
	$("#btnSel").click(function() {
		upMgr.UploadSingle();//上传
		});

</script>
</c:if>
</td></tr>
</table>
  </body>
</html>
