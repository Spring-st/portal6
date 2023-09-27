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
	function createReceipts(id) {
		var r = confirm("确认创建收货单吗？");
		if(r){
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
  			    	alert('选勾选发货单！');
  			    	return false;
  			    }
  		        $.ajax({         
                type:"POST", //请求方式        
                url:"insertPurchaseOrderReceiptsByAjax.do", //请求路径        
                cache: false,           
                data:"ids="+str,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                	window.location.href="listPurchaseOrderCondiment.do";
			   } 
            })
		}
	}

function printPurchaseOrderCondiment() {
		  var el = document.getElementsByName('ids');
		  var len = el.length;   
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str=str+el[i].value;
					count++;
				}
  			}   
  		}
  		        if(count==0){
  			    	alert('请勾选调料单！');
  			    	return false;
  			    }
  		        window.location.href = "printPurchaseOrderCondiment.do?array="+str;
		}
		
		function withdraw(){
			var r = confirm("确认撤回该调料单吗？");
			if(r){
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
  			    	alert('选勾选发货单！');
  			    	return false;
  			    }
  		      
  		        $.ajax({         
                type:"POST", //请求方式        
                url:"updatePurchaseOrderCondiment.do?ids="+str, //请求路径        
                cache: false,           
                data:"ids="+str,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(da){
                	if(da["Not"]=="Not"){
                		alert("调料单已收货，无法撤回!");
                	}
                	if(da["ABC"]=="1"){
                		window.location.href="listPurchaseOrderCondiment.do";
                	}
                	window.location.href="listPurchaseOrderCondiment.do";
			   } 
            });
		}
		}
		function checkBoxs(obj){    
		var checks = document.getElementsByName("ids");    
		if(obj.checked){
			for(var i=0;i<checks.length;i++){
				checks[i].checked = false;        
			}       
			obj.checked = true;
		}else{
			for(var i=0;i<checks.length;i++){    
				checks[i].checked = false;
			}   
		}
	}
//-->
</script>
<html:form action="/listPurchaseOrderCondiment.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pocs.po_detial_id.poip_number.po_number" desc="采购单"/>
	<input type="hidden" name="fields" value="pocs.code" desc="调料单号"/>
	<input type="hidden" name="fields" value="pocs.po_detial_id.line" desc="行号"/>
	<input type="hidden" name="fields" value="pocs.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="pocs.supplier.code" desc="供应商编号"/>
	<input type="hidden" name="fields" value="pocs.date" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listPurchaseOrderCondiment.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			    <th width="2%"><%--<input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/>--%></th>
				<th>调料单号</th>
				<th>
				<page:order order="id" style="text-decoration:none">
					采购单
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order></th>
					<th>行号</th>
				    <th>物料号</th>
				    <th>描述一</th>
				    <th>供应商代码</th>
				    <th>采购单时间</th>
				    <th>发货时间</th>
				    <th>采购单量</th>
				    <th>发货数量</th>
				    <th>收货数量</th>
				    <th>入库数量</th>
				    <th>状态</th>
				    <th>打印状态</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="condimentRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

