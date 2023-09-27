<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>

<html:form action="/listBasicPartPriceAction.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="partPrice.customer" desc="客户编号"/>
	<input type="hidden" name="fields" value="partPrice.sotaxc" desc="税率"/>
	<input type="hidden" name="fields" value="partPrice.partId" desc="物料编号"/>
	<input type="hidden" name="fields" value="partPrice.solist" desc="折扣表"/>
	<input type="hidden" name="fields" value="partPrice.pcDesc" desc="描述"/>
	<input type="hidden" name="fields" value="partPrice.curr" desc="币种"/>
	<input type="hidden" name="fields" value="partPrice.pcUm" desc="单位"/>
	<input type="hidden" name="fields" value="partPrice.startDate" desc="开始日期"/>
	<input type="hidden" name="fields" value="partPrice.expireDate" desc="到期日期"/>
	<input type="hidden" name="fields" value="partPrice.amtType" desc="金额类型"/>
	<input type="hidden" name="fields" value="partPrice.amt" desc="价格"/>
	<input type="hidden" name="fields" value="partPrice.rmks" desc="备注"/>
	
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	  </table>
</html:form>
<page:form action="/listBasicPartPriceAction.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>
					<page:order order="id" style="text-decoration:none">
						<bean:message key="basicpartprice.customer" />
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<bean:message key="basicpartprice.sotaxc" />
				</th>
			   <th>
			    	<bean:message key="basicpartprice.partId" />
			    </th>
				<th>
			    	<bean:message key="basicpartprice.solist" />
			    </th>
			    <th>
			    	<bean:message key="basicpartprice.pcDesc" />
			    </th>
			    <th>
			    	<bean:message key="basicpartprice.curr" />
			    </th>
			    <th>
			    	<bean:message key="basicpartprice.pcUm" />
			    </th>
			    <th>
			    	<bean:message key="basicpartprice.startDate" />
			    </th>
			    <th>
			    	<bean:message key="basicpartprice.expireDate" />
			    </th>
			    <th>
			    	<bean:message key="basicpartprice.amtType" />
			    </th>
			    <th>
			    	<bean:message key="basicpartprice.amt" />
			    </th>
			    <th>
			    	<bean:message key="basicpartprice.rmks" />
			    </th>
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

