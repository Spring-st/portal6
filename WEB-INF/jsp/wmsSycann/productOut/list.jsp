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
		function add(){
		 v = window.showModalDialog(
					'showDialog.do?title=purchaseOrderRqcDetermine.title&newProductOffline.do', 
					null, 'dialogWidth:600px;dialogHeight:400px;status:no;help:no;scroll:no');
				if (v) {
					window.location.href = "listProductOffline.do"; 
				};
		}
</script>
<html:form action="/listProductOffline.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="produce.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="produce.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="produce.part.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="produce.tool.code" desc="器具编号"/>
	<input type="hidden" name="fields" value="produce.part.id" desc="库位"/>
	<input type="hidden" name="fields" value="produce.po_date" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listProductOffline.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
			<th>物料号</th>	
			<th>描述一</th>
			<th>描述二</th>
			<th>器具编号</th>	
			<th>库位</th>
			<th>包装量</th>
			<th>数量</th>
			<th>操作人</th>
			<th>时间</th>
			<th>状态</th>
			<th>赛赫条码</th>
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
