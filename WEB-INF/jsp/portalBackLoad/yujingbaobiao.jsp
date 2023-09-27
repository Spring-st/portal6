<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>


<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/selection.js"></script>
<script type="text/javascript">
</script>
<html:form action="listShippingOrderReporty">
<html:hidden property="descend" />
<input type="hidden" id="ExportType" name="exportType"
		style="display: none" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../yujing.jsp"/></td>
	  	</tr>
	</table>
</html:form>

<page:form action="listShippingOrderReporty.do" method="post">	
<input type="hidden" name="portalShipJitPartId" id="portalShipJitPartId" value="${portalShipJitPartId}"/>
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
      	
        <th><page:order order="partId" style="text-decoration:none">
					物料
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>描述</th>
				<th>单位</th>
				<th>属性</th>
				<th>售后件/量产件</th>
				<th><page:order order="partCarType" style="text-decoration:none">
					车型
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>供应商</th>
				<th>当前库存</th>
				<th>
				<page:order order="highQty" style="text-decoration:none">
					高储库存
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order>
				</th>
				
				<th>
				<page:order order="lowQty" style="text-decoration:none">
					低储库存
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order>
				</th>
				<th>
				<page:order order="securityQty" style="text-decoration:none">
					安全库存
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order>
				
      </tr>
    </thead>
    <tbody id="datatable">
    
      <logic:iterate id="X_OBJECT" name="X_RESULTLIST">
        <bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
        	
        	<tr align="center">
        		
        		<td >${X_OBJECT.part.id}</td>
        		<td >${X_OBJECT.part.name}</td>
        		<td >${X_OBJECT.part.unit}</td>
        		<td >${X_OBJECT.part.productClass}</td>
        		<td >${X_OBJECT.part.drwgLoc}</td>
        		<td >${X_OBJECT.part.productSpecifications}</td>
        		<td >${X_OBJECT.part.vend}</td>
        		<td ><fmt:formatNumber type="number" value="${X_OBJECT.currentQty}" maxFractionDigits="0"/></td>
        		<td ><fmt:formatNumber>${X_OBJECT.part.highQty}</fmt:formatNumber></td>
        		<td ><fmt:formatNumber>${X_OBJECT.part.lowQty}</fmt:formatNumber></td>
        		<td ><fmt:formatNumber>${X_OBJECT.part.securityQty}</fmt:formatNumber></td>
        		
        		
        	</tr>
        	
      </logic:iterate>
 
    
    
    </tbody>
  </table>
  <script type="text/javascript">
    applyRowStyle(document.all('datatable'));
  </script>
</page:form>		  