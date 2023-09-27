/*     */ package com.aof.service.po.impl;
/*     */ 
/*     */ import com.aof.dao.DAO;
/*     */ import com.aof.dao.po.PurchaseOrderCondimentSingleDAO;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.StoreRoom;
/*     */ import com.aof.model.inventory.InventoryTransit;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.BoxStatusRqc;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.StoreRoomType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PortalShipOrderItem;
/*     */ import com.aof.model.po.PurchaseOrderCondimentSingle;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import com.aof.model.po.query.PurchaseOrderCondimentSingleQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.InventoryTool;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.inventory.InventoryTransitManager;
/*     */ import com.aof.service.po.PortalShipOrderItemManager;
/*     */ import com.aof.service.po.PurchaseOrderCondimentSingleManager;
/*     */ import com.aof.web.struts.action.ActionUtils;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PurchaseOrderCondimentSingleManagerImpl
/*     */   extends BaseManager
/*     */   implements PurchaseOrderCondimentSingleManager
/*     */ {
/*     */   private PurchaseOrderCondimentSingleDAO dao;
/*     */   private PortalShipOrderItemManager portalShipOrderItemManager;
/*     */   private InventoryManager inventoryManager;
/*     */   InventoryTransitManager inventoryTransitManager;
/*     */   
/*     */   public InventoryTransitManager getInventoryTransitManager() {
/*  44 */     return this.inventoryTransitManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInventoryTransitManager(InventoryTransitManager inventoryTransitManager) {
/*  49 */     this.inventoryTransitManager = inventoryTransitManager;
/*     */   }
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  53 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPortalShipOrderItemManager(PortalShipOrderItemManager portalShipOrderItemManager) {
/*  58 */     this.portalShipOrderItemManager = portalShipOrderItemManager;
/*     */   }
/*     */   
/*     */   public void setDao(PurchaseOrderCondimentSingleDAO dao) {
/*  62 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderCondimentSingle getPurchaseOrderCondimentSingle(Integer id) {
/*  68 */     return this.dao.getPurchaseOrderCondimentSingle(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderCondimentSingleListCount(Map conditions) {
/*  73 */     return this.dao.getPurchaseOrderCondimentSingleListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderCondimentSingleList(Map conditions, int pageNo, int pageSize, PurchaseOrderCondimentSingleQueryOrder order, boolean descend) {
/*  80 */     return this.dao.getPurchaseOrderCondimentSingleList(conditions, pageNo, 
/*  81 */         pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderCondimentSingle updatePurchaseOrderCondimentSingle(PurchaseOrderCondimentSingle single) {
/*  87 */     return this.dao.updatePurchaseOrderCondimentSingle(single);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderCondimentSingle insertPurchaseOrderCondimentSingle(PurchaseOrderCondimentSingle single) {
/*  93 */     return this.dao.insertPurchaseOrderCondimentSingle(single);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderCondimentSingle insertPurchaseOrderCondimentSingle(String arrays) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc ';'
/*     */     //   3: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   6: astore_2
/*     */     //   7: aload_0
/*     */     //   8: new java/util/Date
/*     */     //   11: dup
/*     */     //   12: invokespecial <init> : ()V
/*     */     //   15: invokespecial getLastCodeReceipts : (Ljava/util/Date;)Ljava/lang/String;
/*     */     //   18: astore_3
/*     */     //   19: aload_2
/*     */     //   20: dup
/*     */     //   21: astore #7
/*     */     //   23: arraylength
/*     */     //   24: istore #6
/*     */     //   26: iconst_0
/*     */     //   27: istore #5
/*     */     //   29: goto -> 657
/*     */     //   32: aload #7
/*     */     //   34: iload #5
/*     */     //   36: aaload
/*     */     //   37: astore #4
/*     */     //   39: aload #4
/*     */     //   41: ldc ','
/*     */     //   43: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   46: astore #8
/*     */     //   48: aload #8
/*     */     //   50: iconst_0
/*     */     //   51: aaload
/*     */     //   52: astore #9
/*     */     //   54: aload #8
/*     */     //   56: iconst_1
/*     */     //   57: aaload
/*     */     //   58: astore #10
/*     */     //   60: aload_0
/*     */     //   61: getfield portalShipOrderItemManager : Lcom/aof/service/po/PortalShipOrderItemManager;
/*     */     //   64: aload #9
/*     */     //   66: invokestatic parseInt : (Ljava/lang/String;)I
/*     */     //   69: invokestatic valueOf : (I)Ljava/lang/Integer;
/*     */     //   72: invokeinterface getPortalShipOrderItem : (Ljava/lang/Integer;)Lcom/aof/model/po/PortalShipOrderItem;
/*     */     //   77: astore #11
/*     */     //   79: new com/aof/model/po/PurchaseOrderCondimentSingle
/*     */     //   82: dup
/*     */     //   83: invokespecial <init> : ()V
/*     */     //   86: astore #12
/*     */     //   88: aload #11
/*     */     //   90: invokevirtual getPoipItem : ()Lcom/aof/model/po/PurchaseOrderInspectionPendingItem;
/*     */     //   93: invokevirtual getPoip_number : ()Lcom/aof/model/po/PurchaseOrderInspectionPending;
/*     */     //   96: invokevirtual getSupplier : ()Lcom/aof/model/admin/Supplier;
/*     */     //   99: astore #13
/*     */     //   101: aload #12
/*     */     //   103: new java/util/Date
/*     */     //   106: dup
/*     */     //   107: invokespecial <init> : ()V
/*     */     //   110: invokevirtual setDate : (Ljava/util/Date;)V
/*     */     //   113: aload #12
/*     */     //   115: aload_3
/*     */     //   116: invokevirtual setCode : (Ljava/lang/String;)V
/*     */     //   119: aload #12
/*     */     //   121: aload #11
/*     */     //   123: invokevirtual getPoipItem : ()Lcom/aof/model/po/PurchaseOrderInspectionPendingItem;
/*     */     //   126: invokevirtual setPo_detial_id : (Lcom/aof/model/po/PurchaseOrderInspectionPendingItem;)V
/*     */     //   129: aload #12
/*     */     //   131: aload #11
/*     */     //   133: invokevirtual getPoipItem : ()Lcom/aof/model/po/PurchaseOrderInspectionPendingItem;
/*     */     //   136: invokevirtual getItemNumber : ()Lcom/aof/model/basic/WmsPart;
/*     */     //   139: invokevirtual setPart : (Lcom/aof/model/basic/WmsPart;)V
/*     */     //   142: aload #12
/*     */     //   144: aload #13
/*     */     //   146: invokevirtual setSupplier : (Lcom/aof/model/admin/Supplier;)V
/*     */     //   149: aload #12
/*     */     //   151: new java/math/BigDecimal
/*     */     //   154: dup
/*     */     //   155: aload #10
/*     */     //   157: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   160: invokevirtual setNumber : (Ljava/math/BigDecimal;)V
/*     */     //   163: aload #12
/*     */     //   165: aload #11
/*     */     //   167: invokevirtual getPoipItem : ()Lcom/aof/model/po/PurchaseOrderInspectionPendingItem;
/*     */     //   170: invokevirtual getSupplierItemNumber : ()Ljava/lang/String;
/*     */     //   173: invokevirtual setSupplier_part : (Ljava/lang/String;)V
/*     */     //   176: aload #12
/*     */     //   178: aload #11
/*     */     //   180: invokevirtual getPoipItem : ()Lcom/aof/model/po/PurchaseOrderInspectionPendingItem;
/*     */     //   183: invokevirtual getLine : ()Ljava/lang/String;
/*     */     //   186: invokevirtual setLine : (Ljava/lang/String;)V
/*     */     //   189: aload #12
/*     */     //   191: getstatic com/aof/model/metadata/PurchaseOrderCondimentSingleStatus.Wait : Lcom/aof/model/metadata/PurchaseOrderCondimentSingleStatus;
/*     */     //   194: invokevirtual setStatus : (Lcom/aof/model/metadata/PurchaseOrderCondimentSingleStatus;)V
/*     */     //   197: aload #12
/*     */     //   199: iconst_0
/*     */     //   200: invokestatic valueOf : (I)Ljava/lang/Integer;
/*     */     //   203: invokevirtual setIsPrint : (Ljava/lang/Integer;)V
/*     */     //   206: aload_0
/*     */     //   207: getfield dao : Lcom/aof/dao/po/PurchaseOrderCondimentSingleDAO;
/*     */     //   210: aload #12
/*     */     //   212: invokeinterface insertPurchaseOrderCondimentSingle : (Lcom/aof/model/po/PurchaseOrderCondimentSingle;)Lcom/aof/model/po/PurchaseOrderCondimentSingle;
/*     */     //   217: pop
/*     */     //   218: aload #11
/*     */     //   220: invokevirtual getAlready_season_qty : ()Ljava/math/BigDecimal;
/*     */     //   223: ifnull -> 245
/*     */     //   226: aload #11
/*     */     //   228: invokevirtual getAlready_season_qty : ()Ljava/math/BigDecimal;
/*     */     //   231: new java/math/BigDecimal
/*     */     //   234: dup
/*     */     //   235: iconst_0
/*     */     //   236: invokespecial <init> : (I)V
/*     */     //   239: invokevirtual compareTo : (Ljava/math/BigDecimal;)I
/*     */     //   242: ifne -> 262
/*     */     //   245: aload #11
/*     */     //   247: new java/math/BigDecimal
/*     */     //   250: dup
/*     */     //   251: aload #10
/*     */     //   253: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   256: invokevirtual setAlready_season_qty : (Ljava/math/BigDecimal;)V
/*     */     //   259: goto -> 284
/*     */     //   262: aload #11
/*     */     //   264: aload #11
/*     */     //   266: invokevirtual getAlready_season_qty : ()Ljava/math/BigDecimal;
/*     */     //   269: new java/math/BigDecimal
/*     */     //   272: dup
/*     */     //   273: aload #10
/*     */     //   275: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   278: invokevirtual add : (Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
/*     */     //   281: invokevirtual setAlready_season_qty : (Ljava/math/BigDecimal;)V
/*     */     //   284: aload #11
/*     */     //   286: invokevirtual getAlready_season_qty : ()Ljava/math/BigDecimal;
/*     */     //   289: aload #11
/*     */     //   291: invokevirtual getDeliveryNumber : ()Ljava/math/BigDecimal;
/*     */     //   294: invokevirtual compareTo : (Ljava/math/BigDecimal;)I
/*     */     //   297: ifne -> 308
/*     */     //   300: aload #11
/*     */     //   302: getstatic com/aof/model/metadata/YesNo.YES : Lcom/aof/model/metadata/YesNo;
/*     */     //   305: invokevirtual setStatus : (Lcom/aof/model/metadata/YesNo;)V
/*     */     //   308: aload_0
/*     */     //   309: getfield portalShipOrderItemManager : Lcom/aof/service/po/PortalShipOrderItemManager;
/*     */     //   312: aload #11
/*     */     //   314: invokeinterface updatePortalShipOrderItem : (Lcom/aof/model/po/PortalShipOrderItem;)Lcom/aof/model/po/PortalShipOrderItem;
/*     */     //   319: pop
/*     */     //   320: new java/math/BigDecimal
/*     */     //   323: dup
/*     */     //   324: aload #10
/*     */     //   326: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   329: astore #14
/*     */     //   331: aload #11
/*     */     //   333: invokevirtual getPoipItem : ()Lcom/aof/model/po/PurchaseOrderInspectionPendingItem;
/*     */     //   336: invokevirtual getCapacity : ()Ljava/math/BigDecimal;
/*     */     //   339: astore #15
/*     */     //   341: aload #14
/*     */     //   343: invokevirtual doubleValue : ()D
/*     */     //   346: invokestatic valueOf : (D)Ljava/lang/Double;
/*     */     //   349: astore #16
/*     */     //   351: aload #15
/*     */     //   353: invokevirtual doubleValue : ()D
/*     */     //   356: invokestatic valueOf : (D)Ljava/lang/Double;
/*     */     //   359: astore #17
/*     */     //   361: aload #16
/*     */     //   363: invokevirtual doubleValue : ()D
/*     */     //   366: aload #17
/*     */     //   368: invokevirtual doubleValue : ()D
/*     */     //   371: dcmpg
/*     */     //   372: ifge -> 390
/*     */     //   375: aload_0
/*     */     //   376: aload #11
/*     */     //   378: aload #14
/*     */     //   380: aload #12
/*     */     //   382: iconst_0
/*     */     //   383: invokevirtual insertBox : (Lcom/aof/model/po/PortalShipOrderItem;Ljava/math/BigDecimal;Lcom/aof/model/po/PurchaseOrderCondimentSingle;Z)Lcom/aof/model/po/Box;
/*     */     //   386: pop
/*     */     //   387: goto -> 552
/*     */     //   390: aload #16
/*     */     //   392: invokevirtual doubleValue : ()D
/*     */     //   395: aload #17
/*     */     //   397: invokevirtual doubleValue : ()D
/*     */     //   400: drem
/*     */     //   401: dconst_0
/*     */     //   402: dcmpl
/*     */     //   403: ifne -> 420
/*     */     //   406: aload #16
/*     */     //   408: invokevirtual doubleValue : ()D
/*     */     //   411: aload #17
/*     */     //   413: invokevirtual doubleValue : ()D
/*     */     //   416: ddiv
/*     */     //   417: goto -> 445
/*     */     //   420: aload #16
/*     */     //   422: invokevirtual doubleValue : ()D
/*     */     //   425: aload #16
/*     */     //   427: invokevirtual doubleValue : ()D
/*     */     //   430: aload #17
/*     */     //   432: invokevirtual doubleValue : ()D
/*     */     //   435: drem
/*     */     //   436: dsub
/*     */     //   437: aload #17
/*     */     //   439: invokevirtual doubleValue : ()D
/*     */     //   442: ddiv
/*     */     //   443: dconst_1
/*     */     //   444: dadd
/*     */     //   445: invokestatic valueOf : (D)Ljava/lang/Double;
/*     */     //   448: astore #18
/*     */     //   450: iconst_0
/*     */     //   451: istore #19
/*     */     //   453: goto -> 540
/*     */     //   456: iload #19
/*     */     //   458: iconst_1
/*     */     //   459: iadd
/*     */     //   460: i2d
/*     */     //   461: aload #18
/*     */     //   463: invokevirtual doubleValue : ()D
/*     */     //   466: dcmpl
/*     */     //   467: ifne -> 525
/*     */     //   470: aload #16
/*     */     //   472: invokevirtual doubleValue : ()D
/*     */     //   475: aload #17
/*     */     //   477: invokevirtual doubleValue : ()D
/*     */     //   480: drem
/*     */     //   481: dconst_0
/*     */     //   482: dcmpl
/*     */     //   483: ifeq -> 525
/*     */     //   486: aload #14
/*     */     //   488: invokevirtual intValue : ()I
/*     */     //   491: aload #15
/*     */     //   493: invokevirtual intValue : ()I
/*     */     //   496: irem
/*     */     //   497: istore #20
/*     */     //   499: new java/math/BigDecimal
/*     */     //   502: dup
/*     */     //   503: iload #20
/*     */     //   505: invokespecial <init> : (I)V
/*     */     //   508: astore #21
/*     */     //   510: aload_0
/*     */     //   511: aload #11
/*     */     //   513: aload #21
/*     */     //   515: aload #12
/*     */     //   517: iconst_0
/*     */     //   518: invokevirtual insertBox : (Lcom/aof/model/po/PortalShipOrderItem;Ljava/math/BigDecimal;Lcom/aof/model/po/PurchaseOrderCondimentSingle;Z)Lcom/aof/model/po/Box;
/*     */     //   521: pop
/*     */     //   522: goto -> 537
/*     */     //   525: aload_0
/*     */     //   526: aload #11
/*     */     //   528: aload #15
/*     */     //   530: aload #12
/*     */     //   532: iconst_0
/*     */     //   533: invokevirtual insertBox : (Lcom/aof/model/po/PortalShipOrderItem;Ljava/math/BigDecimal;Lcom/aof/model/po/PurchaseOrderCondimentSingle;Z)Lcom/aof/model/po/Box;
/*     */     //   536: pop
/*     */     //   537: iinc #19, 1
/*     */     //   540: iload #19
/*     */     //   542: i2d
/*     */     //   543: aload #18
/*     */     //   545: invokevirtual doubleValue : ()D
/*     */     //   548: dcmpg
/*     */     //   549: iflt -> 456
/*     */     //   552: new java/util/HashMap
/*     */     //   555: dup
/*     */     //   556: invokespecial <init> : ()V
/*     */     //   559: astore #18
/*     */     //   561: aload #18
/*     */     //   563: getstatic com/aof/model/inventory/query/InventoryTransitQueryCondition.PART_CODE_EQ : Lcom/aof/model/inventory/query/InventoryTransitQueryCondition;
/*     */     //   566: aload #12
/*     */     //   568: invokevirtual getPart : ()Lcom/aof/model/basic/WmsPart;
/*     */     //   571: invokevirtual getId : ()Ljava/lang/String;
/*     */     //   574: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   579: pop
/*     */     //   580: aload_0
/*     */     //   581: getfield inventoryTransitManager : Lcom/aof/service/inventory/InventoryTransitManager;
/*     */     //   584: aload #18
/*     */     //   586: iconst_0
/*     */     //   587: iconst_m1
/*     */     //   588: aconst_null
/*     */     //   589: iconst_0
/*     */     //   590: invokeinterface getInventoryTransitList : (Ljava/util/Map;IILcom/aof/model/inventory/query/InventoryTransitQueryOrder;Z)Ljava/util/List;
/*     */     //   595: astore #19
/*     */     //   597: aload #19
/*     */     //   599: invokeinterface size : ()I
/*     */     //   604: ifle -> 654
/*     */     //   607: aload #19
/*     */     //   609: iconst_0
/*     */     //   610: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   615: checkcast com/aof/model/inventory/InventoryTransit
/*     */     //   618: astore #20
/*     */     //   620: aload #20
/*     */     //   622: aload #20
/*     */     //   624: invokevirtual getQty : ()Ljava/math/BigDecimal;
/*     */     //   627: new java/math/BigDecimal
/*     */     //   630: dup
/*     */     //   631: aload #10
/*     */     //   633: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   636: invokevirtual subtract : (Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
/*     */     //   639: invokevirtual setQty : (Ljava/math/BigDecimal;)V
/*     */     //   642: aload_0
/*     */     //   643: getfield inventoryTransitManager : Lcom/aof/service/inventory/InventoryTransitManager;
/*     */     //   646: aload #20
/*     */     //   648: invokeinterface updateInventoryTransit : (Lcom/aof/model/inventory/InventoryTransit;)Lcom/aof/model/inventory/InventoryTransit;
/*     */     //   653: pop
/*     */     //   654: iinc #5, 1
/*     */     //   657: iload #5
/*     */     //   659: iload #6
/*     */     //   661: if_icmplt -> 32
/*     */     //   664: aconst_null
/*     */     //   665: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #98	-> 0
/*     */     //   #99	-> 7
/*     */     //   #100	-> 19
/*     */     //   #101	-> 39
/*     */     //   #102	-> 48
/*     */     //   #103	-> 54
/*     */     //   #105	-> 60
/*     */     //   #106	-> 64
/*     */     //   #105	-> 77
/*     */     //   #107	-> 79
/*     */     //   #108	-> 88
/*     */     //   #109	-> 96
/*     */     //   #108	-> 99
/*     */     //   #110	-> 101
/*     */     //   #111	-> 113
/*     */     //   #112	-> 119
/*     */     //   #113	-> 129
/*     */     //   #114	-> 142
/*     */     //   #115	-> 149
/*     */     //   #116	-> 163
/*     */     //   #117	-> 176
/*     */     //   #118	-> 189
/*     */     //   #119	-> 197
/*     */     //   #120	-> 206
/*     */     //   #122	-> 218
/*     */     //   #123	-> 226
/*     */     //   #124	-> 231
/*     */     //   #123	-> 242
/*     */     //   #125	-> 245
/*     */     //   #126	-> 259
/*     */     //   #127	-> 262
/*     */     //   #128	-> 269
/*     */     //   #127	-> 278
/*     */     //   #131	-> 284
/*     */     //   #132	-> 289
/*     */     //   #131	-> 297
/*     */     //   #133	-> 300
/*     */     //   #136	-> 308
/*     */     //   #139	-> 320
/*     */     //   #140	-> 331
/*     */     //   #142	-> 341
/*     */     //   #143	-> 351
/*     */     //   #145	-> 361
/*     */     //   #146	-> 375
/*     */     //   #147	-> 387
/*     */     //   #149	-> 390
/*     */     //   #151	-> 450
/*     */     //   #153	-> 456
/*     */     //   #154	-> 486
/*     */     //   #155	-> 499
/*     */     //   #156	-> 510
/*     */     //   #157	-> 522
/*     */     //   #158	-> 525
/*     */     //   #151	-> 537
/*     */     //   #162	-> 552
/*     */     //   #163	-> 561
/*     */     //   #164	-> 580
/*     */     //   #165	-> 597
/*     */     //   #166	-> 607
/*     */     //   #167	-> 620
/*     */     //   #168	-> 642
/*     */     //   #100	-> 654
/*     */     //   #208	-> 664
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	666	0	this	Lcom/aof/service/po/impl/PurchaseOrderCondimentSingleManagerImpl;
/*     */     //   0	666	1	arrays	Ljava/lang/String;
/*     */     //   7	659	2	array	[Ljava/lang/String;
/*     */     //   19	647	3	strCode	Ljava/lang/String;
/*     */     //   39	615	4	str	Ljava/lang/String;
/*     */     //   48	606	8	idandqty	[Ljava/lang/String;
/*     */     //   54	600	9	id	Ljava/lang/String;
/*     */     //   60	594	10	qty	Ljava/lang/String;
/*     */     //   79	575	11	item	Lcom/aof/model/po/PortalShipOrderItem;
/*     */     //   88	566	12	single	Lcom/aof/model/po/PurchaseOrderCondimentSingle;
/*     */     //   101	553	13	supplier	Lcom/aof/model/admin/Supplier;
/*     */     //   331	323	14	sumQty	Ljava/math/BigDecimal;
/*     */     //   341	313	15	capacity	Ljava/math/BigDecimal;
/*     */     //   351	303	16	newSumQty	Ljava/lang/Double;
/*     */     //   361	293	17	newCapacity	Ljava/lang/Double;
/*     */     //   450	102	18	num	Ljava/lang/Double;
/*     */     //   453	99	19	i	I
/*     */     //   499	23	20	counts	I
/*     */     //   510	12	21	sumCounts	Ljava/math/BigDecimal;
/*     */     //   561	93	18	conditions	Ljava/util/Map;
/*     */     //   597	57	19	inventoryTransitList	Ljava/util/List;
/*     */     //   620	34	20	inventoryTransit	Lcom/aof/model/inventory/InventoryTransit;
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*     */     //   597	57	19	inventoryTransitList	Ljava/util/List<Lcom/aof/model/inventory/InventoryTransit;>;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getLastCodeReceipts(Date date) {
/* 212 */     StringBuffer sb = new StringBuffer("CS");
/* 213 */     for (int i = 0; i < 3; i++)
/* 214 */       sb.append('0'); 
/* 215 */     sb.append(StringUtils.right(ActionUtils.get8CharsFromDate(date), 6));
/* 216 */     String prefix = sb.toString();
/* 217 */     String maxId = this.dao.getMaxPoReceiptsBeginWith(prefix);
/* 218 */     int serialNo = 1;
/*     */     
/* 220 */     if (maxId != null) {
/* 221 */       Integer maxSN = ActionUtils.parseInt(StringUtils.right(maxId, 3));
/* 222 */       if (maxSN == null)
/* 223 */         throw new RuntimeException("max serial no. is not digit"); 
/* 224 */       serialNo = maxSN.intValue() + 1;
/*     */     } 
/*     */     
/* 227 */     String sn = String.valueOf(serialNo);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     for (int j = 0; j < 3 - sn.length(); j++)
/* 234 */       sb.append('0'); 
/* 235 */     sb.append(sn);
/* 236 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Box insertBox(PortalShipOrderItem item, BigDecimal amount, PurchaseOrderCondimentSingle single, boolean sign) {
/* 242 */     String supplierCode = item.getPoipItem().getPoip_number().getSupplier()
/* 243 */       .getCode();
/* 244 */     WmsLot wl = (new InventoryTool((DAO)this.dao))
/* 245 */       .insertWOBox(supplierCode, "yyyyMMdd");
/* 246 */     Box box = new Box();
/* 247 */     box.setLot(wl);
/* 248 */     box.setPsoItem(item);
/* 249 */     box.setDate(item.getPortalShipOrder().getCreateDate());
/* 250 */     box.setEnabled(EnabledDisabled.ENABLED);
/* 251 */     box.setPart(item.getPoipItem().getItemNumber());
/* 252 */     box.setNumber(amount);
/* 253 */     box.setPrint_number(Integer.valueOf(0));
/* 254 */     if (sign) {
/* 255 */       box.setStatus_rqc(BoxStatusRqc.EXEMPTION);
/*     */     } else {
/* 257 */       box.setStatus_rqc(BoxStatusRqc.NotTheQuality);
/*     */     } 
/* 259 */     box.setStatus(BoxStatus.Wait);
/* 260 */     box.setStatus_freeze(YesNo.NO);
/* 261 */     box.setIsPrint(YesNo.NO);
/* 262 */     box.setSingle(single);
/* 263 */     box.setPo_number(item.getPoipItem().getPoip_number().getPo_number());
/* 264 */     box.setPo_line(item.getPoipItem().getLine());
/* 265 */     box.setPo_date(item.getPoipItem().getPoip_number().getCreateDate());
/* 266 */     box.setPo_supp(item.getPoipItem().getPoip_number().getSupplier()
/* 267 */         .getCode());
/* 268 */     box.setPo_supp_name(item.getPoipItem().getPoip_number().getSupplier().getName());
/* 269 */     System.out.println(box.getStatus_rqc());
/* 270 */     this.dao.saveObject(box);
/* 271 */     return box;
/*     */   }
/*     */   
/*     */   public void updatePurchaseOrderCondimentConfirm(String arrays, StoreRoomType s) {
/* 275 */     String[] array = arrays.split(";"); byte b; int i; String[] arrayOfString1;
/* 276 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/* 277 */       String[] idAndQty = str.split(",");
/* 278 */       String id = idAndQty[0];
/* 279 */       String qty = idAndQty[1];
/* 280 */       PortalShipOrderItem item = this.portalShipOrderItemManager
/* 281 */         .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(id)));
/*     */       
/* 283 */       List<StoreRoom> list1 = this.dao.getObjectList("from StoreRoom sr where code='ZZK'");
/* 284 */       if (list1 != null) {
/* 285 */         StoreRoom r = list1.get(0);
/*     */         
/* 287 */         List<StorageLocation> list = this.dao.getObjectList("from StorageLocation s where s.basic_storeroom_id = " + r.getId());
/* 288 */         if (list.size() > 0) {
/* 289 */           StorageLocation location = list.get(0);
/*     */           
/* 291 */           String part = item.getPoipItem().getItemNumber().getId();
/* 292 */           List<InventoryTransit> listInventoryTransit = this.dao
/* 293 */             .getObjectList("from InventoryTransit it where it.part.id='" + 
/* 294 */               part + 
/* 295 */               "' and it.location.code='" + 
/* 296 */               location.getCode() + "' ");
/* 297 */           if (listInventoryTransit.size() > 0) {
/* 298 */             InventoryTransit transit = listInventoryTransit.get(0);
/* 299 */             transit.setQty(transit.getQty().add(new BigDecimal(qty)));
/* 300 */             this.dao.updateObject(transit);
/*     */           } else {
/* 302 */             InventoryTransit transit = new InventoryTransit();
/* 303 */             transit.setLocation(location);
/* 304 */             transit.setPart(item.getPoipItem().getItemNumber());
/* 305 */             transit.setQty(new BigDecimal(qty));
/* 306 */             this.dao.saveObject(transit);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 311 */       if (item.getReceived_qty() != null) {
/* 312 */         item.setReceived_qty(item.getReceived_qty().add(
/* 313 */               new BigDecimal(qty)));
/*     */       } else {
/* 315 */         item.setReceived_qty(new BigDecimal(qty));
/*     */       } 
/*     */       
/* 318 */       item.setStatus_confirm(YesNo.YES);
/*     */       
/* 320 */       this.portalShipOrderItemManager.updatePortalShipOrderItem(item);
/*     */       b++; }
/*     */   
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PurchaseOrderCondimentSingleManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */