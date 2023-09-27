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
var tempFactpry="";
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
function selectPurchaseOrderList(){
	var selectPoipItemId= document.getElementById("selectPoipItemId").value;
	var supplierCode=document.getElementById("supplierCode").value;
	v = window.showModalDialog(
			//'showDialog.do?title=site.purchase.title&listPurchaseItemTwo.do?selectPoipItemId='+selectPoipItemId+"&supplierCode="+supplierCode, 
			//null, 'dialogWidth:1200px;dialogHeight:640px;status:no;help:no;scroll:no');
	'showDialog.do?title=site.purchase.title&listPurchaseItemTwo.do?selectPoipItemId='+selectPoipItemId, 
			null, 'dialogWidth:1200px;dialogHeight:640px;status:no;help:no;scroll:no');
		//alert("aa");
		if (v) {
			var resultList = v; 
			for(i = 0;i<resultList.length; i++){
				var v=resultList[i];
				
				var trString = "<tr class='aboveRow' align='center'>";   
		        trString += "<td align='center'>"+"<input type=\"hidden\" name=\"poipNumbers\" value=\""+(v['poipNumber'])+"\" />"+"<input type=\"hidden\" name=\"poipItemIds\" value=\""+(v['poipItemId'])+"\" />"+v['poipNumber']+"</td>";
		        trString += "<td>"+v['line']+"</td>";
		        trString += "<td>"+v['supplierCode']+"</td>";
		        trString += "<td>"+v['supplierName']+"</td>";
		        //trString += "<td>"+v['factpry']+"</td>";
				trString += "<td>"+"<input type=\"hidden\" name=\"itemNumbers\" value=\""+(v['itemNumberId'])+"\" />"+v['itemNumberId']+"</td>";
		        //trString += "<td>"+v['itemNumberDpi']+"</td>";
		       // trString += "<td>"+v['itemNumberoldCode']+"</td>";
				//trString += "<td>"+v['itemNumberName']+"</td>";
                //trString += "<td>"+v['supplierItemNumber']+"</td>";
                trString += "<td>"+v['um']+"</td>"; 
                trString += "<td>"+v['caigouNumber']+"</td>";
                trString += "<td>"+v['duDate']+"</td>";
                trString += "<td>"+v['qtyOpen']+"</td>";
                trString += "<td>"+v['createDate']+"</td>";
               // trString += "<td>"+v['qtyOpen']+"</td>";
                trString += "<td>"+"<input type=\"hidden\" name=\"deliveryNumbers\" value=\""+(v['fahuoNumber'])+"\" />"+v['fahuoNumber']+"</td>";
               // trString += "<td>"+v['hege']+"</td>";  
               // trString += "<td>"+v['tuihuo']+"</td>";
               // trString += "<td>"+v['duDate']+"</td>";
               // trString += "<td>"+v['vd_promo']+"</td>";
                trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this,"+v['id']+")'><bean:message key='all.delete' /></a></td>";                 
			  selectPoipItemId+=v['id']+";";
		      trString += "</tr>"; 
		      document.getElementById("supplierCode").value=v['supplierCode'];
		      //alert(trString)
		      if(tempFactpry==""){
		    	  tempFactpry=v['factpry'];
		    	  
		      }else{
		    	  if(v['factpry']!=tempFactpry){
						alert("同一张送货单不允许供应多家工厂!");
						return false;
		    	  }
		      }
		      //alert(trString);
		     $("#datatable2").append(trString).show();
          }
			 document.getElementById("selectPoipItemId").value=selectPoipItemId;
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
		var selectPoipItemId= document.getElementById("selectPoipItemId").value;
		var pattern = id;
		selectPoipItemId = selectPoipItemId.replace(new RegExp(pattern), "");
		//console.log(selectPoipItemId);
		document.getElementById("selectPoipItemId").value=selectPoipItemId;
		
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable2');
		ttbody.deleteRow(oldRow.sectionRowIndex);
		//
		 var count=0;
			var number=document.getElementsByName("deliveryNumbers");
			for(var i=0;i<number.length;i++){
				count =parseInt(count)+parseInt(number[i].value);
			}
			document.getElementById("sum_number").innerHTML=count;
		//
	}
function view(id){
	
}
function createAll(poNumber){
         $.ajax({         
                type:"POST", //请求方式        
                url:"showPortalShipPurchaseOrderItemTwo.do", //请求路径        
                cache: false,           
                data:"poNumber="+poNumber,  //传参        
                dataType: 'json',   //返回值类型        poipItemList
                success:function(json){
                	//alert(json.length);
                    for(var i=0;i<json.length;i++){
                    	var poipItem = json[i];
				        var trString = "<tr class='aboveRow'>";   
		                trString += "<td  align='center'>"+"<input type=\"hidden\" name=\"poipItemIds\" value=\""+(json[i].poipItemId)+"\" />"+json[i].poipNumber+"</td>";
				        trString += "<td align='left'>"+json[i].line+"</td>";
		                trString += "<td>"+json[i].itemNumberId+"</td>";
                		trString += "<td>"+json[i].itemNumberName+"</td>";
                		trString += "<td>"+json[i].supplierItemNumber+"</td>"; 
                		trString += "<td>"+json[i].um+"</td>";
                		trString += "<td>"+json[i].orderNumber+"</td>";
                		trString += "<td>"+"<input type=\"hidden\" name=\"deliveryNumbers\" value=\""+(json[i].fahuoNumber)+"\" />"+json[i].fahuoNumber+"</td>";
                		trString += "<td></td>";
                		trString += "<td></td>";
                		trString += "<td>"+json[i].duDate+"</td>";
		                trString += "</tr>"; 
		                $("#datatable2").append(trString).show();
                    }
                    //关闭poNumber
                    document.getElementById("createAll"+poNumber).style.display="none";
                    document.getElementById("createPart"+poNumber).style.display="none";
			    } 
            }        
         )
}
function createPart(poNumber){

	var v = window.showModalDialog(
			'showDialog.do?title=site.purchase.title&selectPartPurchaseOrderItemTwo.do?poNumber='+poNumber, 
			null, 'dialogWidth:640px;dialogHeight:470px;status:no;help:no;scroll:no');
	//alert(v[0]['id']);

	if(v){
		 	
			//alert(v[i]['poipNumber']);
			var trString = "<tr class='aboveRow'>";   
		        trString += "<td align='center'>"+"<input type=\"hidden\" name=\"poipNumbers\" value=\""+(v['poipNumber'])+"\" />"+"<input type=\"hidden\" name=\"poipItemIds\" value=\""+(v['poipItemId'])+"\" />"+v['poipNumber']+"</td>";
		        trString += "<td>"+v['line']+"</td>";
				trString += "<td align='left'>"+"<input type=\"hidden\" name=\"itemNumbers\" value=\""+(v['itemNumberId'])+"\" />"+v['itemNumberId']+"</td>";
		        trString += "<td>"+v['itemNumberName']+"</td>";
                trString += "<td>"+v['supplierItemNumber']+"</td>";
                trString += "<td>"+v['um']+"</td>"; 
                trString += "<td>"+v['qtyOpen']+"</td>";
                trString += "<td>"+"<input type=\"hidden\" name=\"deliveryNumbers\" value=\""+(v['fahuoNumber'])+"\" />"+v['fahuoNumber']+"</td>";
                trString += "<td>"+v['hege']+"</td>";
                trString += "<td>"+v['tuihuo']+"</td>";
                trString += "<td>"+v['duDate']+"</td>";
                //trString += "<td>"+v[i]['pici']+"</td>";
		      trString += "</tr>"; 
		     $("#datatable2").append(trString).show();
		    
		 //关闭poNumber
                    document.getElementById("createAll"+poNumber).style.display="none";
                    document.getElementById("createPart"+poNumber).style.display="none";
	}
	
}
function deleteAll(id){
	
}

function createPortalShipOrderTwo(){
	//var list =new Array();
	//var poipNumberList = document.getElementsByName("poipNumbers");
	//alert("aa");
	//for(var i=0;i<poipNumberList.length;i++){
	//	list[i] = poipNumberList[i].value;
	//}

	var form = document.purchaseOrderInspectionPendingQueryForm;
	form.action = "createPortalShipOrderTwo.do";
	//alert("aaa");
	form.submit();
	
	//window.location.href="createPortalShipOrderTwo.do";
}


function createPortalShipOrder(){
	var poItems = document.getElementsByName("poipItemIds");
	var str="";
	if(!poItems.length>0){
		alert("请选择采购单明细！");
		return false;
	}
	for(var i=0;i<poItems.length;i++){
		str=str+poItems[i].value+","
	}
	
	$.ajax({         
                type:"POST", //请求方式        
                url:"validate.do", //请求路径        
                cache: false,           
              	data:"id="+str,  //传参        
                dataType: 'json',   //返回值类型        
                async:false,
                success:function(json){
                	if(json =="true"||json =="false"){
               		
                		var list =new Array();
						var poipNumberList = document.getElementsByName("poipNumbers");
						for(var i=0;i<poipNumberList.length;i++){
							list[i] = poipNumberList[i].value;
						}
					
						var form = document.purchaseOrderInspectionPendingQueryForm;
						form.action = "createPortalShipOrderTwo.do";
						form.submit();
                	
                	}//else{
					 //	alert("有更早的采购单没有发货,无法创建发货单!");
					//	return false;
					//}
               }
			    
            });
	
	
	
	
	
	
}


function validateForm(){
	//var date=document.getElementById("receivingDate").value;
	var ids = document.getElementsByName("poipItemIds");
	//var d=new Date();
	//var str = d.getFullYear()+"/"+(d.getMonth()+1)+"/"+d.getDate(); 
	//var newDate=new Date(str).getTime();
	//var deliveryDate= new Date(date).getTime();
	//if(deliveryDate<newDate){
	//	alert("发货日期不能小于当前日期!");
	//	return false;
	//}
	//if(date.length<=0){
	//	alert("请选择发货日期!");
	//	return false;
	//}
	if(ids.length>0){
		document.getElementsByName("submita")[0].disabled=true;
		return true;
	}else{
		alert("未选择采购单明细不能创建发货单!");
		return false;
	}
}


</script>
<html:form action="/createPortalShipOrderTwo" method="post" onsubmit="return validateForm();">
<input type="hidden" id="createType" name="createType" value="NJIT_PO"/>
<input type="hidden" id="supplierCode" name="supplierCode" value="">
<%--<table width="100%" border="0" cellpadding="4" cellspacing="0">
	<tr>
		<td width="10%" class="bluetext">
					到货日期：
				</td>
			<td class="bluetext">
					<html:text property="receivingDate" size="15" /> 
					<a onclick="event.cancelBubble=true;"
					href="javascript:showCalendar('dimg1',false,'receivingDate',null,null,'purchaseOrderInspectionPendingQueryForm')"><img
					align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
			</td>
	</tr>
</table>


--%><input type="hidden" id="poipItemId" name="poipItemId" value=""/>
<input type="hidden" id="selectPoipItemId"  value=""/>
 <bean:define id="sum_number" value=""/>

<%--<hr/>
	--%><%--<table width="100%" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td width="10%" colspan="4">
				<input type="button" value="1.选择供应商" style="width: 110px" onclick="selectSuuper()"/>
			</td>
			<td align="left"><span style="font-size: 16px;" id="supplierCodeSpan"></span> </td>
		</tr>
	</table>
	--%>
	<table width="100%" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td colspan="4">
				<input type="button" value="选择采购单明细" style="width: 110px"  onclick="selectPurchaseOrderList()"/>
			</td>
		</tr>
	</table>


<h3 style="color:blue"><bean:message key="purchaseOrderReceipts.item"/></h3>
	<table  class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff" align="center">
						   <th>
						         采购订单
						   </th>
						   <th>
						        行号
							</th>
							<th>
						         供应商编码
							</th>
							<th>
						         供应商名称
							</th>
							<th>
						    	物料编码
							</th>
							<th>
						    	单位
							</th>
							<th>
						    	采购数量
							</th>
							<th>
						    	交货时间
							</th>
							<th>
						       	未发货数量
						   </th>
						   <th>
						       	  订单下达日期
						   </th>
							 <th>
						               本次发货数量
							</th>
							<%--<th>
						    	SITE
							</th>
							<th>
						    	物料编号
							</th>
							<th>
						    	DPI
							</th>
							<th>
						    	原厂编号
							</th>
							<th>
						         物料名称
						   </th>
						   <th>
						         供应商物料号
							</th>
							<th>
						    	单位
							</th>
							<th>
						        未结订单数量
						   </th>
						   <th>
						         发货数量
							</th>
							<th>
						    	合格数量
							</th>
							<th>
						         退货数量
						   </th>
							<th>到货日期</th>
							<th>类型</th>
							--%><th>操作</th>
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
		<tr>
			<td width="100%" colspan="4">
			<hr/>
			</td>
		</tr>
	</table>
	<table  class="data" width="100%" style="display: none;">
					<thead>
						<tr bgcolor="#9999ff">
						   <th>
						         <bean:message key="PO.No"/>
						   </th>
							<th>
						    	<bean:message key="purchaseOrder.itemCode"/>
							</th>
						   <th>
						         <bean:message key="purchaseOrder.line"/>
							</th>
							<th>
						         <bean:message key="purchaseOrder.itemName"/>
						   </th>
						   <th>
						         <bean:message key="purchaseOrder.bzxh"/>
							</th>
							<th>
						    	<bean:message key="purchaseOrder.sl"/>
							</th>
							<th>
						         <bean:message key="purchaseOrder.tmcode"/>
						   </th>
						</tr>
					</thead>
				<tbody id="datatable3">
			    </tbody>
	</table>
	<table width="100%">
		<tr style="display: none;">
			<td width="100%" colspan="4"><hr/></td>
		</tr>
		<tr>
			<td colspan="4" align="right">
			<input type="submit" value="生成发货单" />
			<%--<input type="button" value="生成发货单" onclick="createPortalShipOrder()"/>
			--%><%--<input type="button" value="<bean:message key="all.save" />" onclick="createPortalShipOrderTwo()"/>
			<input type="button" value="<bean:message key="print.boxCode" />" onclick=""/>
			<input type="button" value="<bean:message key="print.portalShipOrder" />" onclick=""/>
			<input type="button" value="<bean:message key="all.delete"/>" onclick="" />--%>
			</td>
		</tr>
	</table>
</html:form>
<script type="text/javascript">
<!--
//cascadeSiteDepartment("${X_SITELIST[0].id}","department_id");

//-->
</script>
<script type="text/javascript">
    //applyRowStyle(document.all('datatable'));
</script>
<script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
</script>
<script type="text/javascript">
   // applyRowStyle(document.all('datatable3'));
</script>