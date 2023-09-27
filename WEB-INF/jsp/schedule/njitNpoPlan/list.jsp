<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">

</script>
<html:form action="/listPurchaseItemWrong.do">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="entity.partId" desc="物料编码"/>
	<input type="hidden" name="fields" value="entity.name" desc="中文描述"/>
	<input type="hidden" name="fields" value="entity.current_qty" desc="当前库存"/>
	<input type="hidden" name="fields" value="entity.e" desc="产品类型"/>
	<input type="hidden" name="fields" value="entity.g" desc="车型"/>
	<input type="hidden" name="fields" value="entity.h" desc="供应商"/>
	<input type="hidden" name="fields" value="entity.b" desc="库存低储"/>
	<input type="hidden" name="fields" value="entity.d" desc="库存高储"/>
	<input type="hidden" name="fields" value="entity.c" desc="安全库存"/>
	<input type="hidden" name="fields" value="entity.f" desc="类型"/>
	
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=30% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listPurchaseItemWrong.do" method="post">
	<table class="data" width="4500px;" word-break="keep-all;" >
		<thead>
			<tr bgcolor="#9999ff">
				<th>序号</th>
				<th width="100px;">
					<page:order order="part_Id" style="text-decoration:none">
					物料编码
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order>
				</th>
				<th width="100px;">中文描述</th>
				<th width="100px;">当前库存</th>
				<th width="100px;">库存低储</th>
				<th width="100px;">安全库存</th>
				<th width="100px;">库存高储</th>
				<th width="100px;">产品类型</th>
				<th width="100px;">类型</th>
				<th width="100px;">车型</th>
				<th width="100px;">供应商</th>
				<c:forEach var="dateStr" items="${dateList}">
					<th>${dateStr} </th>
				</c:forEach>
			</tr>
			
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST"  indexId="indexid">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<bean:define id="index" toScope="request" name="indexid" />
				<jsp:include page="messagePage.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

