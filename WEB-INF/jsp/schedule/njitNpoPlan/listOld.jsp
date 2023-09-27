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
<html:form action="/listPurchaseItemWrong.do">
	<input type="hidden" name="fields" value="" desc="请选择"/><%--
	<input type="hidden" name="fields" value="entity.dataset" desc="数据集"/>
	--%><input type="hidden" name="fields" value="entity.partId.id" desc="原材料号"/>
	<input type="hidden" name="fields" value="entity.partId.describe1" desc="中文描述"/>
	<input type="hidden" name="fields" value="entity.partId.unit" desc="单位"/>
	<input type="hidden" name="fields" value="entity.partId.vend" desc="供应商编码"/>
	<%--<input type="hidden" name="fields" value="entity.nbr" desc="单号"/>
	<input type="hidden" name="fields" value="entity.line" desc="行"/>
	<input type="hidden" name="fields" value="entity.relDate" desc="发放日期"/>
	--%><input type="hidden" name="fields" value="entity.qty" desc="到期日期"/>
	<input type="hidden" name="fields" value="entity.qty" desc="数量"/>
	<%--<input type="hidden" name="fields" value="entity.time" desc="时间"/>
	<input type="hidden" name="fields" value="entity.type" desc="类型"/>
	<input type="hidden" name="fields" value="entity.detail" desc="明细"/>
	--%><input type="hidden" name="fields" value="entity.version" desc="版本号"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<hr />
<page:form action="/listPurchaseItemWrong.do" method="post">
	<table class="data" >
		<thead>
			<tr bgcolor="#9999ff">
				<%--<th>数据集</th>
				--%><th><page:order order="partId" style="text-decoration:none">
					<bean:message key="njitnpoplan.partid" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>中文描述</th>
				<th>单位</th>
				<th><bean:message key="njitnpoplan.part.vend" /></th>
				<%--<th>单号</th>
				<th>行</th>
				<th>发放日期</th>
				--%><th>到期日期</th>
				<th>数量</th><%--
				<th>时间</th>
				<th>类型</th>
				<th>明细</th>
				--%><th>版本号</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="messagePage.jsp" />
				<%-- <jsp:include page="row.jsp" /> --%>
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

