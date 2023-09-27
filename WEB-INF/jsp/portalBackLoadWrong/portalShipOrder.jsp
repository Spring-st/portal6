<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">

function view(id) {
	var url = "viewPortalShipOrderTwoWrong.do?id=" + id+"&GrantedSite=1";
	window.location.href = url;
}

function printExcel(){
	window.location.href = "listShippingOrder.do?isExcel=true";
}

function addCondiment(){
	var url = "newPortalShipOrderTwoWrong.do";
	window.location.href = url;
}
//根据发货单Id去删除数据
function deleteDelivery(id){
	var url="deleteDeliveryNPo.do?id="+id;
	window.location.href=url;
}
</script>
<html:form action="listShippingOrderWrong" >
    <input type="hidden" name="fields" value="" desc="请选择"/>
    <input type="hidden" name="fields" value="po.code" desc="发货单号"/>
    <input type="hidden" name="fields" value="po.supplier.name" desc="供应商名称"/>
    <input type="hidden" name="fields" value="po.supplier.code" desc="供应商编码"/>
	<input type="hidden" name="fields" value="po.createDate" desc="创建时间"/>
	<input type="hidden" name="fields" value="po.arrivalDate" desc="发货时间"/>
	
	<input type="hidden" name="fields" value="po.isSync" desc="是否同步（1已同步）"/>
	<input type="hidden" name="fields" value="po.isPrint" desc="是否打印（0YES 1NO）"/>
	<input type="hidden" name="fields" value="po.enabled" desc="状态(0打开 1关闭)"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="listShippingOrderWrong.do" method="post">
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
					是否打印
				</th>
				<th>
					是否同步
				</th>
				<th>
					状态
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr align="center">
					<td>
						<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
					</td>
					<td >
							${X_OBJECT.supplier.name}
					</td>
					<td>
							${X_OBJECT.supplier.code}
					</td>
					<td>${X_OBJECT.createDate}</td>
					<td>${X_OBJECT.arrivalDate}</td>
					<td width="10%" align="center">
						<c:if test="${X_OBJECT.isPrint!=null}">
							<span style="color:${X_OBJECT.isPrint.color}"> 
							<bean:write name="X_OBJECT" property="isPrint.${x_lang}ShortDescription" /> </span>
						</c:if>
					</td>
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
					<td width="10%" align="center">
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