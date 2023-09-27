<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script language="javascript" src="includes/table.js"></script>

<script type="text/javascript">


 function uncheckAll() {  
        var el = document.getElementsByName('ids');   
  		var len = el.length;   
  		for(var i=0; i<len; i++){   
  			if((el[i].type=="checkbox")&&(el[i].checked==true)){
				el[i].checked= false;
  			}   
  		}
   } 
   function checkedInput(obj){
	   if(obj.checked==true)
		   checkAll();
	   else
		   uncheckAll();
	    
   }
   function checkAll() {  
		var el = document.getElementsByName('ids');   
  		var len = el.length;   
  		for(var i=0; i<len; i++)   
  		{   
  			if((el[i].type=="checkbox")&&(el[i].checked==false))   
  			{
				el[i].checked= true;
  			}   
  		}
    }  
   
   
   function check(){
	   var el = document.getElementsByName('ids');   
  		var len = el.length;   
  		var count=0;
  		for(var i=0; i<len; i++)   
  		{   
  			if((el[i].type=="checkbox")&&(el[i].checked==true))   
  			{
				count++;
  			}   
  		}
  		
  		if(count ==len){
  			document.getElementById("checkAll").checked=true;
  		}else{
  			document.getElementById("checkAll").checked=false;
  		}
   }



function view(id) {
	var url = "viewBySupplierPurchaseOrder.do?id=" + id;
	window.location.href = url;
}




 	function generate(code){
 	//	alert("aaa");
 		var tempFactpry="";
 		var check = document.getElementsByName('ids');
 		//alert(check.length);
		var result = new Array();
		var j=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked == true){
				
				var id=check[i].value;
				var qty_box=document.getElementById("qty_box"+id).value;
				var ord_mult=document.getElementById("td_itemNumberOrdMult"+id).innerHTML;
				if(qty_box<=0){
					if(ord_mult<=0){
						alert("请先维护包装箱容量！");
						return false;
					}
				}
				var deliverNumber = document.getElementById('td_fahuoNumber'+id).value;
				var qtyOpen = document.getElementById('td_qtyOpen'+id).innerHTML;
				var weifaNumber=qtyOpen-deliverNumber;
				var map = [];
				map['id'] = id;
				map['code'] = code;
				map['poipItemId'] = document.getElementById('td_poId'+id).value;
				map['poipNumber'] = document.getElementById('td_poOrder'+id).innerHTML;
				map['line'] = document.getElementById('td_line'+id).innerHTML;
				map['itemNumberId'] = document.getElementById('td_itemNumberId'+id).innerHTML;
				//map['itemNumberoldCode'] = document.getElementById('td_itemNumberoldCode'+id).innerHTML;
				map['itemNumberName'] = document.getElementById('td_itemNumberName'+id).innerHTML;
				map['supplierItemNumber'] = document.getElementById('td_supplierPartCode'+id).innerHTML;
				map['supplierCode'] = document.getElementById('td_supplierCode'+id).innerHTML;
				map['supplierName'] = document.getElementById('td_supplierName'+id).innerHTML;
				map['um'] = document.getElementById('td_unit'+id).innerHTML;
				map['qtyOpen'] = weifaNumber;
				map['caigouNumber'] = document.getElementById('td_caigouNumber'+id).innerHTML;;
				map['fahuoNumber'] = deliverNumber;
				map['createDate'] = document.getElementById('td_createDate'+id).innerHTML;
				map['hege'] = "";
				map['tuihuo'] = "";
				map['duDate'] = document.getElementById('td_dueDate'+id).innerHTML;
				map['factpry'] = document.getElementById('td_factpry'+id).innerHTML;
				map['vd_promo']=document.getElementById("vd"+id).innerHTML;
				//alert(map["vd_promo"]);
				
				//工厂防重
				if(tempFactpry==""){
					tempFactpry=document.getElementById('td_factpry'+id).innerHTML;
				}else{
					if(tempFactpry!=document.getElementById('td_factpry'+id).innerHTML){
						alert("同一张送货单不允许供应多家工厂!");
						return false;
					}
				}
				
				
				result[j]=map;
				j++;
				if(deliverNumber==null || deliverNumber==""){
					alert("请输入要发货的数量！");
					return false;
				}
				if(parseFloat(deliverNumber) <=0 ){
					alert("发货数量不能小于等于0，请重新输入!");
					document.getElementById("td_fahuoNumber"+id).value ="";
					document.getElementById("td_fahuoNumber"+id).focus();
					return false;
				}
				if(parseFloat(deliverNumber) >qtyOpen){
					alert("发货数量不能大于未结数量，请重新输入!");
					document.getElementById("td_fahuoNumber"+id).value ="";
					document.getElementById("td_fahuoNumber"+id).focus();
					return false;
				}
			}
			
		}
		
		
		var ss  = document.getElementsByName("poipItemIds");
			
			for(var i=0;i<ss.length;i++){
				$("input[name='cbox"+ss[i]+"']").attr('disabled','disabled');
				
			}
		
		
		if(j==0){
				alert("请选择采购单!");
		  		return false;
		}
		
			var checkEl= new Array();
			var m=0;
			var bool = true;
			for(i=0;i<check.length;i++){
				if(check[i].checked == true){
					checkEl[m]=check[i];
					m++;
				}
			}
			
			
			if(checkEl.length>1){
				for(var i=0;i<checkEl.length;i++){
					var val=document.getElementById("vd_promo"+checkEl[i].value).value;
					for(var j=i+1;j<checkEl.length;j++){
						//alert(j);
						var vd=document.getElementById("vd_promo"+checkEl[j].value).value;
						if(vd!=val){
							bool=false;
						}
					}

				}
			}
			
			if(!bool){
				alert("同一张发货单不能同时既发给工厂又发给中转库！");
				return false;
			}
			

		window.parent.returnValue = result;
		window.parent.close();
	 }



 	function printExcel(){
		window.location.href = "listPurchase.do?isExcel=true";
	}
function computePurchaseItemTwoProduct(){ 
	var el = document.getElementsByName('ids');
	var len = el.length;   
	var str="";
	for(var i=0; i<len; i++){   
		if((el[i].type=="checkbox") && (el[i].checked== true)){
			if(el[i].id!="checkAll"){
				if(str==""){
					str = str + el[i].value;
				}else{
					str = str +","+ el[i].value;
				}
			}
		}   
	}
	if(str!=""){
		$("#data").html("");
		$.ajax({         
			type:"POST", //请求方式        
			url:"computePurchaseItemTwoProductByAjax.do", //请求路径        
			cache: false,           
			data:"arrays="+str,  //传参        
			dataType: 'json',   //返回值类型        poipItemList
			success:function(json){
				//alert(json.length);
				for(var i=0;i<json.length;i++){
					var trString = "<tr align='center'>";   
					trString += "<td>"+"<bean:message key="purchaseOrder.itemCode1" />"+":"+"</td>";
					trString += "<td>"+json[i].part+"</td>";
					trString += "<td>"+"计划数量"+":"+"</td>";
					trString += "<td>"+json[i].qty+"</td>";
					trString += "<td>"+"在途数量"+":"+"</td>";
					trString += "<td>"+json[i].transitQty+"</td>";
					trString += "<td>"+"<bean:message key="projectedinventory.currentqty" />"+":"+"</td>";
					trString += "<td>"+json[i].currentQty+"</td>";
					trString += "<td>"+"推荐数量"+":"+"</td>";
					trString += "<td>"+json[i].recommendQty+"</td>";
					trString += "<td>"+"<bean:message key="wmspart.highQty" />"+":"+"</td>";
					trString += "<td>"+json[i].highQty+"</td>";
					trString += "<td>"+"<bean:message key="wmspart.lowQty" />"+":"+"</td>";
					trString += "<td>"+json[i].lowQty+"</td>";
					trString += "<td>"+"提示"+":"+"</td>";
					if(json[i].message==""){
						trString += "<td>"+json[i].message+"</td>";
					}else{
						trString += "<td style=\"\color: red;font-weight: 500;\"\>"+json[i].message+"</td>";
					}
					trString += "</tr>"; 
		          	$("#data").append(trString).show();
				}
			} 
		})
	}else{
		$("#data").html("");
	}
}
</script>
<html:form action="listPurchaseItemTwo" >

	<input type="hidden" name="fields" value="poitem.poip_number.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="poitem.poip_number.supplier.code" desc="供应商编码"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.id" desc="物料号"/>
	<%--<input type="hidden" name="fields" value="poitem.itemNumber.dpiNo" desc="DPI"/>--%>
	<input type="hidden" name="fields" value="poitem.receivingDate" desc="交货日期"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<html:hidden property="selectPoipItemId" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
	
	<!-- 
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
		<%--<td class="bluetext">
			<bean:message key="supplier"/>:
		</td>
		<td>
			<html:text property="supplier" size="15" />
		</td>--%>
		 
		<td class="bluetext">
					<bean:message key="order.number"/>:
		</td>
		<td><html:text property="poip_number" size="15" /></td>
		<td class="bluetext">
			<bean:message key="Status"/>:
		</td>
		<td>
			<html:select property="status">
			<html:option value=""><bean:message key="all.all"/></html:option>
			<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><html:options collection = "X_WmsPurchaseOrderStatusLIST" property = "enumCode" labelProperty = "engShortDescription"/></c:if>
			<c:if test="${sessionScope.LOGIN_USER.locale!='en'}"><html:options collection = "X_WmsPurchaseOrderStatusLIST" property = "enumCode" labelProperty = "chnShortDescription"/></c:if>
			</html:select>
			</td>
		</tr>
		 <tr>
			<td class="bluetext"><bean:message key="purchaseOrder.itemCode"/>:
			</td>
			<td><html:text property="itemNumber" size="15"/></td>
			<td class="bluetext">
				交货日期:
			</td> 
			<td>
			<html:text property="dueDate" size="15" />
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg2',false,'dueDate',null,null,'purchaseOrderInspectionPendingItemQueryForm')"><img
				align="absmiddle" border="0" id="dimg2"
			src="images/datebtn.gif" /> </a>
			</td>
		</tr>
		<%--<tr>
			<td class="bluetext">
				<bean:message key="create.time"/>:
			</td> 
			<td>
			<html:text property="createDate" size="15" />
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg3',false,'createDate',null,null,'purchaseOrderInspectionPendingItemQueryForm')"><img
				align="absmiddle" border="0" id="dimg3"
			src="images/datebtn.gif" /> </a>
			</td>
			<td colspan="2"></td>
		</tr>
		--%><tr>
			<td colspan="2"></td>
			<td align="right">
			<input type="submit" value="<bean:message key="all.query"/>" />
			<%--<input type="button" onclick="printExcel();" value="<bean:message key="page.export.excel"/>" />
			--%></td>
			<td align="center">
				<%--<input type="button" onclick="generate()" value="<bean:message key="to.generate.the.invoice"/>" />
			--%></td>
		</tr>
		<tr><td colspan="4"><hr/></td></tr>
	</table>
-->
</html:form>
<table id="data">
</table>
<page:form action="listPurchaseItemTwo.do" method="post">

	<table class="data">
		<thead>
			<tr bgcolor="#9999ff" align="center">
			<th width="2%"><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this);computePurchaseItemTwoProduct();"/></th>
			<th><bean:message key="PO.No1"/></th>
			<th><bean:message key="purchaseOrder.line" /></th>
			<%--<th>SITE</th>--%>
			<th><bean:message key="supplier1"/></th>
			<th><bean:message key="supplier.code1"/></th>
			<th><bean:message key="purchaseOrder.itemCode1" /></th>
			<th>物料包装数量</th>
			<%-- <th>原厂编号</th> --%>
			<%--<th><bean:message key="purchaseOrder.itemName" /></th>--%>
			<%--<th><bean:message key="purchaseOrder.supplierItemNumber" /></th>--%>
			<th><bean:message key="purchaseOrder.um" /></th>
			<th><bean:message key="purchaseOrder.qty" /></th>
			<th>交货日期</th>
			<th>未发货数量</th>
			<th>订单下达日期</th>
			<th>类型</th>
			<th>本次发货数量</th>
			<%--<th width="5%"><bean:message key="purchaseOrder.receiptQty" /></th>
			
			<th width="5%"><bean:message key="purchaseOrder.transitNumber" /></th>
			<th width="5%"><bean:message key="purchaseOrder.QuantityNumber" /></th>			
			
			<th width="5%"><bean:message key="purchaseOrder.returnNumber" /></th>	
			<th width="5%"><bean:message key="purchaseOrder.replacementNumber" /></th>	
			--%></tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr align="center">
					<td width="2%">
					
						<input type="hidden"  id="td_poId${X_OBJECT.id}" value="${X_OBJECT.id}"/>
						
							<%--<input type="checkbox" name="ids" value="${X_OBJECT.id}" id="cbox${X_OBJECT.id}" onclick="check()" />
						--%>
						<c:choose>
							<c:when test="${X_OBJECT.checked=='checked'}">
								<input type="checkbox" disabled="disabled" />
							</c:when>
							<c:otherwise>
								<%--<input type="checkbox" name="ids" value="${X_OBJECT.id}" id="cbox${X_OBJECT.id}" onclick="check();computePurchaseItemTwoProduct();" />
							--%>
							<input type="checkbox" name="ids" value="${X_OBJECT.id}" id="cbox${X_OBJECT.id}" onclick="check();" />
							</c:otherwise>
						</c:choose>						
						<!-- 
							<c:if test="${X_OBJECT.checked=='checked'}">disabled="disabled"</c:if>
							/>
						 -->
					</td>
					<td id="td_poOrder${X_OBJECT.id}">
						${X_OBJECT.poip_number.po_number}
					</td>
					<td id="td_line${X_OBJECT.id}">${X_OBJECT.line}</td>
					<td style="display: none;" id="td_factpry${X_OBJECT.id}">
						${X_OBJECT.factory}
					</td>
					<td id="td_supplierName${X_OBJECT.id}">${X_OBJECT.poip_number.supplier.name}</td>
					<td id="td_supplierCode${X_OBJECT.id}">${X_OBJECT.poip_number.supplier.code}</td>
					<td id="td_itemNumberId${X_OBJECT.id}">${X_OBJECT.itemNumber.id}</td>
					<td id="td_itemNumberOrdMult${X_OBJECT.id}">
						<fmt:formatNumber value=" ${X_OBJECT.itemNumber.ord_mult}" maxFractionDigits="0" minFractionDigits="0"/>
					</td>
					<%--<td style="display: none;" id="td_itemNumberoldCode${X_OBJECT.id}">${X_OBJECT.itemNumber.oldCode}</td>
					--%><td style="display: none;" id="td_itemNumberName${X_OBJECT.id}">${X_OBJECT.itemNumber.name}</td>
					
					
					<td style="display: none;" id="td_supplierPartCode${X_OBJECT.id}">${X_OBJECT.itemNumber.supplierPartCode}</td>
					
					
					<td id="td_unit${X_OBJECT.id}">${X_OBJECT.itemNumber.unit}</td>
					
					
					<td id="td_caigouNumber${X_OBJECT.id}"><input size="5" type="hidden" value="${X_OBJECT.qty}" class="waitQuantity" />
					   <fmt:formatNumber value=" ${X_OBJECT.qty}" maxFractionDigits="0" minFractionDigits="0"/>
					</td>
					
					
					<td id="td_dueDate${X_OBJECT.id}">${X_OBJECT.dueDate}</td>
					
					<td id="td_qtyOpen${X_OBJECT.id}"><fmt:formatNumber value="${X_OBJECT.qtyOpen}" maxFractionDigits="0" minFractionDigits="0"/> </td>
					
					
					<td id="td_createDate${X_OBJECT.id}">${X_OBJECT.poip_number.createDate}</td>
					  <td id="vd${X_OBJECT.id}">
					  	<c:if test="${X_OBJECT.vd_promo=='T'}">中转库</c:if>
					  	<c:if test="${X_OBJECT.vd_promo!='T'}">厂区</c:if>
					  </td>
					<td > <input type="hidden" id="qty_box${X_OBJECT.id}" value="${X_OBJECT.qty_std}"/>
						<fmt:formatNumber type="number" value="${X_OBJECT.qtyOpen}" pattern="0" maxFractionDigits="0" var="qtyOpen"/> 
						<input size="10" type="text" <c:if test="${X_OBJECT.checked=='checked'}">disabled="disabled"</c:if> id='td_fahuoNumber${X_OBJECT.id}' value="${qtyOpen}"/> 
						<input type="hidden" id="vd_promo${X_OBJECT.id}" value="${X_OBJECT.vd_promo}"/>
					</td>
					
					<%--<td><input size="5" type="hidden" value="${X_OBJECT.qty-X_OBJECT.qtyOpen}" class="receiptQty" />${X_OBJECT.qty-X_OBJECT.qtyOpen}</td>
					
					<td>${X_OBJECT.qty-X_OBJECT.qtyOpen-X_OBJECT.receiptQty}</td>
					<td>${X_OBJECT.receiptQty}</td>
					<td></td>
					<td></td>
					--%></tr>
			</logic:iterate>
		</tbody>
	</table>
	<table>
		<tr>
			<td align="right">
				<input type="button" onclick="generate('${X_OBJECT.poip_number.poip_number}')" value="选择" />
			</td>
		</tr>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



