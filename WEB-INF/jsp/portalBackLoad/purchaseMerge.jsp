<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">

</script>
<html:form action="/listPurchaseMerge.do">
	 <input type="hidden" name="fields" value="" desc="请选择"/>
	 <%--<input type="hidden" name="fields" value="poitem.poip_number.po_number" desc="采购单号"/>
	 --%><input type="hidden" name="fields" value="poitem.poip_number.supplier.code" desc="供应商编号"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.describe1" desc="物料描述"/>
	<input type="hidden" name="fields" value="poitem.qty" desc="数量"/>
	<input type="hidden" name="fields" value="poitem.itemNumber.unit" desc="单位"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<hr />
<page:form action="/listPurchaseMerge.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<%--<th>采购单号</th>--%>
				<th>供应商编码</th>
				<th>物料编码</th>
				<th>物料描述</th>
				<th>数量</th>
				<th>单位</th>
				<th>类型</th>
				<th>2小时需求数量</th>
				<th>4小时需求数量</th>
				<th>6小时需求数量</th>
				<th>8小时需求数量</th>
				<th>10小时需求数量</th>
				<th>12小时需求数量</th>
				<th>14小时需求数量</th>
				<th>16小时需求数量</th>
				<th>18小时需求数量</th>
				<th>20小时需求数量</th>
				<th>22小时需求数量</th>
				<th>24小时需求数量</th>
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
