<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
<!--
	function itemview(srid) {
		var site="${x_wmsStocking.site.id}"
		v = window.showModalDialog(
			'showDialog.do?title=Stockingreport.view.title&listWmsStockingItem.do?site='+site+"&storeroomId="+srid+"&stocking=${x_wmsStocking.id }", 
			null, 'dialogWidth:700px;dialogHeight:680px;status:no;help:no;scroll:no');
	}
    function confirmEnd(){
    	if(confirm('<bean:message key="wmsStocking.confirmEnd" />')){
			var form = document.wmsStockingForm;
			    form.action = "updateWmsStocking.do";
			    form.submit();
		}
    }
//-->
</script>
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">
				<bean:message key="travelApplication.site" />&nbsp;
			</td>
			<td>${x_stocking.site.name}</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="wmsStocking.createDate" />:</td>
			<td>${x_stocking.createDate}</td>
		</tr>
		
		<tr>
			<td class="bluetext"><bean:message key="wmsStocking.createUser" />:</td>
			<td>${x_stocking.createUser.name}</td>
		</tr>
</table>	
<hr />	
		<table class="data">
				<thead>
					<tr bgcolor="#9999ff">
						<th width="10%">库位</th>
						<th>库存量</th>
						<th>盘点数量</th>
						<th>差异</th>
						<th>类型</th>
					</tr>
				</thead>
				<tbody id="datatable">	
					<c:forEach items="${x_stockingItems}" var="item" varStatus="status">
					   <tr align="center">
						<td>${item.location.code}</td>
						<td>${item.planCount}</td>
						<td>${item.realityCount}</td>
						<td>${item.planCount-item.realityCount}</td>
						<td>
                         <c:if test="${item.realityCount-item.planCount>0}">
                         <span style="color: red;">盘亏</span>
                         </c:if>
                         <c:if test="${item.realityCount-item.planCount==0||item.realityCount-item.planCount<0}">
                          <span class="bluetext">盘盈</span>
                         </c:if>  
                        </td>
					</tr>
					</c:forEach> 
				</tbody>	
				</table>
<div align="center">
<input type="button" value="<bean:message key="all.back" />" onclick="history.go(-1);"/> 
</div>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>