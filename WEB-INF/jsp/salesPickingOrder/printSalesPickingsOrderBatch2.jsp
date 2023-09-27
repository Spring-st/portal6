<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html><head><title></title>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
	<style type="text/css" >
		.bottomSolid {border-bottom:solid #000000 1px;}
		.bottomDouble {border-bottom:double #000000 2px;}
		table{ font-size:8px;}
	</style>
	<script language="javascript" src="includes/table.js"></script>
	<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12"> 
	</object>
	    <script language="javascript">  
	    
	    
	function window.onload()    {  
		factory.printing.header = "";  //页尾        
		factory.printing.footer = "";  //页首        
		//factory.printing.portrait = true;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
		factory.printing.portrait = true;
		factory.printing.leftMargin = 10.0;         
		factory.printing.topMargin = 10.0;         
		factory.printing.rightMargin = 10.0;        
		factory.printing.bottomMargin = 10.0;        
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
  	function printTure() //打印函数 
	{ 
  		document.all("viewpanel").style.display="none";//隐藏按钮 
		var selElements = document.getElementsByTagName('span');
		for (var i = 0; i < selElements.length; i++) {
			if (selElements[i].className == 'ghpc') {
			selElements[i].style.display='none';
			}
		}
		//document.getElementById('caozuo').style.display="none"; 
		document.getElementById('caozuo').innerHTML=""; 
		factory.printing.Print(false); //调用控件打印 
		document.all("viewpanel").style.display="";//显示 
		var selElements = document.getElementsByTagName('span');
		for (var i = 0; i < selElements.length; i++) {
			if (selElements[i].className == 'ghpc') {
			selElements[i].style.display='';
			}
		}
		//document.getElementById('caozuo').style.display="";
		document.getElementById('caozuo').innerHTML="操作";
		var ids="<c:forEach items="${X_RESULTLIST}" var="po" >${po.id};</c:forEach>";
		
		var url="updatePirntSalesPickingOrderBatch.do?id=${x_salesShipOrder.id}";
		window.location=url;
	} 
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		var selElements = document.getElementsByTagName('span');
		for (var i = 0; i < selElements.length; i++) {
			if (selElements[i].className == 'ghpc') {
			selElements[i].style.display='none';
			}
		}
		//document.getElementById('caozuo').style.display="none"; 
		document.getElementById('caozuo').innerHTML=""; 
		factory.printing.Preview();
		var selElements = document.getElementsByTagName('span');
		for (var i = 0; i < selElements.length; i++) {
			if (selElements[i].className == 'ghpc') {
			selElements[i].style.display='';
			}
		}
		//document.getElementById('caozuo').style.display="none"; 
		document.getElementById('caozuo').innerHTML="操作";
		document.all("viewpanel").style.display="";//显示 
		var url="updatePirntSalesPickingOrderBatch.do?id=${x_salesShipOrder.id}";
		window.location=url;
	}
	//function window.close(){
	//	window.location.href="listPurchaseOrderReceipts.do";
	//}
	function ReplaceBatch (id) { 
		window.location.href="SalesPickingOrderReplaceBatch.do?id="+id+"&type=${x_type}";
		//window.location.href="viewCreateSalesPreShipOrder.do?id=${x_salesShipOrder.id}";
		
	}
    </script> 
     </head>  
     <body topmargin="0px" leftmargin="0px" rightmargin="0px" bottommargin="0px">  
    <center id="printContent">
    <div id="div_1">
    <table width="100%">
    	<tr><td>
    <input type="hidden" name="type" value="${x_type}" />
    
    <table width="100%" border="0">
     
	<tr>
		<td colspan="2" width="33%" style="padding-left: 20">
		<img width=194  src='images/logo_t.png'>
		</td>
		<td colspan="2"  align="center" width="33%">
		<h1 style="color:blue">检料单</h1>
		</td>
 		 <td  colspan="2" width="300px" align="left">
			<img width=300  src='${path}/CreateBarCode?msg=${x_salesShipOrder.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=12&width=300&moduleWidth=300'>
 		 </td>
		</tr>
		<%--<tr>
 		 <td class="bluetext" width="16%">客户名称:</td>
 		 <td colspan=2>
			${x_salesShipOrder.customerName}
 		 </td>
 		 <td class="bluetext" width="16%">供应商名称:</td>
 		 <td colspan=2>
			${x_salesPreshiporderItemList[0].soipitemId.soId.customer.name1}
 		 </td>
		</tr>
		<tr>
 		 <td class="bluetext" width="16%">交货地点:</td>
 		 <td colspan=2>
			<c:if test="${x_salesShipOrder.type eq '1'}">
 		 		${x_salesPreshiporderItemList[0].soipitemId.factory}
 		 	</c:if>
			<c:if test="${x_salesShipOrder.type ne '1'}">
 		 		${x_salesPreshiporderItemList[0].customerPlanId.customerAddress}
 		 	</c:if>
 		 </td>
 		 <td class="bluetext" width="16%">供应商代号:</td>
 		 <td colspan=2>
			${x_salesPreshiporderItemList[0].soipitemId.soId.customer.code}
 		 </td>
		</tr>

		<tr>
 		 <td class="bluetext" width="16%">交货联系人:</td>
 		 <td colspan=2>
			${x_salesShipOrder.customerName}
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
	--%></table>
	</td></tr><tr><td align="center">
	<h3 style="color:blue">检料单明细信息</h3>
	<c:if test="${x_salesShipOrder.type eq '1'}">
		<table class="data" width="100%" >
					<thead>
						<tr bgcolor="#9999ff">
						 <th>销售订单号</th>
							<%--<th>客户</th>
							--%><th>客户编码</th>
							<%--<th>送货地址</th>
							--%><th>行号</th>
							<th>物料编码</th>
							<%--<th>DPI</th>
							<th>原厂编号</th>
							--%><%--<th>物料名称</th>
							--%><th>单位</th>
							<%--<th>要求交货日期</th>
							--%><th>销售订单数量</th>
							<th>预发货数量</th>
							<%--<th>类型</th>
							--%><th>备注(需要优先发货)</th>
						</tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_salesPreshiporderItemList" indexId="status">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr align="center">
							<td>${X_OBJECT.soipitemId.soId.soNumber}</td>
							<%--<td>${X_OBJECT.soipitemId.soId.custName}</td>
							--%><td>${X_OBJECT.soipitemId.soId.custCode}</td>
							<%--<td>${X_OBJECT.soipitemId.soId.custAddress}</td>
							--%><td>${X_OBJECT.soipitemId.line}</td>
							<td>${X_OBJECT.soipitemId.itemNumber.id}</td>
							<%--<td>${X_OBJECT.soipitemId.itemNumber.dpiNo}</td>
							<td>${X_OBJECT.soipitemId.itemNumber.oldCode}</td>
							--%><%--<td>${X_OBJECT.soipitemId.itemNumber.name}</td>
							--%><td>${X_OBJECT.soipitemId.itemNumber.unit}</td>
							<%--<td>${X_OBJECT.soipitemId.dueDate}</td>
							--%><td><fmt:formatNumber value="${X_OBJECT.soipitemId.qty}" maxFractionDigits="0"/></td>
							<%--<td>${X_OBJECT.soipitemId.qtyopen}</td>--%>
							<td><fmt:formatNumber value="${X_OBJECT.deliverynumber}" maxFractionDigits="0"/></td>
							<%--<td>${X_OBJECT.soipitemId.sotype}</td>
							--%><td>${X_OBJECT.soRemark}</td>
						</tr>
					</logic:iterate>
			    </tbody>
			    
		</table>
	</c:if>
	<c:if test="${x_salesShipOrder.type ne '1'}">
		<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff">
							<th>客户需求计划单号</th>
							<%--<th>客户</th>
							--%><th>客户编码</th>
							<%--<th>送货地址</th>
							--%><th>行号</th>
							<th>物料编码</th>
							<%--<%--<th>物料名称</th>
							<th>DPI</th>
							<th>原厂编号</th>--%>
							<th>单位</th>
							<%--<th>要求交货日期</th>
							<th>客户需求数量</th>--%>
							<%--<th>未发货数量</th>--%>
							<th>预发货数量</th>
							<%--<th>实际发货数量</th>
							<th>类型</th>
							--%><th>备注(需要优先发货)</th>
						</tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_salesPreshiporderItemList">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr align="center">
								<td align="center">${X_OBJECT.customerPlanId.planNumbers}</td>
								<%--<td align="center">${X_OBJECT.customerPlanId.customer.name1}</td>
								--%><td align="center">${X_OBJECT.customerPlanId.customer.code}</td>
								<%--<td align="center">${X_OBJECT.customerPlanId.customerAddress}</td>
								--%><td align="center">${X_OBJECT.customerPlanId.line}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.id}</td>
								<%--<td align="center">${X_OBJECT.customerPlanId.wmspart.name}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.dpiNo}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.oldCode}</td>--%>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.unit}</td>
								<%--<td align="center">
									<bean:write name="X_OBJECT" property="customerPlanId.shipmentDate" format="yyyy-MM-dd" />
									${X_OBJECT.customerPlanId.shipmentDate}
								</td>
								<td><fmt:formatNumber value="${X_OBJECT.customerPlanId.qty}" maxFractionDigits="0"/></td>--%>
								<%--<td>${X_OBJECT.customerPlanId.qtyopen}</td>--%>
								<td><fmt:formatNumber value="${X_OBJECT.deliverynumber}" maxFractionDigits="0"/></td>
								<%--<td><fmt:formatNumber value="${X_OBJECT.shipQty}" maxFractionDigits="0"/></td>
								<td align="center">${X_OBJECT.customerPlanId.sotype}</td>--%>
								<td>${X_OBJECT.soRemark}</td>
						</tr>
					</logic:iterate>
			    </tbody>
		</table>
	</c:if>
	<hr>
	<table class="data" >
   	<thead>
   	   <tr bgcolor="#9999ff" align="center">
   	      <td class="bluetext" width="8%">库位代码</td>
   	      <td class="bluetext" style="padding: 0px 0px;" width="92%">
   	          	<span style="width:24%;">批次</span>
   	          	<span style="width:18%;">物料编号</span>
   	          	<%--<span style="width:13%;">DPI</span>
   	          	<span style="width:10%;">原厂编号</span>
   	          	<span style="width:140px;">物料描述</span>
   	          	--%><span style="width:17%;">数量</span>
   	          	<span style="width:20%;">单位</span>
   	          	<%--<span style="width:14%;">是否必发</span>
   	          	--%><span style="width:15%;" id="caozuo">操作</span>
   	       </td>
   	          <%--
   	          <td class="bluetext">批次</td>
   	          <td class="bluetext">库位代码</td>
   	          <td class="bluetext">物料描述</td>
   	          <td class="bluetext">数量 </td>
   	          <td class="bluetext">是否必发 </td>
   	   --%></tr>
   	   </thead>
   	   <tbody id="datatable3">
   	   <c:set var="batchsPartId" value="0"></c:set>
   	   <c:forEach var="X_OBJECT" items="${x_batchList}">
   	       <c:if test="${X_OBJECT.box.location.id ne batchsLocationId}">
   	       <tr align="center">
   	          <td style="border-top: 1px dashed #000; border-right:1px dashed #000;" width="10%">${X_OBJECT.box.location.code}</td>
   	           <td style="border-top: 1px dashed #000" >
   	          <c:forEach var="obj" items="${x_batchList}">
   	      			<table style="margin-left:0px;"  border="0" width="100%">
   	      			<c:if test="${obj.box.location.id eq X_OBJECT.box.location.id}">
   	      			<tr align="center">
   	      				<td style="width:18%;">${obj.box.lot.id}</td>
						<td style="width:14%;">${obj.box.part.id}</td>
						<%--<td style="width:13%;">${obj.box.part.dpiNo}</td>
						<td style="width:10%;">${obj.box.part.oldCode}</td>
						--%><%--<td style="width:140px;">${obj.box.part.describe1}</td>
						--%><td style="width:14%;"><fmt:formatNumber value="${obj.box.number}" maxFractionDigits="0"/></td>
						<%--<td style="width:14%;">
							<c:if test="${obj.status=='0'}">
								${obj.status.chnShortDescription}
							</c:if>
						</td>
						--%>
						<td style="width:14%;">${obj.box.part.unit}</td>
						<td width="13%;">
							<span class="ghpc">
								<c:if test="${x_salesShipOrder.status=='1'}">
									<c:if test="${(obj.box.location.basic_storeroom_id.type=='4') or (obj.box.location.basic_storeroom_id.type=='6')}">
										<a href="javascript:ReplaceBatch('${obj.id}')" >更换批次</a>
									</c:if>
								</c:if>
							</span>
						</td>
   	          		</tr>
   	          	</c:if>
   	          	</table>
   	      	  </c:forEach>
   	      	  &nbsp;
   	      	  </td>
   	        </tr>
   	        <c:set var="batchsLocationId" value="${X_OBJECT.box.location.id}"></c:set>
   	        </c:if>
		</c:forEach>
   	   </tbody>
   	</table>
		</td></tr>
		
		<tr>
			<td>
				<table width="100%" border="0">
					<tr>
						<td class="bluetext" width="16%">捡料人签(章):</td>
	 					 <td colspan=2 width="34%">
	 		 			</td>
					</tr>
	<%--
		<tr>
 		 <td class="bluetext" width="16%">供应商签(章):</td>
 		 <td colspan=2 width="34%">
			`
 		 </td>
 		 <td class="bluetext" width="16%">收货人签(班组章):</td>
 		 <td colspan=2 width="34%">
			`
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
		</tr>--%>
		</table>
		
			</td>
		</tr>
    </table>
    </div>
    </center>
        <div id="viewpanel" align="center"> 
    <input name="bequery" type="button" value="打印" style="cursor:hand;" onclick="printTure();">
    <input  name="bequery" type="button" value="打印预览" style="cursor:hand;" onclick="printView();"/>
    <input  name="bequery" type="button" value="返回" onclick="history.go(-1);"/>
    </div>  
        </body>  
        <script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
     applyRowStyle(document.all('datatable3'));
  
    
</script>
    </html> 