<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
     function view(id) { 
		v = window.showModalDialog(
			'showDialog.do?title=StoreRoom.edit.title&viewBatchAdjustment.do?id=' + id , 
			null, 'dialogWidth:500px;dialogHeight:400px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
		};
	}
//-->
</script>
<html:form action="/listBatchAdjustment.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.new_box_id.lot.id" desc="原条码编号"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.new_box_id.number" desc="原条码数量"/>
	<input type="hidden" name="fields" value="bab.box_id.lot.id" desc="新条码编号"/>
	<input type="hidden" name="fields" value="bab.box_id.number" desc="新条码数量"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.new_box_id.psoItem.poipItem.poip_number.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.new_box_id.psoItem.poipItem.line" desc="行号"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.new_box_id.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.new_box_id.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.new_box_id.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.new_box_id.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.new_box_id.part.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.old_location.code" desc="库位"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.operation.name" desc="操作用户"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.date" desc="操作时间"/>
	<input type="hidden" name="fields" value="bab.box_adjustment_id.remark" desc="备注"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listBatchAdjustment.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>原条码编号</th>
				<th>数量</th>
				<th>新条码编号</th>
				<th>数量</th>
				<th>采购单号</th>
				<th>行号</th>	
				<th>物料号</th>
				<th>DPI</th>
				<th>原厂编号</th>
				<th>描述一</th>
				<th>描述二</th>
				<th>库位</th>
				<th>类型</th>
				<th>操作用户</th>
				<th>操作时间</th>
				<th>备注</th>
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

