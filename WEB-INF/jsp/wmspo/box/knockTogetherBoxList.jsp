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
    
  
        var wmsTools=document.getElementById("wmsTools").value;
        
       $.ajax( {
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
				    trString += "<td align='center'><input type='checkbox' id='de' value="+lotSer+" /></td>";
				     trString += "<td id='lotSer'>"+(lotSer)+"</td>";
				    trString += "<td >"+(wmsTool)+"</td>";
				    trString += "<td>"+(wmspart)+"</td>";
				    trString += "<td >"+(describe1)+"</td>";
				    trString += "<td>"+(describe2)+"</td>";
				    trString += "<td>"+(count)+"</td>";
				    trString += "</tr>";
				    $("#datatable").append(trString).show();
				}
    }
			});
      
			}
    	
</script>
<html:form action="/listKnockTogetherBox" styleId="formId">
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		
		<tr><td class="bluetext"><bean:message key="storageLocation.wmsTool" />:</td>
			<td><input type="text" id="wmsTools" /></td>		
			<td >
				<input type="button" value="<bean:message key="all.new"/>"
					onClick="add()" />
			</td>
			
		</tr>
	</table>
</html:form>
<page:form action="/listKnockTogetherBox.do" method="post">
	<jsp:include page="../../pageHead.jsp"/>
	<html:hidden property="id" />
		
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			    <th></th>
			    <th >
					<bean:message key="purchaseOrder.lotSer" />					
				</th>
				    <th >
					<bean:message key="purchaseOrder.wmsTool" />
					</th>
				<th >
					<bean:message key="purchaseOrder.wmsPart" />					
				</th>
				    <th >
					<bean:message key="purchaseOrder.describe1" />
					</th>
					<th >
					<bean:message key="purchaseOrder.describe2" />
					</th>
					<th >
					<bean:message key="purchaseOrder.count" />
					</th>		
			</tr>
			
		</thead>
        <tbody id="datatable" >
			 
		</tbody>
	
		<tbody >
		<tr><td><input type="button" value="合并至" /></td><td><input type="text" name="wmsTool" id="wmsTools"/></td><td><input type="button" value="确定" onclick="verify()"/></td></tr>
		</tbody>
	</table>
</page:form>
<script type="text/javascript">
<!--
function verify(){  
    		   
    		    var wmsTool = document.getElementById("wmsTool").value;
       			var el = document.getElementsByName('de');   
  		        var len = el.length;   
  		        var str="";
  		        var count=0;
  		        for(var i=0; i<len; i++)   
  		        {   
  			          if((el[i].type=="checkbox") && (el[i].checked== true))   
  			    {
				  if(el[i].lotSer!="checkAll"){
					   str=str+el[i].value+",";
					   count++;
				}
				 
  			}   
  		}
  		         if(count==0){
  			    	alert('<bean:message key="Please select a print batches"/>');
  			    	return false;
  			    }	
  		         if(wmsTool==""){
  			    	alert('<bean:message key="Please select a print wmsTool"/>');
  			    	return false;
  			    }	
       		window.location.href="updateKnockTogetherBoxQuality.do?str="+str.toString()+"&wmsTool="+wmsTool;
       	}
//-->
</script>




