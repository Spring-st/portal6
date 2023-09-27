<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<link type="text/css" href="includes/pro_drop_1.css" />
<script language="javascript" src="includes/stuHover.js">
</script>
<style type="text/css">
/* ================================================================ 
This copyright notice must be kept untouched in the stylesheet at 
all times.

The original version of this stylesheet and the associated (x)html
is available at http://www.stunicholls.com/menu/pro_drop_1.html
Copyright (c) 2005-2007 Stu Nicholls. All rights reserved.
This stylesheet and the associated (x)html may be modified in any 
way to fit your requirements.
=================================================================== */
.preload1 {
	background: url(images/blank_over.gif);
}

.preload2 {
	background: url(images/blank_overa.gif);
}

#nav {
	padding: 0;
	margin: 0;
	list-style: none;
	height: 36px;
	background: #fff;
	position: relative;
	z-index: 500;
	font-family: arial, verdana, sans-serif;
}

#nav li.top {
	display: block;
	float: left;
}

#nav li a.top_link {
	display: block;
	float: left;
	height: 36px;
	line-height: 27px;
	color: #ccc;
	text-decoration: none;
	font-size: 11px;
	font-weight: bold;
	padding: 0 0 0 12px;
	cursor: pointer;
	background: url(images/blank.gif);
}

#nav li a.top_link span {
	float: left;
	display: block;
	padding: 0 24px 0 12px;
	height: 36px;
	background: url(images/blank.gif) right top;
}

#nav li a.top_link span.down {
	float: left;
	display: block;
	padding: 0 24px 0 12px;
	height: 36px;
	background: url(images/blanka.gif) no-repeat right top;
}

#nav li a.top_link:hover {
	color: #fff;
	background: url(images/blank_over.gif) no-repeat;
}

#nav li a.top_link:hover span {
	background: url(images/blank_over.gif) no-repeat right top;
}

#nav li a.top_link:hover span.down {
	background: url(images/blank_overa.gif) no-repeat right top;
}

#nav li:hover>a.top_link {
	color: #fff;
	background: url(images/blank_over.gif) no-repeat;
}

#nav li:hover>a.top_link span {
	background: url(images/blank_over.gif) no-repeat right top;
}

#nav li:hover>a.top_link span.down {
	background: url(images/blank_overa.gif) no-repeat right top;
}

/* Default list styling */
#nav li:hover {
	position: relative;
	z-index: 200;
}

/* keep the 'next' level invisible by placing it off screen. */
#nav ul,#nav li:hover ul ul,#nav li:hover ul li:hover ul ul,#nav li:hover ul li:hover ul li:hover ul ul,#nav li:hover ul li:hover ul li:hover ul li:hover ul ul
	{
	position: absolute;
	left: -9999px;
	top: -9999px;
	width: 0;
	height: 0;
	margin: 0;
	padding: 0;
	list-style: none;
}

#nav li:hover ul.sub {
	left: 0;
	top: 31px;
	background: #fff;
	padding: 3px;
	border: 1px solid #3a93d2;
	white-space: nowrap;
	width: 90px;
	height: auto;
	z-index: 300;
}

#nav li:hover ul.sub li {
	display: block;
	height: 20px;
	position: relative;
	float: left;
	width: 90px;
	font-weight: normal;
}

#nav li:hover ul.sub li a {
	display: block;
	font-size: 11px;
	height: 20px;
	width: 90px;
	line-height: 20px;
	text-indent: 5px;
	color: #000;
	text-decoration: none;
}

#nav li ul.sub li a.fly {
	background: #fff url(images/arrow.gif) 80px 7px no-repeat;
}

#nav li:hover ul.sub li a:hover {
	background: #3a93d2;
	color: #fff;
}

#nav li:hover ul.sub li a.fly:hover {
	background: #3a93d2 url(images/arrow_over.gif) 80px 7px no-repeat;
	color: #fff;
}

#nav li:hover ul li:hover>a.fly {
	background: #3a93d2 url(images/arrow_over.gif) 80px 7px no-repeat;
	color: #fff;
}

#nav li:hover ul li:hover ul,#nav li:hover ul li:hover ul li:hover ul,#nav li:hover ul li:hover ul li:hover ul li:hover ul,#nav li:hover ul li:hover ul li:hover ul li:hover ul li:hover ul
	{
	left: 90px;
	top: -4px;
	background: #fff;
	padding: 3px;
	border: 1px solid #3a93d2;
	white-space: nowrap;
	width: 90px;
	z-index: 400;
	height: auto;
}
</style>
<script type="text/javascript">
function selectType(vals) {
	var val = vals.id;
	if (val == 1) {
		addCondiment();
	} else if (val == 2) {
		selectScann();
	} else if (val == 3) {
		partOutbound();
	} else if (val == 4) {
		add();
	} else if (val == 5) {
		produceScannOut();
	} else if (val == 6) {
		printBc();
	} else if (val == 7) {
		printPoBc();
	} else if (val == 8) {
		printProduce();
	} else if (val == 9) {
		printLocation();
	} else if (val == 10) {
		addStoreRoom();
	} else if (val == 11) {
		addPart();
		printPartBac();
	} else if (val == 13) {
		addBadReasons();
	} else if (val == 14) {
		addPartLocation();
	} else if (val == 15) {
		download();
	} else if (val == 16) {
		inserttodate();
	} else if (val == 17) {
		addWmsTool();
	} else if (val == 18) {
		breakUp();
	} else if (val == 19) {
		merge();
	} else if (val == 20) {
		addUnplannedWarehousing();
	} else if (val == 21) {
		addWmsPlanToGoOut();
	} else if (val == 22) {
		selectBox();
	} else if (val == 25) {
		poRejectedMaterialByBox();
	} else if (val == 26) {
		unplannedWarehousingScann();
	} else if (val == 27) {
		poRejectedMaterialByBox();
	} else if (val == 28) {
		printPurchaseOrderCondiment();
	} else if (val == 29) {
		condimentConfirm();
	} else if (val == 30) {
		add();
	} else if (val == 31) {
		withdraw();
	} else if (val == 32) {
		moveLocation();
	} else if (val == 33) {
		editCapacity();
	} else if (val == 34) {
		poRqc();
	} else if (val == 35) {
		poPutInStorage();
	} else if (val == 36) {
		abolition();
	} else if (val == 37) {
		highLine();
	} else if (val == 38) {
		highLine();
	} else if (val == 40) {
		download();
	} else if (val == 41) {
		showImportSupplierPartSamplingRatio();
	} else if (val == 42) {
		clsoePo();
	} else if (val == 43) {
		openPo();
	} else if (val == 44) {
		rqcClose();
	} else if (val == 45) {
		printLot();
	} else if (val == 46) {
		selectScann();
	} else if (val == 47) {
		updateRqc();
	} else if (val == 48) {
		add();
	} else if (val == 49) {
		confirm();
	} else if (val == 50) {
		add();
	} else if (val == 51) {
		add();
	} else if (val == 52) {
		add();
	} else if (val == 53) {
		add();
	} else if (val == 54) {
		add();
	} else if (val == 55) {
		selectBoxFreezeOpen();
	} else if (val == 56) {
		selectBoxFreeze();
	} else if (val == 57) {
		poHighLine();
	} else if (val == 58) {
		add();
	} else if (val == 59) {
		poRejectedMaterialByBox();
	} else if (val == 103) {
		addFinishedSaiheRelation();
	}else if(val == 108){
		addProductOutStorage();
	}else if(val == 109){
		confirmOutStorage();
	}else if(val==110){
		Producttransfer();
	}else if(val== 112){
		poPuInStorageMaterialByBox();
	}else if(val== 113){
		addCustomerPlan();
	}else if(val== 114){
		addBasicCustomer();
	}else if(val== 115){
		addDailyProductPlan();
	}else if(val== 116){
		addSalesOrder();
	}else if(val== 117){
		addWorkOrderBom();
	}else if(val== 118){
		addSalesPreshiporder();
	}else if(val== 121){	
		updateNotRqc();
	}else if(val== 125){
		RecordPrint();
	}else if(val== 126){
		generate();
	} else if (val == 127) {
		poBlpInStorage();
	}else if (val == 129) {
		updateRecordsByType(1);
	}else if (val == 130) {
		updateRecordsByType(2);
	}else if (val == 132) {
		generate();
	}else if (val == 133) {
		addCustomerPlanPreshiporder();
	}else if (val == 134) {
 	purchaseReturnMaterialByBox();
	}else if (val == 141) {
 	salesOrderItemClose();
	}else if (val == 142) {
 	salesOrderItemOpen();
	}else if(val ==136){
		add();
	}else if(val ==147){
		addBasicPartPrice();
	}else if(val ==154){
		selectWmsPartStocking();
	}else if(val ==155){
		updateRecordsByType(1);
	}else if(val ==167){
		add();
	}
	
	//else if(val ==162){
	//	decomposition ();
	//}
	else if(val ==161){
		add();
	}else if(val ==166){
		decompositiontUnfinishPlan ();
	}else if(val ==159){
		addCondiment();
	}else if(val ==169){
		addCondiment();
	}else if(val ==170){
		addCondiment();
	}else if(val ==164){
		combinePlan();
	}else if(val ==1612){
		startTimingTask();
	}else if(val ==1613){
		decomposition ();
	}else if(val ==1642){
		automaticCombinePlanAll();
	}else if(val ==180){
		addSycSleepTime();
	}
}
function selectType1(vals) {
	var val = vals.id;
	if (val == 102) {
		printPartBac();
	}else if(val ==105){
		add();
	}else if(val ==107){
		productInStorage();
	}
}
function selectEXCEL(val) {
	var value = val.name;
	$('#ExportType').attr("value", value);
	$('#subId').click();
	$('#ExportType').attr("value", "");
}
</script>
<span class="preload1"></span>
<span class="preload2"></span>
<ul id="nav">
	<!-- 现在在第几页  -->
	<li class="top">
		<a href="#nogo1" class="top_link"><span style="color: yellow;">
				<c:if test="${x_selType == '1'}">创建调料单 </c:if> <c:if
					test="${x_selType == '2'}">采购收货 </c:if> <c:if
					test="${x_selType == '3'}">采购出库 </c:if> <c:if
					test="${x_selType == '4'}">成品下线扫描 </c:if> <c:if
					test="${x_selType == '6'}">调料单条码打印 </c:if> <c:if
					test="${x_selType == '7'}">采购单条码打印 </c:if> <c:if
					test="${x_selType == '8'}">成品出库记录 </c:if> <c:if
					test="${x_selType == '9'}">库位维护 </c:if> <c:if
					test="${x_selType == '10'}">库区维护 </c:if> <c:if
					test="${x_selType == '11'}">物料信息维护 </c:if> <c:if
					test="${x_selType == '12'}">不合格原因维护 </c:if> <c:if
					test="${x_selType == '13'}">物料库位维护 </c:if> <c:if
					test="${x_selType == '14'}">器具维护 </c:if> <c:if
					test="${x_selType == '15'}">批次拆分/合并 </c:if> <c:if
					test="${x_selType == '16'}">非计划入库报表 </c:if> <c:if
					test="${x_selType == '17'}">非计划出库报表 </c:if> <c:if
					test="${x_selType == '18'}">入库前退货 </c:if> <c:if
					test="${x_selType == '19'}">非计划入库扫描 </c:if> <c:if
					test="${x_selType == '20'}">非计划出库扫描 </c:if> <c:if
					test="${x_selType == '21'}">打印调料单 </c:if> <c:if
					test="${x_selType == '22'}">中转库来料确认 </c:if> <c:if
					test="${x_selType == '23'}">移库扫描 </c:if> <c:if
					test="${x_selType == '24'}">采购单包装量修改 </c:if> <c:if
					test="${x_selType == '25'}">品质扫描质检 </c:if> <c:if
					test="${x_selType == '26'}">采购入库 </c:if> <c:if
					test="${x_selType == '27'}">条码废除 </c:if> <c:if
					test="${x_selType == '28'}">一号线上线扫描 </c:if> <c:if
					test="${x_selType == '30'}">供应商物料抽检比例 </c:if> <c:if
					test="${x_selType == '31'}">采购单关闭 </c:if> <c:if
					test="${x_selType == '32'}">采购单打开 </c:if> <c:if
					test="${x_selType == '33'}">关闭质检单 </c:if> <c:if
					test="${x_selType == '34'}">条码打印 </c:if> <c:if
					test="${x_selType == '35'}">不良品扫描 </c:if> <c:if
					test="${x_selType == '36'}">库房盘点信息 </c:if> <c:if
					test="${x_selType == '100'}">采购单收货信息</c:if> <c:if
					test="${x_selType == '99'}">采购质检报表</c:if> <c:if
					test="${x_selType == '98'}">采购入库报表</c:if> <c:if
					test="${x_selType == '97'}">条码调整报表</c:if> <c:if
					test="${x_selType == '96'}">条码反拆解</c:if> <c:if
					test="${x_selType == '95'}">库存信息报表</c:if> <c:if
					test="${x_selType == '94'}">库存变动报表</c:if> <c:if
					test="${x_selType == '93'}">扫描日志</c:if> <c:if
					test="${x_selType == '92'}">中转库库存报表</c:if> <c:if
					test="${x_selType == '91'}">物料BOM信息</c:if> <c:if
					test="${x_selType == '90'}">扣料信息报表</c:if> <c:if
					test="${x_selType == '89'}">条码信息报表</c:if> <c:if
					test="${x_selType == '88'}">一号线上线扫描报表</c:if> <c:if
					test="${x_selType == '87'}">二号线上线扫描报表</c:if> <c:if
					test="${x_selType == '86'}">盘点差异报表</c:if> <c:if
					test="${x_selType == '85'}">入库后退货记录报表</c:if> <c:if
					test="${x_selType == '84'}">费用科目维护</c:if> <c:if
					test="${x_selType == '83'}">费用部门维护</c:if> <c:if
					test="${x_selType == '82'}">非计划原因代码维护</c:if> <c:if
					test="${x_selType == '81'}">进销存</c:if> <c:if
					test="${x_selType == '80'}">非计划原因代码新增</c:if> <c:if
					test="${x_selType == '78'}">解除冻结</c:if> <c:if
					test="${x_selType == '77'}">冻结条码</c:if> <c:if
					test="${x_selType == '76'}">选择赛鹤条码</c:if> <c:if
					test="${x_selType == '75'}">二号线上线扫描</c:if> <c:if
					test="${x_selType == '101'}">新增成品出库</c:if> <c:if
					test="${x_selType == '103'}">成品条码与赛赫条码对应关系</c:if> <c:if
					test="${x_selType == '105'}">成品器具盛放数量</c:if> <c:if
					test="${x_selType == '106'}">采购出库报表</c:if> <c:if
					test="${x_selType == '107'}">成品下线</c:if><c:if
					test="${x_selType == '108'}">成品出库</c:if><c:if
					test="${x_selType == '110'}">成品移库</c:if><c:if
					test="${x_selType == '111'}">未同步数据列表</c:if><c:if
					test="${x_selType == '112'}">入库后退货</c:if><c:if
					test="${x_selType == '113'}">客户需求计划录入</c:if> <c:if
					test="${x_selType == '114'}">客户维护</c:if><c:if
					test="${x_selType == '115'}">日生产计划</c:if><c:if
					test="${x_selType == '116'}">销售订单明细</c:if><c:if
					test="${x_selType == '117'}">工单BOM</c:if><c:if
					test="${x_selType == '118'}">预发货单列表</c:if><c:if
					test="${x_selType == '119'}">检料单</c:if><c:if
					test="${x_selType == '120'}">发货单</c:if><c:if
					test="${x_selType == '126'}">物料库存报表</c:if><c:if
					test="${x_selType == '127'}">库位库存报表</c:if><c:if
					test="${x_selType == '128'}">入库前退货记录报表</c:if><c:if
					test="${x_selType == '129'}">盘点差异列表</c:if><c:if
					test="${x_selType == '130'}">盘点扫描记录列表</c:if><c:if
					test="${x_selType == '133'}">创建客户需求发货单列表</c:if><c:if
					test="${x_selType == '135'}">库存明细报表</c:if><c:if
					test="${x_selType == '141'}">销售订单关闭</c:if><c:if
					test="${x_selType == '136'}">客户退货</c:if><c:if
					test="${x_selType == '144'}">实时库存报表 </c:if><c:if
					test="${x_selType == '145'}">客户价格报表 </c:if><c:if
					test="${x_selType == '146'}">出库明细报表 </c:if><c:if
					test="${x_selType == '147'}">价格维护列表 </c:if><c:if
					test="${x_selType == '148'}">客户需求计划打开/关闭 </c:if><c:if
					test="${x_selType == '149'}">销售出库报表</c:if><c:if
					test="${x_selType == '150'}">销售明细报表</c:if><c:if
					test="${x_selType == '151'}">销售汇总报表</c:if><c:if
					test="${x_selType == '152'}">客户退货报表</c:if><c:if
					test="${x_selType == '153'}">销售分析报表</c:if><c:if
					test="${x_selType == '154'}">选择物料</c:if><c:if
					test="${x_selType == '155'}">盘点差异列表</c:if><c:if
					test="${x_selType == '156'}">生产计划上传</c:if><c:if
					test="${x_selType == '157'}">生产计划下载</c:if><c:if
					test="${x_selType == '158'}">PO计划查看</c:if><c:if
					test="${x_selType == '159'}">创建PO发货单</c:if><c:if
					test="${x_selType == '160'}">PO计划查看</c:if><c:if
					test="${x_selType == '161'}">同步原始数据列表</c:if><c:if
					test="${x_selType == '162'}">成品计划</c:if><c:if
					test="${x_selType == '163'}">组件详细信息</c:if><c:if
					test="${x_selType == '164'}">合并计划汇总列表</c:if><c:if
					test="${x_selType == '165'}">导入半成品计划列表</c:if><c:if
					test="${x_selType == '166'}">半成品计划列表</c:if><c:if
					test="${x_selType == '167'}">ALC与总成关系</c:if><c:if
					test="${x_selType == '168'}">原材料日需求计划报表</c:if><c:if
					test="${x_selType == '169'}">创建非PO发货单</c:if><c:if
					test="${x_selType == '170'}">创建成品发货单</c:if><c:if
					test="${x_selType == '171'}">JIT时段需求报表</c:if><c:if
					test="${x_selType == '172'}">预测时段库存报表</c:if><c:if
					test="${x_selType == '173'}">实时库存报表</c:if><c:if
					test="${x_selType == '174'}">原材料预计需求报表</c:if><c:if
					test="${x_selType == '175'}">日计划报表</c:if><c:if
					test="${x_selType == '176'}">72小时预计需求报表</c:if><c:if
					test="${x_selType == '177'}">非PO发货单查看</c:if><c:if
					test="${x_selType == '178'}">期初打印条码</c:if><c:if
					test="${x_selType == '179'}">自动分解错误信息</c:if><c:if
					test="${x_selType == '180'}">送货单报表</c:if><c:if
					test="${x_selType == '181'}">每日预计库存量报表</c:if><c:if
					test="${x_selType == '182'}">原材料预计需求量报表</c:if><c:if
					test="${x_selType == '183'}">ALC时段汇总报表</c:if><c:if
					test="${x_selType == '184'}">库存预警报表</c:if>
					</span> </a>
	</li>

	<!-- 现在在第几页  -->
	<li class="top">
		<a href="#nogo1" class="top_link"><span>当前在 <page:select
					style="font-size:11px" format="page.format" resource="true" /> </span> </a>
	</li>

	<!-- 每页显示条数  -->
	<li class="top">
		<a href="#nogo22" id="services" class="top_link"><span
			class="down">每页显示条数</span> </a>
		<ul class="sub">
			<li value="15">
				<a href="#" name="15" onclick="setPageSizes(this)">15</a>
			</li>
			<li value="50">
				<a href="#" name="50" onclick="setPageSizes(this)">50</a>
			</li>
			<li value="100">
				<a href="#" name="100" onclick="setPageSizes(this)">100</a>
			</li>
			<li value="200">
				<a href="#" name="200" onclick="setPageSizes(this)">200</a>
			</li>
			<li value="1000">
				<a href="#" name="1000" onclick="setPageSizes(this)">1000</a>
			</li>
			<li value="-1">
				<a href="#" name="-1" onclick="setPageSizes(this)">All</a>
			</li>
		</ul>
	</li>

	<!-- 操作   -->
	<li class="top">
		<a href="#nogo22" id="services" class="top_link"><span
			class="down">操作</span> </a>
		<ul class="sub">
			<c:if test="${x_selType == null}">
				<li>
					<a href="#">无操作</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '1'}">
				<li>
					<a href="#" id="1" onclick="selectType(this);">创建调料单</a>
				</li>
				
			</c:if>
			<c:if test="${x_selType == '2'}">
				<li>
					<a href="#" id="2" onclick="selectType(this);">采购收货 </a>
				</li>
				<li>
					<a href="#" id="134" onclick="selectType(this);">采购退货 </a>
				</li>
			</c:if>
			<c:if test="${x_selType == '3'}">
				<li>
					<a href="#" id="3" onclick="selectType(this);">原材料出库 </a>
				</li>
			</c:if>
			<c:if test="${x_selType == '4'}">
				<li>
					<a href="#" id="4" onclick="selectType(this);">新增成品入库 </a>
				</li>
			</c:if>
			<c:if test="${x_selType == '5'}">
				<li>
					<a href="#" id="5" onclick="selectType(this);">仓储出库</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '6'}">
				<li>
					<a href="#" id="6" onclick="selectType(this);">打印条码</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '7'}">
				<li>
					<a href="#" id="7" onclick="selectType(this);">打印条码</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '8'}">
				<li>
					<a href="#" id="8" onclick="selectType(this);">打印出库单</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '9'}">
				<li>
					<a href="#" id="9" onclick="selectType(this);">打印库位</a>
				</li>
				<li>
					<a href="#" id="30" onclick="selectType(this);">新增库位</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '10'}">
				<li>
					<a href="#" id="10" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '11'}">
				<li>
					<a href="#" id="11" onclick="selectType(this);">新增物料</a>
				</li>

			</c:if>
			<c:if test="${x_selType1 == '102'}">
				<li>
					<a href="#" id="102" onclick="selectType1(this);">打印物料</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '12'}">
				<li>
					<a href="#" id="13" onclick="selectType(this);">新增不合格原因</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '13'}">
				<li>
					<a href="#" id="14" onclick="selectType(this);">新增</a>
				</li>
				<li>
					<a href="#" id="15" onclick="selectType(this);">下载导入模版</a>
				</li>
				<li>
					<a href="#" id="16" onclick="selectType(this);">导入数据</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '14'}">
				<li>
					<a href="#" id="17" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '15'}">
				<li>
					<a href="#" id="18" onclick="selectType(this);">拆分条码</a>
				</li>
				<li>
					<a href="#" id="19" onclick="selectType(this);">合并条码</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '16'}">
				<li>
					<a href="#" id="20" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '17'}">
				<li>
					<a href="#" id="21" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '18'}">
				<li>
					<a href="#" id="59" onclick="selectType(this);">确认退货</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '20'}">
				<li>
					<a href="#" id="27" onclick="selectType(this);">非计划出库</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '21'}">
				<li>
					<a href="#" id="28" onclick="selectType(this);">打印调料单</a>
				</li>
				<li>
					<a href="#" id="31" onclick="selectType(this);">撤回 </a>
				</li>
			</c:if>
			<c:if test="${x_selType == '22'}">
				<li>
					<a href="#" id="29" onclick="selectType(this);">发货单来料确认</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '23'}">
				<li>
					<a href="#" id="32" onclick="selectType(this);">移库</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '24'}">
				<li>
					<a href="#" id="33" onclick="selectType(this);">修改</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '25'}">
				<li>
					<a href="#" id="34" onclick="selectType(this);">品质质检</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '26'}">
				<li>
					<a href="#" id="35" onclick="selectType(this);">仓储入库</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '27'}">
				<li>
					<a href="#" id="36" onclick="selectType(this);">条码废除</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '28'}">
				<li>
					<a href="#" id="37" onclick="selectType(this);">上线扫描</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '29'}">
				<li>
					<a href="#" id="39" onclick="selectType(this);">下线扫描</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '30'}">
				<li>
					<a href="#" id="40" onclick="selectType(this);">下载模版</a>
				</li>
				<li>
					<a href="#" id="41" onclick="selectType(this);">导入</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '31'}">
				<li>
					<a href="#" id="42" onclick="selectType(this);">关闭采购单</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '32'}">
				<li>
					<a href="#" id="43" onclick="selectType(this);">打开采购单</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '33'}">
				<li>
					<a href="#" id="44" onclick="selectType(this);">质检单关闭</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '34'}">
				<li>
					<a href="#" id="45" onclick="selectType(this);">打印</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '35'}">
				<li>
					<!--<a href="#" id="46" onclick="selectType(this);">不良品扫描</a>
				-->
					<a href="#" id="121" onclick="selectType(this);">不合格</a>
				</li>
				<li>
					<a href="#" id="47" onclick="selectType(this);">合格</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '36'}">
				<li>
					<a href="#" id="48" onclick="selectType(this);">新增盘点</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '37'}">
				<li>
					<a href="#" id="49" onclick="selectType(this);">确认</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '84'}">
				<li>
					<a href="#" id="50" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '83'}">
				<li>
					<a href="#" id="51" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '82'}">
				<li>
					<a href="#" id="52" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '81'}">
				<li>
					<a href="#" id="53" onclick="selectType(this);">创建进销存</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '80'}">
				<li>
					<a href="#" id="54" onclick="selectType(this);">非计划原因代码新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '79'}">
				<li>
					<a href="#" id="22" onclick="selectType(this);">确认选择</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '78'}">
				<li>
					<a href="#" id="55" onclick="selectType(this);">确认解冻</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '77'}">
				<li>
					<a href="#" id="56" onclick="selectType(this);">确认冻结</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '76'}">
				<li>
					<a href="#" id="57" onclick="selectType(this);">确认选择</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '75'}">
				<li>
					<a href="#" id="38" onclick="selectType(this);">确认扫描</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '101'}">
				<li>
					<a href="#" id="58" onclick="selectType(this);">新增成品出库</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '103'}">
				<li>
					<a href="#" id="103" onclick="selectType(this);">新增条码对应关系</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '105'}">
				<li>
					<a href="#" id="105" onclick="selectType1(this);">新增器具盛放数量</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '107'}">
				<li>
					<a href="#" id="107" onclick="selectType1(this);">成品下线入库</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '108'}">
				<li>
					<a href="#" id="108" onclick="selectType(this);">新增成品出库</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '108'}">
				<li>
					<a href="#" id="109" onclick="selectType(this);">确认</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '110'}">
				<li>
					<a href="#" id="110" onclick="selectType(this);">移库</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '112'}">
				<li>
					<a href="#" id="112" onclick="selectType(this);">确认退货</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '113'}">
				<li>
					<a href="#" id="113" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '114'}">
				<li>
					<a href="#" id="114" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '115'}">
				<li>
					<a href="#" id="115" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<%--<c:if test="${x_selType == '116'}">
				<li>
					<a href="#" id="116" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			--%><c:if test="${x_selType == '117'}">
				<li>
					<a href="#" id="117" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '118'}">
				<li>
					<a href="#" id="118" onclick="selectType(this);">新增预发货单</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '85'}">
				<li>
					<a href="#" id="125" onclick="selectType(this);">打印</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '126'}">
				<li>
					<a href="#" id="126" onclick="selectType(this);">选择</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '26'}">
				<li>
					<a href="#" id="127" onclick="selectType(this);">不良品入库</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '18'}">
				<li>
					<a href="#" id="34" onclick="selectType(this);">质检判定</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '112'}">
				<li>
					<a href="#" id="34" onclick="selectType(this);">质检判定</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '128'}">
				<li>
					<a href="#" id="125" onclick="selectType(this);">打印</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '129'}">
				<li>
					<a href="#" id="129" onclick="selectType(this);">人工处理</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '155'}">
				<li>
					<a href="#" id="155" onclick="selectType(this);">人工处理</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '129'}">
				<li>
					<a href="#" id="130" onclick="selectType(this);">系统处理</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '132'}">
				<li>
					<a href="#" id="132" onclick="selectType(this);">选择</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '133'}">
				<li>
					<a href="#" id="133" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '141'}">
				<li>
					<a href="#" id="141" onclick="selectType(this);">关闭销售订单</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '142'}">
				<li>
					<a href="#" id="142" onclick="selectType(this);">打开销售订单</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '136'}">
				<li>
					<a href="#" id="136" onclick="selectType(this);">新增客户退货</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '147'}">
				<li>
					<a href="#" id="147" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '154'}">
				<li>
					<a href="#" id="154" onclick="selectType(this);">选择</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '167'}">
				<li>
					<a href="#" id="167" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<%--<c:if test="${x_selType == '162'}">
				<li>
					<a href="#" id="162" onclick="selectType(this);">分解</a>
				</li>
			</c:if>
			--%><c:if test="${x_selType == '166'}">
				<li>
					<a href="#" id="166" onclick="selectType(this);">分解</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '159'}">
				<li>
					<a href="#" id="159" onclick="selectType(this);">创建PO发货单</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '169'}">
				<li>
					<a href="#" id="169" onclick="selectType(this);">创建非PO发货单</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '170'}">
				<li>
					<a href="#" id="170" onclick="selectType(this);">创建JIT发货单</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '161'}">
				<li>
					<a href="#" id="161" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '164'}">
				<li>
					<a href="#" id="164" onclick="selectType(this);">合并计划</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '164'}">
				<li>
					<a href="#" id="1642" onclick="selectType(this);">自动合并计划</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '161'}">
				<li>
					<a href="#" id="1612" onclick="selectType(this);">同步</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '161'}">
				<li>
					<a href="#" id="1613" onclick="selectType(this);">分解</a>
				</li>
			</c:if>
			<c:if test="${x_selType == '180'}">
				<li>
					<a href="#" id="180" onclick="selectType(this);">新增</a>
				</li>
			</c:if>
		</ul>
	</li>
	<!-- 导出   -->
	<li class="top">
		<a href="#nogo53" id="shop" class="top_link"><span class="down">导出</span>
		</a>
		<ul class="sub">
			<li value="EXCEL">
				<a href="#" name="EXCEL" onclick="selectEXCEL(this);"> <span
					style="margin: 0px 10px;">EXCEL</span> <img src="images/excel.gif"
						border=0 /> </a>
			</li>
			<li value="XML">
				<a href="#" name="XML" onclick="selectEXCEL(this);"> <span
					style="margin: 0px 17px;">XML</span> <img src="images/xml.gif"
						border=0 /> </a>
			</li>
			<li value="CSV">
				<a href="#" name="CSV" onclick="selectEXCEL(this);"> <span
					style="margin: 0px 17px;">CSV</span> <img src="images/csv.gif"
						border=0 /> </a>
			</li>
		</ul>
	</li>
	<!-- 总页数   -->
	<li class="top">
		<a href="#nogo1" class="top_link"><span style="color: white;">
				<bean:message key="page.total" /> <page:pageCount /> <bean:message
					key="page.page" />(<page:count /> <bean:message key="page.record" />)
		</span> </a>
	</li>
</ul>
<script type="text/javascript">
function setPageSizes(page) {
	var val = page.name;
	document.getElementById("pageSize").value = val;
	document.forms[1].submit();
}
//var pagesize=document.getElementById("pageSize").value;
//var setPageSize=document.getElementById("setPageSize").options;
//for(var i=0;i<setPageSize.length;i++){
//if(pagesize==setPageSize[i].value){
//setPageSize[i].selected=true;
//}
//}
</script>