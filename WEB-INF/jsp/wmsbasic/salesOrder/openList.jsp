<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
	 function addSalesOrder() {
		v = window.showModalDialog(
			'showDialog.do?title=SalesOrderItem.new.title&newSalesOrderItem.do' , 
			null, 'dialogWidth:400px;dialogHeight:450px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=SalesOrderItem.edit.title&editSalesOrderItem.do?id=' + id , 
			null, 'dialogWidth:400px;dialogHeight:450px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
		};
	}
	function salesOrderItemOpen(){
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
  			    	alert('选勾选要打开的销售订单！');
  			    	return false;
  			    }
      		   
       	r = confirm("确认打开这些销售订单吗？");
       	if(r){
       	  $.ajax({
			type : "POST", //请求方式        
			url : "salesOrderItemOpen.do", //请求路径        
			cache : false,
			data : "arrays=" + str, //传参        
			dataType : 'json', //返回值类型        
			success : function(json) {
				 window.location.href = "listOpenSalesOrderItem.do";
           		}
	  		});
       	 }
	 }
</script>
<html:form action="listOpenSalesOrderItem" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesOrderItem.soipNumber" desc="销售订单号"/>
	<input type="hidden" name="fields" value="salesOrderItem.soId.custName" desc="客户"/>
	<input type="hidden" name="fields" value="salesOrderItem.soId.custCode" desc="客户编码"/>
	<input type="hidden" name="fields" value="salesOrderItem.soId.custAddress" desc="送货地址"/>
	<input type="hidden" name="fields" value="salesOrderItem.line" desc="行号"/>
	<input type="hidden" name="fields" value="salesOrderItem.itemNumber.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="salesOrderItem.itemNumber.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="salesOrderItem.itemNumber.oldCode" desc="原厂编号"/>
	<%--<input type="hidden" name="fields" value="salesOrderItem.supplierItemNumber" desc="供应商零件号"/>--%>
	<input type="hidden" name="fields" value="salesOrderItem.um" desc="单位" />
	<input type="hidden" name="fields" value="salesOrderItem.qty" desc="销售订单数量"/>
	<input type="hidden" name="fields" value="salesOrderItem.dueDate" desc="到货日期"/>
	<input type="hidden" name="fields" value="salesOrderItem.receiptQty" desc="已发货数量"/>
	<input type="hidden" name="fields" value="salesOrderItem.boxcount" desc="包装箱量"/>
	<input type="hidden" name="fields" value="salesOrderItem.describe" desc="描述"/>
	<%--<input type="hidden" name="fields" value="salesOrderItem.returnQtySum" desc="退货数量"/>
	<input type="hidden" name="fields" value="salesOrderItem.factory" desc="发货工厂" />
	--%><input type="hidden" name="fields" value="salesOrderItem.poDomain" desc="域" />
	<input type="hidden" name="fields" value="salesOrderItem.site.name" desc="站点" />
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<input type="hidden" id="statusCheck" name="statusCheck"/>

	<table width=100% border="0" cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="listOpenSalesOrderItem" method="post">
	<table class="data" width="1300px;" >
		<thead>
			<tr bgcolor="#9999ff">
			<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th><page:order order="id" style="text-decoration:none">
					销售订单号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order></th>
				<th>客户	</th>
				<th>客户编码	</th>
				<th>送货地址</th>
				<th>行号	</th>
				<th>物料编号	</th>
				<th>DPI</th>
				<th>原厂编号</th>
				<th>单位	</th>
				<th>销售订单数量	</th>
				<th>到货日期	</th>
				<th>已发货数量	</th>
				<th>包装箱量	</th>
				<th>描述	</th>
				<%--<th>退货数量	</th>
				<th>发货工厂</th>
				--%><th>域</th>
				<th>站点	</th>
				<%--<th>是否开票	</th>
				<th>类型	</th>
			--%>
				<th>状态	</th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="openRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
//	alert(document.getElementById("formId").innerHTML);
    applyRowStyle(document.all('datatable'));
</script>

