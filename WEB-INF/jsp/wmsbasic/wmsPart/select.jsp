<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/selection.js"></script>
<script type="text/javascript">
<!--
	function select(id, dpiNo,name,unit,supplierPartCode,describe1,describe2) {
		var result = [];
		result['id'] = id;
		result['name'] = name;
		result['unit'] = unit;
		result['describe1'] = describe1;
		result['describe2'] = describe2;
		result['supplierPartCode'] = supplierPartCode;
		window.parent.returnValue = result;
		window.parent.close();
	}
//-->
</script>
<html:form action="/selectWmsPart.do">
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="srt.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="srt.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="srt.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="srt.name" desc="物料名称"/>
	<input type="hidden" name="fields" value="srt.describe1" desc="描述一"/>
	
	<input type="hidden" name="type" value="${x_type}"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectWmsPart" method="post">			
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
        <th><page:order order="id" style="text-decoration:none">
					物料编号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>
				物料名称
					</th>
					<th>
					描述一
					</th>
					<th>
					描述二
					</th>
					<th>
					<bean:message key="wmsPart.unit" />
					</th>
				<th>
				<bean:message key="wmsPart.status"/></th>
				<th>
				</th>
      </tr>
    </thead>
    <tbody id="datatable">
      <logic:iterate id="X_OBJECT" name="X_RESULTLIST">
        <bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
        <jsp:include page="selectRow.jsp"/>
      </logic:iterate>
    </tbody>
  </table>
  <script type="text/javascript">
    applyRowStyle(document.all('datatable'));
  </script>
</page:form>		      
