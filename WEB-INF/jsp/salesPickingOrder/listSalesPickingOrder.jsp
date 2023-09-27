<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">

function view(id) {
	var url = "viewalesPickingOrderBatch.do?id=" + id;
	window.location.href = url;
}

function printExcel(){
	window.location.href = "listShippingOrder.do?isExcel=true";
}
</script>
<html:form action="listSalesPickingOrder" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesPreshiporder.code" desc="检料单号"/>
	<input type="hidden" name="fields" value="salesPreshiporder.createDate" desc="创建时间"/>
	<input type="hidden" name="fields" value="salesPreshiporder.customerName" desc="客户"/>
	<input type="hidden" name="fields" value="salesPreshiporder.customerCode" desc="客户编号"/>
	<input type="hidden" name="fields" value="salesPreshiporder.arrivaldate" desc="交货日期"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<input type="hidden" id="statusCheck" name="statusCheck"/>
	
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="listSalesPickingOrder.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
														
				<th width="18%">
					检料单号
				</th>
				<th width="18%">
					创建日期
				</th>
				<th width="15%">
					客户
				</th>
				<th width="15%">
					客户编码
				</th>										
				<th width="18%">
					交货日期
				</th>
				<th width="10%">
					状态
				</th>
				<th width="10%">
					是否打印
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
					<td width="18%" align="center">
							<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
					</td>
					<td width="18%" align="center">
						<c:if test="${X_OBJECT.createDate!= null }">
							<bean:write name="X_OBJECT" property="createDate" format="yyyy/MM/dd" />
						</c:if>
					</td>
					<td width="15%" align="center">
						${X_OBJECT.customerName}
					</td>
					<td width="15%" align="center">
						${X_OBJECT.customerCode}
					</td>
					<td width="15%" align="center">
						<c:if test="${X_OBJECT.arrivaldate!= null }">
							<bean:write name="X_OBJECT" property="arrivaldate" format="yyyy/MM/dd" />
						</c:if>
					</td>
					<td align="center""> 
			 			<c:if test="${X_OBJECT.status=='1'}">
			 				待检料
			 			</c:if>
			 			<c:if test="${X_OBJECT.status=='2'}">
			 				已发货
			 			</c:if>
			 			
			 		</td>
			 		<td align="center""> 
			 			<c:if test="${X_OBJECT.isPrint=='0'}">
			 				<span style="color: red;">已打印</span>
			 			</c:if>
			 			<c:if test="${X_OBJECT.isPrint=='1'}">
			 				未打印
			 			</c:if>
			 			
			 		</td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



