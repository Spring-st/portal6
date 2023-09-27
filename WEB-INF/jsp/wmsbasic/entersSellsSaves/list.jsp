<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function add() {
		v = window.showModalDialog(
			'showDialog.do?title=WmsPart.new.title&newReportEntersSellsSaves.do' , 
			null, 'dialogWidth:300px;dialogHeight:200px;status:no;help:no;scroll:no');
		if (v) {
			 window.location.href = "listReportEntersSellsSaves.do";
		};
	}
//-->
</script>
<html:form action="/listReportEntersSellsSaves.do" styleId="formId">
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="br.part.id" desc="物料代码"/>
	<input type="hidden" name="fields" value="br.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="br.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="br.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="br.part.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="br.month" desc="月份"/>
	<input type="hidden" name="fields" value="br.start_date" desc="开始时间"/>
	<input type="hidden" name="fields" value="br.end_date" desc="结束时间"/>
	<input type="hidden" name="fields" value="br.initial_qty" desc="初始值"/>
	<input type="hidden" name="fields" value="br.balance_qty" desc="结存"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listReportEntersSellsSaves.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>物料代码</th>
				<th>DPI</th>
				<th>原厂编号</th>
				<th>描述一</th>
				<th>描述二</th>
				<th>月份</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>初始值</th>
				<th>结存</th>
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

