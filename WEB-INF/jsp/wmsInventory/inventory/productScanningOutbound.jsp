<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	 function produceScannOut(){
		var el = document.getElementsByName('ids');
		 	  var len = el.length;   
  		 	  var str="";
  			  var count=0;
  			  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str = str + el[i].value + ",";
					count++;
				}
  			}   
  		}
  		   if(count==0){
  			 alert('选勾选要出库的批次！');
  			 return false;
  		}
		$.ajax({
			type : "POST", //请求方式        
			url : "productScanningOutbound.do", //请求路径        
			cache : false,
			data : "ids=" + str, //传参        
			dataType : 'json', //返回值类型        
			success : function(json) {
				if(json==true){
					alert("出库成功!");
					window.location.href = 'listProductScanningOutbound.do';
				}else{
					alert("出库失败!");
				}	 
            }
	  	});
	 }
//-->
</script>
<html:form action="/listProductScanningOutbound.do">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="it.box.psoItem.poipItem.poip_number.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="it.box.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="it.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="it.box.psoItem.poipItem.poip_number.supplier.code" desc="供应商代码"/>
	<input type="hidden" name="fields" value="it.box.in_date" desc="入库时间"/>
	<input type="hidden" name="fields" value="it.box.location.code" desc="库位"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listProductScanningOutbound.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				 <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th><page:order order="id" style="text-decoration:none">
					条码编号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				    </page:order></th>
				    <th>采购单号</th>
					<th>行号</th>
					<th>物料号</th>
					<th>供应商代码</th>
					<th>描述一</th>
					<th>描述二</th>
					<th>入库时间</th>
					<th>条码数量</th>
					<th>库位</th>
					<th>库位库存</th>
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
