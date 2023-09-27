<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script type="text/javascript">
var fahuoDate="";
function selectSuuper(){
	var r = window.showModalDialog(
			'showDialog.do?title=all.select&selectSupplierByPortalShipOrder.do',null,'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
	if (r){
		var resultList = r;
		for(i = 0;i<resultList.length; i++){
			var v=resultList[i];
			document.getElementById("supplierCode").value=v['code'];
			document.getElementById("supplierCodeSpan").innerHTML=v['code'];
		}
	}
}
function selectWmsPart(){
		var partStr=""; 
		var part=document.getElementsByName("ids");
		var date=document.getElementById("receivingDate").value;
		var supplierCode=document.getElementById("supplierCode").value;
		if(date==""){
			alert("请选择发货时间!");
			return false;
		}
		for(var i=0;i<part.length;i++){
			partStr +=part[i].value+",";
		}
		var r = window.showModalDialog(
			'showDialog.do?title=all.select&selectWmsPartByPortalShipOrder.do?portalShipOrderByPartId='+partStr+"&receivingDate="+date+"&supplierCode="+supplierCode, 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (r) { 
			var resultList = r; 
			for(i = 0;i<resultList.length; i++){
				var v=resultList[i];
				var trString = "<tr align='center'>";   
		        trString += "<td>"+"<input type=\"hidden\" name=\"ids\" value=\""+(v['id'])+"\" />"+v['id']+"</td>";
		        trString += "<td>"+v['name']+"</td>";
		        trString += "<td>"+v['describe1']+"</td>";
		        trString += "<td>"+v['vend']+"</td>";
		        trString += "<td>"+v['unit']+"</td>";
		        trString += "<td>"+v['ord_mult']+"</td>";
		        trString += "<td>"+v['securityQty']+"</td>";
		        trString += "<td>"+v['projectedQty']+"</td>";
		        trString += "<td>"+"<input type=\"hidden\" name=\"deliveryNumbers\" value=\""+(v['fahuoNumber'])+"\" />"+v['fahuoNumber']+"</td>";
                trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'><bean:message key='all.delete' /></a></td>";                 
		      trString += "</tr>"; 
		     $("#datatable2").append(trString).show();
          }
		};
		var count=0;
		var number=document.getElementsByName("deliveryNumbers");
		for(var i=0;i<number.length;i++){
			count =parseFloat(count)+parseFloat(number[i].value);
		}
		document.getElementsByName("sum_number")[0].innerHTML=count;
		 applyRowStyle(document.all('datatable2'));
	}
function detailDeleteRow(obj){	
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable2');
		ttbody.deleteRow(oldRow.sectionRowIndex);
		var count=0;
		var number=document.getElementsByName("deliveryNumbers");
		for(var i=0;i<number.length;i++){
			count =parseInt(count)+parseInt(number[i].value);
		}
		document.getElementsByName("sum_number")[0].innerHTML=count;
	}
function validateForm(){
	var date=document.getElementById("receivingDate").value;
	var ids = document.getElementsByName("ids");
	var d=new Date();
	var str = d.getFullYear()+"/"+(d.getMonth()+1)+"/"+d.getDate(); 
	var newDate=new Date(str).getTime();
	var deliveryDate= new Date(date).getTime();
	if(deliveryDate<newDate){
		alert("发货日期不能小于当前日期!");
		return false;
	}
	if(date.length<=0){
		alert("请选择发货日期!");
		return false;
	}
	if(ids.length>0){
		document.getElementsByName("submita")[0].disabled=true;
		return true;
	}else{
		alert("未选择物料不能创建发货单!");
		return false;
	}
}
function updateDa(){
	var date=document.getElementById("receivingDate").value;
	if(fahuoDate==date){
	}else{
		window.location.href="newPortalShipOrderTwoWrong.do";
	}
}
</script>
<html:form action="/createPortalShipOrderTwoWrongByPart" onsubmit="return validateForm();">
<input type="hidden" id="createType" name="createType" value="NJIT_NPO"/>
<input type="hidden" id="supplierCode" name="supplierCode" value="">
<table width="100%" border="0" cellpadding="4" cellspacing="0">
	<tr >
			<td width="10%" class="bluetext">
					到货日期：
			</td>
			<td class="bluetext">
					<html:text property="receivingDate"  size="15" readonly="true"/> 
					<a
					href="javascript:showCalendar('dimg1',false,'receivingDate',null,null,'purchaseOrderInspectionPendingQueryForm')"><img
					align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
			</td>
	</tr>
</table>
	<table width="100%" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td width="10%" colspan="4">
				<input type="button" value="1.选择供应商" style="width: 100px" onclick="selectSuuper()"/>
			</td>
			<td align="left"><span style="font-size: 16px;" id="supplierCodeSpan"></span> </td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td colspan="4">
				<input type="button" value="2.选择物料 " style="width: 100px" onclick="selectWmsPart()"/>
			</td>
			
		</tr>
	</table>
<h3 style="color:blue"><bean:message key="purchaseOrderPart.item"/></h3>
	<table  class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
							<th>物料编码</th>
							<th>物料名称</th>
							<th>车型</th>
							<th>供应商编码</th>
							<th>单位</th>
							<th>标准包装量</th>
							<th>安全库存</th>
							<th>预计库存</th>
							<th>发货数量</th>
							<th>操作</th>
						</tr>
					</thead>
				<tbody id="datatable2">
				
			    </tbody>
			   
	</table>
		<table width=100% border=1 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		 	<tr align="right">
				<td class="bluetext">合计数量:&nbsp;&nbsp;&nbsp;<span id="sum_number"></span>&nbsp;&nbsp;</td>
			 </tr>
		</table>
	<table width="100%">
		<tr style="display: none;">
			<td width="100%" colspan="4"><hr/></td>
		</tr>
		<tr>
			<td colspan="4" align="right">
			<input  id="submita" type="submit" value="生成发货单" />
			</td>
		</tr>
	</table>
</html:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
</script>