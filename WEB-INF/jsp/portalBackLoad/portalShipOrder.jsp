<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">

function view(id) {
	var url = "viewPortalShipOrderTwo.do?id=" + id;
	window.location.href = url;
}

function printExcel(){
	window.location.href = "listShippingOrder.do?isExcel=true";
}

function addCondiment(){
	var url = "newPortalShipOrderTwo.do";
	window.location.href = url;
}

//根据发货单Id去删除数据
function deleteDelivery(id){
	var url="deleteDeliveryPo.do?id="+id;
	window.location.href=url;
}
</script>
<html:form action="listShippingOrder" >
    <input type="hidden" name="fields" value="" desc="请选择"/>
    <input type="hidden" name="fields" value="po.code" desc="发货单号"/>
	<input type="hidden" name="fields" value="po.createDate" desc="创建时间"/>
	<input type="hidden" name="fields" value="po.supplier.code" desc="供应商编码"/>
	<input type="hidden" name="fields" value="po.arrivalDate" desc="发货时间"/>
	<input type="hidden" name="fields" value="po.isSync" desc="是否同步（1已同步）"/>
	
	<input type="hidden" name="fields" value="po.enabled" desc="状态(0打开 1关闭)"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
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
<page:form action="listShippingOrder.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>
					<page:order order="id" style="text-decoration:none">
						<bean:message key="SO.No1"/>
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<bean:message key="supplier1"/>
				</th>
				<th>
					<bean:message key="supplier.code1"/>
				</th>
				<th>
					<page:order order="createDate" style="text-decoration:none">
						创建时间
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="arrivalDate" style="text-decoration:none">
						发货时间
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="arrivalDate" style="text-decoration:none">
						是否同步
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<bean:message key="portalShipOrder.status"/> 
				</th>
				<th nowrap="nowrap">
				<page:order order="arrivalDate" style="text-decoration:none">
						操作
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
					
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
						<td width="20%" align="center">
								<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
						</td>
						<td width="25%" align="center">
								${X_OBJECT.supplier.name}
						</td>
						<td width="10%" align="center">
								${X_OBJECT.supplier.code}
						</td>
						<td width="10%" align="center">
							<c:if test="${X_OBJECT.createDate!= null }">
								<bean:write name="X_OBJECT" property="createDate" format="yyyy/MM/dd" />
							</c:if>
						</td>
						<td width="10%" align="center">
							<c:if test="${X_OBJECT.arrivalDate!= null }">
								<bean:write name="X_OBJECT" property="arrivalDate" format="yyyy/MM/dd" />
							</c:if>
						</td>
						<%--<td width="10%" align="center">
								<c:if test="${X_OBJECT.status!=null}">
									<span style="color:${X_OBJECT.status.color}"> 
									<bean:write name="X_OBJECT" property="status.${x_lang}ShortDescription" /> </span>
								</c:if>
						</td>
						--%>
						<td width="10%" align="center">
						 <c:if test="${X_OBJECT.isSync!=1}">
						 	未同步
						</c:if>
						 <c:if test="${X_OBJECT.isSync==1}">
						 	已同步
						</c:if>
						</td>
						<td width="10%" align="center">
							 <c:if test="${X_OBJECT.enabled.enumCode==0}">
							 	打开
							</c:if>
							 <c:if test="${X_OBJECT.enabled.enumCode==1}">
							 	关闭
							</c:if>
						</td>
						<td width="10%" >
							<c:if test="${X_OBJECT.isSync!=1}">
							<input type="button" onclick="deleteDelivery('${X_OBJECT.id}')" value="删除">
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






