<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
	function edit(id){
		var url="editWmsPlanToGoOut.do?id="+id;
		window.location.href = url;
	}
	
	function addWmsPlanToGoOut(){
		var url="newWmsPlanToGoOut.do";
		var title="newWmsPlanToGoOut.info";
		var v=dialogAction(url,title,500,500);
		if (v) {
			//var table = document.all('datatable');
			//appendRow(table, v);
		    //applyRowStyle(table);
		    window.location=window.location;
		};
	}
	
	function inputnumber(){
		var url="printNext.do";
		var title="ProductionProwarehouse.print";
		var v=dialogAction(url,title,600,300);
		if(v!=undefined){
			window.location="createProductionProwarehouse.do?asn="+v;
		}
	}
	 
	function cancelDocuments(id){
		var r = confirm("确定删除该条计划外出库单吗？");
		if(r){
			$.post("deleteWmsUnplannedWarehousing.do",
			   {"id":id},
			   function(date){
					alert("恭喜您,删除成功！");
					window.location.href = "listWmsPlanToGoOut";
			 })
		}
	}
</script>
<html:form action="/listWmsPlanToGoOut.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pwom.code" desc="非计划出库代码"/>
	<input type="hidden" name="fields" value="pwom.applicant.name" desc="操作用户"/>
	<input type="hidden" name="fields" value="pwom.date" desc="申请日期"/>
	<input type="hidden" name="fields" value="pwom.qty" desc="计划数量"/>
	<input type="hidden" name="fields" value="pwom.actual_qty" desc="已出数量"/>
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
<page:form action="/listWmsPlanToGoOut.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th><page:order order="id" style="text-decoration:none">
					非计划出库代码
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
				<th>已出数量</th>
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
