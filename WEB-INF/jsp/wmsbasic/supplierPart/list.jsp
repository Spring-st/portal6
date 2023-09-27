<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function addUserRole() {
		v = window.showModalDialog(
			'showDialog.do?title=userRole.new.title&newSupplierPart.do?supplierId=${X_SUPPLIER.id}', 
			null, 'dialogWidth:550px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function editProcedureCraftSub(id1) {
		v = window.showModalDialog(
			'showDialog.do?title=userRole.edit.title&editSupplierPart.do?&id=' + id1, 
			null, 'dialogWidth:550px;dialogHeight:380px;status:no;help:no;scroll:no');
		
		if (v) {
			updateRow(document.all('r' + id1), v);
		};
	}

	function deleteProcedureCraftSub(id1) {
		var r = confirm("确认要删除？");
		if (r) {
			window.location.href = "deleteSupplierPart.do?&id=" + id1;
			//deleteRow(document.all('r' + id1), v);
		}
	}
//-->
</script>
<table width="100%" border="0" cellpadding="4" cellspacing="0">
  <tbody>
    <tr>
      <td class="bluetext"><bean:message key="supplier.code"/>:</td>
      <td>${X_SUPPLIER.code}</td>
      <td class="bluetext"><bean:message key="supplier.name"/>:</td>
      <td>${X_SUPPLIER.name}</td>
    </tr>
  </tbody>
</table>

<hr/>
<div><h3 class="formtitle"><bean:message key="SupplierPart.grantedRole"/></h3></div>
<input type="button" value="<bean:message key="all.new"/>" onclick="addUserRole();" />
<input type="button" value="<bean:message key="all.back"/>" onclick='window.location.href="listSupplier.do";'/>
<form action="listSupplierPart.do" method="get">
<input type="hidden" name="supplierId" value="${X_SUPPLIER.id}"/>
<table class="data">
  <thead>
      <tr bgcolor="#9999ff">
      <th><bean:message key="SupplierPart.supplierId.code"/></th> 
      <th><bean:message key="SupplierPart.supplierId.address1"/></th> 
      <th>电话</th>   
      <th><bean:message key="SupplierPart.supplierPart"/></th>  
      <th>单位</th>  
      <th>包装量</th>  
      <th>行号</th> 
      <th><bean:message key="SupplierPart.factory"/></th> 
      <th><bean:message key="SupplierPart.partId.code"/></th>   
      <th><bean:message key="SupplierPart.partId.name"/></th>   
      <th>单位</th> 
      <th>容量</th> 
      <th>所占比例</th> 
      <th>调整</th>
      <th>删除</th>
    </tr>
  </thead>
  <tbody id="datatable">
    <logic:iterate id="X_OBJECT" name="X_SUBLIST">
      <bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
      <jsp:include page="row.jsp"/>
    </logic:iterate>
  </tbody>
</table>
<script type="text/javascript">
  applyRowStyle(document.all('datatable'));
</script>
</form>
