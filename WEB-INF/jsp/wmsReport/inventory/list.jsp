<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
function updateByBoxCout(){
				$.ajax({  
			        type:"post", 
			        async:false,
			        url:"updateByBoxCout.do",  
			        data:{},
			        dataType:"json",  
			        success: function (data) {
			        	//alert(data.msg);
			        	window.location.reload();
			        }  
			  });
	}
//-->
</script>
<html:form action="/inventoryReport.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="ind.location.code" desc="库位编码"/>
	<input type="hidden" name="fields" value="ind.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="ind.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="ind.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="ind.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="ind.part.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="ind.number" desc="库位库存"/>
	<input type="hidden" name="fields" value="ind.part_qty" desc="物料库存"/>
	<input type="hidden" name="fields" value="ind.part.unit" desc="单位"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	  	<%--<tr>
	  		<td colspan="4"><input type="button" value="更新库存" onclick="updateByBoxCout();"/></td>
	  	</tr>
	--%></table>
</html:form>
<page:form action="/inventoryReport.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%"><!--<page:order order="id" style="text-decoration:none">-->
					库位编码
					<!--<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order>--></th>
				    <th>物料号</th>
				    <th>DPI</th>
				    <th>原厂编号</th>
				    <th>描述一</th>
				    <th>描述二</th>
				    <th>库位库存</th>
				    <th>物料库存</th>
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

