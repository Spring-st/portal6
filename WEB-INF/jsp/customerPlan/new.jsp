<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
function getNowDate(input){
	var now = new Date(); 
	input.value=now.getYear()+"/"
		+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"/"
		+(now.getDate()<10?"0":"")+now.getDate();
}
function window.onload(){
	var input=document.getElementById("date");
	//getNowDate(input);
}
function selectBasicCustomer() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectBasicCustomer.do', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("code").innerHTML=v['code'];
			document.getElementById("name").innerHTML=v['name'];
			document.getElementById("customerAddress").value=v['address'];
			document.getElementById("customer_id").value=v['id'];
			document.getElementById("selectImg").style.display = "none";
		};
	}
function selectWmsPart() {
	
	var customer_id= document.getElementById("customer_id").value;
	if(customer_id==null || customer_id==""){
		alert("请先选择客户！");
		return false;
	}
	var selectSoipitemId= document.getElementById("selectSoipitemId").value;
	var partStr=""; 
	var part=document.getElementsByName("partId");
		for(var i=0;i<part.length;i++){
			partStr +=part[i].value+",";
		}
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectCustomerPlanDeliveryWmsPart.do?partId='+partStr+"&customerId="+customer_id, 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) { 
		var resultList = v; 
			for(i = 0;i<resultList.length; i++){
				var v=resultList[i];
				var trString = "<tr class='aboveRow'>";   
		        trString += "<td align='center'>"+"<input type=\"hidden\" name=\"partId\" value=\""+(v['id'])+"\" />"+v['id']+"</td>";
		        trString += "<td align='center'>"+v['oldCode']+"</td>";
		        trString += "<td align='center'>"+v['name']+"</td>";
		        trString += "<td align='center'>"+v['carBrand1']+"</td>";
		        trString += "<td align='center'>"+v['domesticCar1']+"</td>";
		        trString += "<td align='center'>"+v['yearFrom1']+"</td>";
		        trString += "<td align='center'>"+v['yearTo1']+"</td>";
		        trString += "<td align='center'>"+v['productSpecifications']+"</td>";
		        trString += "<td align='center'>"+"<input type=\"hidden\" id=\""+v['id']+"\" name=\"partPrices\" value=\""+(v['partPrice'])+"\" />"+v['partPrice']+"</td>";
		        trString += "<td align='center'>"+"<input type=\"hidden\" id=\""+v['id']+"\" name=\"sotaxcs\" value=\""+(v['sotaxc'])+"\" />"+" <input type=\"hidden\" id=\""+v['id']+"\" name=\"currs\" value=\""+(v['curr'])+"\" />"+v['sotaxc']+"</td>";
		        trString += "<td align='center'>"+"<input type=\"hidden\" id=\""+v['id']+"\" name=\"deliveryNumbers\" value=\""+(v['deliverNumber'])+"\" />"+v['deliverNumber']+"</td>";
                trString += "<td align='center'><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this,\""+v['id']+"\")'><bean:message key='all.delete' /></a></td>";                 
		      trString += "</tr>"; 
		      selectSoipitemId+=v['id']+";";
		     // alert(trString);
		     $("#datatable2").append(trString).show();
          }
			 var count=0;
			 var number=document.getElementsByName("deliveryNumbers");
			 for(var i=0;i<number.length;i++){
				count =parseInt(count)+parseInt(number[i].value);
			 }
			 document.getElementById("sum_number").innerHTML=count;
		};
}
function detailDeleteRow(obj,id){
		//alert(123);
		var selectSoipitemId= document.getElementById("selectSoipitemId").value;
		
		var number= document.getElementById(id).value;
		 var count=document.getElementById("sum_number").innerHTML;
		 count =parseInt(count)-parseInt(number);
		document.getElementById("sum_number").innerHTML=count;
		
		var pattern = id;
		selectSoipitemId = selectSoipitemId.replace(new RegExp(pattern), "");
		//console.log(selectSoipitemId);
		document.getElementById("selectSoipitemId").value=selectSoipitemId;
		
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable2');
		ttbody.deleteRow(oldRow.sectionRowIndex);
		
	}
function validatecustomerPlanForm(form){
	var customerid=document.getElementById('customer_id').value;
	if(customerid=="" || customerid.length<1){
		alert("请选择客户!");
		return false;
	}
	var partId=document.getElementsByName('partId');
	if(partId.length<1){
		alert("请选择物料!");
		return false;
	}
	var customerAddress=document.getElementById('customerAddress').value;
	if(customerAddress=="" || customerAddress.length<1){
		alert("送货地址不能为空!");
		return false;
	}
	var partPrices=document.getElementsByName("partPrices");
	for(var i=0;i<partPrices.length;i++){
		
		if(partPrices[i].value=="无有效价格"){
		//if(isNaN(partPrices[i].value)){
			alert(partPrices[i].id+"无有效价格，不能创建客户需求计划！");
			return false;
		}
	}
	return true;
}
//-->
</script>

<html:form action="/insertCustomerPlan" onsubmit="return validatecustomerPlanForm(this)">
<input type="hidden" id="selectSoipitemId"  value=""/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>

		<tr>
			<td width="15%" class="bluetext">客户编号：</td>
			<td  width="20%" ><span id="code"></span>
				<html:hidden property="customer_id" styleId="customer_id"/><span class="required">*</span>
  				<a href="javascript:selectBasicCustomer();" id="selectImg"><img src="images/select.gif" border="0"/></a>
  			</td>
			
			<td width="15%" class="bluetext">客户名称:</td>
			<td>
				<span id="name"></span>
			</td>
		</tr>
		<%--<tr>
			<td class="bluetext">发货工厂</td>
			<td><html:text property="factory"/></td>
			<td class="bluetext">域</td>
			<td><html:text property="poDomain"/></td>
		
		</tr>
		--%><tr>
			<td width="15%" class="bluetext"><bean:message key="customer_plan.shipmentdate" />:</td>
			<td>
				<html:text property="shipmentDate" readonly="true" size="15" />
				<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg1',false,'shipmentDate',null,null,'customerPlanForm')"> 
				<img align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /> </a>
			</td>
			<td class="bluetext">送货地址</td>
			<td><html:text property="customerAddress"  styleId="customerAddress"/></td>
		</tr>
		<tr>
			<td class="bluetext"  width="15%">描述：</td>
			<td><html:textarea property="describe" cols="50" rows="2"/></td>
		
		</tr>
		</table>
		<hr/>
		<table width="100%" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td colspan="4" align="left"">
				<input type="button" value="选择物料" onclick="selectWmsPart()"/>
			</td>
		</tr>
	</table>
		<table  class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff">
						  <th>物料编号</th>
							<th>原厂编号</th>
							<th>物料名称</th>
							<th>国内品牌1</th>
							<th>国内车型1</th>
							<th>年份起1</th>
							<th>年份讫1</th>
							<th>产品规格</th>
							<th>单价</th>
							<th>基准</th>
							<th>需求数量</th>
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
	<table width="100%" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td width="90%" align="right">
				<html:submit><bean:message key="all.save" /></html:submit>
			</td>
			<td width="10%" align="right">
				<input type="button" value="<bean:message key="all.back" />" onclick="history.back()">
			</td>
		</tr>
	</table>
</html:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
</script>
