/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractProductionDayPlanHistory implements Serializable {
/*     */   private Integer id;
/*     */   private String asnNo;
/*     */   private String version;
/*     */   private Date creatDate;
/*     */   private Integer creatTime;
/*     */   private Date dailyDate;
/*     */   private String carkind;
/*     */   private BigDecimal planRem;
/*     */   private BigDecimal planSum;
/*     */   private BigDecimal planWeitou;
/*     */   private BigDecimal planLco;
/*     */   private BigDecimal planLcx;
/*     */   private BigDecimal planAll;
/*     */   private BigDecimal planM1;
/*     */   private BigDecimal planMouth;
/*     */   private BigDecimal planD1;
/*     */   private BigDecimal planPbs;
/*     */   private BigDecimal planPrj;
/*     */   private BigDecimal planWbs;
/*     */   private BigDecimal planSum1;
/*     */   private BigDecimal day1;
/*     */   private BigDecimal day2;
/*     */   private BigDecimal day3;
/*     */   private BigDecimal day4;
/*     */   private BigDecimal day5;
/*     */   private BigDecimal day6;
/*     */   private BigDecimal day7;
/*     */   private BigDecimal day8;
/*     */   private BigDecimal day9;
/*     */   private BigDecimal day10;
/*     */   private BigDecimal day11;
/*     */   private BigDecimal day12;
/*     */   private BigDecimal day13;
/*     */   private BigDecimal day14;
/*     */   private BigDecimal day15;
/*     */   private BigDecimal day16;
/*     */   private BigDecimal day17;
/*     */   private BigDecimal day18;
/*     */   private BigDecimal day19;
/*     */   private BigDecimal day20;
/*     */   private BigDecimal day21;
/*     */   private BigDecimal day22;
/*     */   private BigDecimal day23;
/*     */   private BigDecimal day24;
/*     */   private BigDecimal day25;
/*     */   private BigDecimal day26;
/*     */   private BigDecimal day27;
/*     */   private BigDecimal day28;
/*     */   private BigDecimal day29;
/*     */   private BigDecimal day30;
/*     */   private BigDecimal day31;
/*     */   private BigDecimal day32;
/*     */   private BigDecimal day33;
/*     */   private BigDecimal day34;
/*     */   private BigDecimal day35;
/*     */   private BigDecimal day36;
/*     */   private BigDecimal day37;
/*     */   private BigDecimal day38;
/*     */   private BigDecimal day39;
/*     */   private BigDecimal day40;
/*     */   private BigDecimal day41;
/*     */   private BigDecimal day42;
/*     */   private BigDecimal day43;
/*     */   private BigDecimal day44;
/*     */   private BigDecimal day45;
/*     */   
/*     */   public AbstractProductionDayPlanHistory() {}
/*     */   
/*     */   public AbstractProductionDayPlanHistory(Integer id) {
/*  77 */     this.id = id;
/*     */   }
/*     */   public Integer getId() {
/*  80 */     return this.id;
/*     */   }
/*     */   public void setId(Integer id) {
/*  83 */     this.id = id;
/*     */   }
/*     */   public String getAsnNo() {
/*  86 */     return this.asnNo;
/*     */   }
/*     */   public void setAsnNo(String asnNo) {
/*  89 */     this.asnNo = asnNo;
/*     */   }
/*     */   public String getVersion() {
/*  92 */     return this.version;
/*     */   }
/*     */   public void setVersion(String version) {
/*  95 */     this.version = version;
/*     */   }
/*     */   public Date getCreatDate() {
/*  98 */     return this.creatDate;
/*     */   }
/*     */   public void setCreatDate(Date creatDate) {
/* 101 */     this.creatDate = creatDate;
/*     */   }
/*     */   public Integer getCreatTime() {
/* 104 */     return this.creatTime;
/*     */   }
/*     */   public void setCreatTime(Integer creatTime) {
/* 107 */     this.creatTime = creatTime;
/*     */   }
/*     */   public Date getDailyDate() {
/* 110 */     return this.dailyDate;
/*     */   }
/*     */   public void setDailyDate(Date dailyDate) {
/* 113 */     this.dailyDate = dailyDate;
/*     */   }
/*     */   public String getCarkind() {
/* 116 */     return this.carkind;
/*     */   }
/*     */   public void setCarkind(String carkind) {
/* 119 */     this.carkind = carkind;
/*     */   }
/*     */   public BigDecimal getPlanRem() {
/* 122 */     return this.planRem;
/*     */   }
/*     */   public void setPlanRem(BigDecimal planRem) {
/* 125 */     this.planRem = planRem;
/*     */   }
/*     */   public BigDecimal getPlanSum() {
/* 128 */     return this.planSum;
/*     */   }
/*     */   public void setPlanSum(BigDecimal planSum) {
/* 131 */     this.planSum = planSum;
/*     */   }
/*     */   public BigDecimal getPlanWeitou() {
/* 134 */     return this.planWeitou;
/*     */   }
/*     */   public void setPlanWeitou(BigDecimal planWeitou) {
/* 137 */     this.planWeitou = planWeitou;
/*     */   }
/*     */   public BigDecimal getPlanLco() {
/* 140 */     return this.planLco;
/*     */   }
/*     */   public void setPlanLco(BigDecimal planLco) {
/* 143 */     this.planLco = planLco;
/*     */   }
/*     */   public BigDecimal getPlanLcx() {
/* 146 */     return this.planLcx;
/*     */   }
/*     */   public void setPlanLcx(BigDecimal planLcx) {
/* 149 */     this.planLcx = planLcx;
/*     */   }
/*     */   public BigDecimal getPlanAll() {
/* 152 */     return this.planAll;
/*     */   }
/*     */   public void setPlanAll(BigDecimal planAll) {
/* 155 */     this.planAll = planAll;
/*     */   }
/*     */   public BigDecimal getPlanM1() {
/* 158 */     return this.planM1;
/*     */   }
/*     */   public void setPlanM1(BigDecimal planM1) {
/* 161 */     this.planM1 = planM1;
/*     */   }
/*     */   public BigDecimal getPlanMouth() {
/* 164 */     return this.planMouth;
/*     */   }
/*     */   public void setPlanMouth(BigDecimal planMouth) {
/* 167 */     this.planMouth = planMouth;
/*     */   }
/*     */   public BigDecimal getPlanD1() {
/* 170 */     return this.planD1;
/*     */   }
/*     */   public void setPlanD1(BigDecimal planD1) {
/* 173 */     this.planD1 = planD1;
/*     */   }
/*     */   public BigDecimal getPlanPbs() {
/* 176 */     return this.planPbs;
/*     */   }
/*     */   public void setPlanPbs(BigDecimal planPbs) {
/* 179 */     this.planPbs = planPbs;
/*     */   }
/*     */   public BigDecimal getPlanPrj() {
/* 182 */     return this.planPrj;
/*     */   }
/*     */   public void setPlanPrj(BigDecimal planPrj) {
/* 185 */     this.planPrj = planPrj;
/*     */   }
/*     */   public BigDecimal getPlanWbs() {
/* 188 */     return this.planWbs;
/*     */   }
/*     */   public void setPlanWbs(BigDecimal planWbs) {
/* 191 */     this.planWbs = planWbs;
/*     */   }
/*     */   public BigDecimal getPlanSum1() {
/* 194 */     return this.planSum1;
/*     */   }
/*     */   public void setPlanSum1(BigDecimal planSum1) {
/* 197 */     this.planSum1 = planSum1;
/*     */   }
/*     */   public BigDecimal getDay1() {
/* 200 */     return this.day1;
/*     */   }
/*     */   public void setDay1(BigDecimal day1) {
/* 203 */     this.day1 = day1;
/*     */   }
/*     */   public BigDecimal getDay2() {
/* 206 */     return this.day2;
/*     */   }
/*     */   public void setDay2(BigDecimal day2) {
/* 209 */     this.day2 = day2;
/*     */   }
/*     */   public BigDecimal getDay3() {
/* 212 */     return this.day3;
/*     */   }
/*     */   public void setDay3(BigDecimal day3) {
/* 215 */     this.day3 = day3;
/*     */   }
/*     */   public BigDecimal getDay4() {
/* 218 */     return this.day4;
/*     */   }
/*     */   public void setDay4(BigDecimal day4) {
/* 221 */     this.day4 = day4;
/*     */   }
/*     */   public BigDecimal getDay5() {
/* 224 */     return this.day5;
/*     */   }
/*     */   public void setDay5(BigDecimal day5) {
/* 227 */     this.day5 = day5;
/*     */   }
/*     */   public BigDecimal getDay6() {
/* 230 */     return this.day6;
/*     */   }
/*     */   public void setDay6(BigDecimal day6) {
/* 233 */     this.day6 = day6;
/*     */   }
/*     */   public BigDecimal getDay7() {
/* 236 */     return this.day7;
/*     */   }
/*     */   public void setDay7(BigDecimal day7) {
/* 239 */     this.day7 = day7;
/*     */   }
/*     */   public BigDecimal getDay8() {
/* 242 */     return this.day8;
/*     */   }
/*     */   public void setDay8(BigDecimal day8) {
/* 245 */     this.day8 = day8;
/*     */   }
/*     */   public BigDecimal getDay9() {
/* 248 */     return this.day9;
/*     */   }
/*     */   public void setDay9(BigDecimal day9) {
/* 251 */     this.day9 = day9;
/*     */   }
/*     */   public BigDecimal getDay10() {
/* 254 */     return this.day10;
/*     */   }
/*     */   public void setDay10(BigDecimal day10) {
/* 257 */     this.day10 = day10;
/*     */   }
/*     */   public BigDecimal getDay11() {
/* 260 */     return this.day11;
/*     */   }
/*     */   public void setDay11(BigDecimal day11) {
/* 263 */     this.day11 = day11;
/*     */   }
/*     */   public BigDecimal getDay12() {
/* 266 */     return this.day12;
/*     */   }
/*     */   public void setDay12(BigDecimal day12) {
/* 269 */     this.day12 = day12;
/*     */   }
/*     */   public BigDecimal getDay13() {
/* 272 */     return this.day13;
/*     */   }
/*     */   public void setDay13(BigDecimal day13) {
/* 275 */     this.day13 = day13;
/*     */   }
/*     */   public BigDecimal getDay14() {
/* 278 */     return this.day14;
/*     */   }
/*     */   public void setDay14(BigDecimal day14) {
/* 281 */     this.day14 = day14;
/*     */   }
/*     */   public BigDecimal getDay15() {
/* 284 */     return this.day15;
/*     */   }
/*     */   public void setDay15(BigDecimal day15) {
/* 287 */     this.day15 = day15;
/*     */   }
/*     */   public BigDecimal getDay16() {
/* 290 */     return this.day16;
/*     */   }
/*     */   public void setDay16(BigDecimal day16) {
/* 293 */     this.day16 = day16;
/*     */   }
/*     */   public BigDecimal getDay17() {
/* 296 */     return this.day17;
/*     */   }
/*     */   public void setDay17(BigDecimal day17) {
/* 299 */     this.day17 = day17;
/*     */   }
/*     */   public BigDecimal getDay18() {
/* 302 */     return this.day18;
/*     */   }
/*     */   public void setDay18(BigDecimal day18) {
/* 305 */     this.day18 = day18;
/*     */   }
/*     */   public BigDecimal getDay19() {
/* 308 */     return this.day19;
/*     */   }
/*     */   public void setDay19(BigDecimal day19) {
/* 311 */     this.day19 = day19;
/*     */   }
/*     */   public BigDecimal getDay20() {
/* 314 */     return this.day20;
/*     */   }
/*     */   public void setDay20(BigDecimal day20) {
/* 317 */     this.day20 = day20;
/*     */   }
/*     */   public BigDecimal getDay21() {
/* 320 */     return this.day21;
/*     */   }
/*     */   public void setDay21(BigDecimal day21) {
/* 323 */     this.day21 = day21;
/*     */   }
/*     */   public BigDecimal getDay22() {
/* 326 */     return this.day22;
/*     */   }
/*     */   public void setDay22(BigDecimal day22) {
/* 329 */     this.day22 = day22;
/*     */   }
/*     */   public BigDecimal getDay23() {
/* 332 */     return this.day23;
/*     */   }
/*     */   public void setDay23(BigDecimal day23) {
/* 335 */     this.day23 = day23;
/*     */   }
/*     */   public BigDecimal getDay24() {
/* 338 */     return this.day24;
/*     */   }
/*     */   public void setDay24(BigDecimal day24) {
/* 341 */     this.day24 = day24;
/*     */   }
/*     */   public BigDecimal getDay25() {
/* 344 */     return this.day25;
/*     */   }
/*     */   public void setDay25(BigDecimal day25) {
/* 347 */     this.day25 = day25;
/*     */   }
/*     */   public BigDecimal getDay26() {
/* 350 */     return this.day26;
/*     */   }
/*     */   public void setDay26(BigDecimal day26) {
/* 353 */     this.day26 = day26;
/*     */   }
/*     */   public BigDecimal getDay27() {
/* 356 */     return this.day27;
/*     */   }
/*     */   public void setDay27(BigDecimal day27) {
/* 359 */     this.day27 = day27;
/*     */   }
/*     */   public BigDecimal getDay28() {
/* 362 */     return this.day28;
/*     */   }
/*     */   public void setDay28(BigDecimal day28) {
/* 365 */     this.day28 = day28;
/*     */   }
/*     */   public BigDecimal getDay29() {
/* 368 */     return this.day29;
/*     */   }
/*     */   public void setDay29(BigDecimal day29) {
/* 371 */     this.day29 = day29;
/*     */   }
/*     */   public BigDecimal getDay30() {
/* 374 */     return this.day30;
/*     */   }
/*     */   public void setDay30(BigDecimal day30) {
/* 377 */     this.day30 = day30;
/*     */   }
/*     */   public BigDecimal getDay31() {
/* 380 */     return this.day31;
/*     */   }
/*     */   public void setDay31(BigDecimal day31) {
/* 383 */     this.day31 = day31;
/*     */   }
/*     */   public BigDecimal getDay32() {
/* 386 */     return this.day32;
/*     */   }
/*     */   public void setDay32(BigDecimal day32) {
/* 389 */     this.day32 = day32;
/*     */   }
/*     */   public BigDecimal getDay33() {
/* 392 */     return this.day33;
/*     */   }
/*     */   public void setDay33(BigDecimal day33) {
/* 395 */     this.day33 = day33;
/*     */   }
/*     */   public BigDecimal getDay34() {
/* 398 */     return this.day34;
/*     */   }
/*     */   public void setDay34(BigDecimal day34) {
/* 401 */     this.day34 = day34;
/*     */   }
/*     */   public BigDecimal getDay35() {
/* 404 */     return this.day35;
/*     */   }
/*     */   public void setDay35(BigDecimal day35) {
/* 407 */     this.day35 = day35;
/*     */   }
/*     */   public BigDecimal getDay36() {
/* 410 */     return this.day36;
/*     */   }
/*     */   public void setDay36(BigDecimal day36) {
/* 413 */     this.day36 = day36;
/*     */   }
/*     */   public BigDecimal getDay37() {
/* 416 */     return this.day37;
/*     */   }
/*     */   public void setDay37(BigDecimal day37) {
/* 419 */     this.day37 = day37;
/*     */   }
/*     */   public BigDecimal getDay38() {
/* 422 */     return this.day38;
/*     */   }
/*     */   public void setDay38(BigDecimal day38) {
/* 425 */     this.day38 = day38;
/*     */   }
/*     */   public BigDecimal getDay39() {
/* 428 */     return this.day39;
/*     */   }
/*     */   public void setDay39(BigDecimal day39) {
/* 431 */     this.day39 = day39;
/*     */   }
/*     */   public BigDecimal getDay40() {
/* 434 */     return this.day40;
/*     */   }
/*     */   public void setDay40(BigDecimal day40) {
/* 437 */     this.day40 = day40;
/*     */   }
/*     */   public BigDecimal getDay41() {
/* 440 */     return this.day41;
/*     */   }
/*     */   public void setDay41(BigDecimal day41) {
/* 443 */     this.day41 = day41;
/*     */   }
/*     */   public BigDecimal getDay42() {
/* 446 */     return this.day42;
/*     */   }
/*     */   public void setDay42(BigDecimal day42) {
/* 449 */     this.day42 = day42;
/*     */   }
/*     */   public BigDecimal getDay43() {
/* 452 */     return this.day43;
/*     */   }
/*     */   public void setDay43(BigDecimal day43) {
/* 455 */     this.day43 = day43;
/*     */   }
/*     */   public BigDecimal getDay44() {
/* 458 */     return this.day44;
/*     */   }
/*     */   public void setDay44(BigDecimal day44) {
/* 461 */     this.day44 = day44;
/*     */   }
/*     */   public BigDecimal getDay45() {
/* 464 */     return this.day45;
/*     */   }
/*     */   public void setDay45(BigDecimal day45) {
/* 467 */     this.day45 = day45;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractProductionDayPlanHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */