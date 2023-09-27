<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js">
</script>
<script type="text/javascript" src="includes/basicJS.js">
</script>
<script type="text/javascript" src="includes/jquery-1.3.2.js">
</script>
<script type="text/javascript">
function Producttransfer() {
	//获取所有选中的成品id
	var el = document.getElementsByName('ids');
	var ell = document.getElementsByName('locationCode');
	var len = el.length;
	var str = "";
	var str1 = "";
	var count = 0;
	var count1 = 0;
	var start = 0;
	var startNo = "";
	for ( var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].checked == true)) {
			if (el[i].id != "checkAll") {
				str = str + el[i].value + ",";
				count++;
				str1 = ell[i].value;
				if(start==0){
					startNo=ell[i].value;
					start=1;
				}
				if(startNo!=str1){
					count1++;
				}
				
			}
		}
	}

	if (count == 0) {
		alert('请勾选要移库的赛赫条码!');
		return false;
	}
	if (count1 != 0) {
		alert('请勾选要移库的相同库位号!');
		return false;
	}
	var v = window
			.showModalDialog(
					'showDialog.do?title=product.instorage.new&TransferNew.do?array=' + str,
					null,
					'dialogWidth:600 px;dialogHeight:400 px;status:no;help:no;scroll:no');
	if (v) {
		window.location.href = "TransferList.do";
	}
	;

}
</script>
<html:form action="/TransferList.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="pg.hncCode" desc="海纳川成品条码" />
	<input type="hidden" name="fields" value="pg.shCode" desc="赛赫条码" />
	<input type="hidden" name="fields" value="pg.partTireCode"
		desc="零件号(轮胎)" />
	<input type="hidden" name="fields" value="pg.partHubCode"
		desc="零件号(轮毂)" />
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="/TransferList.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff" align="center">
				<th width="2%">
					<input type="checkbox" value="0" id="checkAll"
						onclick="selectAll();" />
				</th>
				<th>
					海纳川成品条码
				</th>
				<th>
					赛赫条码
				</th>
				<th>
					库位
				</th>
				<th>
					器具编码
				</th>
				<th>
					零件号(轮毂)
				</th>
				<th>
					轮毂描述
				</th>
				<th>
					零件号(轮胎)
				</th>
				<th>
					轮胎描述
				</th>
				<th>
					零件号(气门嘴)
				</th>
				<th>
					气门嘴描述
				</th>
				<th>
					数量
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
	<hr>
</page:form>

<script type="text/javascript">
applyRowStyle(document.all('datatable'));
</script>
