<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">

	function select(id,toolCode) {
	    var list = new Array();
		var result = [];
		result['id'] = id;
		result['toolCode'] = toolCode;
			
		window.parent.returnValue = result;
		window.parent.close();
	}
	
	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=finishedsaiherelation.edit.title&editfinishedToolPutnumber.do?id=' + id , 
			null, 'dialogWidth:500px;dialogHeight:400px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
		};
	}

</script>
<html:form action="/selectToolCode1.do" >
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="finishedToolPutnumber.toolCode" desc="器具编码"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/selectToolCode1.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%"><page:order order="id" style="text-decoration:none">
					器具编码
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>

				<th>可盛放成品数量</th>
				<th>状态</th>
				<th> </th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="selectToolCode1Row.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

