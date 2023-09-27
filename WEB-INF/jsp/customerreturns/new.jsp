<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
 <script type="text/javascript">
function selectCustomer() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectCustomerCode.title&listCustomerReturn.do', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) { 
			document.getElementById("name").innerHTML=v['code'];
			document.getElementById("code").value=v['id'];
//			var selectid=document.getElementById("returnLocation");
//			if(v['address']!=""){
//				selectid[1]=new Option(v['address'],v['address']);
//			}else if(v['address2']!=""){
//				selectid[2]=new Option(v['address2'],v['address2']);
//			}else if(v['address3']!=""){
//				selectid[3]=new Option(v['address3'],v['address3']);
//			}
		};
	}
function selectLocation(){
	v = window.showModalDialog(
			'showDialog.do?title=expense.selectCustomerCode.title&selectCustomerReturnLocation.do', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) { 
			document.getElementById("returnStorageCode").innerHTML=v['code'];
			document.getElementById("returnStorage").value=v['code'];
		};
}
function selectProductCode() {
		var customerCode=document.getElementById("name").innerHTML;
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectProductCode.title&listProductCode.do?id='+customerCode, 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			<%-- window.onload = function(){
			var content = document.getElementById("returnTable").innerHTML;
			window.parent.itemInnerHtml('${workOrderList}',content);
			}--%>
			//var div=document.getElementById("returnDiv");
			//div.style.display="none";
			var ifrme = document.getElementById("iframeForItem");
			var src="itemListCustomerreturnsAction.do?chanpinId=" + v['id'];
			ifrme.src = src;
			ifrme.style.display="block";
			document.getElementById("chanpin").value=v['id'];
			//document.getElementById("returnStorage").value=v['location_code'];
			document.getElementById("returnQuantity").value=v['qty'];
			document.getElementById("chanpinCode").value=v['id'];
			
		};
	}
<%--function itemInnerHtml(content){
		var td = document.getElementById("returnTable");
		td.innerHTML = content;
	}--%>
function returns(){
	window.location.href="listCustomerreturnsAction.do";
}
function validatedCustomerReturnForm(from){
	var basicCustomer_id =document.getElementById("basicCustomer_id").value;
	if(basicCustomer_id==""){
		alert("请选择客户编号!");
		return false;
	}
	<%--var returnLocation =document.getElementById("returnLocation");
	var index=returnLocation.selectedIndex;
	if(index==0){
		alert("请选择退货地址!");
		return false;
	}--%>
	var chanpin_code=document.getElementById("chanpin").value;
	if(chanpin_code==""){
		alert("请选择产品编号!");
		return false;
	}
	var location_code=document.getElementById("returnStorage").value;
	if(location_code==""){
		alert("请填写退货接收库位!");
		return false;
	}
	var qty=document.getElementById("returnQuantity").value;
	if(qty==""){
		alert("请填写退数量!");
		return false;
	}
	var returnInvoice=document.getElementById("invoiceStatus").value;
	if(returnInvoice==3){
		alert("请选择是否为开票退货!")
		return false;
	}
	var returnDate=document.getElementById("returnDate").value;
	if(returnDate==""){
		alert("请选择退货日期!");
		return false;
	}
	var returnPerson=document.getElementById("returnPerson").value;
	if(returnPerson==""){
		alert("请填写退货人!");
		return false;
	}
	var returnPersonContact=document.getElementById("returnPersonContact").value;
	if(returnPersonContact==""){
		alert("请填写退货人联系方式!");
		return false;
	}
	var returnReasons=document.getElementById("returnReasons").value;
	if(returnReasons==""){
		alert("请填写退货原因!");
		return false;
	}
	return true;
}
function setchanpin(vara,qty){
	document.getElementById("chanpin").value=vara;
	document.getElementById("chanpinCode").value=vara;
	if(qty==0){
		document.getElementById("returnQuantity").value="";
	}else{
		document.getElementById("returnQuantity").value=qty;
	}
	
	
}


</script> 
 <html:form action="/insertCustomerreturnsAction" onsubmit="return validatedCustomerReturnForm(this);">
	<table width="40%" >
	<tr>
 		 <td width="15%" class="bluetext">退货单号:</td>
 		 <td width="15%">
			(<bean:message key="common.id.generateBySystem"/>)
 		 </td>
	</tr>
	<tr>
			<td class="bluetext">客户编码:</td>
			<td>
  				<a style="padding-left: 25px" href="javascript:selectCustomer();"><img src="images/select.gif" border="0"/></a>
  				<span id="name"></span>	
  				<html:hidden property="basicCustomer_id" styleId="code"/><span class="required">*</span>
  			</td>
	</tr>
	<%--<tr>
 		<td width="15%" class="bluetext">退货地点:</td>
		<td><select style="width: 120px" id="returnLocation" name="returnLocation" >
		<option value="请选择" >请选择</option>
		</select><span class="required">*</span></td>
	</tr>
	--%><tr>
		<td class="bluetext">退货批次:</td>
		<td>
			<html:text  style="display: none;"  property="chanpinCode" />
 			<%-- <html:hidden property="part_id" styleId="part_id"/><span class="required">*</span>--%>
  			<a style="padding-left: 25px" href="javascript:selectProductCode();"><img src="images/select.gif" border="0"/></a>
  			<input id="chanpin"  type="hidden" value=""/>
  			<span id="name1"></span>
  			<span class="required">*</span>
  		</td>
	</tr>
	<tr>
		<td class="bluetext">退货接收库位:</td>
		<td>
			<span id="returnStorageCode"></span>
			<a style="padding-left: 25px" href="javascript:selectLocation();"><img src="images/select.gif" border="0"/></a>
			<html:hidden property="returnStorage" styleId="returnStorage"/><span class="required">*</span>
			
  		</td>
		<%--<td><html:text style="width: 120px"  property="returnStorage" /><span class="required">*</span></td>
	--%></tr>
	<tr>
		<td class="bluetext" >客户退货数量:</td>
		<td><html:text style="width: 120px"  property="returnQuantity" /><span class="required">*</span></td>
	</tr>
	<tr>
		<td width="15%" class="bluetext">是否为开票退货:</td>
		<td><html:select property="invoiceStatus">
				<html:option value="3">请选择</html:option>
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
					<html:options collection="X_YESNOLIST" property="enumCode" labelProperty="chnShortDescription" />
				</c:if>
				<c:if test="${sessionScope.LOGIN_USER.locale!='zh'}">
					<html:options collection="X_YESNOLIST"
						property="enumCode" labelProperty="engShortDescription" />
				</c:if>
			</html:select></td>
	</tr>
	<tr>
		<td class="bluetext" >退货日期:</td>
		<td>
				<html:text style="width: 120px" property="returnDate" readonly="true" size="15" />
				<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg1',false,'returnDate',null,null,'customerreturnsForm')"> 
				<img align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a><span class="required">*</span>
		</td>
	</tr>
	<tr>
		<td class="bluetext" >退货人:</td>
		<td><html:text style="width: 120px"  property="returnPerson" /><span class="required">*</span></td>
	</tr>
	<tr>
		<td class="bluetext" >退货人联系方式:</td>
		<td><html:text style="width: 120px" property="returnPersonContact" /><span class="required">*</span></td>
	</tr>
	<tr>
		<td class="bluetext" >退货原因:</td>
		<td><html:textarea property="returnReasons"></html:textarea><span class="required">*</span></td>
	</tr>
	</table>
	<div style="border: 1px sold; width: 200px;padding-left: 60px">
		<html:submit><bean:message key="all.save" /></html:submit>
		<span style="padding-left: 30px">
			<input  type="button" onclick="returns()" id="return" value="返回"/>
		</span>
	</div>
	
	<iframe name="iframeForItem"  id="iframeForItem" width="100%" height="350%"  style="display:none;"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" >
	<jsp:include page="itemList.jsp" />
	</iframe>
	</html:form>
	<%--
	
 <div id="returnDiv">
 <html:form action="itemListCustomerreturnsAction" >
	<input type="hidden" name="chanpinId" value="${chanpin_id}"/>
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesWorkorder.lotSer.id" desc="批次号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.describe1" desc="物料描述"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="salesWorkorder.location.code" desc="库位代码"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
 <page:form action="/itemListCustomerreturnsAction.do">	
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff">
							<th>批次号</th>
							<th>物料编号</th>
							<th>物料描述</th>
							<th>DPI</th>
							<th>库位代码</th>
							<th>数量</th>
							<th>状态</th>
						</tr>
					</thead>
			<!--  	 <tbody id="datatable3">
				<c:forEach items="${workOrderList}" var="X_OBJECT">
				<c:set var="X_OBJECT"  scope="request" value="X_OBJECT"/>
					<tr align="center">
						<td width="100px">${X_OBJECT.lotSer.id}</td>
						<td width="100px">${X_OBJECT.part.id}</td>
						<td width="100px">${X_OBJECT.part.describe1}</td>
						<td width="100px">${X_OBJECT.part.dpiNo}</td>
						<td width="100px">${X_OBJECT.location.code}</td>
						<td width="100px"><fmt:formatNumber value="${X_OBJECT.count}" maxFractionDigits="0"/></td>
						<td width="100px">${X_OBJECT.status1.chnShortDescription}</td>
				</tr>
				</c:forEach>
				</tbody>
				-->
	</table>
	</page:form>
</div>--%>