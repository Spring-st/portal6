<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">

function view(id) {
		var url = "viewPortalShipOrderTwoReportSite.do?id=" + id;
		window.location.href = url;
	}


function printExcel(){
	window.location.href = "listShippingOrder.do?isExcel=true";
}

function add(){
	var url = "newPortalShipOrderTwo.do";
	window.location.href = url;
}
</script>
<html:form action="listShippingOrderReportSite" >
    <input type="hidden" name="fields" value="" desc="请选择"/>
    <input type="hidden" name="fields" value="po.code" desc="发货单号"/>
	<input type="hidden" name="fields" value="po.createDate" desc="发货时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
	
<!--  
	<table width=100% border=0 cellpadding=4 cellspacing=0>
			<tr>
											<td class="bluetext">
												<bean:message key="start.time"/>:
											</td>
											<td>
												<html:text property="createDate1" size="15" /> 
												<a onclick="event.cancelBubble=true;"
												href="javascript:showCalendar('dimg1',false,'createDate1',null,null,'portalShipOrderMainQueryForm')"><img
												align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
											</td>
											<td class="bluetext">
												<bean:message key="end.time"/>:
											</td> 
											<td>
												<html:text property="createDate2" size="15" /> 
												<a onclick="event.cancelBubble=true;"
												href="javascript:showCalendar('dimg2',false,'createDate2',null,null,'portalShipOrderMainQueryForm')"><img
												align="absmiddle" border="0" id="dimg2" src="images/datebtn.gif" /></a>
											</td>
										</tr>
										<tr>
											<td class="bluetext">
												<bean:message key="SO.No"/>:
											</td>
											<td>
												<html:text property="code" size="15" />
											</td>
											<td class="bluetext">
													<bean:message key="portalShipOrder.status" />
											</td>
											<td>
													<html:select property="status">
														<html:option value="">
															<bean:message key="all.all" />
														</html:option>
														<html:options collection="x_statusList" property="enumCode"
															labelProperty="${x_lang}ShortDescription" />
													</html:select>
											</td>
										</tr>
										<tr>
											<td class="bluetext"><bean:message key="storageLocation.poNumber" /></td>
											<td><html:text property="pocode" size="15" /></td>
											<td class="bluetext"><bean:message key="partCode.id" /></td>
											<td><html:text property="part_code" size="15" /></td>
										</tr>
										<tr>
											<td colspan="2"></td>
											<td align="center">
												<input type="submit" value="<bean:message key="all.query"/>" />
											</td>
											<td align="left">
												<%--<input type="button" onclick="printExcel();" value="<bean:message key="page.export.excel"/>" />
											--%>
												<input type="button" onclick="add()" value="<bean:message key="all.new"/>" />
											</td>
										</tr>
										<tr>
											<td colspan="4"><hr/></td>
										</tr>
	</table>
-->
</html:form>
<page:form action="listShippingOrderReportSite.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
														
				<th width="25%">
					<bean:message key="SO.No"/>
				</th>
														
				<th width="25%">
					发货时间
				</th>
				<th width="20%">
					<bean:message key="Remark"/> 
				</th>
				<th width="30%">
					<bean:message key="portalShipOrder.status"/> 
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
						<td width="25%" align="center">
								<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
						</td>
						<td width="25%" align="center">
							<c:if test="${X_OBJECT.createDate!= null }">
								<bean:write name="X_OBJECT" property="createDate" format="yyyy/MM/dd" />
							</c:if>
						</td>
						<td width="20%" align="center">
							${X_OBJECT.remark}
						</td>
						<td width="30%" align="center">
								<c:if test="${X_OBJECT.status!=null}">
									<span style="color:${X_OBJECT.status.color}"> 
									<bean:write name="X_OBJECT" property="status.${x_lang}ShortDescription" /> </span>
								</c:if>
						</td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
<jsp:include page="../pageTail.jsp"/>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



