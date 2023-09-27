/*    */ package com.aof.utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IpAddress
/*    */ {
/* 18 */   private int[] address = new int[4];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAddress(int index) {
/* 26 */     return this.address[index];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IpAddress(String addressString) throws Exception {
/* 36 */     String[] part = addressString.split("[.]");
/* 37 */     if (part.length != this.address.length)
/* 38 */       throw new Exception("Error ip address format"); 
/* 39 */     for (int i = 0; i < this.address.length; i++) {
/* 40 */       this.address[i] = Integer.parseInt(part[i]);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(IpAddress anotherIpAddress) {
/* 54 */     for (int index = 0; index < this.address.length; index++) {
/* 55 */       int dif = getAddress(index) - anotherIpAddress.getAddress(index);
/* 56 */       if (dif != 0)
/* 57 */         return dif; 
/*    */     } 
/* 59 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object rhs) {
/* 68 */     if (rhs == null)
/* 69 */       return false; 
/* 70 */     if (this == rhs)
/* 71 */       return true; 
/* 72 */     if (!(rhs instanceof IpAddress))
/* 73 */       return false; 
/* 74 */     IpAddress that = (IpAddress)rhs;
/* 75 */     for (int index = 0; index < this.address.length; index++) {
/* 76 */       if (getAddress(index) != that.getAddress(index))
/* 77 */         return false; 
/* 78 */     }  return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/*    */     String str;
/* 85 */     int i = this.address[0];
/* 86 */     for (int index = 1; index < this.address.length; index++)
/* 87 */       str = String.valueOf(i) + "." + this.address[index]; 
/* 88 */     return str;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/utils/IpAddress.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */