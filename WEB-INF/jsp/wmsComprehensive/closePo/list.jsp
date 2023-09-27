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
<!--
		 function clsoePo(){
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
  			    	alert('选勾选要关闭的采购单！');
  			    	return false;
  			    }
      		   
       	r = confirm("确认关闭这些采购单吗？");
       	if(r){
       	  $.ajax({
			type : "POST", //请求方式        
			url : "purchaseOrderColse.do", //请求路径        
			cache : false,
			data : "arrays=" + str, //传参        
			dataType : 'json', //返回值类型        
			success : function(json) {
				 window.location.href = "listPurchaseOrderColse.do";
           		}
	  		});
       	 }
	 }
//-->
</script>
<html:form action="/listPurchaseOrderColse.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="po.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="po.supplier.code" desc="供应商代码"/>
	<input type="hidden" name="fields" value="po.supplier.name" desc="供应商名称"/>
	<input type="hidden" name="fields" value="po.createDate" desc="订单时间" />
	<input type="hidden" name="fields" value="po.qty" desc="订单数量"/>
	<input type="hidden" name="fields" value="po.receiptQty" desc="收货数量"/>
	<input type="hidden" name="fields" value="po.inventoryNumber" desc="入库数量" />
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<input type="hidden" id="statusCheck" name="statusCheck"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listPurchaseOrderColse.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th width="10%"><page:order order="id" style="text-decoration:none">
					 采购单号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order></th>
				    <th>供应商代码</th>
				    <th>供应商名称</th>
				    <th>订单时间</th>
				    <th>订单数量</th>
				    <th>收货数量</th>
				    <th>入库数量</th>
				    <th>状态</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

