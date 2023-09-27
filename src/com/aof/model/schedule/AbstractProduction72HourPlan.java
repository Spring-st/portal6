/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AbstractProduction72HourPlan
/*     */   implements Serializable {
/*     */   private Integer id;
/*     */   private String version;
/*     */   private Date creatdate;
/*     */   private Integer creattime;
/*     */   private String carkind;
/*     */   private String asn;
/*     */   private BigDecimal M1;
/*     */   private BigDecimal mouth;
/*     */   private BigDecimal D1;
/*     */   private BigDecimal pbs;
/*     */   private BigDecimal prj;
/*     */   private BigDecimal wbs;
/*     */   private BigDecimal sum1;
/*     */   private BigDecimal hourplan_day1;
/*     */   private BigDecimal hourplan_day2;
/*     */   private BigDecimal hourplan_day3;
/*     */   private BigDecimal hourplan_day4;
/*     */   private BigDecimal hourplan_day5;
/*     */   private BigDecimal hourplan_day6;
/*     */   private BigDecimal hourplan_day7;
/*     */   private BigDecimal hourplan_day8;
/*     */   private BigDecimal hourplan_day9;
/*     */   private BigDecimal hourplan_day10;
/*     */   private BigDecimal hourplan_day11;
/*     */   private BigDecimal hourplan_day12;
/*     */   private BigDecimal hourplan_day13;
/*     */   private BigDecimal hourplan_day14;
/*     */   private BigDecimal hourplan_day15;
/*     */   private BigDecimal hourplan_day16;
/*     */   private BigDecimal hourplan_day17;
/*     */   private BigDecimal hourplan_day18;
/*     */   private BigDecimal hourplan_day19;
/*     */   private BigDecimal hourplan_day20;
/*     */   private BigDecimal hourplan_day21;
/*     */   private BigDecimal hourplan_day22;
/*     */   private BigDecimal hourplan_day23;
/*     */   private BigDecimal hourplan_day24;
/*     */   private BigDecimal hourplan_day25;
/*     */   private BigDecimal hourplan_day26;
/*     */   private BigDecimal hourplan_day27;
/*     */   private BigDecimal hourplan_day28;
/*     */   private BigDecimal hourplan_day29;
/*     */   private BigDecimal hourplan_day30;
/*     */   private BigDecimal hourplan_day31;
/*     */   private BigDecimal hourplan_day32;
/*     */   private BigDecimal hourplan_day33;
/*     */   private BigDecimal hourplan_day34;
/*     */   private BigDecimal hourplan_day35;
/*     */   private BigDecimal hourplan_day36;
/*     */   private BigDecimal hourplan_day37;
/*     */   private BigDecimal hourplan_day38;
/*     */   private BigDecimal hourplan_day39;
/*     */   private BigDecimal hourplan_day40;
/*     */   private BigDecimal hourplan_day41;
/*     */   private BigDecimal hourplan_day42;
/*     */   private BigDecimal hourplan_day43;
/*     */   private BigDecimal hourplan_day44;
/*     */   private BigDecimal hourplan_day45;
/*     */   private BigDecimal hourplan_day46;
/*     */   private BigDecimal hourplan_day47;
/*     */   private BigDecimal hourplan_day48;
/*     */   private BigDecimal hourplan_day49;
/*     */   private BigDecimal hourplan_day50;
/*     */   private BigDecimal hourplan_day51;
/*     */   private BigDecimal hourplan_day52;
/*     */   private BigDecimal hourplan_day53;
/*     */   private BigDecimal hourplan_day54;
/*     */   private BigDecimal hourplan_day55;
/*     */   private BigDecimal hourplan_day56;
/*     */   private BigDecimal hourplan_day57;
/*     */   private BigDecimal hourplan_day58;
/*     */   private BigDecimal hourplan_day59;
/*     */   private BigDecimal hourplan_day60;
/*     */   private BigDecimal hourplan_day61;
/*     */   private BigDecimal hourplan_day62;
/*     */   private BigDecimal hourplan_day63;
/*     */   private BigDecimal hourplan_day64;
/*     */   private BigDecimal hourplan_day65;
/*     */   private BigDecimal rem;
/*     */   private BigDecimal sum;
/*     */   private BigDecimal weitou;
/*     */   private BigDecimal lco;
/*     */   private BigDecimal lcx;
/*     */   private BigDecimal hourAll;
/*     */   private Date xxqad_hourdate;
/*     */   private String qadread;
/*     */   private String mesread;
/*     */   private String portalread;
/*     */   private String ediread;
/*     */   private String rmks;
/*     */   private Date createdt;
/*     */   private String createur;
/*     */   private Date updatedt;
/*     */   private Date updateur;
/*     */   
/*     */   public AbstractProduction72HourPlan() {}
/*     */   
/*     */   public AbstractProduction72HourPlan(Integer id) {
/* 107 */     this.id = id;
/*     */   }
/*     */   public Integer getId() {
/* 110 */     return this.id;
/*     */   }
/*     */   public void setId(Integer id) {
/* 113 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getVersion() {
/* 117 */     return this.version;
/*     */   }
/*     */   public void setVersion(String version) {
/* 120 */     this.version = version;
/*     */   }
/*     */   public Date getCreatdate() {
/* 123 */     return this.creatdate;
/*     */   }
/*     */   public void setCreatdate(Date creatdate) {
/* 126 */     this.creatdate = creatdate;
/*     */   }
/*     */   public Integer getCreattime() {
/* 129 */     return this.creattime;
/*     */   }
/*     */   public void setCreattime(Integer creattime) {
/* 132 */     this.creattime = creattime;
/*     */   }
/*     */   public String getCarkind() {
/* 135 */     return this.carkind;
/*     */   }
/*     */   public void setCarkind(String carkind) {
/* 138 */     this.carkind = carkind;
/*     */   }
/*     */   public String getAsn() {
/* 141 */     return this.asn;
/*     */   }
/*     */   public void setAsn(String asn) {
/* 144 */     this.asn = asn;
/*     */   }
/*     */   public BigDecimal getM1() {
/* 147 */     return this.M1;
/*     */   }
/*     */   public void setM1(BigDecimal m1) {
/* 150 */     this.M1 = m1;
/*     */   }
/*     */   public BigDecimal getMouth() {
/* 153 */     return this.mouth;
/*     */   }
/*     */   public void setMouth(BigDecimal mouth) {
/* 156 */     this.mouth = mouth;
/*     */   }
/*     */   public BigDecimal getD1() {
/* 159 */     return this.D1;
/*     */   }
/*     */   public void setD1(BigDecimal d1) {
/* 162 */     this.D1 = d1;
/*     */   }
/*     */   public BigDecimal getPbs() {
/* 165 */     return this.pbs;
/*     */   }
/*     */   public void setPbs(BigDecimal pbs) {
/* 168 */     this.pbs = pbs;
/*     */   }
/*     */   public BigDecimal getPrj() {
/* 171 */     return this.prj;
/*     */   }
/*     */   public void setPrj(BigDecimal prj) {
/* 174 */     this.prj = prj;
/*     */   }
/*     */   public BigDecimal getWbs() {
/* 177 */     return this.wbs;
/*     */   }
/*     */   public void setWbs(BigDecimal wbs) {
/* 180 */     this.wbs = wbs;
/*     */   }
/*     */   public BigDecimal getSum1() {
/* 183 */     return this.sum1;
/*     */   }
/*     */   public void setSum1(BigDecimal sum1) {
/* 186 */     this.sum1 = sum1;
/*     */   }
/*     */   public BigDecimal getHourplan_day1() {
/* 189 */     return this.hourplan_day1;
/*     */   }
/*     */   public void setHourplan_day1(BigDecimal hourplan_day1) {
/* 192 */     this.hourplan_day1 = hourplan_day1;
/*     */   }
/*     */   public BigDecimal getHourplan_day2() {
/* 195 */     return this.hourplan_day2;
/*     */   }
/*     */   public void setHourplan_day2(BigDecimal hourplan_day2) {
/* 198 */     this.hourplan_day2 = hourplan_day2;
/*     */   }
/*     */   public BigDecimal getHourplan_day3() {
/* 201 */     return this.hourplan_day3;
/*     */   }
/*     */   public void setHourplan_day3(BigDecimal hourplan_day3) {
/* 204 */     this.hourplan_day3 = hourplan_day3;
/*     */   }
/*     */   public BigDecimal getHourplan_day4() {
/* 207 */     return this.hourplan_day4;
/*     */   }
/*     */   public void setHourplan_day4(BigDecimal hourplan_day4) {
/* 210 */     this.hourplan_day4 = hourplan_day4;
/*     */   }
/*     */   public BigDecimal getHourplan_day5() {
/* 213 */     return this.hourplan_day5;
/*     */   }
/*     */   public void setHourplan_day5(BigDecimal hourplan_day5) {
/* 216 */     this.hourplan_day5 = hourplan_day5;
/*     */   }
/*     */   public BigDecimal getHourplan_day6() {
/* 219 */     return this.hourplan_day6;
/*     */   }
/*     */   public void setHourplan_day6(BigDecimal hourplan_day6) {
/* 222 */     this.hourplan_day6 = hourplan_day6;
/*     */   }
/*     */   public BigDecimal getHourplan_day7() {
/* 225 */     return this.hourplan_day7;
/*     */   }
/*     */   public void setHourplan_day7(BigDecimal hourplan_day7) {
/* 228 */     this.hourplan_day7 = hourplan_day7;
/*     */   }
/*     */   public BigDecimal getHourplan_day8() {
/* 231 */     return this.hourplan_day8;
/*     */   }
/*     */   public void setHourplan_day8(BigDecimal hourplan_day8) {
/* 234 */     this.hourplan_day8 = hourplan_day8;
/*     */   }
/*     */   public BigDecimal getHourplan_day9() {
/* 237 */     return this.hourplan_day9;
/*     */   }
/*     */   public void setHourplan_day9(BigDecimal hourplan_day9) {
/* 240 */     this.hourplan_day9 = hourplan_day9;
/*     */   }
/*     */   public BigDecimal getHourplan_day10() {
/* 243 */     return this.hourplan_day10;
/*     */   }
/*     */   public void setHourplan_day10(BigDecimal hourplan_day10) {
/* 246 */     this.hourplan_day10 = hourplan_day10;
/*     */   }
/*     */   public BigDecimal getHourplan_day11() {
/* 249 */     return this.hourplan_day11;
/*     */   }
/*     */   public void setHourplan_day11(BigDecimal hourplan_day11) {
/* 252 */     this.hourplan_day11 = hourplan_day11;
/*     */   }
/*     */   public BigDecimal getHourplan_day12() {
/* 255 */     return this.hourplan_day12;
/*     */   }
/*     */   public void setHourplan_day12(BigDecimal hourplan_day12) {
/* 258 */     this.hourplan_day12 = hourplan_day12;
/*     */   }
/*     */   public BigDecimal getHourplan_day13() {
/* 261 */     return this.hourplan_day13;
/*     */   }
/*     */   public void setHourplan_day13(BigDecimal hourplan_day13) {
/* 264 */     this.hourplan_day13 = hourplan_day13;
/*     */   }
/*     */   public BigDecimal getHourplan_day14() {
/* 267 */     return this.hourplan_day14;
/*     */   }
/*     */   public void setHourplan_day14(BigDecimal hourplan_day14) {
/* 270 */     this.hourplan_day14 = hourplan_day14;
/*     */   }
/*     */   public BigDecimal getHourplan_day15() {
/* 273 */     return this.hourplan_day15;
/*     */   }
/*     */   public void setHourplan_day15(BigDecimal hourplan_day15) {
/* 276 */     this.hourplan_day15 = hourplan_day15;
/*     */   }
/*     */   public BigDecimal getHourplan_day16() {
/* 279 */     return this.hourplan_day16;
/*     */   }
/*     */   public void setHourplan_day16(BigDecimal hourplan_day16) {
/* 282 */     this.hourplan_day16 = hourplan_day16;
/*     */   }
/*     */   public BigDecimal getHourplan_day17() {
/* 285 */     return this.hourplan_day17;
/*     */   }
/*     */   public void setHourplan_day17(BigDecimal hourplan_day17) {
/* 288 */     this.hourplan_day17 = hourplan_day17;
/*     */   }
/*     */   public BigDecimal getHourplan_day18() {
/* 291 */     return this.hourplan_day18;
/*     */   }
/*     */   public void setHourplan_day18(BigDecimal hourplan_day18) {
/* 294 */     this.hourplan_day18 = hourplan_day18;
/*     */   }
/*     */   public BigDecimal getHourplan_day19() {
/* 297 */     return this.hourplan_day19;
/*     */   }
/*     */   public void setHourplan_day19(BigDecimal hourplan_day19) {
/* 300 */     this.hourplan_day19 = hourplan_day19;
/*     */   }
/*     */   public BigDecimal getHourplan_day20() {
/* 303 */     return this.hourplan_day20;
/*     */   }
/*     */   public void setHourplan_day20(BigDecimal hourplan_day20) {
/* 306 */     this.hourplan_day20 = hourplan_day20;
/*     */   }
/*     */   public BigDecimal getHourplan_day21() {
/* 309 */     return this.hourplan_day21;
/*     */   }
/*     */   public void setHourplan_day21(BigDecimal hourplan_day21) {
/* 312 */     this.hourplan_day21 = hourplan_day21;
/*     */   }
/*     */   public BigDecimal getHourplan_day22() {
/* 315 */     return this.hourplan_day22;
/*     */   }
/*     */   public void setHourplan_day22(BigDecimal hourplan_day22) {
/* 318 */     this.hourplan_day22 = hourplan_day22;
/*     */   }
/*     */   public BigDecimal getHourplan_day23() {
/* 321 */     return this.hourplan_day23;
/*     */   }
/*     */   public void setHourplan_day23(BigDecimal hourplan_day23) {
/* 324 */     this.hourplan_day23 = hourplan_day23;
/*     */   }
/*     */   public BigDecimal getHourplan_day24() {
/* 327 */     return this.hourplan_day24;
/*     */   }
/*     */   public void setHourplan_day24(BigDecimal hourplan_day24) {
/* 330 */     this.hourplan_day24 = hourplan_day24;
/*     */   }
/*     */   public BigDecimal getHourplan_day25() {
/* 333 */     return this.hourplan_day25;
/*     */   }
/*     */   public void setHourplan_day25(BigDecimal hourplan_day25) {
/* 336 */     this.hourplan_day25 = hourplan_day25;
/*     */   }
/*     */   public BigDecimal getHourplan_day26() {
/* 339 */     return this.hourplan_day26;
/*     */   }
/*     */   public void setHourplan_day26(BigDecimal hourplan_day26) {
/* 342 */     this.hourplan_day26 = hourplan_day26;
/*     */   }
/*     */   public BigDecimal getHourplan_day27() {
/* 345 */     return this.hourplan_day27;
/*     */   }
/*     */   public void setHourplan_day27(BigDecimal hourplan_day27) {
/* 348 */     this.hourplan_day27 = hourplan_day27;
/*     */   }
/*     */   public BigDecimal getHourplan_day28() {
/* 351 */     return this.hourplan_day28;
/*     */   }
/*     */   public void setHourplan_day28(BigDecimal hourplan_day28) {
/* 354 */     this.hourplan_day28 = hourplan_day28;
/*     */   }
/*     */   public BigDecimal getHourplan_day29() {
/* 357 */     return this.hourplan_day29;
/*     */   }
/*     */   public void setHourplan_day29(BigDecimal hourplan_day29) {
/* 360 */     this.hourplan_day29 = hourplan_day29;
/*     */   }
/*     */   public BigDecimal getHourplan_day30() {
/* 363 */     return this.hourplan_day30;
/*     */   }
/*     */   public void setHourplan_day30(BigDecimal hourplan_day30) {
/* 366 */     this.hourplan_day30 = hourplan_day30;
/*     */   }
/*     */   public BigDecimal getHourplan_day31() {
/* 369 */     return this.hourplan_day31;
/*     */   }
/*     */   public void setHourplan_day31(BigDecimal hourplan_day31) {
/* 372 */     this.hourplan_day31 = hourplan_day31;
/*     */   }
/*     */   public BigDecimal getHourplan_day32() {
/* 375 */     return this.hourplan_day32;
/*     */   }
/*     */   public void setHourplan_day32(BigDecimal hourplan_day32) {
/* 378 */     this.hourplan_day32 = hourplan_day32;
/*     */   }
/*     */   public BigDecimal getHourplan_day33() {
/* 381 */     return this.hourplan_day33;
/*     */   }
/*     */   public void setHourplan_day33(BigDecimal hourplan_day33) {
/* 384 */     this.hourplan_day33 = hourplan_day33;
/*     */   }
/*     */   public BigDecimal getHourplan_day34() {
/* 387 */     return this.hourplan_day34;
/*     */   }
/*     */   public void setHourplan_day34(BigDecimal hourplan_day34) {
/* 390 */     this.hourplan_day34 = hourplan_day34;
/*     */   }
/*     */   public BigDecimal getHourplan_day35() {
/* 393 */     return this.hourplan_day35;
/*     */   }
/*     */   public void setHourplan_day35(BigDecimal hourplan_day35) {
/* 396 */     this.hourplan_day35 = hourplan_day35;
/*     */   }
/*     */   public BigDecimal getHourplan_day36() {
/* 399 */     return this.hourplan_day36;
/*     */   }
/*     */   public void setHourplan_day36(BigDecimal hourplan_day36) {
/* 402 */     this.hourplan_day36 = hourplan_day36;
/*     */   }
/*     */   public BigDecimal getHourplan_day37() {
/* 405 */     return this.hourplan_day37;
/*     */   }
/*     */   public void setHourplan_day37(BigDecimal hourplan_day37) {
/* 408 */     this.hourplan_day37 = hourplan_day37;
/*     */   }
/*     */   public BigDecimal getHourplan_day38() {
/* 411 */     return this.hourplan_day38;
/*     */   }
/*     */   public void setHourplan_day38(BigDecimal hourplan_day38) {
/* 414 */     this.hourplan_day38 = hourplan_day38;
/*     */   }
/*     */   public BigDecimal getHourplan_day39() {
/* 417 */     return this.hourplan_day39;
/*     */   }
/*     */   public void setHourplan_day39(BigDecimal hourplan_day39) {
/* 420 */     this.hourplan_day39 = hourplan_day39;
/*     */   }
/*     */   public BigDecimal getHourplan_day40() {
/* 423 */     return this.hourplan_day40;
/*     */   }
/*     */   public void setHourplan_day40(BigDecimal hourplan_day40) {
/* 426 */     this.hourplan_day40 = hourplan_day40;
/*     */   }
/*     */   public BigDecimal getHourplan_day41() {
/* 429 */     return this.hourplan_day41;
/*     */   }
/*     */   public void setHourplan_day41(BigDecimal hourplan_day41) {
/* 432 */     this.hourplan_day41 = hourplan_day41;
/*     */   }
/*     */   public BigDecimal getHourplan_day42() {
/* 435 */     return this.hourplan_day42;
/*     */   }
/*     */   public void setHourplan_day42(BigDecimal hourplan_day42) {
/* 438 */     this.hourplan_day42 = hourplan_day42;
/*     */   }
/*     */   public BigDecimal getHourplan_day43() {
/* 441 */     return this.hourplan_day43;
/*     */   }
/*     */   public void setHourplan_day43(BigDecimal hourplan_day43) {
/* 444 */     this.hourplan_day43 = hourplan_day43;
/*     */   }
/*     */   public BigDecimal getHourplan_day44() {
/* 447 */     return this.hourplan_day44;
/*     */   }
/*     */   public void setHourplan_day44(BigDecimal hourplan_day44) {
/* 450 */     this.hourplan_day44 = hourplan_day44;
/*     */   }
/*     */   public BigDecimal getHourplan_day45() {
/* 453 */     return this.hourplan_day45;
/*     */   }
/*     */   public void setHourplan_day45(BigDecimal hourplan_day45) {
/* 456 */     this.hourplan_day45 = hourplan_day45;
/*     */   }
/*     */   public BigDecimal getHourplan_day46() {
/* 459 */     return this.hourplan_day46;
/*     */   }
/*     */   public void setHourplan_day46(BigDecimal hourplan_day46) {
/* 462 */     this.hourplan_day46 = hourplan_day46;
/*     */   }
/*     */   public BigDecimal getHourplan_day47() {
/* 465 */     return this.hourplan_day47;
/*     */   }
/*     */   public void setHourplan_day47(BigDecimal hourplan_day47) {
/* 468 */     this.hourplan_day47 = hourplan_day47;
/*     */   }
/*     */   public BigDecimal getHourplan_day48() {
/* 471 */     return this.hourplan_day48;
/*     */   }
/*     */   public void setHourplan_day48(BigDecimal hourplan_day48) {
/* 474 */     this.hourplan_day48 = hourplan_day48;
/*     */   }
/*     */   public BigDecimal getHourplan_day49() {
/* 477 */     return this.hourplan_day49;
/*     */   }
/*     */   public void setHourplan_day49(BigDecimal hourplan_day49) {
/* 480 */     this.hourplan_day49 = hourplan_day49;
/*     */   }
/*     */   public BigDecimal getHourplan_day50() {
/* 483 */     return this.hourplan_day50;
/*     */   }
/*     */   public void setHourplan_day50(BigDecimal hourplan_day50) {
/* 486 */     this.hourplan_day50 = hourplan_day50;
/*     */   }
/*     */   public BigDecimal getHourplan_day51() {
/* 489 */     return this.hourplan_day51;
/*     */   }
/*     */   public void setHourplan_day51(BigDecimal hourplan_day51) {
/* 492 */     this.hourplan_day51 = hourplan_day51;
/*     */   }
/*     */   public BigDecimal getHourplan_day52() {
/* 495 */     return this.hourplan_day52;
/*     */   }
/*     */   public void setHourplan_day52(BigDecimal hourplan_day52) {
/* 498 */     this.hourplan_day52 = hourplan_day52;
/*     */   }
/*     */   public BigDecimal getHourplan_day53() {
/* 501 */     return this.hourplan_day53;
/*     */   }
/*     */   public void setHourplan_day53(BigDecimal hourplan_day53) {
/* 504 */     this.hourplan_day53 = hourplan_day53;
/*     */   }
/*     */   public BigDecimal getHourplan_day54() {
/* 507 */     return this.hourplan_day54;
/*     */   }
/*     */   public void setHourplan_day54(BigDecimal hourplan_day54) {
/* 510 */     this.hourplan_day54 = hourplan_day54;
/*     */   }
/*     */   public BigDecimal getHourplan_day55() {
/* 513 */     return this.hourplan_day55;
/*     */   }
/*     */   public void setHourplan_day55(BigDecimal hourplan_day55) {
/* 516 */     this.hourplan_day55 = hourplan_day55;
/*     */   }
/*     */   public BigDecimal getHourplan_day56() {
/* 519 */     return this.hourplan_day56;
/*     */   }
/*     */   public void setHourplan_day56(BigDecimal hourplan_day56) {
/* 522 */     this.hourplan_day56 = hourplan_day56;
/*     */   }
/*     */   public BigDecimal getHourplan_day57() {
/* 525 */     return this.hourplan_day57;
/*     */   }
/*     */   public void setHourplan_day57(BigDecimal hourplan_day57) {
/* 528 */     this.hourplan_day57 = hourplan_day57;
/*     */   }
/*     */   public BigDecimal getHourplan_day58() {
/* 531 */     return this.hourplan_day58;
/*     */   }
/*     */   public void setHourplan_day58(BigDecimal hourplan_day58) {
/* 534 */     this.hourplan_day58 = hourplan_day58;
/*     */   }
/*     */   public BigDecimal getHourplan_day59() {
/* 537 */     return this.hourplan_day59;
/*     */   }
/*     */   public void setHourplan_day59(BigDecimal hourplan_day59) {
/* 540 */     this.hourplan_day59 = hourplan_day59;
/*     */   }
/*     */   public BigDecimal getHourplan_day60() {
/* 543 */     return this.hourplan_day60;
/*     */   }
/*     */   public void setHourplan_day60(BigDecimal hourplan_day60) {
/* 546 */     this.hourplan_day60 = hourplan_day60;
/*     */   }
/*     */   public BigDecimal getHourplan_day61() {
/* 549 */     return this.hourplan_day61;
/*     */   }
/*     */   public void setHourplan_day61(BigDecimal hourplan_day61) {
/* 552 */     this.hourplan_day61 = hourplan_day61;
/*     */   }
/*     */   public BigDecimal getHourplan_day62() {
/* 555 */     return this.hourplan_day62;
/*     */   }
/*     */   public void setHourplan_day62(BigDecimal hourplan_day62) {
/* 558 */     this.hourplan_day62 = hourplan_day62;
/*     */   }
/*     */   public BigDecimal getHourplan_day63() {
/* 561 */     return this.hourplan_day63;
/*     */   }
/*     */   public void setHourplan_day63(BigDecimal hourplan_day63) {
/* 564 */     this.hourplan_day63 = hourplan_day63;
/*     */   }
/*     */   public BigDecimal getHourplan_day64() {
/* 567 */     return this.hourplan_day64;
/*     */   }
/*     */   public void setHourplan_day64(BigDecimal hourplan_day64) {
/* 570 */     this.hourplan_day64 = hourplan_day64;
/*     */   }
/*     */   public BigDecimal getHourplan_day65() {
/* 573 */     return this.hourplan_day65;
/*     */   }
/*     */   public void setHourplan_day65(BigDecimal hourplan_day65) {
/* 576 */     this.hourplan_day65 = hourplan_day65;
/*     */   }
/*     */   public BigDecimal getRem() {
/* 579 */     return this.rem;
/*     */   }
/*     */   public void setRem(BigDecimal rem) {
/* 582 */     this.rem = rem;
/*     */   }
/*     */   public BigDecimal getSum() {
/* 585 */     return this.sum;
/*     */   }
/*     */   public void setSum(BigDecimal sum) {
/* 588 */     this.sum = sum;
/*     */   }
/*     */   public BigDecimal getWeitou() {
/* 591 */     return this.weitou;
/*     */   }
/*     */   public void setWeitou(BigDecimal weitou) {
/* 594 */     this.weitou = weitou;
/*     */   }
/*     */   public BigDecimal getLco() {
/* 597 */     return this.lco;
/*     */   }
/*     */   public void setLco(BigDecimal lco) {
/* 600 */     this.lco = lco;
/*     */   }
/*     */   public BigDecimal getLcx() {
/* 603 */     return this.lcx;
/*     */   }
/*     */   public void setLcx(BigDecimal lcx) {
/* 606 */     this.lcx = lcx;
/*     */   }
/*     */   
/*     */   public BigDecimal getHourAll() {
/* 610 */     return this.hourAll;
/*     */   }
/*     */   public void setHourAll(BigDecimal hourAll) {
/* 613 */     this.hourAll = hourAll;
/*     */   }
/*     */   public Date getXxqad_hourdate() {
/* 616 */     return this.xxqad_hourdate;
/*     */   }
/*     */   public void setXxqad_hourdate(Date xxqad_hourdate) {
/* 619 */     this.xxqad_hourdate = xxqad_hourdate;
/*     */   }
/*     */   public String getQadread() {
/* 622 */     return this.qadread;
/*     */   }
/*     */   public void setQadread(String qadread) {
/* 625 */     this.qadread = qadread;
/*     */   }
/*     */   public String getMesread() {
/* 628 */     return this.mesread;
/*     */   }
/*     */   public void setMesread(String mesread) {
/* 631 */     this.mesread = mesread;
/*     */   }
/*     */   public String getPortalread() {
/* 634 */     return this.portalread;
/*     */   }
/*     */   public void setPortalread(String portalread) {
/* 637 */     this.portalread = portalread;
/*     */   }
/*     */   public String getEdiread() {
/* 640 */     return this.ediread;
/*     */   }
/*     */   public void setEdiread(String ediread) {
/* 643 */     this.ediread = ediread;
/*     */   }
/*     */   public String getRmks() {
/* 646 */     return this.rmks;
/*     */   }
/*     */   public void setRmks(String rmks) {
/* 649 */     this.rmks = rmks;
/*     */   }
/*     */   public Date getCreatedt() {
/* 652 */     return this.createdt;
/*     */   }
/*     */   public void setCreatedt(Date createdt) {
/* 655 */     this.createdt = createdt;
/*     */   }
/*     */   public String getCreateur() {
/* 658 */     return this.createur;
/*     */   }
/*     */   public void setCreateur(String createur) {
/* 661 */     this.createur = createur;
/*     */   }
/*     */   public Date getUpdatedt() {
/* 664 */     return this.updatedt;
/*     */   }
/*     */   public void setUpdatedt(Date updatedt) {
/* 667 */     this.updatedt = updatedt;
/*     */   }
/*     */   public Date getUpdateur() {
/* 670 */     return this.updateur;
/*     */   }
/*     */   public void setUpdateur(Date updateur) {
/* 673 */     this.updateur = updateur;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractProduction72HourPlan.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */