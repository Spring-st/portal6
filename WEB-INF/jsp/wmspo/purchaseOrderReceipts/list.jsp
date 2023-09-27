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
	function edit(id) {
		window.location.href="editPurchaseOrderReceipts.do?id=" + id;
	}
	
	function printCode(){
		window.location.href="pirntCodePurchaseOrderInspectionPendingItem.do";
	}
	function printBc(){
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
<html:form action="listPurchaseOrderReceipts" styleId="formId">
	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td width="20%" class="bluetext"><bean:message key="storageLocation.site" />:</td>
			<td width="30%"><html:select property="site">
			<html:options collection="X_SITELIST" property="id" labelProperty="name"/>
				</html:select></td>
			<td class="bluetext"><bean:message key="storageLocation.poNumber" />:</td>
			<td><html:text property="poOrder" size="8"/></td>
			</tr>
			<tr>
			<td class="bluetext"><bean:message key="storageLocation.poDate" />:</td>
			<td><html:text property="startPoDate" size="8"/><a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg1',false,'startShip_date',null,null,'purchaseOrderReceiptsQueryForm')"><img
				align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
			&nbsp;~&nbsp; <html:text property="endPoDate" size="6" /> 
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg2',false,'endShip_date',null,null,'purchaseOrderReceiptsQueryForm')"><img
				align="absmiddle" border="0" id="dimg2" src="images/datebtn.gif" /></a>
			</td>
			<td class="bluetext"><bean:message key="storageLocation.supplier" />:</td>
			<td><html:text property="supplier" size="8"/></td>
			</tr>
			<tr>		
				<td class="bluetext"><bean:message key="storageLocation.supplier.code" />:</td>
			<td><html:text property="supplierCode" size="8"/></td>	
			<td class="bluetext"><bean:message key="storageLocation.status" />:</td>
		<td>
	    <html:select property="status">
	    	<html:option value=""><bean:message key="all.all"/></html:option>
	      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><html:options collection = "X_STATUS" property = "enumCode" labelProperty = "engShortDescription"/></c:if>
	      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}"><html:options collection = "X_STATUS" property = "enumCode" labelProperty = "chnShortDescription"/></c:if>
	    </html:select>
	  </td>
	  </tr>
	</table>
	<div>
		<html:submit><bean:message key="all.query"/></html:submit>
		<input type="button" value="打印条码" onclick="printBc();" />
	</div>
</html:form>
<hr />
<page:form action="listPurchaseOrderReceipts.do" method="post">
	<jsp:include page="../../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				 <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th><page:order order="id" style="text-decoration:none">
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
					<th>描述二</th>
					<th>包装量</th>
					<th>供应商代码</th>
					<th>收货时间</th>
					<th>计划数</th>
					<th>收货数</th>
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

