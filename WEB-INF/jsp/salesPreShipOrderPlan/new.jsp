<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
var tempCustomerCode="";
function selectCustomerPlanList(){

	var planId= document.getElementById("planId").value;
	v = window.showModalDialog(
			'showDialog.do?title=site.purchase.title&selectCustomerPlan.do?planId='+planId, 
			null, 'dialogWidth:1200px;dialogHeight:640px;status:no;help:no;scroll:no');
		//alert("aa");
		if (v) {
			var resultList = v; 
			for(i = 0;i<resultList.length; i++){
				var v=resultList[i];
				var trString = "<tr class='aboveRow'>";   
		        trString += "<td align='center'>"+"<input type=\"hidden\" name=\"soNumber\" value=\""+(v['soNumber'])+"\" />"+"<input type=\"hidden\" name=\"soItemIds\" value=\""+(v['soItemId'])+"\" />"+v['soNumber']+"</td>";
		        trString += "<td>"+v['customerName']+"</td>";
		        trString += "<td>"+v['customerCode']+"</td>";
		        trString += "<td>"+v['customerAddress']+"</td>";
		        trString += "<td>"+v['line']+"</td>";
		        trString += "<td align='left'>"+"<input type=\"hidden\" name=\"itemNumbers\" value=\""+(v['itemNumberId'])+"\" />"+v['itemNumberId']+"</td>";
		        trString += "<td>"+v['itemNumberDpi']+"</td>";
		        trString += "<td>"+v['itemNumberoldCode']+"</td>";
		        trString += "<td>"+v['itemNumberName']+"</td>";
		        trString += "<td>"+v['istxt']+"</td>";
		        trString += "<td>"+v['um']+"</td>";
		        trString += "<td>"+v['duDate']+"</td>";
		        trString += "<td>"+v['qty']+"</td>";
		        trString += "<td>"+v['qtyopen']+"</td>";
                trString += "<td>"+"<input type=\"hidden\" id=\""+v['id']+"\"  name=\"deliveryNumbers\" value=\""+(v['fahuoNumber'])+"\" />"+"<input type=\"hidden\" name=\"remarks\" value=\""+(v['remark'])+"\" />"+v['fahuoNumber']+"</td>";
                trString += "<td>"+v['sotype']+"</td>";
                trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this,"+v['id']+")'><bean:message key='all.delete' /></a></td>";                 
			  planId+=v['id']+";";
		      trString += "</tr>"; 
		      if(tempCustomerCode==""){
		    	  tempCustomerCode=v['customerCode'];
		    	  
		      }else{
		    	  if(v['customerCode']!=tempCustomerCode){
						alert("同一张送货单不允许发给多个客户!");
						return false;
		    	  }
		      }
		     $("#datatable2").append(trString).show();
          }
			 document.getElementById("planId").value=planId;
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
		var planId= document.getElementById("planId").value;
		
		var number= document.getElementById(id).value;
		 var count=document.getElementById("sum_number").innerHTML;
		 count =parseInt(count)-parseInt(number);
		document.getElementById("sum_number").innerHTML=count;
		
		var pattern = id;
		planId = planId.replace(new RegExp(pattern), "");
		//console.log(planId);
		document.getElementById("planId").value=planId;
		
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable2');
		ttbody.deleteRow(oldRow.sectionRowIndex);
		
	}

function createSalesPreShipOrder(){
	var poItems = document.getElementsByName("soItemIds");
	var str="";
	for(var i=0;i<poItems.length;i++){
		str=str+poItems[i].value+","
	}
   	if(!poItems.length>0){
   		alert("请选择销售计划！");
   		return false;
   	}            		
    var numbers="";
	var soNumbers = document.getElementsByName("soNumber");
	for(var i=0;i<soNumbers.length;i++){
		numbers =numbers+ soNumbers[i].value+",";
	}
	$.ajax({         
                type:"POST", //请求方式        
                url:"salesShipOrderByBoxAJAX.do", //请求路径        
                cache: false,   
              	data:"itemId="+str+"&numbers="+numbers,  //传参        
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
                	
                	}
               }
			    
            });
	
	var form = document.salesPreshiporderQueryForm;
	form.action = "createSalesPreShipOrder.do";
	form.submit();
   }
</script>
<html:form action="/createSalesPreShipOrderPlan" method="post">
<table width="100%" border="0" cellpadding="4" cellspacing="0">
	<tr>
		 <td class="bluetext"><bean:message key="SO.No"/>:</td>
 		 <td class="bluetext" width="25%">
			
 		 </td>
		<td class="bluetext">
					到货时间：
				</td>
			<td class="bluetext">
					<html:text property="receivingDate" size="15" /> 
					<a onclick="event.cancelBubble=true;"
					href="javascript:showCalendar('dimg1',false,'receivingDate',null,null,'salesPreshiporderQueryForm')"><img
					align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
			</td>

</table>


<input type="hidden" id="soipitemId" name="soipitemId" value=""/>
<input type="hidden" id="planId"  value=""/>

<hr/>
	<table width="100%" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td colspan="4">
				<input type="button" value="选择客户需求计划" onclick="selectCustomerPlanList()"/>
			</td>
			
		</tr>
	</table>


<h3 style="color:blue"><bean:message key="purchaseOrderReceipts.item"/></h3>
	<table  class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff">
						  <th>销售订单号</th>
							<th>客户</th>
							<th>客户编码</th>
							<th>送货地址</th>
							<th>行号</th>
							<th>物料编码</th>
							<th>DPI</th>
							<th>原厂编号</th>
							<th>物料名称</th>
							<th>是否开票</th>
							<th>单位</th>
							<th>要求交货日期</th>
							<th>销售订单数量</th>
							<th>未发货数量</th>
							<th>发货数量</th>
							<th>类型</th>
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
		<tr>
			<td width="100%" colspan="4"><hr/>
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
			<input type="button" value="生成发货单" onclick="createSalesPreShipOrder()"/>
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