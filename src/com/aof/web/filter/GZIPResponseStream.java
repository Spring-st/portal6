/*     */ package com.aof.web.filter;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ public class GZIPResponseStream
/*     */   extends ServletOutputStream
/*     */ {
/*  13 */   protected OutputStream bufferedOutput = null;
/*     */ 
/*     */   
/*     */   protected boolean closed = false;
/*     */ 
/*     */   
/*  19 */   protected HttpServletResponse response = null;
/*     */ 
/*     */   
/*  22 */   protected ServletOutputStream output = null;
/*     */ 
/*     */   
/*  25 */   private int bufferSize = 50000;
/*     */ 
/*     */ 
/*     */   
/*     */   public GZIPResponseStream(HttpServletResponse response) throws IOException {
/*  30 */     this.closed = false;
/*  31 */     this.response = response;
/*  32 */     this.output = response.getOutputStream();
/*  33 */     this.bufferedOutput = new ByteArrayOutputStream();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  38 */     if (this.closed) {
/*  39 */       throw new IOException("This output stream has already been closed");
/*     */     }
/*     */ 
/*     */     
/*  43 */     if (this.bufferedOutput instanceof ByteArrayOutputStream) {
/*     */       
/*  45 */       ByteArrayOutputStream baos = (ByteArrayOutputStream)this.bufferedOutput;
/*     */ 
/*     */       
/*  48 */       ByteArrayOutputStream compressedContent = 
/*  49 */         new ByteArrayOutputStream();
/*  50 */       GZIPOutputStream gzipstream = 
/*  51 */         new GZIPOutputStream(compressedContent);
/*  52 */       byte[] bytes = baos.toByteArray();
/*  53 */       gzipstream.write(bytes);
/*  54 */       gzipstream.finish();
/*     */ 
/*     */       
/*  57 */       byte[] compressedBytes = compressedContent.toByteArray();
/*     */ 
/*     */       
/*  60 */       this.response.setContentLength(compressedBytes.length);
/*  61 */       this.response.addHeader("Content-Encoding", "gzip");
/*  62 */       this.output.write(compressedBytes);
/*  63 */       this.output.flush();
/*  64 */       this.output.close();
/*  65 */       this.closed = true;
/*     */     
/*     */     }
/*  68 */     else if (this.bufferedOutput instanceof GZIPOutputStream) {
/*     */       
/*  70 */       GZIPOutputStream gzipstream = (GZIPOutputStream)this.bufferedOutput;
/*     */ 
/*     */       
/*  73 */       gzipstream.finish();
/*     */ 
/*     */       
/*  76 */       this.output.flush();
/*  77 */       this.output.close();
/*  78 */       this.closed = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/*  83 */     if (this.closed) {
/*  84 */       throw new IOException("Cannot flush a closed output stream");
/*     */     }
/*     */     
/*  87 */     this.bufferedOutput.flush();
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/*  91 */     if (this.closed) {
/*  92 */       throw new IOException("Cannot write to a closed output stream");
/*     */     }
/*     */ 
/*     */     
/*  96 */     checkBufferSize(1);
/*     */ 
/*     */     
/*  99 */     this.bufferedOutput.write((byte)b);
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkBufferSize(int length) throws IOException {
/* 104 */     if (this.bufferedOutput instanceof ByteArrayOutputStream) {
/* 105 */       ByteArrayOutputStream baos = (ByteArrayOutputStream)this.bufferedOutput;
/*     */       
/* 107 */       if (baos.size() + length > this.bufferSize) {
/*     */         
/* 109 */         this.response.addHeader("Content-Encoding", "gzip");
/*     */ 
/*     */         
/* 112 */         byte[] bytes = baos.toByteArray();
/*     */ 
/*     */         
/* 115 */         GZIPOutputStream gzipstream = new GZIPOutputStream((OutputStream)this.output);
/* 116 */         gzipstream.write(bytes);
/*     */ 
/*     */         
/* 119 */         this.bufferedOutput = gzipstream;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 125 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 130 */     if (this.closed) {
/* 131 */       throw new IOException("Cannot write to a closed output stream");
/*     */     }
/*     */ 
/*     */     
/* 135 */     checkBufferSize(len);
/*     */ 
/*     */     
/* 138 */     this.bufferedOutput.write(b, off, len);
/*     */   }
/*     */   
/*     */   public boolean closed() {
/* 142 */     return this.closed;
/*     */   }
/*     */   
/*     */   public void reset() {}
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/filter/GZIPResponseStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */