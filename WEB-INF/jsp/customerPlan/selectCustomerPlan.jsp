<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
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
				var deliverNumber = document.getElementById('td_fahuoNumber'+id).value;//发货数量
				var qtyopen = document.getElementById('td_qtyopen'+id).innerHTML;//未发货数量
				var remark =document.getElementById('td_remark'+id).value;//备注
				var map = [];
				map['id'] = id;
				map['customerPlanId'] = document.getElementById('td_customerPlanId'+id).value;
				map['planNumbers'] = document.getElementById('td_planNumbers'+id).innerHTML;//客户需求计划单号
				map['line'] = document.getElementById('td_line'+id).innerHTML;//行号
				map['customerCode'] = document.getElementById('td_customerCode'+id).innerHTML;//客户编号
				map['customerAddress'] = document.getElementById('td_customerAddress'+id).innerHTML;//送货地址
				map['wmspartId'] = document.getElementById('td_wmspartId'+id).innerHTML;//物料编码
				map['wmspartDpiNo'] = document.getElementById('td_wmspartDpiNo'+id).innerHTML;//DPI
				map['wmspartoldCode'] = document.getElementById('td_wmspartoldCode'+id).innerHTML;//原厂编号
				map['wmspartDescribe1'] = document.getElementById('td_wmspartDescribe1'+id).innerHTML;//物料描述一
				map['wmspartUnit'] = document.getElementById('td_wmspartUnit'+id).innerHTML;//单位
				map['shipmentDate'] = document.getElementById('td_shipmentDate'+id).innerHTML;//要货日期
				map['qty'] = document.getElementById('td_qty'+id).innerHTML;//客户需求数量
				map['qtyopen'] = qtyopen;//未发货数量
				map['fahuoNumber'] = deliverNumber;//发货数量
				map['remark']=remark;
				//工厂防重
				if(tempCustomerCode==""){
					tempCustomerCode=document.getElementById('td_customerCode'+id).innerHTML;
				}else{
					if(tempCustomerCode!=document.getElementById('td_customerCode'+id).innerHTML){
						alert("同一张送货单不允许发给多个用户!");
						return false;
					}
				}
				
				
				result[j]=map;
				j++;
				if(deliverNumber==null || deliverNumber==""){
					alert("请输入要发货的数量！");
					return false;
				}
				if(parseFloat(deliverNumber) >qtyopen){
					alert("预发货数量不能大于未结数量，请重新输入!");
					document.getElementById("td_fahuoNumber"+id).value ="";
					document.getElementById("td_fahuoNumber"+id).focus();
					return false;
				}
			}
			
		}
		
		if(j==0){
				alert("请选择客户需求计划!");
		  		return false;
		}
		window.parent.returnValue = result;
		window.parent.close();
	 }
   //-->
</script>
<html:form action="/selectCustomerPlan.do" styleId="formId">
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="customerPlan.planNumbers" desc="单号"/>
	<input type="hidden" name="fields" value="customerPlan.line" desc="行号"/>
	<input type="hidden" name="fields" value="customerPlan.customer.code" desc="客户编号"/>
	<input type="hidden" name="fields" value="customerPlan.customerAddress" desc="客户地址"/>
	<input type="hidden" name="fields" value="customerPlan.wmspart.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="customerPlan.wmspart.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="customerPlan.wmspart.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="customerPlan.wmspart.describe1" desc="物料描述"/>
	<input type="hidden" name="fields" value="customerPlan.um" desc="单位" />
	<input type="hidden" name="fields" value="customerPlan.shipmentDate" desc="要货日期"/>
	<input type="hidden" name="fields" value="customerPlan.qty" desc="需求数量"/>
	<input type="hidden" name="fields" value="customerPlan.qtyOpen" desc="未发货数量"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectCustomerPlan" method="post">			
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
      	<th align="center"><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
        <th>
        	<page:order order="id" style="text-decoration:none">
				单号
				<page:desc>
					<img src="images/down.gif" border="0" />
				</page:desc>
				<page:asc>
					<img src="images/up.gif" border="0" />
				</page:asc>
			</page:order>
		</th>
		<th>行号</th>
		<th>客户编号</th>
		<th>送货地址</th>
		<th>物料编号</th>
		<th>DPI</th>
		<th>原厂编号</th>
		<th>物料描述</th>
		<th>单位</th>
		<th>要货日期</th>
		<th>需求数量</th>
		<th>未发货数量</th>
		<th>发货数量</th>
		<th>备注(需要优先发货,请备注)</th>
     </tr>
   </thead>
    		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr align="center">
					<td>
						<input type="hidden"  id="td_customerPlanId${X_OBJECT.id}" value="${X_OBJECT.id}"/>
						<c:if test="${X_OBJECT.checked eq 'checked'}">
							<input type="checkbox" disabled="disabled" />
						</c:if>
						<c:if test="${X_OBJECT.checked ne 'checked'}">
							<input type="checkbox" name="ids" value="${X_OBJECT.id}" id="cbox${X_OBJECT.id}" onclick="check()" />
						</c:if>
						
					</td>
					<td id="td_planNumbers${X_OBJECT.id}">
						${X_OBJECT.planNumbers}
					</td>
					<td id="td_line${X_OBJECT.id}">
						${X_OBJECT.line}
					</td>
					<td id="td_customerCode${X_OBJECT.id}">${X_OBJECT.customer.code}</td>
					<td id="td_customerAddress${X_OBJECT.id}">${X_OBJECT.customerAddress}</td>
					<td id="td_wmspartId${X_OBJECT.id}">${X_OBJECT.wmspart.id}</td>
					<td id="td_wmspartDpiNo${X_OBJECT.id}">${X_OBJECT.wmspart.dpiNo}</td>
					<td id="td_wmspartoldCode${X_OBJECT.id}">${X_OBJECT.wmspart.oldCode}</td>
					<td id="td_wmspartDescribe1${X_OBJECT.id}">${X_OBJECT.wmspart.describe1}</td>
					<td id="td_wmspartUnit${X_OBJECT.id}">${X_OBJECT.wmspart.unit}</td>
					<td id="td_shipmentDate${X_OBJECT.id}"> <fmt:formatDate value="${X_OBJECT.shipmentDate}" pattern="yyyy-MM-dd"/> </td>
					<td id="td_qty${X_OBJECT.id}">${X_OBJECT.qty}</td>
					<td id="td_qtyOpen${X_OBJECT.id}">${X_OBJECT.qtyOpen}</td>
					<td> 
						<input size="15" type="text"  <c:if test="${X_OBJECT.checked eq 'checked'}">disabled="disabled"</c:if> id='td_fahuoNumber${X_OBJECT.id}'/>
					</td>
					<td>
						<input type="text" <c:if test="${X_OBJECT.checked=='checked'}">disabled="disabled"</c:if> id='td_remark${X_OBJECT.id}'/>
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
