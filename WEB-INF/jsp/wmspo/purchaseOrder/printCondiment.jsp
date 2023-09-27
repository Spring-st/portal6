<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html><head><title></title>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
	<style type="text/css" id="style1">
		.bottomSolid {border-bottom:solid #000000 1px;}
		.bottomDouble {border-bottom:double #000000 2px;}
		table{ font-size:8px}
	</style>
	<script language="javascript" src="includes/table.js"></script>
	<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12"> 
	</object>
<script language="JavaScript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript">
	    
	    
	function window.onload()    {  
	 
		factory.printing.header = "";  //页尾        
		factory.printing.footer = "";  //页首        
		factory.printing.portrait = true;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
		factory.printing.leftMargin = 1.0;         
		factory.printing.topMargin = 1.0;         
		factory.printing.rightMargin = 1.0;        
		factory.printing.bottomMargin = 1.0;        
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
  	function printTure() //打印函数 
	{ 
  		var array=document.getElementById("condimientId").value;
  		 $.ajax({         
                type:"POST", //请求方式        
                url:"updateCondimentIsPrint.do", //请求路径        
                cache: false,           
                data : "ids=" + array,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                	document.all("viewpanel").style.display="none";//隐藏按钮 
					factory.printing.Print(false); //调用控件打印 
					document.all("viewpanel").style.display="";//显示 
         }
       })
		
	} 
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
		//var url="updatePirntPortalShipOrder.do?id=${x_portalShipOrder.id}";
		//window.location=url;
	}
	//function window.close(){
	//	window.location.href="listPurchaseOrderReceipts.do";
	//}
    </script> 
    
     </head>  
     <body topmargin="0px" leftmargin="0px" rightmargin="0px" bottommargin="0px">  
    <center id="printContent">
    <div id="div_1">
    <table width="100%">
    	<tr><td>
    <input type="hidden" value="${item.id}" id="condimientId" />
    
    <table width="100%" border="0">
	<tr>
		<td colspan="2" width="33%" style="padding-left: 20">
		<img width=194  src='images/logo_t.png'>
		</td>
		<td colspan="2"  align="center" width="33%">
		<h3 style="color:blue">调料单号</h3>
		</td>
 		 <td  colspan="2" width="320px" align="left">
				<img width=300  src='${path}/CreateBarCode?msg=${item.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=16&width=300&moduleWidth=300'>
 		 </td>
		</tr>
	<%--
		<tr>
 		 <td class="bluetext" width="16%">交货地点:</td>
 		 <td colspan=2>
			
 		 </td>
 		 <td class="bluetext" width="16%">供应商名称:</td>
 		 <td colspan=2>
			
 		 </td>
		</tr>
		
		<tr>
 		 <td class="bluetext" width="16%">交货时间:</td>
 		 <td colspan=2>
			
 		 </td>
 		 <td class="bluetext" width="16%">供应商代码:</td>
 		 <td colspan=2>
			
 		 </td>
		</tr>

		<tr>
 		 <td class="bluetext" width="16%">交货联系人:</td>
 		 <td colspan=2>
			
 		 </td>
 		 <td class="bluetext" width="16%">发货联系人:</td>
 		 <td colspan=2>
			
 		 </td>
		</tr>

		<tr>
 		 <td class="bluetext" width="16%">电话:</td>
 		 <td colspan=2>
			
 		 </td>
 		 <td class="bluetext" width="16%">电话:</td>
 		 <td colspan=2>
			
 		 </td>
		</tr>
		 --%>
	</table>
	</td></tr><tr><td align="center">
	<h3 style="color:blue">发货明细信息</h3>
	
	<table class="data" width="100%" >
					<thead>
						<tr bgcolor="#9999ff">
							<th>采购单</th>
							<th>行号</th>
							<th>物料号</th>
							<th>描述一</th>
							<th>描述一</th>
							<th>供应商代码</th>
							<th>采购单时间</th>
							<th>发货时间</th>
							<th>采购单量</th>
							<th>发货数量</th>
							<th>收货数量</th>
				    		<th>入库数量</th>
				    		<th>状态</th>
							<!-- 
						   <th>
						         <bean:message key="PO.No"/>
						   </th>
						   <th>
						         <bean:message key="purchaseOrder.line"/>
							</th>
							<th>
						    	<bean:message key="purchaseOrder.itemCode"/>
							</th>
							<th>
						         <bean:message key="purchaseOrder.itemName"/>
						   </th>
						   <th>
						         <bean:message key="purchaseOrder.supplierItemNumber"/>
							</th>
							<th>
						    	<bean:message key="purchaseOrder.um"/>
							</th>
							<th>
						         <bean:message key="purchaseOrder.dingdan"/>
						   </th>
						   <th>
						         <bean:message key="purchaseOrder.fahuo"/>
							</th>
							<th>
						    	<bean:message key="purchaseOrder.hege"/>
							</th>
							<th>
						         <bean:message key="purchaseOrder.tuihuo"/>
						   </th>
							<th><bean:message key="purchaseOrder.duDate" /></th>
							<th>
						    	<bean:message key="purchaseOrder.pici"/>
							</th>
							 -->
						</tr>
					</thead>
				<tbody id="datatable2">
					<%-- <logic:iterate id="X_OBJECT" name="x_items" >--%>
						<%--<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" /> --%>
						<tr>
						<td>${item.po_detial_id.poip_number.po_number}</td>
						<td>${item.po_detial_id.line }</td>
						<td>${item.part.id }</td>
						<td>${item.part.describe1 }</td>
						<td>${item.part.describe2 }</td>
						<td>${item.supplier.code}</td>
						<td>${item.po_detial_id.poip_number.createDate}</td>
						<td>${item.date}</td>
						<td>${item.po_detial_id.qty}</td>
						<td>${item.number}</td>
						<td>
					<c:if test="${item.po_detial_id.receiptQty == null}">0.00</c:if>
					<c:if test="${item.po_detial_id.receiptQty != null}">
						${item.po_detial_id.receiptQty}</c:if>
					</td>
						<td>
					<c:if test="${item.po_detial_id.inventoryNumber == null}">0.00</c:if>
					<c:if test="${item.po_detial_id.inventoryNumber != null}">${item.po_detial_id.inventoryNumber}</c:if>
				</td>
						<td>${item.status.chnShortDescription}</td>
						<td></td>
						<%-- 
								<td>${X_OBJECT.poipItem.poip_number.po_number}</td>
								<td>${X_OBJECT.poipItem.line}</td>
								<td>${X_OBJECT.poipItem.itemNumber.id}</td>
								<td>${X_OBJECT.poipItem.itemNumber.name}</td>
								<td>${X_OBJECT.poipItem.supplierItemNumber}</td>
								<td>${X_OBJECT.poipItem.um}</td>
								<td>${X_OBJECT.poipItem.qty}</td>
								<td>${X_OBJECT.deliveryNumber}</td>
								<td></td>
								<td></td>
								<td>
									${X_OBJECT.poipItem.dueDate }
								</td>
								<td></td>
								 -->
								 --%>
						</tr>
					<%--</logic:iterate> --%>
			    </tbody>
	</table>
		</td></tr>
		
		<tr>
			<td>
				<table width="100%" border="0">
	
		<tr>
 		 <td class="bluetext" width="16%">供应商签(章):</td>
 		 <td colspan=2 width="34%">
			`
 		 </td>
 		 <td class="bluetext" width="16%">收货人签(班组章):</td>
 		 <td colspan=2 width="34%">
			
 		 </td>
		</tr>
		
		<tr>
 		 <td class="bluetext" width="16%">日期:</td>
 		 <td colspan=2 width="34%">
			`
 		 </td>
 		 <td class="bluetext" width="16%">日期:</td>
 		 <td colspan=2 width="34%">
			`
 		 </td>
		</tr>
		
		<tr>
			<td colspan="6">
			<hr width="100%"/>
			</td>
		</tr>
		
		<tr>
			<td  class="bluetext" colspan="6">
			第一联：物流部门留存、第二联：供应商留存、第三联：备用
			</td>
		</tr>
		</table>
		
			</td>
		</tr>
    </table>
    </div>
    </center>
        <div id="viewpanel" align="center"> 
    <input name="bequery" type="button" value="Print" style="cursor:hand;" onclick="printTure();">
    <input  name="bequery" type="button" value="Print preview" style="cursor:hand;" onclick="printView();"/>
    <input  name="bequery" type="button" value="Return" onclick="history.go(-1);"/>
    </div>  
        </body>  
        <script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
  
    
</script>
    </html> 