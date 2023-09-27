<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>

<script type="text/javascript">
<!--
	 
//-->
</script>
<html:form action="/listWmsStockingDifference.do">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="sr.stocking_id.code" desc="库房盘存编号"/>
	<input type="hidden" name="fields" value="sr.location.code" desc="库位"/>
	<input type="hidden" name="fields" value="sr.box.lot.id" desc="条码"/>
	<input type="hidden" name="fields" value="sr.box.part.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="sr.box.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="sr.box.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="sr.box.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="sr.inventory_qty" desc="计划数量"/>
	<input type="hidden" name="fields" value="sr.stocking_qty" desc="盘点数量"/>
	<input type="hidden" name="fields" value="sr.differences" desc="差异"/>
	<input type="hidden" name="fields" value="sr.stocking_id.operation.name" desc="创建人"/>
	<input type="hidden" name="fields" value="sr.stocking_id.start_date" desc="盘点时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<hr />
<page:form action="listWmsStockingDifference.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th><page:order order="id" style="text-decoration:none">
					<bean:message key="wmsStocking.id" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				   </page:order></th>
				   <th>库位</th>
				   <th>条码</th>
				   <th>物料编号</th>
				   <th>DPI</th>
				   <th>原厂编号</th>
				   <th>描述一</th>
				   <th>计划数量</th>
				   <th>盘点数量</th>
				   <th>差异</th>
					<th>创建人</th>
					<th>盘点时间</th>
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

