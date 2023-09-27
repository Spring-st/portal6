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
				var deliverNumber = document.getElementById('td_fahuoNumber'+id).value;
				var part_qty=document.getElementById('td_part_qty'+id).innerHTML;
				var map = [];
				map['id'] = id;
				map['oldCode'] = document.getElementById('td_oldCode'+id).innerHTML;//原厂编号
				map['name'] = document.getElementById('td_name'+id).innerHTML;//物料名称
				map['carBrand1'] = document.getElementById('td_carBrand1'+id).innerHTML;//国内品牌1
				map['domesticCar1'] = document.getElementById('td_domesticCar1'+id).innerHTML;//国内车型1
				map['yearFrom1'] = document.getElementById('td_yearFrom1'+id).innerHTML;//年份起1
				map['yearTo1'] = document.getElementById('td_yearTo1'+id).innerHTML;//年份讫1
				map['productSpecifications'] = document.getElementById('td_productSpecifications'+id).innerHTML;//产品规格
				map['partPrice'] = document.getElementById('td_part_partPrice_amt'+id).innerHTML;//单价
				map['sotaxc'] = document.getElementById('td_part_partPrice_sotaxc'+id).innerHTML;//税率
				map['curr'] = document.getElementById('input_part_partPrice_curr'+id).value;//币种
				map['deliverNumber'] = deliverNumber;//发货数量
				result[j]=map;
				j++;
				if(deliverNumber==null || deliverNumber==""){
					alert("请输入要发货的数量！");
					return false;
				}
				if(parseFloat(deliverNumber) >part_qty){
					alert("发货数量不能大于物料库存量，请重新输入!");
					document.getElementById("td_fahuoNumber"+id).value ="";
					document.getElementById("td_fahuoNumber"+id).focus();
					return false;
				}
			}
			
		}
		window.parent.returnValue = result;
		window.parent.close();
	 }
   //-->
</script>
<html:form action="/selectCustomerPlanDeliveryWmsPart.do">
<input type="hidden" name="customerId" id="customerId" value="${x_customerId}" />
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pps.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="pps.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="pps.part.name" desc="物料名称"/>
	<input type="hidden" name="fields" value="pps.part.carBrand1" desc="国内品牌1"/>
	<input type="hidden" name="fields" value="pps.part.domesticCar1" desc="国内车型1"/>
	<input type="hidden" name="fields" value="pps.part.yearFrom1" desc="年份起1"/>
	<input type="hidden" name="fields" value="pps.part.yearTo1" desc="年份讫1"/>
	<input type="hidden" name="fields" value="pps.part.productSpecifications" desc="产品规格"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectCustomerPlanDeliveryWmsPart" method="post">	
<input type="hidden" name="customerId" id="customerId" value="${x_customerId}" />		
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
      	<th align="center"><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
        <th><page:order order="part_id" style="text-decoration:none">
					物料编号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>原厂编号</th>
				<th>物料名称</th>
				<th>国内品牌1</th>
				<th>国内车型1</th>
				<th><page:order order="part_yearFrom1" style="text-decoration:none">
					年份起1
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>年份讫1</th>
				<th>产品规格</th>
				<th>物料库存</th>
				<th>单价</th>
				<th>基准</th>
				<th>发货数量</th>
      </tr>
    </thead>
    		<tbody id="datatable" >
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr align="center">
					<td>
						<input type="hidden"  id="td_id${X_OBJECT.part.id}" value="${X_OBJECT.part.id}"/>
						<c:if test="${X_OBJECT.part.checked eq 'checked'}">
							<input type="checkbox" disabled="disabled" />
						</c:if>
						<c:if test="${X_OBJECT.part.checked ne 'checked'}">
							<input type="checkbox" name="ids" value="${X_OBJECT.part.id}" id="cbox${X_OBJECT.part.id}" onclick="check()" />
						</c:if>
						
					</td>
					<td id="td_id${X_OBJECT.part.id}">
						${X_OBJECT.part.id}
					</td>
					<td id="td_oldCode${X_OBJECT.part.id}">${X_OBJECT.part.oldCode}</td>
					<td id="td_name${X_OBJECT.part.id}">${X_OBJECT.part.name}</td>
					<td id="td_carBrand1${X_OBJECT.part.id}">${X_OBJECT.part.carBrand1}</td>
					<td id="td_domesticCar1${X_OBJECT.part.id}">${X_OBJECT.part.domesticCar1}</td>
					<td id="td_yearFrom1${X_OBJECT.part.id}">${X_OBJECT.part.yearFrom1}</td>
					<td id="td_yearTo1${X_OBJECT.part.id}">${X_OBJECT.part.yearTo1}</td>
					<td id="td_productSpecifications${X_OBJECT.part.id}">${X_OBJECT.part.productSpecifications}</td>
					<td id="td_part_qty${X_OBJECT.part.id}">${X_OBJECT.sumNumber}</td>
					<c:if test="${not empty X_OBJECT.part.partPrice.amt}">
						<td id="td_part_partPrice_amt${X_OBJECT.part.id}"><fmt:formatNumber value="${X_OBJECT.part.partPrice.amt}" type="number" maxFractionDigits="2" minFractionDigits="2"/></td>
					</c:if>
					<c:if test="${empty X_OBJECT.part.partPrice.amt}">
						<td id="td_part_partPrice_amt${X_OBJECT.part.id}" style="color: red;">无有效价格</td>
					</c:if>
					<td id="td_part_partPrice_sotaxc${X_OBJECT.part.id}">
						${X_OBJECT.part.partPrice.sotaxc}
					</td>
					<td> 
						<input size="15" type="text"  <c:if test="${X_OBJECT.part.checked eq 'checked'}">disabled="disabled"</c:if> id='td_fahuoNumber${X_OBJECT.part.id}'/>
						<input type="hidden" id="input_part_partPrice_curr${X_OBJECT.part.id}" value="${X_OBJECT.part.partPrice.curr}" />
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
