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
	 function printPoBc(){
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
  		
		window.location.href="purchaseOrderBoxPrint.do?arrays=" + str;
	}
//-->
</script>
<html:form action="/purchaseOrderPrint.do">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="poitem.poip_number.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="poitem.line" desc="行号"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.id" desc="物料号"/>
	<input type="hidden" name="fields" value="poip_number.supplier.code" desc="供应商编号"/>
	<input type="hidden" name="fields" value="poitem.poip_number.createDate" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/purchaseOrderPrint.do" method="post">
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
				    <th>行号</th>
				    <th>物料号</th>
				    <th>描述一</th>
				    <th>描述一</th>
				    <th>供应商代码</th>
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
				<jsp:include page="poPrintRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

