/*     */ package com.aof.utils;
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
/*     */ public class RWLock
/*     */ {
/*     */   public static boolean TRACE = false;
/*  20 */   private static ThreadLocal flag = new ThreadLocal();
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
/*  31 */   private Object mutex = new Object();
/*  32 */   private int givenLocks = 0;
/*  33 */   private int waitingWriters = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getReadLock() {
/*  42 */     synchronized (this.mutex) {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/*  47 */         while (this.givenLocks == -1 || this.waitingWriters != 0)
/*     */         {
/*  49 */           if (TRACE)
/*  50 */             System.out.println(String.valueOf(Thread.currentThread().toString()) + "waiting for readlock"); 
/*  51 */           this.mutex.wait();
/*     */         }
/*     */       
/*  54 */       } catch (InterruptedException e) {
/*     */         
/*  56 */         System.out.println(e);
/*     */       } 
/*     */       
/*  59 */       this.givenLocks++;
/*  60 */       flag.set("readlock");
/*     */       
/*  62 */       if (TRACE) {
/*  63 */         System.out.println(String.valueOf(Thread.currentThread().toString()) + " got readlock, GivenLocks = " + this.givenLocks);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getWriteLock() {
/*  74 */     synchronized (this.mutex) {
/*     */       
/*  76 */       this.waitingWriters++;
/*     */       
/*     */       try {
/*  79 */         while (this.givenLocks != 0 && (this.givenLocks != 1 || flag.get() == null))
/*     */         {
/*  81 */           if (TRACE)
/*  82 */             System.out.println(String.valueOf(Thread.currentThread().toString()) + "waiting for writelock"); 
/*  83 */           this.mutex.wait();
/*     */         }
/*     */       
/*  86 */       } catch (InterruptedException e) {
/*     */         
/*  88 */         System.out.println(e);
/*     */       } 
/*     */       
/*  91 */       this.waitingWriters--;
/*  92 */       this.givenLocks = -1;
/*  93 */       flag.set(null);
/*     */       
/*  95 */       if (TRACE) {
/*  96 */         System.out.println(String.valueOf(Thread.currentThread().toString()) + " got writelock, GivenLocks = " + this.givenLocks);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void releaseLock() {
/* 107 */     synchronized (this.mutex) {
/*     */       
/* 109 */       this.mutex.notifyAll();
/* 110 */       if (this.givenLocks == 0) {
/*     */         return;
/*     */       }
/* 113 */       if (this.givenLocks == -1) {
/* 114 */         this.givenLocks = 0;
/*     */       } else {
/* 116 */         this.givenLocks--;
/* 117 */         flag.set(null);
/*     */       } 
/*     */       
/* 120 */       if (TRACE)
/* 121 */         System.out.println(String.valueOf(Thread.currentThread().toString()) + " released lock, GivenLocks = " + this.givenLocks); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/utils/RWLock.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */