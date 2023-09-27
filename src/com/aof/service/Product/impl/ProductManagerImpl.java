/*     */ package com.aof.service.Product.impl;
/*     */ 
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.product.ProductDAO;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.InventoryType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.product.Product;
/*     */ import com.aof.model.product.ProductBelowLine;
/*     */ import com.aof.model.product.query.ProductQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.Product.ProductManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ProductManagerImpl
/*     */   extends BaseManager implements ProductManager {
/*     */   private ProductDAO dao;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   private InventoryManager inventoryManager;
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  28 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  32 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public void setDao(ProductDAO dao) {
/*  36 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public Product getProduct(Integer id) {
/*  41 */     return this.dao.getProduct(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProductListCount(Map conditions) {
/*  46 */     return this.dao.getProductListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getProductList(Map conditions, int pageNo, int pageSize, ProductQueryOrder order, boolean descend) {
/*  52 */     return this.dao.getProductList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public Product insertProduct(Product city) {
/*  57 */     return this.dao.insertProduct(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public Product updateProduct(Product city) {
/*  62 */     return this.dao.updateProduct(city);
/*     */   }
/*     */   
/*     */   public String scanningOutboundByProduct(String lot, String userId) {
/*  66 */     ScanLog scanLog = new ScanLog();
/*  67 */     scanLog.setDate(new Date());
/*  68 */     scanLog.setDescribe(lot);
/*  69 */     scanLog.setType(Integer.valueOf(4));
/*  70 */     User user = (User)this.scanLogDAO.getObject(User.class, userId);
/*  71 */     if (user != null) {
/*  72 */       scanLog.setUserId(user);
/*     */     }
/*  74 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */ 
/*     */     
/*     */     try {
/*  78 */       List<Box> boxList = this.dao
/*  79 */         .getObjectList("from Box box where box.id = '" + 
/*  80 */           lot + 
/*  81 */           "' and box.status <> '4' and box.part.freeze_status <> 0 and box.location.freeae_status <> 0 ");
/*  82 */       if (boxList.size() != 0) {
/*  83 */         Box box = boxList.get(0);
/*     */         
/*  85 */         this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.FINISHPRODUCTINSTORAGEOUT, Boolean.valueOf(true));
/*     */ 
/*     */         
/*  88 */         Product product = new Product();
/*  89 */         product.setBox(box);
/*  90 */         product.setDate(new Date());
/*  91 */         product.setIsSync(YesNo.NO);
/*  92 */         product.setUserId(user);
/*  93 */         product.setQty(box.getNumber());
/*  94 */         this.dao.saveObject(product);
/*     */ 
/*     */         
/*  97 */         box.setOut_date(new Date());
/*     */         
/*  99 */         box.setStatus(BoxStatus.HASTHE);
/* 100 */         this.dao.updateObject(box);
/*     */       } else {
/* 102 */         return "false:批次已出库或者库位冻结物料冻结";
/*     */       } 
/* 104 */     } catch (Exception e) {
/* 105 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public List<ProductBelowLine> getAllProductBelowLines() {
/* 112 */     return this.dao.getAllProductBelowLines();
/*     */   }
/*     */   
/*     */   public void delete(Product product) {
/* 116 */     this.dao.delete(product);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ProductBelowLine> getBelowLineByStorage(String stroage) {
/* 121 */     return this.dao.getBelowLineByStorage(stroage);
/*     */   }
/*     */   
/*     */   public ProductBelowLine getProductBelowLineById(Integer id) {
/* 125 */     return (ProductBelowLine)this.dao.getObject(ProductBelowLine.class, id);
/*     */   }
/*     */   
/*     */   public void updateProductBelowLine(ProductBelowLine belowLine) {
/* 129 */     this.dao.updateObject(belowLine);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/ProductManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */