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
<html:form action="/listProduceBuckleMaterial.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pbm.assembly.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="pbm.part.id" desc="总成编号"/>
	<input type="hidden" name="fields" value="pbm.date" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listProduceBuckleMaterial.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				    <th>总成编号</th>
				    <th>描述一</th>
				    <th>物料编号</th>
				    <th>描述一</th>
				    <th>扣料库位</th>
				    <th>扣料数量</th>
				    <th>时间</th>
				    <th>同步状态</th>
				    <th>同步时间</th>
				    <%-- <th>赛赫条码</th>--%>
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

