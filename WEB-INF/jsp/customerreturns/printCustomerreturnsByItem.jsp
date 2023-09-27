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
		var url="updateCustomerreturnsByItemPrint.do?id=${x_Id}";
		window.location=url;
	} 
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
		var url="updateCustomerreturnsByItemPrint.do?id=${x_Id}";
		window.location=url;
	}
	function toGo(){
		var url="viewCustomerreturnsByItem.do?id=${x_Id}";
		window.location=url;
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
				<h3 style="color:blue">客户退货单</h3>
			</td>
		</tr>
		<tr>
			 <td width="15%" class="bluetext">退货单号:</td>
			 <td colspan="2"  width="35%">
				${x_customerreturns.returnNumber}
			 </td>
			 <td class="bluetext">客户编码:</td>
			<td colspan="2" width="35%">
				${x_customerreturns.basicCustomer.code}
			</td>
		</tr>
		<tr>
			<%--<td width="15%" class="bluetext">退货地点:</td>
			<td colspan="2" width="35%">
				${x_customerreturns.returnLocation}
			</td>
			--%>
			<td class="bluetext" >客户退货数量:</td>
			<td colspan="2" width="35%">
				<fmt:formatNumber value="${x_customerreturns.returnQuantity}" maxFractionDigits="0"/>
			</td>
			<td class="bluetext">退货接收库位:</td>
			<td colspan="2" width="35%">
				${x_customerreturns.returnStorage}
			</td>
		</tr>
		<tr>
			<td class="bluetext" >退货日期:</td>
			<td colspan="2" width="35%">
				${x_customerreturns.returnDate}
			</td>
			<td width="15%" class="bluetext">是否为开票退货:</td>
			<td colspan="2" width="35%">
				<c:if test="${x_customerreturns.invoiceStatus!='0'}">
					否
				</c:if>
				<c:if test="${x_customerreturns.invoiceStatus=='0'}">
					是
	 			</c:if><%--
				${x_customerreturns.invoiceStatus.chnShortDescription}
			--%></td>
		</tr>
		<tr>
			<td class="bluetext" >退货人:</td>
			<td colspan="2" width="35%">
				${x_customerreturns.returnPerson}
			</td>
			<td class="bluetext" >退货人联系方式:</td>
			<td colspan="2" width="35%">
				${x_customerreturns.returnPersonContact}
			</td>
		</tr>
		
		<tr>
			<td class="bluetext" >退货原因:</td>
			<td colspan="2" width="35%">
				${x_customerreturns.returnReasons}
			</td>
			<td></td>
			<td></td>
		</tr>
	</table>
	</td></tr><tr><td align="center">
	
	<h3 style="color:blue">退货明细信息</h3>
	
	<table class="data" width="100%" >
		<thead>
			<tr bgcolor="#9999ff">
				<th>序号</th>
				<th>批次号</th>
				<th>物料编号</th>
				<th>物料描述</th>
				<th>原厂编号</th>
				<th>退货接收库位</th>
				<th>数量</th>
				<th>销售出库时间</th>	
			</tr>
		</thead>
		<tbody id="datatable1">
			<c:set var="status" value="0"></c:set>
 	 			<c:forEach var="X_OBJECT" items="${x_customerReturnItemList}">
 	       			<tr align="center">
 	       				<td align="center">${status+1}</td>
 	       				<td>${X_OBJECT.batchNumber}</td>
						<td>${X_OBJECT.part.id}</td>
						<td>${X_OBJECT.part.describe1}</td>
						<td>${X_OBJECT.part.oldCode}</td>
						<td>${X_OBJECT.returnStorage}</td>
						<td>
							<fmt:formatNumber value="${X_OBJECT.qty}" maxFractionDigits="0"/>
							<bean:define id="returnNumber" value="${returnNumber+X_OBJECT.qty}"/>
						</td>
						<td>${X_OBJECT.salesDeliveryDate}</td>
 	         		</tr>
			</c:forEach>
	   </tbody>
	</table>
	<table width=100% border=0 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		<tr align="right">
			<td class="bluetext">合计数量:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${returnNumber}" maxFractionDigits="2" minFractionDigits="2"/></td>
		</tr>
	</table>
	</td></tr>
		
		<tr>
			<td>
				<table width="100%" border="0">
	
		<tr>
 		 <td class="bluetext" width="8%">销售签(章):</td>
 		 <td  width="17%">
			`
 		 </td>
 		 <td class="bluetext" width="8%">财务签(章):</td>
 		 <td  width="17%">
			`
 		 </td>
 		 
 		 <td class="bluetext" width="8%">仓库签(班组章):</td>
 		 <td  width="17%">
			`
 		 </td>
		</tr>
		
		<tr>
 		 <td class="bluetext" width="8%">日期:</td>
 		 <td width="17%">
			`
 		 </td>
 		 <td class="bluetext" width="8%">日期:</td>
 		 <td width="17%">
			`
 		 </td>
 		 
 		 <td class="bluetext" width="8%">日期:</td>
 		 <td width="17%">
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
			第一联：销售保留，第二联：财务保留，第三联：仓库保留
			<%--第一联：仓库保留，第二联：财务保留，第三联：供应商保留--%>
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
    <input  name="bequery" type="button" value="返回" onclick="toGo();"/>
    </div>  
</body>  
<script type="text/javascript">
    applyRowStyle(document.all('datatable1'));
    //applyRowStyle(document.all('datatable2'));
</script>
    </html> 