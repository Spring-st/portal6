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
  		    	alert("请选择打印数据！");
  		    	return false;
  		    } 
			//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
			window.location.href = "purchaseManualCreateBarcodeMax.do?str="+str.toString();
	}
	function printCodeZhong(){
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
	  		    	alert("请选择打印数据！");
	  		    	return false;
	  		    } 
				//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
				window.location.href = "purchaseManualCreateBarcodeZhong.do?str="+str.toString();
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
  		    	alert("请选择打印数据！");
  		    	return false;
  		    } 
			//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
			window.location.href = "purchaseManualCreateBarcodeMin.do?str="+str.toString();
	}
	 
	 
		
	 
	 
	  function printQECodeMin(){
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
  		    	alert("请选择打印数据！");
  		    	return false;
  		    } 
			//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
			window.location.href = "purchaseManualCreateBarQEcodeMin.do?str="+str.toString();
	}
	 
	 function printQECodeMax(){
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
  		    	alert("请选择打印数据！");
  		    	return false;
  		    } 
			//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
			window.location.href = "purchaseManualCreateBarQEcodeMax.do?str="+str.toString();
	}
	 
	 
	 function printQECodeZhong(){
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
  		    	alert("请选择打印数据！");
  		    	return false;
  		    } 
			//window.location.href = "printCodePurchaseOrderInspectionPendingItem.do?supplierBatch="+supplierBatch+"&str="+str.toString();
			window.location.href = "purchaseManualCreateBarQEcodeZhong.do?str="+str.toString();
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
		window.location.href="viewPortalShipOrderTwoWrong.do?id="+obj;
	}
	function validateForm(){
		var myFile = document.getElementsByName('fileContent')[0].value;
		if(myFile==""){
			alert("请填写导入文件路径!");
			return false;
		} 
		return true;
	} 
	function dcexcel(){
		window.location.href="excelCreateBarcode.do";
	}
</script>
<html:form action="listPurchaseManualCreateBarcode" >
  	<input type="hidden" name="fields" value="" desc="请选择">
    <input type="hidden" name="fields" value="entity.part" desc="物料号"/>
    <input type="hidden" name="fields" value="entity.supplierName" desc="供应商编码"/>
    <input type="hidden" name="fields" value="entity.serialDate" desc="日期"/>
	<input type="hidden" name="fields" value="entity.serialNumber" desc="流水号"/>
	<input type="hidden" name="fields" value="entity.qty" desc="数量"/>
	<input type="hidden" name="fields" value="entity.printStatus" desc="是否打印(0未打印,1已打印)"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<html:form action="/importBarCode.do" enctype="multipart/form-data" onsubmit="return validateForm()">
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td width="5%"></td>
	  		<td width="40%"><html:file property="fileContent" size="40"></html:file></td>
	  		<td width="5%" align="left"><html:submit>导入</html:submit></td>
	  		<td align="left"><input type="button" value="<bean:message key="template.import"/>"
					onClick="dcexcel()" /></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listPurchaseManualCreateBarcode.do"  method="post">
<table class="data">
		<thead>
			<tr bgcolor="#9999ff" align="center">
	            <th><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
				<th>物料号</th>
				<th>物料名称</th>
				<th>单位</th>
				<th>车型</th>
				<th>供应商名称</th>
				<th>日期</th>
				<th>流水号</th>
				<th>数量</th>
				<th>是否打印</th>
			</tr>
		</thead>
		<tbody id="item_datatable2">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
					<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
					<jsp:include page="row.jsp" />
				</logic:iterate>
		</tbody>
	 </table>
</page:form>
<table width="100%" >
<tr>
	<td>
		<%--<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印一维码小标签"  onclick="printCodeMin()"/></c:if>
		<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印一维码中标签"  onclick="printCodeZhong()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印一维码大标签"  onclick="printCodeMax()"/></c:if>
        --%><c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印二维码小标签"  onclick="printQECodeMin()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印二维码中标签"  onclick="printQECodeZhong()"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印二维码大标签"  onclick="printQECodeMax()"/></c:if><%--
        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="<bean:message key="return" />"  onclick="ret('${x_portalShipOrder.id}')"/></c:if>
        <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Return"  onclick="ret('${x_portalShipOrder.id}')"/></c:if>

--%></td>
</tr>
</table>
<script type="text/javascript">
    applyRowStyle(document.all('item_datatable2'));
</script>