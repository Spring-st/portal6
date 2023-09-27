<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
	function view(id){
		var url="editWmsPlantGoOutItem.do?id="+id;
		window.location.href = url;
	}
	
	function addUnplannedWarehousing(){
		var url="newWmsUnplannedWarehousing.do";
		var title="newWmsUnplannedWarehousing.info";
		var v=dialogAction(url,title,500,500);
		if (v) {
			//var table = document.all('datatable');
			//appendRow(table, v);
		    //applyRowStyle(table);
		    window.location=window.location;
		};
	}
	
	function edit(value){
		var url="editWmsUnplannedWarehousing.do?id="+value;
		var title="editWmsPlanToGoOutItem.info";
		window.location.href = url;
	}
	
	function view(value){
		var url="viewWmsUnplannedWarehousing.do?id="+value;
		var title="editWmsPlanToGoOutItem.info";
		window.location.href = url;
	}
	
	function cancelDocuments(id){//取消单据
		$.post("deleteWmsPlanToGoOut.do",
			   {"id":id},
			   function(date){
				   location=location;
		 })
	}
</script>
<html:form action="/listWmsUnplannedWarehousing.do" >
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pwom.code" desc="非计划入库代码"/>
	<input type="hidden" name="fields" value="pwom.applicant.name" desc="操作用户"/>
	<input type="hidden" name="fields" value="pwom.date" desc="申请日期"/>
	<input type="hidden" name="fields" value="pwom.qty" desc="计划数量"/>
	<input type="hidden" name="fields" value="pwom.actual_qty" desc="已入数量"/>
	<input type="hidden" name="fields" value="pwom.reason_code.instructions" desc="原因代码"/>
	<input type="hidden" name="fields" value="pwom.reason_code.expenses_course" desc="费用项目"/>
	<input type="hidden" name="fields" value="pwom.reason_code.department_cost" desc="费用部门"/>
	<input type="hidden" name="fields" value="pwom.remark" desc="备注"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listWmsUnplannedWarehousing.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th><page:order order="id" style="text-decoration:none">
					非计划入库代码
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>操作用户</th>
				<th>申请日期</th>
				<th>计划数量</th>
				<th>已入数量</th>
				<th>原因代码</th>
				<th>费用科目</th>
				<th>费用部门</th>
				<th>状态</th>
				<th>备注</th>
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
