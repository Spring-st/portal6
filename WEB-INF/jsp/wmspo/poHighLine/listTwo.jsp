<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
			 
</script>
<html:form action="/poHighLineTwoList.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pb.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="pb.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="pb.part.unit" desc="单位"/>
	<input type="hidden" name="fields" value="pb.po_date" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/poHighLineTwoList.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
			<th>条码编号</th>
			<th>轮胎编号</th>	
			<th>描述一</th>
			<th>描述二</th>
			<th>库位</th>
			<th>数量</th>
			<th>单位</th>
			<th>排序</th>
			<th>上线时间</th>
			<th>操作用户</th>
		</tr>
	</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<tr id="r${X_OBJECT.id}" align="center">
				<td>${X_OBJECT.box.lot.id}</td>
				<td>${X_OBJECT.child_part.id}</td>
				<td>${X_OBJECT.child_part.describe1}</td>
				<td>${X_OBJECT.child_part.describe2}</td>
				<td>${X_OBJECT.box.location.code}</td>
				<td>${X_OBJECT.box.number}</td>
				<td>${X_OBJECT.child_part.unit}</td>
				<td>${X_OBJECT.serial_number}</td>
				<td>${X_OBJECT.date}</td>
				<td>${X_OBJECT.userId.name}</td>
			</tr>
			</logic:iterate>
			
		</tbody>
		</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
