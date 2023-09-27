<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
 
//-->
</script>
<html:form action="/inventoryMovingReport.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="iv.lotSer.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="iv.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="iv.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="iv.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="iv.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="iv.part.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="iv.old_location.code" desc="原库位"/>
	<input type="hidden" name="fields" value="iv.new_location.code" desc="新库位"/>
	<input type="hidden" name="fields" value="iv.qty" desc="数量"/>
	<input type="hidden" name="fields" value="iv.part.unit" desc="单位"/>
	<input type="hidden" name="fields" value="iv.date" desc="日期"/>
	<input type="hidden" name="fields" value="iv.is_sync" desc="是否同步(0-已同步,1-未同步)"/>
	<input type="hidden" name="fields" value="iv.is_sync_date" desc="同步日期"/>
	<input type="hidden" name="fields" value="iv.remark" desc="备注"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/inventoryMovingReport.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				 <th>条码编号</th>
			    <th>物料号</th>
			    <th>DPI</th>
			    <th>原厂编号</th>
			    <th>描述一</th>
			    <th>描述二</th>
			    <th>原库位</th>
			    <th>新库位</th>
			    <th>数量</th>
			    <th>单位</th>
			    <th>日期</th>
			    <th>是否同步</th>
			    <th>同步日期</th>
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

