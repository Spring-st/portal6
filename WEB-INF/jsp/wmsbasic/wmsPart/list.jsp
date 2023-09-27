<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
<!--
	function addPart() {
		v = window.showModalDialog(
			'showDialog.do?title=WmsPart.new.title&newWmsPart.do' , 
			null, 'dialogWidth:700px;dialogHeight:500px;status:no;help:no;scroll:no');
		if (v) {
			//var table = document.all('datatable');
			//appendRow(table, v);
		   // applyRowStyle(table);
		    window.location.href="listWmsPart.do";
		};
	}
	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=WmsPart.edit.title&editWmsPart.do?id=' + id , 
			null, 'dialogWidth:700px;dialogHeight:500px;status:no;help:no;scroll:no');
		if (v) {
			//updateRow(document.all('r' + id), v);
			window.location.href="listWmsPart.do";
		};
	}
	 function printPartBac(){
		 var el = document.getElementsByName('ids');
		  var len = el.length;   
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str=str+el[i].value+",";
					count++;
				}
  			}   
  		}
  		        if(count==0){
  			    	<%--alert('<bean:message key="Please select a print batches"/>');--%>
  			    	return false;
  			    }
  		        
  		        window.location.href="wmsPartPrint.do?ids="+str.toString();
	 }
//-->
</script>
<html:form action="/listWmsPart.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="srt.productSpecifications" desc="车型"/>
	<input type="hidden" name="fields" value="srt.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="srt.describe1" desc="描述1"/>
	<input type="hidden" name="fields" value="srt.describe2" desc="描述2"/>
	<input type="hidden" name="fields" value="srt.drwgLoc" desc="类型"/>
	<input type="hidden" name="fields" value="srt.vend" desc="采购供应商编码"/>
	<input type="hidden" name="fields" value="srt.productClass" desc="产品类"/>
	<input type="hidden" name="fields" value="srt.ord_mult" desc="标准包装量"/>
	<input type="hidden" name="fields" value="srt.highQty" desc="高储库存 "/>
	<input type="hidden" name="fields" value="srt.lowQty" desc="低储库存 "/>
	<input type="hidden" name="fields" value="srt.securityQty" desc="安全库存 "/>
	<input type="hidden" name="fields" value="srt.unit" desc="单位"/>
	<input type="hidden" name="fields" value="srt.freeze_status" desc="是否冻结"/>
	<input type="hidden" name="fields" value="srt.enabled" desc="状态"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listWmsPart.do" method="post">
	<table class="data" style="table-layout: fixed;">
		<thead>
			<tr bgcolor="#9999ff">
			<th width="30px"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
					<th width="140px">车型</th>
					<th width="140px" ><page:order order="id" style="text-decoration:none">
							物料编号
							<page:desc>
								<img src="images/down.gif" border="0" />
							</page:desc>
							<page:asc>
								<img src="images/up.gif" border="0" />
							</page:asc>
						</page:order>
					</th>
					<th> <bean:message key="wmsPart.describe1" /> </th>
					<th> <bean:message key="wmsPart.describe2" /> </th>
					<th>类型</th>
					<th> 采购供应商编码 </th>
					<th >产品类 </th>
					<th >标准包装量</th>
					<th >高储库存 </th>
					<th >低储库存 </th>
					<th>安全库存</th>
					<th> 单位 </th>
					<th>是否冻结</th>
					<th>状态</th>
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
