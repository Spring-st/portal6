<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
			var sign=false;
			function selectScann(){ 
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
  			    	alert('选勾选要扫描的批次！');
  			    	return false;
  			    }
      		    if(!sign){
	       		   r = confirm("已选中批次确认采购收货吗？");
	       		   if(r){
	       			    v = window.showModalDialog(
						'showDialog.do?title=purchaseOrderRqcDetermine.title&purchaseOrderRqcDetermine.do?arrays='+str + "&type=2", 
						null, 'dialogWidth:550px;dialogHeight:300px;status:no;help:no;scroll:no');
					if (v) {
						 //window.location = window.location;
						 window.location.href = "purchaseOrderReceiptsBoxList.do";	
						};
	       		    }
				}else{
       				alert("正在收货,请稍后...");
  					return false;
       		   }
		}
		function purchaseReturnMaterialByBox(){
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
  			    	alert('选勾选要退货的批次！');
  			    	return false;
  			    }
      		   if(!sign){
	       		   r = confirm("已选中批次确认退货吗？");
	       		   if(r){
	       			  $.ajax({
						type : "POST", //请求方式        
						url : "purchaseReturnMaterialByAjax.do", //请求路径        
						cache : false,
						data : "array=" + str,      
						dataType : 'json', //返回值类型        
						success : function(json) {
					 		//alert("退货成功!");
					 			window.location.href = 'purchaseOrderReceiptsBoxList.do';
	         	 		}
		 	 		});
	       		}
       		}else{
       			alert("正在退货,请稍后...");
  				return false;
       		}
		}
</script>

<html:form action="/purchaseOrderReceiptsBoxList.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pb.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="pb.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="pb.po_line" desc="行号"/>
	<input type="hidden" name="fields" value="pb.psoItem.portalShipOrder.code" desc="发货单号"/>
	<%--<input type="hidden" name="fields" value="pb.single.code" desc="调料单号"/>--%>
	<input type="hidden" name="fields" value="pb.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="pb.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="pb.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="pb.po_supp" desc="供应商编号"/>
	<input type="hidden" name="fields" value="pb.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="pb.number" desc="数量"/>
	<input type="hidden" name="fields" value="pb.po_date" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>

<page:form action="/purchaseOrderReceiptsBoxList.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
		    <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
			<th>条码编号</th>	
			<th>采购单号</th>
			<th>行号</th>	
			<th>发货单号</th>
			<th>物料号</th>
			<th>DPI</th>
			<th>原厂编号</th>	
			<th>供应商编号</th>	
			<th>描述一</th>
			<th>数量</th>
			<th>时间</th>
			<th>质检状态</th>
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
