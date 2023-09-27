<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" /> 

<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
     function add() {
        var wmsTools=document.getElementById("wmsTools").value;
        $.ajax({
			type : "POST", //请求方式        
			url : "knockTogetherBoxJson.do", //请求路径        
			cache : false,
			data : "wmsTool=" + wmsTools, //传参        
			dataType : 'json', //返回值类型        
			success : function(json) {
				for (i = 0; i < json.length; i++){
					var lotSer=json[i].lotSer;			
					var blanket=json[i].blanket;
					var wmsTool=json[i].wmsTool;
					var wmspart=json[i].wmspart;			
					var describe1=json[i].describe1;
					var describe2=json[i].describe2;
					var count=json[i].count;
				    var trString="<tr class='ids'>";	
				    trString += "<td><input type=\"checkbox\" name=\"ids\" value='"+lotSer+"' onclick=\"productbox_click();\"/></td>";
				    trString += "<td><input type='hidden' name='ids' value='"+lotSer+"' />"+(lotSer)+"</td>";
				    trString += "<td>"+(blanket)+"</td>";
				    trString += "<td>"+(wmsTool)+"</td>";
				    trString += "<td>"+(wmspart)+"</td>";
				    trString += "<td>"+(describe2)+"</td>";
				    trString += "<td>"+(count)+"</td>";
				    trString += "</tr>";
				    $("#datatable").append(trString).show();
				    applyRowStyle(document.all('datatable'));
				}
           }
	  });
        document.getElementById("addid").style.display = 'none';
        
  }
     function validatePoIpiBoxFormForm(){
      var el = document.getElementsByName('ids');
		  var result = []; 
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<el.length; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str = str + el[i].value+",";
					count++;
				}
  			}   
  		} 
  		  if(count==0){
  			  alert("请选择更换批次！");
  			  return false;
  		  } 
  		  
  		  document.getElementById("arrays").value = str;
     }
    function validatePoIpiBoxFormForm(){
    	var locationTxt = document.getElementById("location_id").value;
         if(locationTxt==""){
        	 alert("请选择库位！");
        	  return false;
         } 
      var el = document.getElementsByName('ids');
		  var result = []; 
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<el.length; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str = str + el[i].value+",";
					count++;
				}
  			}   
  		} 
  		  if(count==0){
  			  alert("请选择更换批次！");
  			  return false;
  		  } 
  		  
  		  document.getElementById("arrays").value = str;
     }
</script>
<html:form action="/updateDevanningBoxQuality.do" onsubmit="return validatePoIpiBoxFormForm(this)" styleId="formId">
<input type="hidden" id="arrays" name="arrays"/>

	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr><td width="10%" class="bluetext"><bean:message key="storageLocation.location" />:</td>
			<td width="15%"><input type="text" id="wmsTools" value="${x_location}"/></td>		
			<td width="65%" align="left">
				<input type="button"  value="查询" onClick="add()" id="addid"/>
			</td>
		</tr>
	</table>
<hr />
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			    <th width="3" align="center"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
			    <th>条码编号</th>
			    <th><bean:message key="warehouseInventoryRepor.blanket" /> </th>
				<th><bean:message key="purchaseOrder.wmsTool" /> </th>
				<th><bean:message key="purchaseOrder.wmsPart" /></th>
				<th><bean:message key="warehouseInventoryRepor.standard" /> </th>
			    <th><bean:message key="purchaseOrder.count" /></th> 
			</tr>
		</thead>
        <tbody id="datatable" >
        <c:forEach var="x_object" items="${x_result}">
           <tr>
              <td><input type="checkbox" name="ids" value="${x_object.lotSer.id}" onclick="productbox_click();"/></td>
              <td>${x_object.lotSer.id}</td>
              <td>${x_object.blanketMark}</td>
              <td>${x_object.wmsTool}</td>
              <td>${x_object.wmsPart.id}</td>
              <td>${x_object.wmsPart.describe2}</td>
              <td>${x_object.count}</td>
           </tr>
        </c:forEach>
		</tbody>
	</table>
<table>
 <tr>
 <td>
  <input type="hidden" id="location_id" name="location"/>
		        <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><span style="color: red; font-size: 15px">请选择需要更换的库位:</span></c:if>
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">location</c:if>
		         <span id="location_name">${x_purchaseOrderItem.location.code}</span>
		         <a href="javascript:selectStorageLocation();"><img src="images/select.gif" border="0"/></a>
 </td>
 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
 <td><input align="right" type="submit" value="提交" /></td>
 <td><input align="right" type="button" value="重置" onclick="location=location"/></td>
 <td><input align="right" type="button" value="关闭" onclick="window.close()"/></td>
 </tr>
</table>
</html:form>
<script type="text/javascript">
<!--
function selectStorageLocation(){
	var v = window.showModalDialog(
			'showDialog.do?title=supplier.selectSupplier.title&selectStorageLocationRadio.do', 
			null, 'dialogWidth:800px;dialogHeight:650px;status:no;help:no;scroll:no');
	if (v) {
			document.getElementById("location_id").value=v.code;
			document.getElementById("location_name").innerHTML=v.code;
		};
}
//-->
</script>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
