<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script type="text/javascript" src="includes/selection.js"></script>
<script type="text/javascript">
<!--
	function select(id,code, name, address, describe2) {
	    var list = new Array();
		var result = [];
		result['id'] = id;
		result['str'] = id;
		result['code'] = code;
		result['name'] = name;
		result['address'] = address;
		result['describe'] = describe2;
		
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
//-->
</script>
<html:form action="selectCustomerReturnLocation">
 <input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pwom.code" desc="库位编号"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectCustomerReturnLocation" method="post">			
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
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
					<th>
					<bean:message key="storageLocation.address" />
					</th>
					<th>
					<bean:message key="storageLocation.max_inventory" />
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
				    <th> </th>
      </tr>
    </thead>
    <tbody id="datatable">
      <logic:iterate id="X_OBJECT" name="X_RESULTLIST">
        <bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
        <jsp:include page="selectCustomerReturnLocationRow.jsp"/>
      </logic:iterate>
    </tbody>
  </table>
  <script type="text/javascript">
    applyRowStyle(document.all('datatable'));
  </script>
</page:form>		      
