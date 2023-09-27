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

   function generate(){
 		var tempCustomerCode="";
 		var check = document.getElementsByName('ids');
		var result = new Array();
		var j=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked == true){
				var id=check[i].value;
				var deliverNumber = document.getElementById('td_fahuoNumber'+id).value;
				var map = [];
				map['id'] = id;
				map['dpiNo'] = document.getElementById('td_dpiNo'+id).innerHTML;//DPI
				map['name'] = document.getElementById('td_name'+id).innerHTML;//销售订单号
				map['describe1'] = document.getElementById('td_describe1'+id).innerHTML;//客户
				map['describe2'] = document.getElementById('td_describe2'+id).innerHTML;//客户编号
				map['unit'] = document.getElementById('td_unit'+id).innerHTML;//客户地址
				map['deliverNumber'] = deliverNumber;
				result[j]=map;
				j++;
				if(deliverNumber==null || deliverNumber==""){
					alert("请输入要发货的数量！");
					return false;
				}
			}
			
		}
		window.parent.returnValue = result;
		window.parent.close();
	 }
   //-->
</script>
<html:form action="/selectWmsPart.do">
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="srt.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="srt.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="srt.name" desc="物料名称"/>
	<input type="hidden" name="fields" value="srt.describe1" desc="描述一"/>
	
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectWmsPart" method="post">			
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
      	<th align="center"><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
        <th><page:order order="id" style="text-decoration:none">
					物料编号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>DPI</th>
				<th>
				物料名称
					</th>
					<th>
					描述一
					</th>
					<th>
					描述二
					</th>
					<th>
					<bean:message key="wmsPart.unit" />
					</th>
				<th>
				发货数量
				</th>
      </tr>
    </thead>
    		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
					<td  align="center">
						<input type="hidden"  id="td_id${X_OBJECT.id}" value="${X_OBJECT.id}"/>
						<input type="checkbox" name="ids" value="${X_OBJECT.id}" id="cbox${X_OBJECT.id}" onclick="check()" />
					</td>
					<td id="td_id${X_OBJECT.id}">
						${X_OBJECT.id}
					</td>
					<td id="td_dpiNo${X_OBJECT.id}">
						${X_OBJECT.dpiNo}
					</td>
					<td id="td_name${X_OBJECT.id}">${X_OBJECT.name}</td>
					<td id="td_describe1${X_OBJECT.id}">${X_OBJECT.describe1}</td>
					<td id="td_describe2${X_OBJECT.id}">${X_OBJECT.describe2}</td>
					<td id="td_unit${X_OBJECT.id}">${X_OBJECT.unit}</td>
					<td> 
						<input size="15" type="text" id='td_fahuoNumber${X_OBJECT.id}'/>
					</td>
					</tr>
			</logic:iterate>
		</tbody>
  </table>
  <table>
		<tr>
			<td align="right">
				<input type="button" onclick="generate()" value="选择" />
			</td>
		</tr>
	</table>
  <script type="text/javascript">
    applyRowStyle(document.all('datatable'));
  </script>
</page:form>		      
