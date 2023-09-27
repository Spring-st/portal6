<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function goTo(){
		var url = "listCustomerPlan.do";
		window.location.href = url;
	}
//-->
</script>
	<table width=100% border=0 cellpadding=4 cellspacing=0>

		<tr>
			<td width="15%" class="bluetext">客户编号：</td>
			<td  width="20%" >${X_OBJECT.customer.code}</td>
			
			<td width="15%" class="bluetext">客户名称:</td>
			<td>
				${X_OBJECT.customer.name1}
			</td>
		</tr>
		<tr>
			<td width="15%" class="bluetext"><bean:message key="customer_plan.shipmentdate" />:</td>
			<td  width="20%">
				<fmt:formatDate value="${X_OBJECT.shipmentDate}" pattern="yyyy-MM-dd"/> 
			</td>
			<td class="bluetext">送货地址</td>
			<td >${X_OBJECT.customerAddress}</td>
		</tr>
		<tr>
			<td class="bluetext"  width="15%">描述：</td>
			<td  width="20%">${X_OBJECT.describe}</td>
			<td width="15%"></td>
			<td></td>
		</tr>
		</table>
		<hr/>
		<table width="100%" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td colspan="4" align="left"">
				<%--<input type="button" value="选择物料" onclick="selectWmsPart()"/>
			--%>
			<h3>物料明细</h3>
			</td>
		</tr>
	</table>
		<table class="data" width="100%" >
					<thead>
						<tr bgcolor="#9999ff">
						  <th>物料编号</th>
							<th>DPI</th>
							<th>原厂编号</th>
							<th>物料名称</th>
							<th>物料描述一</th>
							<th>物料描述二</th>
							<th>单位</th>
							<th>单价</th>
							<th>税率</th>
							<th>需求数量</th>
						</tr>
					</thead>
				<tbody id="datatable2">
					<tr align="center">
						<td>${X_OBJECT.wmspart.id}</td>
						<td>${X_OBJECT.wmspart.dpiNo}</td>
						<td>${X_OBJECT.wmspart.oldCode}</td>
						<td>${X_OBJECT.wmspart.name}</td>
						<td>${X_OBJECT.wmspart.describe1}</td>
						<td>${X_OBJECT.wmspart.describe2}</td>
						<td>${X_OBJECT.wmspart.unit}</td>
						<td><fmt:formatNumber value="${X_OBJECT.unitPrice}" type="number" maxFractionDigits="2" minFractionDigits="2"/></td>
						<td>${X_OBJECT.sotaxc}</td>
						<td>${X_OBJECT.qty}</td>
					</tr>
			    </tbody>
		</table>
		<table  width="100%">
		<%--<tr>
			<td width="100%" colspan="4"><hr/></td>
		</tr>
		--%><tr>
			<td></td>
			<td colspan="3" align="right">
			<input type="button" value="返回" onclick="goTo()"/>
			</td>
		</tr>
	</table>
<script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
</script>
