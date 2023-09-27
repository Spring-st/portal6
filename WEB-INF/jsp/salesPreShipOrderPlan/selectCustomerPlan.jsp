<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
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

   function generate(code){
 		var tempCustomerCode="";
 		var check = document.getElementsByName('ids');
		var result = new Array();
		var j=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked == true){
				var id=check[i].value;
				var qty_box=document.getElementById("qty_box"+id).value;
				if(qty_box<=0){
					alert("请先维护销售订单的包装箱容量！");
					return false;
				}
				var deliverNumber = document.getElementById('td_fahuoNumber'+id).value;
				var remark =document.getElementById('td_remark'+id).value; 
				var qtyopen = document.getElementById('td_qtyopen'+id).innerHTML;
				var map = [];
				map['id'] = id;
				map['code'] = code;
				map['soItemId'] = document.getElementById('td_soItemId'+id).value;//销售订单item的id
				map['soNumber'] = document.getElementById('td_soNumber'+id).innerHTML;//销售订单号
				map['customerName'] = document.getElementById('td_customerName'+id).innerHTML;//客户
				map['customerCode'] = document.getElementById('td_customerCode'+id).innerHTML;//客户编号
				map['customerAddress'] = document.getElementById('td_customerAddress'+id).innerHTML;//客户地址
				map['line'] = document.getElementById('td_line'+id).innerHTML;//行号
				map['itemNumberId'] = document.getElementById('td_itemNumberId'+id).innerHTML;//物料编码
				map['itemNumberDpi'] = document.getElementById('td_itemNumberDpi'+id).innerHTML;//物料编码
				map['itemNumberoldCode'] = document.getElementById('td_itemNumberoldCode'+id).innerHTML;//原厂编号
				map['itemNumberName'] = document.getElementById('td_itemNumberName'+id).innerHTML;//物料名称
				map['istxt'] = document.getElementById('td_istxt'+id).innerHTML;//是否开票
				map['um'] = document.getElementById('td_unit'+id).innerHTML;//单位
				map['duDate'] = document.getElementById('td_dueDate'+id).innerHTML;//要货日期
				map['qty'] = document.getElementById('td_qty'+id).innerHTML;//销售订单数量
				map['qtyopen'] = qtyopen;//未发货数量
				map['sotype']=document.getElementById("td_sotype"+id).innerHTML;//类型
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
		
		
		//var ss  = document.getElementsByName("poipItemIds");
		//	for(var i=0;i<ss.length;i++){
		//		$("input[name='cbox"+ss[i]+"']").attr('disabled','disabled');
		//	}	
			
		
		
		if(j==0){
				alert("请选择销售订单!");
		  		return false;
		}
		
			var checkEl= new Array();
			var m=0;
			var bool = true;
			for(i=0;i<check.length;i++){
				if(check[i].checked == true){
					checkEl[m]=check[i];
					m++;
				}
			}
			
			
			if(checkEl.length>1){
				for(var i=0;i<checkEl.length;i++){
					var val=document.getElementById("so_type"+checkEl[i].value).value;
					for(var j=i+1;j<checkEl.length;j++){
						//alert(j);
						var vd=document.getElementById("so_type"+checkEl[j].value).value;
						if(vd!=val){
							bool=false;
						}
					}

				}
			}
			
			if(!bool){
				alert("同一张预发货单不能同时既发给国外又发给国内！");
				return false;
			}
			

		window.parent.returnValue = result;
		window.parent.close();
	 }
   //-->
</script>
<html:form action="selectSalesOrderItem" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesOrderItem.soipNumber" desc="销售订单号"/>
	<input type="hidden" name="fields" value="salesOrderItem.soId.custCode" desc="客户编号"/>
	<input type="hidden" name="fields" value="salesOrderItem.itemNumber.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="salesOrderItem.itemNumber.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="salesOrderItem.itemNumber.oldCode" desc="原厂编号"/>
	
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<html:hidden property="selectSoipitemId" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectSalesOrderItem.do" method="post">

	<%--<jsp:include page="../pageHead.jsp"/>
	--%><table class="data">
		<thead>
			<tr  bgcolor="#9999ff">
			<th width="1%"><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
			
			<th width="6%">销售订单号</th>
			<th width="7%">客户</th>
			<th width="5%">客户编码</th>
			<th width="8%">送货地址</th>
			<th width="4%">行号</th>
			<th width="8%">物料编码</th>
			<th width="5%">DPI</th>
			<th width="5%">原厂编号</th>
			<th width="8%">物料名称</th>
			<th width="4%">是否开票</th>
			<th width="4%">单位</th>
			<th width="8%">要求交货日期</th>
			<th width="8%">销售订单数量</th>
			<th width="8%">未发货数量</th>
			<th width="4%">类型</th>
			<th width="5%">发货数量</th>
			<th width="5%">备注(需要优先发货,请备注)</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
					<td width="3%">
					
						<input type="hidden"  id="td_soItemId${X_OBJECT.id}" value="${X_OBJECT.id}"/>
						
							<input type="checkbox" name="ids" value="${X_OBJECT.id}" id="cbox${X_OBJECT.id}" onclick="check()" />
						<!-- 
							<c:if test="${X_OBJECT.checked=='checked'}">disabled="disabled"</c:if>
							/>
						 -->
					</td>
					<td id="td_soNumber${X_OBJECT.id}">
						${X_OBJECT.soId.soNumber}
					</td>
					<td id="td_customerName${X_OBJECT.id}">${X_OBJECT.soId.custName}</td>
					<td id="td_customerCode${X_OBJECT.id}">${X_OBJECT.soId.custCode}</td>
					<td id="td_customerAddress${X_OBJECT.id}">${X_OBJECT.soId.custAddress}</td>
					<td id="td_line${X_OBJECT.id}">${X_OBJECT.line}</td>
					<td id="td_itemNumberId${X_OBJECT.id}">${X_OBJECT.itemNumber.id}</td>
					<td id="td_itemNumberDpi${X_OBJECT.id}">${X_OBJECT.itemNumber.dpiNo}</td>
					<td id="td_itemNumberoldCode${X_OBJECT.id}">${X_OBJECT.itemNumber.oldCode}</td>
					<td id="td_itemNumberName${X_OBJECT.id}">${X_OBJECT.itemNumber.name}</td>
					<td id="td_istxt${X_OBJECT.id}">${X_OBJECT.istxt.chnShortDescription}</td>
					<td id="td_unit${X_OBJECT.id}">${X_OBJECT.itemNumber.unit}</td>
					<td id="td_dueDate${X_OBJECT.id}">${X_OBJECT.dueDate}</td>
					
					<td id="td_qty${X_OBJECT.id}">
					    ${X_OBJECT.qty}
					</td>
				
		
					
					<td id="td_qtyopen${X_OBJECT.id}">${X_OBJECT.qtyopen}</td>
					
					  <td id="td_sotype${X_OBJECT.id}">
					  	${X_OBJECT.sotype}
					  </td>

					
					<td > 
						<input type="hidden" id="qty_box${X_OBJECT.id}" value="${X_OBJECT.boxcount}"/>
						<input size="10" type="text" <c:if test="${X_OBJECT.checked=='checked'}">disabled="disabled"</c:if> id='td_fahuoNumber${X_OBJECT.id}'/>
						<input type="hidden" id="so_type${X_OBJECT.id}" value="${X_OBJECT.sotype}"/>
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
				<input type="button" onclick="generate('${X_OBJECT.soId.soNumber}')" value="选择" />
			</td>
		</tr>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



