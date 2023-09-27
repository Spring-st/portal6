<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>

<script type="text/javascript">
		var sign=false;
<%--		function poRejectedMaterialByBox(){ 
			  var r = false;
			  var el = document.getElementsByName('ids');
		 	  var len = el.length;   
  		 	  var str="";
  		 	  var ids="";
  			  var count=0;
  			  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
					 var lotId  = document.getElementById("cb"+el[i].value);
					str = str + lotId.value + ",";
					count++;
				    ids=ids+el[i].value+",";
				//	count++;
				}
  			}   
  		}
  			  if(count==0){
  				  alert("请选择退货批次");
  				  return false;
  			  }
  			   if(!sign){
  				   //  sign=true;
  				   	v = window
					.showModalDialog(
							'showDialog.do?title=purchaseOrderRqcDetermine.title&produceRejectedMaterialBoxByReasons.do?arrays='
									+ str + "&type=1&ids="+ids, null,
							'dialogWidth:500px;dialogHeight:300px;status:no;help:no;scroll:no');
			if (v) {
				window.location.href = 'listProduceRejectedMaterialBoxList.do';
			}
			;
  			//	  
  			//	$.ajax({
			//	type : "POST", //请求方式        
			//	url : "poRejectedMaterialBoxByAjax.do", //请求路径        
			//	cache : false,
			//	data : "array=" + str,      
			//	dataType : 'json', //返回值类型        
			//	success : function(json) {
			//	 alert("退货成功!");
			//	 window.location.href = 'listProduceRejectedMaterialBoxList.do';
         	 // }
	 	 //});
  			}else{
  				alert("正在退货,请稍后...");
  				return false;
  			}
}--%>
function poRqc(type) {
	if (type != "") {
		var r = false;
		var el = document.getElementsByName('ids');
		var len = el.length;
		var str = "";
		var count = 0;
		for ( var i = 0; i < len; i++) {
			if ((el[i].type == "checkbox") && (el[i].checked == true)) {
				if (el[i].id != "checkAll") {
					var lotId  = document.getElementById("cb"+el[i].value);
					str = str + lotId.value + ",";
					count++;
				}
			}
		}
		if (count == 0) {
			alert('选勾选要扫描的批次！');
			return false;
		}

		r = confirm("已选中批次确认质检吗？");
		if (r) {
			v = window
					.showModalDialog(
							'showDialog.do?title=purchaseOrderRqcDetermine.title&purchaseOrderRqcDetermine.do?arrays='
									+ str + "&type=3", null,
							'dialogWidth:500px;dialogHeight:300px;status:no;help:no;scroll:no');
			if (v) {
				window.location.href = 'listProduceRejectedMaterialBoxList.do';
			}
			;
		}
	}
}
function poRejectedMaterialByBox(){
	var el = document.getElementsByName('ids');
	var len = el.length;
	var str = "";
	var count = 0;
	var r = false;
	for ( var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].checked == true)) {
			if (el[i].id != "checkAll") {
				str = str + el[i].value + ",";
				count++;
			}
		}
	}
	if (count == 0) {
		alert('选勾选要退货的批次！');
		return false;
	}
	if(!sign){
		sign=true;
		r = confirm("已选中批次确认退货吗？");
		if (r) {
			window.location.href = 'updateProduceRejectedMaterialBox.do?ids='+str+'&type=1';
		}else{
			sign=false;
		}
	}else{
		alert("正在退货,请稍后...");
		return false;
	} 
} 	
	function updateRemark(id,remark){
		$.ajax({
			type : "POST", //请求方式        
			url : "updateBoxByRemarkAjax.do", //请求路径        
			cache : false,
			data : "id=" + id+"&remark="+remark,      
			dataType : 'json', //返回值类型        
			success : function(json) {
         	}
 		});
	}
</script>
<html:form action="/listProduceRejectedMaterialBoxList.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pb.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="pb.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="pb.po_line" desc="行号"/>
	<input type="hidden" name="fields" value="pb.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="pb.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="pb.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="pb.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="pb.po_supp" desc="供应商编号"/>
	<input type="hidden" name="fields" value="pb.po_supp_name" desc="供应商名称"/>
	<input type="hidden" name="fields" value="pb.location.code" desc="库位"/>
	<input type="hidden" name="fields" value="pb.number" desc="数量"/>
	<input type="hidden" name="fields" value="pb.part.unit" desc="单位"/>
	<input type="hidden" name="fields" value="pb.psoItem.portalShipOrder.createDate" desc="时间"/>
	<input type="hidden" name="fields" value="pb.remark" desc="备注"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width="90%">
		<tr>
			<td>
				<html:errors />
			</td>
		</tr>
		<tr>
			<td>
				<div class="message">
					<html:messages id="x_message" message="true">
						${x_message}<br>
					</html:messages>
				</div>
			</td>
		</tr>
	</table>
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listProduceRejectedMaterialBoxList.do" method="post">
	<table class="data" style="width: 1150px;">
		<thead>
		<tr bgcolor="#9999ff" align="center">
		    <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
			<th>条码编号</th>	
			<th>采购单号</th>
			<th>行号</th>	
			<th>物料号</th>
			<th>DPI</th>	
			<th>原厂编号</th>
			<th>描述一</th>
			<th>供应商编号</th>	
			<th>供应商名称</th>	
			<th>库位</th>
			<th>数量</th>
			<th>单位</th>
			<th>时间</th>
			<th>质检状态</th>
			<th>备注</th>
			<th>冻结状态</th>
			<th>批次状态</th>
		</tr>
	</thead>
	<bean:define id="sumaCount" value="0"/>
	<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="boxlist">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="boxRow.jsp" />
				<bean:define id="sumaCount" value="${sumaCount+(X_OBJECT.number)}"/>
			</logic:iterate>
		</tbody>
		</table>
	<table width=100% border=1 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		 	<tr align="right">
				<td class="bluetext">合计数量:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumaCount}" maxFractionDigits="2" minFractionDigits="2"/></td>
			 </tr>
		</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
