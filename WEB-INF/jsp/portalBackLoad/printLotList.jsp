<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
<!--
    function printCodeMax(){
		//var supplierBatch = document.getElementById("supplierBatch").value;
		//if(supplierBatch==""){
		//	alert("请输入供应商批次!");
		//	return false;
		//}
	    var el = document.getElementsByName('ids');  
  		var len = el.length;   
  		var str="";
  		var count=0;
  		for(var i=0; i<len; i++){   
  			if((el[i].type=="checkbox") && (el[i].checked== true)){
				if(el[i].id!="checkAll"){
					var itemid=document.getElementById("item"+el[i].value).value;
					//str=str+el[i].value+","+itemid+";";
					str=str+itemid+",";
					count++;
				}
  			}  
  		}
  		    if(count==0){
  		    	alert("请选择打印批次！");
  		    	return false;
  		    } 
			//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
			window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?str="+str.toString();
	}
	function printCodeMin(){
		//var supplierBatch = document.getElementById("supplierBatch").value;
		//if(supplierBatch==""){
		//	alert("请输入供应商批次!");
		//	return false;
		//}
	    var el = document.getElementsByName('ids');  
  		var len = el.length;   
  		var str="";
  		var count=0;
  		for(var i=0; i<len; i++){   
  			if((el[i].type=="checkbox") && (el[i].checked== true)){
				if(el[i].id!="checkAll"){
					var itemid=document.getElementById("item"+el[i].value).value;
					//str=str+el[i].value+","+itemid+";";
					str=str+itemid+",";
					count++;
				}
  			}  
  		}
  		    if(count==0){
  		    	alert("请选择打印批次！");
  		    	return false;
  		    } 
			//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
			window.location.href = "printCodePurchaseOrderInspectionPendingItemMin.do?str="+str.toString();
	}
	function printCodeZhong(){
		//var supplierBatch = document.getElementById("supplierBatch").value;
		//if(supplierBatch==""){
		//	alert("请输入供应商批次!");
		//	return false;
		//}
	    var el = document.getElementsByName('ids');  
  		var len = el.length;   
  		var str="";
  		var count=0;
  		for(var i=0; i<len; i++){   
  			if((el[i].type=="checkbox") && (el[i].checked== true)){
				if(el[i].id!="checkAll"){
					var itemid=document.getElementById("item"+el[i].value).value;
					//str=str+el[i].value+","+itemid+";";
					str=str+itemid+",";
					count++;
				}
  			}  
  		}
  		    if(count==0){
  		    	alert("请选择打印批次！");
  		    	return false;
  		    } 
			//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
			window.location.href = "printQrCodePurchasePendingItemZhong.do?str="+str.toString();
	}
	 function printQrCodeMax(){
		    var el = document.getElementsByName('ids');  
	  		var len = el.length;   
	  		var str="";
	  		var count=0;
	  		for(var i=0; i<len; i++){   
	  			if((el[i].type=="checkbox") && (el[i].checked== true)){
					if(el[i].id!="checkAll"){
						var itemid=document.getElementById("item"+el[i].value).value;
						//str=str+el[i].value+","+itemid+";";
						str=str+itemid+",";
						count++;
					}
	  			}  
	  		}
	  		    if(count==0){
	  		    	alert("请选择打印批次！");
	  		    	return false;
	  		    } 
				//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
				window.location.href = "printQrCodePurchasePendingItem.do?str="+str.toString();
		}
		function printQrCodeMin(){
		    var el = document.getElementsByName('ids');  
	  		var len = el.length;   
	  		var str="";
	  		var count=0;
	  		for(var i=0; i<len; i++){   
	  			if((el[i].type=="checkbox") && (el[i].checked== true)){
					if(el[i].id!="checkAll"){
						var itemid=document.getElementById("item"+el[i].value).value;
						//str=str+el[i].value+","+itemid+";";
						str=str+itemid+",";
						count++;
					}
	  			}  
	  		}
	  		    if(count==0){
	  		    	alert("请选择打印批次！");
	  		    	return false;
	  		    } 
				//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
				window.location.href = "printQrCodePurchasePendingItemMin.do?str="+str.toString();
		}
		function printQrCodeZhong(){
		    var el = document.getElementsByName('ids');  
	  		var len = el.length;   
	  		var str="";
	  		var count=0;
	  		for(var i=0; i<len; i++){   
	  			if((el[i].type=="checkbox") && (el[i].checked== true)){
					if(el[i].id!="checkAll"){
						var itemid=document.getElementById("item"+el[i].value).value;
						//str=str+el[i].value+","+itemid+";";
						str=str+itemid+",";
						count++;
					}
	  			}  
	  		}
	  		    if(count==0){
	  		    	alert("请选择打印批次！");
	  		    	return false;
	  		    } 
				//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
				window.location.href = "printCodePurchaseOrderInspectionPendingItemZhong.do?str="+str.toString();
		}
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
	    setCount();
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
       function setCount(){
	    var el = document.getElementsByName('ids');   
  		var len = el.length;   
  		var arrayObj = new Array();
  		for(var i=0; i<len; i++)   
  		{   
  			if((el[i].type=="checkbox")&&(el[i].checked==true)&&(el[i].id!="checkAll"))   
  			{
  				var str=el[i].value;
  				arrayObj.push(str);
  				
  			}   
  		 }
  		}
      // function window.close(){
    //	 window.location.href="editPurchaseOrderReceipts.do?id=${id}";
     //  }
//-->
	function ret(obj){
		//alert(obj);
		window.location.href="viewPortalShipOrderTwo.do?id="+obj;
	}
</script>
 <html:form action="/printPortalShipOrderPartCodeList"  method="post">
 </html:form>
 <page:form action="/printPortalShipOrderPartCodeList"  method="post">
<table width="100%">
<tr><td>
<h3 style="color:blue"><bean:message key="purchaseOrderReceipts.lot"/></h3>
<jsp:include page="../pageHead.jsp"/>

  <table class="data">
	<thead>
		<tr bgcolor="#9999ff" align="center">
<%--			<th><bean:message key="purchaseOrderReceipts.id" /></th>	--%>
            <th><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
			<th><bean:message key="purchaseOrder.lotser" /></th>
			<td>采购单号</td>
			<td>行号</td>
			<td>物料号</td>
			<td>批次数量</td>
			<th><bean:message key="purchaseOrder.isPrint" /></th>
		</tr>
	</thead>
	<tbody id="item_datatable2">
		<c:forEach var="X_OBJECT" items="${x_boxList}">
			<c:set var="X_OBJECT" scope="request" value="${X_OBJECT}"/>
			<jsp:include page="printLotRow.jsp" />
		</c:forEach>
		
	</tbody>
 </table>
</td></tr>

<tr><td>
        <%--<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印一维码小标签"  onclick="printCodeMin()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印一维码中标签"  onclick="printCodeZhong()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印一维码大标签"  onclick="printCodeMax()"/></c:if>
        --%><c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印二维码小标签"  onclick="printQrCodeMin()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印二维码中标签"  onclick="printQrCodeZhong()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印二维码大标签"  onclick="printQrCodeMax()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Print label Min"  onclick="printCodeMin()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Print label Zhong"  onclick="printCodeZhong()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Print label Max"  onclick="printCodeMax()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="<bean:message key="return" />"  onclick="ret('${x_portalShipOrder.id}')"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Return"  onclick="ret('${x_portalShipOrder.id}')"/></c:if>

</td></tr>
</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('item_datatable2'));
</script>