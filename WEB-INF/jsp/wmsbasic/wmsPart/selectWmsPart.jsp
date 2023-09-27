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
	function selectWmsPartStocking() {
 		var tempCustomerCode="";
 		var check = document.getElementsByName('ids');
		var result = new Array();
		var j=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked == true){
				var id=check[i].value;
				var map = [];
				map['id'] = id;
				map['oldCode'] = document.getElementById('td_oldCode'+id).innerHTML;//DPI
				map['name'] = document.getElementById('td_name'+id).innerHTML;//物料名称
				map['describe1'] = document.getElementById('td_describe1'+id).innerHTML;//描述1
				map['describe2'] = document.getElementById('td_describe2'+id).innerHTML;//描述2
				map['unit'] = document.getElementById('td_unit'+id).innerHTML;//客户地址
				result[j]=map;
				j++;
			}
		}
		window.parent.returnValue = result;
		window.parent.close();
	}
//-->
</script>
<html:form action="/selectWmsPartStocking.do">

<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="spsn.part.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="spsn.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="spsn.part.name" desc="物料名称"/>
	<input type="hidden" name="fields" value="spsn.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="spsn.sumNumber" desc="物料库存"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectWmsPartStocking" method="post">	
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
      	<th align="center"><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
        <th><page:order order="part" style="text-decoration:none">
					物料编号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>原厂编号</th>
				<th>
				物料名称
					</th>
					<th>
					描述一
					</th>
					<th>
					描述二
					</th>
					<th><page:order order="sumNumber" style="text-decoration:none">
					物料库存
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
					<th>
					<bean:message key="wmsPart.unit" />
					</th>
      </tr>
    </thead>
    <tbody id="datatable">
      <logic:iterate id="X_OBJECT" name="X_RESULTLIST">
        <bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
        <jsp:include page="selectWmsPartRow.jsp"/>
      </logic:iterate>
    </tbody>
  </table>
  <table>
		<tr>
			<td align="right">
				<input type="button" onclick="selectWmsPartStocking()" value="选择" />
			</td>
		</tr>
	</table>
  <script type="text/javascript">
    applyRowStyle(document.all('datatable'));
  </script>
</page:form>		      
