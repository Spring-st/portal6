<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
 		function validateBoxQueryForm(){
	  		  var el = document.getElementsByName('ids');
		 	  var len = el.length;   
  		 	  var str="";
  			  var count=0;
  			  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str = str + el[i].value+",";
					count++;
				}
  			}   
  		}
  		        if(count==0){
  			    	alert('选勾选要扫描的条码！');
  			    	return false;
  			    }
  		        
      			r = confirm("确认上线扫描吗？");
       		    if(r){
       		    	document.getElementById("arrays").value = str;
       		    	return true;
       			 }
 }
</script>
<html:form action="/insertPurchaseOrderHighLineTwo.do" onsubmit="return validateBoxQueryForm();">
    <input type="hidden" id="arrays" name="arrays" />
    
    <div style="font-size: 20px; font-weight: bold; text-align: center; color: blue; margin: 15px; 0px;">选择条码上线</div>
    <table style="margin: 20px 0px;" width="100%">
		<thead>
			<tr style="background-color: orange">
				<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th>条码编号</th>
				<th>物料号</th>
				<th>描述</th>
				<th>数量</th>
			</tr>
		</thead>
		<tbody id="datatable" >
			 <c:forEach var="x_object" items="${x_boxList}">
			        <tr>
			           <td><input type="checkbox" name="ids" value="${x_object.lot.id}" onclick="productbox_click();"/> </td>
                       <td>${x_object.lot.id}</td>
                       <td>${x_object.part.id}</td>
                       <td>${x_object.part.describe1}</td>	
                       <td>${x_object.number}</td>
                    </tr>		
			  </c:forEach>
		</tbody>
	</table>
<div>
 
</div>
     <hr />
	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /></html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>	
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
