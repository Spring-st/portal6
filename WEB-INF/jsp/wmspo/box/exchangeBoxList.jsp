<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" /> 
<script type="text/javascript">
    	function add() {
        var wmsTools=document.getElementById("locationOld").value;
        if(wmsTools==""){
        	alert("请填写库位!");
        }
        $.ajax({
			type : "POST", //请求方式        
			url : "knockTogetherBoxJson.do", //请求路径        
			cache : false,
			data : "wmsTool=" + wmsTools, //传参        
			dataType : 'json', //返回值类型        
			success : function(json) {
				for (i = 0; i < json.length; i++){
					var lotSer=json[i].lotSer;			
					var wmsTool=json[i].wmsTool;
					var wmspart=json[i].wmspart;			
					var describe1=json[i].describe1;
					var describe2=json[i].describe2;
					var count=json[i].count;
				    var trString="<tr class='ids'>";	
				    trString += "<td><input type='hidden' name='lotSer' value='"+lotSer+"' />"+(lotSer)+"</td>";
				    trString += "<td>"+(wmsTool)+"</td>";
				    trString += "<td>"+(wmsTool)+"</td>";
				    trString += "<td>"+(count)+"</td>";
				    trString += "</tr>";
				    $("#datatable").append(trString).show();
				}
           }
	  });
  }
</script>
<html:form action="/updateExchangeBoxQuality.do" styleId="formId">
	<table width=90% border=0 cellpadding=4 cellspacing=0>
			<tr>
			  <td class="bluetext"> <bean:message key="ExchangeBoxQuality.oldWmsTool"/>: </td>
			  <td>
			  <input type="text" id="locationOld" name="locationOld" />
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <a href="#" onclick="add();"><span class="bluetext">查看库位信息</span></a>	
			  </td>
			  </tr>
			  <tr>
			  <td class="bluetext">
			  <bean:message key="ExchangeBoxQuality.newWmsTool"/>:		
		      </td>
			  <td>
			  <input type="text" name="locationNew" />		
			  </td>
			  </tr>
	</table>
	<hr />
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			    <th><bean:message key="purchaseOrder.lotSer" />	</th>
			    <th><bean:message key="warehouseInventoryRepor.blanket" /> </th>
				<th><bean:message key="purchaseOrder.location" /> </th>
			    <th><bean:message key="purchaseOrder.count" /></th> 
			</tr>
		</thead>
        <tbody id="datatable" >
		</tbody>
	</table>
	<table align="center">
	<tr >
	<td><input align="right" type="button" value="关闭" onclick="window.close()"/></td>
	<td><input align="right" type="submit" value="确定" /></td>
	</tr>
	</table>
</html:form>
<script type="text/javascript">
<!--
function verify(){  
    		    var oldWmsTool = document.getElementById("w").value;   		     
       			 var newWmsTool = document.getElementById("m").value; 
  		         if(oldWmsTool==""){
  			    	alert('<bean:message key="Please select a print oldWmsTool"/>');
  			    	return false;
  			    }	
  		         if(newWmsTool==""){
  			    	alert('<bean:message key="Please select a print newWmsTool"/>');
  			    	return false;
  			    }	
       		window.location.href="updateExchangeBoxQuality.do?oldWmsTool="+oldWmsTool+"&newWmsTool="+newWmsTool;
       	}
//-->
</script>
