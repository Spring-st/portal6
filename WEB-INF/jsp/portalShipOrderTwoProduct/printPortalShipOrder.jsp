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
	<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
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
  		//alert(123);
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Print(false); //调用控件打印 
		document.all("viewpanel").style.display="";//显示 
		var ids="<c:forEach items="${X_RESULTLIST}" var="po" >${po.id};</c:forEach>";
		
		//var url="updatePirntPortalShipOrderByJitPart.do?id=${x_portalShipOrder.id}";
		//window.location=url;
		var idd="${x_portalShipOrder.id}";
		$.ajax({
			type:"POST", //请求方式           
			url:"updatePirntPortalShipOrderByJitPart.do", //请求路径      
			cache: false,
			data : "id=" + idd,      
			dataType : 'json', //返回值类型        
			success : function(json) {
				
         	}
 		});
	} 
  	var num=0;
	<c:forEach items="${x_portalShipOrderItemList}" var="X_OBJECT" >
		<c:if test="${X_OBJECT.deliveryNumber%X_OBJECT.part.ord_mult==0}">
				num=num+<fmt:formatNumber>${X_OBJECT.deliveryNumber/X_OBJECT.part.ord_mult}</fmt:formatNumber>
		</c:if>
		<c:if test="${X_OBJECT.deliveryNumber%X_OBJECT.part.ord_mult!=0}">
				num=num+<fmt:formatNumber>${(X_OBJECT.deliveryNumber-(X_OBJECT.deliveryNumber%X_OBJECT.part.ord_mult))/X_OBJECT.part.ord_mult+1}</fmt:formatNumber>
		</c:if>
	</c:forEach>
	onload= function aa(){
		document.getElementById("spanche").innerText=num;
	}
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
		//var url="updatePirntPortalShipOrderByJitPart.do?id=${x_portalShipOrder.id}";
		//window.location=url;
			var id="${x_portalShipOrder.id}";
			$.ajax({         
                type:"POST", //请求方式        
                url:"updatePirntPortalShipOrderByJitPart.do", //请求路径        
                cache: false,           
              	data:{"id":id},//传参        
                dataType: 'json',   //返回值类型        
                async:false,
                success:function(json){
              		//if(json.object==true){
              		//	window.location.href="viewPortalShipOrderTwo.do?id="+json.id;
              		//}
               }
            });
	}
	//function window.close(){
	//	window.location.href="listPurchaseOrderReceipts.do";
	//}
    </script> 
    <style>
    	.table{border-collapse:collapse;border-spacing:0;border-left:1px  ;border-top:1px  ; border-right: 1px;border-bottom: 1px;  }
    </style>
     </head>  
     <body topmargin="0px" leftmargin="0px" rightmargin="0px" bottommargin="0px"> 
    <center id="printContent">
    <div id="div_1">
    <table width="100%">
    	<tr><td>
    
    
    <%--<table width="100%" border="0">
	<tr>
		<td colspan="2" width="34%" style="padding-left: 20">
		<img width=140  src='images/Lear_log_proc.jpg'><img width=140  src='images/Dymos_log_proc.jpg'><br/><span style="font-size: 15px;">北京李尔岱摩斯汽车有限公司</span> 
		</td>
		<td colspan="2"  align="center" width="33%">
		<h3 style="color:blue">供应商送货单</h3>
		</td>
 		 <td  colspan="2" width="320px" align="left">
			<img width=300  src='${path}/CreateBarCode?msg=${x_portalShipOrder.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=16&width=300&moduleWidth=300'>
 		 </td>
		</tr>
	
		<tr>
 		  <td class="bluetext" width="16%">交货地点:</td>
 		   <td colspan=2>
			${x_portalShipOrderItemList[0].poipItem.factory}
 		   </td>
 		 <td class="bluetext" width="16%">供应商名称:</td>
 		 <td colspan=2>
			${x_portalShipOrder.supplier.name}
 		 </td>
		</tr>
		
		<tr>
 		 <td class="bluetext" width="16%">打印时间:</td>
 		 <td colspan=2>
 		 ${X_DATE}
 		 	<c:if test="${x_portalShipOrder.arrivalDate == null}">
 		 		${X_DATE}
 		 	</c:if>
			<c:if test="${x_portalShipOrder.arrivalDate != null }">
 		 		${x_portalShipOrder.arrivalDate}
 		 	</c:if>
 		 </td>
 		 <td class="bluetext" width="16%">供应商代码:</td>
 		 <td colspan=2>
			${x_portalShipOrder.supplier.code}
 		 </td>
		</tr>
		
		<tr>
		  
 		</tr>

	<tr>
 		 <td class="bluetext" width="16%">交货联系人:</td>
 		 <td colspan=2>
			${x_portalShipOrderItemList[0].poipItem.poip_number.buyer}
 		 </td>
 		 <td class="bluetext" width="16%">发货联系人:</td>
 		 <td colspan=2>
			${x_portalShipOrder.supplier.contact}
 		 </td>
		</tr>

		<tr>
 		 <td class="bluetext" width="16%">电话:</td>
 		 <td colspan=2>
			${x_portalShipOrderItemList[0].poipItem.poip_number.buyer_phone}
 		 </td>
 		 <td class="bluetext" width="16%">电话:</td>
 		 <td colspan=2>
			${x_portalShipOrder.supplier.telephone1}
 		 </td>
		</tr>
	</table>
	</td></tr><tr><td align="center">
	<h3 style="color:blue">送货明细信息</h3>
	
	<table class="data" width="100%" >
					<thead>
						<tr bgcolor="#9999ff" align="center">
							<th>序号</th>
							<th>
						    	<bean:message key="purchaseOrder.itemCode1"/>
							</th>
							<th>
						    	物料名称
							</th>
							<th>
						    	<bean:message key="purchaseOrder.um"/>
							</th>
							<th>发货数量</th>
							<th>发货箱数</th>
							<th>备注</th>
						</tr>
					</thead>
				<tbody id="datatable2">
					<logic:iterate id="X_OBJECT" name="x_portalShipOrderItemList" indexId="status">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<tr align="center">
						<td align="center">${status+1}</td>
						<td align="center">${X_OBJECT.part.id}</td>
						<td align="center">${X_OBJECT.part.name}</td>
						<td align="center">${X_OBJECT.part.unit}</td>
						<td align="center">${X_OBJECT.deliveryNumber}</td>
						<td align="center">
						<c:if test="${X_OBJECT.deliveryNumber%X_OBJECT.part.ord_mult==0}">
						<fmt:formatNumber>${X_OBJECT.deliveryNumber/X_OBJECT.part.ord_mult}</fmt:formatNumber>
						</c:if>
						<c:if test="${X_OBJECT.deliveryNumber%X_OBJECT.part.ord_mult!=0}">
						<fmt:formatNumber>${(X_OBJECT.deliveryNumber-(X_OBJECT.deliveryNumber%X_OBJECT.part.ord_mult))/X_OBJECT.part.ord_mult+1}</fmt:formatNumber>
						</c:if>
						</td>
						
						<td align="center"></td>
						</tr>
						 <bean:define id="sumamount" value="${sumamount+(X_OBJECT.deliveryNumber)}"/>
					</logic:iterate>
			    </tbody>
	</table>
	<table width=100% border=0 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		 	<tr align="right">
				<td class="bluetext">合计数量:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumamount}" maxFractionDigits="2" minFractionDigits="2"/></td>
			 </tr>
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
			第一联：仓库保留，第二联：司机回执，第三联：供应商保留
				第一联：物流部门留存、 第二联：供应商留存、 第三联：备用
			</td>
		</tr>
		</table>
		
			--%>
			<table width="100%" >
	    	<tr>
	    		<td colspan="2">
	    			<img width=140 height=70 src='images/Lear_log_proc.jpg'>
	    			<img width=140 height=70  src='images/Dymos_log_proc.jpg'>
	    		</td>
	    		<td colspan="2" align="right">
	    			<img width=300 height=100  src='${path}/CreateBarCode?msg=${x_portalShipOrder.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=16&width=300&moduleWidth=300'>
	    		</td>
	    	</tr>
	    	<tr align="center">
	    		<td colspan="4">
	    			<span style="font-weight: bold;font-size: 22px;">北京李尔现代坦迪斯汽车系统有限公司</span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan="4" align="center"> 
	    			<span style="font-size: 20px;font-weight: bold;">Packing List<br/>送料单</span>
	    		</td>
	    	</tr>
	    	<%--<tr>
	    		<td width="25%" style="font-size: 14px;">供应商代码：<br/><span style="font-size: 11px;">Supplier Code</span></td>
	    		<td width="25%" valign="top" align="left" style="font-size: 14px;">${x_portalShipOrder.code}</td>
	    		<td width="25%" style="font-size: 14px;">货物运到：<br/><span style="font-size: 11px;">Ship to</span></td>
	    		<td width="25%" valign="top" align="left" style="font-size: 14px;">北京李尔现代坦迪斯汽车系统有限公司</td>
	    	</tr>
	    	<tr>
	    		<td width="25%" style="font-size: 14px;">供应商名称：<br/><span style="font-size: 11px;">Supplier Name</span></td>
	    		<td width="25%" valign="top" align="left" style="font-size: 14px;">${x_portalShipOrder.supplier.name}</td>
	    		<td width="25%" style="font-size: 14px;">发运日期：<br/><span style="font-size: 11px;">Shipping date</span></td>
	    		<td width="25%" valign="top" align="left" ><span style="font-size: 14px;">　　年　　月　　日　　 时</span></td>
	    	</tr>
	    	--%><tr>
	    		<td width="50%" colspan="2" style="font-size: 14px;">供应商代码：　　${x_portalShipOrder.supplier.code}<br/>
	    			<span style="font-size: 11px;">Supplier Code</span>
	    		</td>
	    		<td width="50%" colspan="2" style="font-size: 14px;">货物运到：　　北京李尔现代坦迪斯汽车系统有限公司</span><br/>
	    			<span style="font-size: 11px;">Ship to</span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td width="50%" colspan="2" style="font-size: 14px;">供应商名称：　　${x_portalShipOrder.supplier.name}
	    			<br/><span style="font-size: 11px;">Supplier Name</span>
	    		</td>
	    		<td width="50%" colspan="2" style="font-size: 14px;">发运日期：　　	<c:if test="${empty x_portalShipOrder.printDate}">${X_DATE}</c:if>
	    																			<c:if test="${not empty x_portalShipOrder.printDate }">${x_portalShipOrder.printDate}</c:if>
	    		<br/><span style="font-size: 11px;">Shipping date</span></td>
	    	</tr>
	    	<tr>
	    		<td width="10%" style="font-size: 14px;">采购单号：<br/><span style="font-size: 11px;">Purchase Order No</span></td>
	    		<td  colspan="3" valign="top" align="left" style="font-size: 14px;">&nbsp;</td>
	    	</tr>
	    	<tr>
	    		<td colspan="4" align="left" style="font-weight: bold;font-size: 17px;">DETAILS:</td>
	    	</tr>
	    	<tr>
	    			<td colspan="4" align="center">
						<table class="data" width="100%" >
							<thead>
								<tr bgcolor="#9999ff" align="center">
									<th>序号</th>
									<th>
								    	零件号
									</th>
									<th>
								    	零件说明
									</th>
									<th>
								    	<bean:message key="purchaseOrder.um"/>
									</th>
									<th>数量</th>
									<th>实收数量</th>
									<th>车/箱数</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody id="datatable2">
								<logic:iterate id="X_OBJECT" name="x_portalShipOrderItemList" indexId="status">
									<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
									<tr align="center">
									<td align="center">${status+1}</td>
									<td align="center">${X_OBJECT.part.id}</td>
									<td align="center">${X_OBJECT.part.name}</td>
									<td align="center">${X_OBJECT.part.unit}</td>
									<td align="center"><fmt:formatNumber value="${X_OBJECT.deliveryNumber}" pattern="#"/></td>
									<td align="center"><fmt:formatNumber value="${X_OBJECT.actual_qty}" pattern="#"/></td>
									<td align="center">
										<c:if test="${X_OBJECT.deliveryNumber%X_OBJECT.part.ord_mult==0}">
											<fmt:formatNumber>${X_OBJECT.deliveryNumber/X_OBJECT.part.ord_mult}</fmt:formatNumber>
										</c:if>
										<c:if test="${X_OBJECT.deliveryNumber%X_OBJECT.part.ord_mult!=0}">
											<fmt:formatNumber>${(X_OBJECT.deliveryNumber-(X_OBJECT.deliveryNumber%X_OBJECT.part.ord_mult))/X_OBJECT.part.ord_mult+1}</fmt:formatNumber>
										</c:if>
									</td>
									<td align="center"></td>
									</tr>
								</logic:iterate>
						    </tbody>
						</table>
					</td>
	    	</tr>
	    	<tr valign="bottom">
	    		<td width="50%" colspan="2" style="font-size: 14px;">
	    			　　　总计车数或箱数：<br><span style="font-size: 11px;">　　　 Total Qty of Pallets/Containers</span>
	    		</td>
	    		<td width="50%" align="right"  colspan="2" style="font-size: 14px;" >
	    			<div   style="border: 1px solid; width: 220px;height: 40px;">
	    				<table width="220px;" height="40px;">
	    				<tr>
	    					<td align="right">
	    						<span id="spanche"></span>车/箱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    					</td>
	    				</tr>
	    			</table>
	    			</div>
	    		</td>
	    	</tr>
	    	</table>
	    	<table width="100%" border="1"  class="table">
	    	<tr align="center" height="30px" >
	    		<td width="50%" colspan="2" style="font-size: 15px;font-weight: bold;">
	    			供应商签字<span style="font-size: 12px;">(Signed By Vendor)</span>
	    		</td>
	    		<td width="50%"  colspan="2" style="font-size: 15px;font-weight: bold;">
	    			李尔现代坦迪斯<span style="font-size: 12px;">(Approved By)</span>
	    		</td>
	    	</tr>
	    	<tr align="center" height="30px" class="td">
	    		<td width="25%"   style="font-size: 15px;font-weight: bold;">
	    			库管<span style="font-size: 11px;">(supplier)</span>
	    		</td>
	    		<td width="25%" style="font-size: 15px;font-weight: bold;">
	    			品保<span style="font-size: 11px;">(ＱＣ)</span>
	    		</td>
	    		<td width="25%"   style="font-size: 15px;font-weight: bold;">
	    			物流<span style="font-size: 11px;">(Logistics)</span>
	    		</td>
	    		<td width="25%" style="font-size: 15px;font-weight: bold;">
	    			品保<span style="font-size: 11px;">(ＱＣ)</span>
	    		</td>
	    	</tr>
	    	<tr  height="70px" >
	    		<td>
	    			&nbsp;
	    		</td>
	    		<td>
	    			&nbsp;
	    		</td>
	    		<td>
	    			&nbsp;
	    		</td>
	    		<td>
	    			&nbsp;
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
  
    
</script>
    </html> 