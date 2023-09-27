<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

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
		var url="updateRejectedMaterialBeforePrint.do?id=${X_REJECTEDMATERIALID}";
		window.location=url;
	} 
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
		var url="updateRejectedMaterialBeforePrint.do?id=${X_REJECTEDMATERIALID}";
		window.location=url;
	}
	function historyGo(){
		window.location.href="listProduceRejectedMaterialBefore.do";
	}
    </script> 
   </head>  
     <body topmargin="0px" leftmargin="0px" rightmargin="0px" bottommargin="0px">  
    <center id="printContent">
    <div id="div_1">
    <table width="100%">
    	<tr><td>
    <table width="100%" border="0">
	<tr height="100px">
		<td colspan="2" width="33%" style="padding-left: 20">
			<img width=194  src='images/logo_t.png'>
		</td>
		<td colspan="2"  align="center" width="33%">
			<h3 style="color:blue">入库前退料单</h3>
		</td>
	</tr>
		<tr>
		<td class="bluetext" width="16%">供应商编号:</td>
 		 <td colspan=2>
			${X_RESULTLIST[0].box.psoItem.poipItem.poip_number.supplier.code}
 		 </td>
 		 <td class="bluetext" width="16%"><%--交货时间:--%></td>
 		 <td colspan=2>
			<%--${X_RESULTLIST[0].box.psoItem.portalShipOrder.arrivalDate}--%>
 		 </td>
 		 
		</tr>
		<tr>
		<td class="bluetext" width="16%">供应商名称:</td>
 		 <td colspan=2>
			${X_RESULTLIST[0].box.psoItem.poipItem.poip_number.supplier.name}
 		 </td>
 		 <td class="bluetext" width="16%"><%--交货地点:--%></td>
 		 <td colspan=2>
			<%--${X_RESULTLIST[0].box.psoItem.poipItem.factory}--%>
 		 </td>
		</tr>
		
		

		<tr>
			<td class="bluetext" width="16%"><%--交货时间:--%></td>
 		 	<td colspan=2>
				<%--${X_RESULTLIST[0].box.psoItem.portalShipOrder.arrivalDate}--%>
 		 	</td>
 		 <td class="bluetext" width="16%"><%--发货联系人:--%></td>
 		 <td colspan=2>
			<%--${X_RESULTLIST[0].box.psoItem.poipItem.poip_number.supplier.contact}--%>
 		 </td>
		</tr>

		<tr>
 		 <td class="bluetext" width="16%"><%--电话:--%></td>
 		 <td colspan=2>
			<%--${X_RESULTLIST[0].box.psoItem.poipItem.poip_number.buyer_phone}--%>
 		 </td>
 		 <td class="bluetext" width="16%"><%--电话:--%></td>
 		 <td colspan=2>
			<%--${X_RESULTLIST[0].box.psoItem.poipItem.poip_number.supplier.telephone1}&nbsp;&nbsp;&nbsp;&nbsp;${X_RESULTLIST[0].box.psoItem.poipItem.poip_number.supplier.telephone2}--%>
 		 </td>
		</tr>
	</table>
	</td></tr><tr><td align="center">
	<h3 style="color:blue">退货明细信息</h3>
	
	<table class="data" width="100%" >
					<thead>
						<tr bgcolor="#9999ff">
							<th>序号</th>
							<th>采购单号</th>
							<th>行号</th>
							<th>零件号</th>
							<th>原厂编号</th>
							<th>零件名称</th>
							<th>单位</th>
							<%--<th>发货数量</th>
							<th>发货箱数</th>
							--%><th>退货数量</th>
							<th>退货日期</th>
							<th>退货原因</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody id="datatable1">
					<c:set var="poipItem" value="0"></c:set>
					<c:set var="poipItemLine" value="0"></c:set>
					<c:set var="status" value="0"></c:set>
   	 				<c:forEach var="X_OBJECT" items="${X_RESULTLIST}">
   	     				<c:if test="${(X_OBJECT.box.psoItem.poipItem.poip_number.poip_number ne poipItem) or (X_OBJECT.box.psoItem.poipItem.line ne poipItemLine)}">
   	       				<tr>
   	       					<td align="center">${status+1}</td>
   	         				<td align="center">${X_OBJECT.box.psoItem.poipItem.poip_number.poip_number}</td>
   	         				<td align="center">${X_OBJECT.box.psoItem.poipItem.line}</td>
   	         				<td align="center">${X_OBJECT.box.psoItem.poipItem.itemNumber.id}</td>
   	         				<td align="center">${X_OBJECT.box.psoItem.poipItem.itemNumber.oldCode}</td>
   	         				<td align="center">${X_OBJECT.box.psoItem.poipItem.itemNumber.name}</td>
   	         				<td align="center">${X_OBJECT.box.psoItem.poipItem.um}</td>
   	         				<%--<td align="center">${(X_OBJECT.box.psoItem.poipItem.inventoryNumber)+(X_OBJECT.box.psoItem.poipItem.returnNumber)}</td>
   	         				 <bean:define id="sunNumber" value="${(X_OBJECT.box.psoItem.poipItem.inventoryNumber)+(X_OBJECT.box.psoItem.poipItem.returnNumber)}"/>
   	         				--%>
   	         				<%--<td align="center">
								<c:if test="${sunNumber%X_OBJECT.box.psoItem.poipItem.qty_std==0}">
									${sunNumber/X_OBJECT.box.psoItem.poipItem.qty_std}
								</c:if>
								<c:if test="${sunNumber%X_OBJECT.box.psoItem.poipItem.qty_std!=0}">
									${(sunNumber-(sunNumber%X_OBJECT.box.psoItem.poipItem.qty_std))/X_OBJECT.box.psoItem.poipItem.qty_std+1}
								</c:if>
							</td>
   	         				--%><%--<td align="center">${X_OBJECT.box.psoItem.poipItem.returnNumber}</td>--%>
   	         				<%--<td align="center">${X_OBJECT.returnNumber}</td>--%>
   	         				<%--<td align="center">${(sunNumber)-(X_OBJECT.box.psoItem.poipItem.returnNumber)}</td>--%>
   	         				<td align="center"><fmt:formatNumber value="${X_OBJECT.returnNumber}" maxFractionDigits="2"  minFractionDigits="2"/></td>
   	         				<bean:define id="returnNumber" value="${returnNumber+X_OBJECT.returnNumber}"/>
   	         				<%--<td align="center">${X_OBJECT.box.psoItem.poipItem.dueDate}</td>--%>
   	         				<td align="center">${X_OBJECT.date}</td>
   	         				<td align="center"><%--${X_OBJECT.reasons.describe}--%>
   	         					<table>
									<c:forEach items="${X_OBJECT.box.unqualifiedList}" var="item" varStatus="status">
											<tr>
												<td>${item.reasons.describe}</td>
											</tr>
									</c:forEach>
								</table>
   	         				</td>
   	         				<td>${X_OBJECT.box.remark}</td>
   	        			</tr>
   	        			<%--<bean:define id="sumamount" value="${sumamount+(sunNumber)-(X_OBJECT.box.psoItem.poipItem.returnNumber)}"/>
   	       			 	--%><c:set var="poipItem" value="${X_OBJECT.box.psoItem.poipItem.poip_number.poip_number}"></c:set>
   	       			 	<c:set var="poipItemLine" value="${X_OBJECT.box.psoItem.poipItem.line}"></c:set>
   	       			 	<c:set var="status" value="${status+1}"></c:set>
   	        			</c:if>
					</c:forEach>
			   </tbody>
				<%--<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_portalShipOrderItemList" indexId="status">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr>
						<td align="center">${status+1}</td>
						<td align="center">${X_OBJECT.poipItem.poip_number.po_number}</td>
						<td align="center">${X_OBJECT.poipItem.itemNumber.id}</td>
						<td align="center">${X_OBJECT.poipItem.itemNumber.dpiNo}</td>
						<td align="center">${X_OBJECT.poipItem.itemNumber.name}</td>
						<td align="center">${X_OBJECT.poipItem.um}</td>
						<td align="center">${X_OBJECT.deliveryNumber}</td>
						<td align="center">
						<c:if test="${X_OBJECT.deliveryNumber%X_OBJECT.poipItem.qty_std==0}">
						${X_OBJECT.deliveryNumber/X_OBJECT.poipItem.qty_std}
						</c:if>
						<c:if test="${X_OBJECT.deliveryNumber%X_OBJECT.poipItem.qty_std!=0}">
						${(X_OBJECT.deliveryNumber-(X_OBJECT.deliveryNumber%X_OBJECT.poipItem.qty_std))/X_OBJECT.poipItem.qty_std+1}
						</c:if>
						</td>
						
						<td align="center">${X_OBJECT.poipItem.dueDate }</td>
						<td align="center"></td>
						</tr>
						 <bean:define id="sumamount" value="${sumamount+(X_OBJECT.deliveryNumber)}"/>
					</logic:iterate>
			    </tbody>
	--%></table>
	<table width=100% border=0 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		<tr align="right">
			<td class="bluetext">合计数量:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${returnNumber}" maxFractionDigits="2" minFractionDigits="2"/></td>
		</tr>
	</table>
	<%--<table width=100% border=0 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		 	<tr align="right">
				<td class="bluetext">合计数量:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumamount}" maxFractionDigits="2" minFractionDigits="2"/></td>
			 </tr>
		</table>
		--%></td></tr>
		
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
			第一联：仓库保留，第二联：财务保留，第三联：供应商保留
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
    <input  name="bequery" type="button" value="返回" onclick="historyGo();"/>
    </div>  
        </body>  
        <script type="text/javascript">
    applyRowStyle(document.all('datatable1'));
  
    
</script>
    </html> 