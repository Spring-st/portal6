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
		factory.printing.portrait = false;
		factory.printing.leftMargin = 10.0;         
		factory.printing.topMargin = 10.0;         
		factory.printing.rightMargin = 10.0;        
		factory.printing.bottomMargin = 10.0;        
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
  	function printTure() //打印函数 
	{ 
  		//alert(123);
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Print(false); //调用控件打印 
		document.all("viewpanel").style.display="";//显示 
		var ids="<c:forEach items="${X_RESULTLIST}" var="po" >${po.id};</c:forEach>";
		
		var url="updatePirntSalesWorkShipOrder.do?id=${x_salesShipOrder.id}";
		window.location=url;
	} 
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
		var url="updatePirntSalesWorkShipOrder.do?id=${x_salesShipOrder.id}";
		window.location=url;
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
    
    
    <table width="100%" border="0">
	<tr>
		<td colspan="2" width="33%" style="padding-left: 20">
		<img width=194  src='images/logo_t.png'>
		</td>
		<td colspan="2"  align="center"width="33%" style="white-space: nowrap;">
			<font style="font-family : 宋体; font-size : 1.8em;" >云南泰坦斯汽车配件有限公司</font><br/>
			<font style="font-family : 宋体; font-size : 1.5em;">发货单</font>
		</td>
 		 <td  colspan="2" width="320px" align="center">
			<%--<img width=300  src='${path}/CreateBarCode?msg=${x_salesShipOrder.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=16&width=300&moduleWidth=300'>
 		 --%>${x_salesShipOrder.code}
 		 </td>
		</tr>
		<tr >
			<td class="bluetext" width="16%">客户编码:</td>
			<td colspan=2>
				${x_salesShipOrder.customerCode}
			</td>
			<td class="bluetext" width="16%">收货人姓名:</td>
			<td colspan=2>
				${x_salesShipOrder.customerName}
			</td>
 		</tr>
		<tr>
			<td class="bluetext" width="16%">收货人地址:</td>
			<td colspan=2>
				<c:if test="${x_salesShipOrder.type eq '1'}">
	 		 		${x_salesPreshiporderItemList[0].soipitemId.factory}
	 		 	</c:if>
				<c:if test="${x_salesShipOrder.type ne '1'}">
	 		 		${x_salesPreshiporderItemList[0].customerPlanId.customerAddress}
	 		 	</c:if>
 		 	</td>
			<td class="bluetext" width="16%">收货人联系方式:</td>
			<td colspan=2>
			 	<c:if test="${x_salesShipOrder.type eq '1'}">
			 		
			 	</c:if>
				<c:if test="${x_salesShipOrder.type ne '1'}">
			 		${x_salesPreshiporderItemList[0].customerPlanId.customer.phone}
			 	</c:if>
 		 	</td>
		</tr>
		<tr>
		<td class="bluetext" width="16%">备注:</td>
 		 	<td colspan=2>
				<c:if test="${x_salesShipOrder.type eq '1'}">
	 		 		
	 		 	</c:if>
				<c:if test="${x_salesShipOrder.type ne '1'}">
	 		 		${x_salesPreshiporderItemList[0].customerPlanId.describe}
	 		 	</c:if>
 		 	</td>
 		 	
 		 </tr>
 		<%--<tr>
 		 <td class="bluetext" width="16%">收货人手机号码:</td>
 		 	
 		 <td colspan=2>
 		 	<c:if test="${x_salesShipOrder.type eq '1'}">
 		 		
 		 	</c:if>
			<c:if test="${x_salesShipOrder.type ne '1'}">
 		 		${x_salesPreshiporderItemList[0].customerPlanId.customer.phone}
 		 	</c:if>
 		 </td>
 		 
 		 <td class="bluetext" width="16%">电话:</td>
 		 <td colspan=2>
 		 </td>
		</tr>--%>
		<%--<tr>
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
		</tr>--%>
	</table>
	</td></tr><tr><td align="center">
	<h3 style="color:blue">发货单明细信息</h3>
	<bean:define id="curr" value="0"/>
	<c:if test="${x_salesShipOrder.type eq '1'}">
		<table class="data" width="100%" >
					<thead>
						<tr bgcolor="#9999ff">
						 <th>销售订单号</th>
							<th>客户</th>
							<th>客户编码</th>
							<%--<th>送货地址</th>
							<th>行号</th>--%>
							<th>物料编码</th>
							<th>物料描述</th>
							<%--<th>DPI</th>
							<th>原厂编号</th>
							--%><%--<th>物料名称</th>
							<th>单位</th>--%>
							<th>要求交货日期</th>
							<th>销售订单数量</th>
							<th>实际发货数量</th>
							<th>价格</th>
							
							<%--<th><bean:message key="basicpartprice.curr" /></th>
							--%><th><bean:message key="basicpartprice.sotaxc" /></th>
							<%--<th>类型</th>
							<th>备注(需要优先发货)</th>--%>
							<th>小计</th>
						</tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_salesPreshiporderItemList" indexId="status">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr>
							<td>${X_OBJECT.soipitemId.soId.soNumber}</td>
							<td>${X_OBJECT.soipitemId.soId.custName}</td>
							<td>${X_OBJECT.soipitemId.soId.custCode}</td>
							<%--<td>${X_OBJECT.soipitemId.soId.custAddress}</td>
							<td>${X_OBJECT.soipitemId.line}</td>--%>
							<td>${X_OBJECT.soipitemId.itemNumber.id}</td>
							<td>${X_OBJECT.soipitemId.itemNumber.describe1}</td>
							<%--<td>${X_OBJECT.soipitemId.itemNumber.dpiNo}</td>
							<td>${X_OBJECT.soipitemId.itemNumber.oldCode}</td>
							--%><%--<td>${X_OBJECT.soipitemId.itemNumber.name}</td>
							<td>${X_OBJECT.soipitemId.itemNumber.unit}</td>--%>
							<td>${X_OBJECT.soipitemId.dueDate}</td>
							<td>${X_OBJECT.soipitemId.qty}</td>
							<%--<td>${X_OBJECT.soipitemId.qtyopen}</td>--%>
							<td>${X_OBJECT.shipQty}</td>
							<td>
								<c:if test="${X_OBJECT.price <= 0}">
									<span style="color: red;"><fmt:formatNumber value="${X_OBJECT.price}" maxFractionDigits="2" minFractionDigits="2"/></span>
								</c:if>
								<c:if test="${X_OBJECT.price > 0}">
									<span><fmt:formatNumber value="${X_OBJECT.price}" maxFractionDigits="2" minFractionDigits="2"/></span>
								</c:if>
							</td>
							<%--<td>${X_OBJECT.curr}</td>
							--%><td>${X_OBJECT.sotaxc}</td>
							<%--<td>${X_OBJECT.soipitemId.sotype}</td>
							<td>${X_OBJECT.soRemark}</td>--%>
							<td>
								<fmt:formatNumber value="${X_OBJECT.price * X_OBJECT.shipQty}" maxFractionDigits="2" pattern="#0.00#" minFractionDigits="2" var="price"/>
								<c:if test="${(X_OBJECT.price * X_OBJECT.shipQty) <= 0}">
									<span style="color: red;">${price}</span>&nbsp;
								</c:if>
								<c:if test="${(X_OBJECT.price * X_OBJECT.shipQty) > 0}">
									<span>${price}</span>&nbsp;
								</c:if>
							</td>
						</tr>
						<c:if test="${curr eq '0'}">
							<bean:define id="curr" value="${X_OBJECT.curr}"/>
						</c:if>
						<c:if test="${curr ne '0'}">
							<c:if test="${curr ne '1'}">
								<c:if test="${curr ne X_OBJECT.curr}">
									<bean:define id="curr" value="1"/>
								</c:if>
							</c:if>
						</c:if>
						<bean:define id="sumamount" value="${sumamount+(X_OBJECT.shipQty)}"/>
						<fmt:formatNumber var="sumPrice" value="${sumPrice + price}" pattern="#0.0#" maxFractionDigits="2" minFractionDigits="2" />
						<%--<bean:define id="sumPrice" value="${sumPrice+ price}"/>
					--%></logic:iterate>
			    </tbody>
			    
		</table>
		</c:if>
		<c:if test="${x_salesShipOrder.type ne '1'}">
		<table class="data" width="100%" >
					<thead>
						<tr bgcolor="#9999ff">
							<th>客户需求计划单号</th>
							<%--<th>客户</th>
							<th>客户编码</th>
							--%><%--<th>送货地址</th>
							<th>行号</th>--%>
							<th>条码编号</th>
							<th>物料编码</th>
							<th>物料名称</th>
							<%--<th>物料名称</th>
							<th>DPI</th>
							<th>原厂编号</th>
							<th>单位</th>--%>
							<th>车型</th>
							<th>年份起</th>
							<th>年份讫</th>
							<th>要求交货日期</th>
							<%--<th>客户需求数量</th>
							--%><th>数量</th>
							<th>单价</th>
							<%--<th><bean:message key="basicpartprice.curr" /></th>--%>
							<th>基准</th>
							<th>备注</th>
							<th>小计</th>
							<%--<th>备注</th>
						--%></tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_salesPreshiporderItemList" indexId="status">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr align="center">
							<td align="center">${X_OBJECT.customerPlanId.planNumbers}</td>
								<%--<td align="center">${X_OBJECT.customerPlanId.customer.name1}</td>
								<td align="center">${X_OBJECT.customerPlanId.customer.code}</td>
								--%><%--<td align="center">${X_OBJECT.customerPlanId.customerAddress}</td>
								<td align="center">${X_OBJECT.customerPlanId.line}</td>--%>
								<td align="center">
									<table>
										<c:forEach items="${X_OBJECT.salesWorkorderList}" var="workorder" varStatus="status">
												<tr>
													<td>${workorder.lotSer.id}</td>
												</tr>
										</c:forEach>
									</table>
								</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.id}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.describe1}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.domesticCar1}</td>
								<%--<td align="center">${X_OBJECT.customerPlanId.wmspart.domesticCar1}</td>
								--%><td align="center">${X_OBJECT.customerPlanId.wmspart.yearFrom1}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.yearTo1}</td>
								<%--<td align="center">${X_OBJECT.customerPlanId.wmspart.name}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.dpiNo}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.oldCode}</td>
								<td align="center">${X_OBJECT.customerPlanId.wmspart.unit}</td>--%>
								<td align="center">
									<bean:write name="X_OBJECT" property="customerPlanId.shipmentDate" format="yyyy-MM-dd" />
								</td>
								<%--<td><fmt:formatNumber value="${X_OBJECT.customerPlanId.qty}" maxFractionDigits="0"/></td>
								--%><td><fmt:formatNumber value="${X_OBJECT.shipQty}" maxFractionDigits="0"/></td>
								<td>
									<c:if test="${X_OBJECT.customerPlanId.unitPrice <= 0}">
										<span style="color: red;"><fmt:formatNumber value="${X_OBJECT.customerPlanId.unitPrice}" maxFractionDigits="2" minFractionDigits="2"/></span>
									</c:if>
									<c:if test="${X_OBJECT.customerPlanId.unitPrice > 0}">
										<span><fmt:formatNumber value="${X_OBJECT.customerPlanId.unitPrice}" maxFractionDigits="2" minFractionDigits="2"/></span>
									</c:if>
								</td>
								<%--<td>${X_OBJECT.customerPlanId.curr}</td>
								--%><td>${X_OBJECT.customerPlanId.sotaxc}</td>
								<td>${X_OBJECT.soRemark}</td>
								<td>
									<fmt:formatNumber value="${X_OBJECT.customerPlanId.unitPrice * X_OBJECT.shipQty}" maxFractionDigits="2" pattern="#0.00#" minFractionDigits="2" var="price"/>
									<c:if test="${(X_OBJECT.customerPlanId.unitPrice * X_OBJECT.shipQty) <= 0}">
										<span style="color: red;">${price}</span>&nbsp;
									</c:if>
									<c:if test="${(X_OBJECT.customerPlanId.unitPrice * X_OBJECT.shipQty) > 0}">
										<span>${price}</span>&nbsp;
									</c:if>
								</td>
								<%--<td>${X_OBJECT.customerPlanId.describe}</td>
						--%></tr>
						<c:if test="${curr eq '0'}">
							<bean:define id="curr" value="${X_OBJECT.customerPlanId.curr}"/>
						</c:if>
						<c:if test="${curr ne '0'}">
							<c:if test="${curr ne '1'}">
								<c:if test="${curr ne X_OBJECT.customerPlanId.curr}">
									<bean:define id="curr" value="1"/>
								</c:if>
							</c:if>
						</c:if>
						
						<bean:define id="sumamount" value="${sumamount+(X_OBJECT.shipQty)}"/>
						<fmt:formatNumber var="sumPrice" value="${sumPrice + price}" pattern="#0.00#" maxFractionDigits="2" minFractionDigits="2" />
						 <%--<c:set var="sumPrice" value="${sumPrice + price}"></c:set>
						--%><%--<bean:define id="sumPrice" value="${sumPrice + price}" />
					--%></logic:iterate>
			    </tbody>
			    
		</table>
		</c:if>
	<table width=100% border=0 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		 	<tr align="left">
				<td class="bluetext">合计数量:&nbsp;&nbsp;<fmt:formatNumber value="${sumamount}" maxFractionDigits="2" minFractionDigits="2"/></td>
			</tr>
		<tr align="left">
				<td class="bluetext">合计金额:&nbsp;
					<c:if test="${curr ne '1'}">
						<fmt:formatNumber value="${sumPrice}" maxFractionDigits="2" minFractionDigits="2"/>			
					</c:if>
				</td>
			</tr>
		</table>
		</td></tr>
		
		<tr>
			<td>
				<table width="100%" border="0">
	
		<tr>
 	<%-- <td class="bluetext" width="16%">供应商签(章):</td>
 		 <td colspan=2 width="34%">
			`
 		 </td>
 		 <td class="bluetext" width="16%">收货人签(班组章):</td>
 		 <td colspan=2 width="34%"></td>--%>
 		 
 		  <td class="bluetext" width="13%">仓库签(章):</td>
 		 <td colspan=2 width="20%">
			`
 		 </td>
 		 <td class="bluetext" width="13%">物流签(章):</td>
 		 <td colspan=2 width="20%">
 		 
 		 </td>
 		 <td class="bluetext" width="13%">收货人签(章):</td>
 		 <td colspan=2 width="20%">
 		 
 		 </td>
		</tr>
		
		<tr>
 		 <td class="bluetext" width="13%">日期:</td>
 		 <td colspan=2 width="20%">
			`
 		 </td>
 		 <td class="bluetext" width="13%">日期:</td>
 		 <td colspan=2 width="20%">
			
 		 </td>
 		 <td class="bluetext" width="13%">日期:</td>
 		 <td colspan=2 width="20%">
			
 		 </td>
		</tr>
		
		<tr>
			<td colspan="9">
			<hr width="100%"/>
			</td>
		</tr>
		
		<tr>
			<td  class="bluetext" colspan="9">
			第一联：仓库留存、第二联：财务留存、第三联：销售留存、第四联：物流回执、第五联：客户留存；
			</td>
		</tr>
		<tr>
			<td  class="bluetext" colspan="8">
			公司地址：云南省昆明市众天路东聚汽配城C区10幢3-4号&nbsp;&nbsp;&nbsp;&nbsp;电话：0871-68108188
			</td>
			<td  class="bluetext" align="right">
				业务专员：${x_salesPreshiporderItemList[0].customerPlanId.operation}
			</td>
		</tr>
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
  //applyRowStyle(document.all('datatable3'));
  
    
</script>
    </html> 