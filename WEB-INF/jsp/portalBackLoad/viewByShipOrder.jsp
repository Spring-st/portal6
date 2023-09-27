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
    
    <title>My JSP 'viewByShipOrder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript" type="text/javascript">
function saveAsnNo(id){
	var asnNo = document.getElementById("asnNo").value;
	if(asnNo==""||asnNo==null){
		alert("请输入ASN编号并保存");
		return false;
	}else{
		var url ='saveAsnno.do?asnNo='+asnNo+'&id=' + id;
		window.location.href = url;
	}
}
function printAsn(id) {
	var asnNo = document.getElementById("asnNo").value;
	if(asnNo==""||asnNo==null){
		alert("请输入ASN编号,保存后打印");
		return false;
	}else{
		var url = "printAsn.do?id=" + id;
		window.location.href = url;
	}
}
function confirmPrint(){
	
}
function savePSOAttach(id){
	var url = "insertShipOrderAttach.do?id="+id;
	window.location.href = url;
}
function view(id) {
	var url = "viewBySupplierPurchaseOrder.do?id=" + id;
	window.location.href = url;
}
function ret(){
	var url = "listShippingOrder.do";
	window.location.href = url;
}

function delete2(id){ 
	var psoId = ${x_portalShipOrder.id};
	var url = 'deleteShipOrderAttach.do?psoId=' +psoId+'&id=' +id;
	window.location.href = url;
}
</script>
  </head>
  
  <body>
   <table width="100%">
<tr><td align="center" colspan="2">
<h3 style="color: blue">
	SO NO.${x_portalShipOrder.code}
</h3>
</td>
</tr>
<tr>
	<td class="bluetext">
		<bean:message key="purchaseOrder.site" />:
	</td>
	<td align="left">
		${x_portalShipOrder.site.name}
	</td>
</tr>
<tr>
	<td class="bluetext" ><bean:message key="portalShipOrder.createDate" />:</td>
	<td  align="left">${x_portalShipOrder.createDate}</td>
</tr>
<tr>
	<td colspan="2" align="left">
		<html:form action="saveAsnno" >
		<table align="left" width="70%">
			<tr>
				<td class="bluetext"><bean:message key="portalShipOrder.Asn.No" />:</td>
				<td><%--<html:text property="asnNo" styleId="asnNo" size="15" onclick=""/>${x_portalShipOrder.asnNo}--%>
					<input type="text" id="asnNo" value="${x_portalShipOrder.asnNo}"/>
				</td> 
				<c:if test="${x_portalShipOrder.isPrint=='0'}">
				<td align="center" style="display: none">
				<input type="button" value="<bean:message key="Save" />" onclick="saveAsnNo('${x_portalShipOrder.id}')"/></td>
				</c:if>
				<c:if test="${x_portalShipOrder.isPrint!='0'||x_portalShipOrder.isPrint==null}">
				<td align="center">
				<input type="button" value="<bean:message key="Save" />" onclick="saveAsnNo('${x_portalShipOrder.id}')"/></td>
				</c:if>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<c:if test="${x_portalShipOrder.isPrint=='0'}">
				<td align="center" style="display: none">
				<input type="button" value="<bean:message key="Print.ASN" />" onclick="printAsn('${x_portalShipOrder.id}')"/>
				</td>
				</c:if>
				<c:if test="${x_portalShipOrder.isPrint!='0'||x_portalShipOrder.isPrint==null}">
				<td align="center">
				<input type="button" value="<bean:message key="Print.ASN" />" onclick="printAsn('${x_portalShipOrder.id}')"/>
				</td>
				</c:if>
				<td><%--<input type="button" value="<bean:message key="Confirm.Print.Finish" />" onclick="confirmPrint('${x_portalShipOrder.id}')"/>--%></td>
				<td></td>
			</tr>
		</table>
		</html:form>
	</td>
</tr>
<tr>
<td colspan="2">
	<hr/>
<h3 style="color:blue"><bean:message key="purchaseOrder.item"/></h3>
</td>
</tr>
<tr><td colspan="2">

<table width="100%" border="1" cellpadding="4" cellspacing="0" class="data">
			<thead>
				<tr bgcolor="#9999ff">
					<th width="15%">
						<bean:message key="PO.No"/>
					</th>
					<%--<th width="8%">
						<bean:message key="Pending.PO.No"/>
					</th>
					--%><th width="8%">
						<bean:message key="supplier"/>
					</th>
					<th width="8%">
						<bean:message key="supplier.code"/>
					</th>
					<th width="8%">
						<bean:message key="order.date"/>
					</th>
					<th width="8%">
						<bean:message key="Status"/>
					</th>
					<th width="8%">
						<bean:message key="Remark"/> 
					</th>
				</tr>
			</thead>
			<tbody id="datatable">
			<logic:iterate id="str" name="x_portalShipOrderItemList">
				<bean:define id="str" toScope="request" name="str" />
				<tr>
							<td width="15%" align="center">
								<a href='javascript:view("${str.poNumber.poip_number}")'>${str.poNumber.po_number.poOrder}</a>
							</td>
							<td width="8%">
								${str.poNumber.supplier.name}
							</td>
							<td width="8%">
								${str.poNumber.supplier.code}
							</td>
							<td width="8%" align="center">
								${str.poNumber.po_number.poDate}
							</td>
							<td width="8%">
								${str.poNumber.status.chnShortDescription}
							</td>
							<td width="8%">
								${str.poNumber.remark}
							</td>
				</tr>
			</logic:iterate>
		</tbody>
</table>  
</td>
</tr>
<tr>
	<td colspan="2">
	<hr/>
	
	<%--<html:file property="fileContent" size="15" />
	--%>
		<html:form action="insertShipOrderAttach.do?id=${x_portalShipOrder.id}" enctype="multipart/form-data" >
		<table align="left" width="100%">
			<tr>
				<td class="bluetext" align="left"><bean:message key="portalShipOrder.Asn.attachment" />:</td>
				<td width="85%" align="left">
					<html:file property="fileContent"></html:file>
				</td>
			</tr>
			<tr><td></td><td></td></tr>
			<tr>
				<td></td>
				<td><input type="submit" value="<bean:message key="Upload" />" /></td>
			</tr>
		</table>
		</html:form>
	</td>
</tr>
<tr>
	<td colspan="2" align="left" style="color: green">
	${msg1}
	</td>
</tr>
<tr>
	<td colspan="2">
	<h3 style="color:blue"><bean:message key="purchaseOrder.attach"/></h3>
	</td>
</tr>
<tr>
	<td colspan="2" align="left">
		<table width="100%" border="1" cellpadding="4" cellspacing="0" class="data">
		<c:choose>
			<c:when test="${x_portalShipOrderAttachList == null}">
				<h3><bean:message key="there.is.no.attach.detail"/></h3>
			</c:when>
			<c:otherwise>
				<thead>
					<tr bgcolor="#9999ff">
						<th width="8%">
							<bean:message key="psoa.fileName"/>
						</th>
						<th width="8%">
							<bean:message key="psoa.creater"/>
						</th>
						<th width="8%">
							<bean:message key="psoa.uploadDate"/>
						</th>
						<th width="8%">
							<bean:message key="all.operate"/>
						</th>
					</tr>
				</thead>
				<c:forEach var="str" items="${x_portalShipOrderAttachList}" varStatus="s">
							<tr>
								<td width="15%" align="center">
									<a href="downloadShipOrderAttach.do?id=${str.id}">${str.fileName}</a>
								</td>
								<%--<td width="8%" align="center">
									${str.poNumber.po_number.poOrder}
								</td>--%>
								<td width="8%">
									${str.supplier.name}
								</td>
								<td width="8%">
									<c:if test="${str.uploadDate!= null }">
										<bean:write name="str" property="uploadDate" format="yyyy/MM/dd" />
									</c:if>
								</td>
								<td width="8%">
									<a href='javascript:delete2("${str.id}")'>删除</a>
								</td>
						</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>  
	</td>
</tr>
<tr>
	<td colspan="2">
	<jsp:include page="viewByShipOrderApprove.jsp"></jsp:include>
	</td>
</tr>
<tr>
	<td colspan="2" align="left">
	<br>
		<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="<bean:message key="return" />"  onclick="ret()"/></c:if>
         <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Return"  onclick="ret();"/></c:if>
	</td>
</tr>
</table>
  </body>
</html>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>