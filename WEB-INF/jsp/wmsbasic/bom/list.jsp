<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
 
<html:form action="/listBom.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
		<input type="hidden" name="fields" value="ba.father_part.id" desc="父料号"/>
	<input type="hidden" name="fields" value="ba.father_part.describe1" desc="父料号描述"/>
	<input type="hidden" name="fields" value="ba.child_part.id" desc="子料号"/>
	<input type="hidden" name="fields" value="ba.child_part.describe1" desc="子料号描述"/>
	<input type="hidden" name="fields" value="ba.child_part.productClass" desc="产品类"/>
	<input type="hidden" name="fields" value="ba.unit_qty" desc="用量"/>
	<input type="hidden" name="fields" value="ba.child_part.unit" desc="单位"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listBom.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
					<th>父料号</th>
					<th>父料号描述</th>
					<th>子料号</th>
					<th>子料号描述</th>
					<th>产品类</th>
					<th>用量</th>
					<th>单位</th>
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

