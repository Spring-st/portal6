<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/selection.js"></script>
<script type="text/javascript">
<!--
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
   
   
   function check(){
	   var el = document.getElementsByName('ids');   
  		var len = el.length;   
  		var count=0;
  		for(var i=0; i<len; i++)   
  		{   
  			if((el[i].type=="checkbox")&&(el[i].checked==true))   
  			{
				count++;
  			}   
  		}
  		
  		if(count ==len){
  			document.getElementById("checkAll").checked=true;
  		}else{
  			document.getElementById("checkAll").checked=false;
  		}
   }
	function selectSupplierShipOrder() {
 		var tempCustomerCode="";
 		var check = document.getElementsByName('ids');
		var result = new Array();
		var j=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked == true){
				var id=check[i].value;
				var map = [];
				map['id'] = id;
				map['code'] = document.getElementsByName('td_code'+id)[0].innerHTML;//供应商code
				map['name'] = document.getElementsByName('td_name'+id)[0].innerHTML;//供应商名称
				result[j]=map;
				j++;
			}
		}
		var num=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked==true){
				num=num+1;
			}
		}
		if(num==0){
			alert("请选择供应商!");
			return false;
		}
		if(num>1){
			alert("只能选择一个供应商!");
			return false;
		}
		window.parent.returnValue = result;
		window.parent.close();
	}
//-->
</script>
<html:form action="/selectSupplierByPortalShipOrder.do">
<%--<input type="hidden" name="fields" value="" desc="请选择"/>
	--%><input type="hidden" name="fields" value="supplier.code" desc="供应商编号"/>
	<input type="hidden" name="fields" value="supplier.name" desc="供应商名称"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/selectSupplierByPortalShipOrder.do">	
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
      	<th align="center"><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
        <th><page:order order="code" style="text-decoration:none">
					供应商编号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>供应商名称</th>
				
      </tr>
    </thead>
    <tbody id="datatable">
      <logic:iterate id="X_OBJECT" name="X_RESULTLIST">
        <bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
        	<tr align="center">
        		<td>
					<input type="checkbox" name="ids" value="${X_OBJECT.id}" id="cbox${X_OBJECT.id}" onclick="check();" />
        		</td>
        		<td id="td_code${X_OBJECT.id}">${X_OBJECT.code}</td>
        		<td id="td_name${X_OBJECT.id}">${X_OBJECT.name}</td>
        	</tr>
      </logic:iterate>
    </tbody>
  </table>
  <table>
		<tr>
			<td align="right">
				<input type="button" onclick="selectSupplierShipOrder()" value="选择" />
			</td>
		</tr>
	</table>
  <script type="text/javascript">
    applyRowStyle(document.all('datatable'));
  </script>
</page:form>	