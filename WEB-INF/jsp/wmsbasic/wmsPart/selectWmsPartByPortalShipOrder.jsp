<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
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
	function selectWmsPartShipOrder() {
 		var tempCustomerCode="";
 		var check = document.getElementsByName('ids');
		var result = new Array();
		var j=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked == true){
				var id=check[i].value;
				var map = [];
				map['id'] = id;
				map['name'] = document.getElementsByName('td_name'+id)[0].innerHTML;//物料名称
				map['describe1'] = document.getElementsByName('td_describe1'+id)[0].innerHTML;//描述1
				map['vend'] = document.getElementsByName('td_vend'+id)[0].innerHTML;//供应商
				map['unit'] = document.getElementsByName('td_unit'+id)[0].innerHTML;//单位
				map['ord_mult'] = document.getElementsByName('td_ord_mult'+id)[0].innerHTML;//标准包装量
				map['securityQty'] = document.getElementsByName('td_securityQty'+id)[0].innerHTML;//安全库存
				map['projectedQty'] = document.getElementsByName('td_projectedQty'+id)[0].innerHTML;//预计库存
				var qty="0";
				var fahuoNumber = document.getElementsByName('td_fahuoNumber'+id)[0].value;
				var ordMult=document.getElementsByName('td_ord_mult'+id)[0].innerHTML;//标准包装量
				//if(parseFloat(fahuoNumber)<=parseFloat(qty)){
				//	alert(id +": 发货数量必须大于0");
				//	return false;
				//}
				map['fahuoNumber']= fahuoNumber;
				if(parseFloat(ordMult)==0){
					alert("包装量为0,不能发货!");
					return false;
				}
				if(fahuoNumber <=0){
					alert("发货数量不能小于等于0!");
					return false;
				}
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
			alert("请选择发货物料!");
			return false;
		}
		window.parent.returnValue = result;
		window.parent.close();
	}
//-->
</script>
<html:form action="/selectWmsPartByPortalShipOrder.do">
<input type="hidden" name="receivingDate" id="receivingDate" value="${receivingDate}"/>
<input type="hidden" id="supplierCode" name="supplierCode" value="${supplierCode}">
<input type="hidden" name="portalShipOrderByPartId" id="portalShipOrderByPartId" value="${portalShipOrderByPartId}"/>
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pfnr.part.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="pfnr.part.name" desc="物料名称"/>
	<input type="hidden" name="fields" value="pfnr.part.productSpecifications" desc="车型"/>
	<input type="hidden" name="fields" value="pfnr.part.drwgLoc" desc="类型"/>
	<input type="hidden" name="fields" value="pfnr.part.vend" desc="供应商编码"/>
	<input type="hidden" name="fields" value="pfnr.part.ord_mult" desc="标准包装量"/>
	<input type="hidden" name="fields" value="pfnr.part.securityQty" desc="安全库存"/>
	<input type="hidden" name="fields" value="pfnr.projectedQty" desc="预计库存"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/selectWmsPartByPortalShipOrder.do">	
<input type="hidden" name="receivingDate" id="receivingDate" value="${receivingDate}"/>
<input type="hidden" id="supplierCode" name="supplierCode" value="${supplierCode}">
<input type="hidden" name="portalShipOrderByPartId" id="portalShipOrderByPartId" value="${portalShipOrderByPartId}"/>
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
      	<th align="center"><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
        <th><page:order order="part_Id" style="text-decoration:none">
					物料编码
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>物料名称</th>
				<th><page:order order="productspecifications" style="text-decoration:none">
					车型
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th><page:order order="part_drwgLoc" style="text-decoration:none">
					类型
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>供应商编码</th>
				<th>单位</th>
				<th>标准包装量</th>
				<th>安全库存</th>
				<th>预计库存</th>
				<th>发货数量</th>
				
      </tr>
    </thead>
    <tbody id="datatable">
      <logic:iterate id="X_OBJECT" name="X_RESULTLIST">
        <bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
        	<tr align="center">
        		<td>
        			<c:choose>
							<c:when test="${X_OBJECT.part.checked=='checked'}">
								<input type="checkbox" disabled="disabled" />
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="ids" value="${X_OBJECT.part.id}" id="cbox${X_OBJECT.part.id}" onclick="check();" />
							</c:otherwise>
						</c:choose>	
        		</td>
        		<td id="td_id${X_OBJECT.part.id}">${X_OBJECT.part.id}</td>
        		<td id="td_name${X_OBJECT.part.id}">${X_OBJECT.part.name}</td>
        		<td id="td_describe1${X_OBJECT.part.id}">${X_OBJECT.part.productSpecifications}</td>
        		<td>${X_OBJECT.part.drwgLoc}</td>
        		<td id="td_vend${X_OBJECT.part.id}">${X_OBJECT.part.vend}</td>
        		<td id="td_unit${X_OBJECT.part.id}">${X_OBJECT.part.unit}</td>
        		<td id="td_ord_mult${X_OBJECT.part.id}">
        			<fmt:formatNumber value="${X_OBJECT.part.ord_mult}" maxFractionDigits="0" />
        		</td>
        		<td id="td_securityQty${X_OBJECT.part.id}">
        			<fmt:formatNumber value="${X_OBJECT.part.securityQty}" maxFractionDigits="0" />
        		</td>
        		<td id="td_projectedQty${X_OBJECT.part.id}">
        			<fmt:formatNumber value="${X_OBJECT.projectedQty}" maxFractionDigits="0" />
        		</td>
        		<td>
        			<fmt:formatNumber value="${X_OBJECT.noNeedqty}" var="qty" maxFractionDigits="0" minFractionDigits="0" />
        			<input size="10" type="text" value="${qty}" <c:if test="${X_OBJECT.part.checked=='checked'}">disabled="disabled"</c:if> id='td_fahuoNumber${X_OBJECT.part.id}'/>
        		</td>
        	</tr>
      </logic:iterate>
    </tbody>
  </table>
  <table>
		<tr>
			<td align="right">
				<input type="button" onclick="selectWmsPartShipOrder()" value="选择" />
			</td>
		</tr>
	</table>
  <script type="text/javascript">
    applyRowStyle(document.all('datatable'));
  </script>
</page:form>		      
