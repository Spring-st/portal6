/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.PortalShipOrderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
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
/*     */ public abstract class AbstractPortalShipOrder
/*     */   implements Serializable
/*     */ {
/*  32 */   private Integer hashValue = Integer.valueOf(0);
/*     */   
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private Department department;
/*     */   private EnabledDisabled enabled;
/*     */   private String remark;
/*     */   private String code;
/*     */   private String asnNo;
/*     */   private YesNo isPrint;
/*     */   private PortalShipOrderStatus status;
/*     */   private Date arrivalDate;
/*     */   private Date createDate;
/*     */   private Integer type;
/*     */   private Supplier supplier;
/*     */   private String createType;
/*     */   private Integer isSync;
/*     */   private Date realDate;
/*     */   private BigDecimal differenceCount;
/*     */   private Date printDate;
/*     */   
/*     */   public Date getCreateDate() {
/*  54 */     return this.createDate;
/*     */   }
/*     */   
/*     */   public void setCreateDate(Date createDate) {
/*  58 */     this.createDate = createDate;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  62 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  66 */     this.site = site;
/*     */   }
/*     */   
/*     */   public PortalShipOrderStatus getStatus() {
/*  70 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PortalShipOrderStatus status) {
/*  74 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractPortalShipOrder() {}
/*     */   
/*     */   public AbstractPortalShipOrder(Integer id) {
/*  81 */     setId(id);
/*     */   }
/*     */   
/*     */   public Date getArrivalDate() {
/*  85 */     return this.arrivalDate;
/*     */   }
/*     */   
/*     */   public void setArrivalDate(Date arrivalDate) {
/*  89 */     this.arrivalDate = arrivalDate;
/*     */   }
/*     */   
/*     */   public Integer getHashValue() {
/*  93 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(Integer hashValue) {
/*  97 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 101 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 105 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Department getDepartment() {
/* 109 */     return this.department;
/*     */   }
/*     */   
/*     */   public void setDepartment(Department department) {
/* 113 */     this.department = department;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 117 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 121 */     this.enabled = enabled;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 125 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 129 */     this.remark = remark;
/*     */   }
/*     */   
/*     */   public String getAsnNo() {
/* 133 */     return this.asnNo;
/*     */   }
/*     */   
/*     */   public void setAsnNo(String asnNo) {
/* 137 */     this.asnNo = asnNo;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 141 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 145 */     this.code = code;
/*     */   }
/*     */   
/*     */   public YesNo getIsPrint() {
/* 149 */     return this.isPrint;
/*     */   }
/*     */   
/*     */   public void setIsPrint(YesNo isPrint) {
/* 153 */     this.isPrint = isPrint;
/*     */   }
/*     */   
/*     */   public Integer getType() {
/* 157 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(Integer type) {
/* 161 */     this.type = type;
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 165 */     if (rhs == null)
/* 166 */       return false; 
/* 167 */     if (this == rhs)
/* 168 */       return true; 
/* 169 */     if (!(rhs instanceof PortalShipOrder))
/* 170 */       return false; 
/* 171 */     PortalShipOrder that = (PortalShipOrder)rhs;
/* 172 */     if (getId() != null)
/* 173 */       return getId().equals(that.getId()); 
/* 174 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 185 */     if (this.hashValue.intValue() == 0) {
/* 186 */       int result = 17;
/* 187 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 188 */       result = result * 37 + poIdValue;
/* 189 */       this.hashValue = Integer.valueOf(result);
/*     */     } 
/* 191 */     return this.hashValue.intValue();
/*     */   }
/*     */   
/*     */   public Supplier getSupplier() {
/* 195 */     return this.supplier;
/*     */   }
/*     */   
/*     */   public void setSupplier(Supplier supplier) {
/* 199 */     this.supplier = supplier;
/*     */   }
/*     */   
/*     */   public String getCreateType() {
/* 203 */     return this.createType;
/*     */   }
/*     */   
/*     */   public void setCreateType(String createType) {
/* 207 */     this.createType = createType;
/*     */   }
/*     */   
/*     */   public Integer getIsSync() {
/* 211 */     return this.isSync;
/*     */   }
/*     */   
/*     */   public void setIsSync(Integer isSync) {
/* 215 */     this.isSync = isSync;
/*     */   }
/*     */   
/*     */   public Date getRealDate() {
/* 219 */     return this.realDate;
/*     */   }
/*     */   
/*     */   public void setRealDate(Date realDate) {
/* 223 */     this.realDate = realDate;
/*     */   }
/*     */   
/*     */   public BigDecimal getDifferenceCount() {
/* 227 */     return this.differenceCount;
/*     */   }
/*     */   
/*     */   public void setDifferenceCount(BigDecimal differenceCount) {
/* 231 */     this.differenceCount = differenceCount;
/*     */   }
/*     */   
/*     */   public Date getPrintDate() {
/* 235 */     return this.printDate;
/*     */   }
/*     */   
/*     */   public void setPrintDate(Date printDate) {
/* 239 */     this.printDate = printDate;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPortalShipOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */