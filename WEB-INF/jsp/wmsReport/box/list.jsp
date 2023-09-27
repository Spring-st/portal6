<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	 
</script>
<html:form action="/listBoxReport.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pb.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="pb.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="pb.po_line" desc="行号"/>
	<%--<input type="hidden" name="fields" value="pb.single.code" desc="调料单号"/>--%>
	<input type="hidden" name="fields" value="pb.psoItem.portalShipOrder.code" desc="发货单号"/>
	<input type="hidden" name="fields" value="pb.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="pb.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="pb.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="pb.po_supp" desc="供应商编号"/>
	<input type="hidden" name="fields" value="pb.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="pb.location.code" desc="库位编号"/>
	<input type="hidden" name="fields" value="pb.number" desc="数量"/>
	<input type="hidden" name="fields" value="pb.part.unit" desc="单位"/>
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
<page:form action="/listBoxReport.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
			<th>条码编号</th>	
			<th>采购单号</th>
			<th>行号</th>	
			<th>发货单号</th>
			<th>物料号</th>
			<th>DPI</th>
			<th>原厂编号</th>	
			<th>供应商编号</th>	
			<th>描述一</th>
			<th>库位编号</th>
			<th>数量</th>
			<th>单位</th>
			<th>
			<page:order order="id" style="text-decoration:none">
					时间
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order>
			</th>
			<th>质检状态</th>
			<th>冻结状态</th>
			<th>批次状态</th>
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
