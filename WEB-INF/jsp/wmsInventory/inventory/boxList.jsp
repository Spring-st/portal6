<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">

	function validateInventoryQueryForm(){
		var el = document.getElementsByName('ids');
		 	  var len = el.length;   
  		 	  var str="";
  			  var count=0;
  			  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str = str + el[i].value + ",";
					count++;
				}
  			}   
  		}
  		   if(count==0){
  			 alert('选勾选要出库的批次！');
  			 return false;
  		}
  		
  		document.getElementById("arraysId").value = str;
  		document.inventoryQueryForm.submit();        
	}

</script>
<html:form action="/purchaseOrderOutbound.do" onsubmit="return validateInventoryQueryForm()">
<input type="hidden" id="arraysId" name="arrays"/>
<div style="text-align: center; font-size: 20px; font-weight: bolder;padding: 15px 0px;">原材料扫描出库</div>
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
			<th>条码编号</th>
			<th>物料号</th>
			<th>描述一</th>
			<th>库位代码</th>
			<th>数量</th>	
		</tr>
	</thead>
	<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="x_box">
				 <tr>	
				 	<td align="center"><input style="display:none;" checked="checked" type="checkbox" name="ids" value="${X_OBJECT.lot.id}" onclick="productbox_click();"/>${X_OBJECT.lot.id}</td>
				 	<td align="center">${X_OBJECT.part.id}</td>
				 	<td align="center">${X_OBJECT.part.describe1}</td>
				 	<td align="center">${X_OBJECT.location.code}</td>
				 	<td align="center">${X_OBJECT.number}</td>
				 </tr>
			</logic:iterate>
		</tbody>
		</table>
 <div style="margin: 10px 10px;" align="center">
		<html:submit>确认出库</html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>	
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
