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
	function partOutbound(){
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
  			    	alert('选勾选要扫描的批次！');
  			    	return false;
  			    }
  		   
       			    v = window.showModalDialog(
					'showDialog.do?title=purchaseOrderRqcDetermine.title&getRecommendLot.do?arrays='+str, 
					null, 'dialogWidth:600px;dialogHeight:400px;status:no;help:no;scroll:no');
				if (v) {
					 location = location;
			};
	}
//-->
</script>
<html:form action="/listInventory.do">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="it.box.psoItem.poipItem.poip_number.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="it.box.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="it.poipItem.poip_number.po_number" desc="物料号"/>
	<input type="hidden" name="fields" value="it.poipItem.line" desc="供应商代码"/>
	<input type="hidden" name="fields" value="it.poipItem.itemNumber.id" desc="库位"/>
	<input type="hidden" name="fields" value="it.poipItem.poip_number.supplier.code" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listInventory.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th>
					<page:order order="id" style="text-decoration:none">
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
					<th>入库时间</th>
					<th>出库时间</th>
					<th>条码数量</th>
					<th>库位</th>
					<th>库存</th>
					<th>状态</th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				 <tr id="r${X_OBJECT.id}">
					<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
					<td>${X_OBJECT.box.lot.id}</td>
					<td>${X_OBJECT.box.po_number}</td>
					<td>${X_OBJECT.box.po_line}</td>
					<td>${X_OBJECT.box.part.id}</td>
					<td>${X_OBJECT.box.po_supp}</td>
					<td>${X_OBJECT.box.part.describe1}</td>
					<td>${X_OBJECT.box.in_date}</td>
					<td>${X_OBJECT.box.po_date}</td>
					<td>${X_OBJECT.box.number}</td>
					<td>${X_OBJECT.box.location.code}</td>
					<td>${X_OBJECT.qty}</td>
					<td>${X_OBJECT.box.status.chnShortDescription}</td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
