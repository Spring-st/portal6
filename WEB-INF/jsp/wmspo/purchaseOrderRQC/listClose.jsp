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
	function rqcClose(type){ 
			if(type!=""){
			  var r = false;
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
  			    	alert('选勾选要关闭的质检单！');
  			    	return false;
  			    }
      		 
       		   r = confirm("确定要关闭质检单吗？");
       		   if(r){
       			  	 $.ajax({
						type : "POST", //请求方式        
						url : "purchaseOrderRQCClose.do", //请求路径        
						cache : false,
						data : "arrays=" + str, //传参        
						dataType : 'json', //返回值类型        
						success : function(json) {
							window.location.href = "listPurchaseOrderRQCClose.do";
			           }
				  });
       		  }
		 }
}
//-->
</script>
<html:form action="/listPurchaseOrderRQCClose.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="rqc.po_number" desc="采购单编号"/>
	<input type="hidden" name="fields" value="rqc.line" desc="行号"/>
	<input type="hidden" name="fields" value="rqc.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="rqc.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="rqc.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="rqc.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="rqc.supper.code" desc="供应商编号"/>
	<input type="hidden" name="fields" value="rqc.supper.name" desc="供应商名称"/>
	<input type="hidden" name="fields" value="rqc.po_date" desc="采购日期"/>
	<input type="hidden" name="fields" value="rqc.create_date" desc="质检日期"/>
	<input type="hidden" name="fields" value="rqc.po_qty" desc="采购数量"/>
	<input type="hidden" name="fields" value="rqc.need_qty_rqc" desc="需质检量"/>
	<input type="hidden" name="fields" value="rqc.actual_qty_rqc" desc="已质检量"/>
	<input type="hidden" name="fields" value="rqc.qualified_qty" desc="合格量"/>
	<input type="hidden" name="fields" value="rqc.unqualified_qty" desc="不合格"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listPurchaseOrderRQCClose.do" method="post">
		<table class="data">
			<thead>
				<tr bgcolor="#9999ff">
				 <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
					<th>采购单号</th>
					<th>行号</th>
					<th>物料号</th>
					<th>DPI</th>
					<th>原厂编号</th>
					<th>描述一</th>
					<th>供应商编号</th>
					<th>供应商名称</th>
					<th>采购日期</th>
					<th>质检日期</th>
					<th>采购数量</th>
					<th>需质检量</th>
					<th>已质检量</th>
					<th>合格数量</th>
					<th>不合格</th>
					<th>状态 </th>
					
				</tr>
			</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<tr id="" align="center">
					<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
					<td>${X_OBJECT.po_number}</td>
					<td>${X_OBJECT.line}</td>
					<td>${X_OBJECT.part.id}</td>
					<td>${X_OBJECT.part.dpiNo}</td>
					<td>${X_OBJECT.part.oldCode}</td>
					<td>${X_OBJECT.part.describe1}</td>
					<td>${X_OBJECT.supper.code}</td>
					<td>${X_OBJECT.supper.name}</td>
					<td>${X_OBJECT.po_date}</td>
					<td>${X_OBJECT.create_date}</td>
					<td>${X_OBJECT.po_qty}</td>
					<td>${X_OBJECT.need_qty_rqc}</td>
					<td>${X_OBJECT.actual_qty_rqc}</td>
				  	<td>${X_OBJECT.qualified_qty}</td>
				  	<td>
				  		<c:if test="${X_OBJECT.unqualified_qty==null}">0</c:if>
				  		<c:if test="${X_OBJECT.unqualified_qty!=null}">${X_OBJECT.unqualified_qty }</c:if>
				  	</td>
				  	<td>${X_OBJECT.status.chnShortDescription}</td>
			</tr>
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

