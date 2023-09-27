<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>

<script type="text/javascript">


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

   function generate(type){
 		var tempCustomerCode="";
 		var check = document.getElementsByName('ids');
		var result = new Array();
		var j=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked == true){
				var id=check[i].value;
				var code = document.getElementById('td_code'+id).innerHTML;
				var name =document.getElementById('td_basic_storeroom_id_code'+id).innerHTML;
				var address = document.getElementById('td_address'+id).innerHTML;
				var describe = document.getElementById('td_description'+id).innerHTML;
				var map = [];
				map['id'] = id;
				map['str'] = id;
				map['code'] = code;
				map['name'] = name;
				map['address'] = address;
				map['describe'] = describe;
				result[j]=map;
				j++;
			}
		}
		if(j==0){
				alert("请选择库位!");
		  		return false;
		}
		window.parent.returnValue = result;
		window.parent.close();
	 }
    function confirm(){
      var result = [];
      var el = document.getElementsByName('ids');   
  	  var len = el.length; 
      var str="";
   	  var count=0;
 	  for(var i=0; i<len; i++){   
 		 if((el[i].type=="checkbox") && (el[i].checked== true)){
			 if(el[i].id!="checkAll"){
				 str=str+el[i].value+",";
				  count++;
			    }
 			}   
 		 }
 	  if(count==0){
  		alert("请选择库位！");
  		return false;
  		}
 	    result['str'] = str;
		window.parent.returnValue = result;
		window.parent.close();
     }
</script>
<html:form action="selectStorageLocationType" >
	 <input type="hidden" name="fields" value="" desc="请选择"/>
	 <input type="hidden" name="fields" value="sl.code" desc="库位编号"/>
	 <input type="hidden" name="fields" value="sl.description" desc="描述"/>
	 <input type="hidden" name="fields" value="sl.basic_storeroom_id.code" desc="所属库房"/>
	
	 
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<%--<input type="hidden" id="ExportType" name="exportType"/>--%>
	
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectStorageLocationType.do" method="post">
	<table class="data">
	
    	<thead>
    	  <tr bgcolor="#9999ff">
    	  
    	  	<th>
    	  		<input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/>
    	  	</th>
			 <th>
				<page:order order="id" style="text-decoration:none">
					<bean:message key="storageLocation.id" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				    <th>
					<bean:message key="storageLocation.describe" />
					</th>
					<%--<th>
					<bean:message key="storageLocation.address" />
					</th>
					<th>
					<bean:message key="storageLocation.max_inventory" />
					</th>--%>
					<th>
					库位库存
					</th>
					<th>
					<bean:message key="storageLocation.storeroom_id" />
					</th>
					<th>
					冻结状态
					</th>
					<th>
					<bean:message key="storageLocation.enabled" />
					</th>
     			</tr>
    	</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
					<td align="center"">
						<c:if test="${X_OBJECT.freeae_status!='0'}"><input type="checkbox" name="ids" value="${X_OBJECT.id}" id="cbox${X_OBJECT.id}" onclick="check();"/></c:if>
						<c:if test="${X_OBJECT.freeae_status=='0'}"><input type="checkbox" disabled="disabled" /></c:if>
					</td>
					<td id="td_code${X_OBJECT.id}">
						${X_OBJECT.code}
					</td>
					<td id="td_description${X_OBJECT.id}">
						${X_OBJECT.description }
					</td>
					<%--<td id="td_address${X_OBJECT.id}">
						${X_OBJECT.address }
					</td>
					<td id="td_max_inventory${X_OBJECT.id}">
						${X_OBJECT.max_inventory }
					</td>--%>
					<td>
						${X_OBJECT.number }
					</td>
					<td id="td_basic_storeroom_id_code${X_OBJECT.id}"> 
						${X_OBJECT.basic_storeroom_id.code }
					</td>
					<td id="td_freeae_status${X_OBJECT.id}">
						<c:if test="${X_OBJECT.freeae_status=='0'}">已冻结</c:if>
        				<c:if test="${X_OBJECT.freeae_status=='1'||X_OBJECT.freeae_status==null}">未冻结</c:if>
					</td>
					<td id="td_enabled${X_OBJECT.id}">
						<span style="color:${X_OBJECT.enabled.color}">
      						<c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.enabled.engShortDescription}</c:if>
      						<c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
    					</span>
					</td>
					</tr>
			</logic:iterate>
		</tbody>
	</table>
	<table>
		<tr>
			<td align="right">
				<input type="button" onclick="confirm()" value="选择" />
			</td>
		</tr>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



