<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html><head><title></title>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
	<style type="text/css" id="style1">
		.bottomSolid {border-bottom:solid #000000 1px;}
		.bottomDouble {border-bottom:double #000000 2px;}
		table{ font-size:8px}
	</style>
	<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12"> 
	</object>
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
  		//document.getElementById("asnsave").style.display="none";
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Print(false); //调用控件打印 
		document.all("viewpanel").style.display="";//显示 
		//var ids="<c:forEach items="${X_RESULTLIST}" var="po" >${po.id};</c:forEach>";
		var url="updatePirntAsnPortalShipOrder.do?id="+${x_portalShipOrder.id};
		window.location=url;
	} 
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
	}
	//function window.close(){
	//	window.location.href="listPurchaseOrderReceipts.do";
	//}
    </script> 
    
     </head>  
     <body topmargin="0px" leftmargin="0px" rightmargin="0px" bottommargin="0px">  
    <center id="printContent">
    <div id="div_1">
        <table width="600px" align="center" border="1">
    	<tr>
    		<td align="center" colspan="3">
    			ASN
    		</td>
    	</tr>
    	<tr>
    		<td align="center"  width="30%">
    			Date:${x_portalShipOrder.createDate }
    		</td>
    		<td align="center" >
    			<bean:message key="ouTa(shenyang)provide.acoustic.accessories.co" />  
    		</td>
    		<td rowspan="5">
    			ASN-NO.
    			<br>
    			<img width=240  src='${path}/CreateBarCode?msg=${x_portalShipOrder.asnNo}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=14&width=240&moduleWidth=240'>
    		</td>
    	</tr>
    	<tr>
    		<td align="center" >
    		JIS SHIPMENT
    		</td>
    		<td align="center" >
    		BMW Supplier Code:204652-10
    		</td>
    	</tr>
    	<tr>
    		<td align="center">
    			Ship_to:	
    		</td>
    		<td align="center">
    		</td>
    	</tr>
    	<tr>
    		
    		<td align="center">
    			No.1 BMW Avenue
    		</td>
    		<td align="center">
    		Total bin quantity:1
    		</td>
    	</tr>
    	<tr>
    		<td align="center">
    			
    		</td>
    		<td align="center">
    			Unloading information:WH1 main
    		</td>
    	</tr>
    	</table>
    	<table width="600px" align="center" border="0">
    		
    		<c:choose>
					<c:when test="${x_portalShipOrderItemList == null}">
							<h3>
							<bean:message key="There.is.no.purchase.order.information" />
							</h3>
					</c:when>
					<c:otherwise>
							<thead>
				    			<tr>
				    				<th><bean:message key="PO.No"/></th>
				    				<th><bean:message key="supplier"/></th>
				    				<th><bean:message key="supplier.code"/></th>
				    				<th><bean:message key="order.date"/></th>
				    				<th><bean:message key="Status"/></th>
				    				<th><bean:message key="Remark"/></th>
				    			</tr>
				    		</thead>				
							<c:forEach var="str" items="${x_portalShipOrderItemList}" varStatus="s">
													
								<tr>
				    				<td>${str.poNumber.po_number.poOrder}</td>
				    				<td>${str.poNumber.supplier.name}</td>
				    				<td>${str.poNumber.supplier.name}</td>
				    				<td>${str.poNumber.po_number.poDate}</td>
				    				<td>${str.poNumber.status.chnShortDescription}</td>
				    				<td>${str.poNumber.remark}</td>
				    			</tr>
							</c:forEach>
					</c:otherwise>
				</c:choose>
    	</table>
    
    </div>
    </center>
        <div id="viewpanel" align="center"> 
     <input name="bequery" type="button" value="Print" style="cursor:hand;" onclick="printTure();">
    <input  name="bequery" type="button" value="Print preview" style="cursor:hand;" onclick="printView();"/>
    <input  name="bequery" type="button" value="Return" onclick="history.go(-1);"/>
    </div>  
        </body>  
    </html> 