<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
 function selectTool() {
		v = window.showModalDialog(
			'showDialog.do?title=assembly2 expense.selectTA.title&selectToolCode1.do', 
			null, 'dialogWidth:1024px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("toolid").value=v['id'];
			document.getElementById("toolSpan").innerHTML=v['toolCode'];
		};
	}

 function selectLocation() {
		v = window.showModalDialog(
			'showDialog.do?title=assembly2 expense.selectTA.title&selectStorageLocation1.do', 
			null, 'dialogWidth:1024px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("locationId").value=v['id'];
			document.getElementById("locationSpan").innerHTML=v['code'];
			document.getElementById("addressAddress").innerHTML=v['address'];
		};
	}
function validateProductInStorageFrom(){
	var toolCode=document.getElementById("toolid").value;
	var locationCode=document.getElementById("locationId").value;
	var capacity=document.getElementById("capacity").value;
	var totalnumber=document.getElementById("totalNumber").value;
	if(locationCode==""){
		alert('请选择库位！');	
		return false;
	}
	if(toolCode==""){
		alert('请选择器具');
		return false;
	}
	if(parseInt(capacity)<parseInt(totalnumber)){
		alert('出库数量必须小于等于器具盛放数量！');
		return false;
	}
	return true;

}
//-->
</script>

<form name="productDownForm" action="productDownInStorage.do"  onsubmit="return validateProductInStorageFrom();">
<div style="font-size: 20px; color: blue; text-align: center; margin: 10px 0px;">成品下线入库</div>
<table style="margin: 20px 0px;" width="100%">
		<thead>
			<tr style="background-color: orange">
				<th>海纳川条码</th>
				<th>赛赫条码</th>
				<th>零件号(轮胎)</th>
				<th>零件号(轮毂)</th>
				<th>零件号(气门嘴)</th>
			</tr>
		</thead>
		<tbody id="datatable" >
			 <c:forEach var="x_object" items="${X_PRODUCTLIST}">
			        <tr>
                       <td>${x_object.hncCode}
                       	<input type="hidden" name="ids" value="${x_object.id}" />
                       </td>
                       <td>${x_object.shCode}</td>
                       <td>${x_object.partTireCode}</td>
                       <td>${x_object.partHubCode}</td>
                       <td>${x_object.partValvestemCode}</td>
                    </tr>		
			  </c:forEach>
		</tbody>
	</table>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
		
		 <tr>
			<td class="bluetext">器具编号:</td>
			<td><input type="hidden" id="toolid" name="tool"/>
				<span id="toolSpan"><%--${X_PRODUCTTOOL.toolCode}--%></span>
				<a href="javascript:selectTool();"><img src="images/select.gif" border="0"/></a>
			</td>
			<td><%--<input type="hidden" id="toolId" name="toolId" value="${X_PRODUCTTOOL.id}"/>--%></td>
			<td class="bluetext">包装量：</td>
			<td id="capacityId" style="color: red; font-weight: bolder;">${X_PRODUCTTOOL.putNumber}</td>
			<td><input type="hidden" id="capacity" name="capacity" value="${X_PRODUCTTOOL.putNumber}"/>
			<input type="hidden" id="totalNumber" value="${X_PRODUCTQTY}" />
			</td>
			</tr>
		 <tr>
			<td class="bluetext">库位编号:</td>
			<td><input type="hidden" id="locationId" name="locationId"/>
			<span id="locationSpan"></span>
			<a href="javascript:selectLocation();"><img src="images/select.gif" border="0"/></a>
			</td>
			<td></td>
			<td class="bluetext">地址:</td>
			<td>
			<span id="addressAddress"></span></td>
			</tr>
	</table>
	<hr/>

	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /></html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
