/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AbstractProduction72HourPlanHistory implements Serializable {
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
/*     */   public AbstractProduction72HourPlanHistory() {}
/*     */   
/*     */   public AbstractProduction72HourPlanHistory(Integer id) {
/* 106 */     this.id = id;
/*     */   }
/*     */   public Integer getId() {
/* 109 */     return this.id;
/*     */   }
/*     */   public void setId(Integer id) {
/* 112 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getVersion() {
/* 116 */     return this.version;
/*     */   }
/*     */   public void setVersion(String version) {
/* 119 */     this.version = version;
/*     */   }
/*     */   public Date getCreatdate() {
/* 122 */     return this.creatdate;
/*     */   }
/*     */   public void setCreatdate(Date creatdate) {
/* 125 */     this.creatdate = creatdate;
/*     */   }
/*     */   public Integer getCreattime() {
/* 128 */     return this.creattime;
/*     */   }
/*     */   public void setCreattime(Integer creattime) {
/* 131 */     this.creattime = creattime;
/*     */   }
/*     */   public String getCarkind() {
/* 134 */     return this.carkind;
/*     */   }
/*     */   public void setCarkind(String carkind) {
/* 137 */     this.carkind = carkind;
/*     */   }
/*     */   public String getAsn() {
/* 140 */     return this.asn;
/*     */   }
/*     */   public void setAsn(String asn) {
/* 143 */     this.asn = asn;
/*     */   }
/*     */   public BigDecimal getM1() {
/* 146 */     return this.M1;
/*     */   }
/*     */   public void setM1(BigDecimal m1) {
/* 149 */     this.M1 = m1;
/*     */   }
/*     */   public BigDecimal getMouth() {
/* 152 */     return this.mouth;
/*     */   }
/*     */   public void setMouth(BigDecimal mouth) {
/* 155 */     this.mouth = mouth;
/*     */   }
/*     */   public BigDecimal getD1() {
/* 158 */     return this.D1;
/*     */   }
/*     */   public void setD1(BigDecimal d1) {
/* 161 */     this.D1 = d1;
/*     */   }
/*     */   public BigDecimal getPbs() {
/* 164 */     return this.pbs;
/*     */   }
/*     */   public void setPbs(BigDecimal pbs) {
/* 167 */     this.pbs = pbs;
/*     */   }
/*     */   public BigDecimal getPrj() {
/* 170 */     return this.prj;
/*     */   }
/*     */   public void setPrj(BigDecimal prj) {
/* 173 */     this.prj = prj;
/*     */   }
/*     */   public BigDecimal getWbs() {
/* 176 */     return this.wbs;
/*     */   }
/*     */   public void setWbs(BigDecimal wbs) {
/* 179 */     this.wbs = wbs;
/*     */   }
/*     */   public BigDecimal getSum1() {
/* 182 */     return this.sum1;
/*     */   }
/*     */   public void setSum1(BigDecimal sum1) {
/* 185 */     this.sum1 = sum1;
/*     */   }
/*     */   public BigDecimal getHourplan_day1() {
/* 188 */     return this.hourplan_day1;
/*     */   }
/*     */   public void setHourplan_day1(BigDecimal hourplan_day1) {
/* 191 */     this.hourplan_day1 = hourplan_day1;
/*     */   }
/*     */   public BigDecimal getHourplan_day2() {
/* 194 */     return this.hourplan_day2;
/*     */   }
/*     */   public void setHourplan_day2(BigDecimal hourplan_day2) {
/* 197 */     this.hourplan_day2 = hourplan_day2;
/*     */   }
/*     */   public BigDecimal getHourplan_day3() {
/* 200 */     return this.hourplan_day3;
/*     */   }
/*     */   public void setHourplan_day3(BigDecimal hourplan_day3) {
/* 203 */     this.hourplan_day3 = hourplan_day3;
/*     */   }
/*     */   public BigDecimal getHourplan_day4() {
/* 206 */     return this.hourplan_day4;
/*     */   }
/*     */   public void setHourplan_day4(BigDecimal hourplan_day4) {
/* 209 */     this.hourplan_day4 = hourplan_day4;
/*     */   }
/*     */   public BigDecimal getHourplan_day5() {
/* 212 */     return this.hourplan_day5;
/*     */   }
/*     */   public void setHourplan_day5(BigDecimal hourplan_day5) {
/* 215 */     this.hourplan_day5 = hourplan_day5;
/*     */   }
/*     */   public BigDecimal getHourplan_day6() {
/* 218 */     return this.hourplan_day6;
/*     */   }
/*     */   public void setHourplan_day6(BigDecimal hourplan_day6) {
/* 221 */     this.hourplan_day6 = hourplan_day6;
/*     */   }
/*     */   public BigDecimal getHourplan_day7() {
/* 224 */     return this.hourplan_day7;
/*     */   }
/*     */   public void setHourplan_day7(BigDecimal hourplan_day7) {
/* 227 */     this.hourplan_day7 = hourplan_day7;
/*     */   }
/*     */   public BigDecimal getHourplan_day8() {
/* 230 */     return this.hourplan_day8;
/*     */   }
/*     */   public void setHourplan_day8(BigDecimal hourplan_day8) {
/* 233 */     this.hourplan_day8 = hourplan_day8;
/*     */   }
/*     */   public BigDecimal getHourplan_day9() {
/* 236 */     return this.hourplan_day9;
/*     */   }
/*     */   public void setHourplan_day9(BigDecimal hourplan_day9) {
/* 239 */     this.hourplan_day9 = hourplan_day9;
/*     */   }
/*     */   public BigDecimal getHourplan_day10() {
/* 242 */     return this.hourplan_day10;
/*     */   }
/*     */   public void setHourplan_day10(BigDecimal hourplan_day10) {
/* 245 */     this.hourplan_day10 = hourplan_day10;
/*     */   }
/*     */   public BigDecimal getHourplan_day11() {
/* 248 */     return this.hourplan_day11;
/*     */   }
/*     */   public void setHourplan_day11(BigDecimal hourplan_day11) {
/* 251 */     this.hourplan_day11 = hourplan_day11;
/*     */   }
/*     */   public BigDecimal getHourplan_day12() {
/* 254 */     return this.hourplan_day12;
/*     */   }
/*     */   public void setHourplan_day12(BigDecimal hourplan_day12) {
/* 257 */     this.hourplan_day12 = hourplan_day12;
/*     */   }
/*     */   public BigDecimal getHourplan_day13() {
/* 260 */     return this.hourplan_day13;
/*     */   }
/*     */   public void setHourplan_day13(BigDecimal hourplan_day13) {
/* 263 */     this.hourplan_day13 = hourplan_day13;
/*     */   }
/*     */   public BigDecimal getHourplan_day14() {
/* 266 */     return this.hourplan_day14;
/*     */   }
/*     */   public void setHourplan_day14(BigDecimal hourplan_day14) {
/* 269 */     this.hourplan_day14 = hourplan_day14;
/*     */   }
/*     */   public BigDecimal getHourplan_day15() {
/* 272 */     return this.hourplan_day15;
/*     */   }
/*     */   public void setHourplan_day15(BigDecimal hourplan_day15) {
/* 275 */     this.hourplan_day15 = hourplan_day15;
/*     */   }
/*     */   public BigDecimal getHourplan_day16() {
/* 278 */     return this.hourplan_day16;
/*     */   }
/*     */   public void setHourplan_day16(BigDecimal hourplan_day16) {
/* 281 */     this.hourplan_day16 = hourplan_day16;
/*     */   }
/*     */   public BigDecimal getHourplan_day17() {
/* 284 */     return this.hourplan_day17;
/*     */   }
/*     */   public void setHourplan_day17(BigDecimal hourplan_day17) {
/* 287 */     this.hourplan_day17 = hourplan_day17;
/*     */   }
/*     */   public BigDecimal getHourplan_day18() {
/* 290 */     return this.hourplan_day18;
/*     */   }
/*     */   public void setHourplan_day18(BigDecimal hourplan_day18) {
/* 293 */     this.hourplan_day18 = hourplan_day18;
/*     */   }
/*     */   public BigDecimal getHourplan_day19() {
/* 296 */     return this.hourplan_day19;
/*     */   }
/*     */   public void setHourplan_day19(BigDecimal hourplan_day19) {
/* 299 */     this.hourplan_day19 = hourplan_day19;
/*     */   }
/*     */   public BigDecimal getHourplan_day20() {
/* 302 */     return this.hourplan_day20;
/*     */   }
/*     */   public void setHourplan_day20(BigDecimal hourplan_day20) {
/* 305 */     this.hourplan_day20 = hourplan_day20;
/*     */   }
/*     */   public BigDecimal getHourplan_day21() {
/* 308 */     return this.hourplan_day21;
/*     */   }
/*     */   public void setHourplan_day21(BigDecimal hourplan_day21) {
/* 311 */     this.hourplan_day21 = hourplan_day21;
/*     */   }
/*     */   public BigDecimal getHourplan_day22() {
/* 314 */     return this.hourplan_day22;
/*     */   }
/*     */   public void setHourplan_day22(BigDecimal hourplan_day22) {
/* 317 */     this.hourplan_day22 = hourplan_day22;
/*     */   }
/*     */   public BigDecimal getHourplan_day23() {
/* 320 */     return this.hourplan_day23;
/*     */   }
/*     */   public void setHourplan_day23(BigDecimal hourplan_day23) {
/* 323 */     this.hourplan_day23 = hourplan_day23;
/*     */   }
/*     */   public BigDecimal getHourplan_day24() {
/* 326 */     return this.hourplan_day24;
/*     */   }
/*     */   public void setHourplan_day24(BigDecimal hourplan_day24) {
/* 329 */     this.hourplan_day24 = hourplan_day24;
/*     */   }
/*     */   public BigDecimal getHourplan_day25() {
/* 332 */     return this.hourplan_day25;
/*     */   }
/*     */   public void setHourplan_day25(BigDecimal hourplan_day25) {
/* 335 */     this.hourplan_day25 = hourplan_day25;
/*     */   }
/*     */   public BigDecimal getHourplan_day26() {
/* 338 */     return this.hourplan_day26;
/*     */   }
/*     */   public void setHourplan_day26(BigDecimal hourplan_day26) {
/* 341 */     this.hourplan_day26 = hourplan_day26;
/*     */   }
/*     */   public BigDecimal getHourplan_day27() {
/* 344 */     return this.hourplan_day27;
/*     */   }
/*     */   public void setHourplan_day27(BigDecimal hourplan_day27) {
/* 347 */     this.hourplan_day27 = hourplan_day27;
/*     */   }
/*     */   public BigDecimal getHourplan_day28() {
/* 350 */     return this.hourplan_day28;
/*     */   }
/*     */   public void setHourplan_day28(BigDecimal hourplan_day28) {
/* 353 */     this.hourplan_day28 = hourplan_day28;
/*     */   }
/*     */   public BigDecimal getHourplan_day29() {
/* 356 */     return this.hourplan_day29;
/*     */   }
/*     */   public void setHourplan_day29(BigDecimal hourplan_day29) {
/* 359 */     this.hourplan_day29 = hourplan_day29;
/*     */   }
/*     */   public BigDecimal getHourplan_day30() {
/* 362 */     return this.hourplan_day30;
/*     */   }
/*     */   public void setHourplan_day30(BigDecimal hourplan_day30) {
/* 365 */     this.hourplan_day30 = hourplan_day30;
/*     */   }
/*     */   public BigDecimal getHourplan_day31() {
/* 368 */     return this.hourplan_day31;
/*     */   }
/*     */   public void setHourplan_day31(BigDecimal hourplan_day31) {
/* 371 */     this.hourplan_day31 = hourplan_day31;
/*     */   }
/*     */   public BigDecimal getHourplan_day32() {
/* 374 */     return this.hourplan_day32;
/*     */   }
/*     */   public void setHourplan_day32(BigDecimal hourplan_day32) {
/* 377 */     this.hourplan_day32 = hourplan_day32;
/*     */   }
/*     */   public BigDecimal getHourplan_day33() {
/* 380 */     return this.hourplan_day33;
/*     */   }
/*     */   public void setHourplan_day33(BigDecimal hourplan_day33) {
/* 383 */     this.hourplan_day33 = hourplan_day33;
/*     */   }
/*     */   public BigDecimal getHourplan_day34() {
/* 386 */     return this.hourplan_day34;
/*     */   }
/*     */   public void setHourplan_day34(BigDecimal hourplan_day34) {
/* 389 */     this.hourplan_day34 = hourplan_day34;
/*     */   }
/*     */   public BigDecimal getHourplan_day35() {
/* 392 */     return this.hourplan_day35;
/*     */   }
/*     */   public void setHourplan_day35(BigDecimal hourplan_day35) {
/* 395 */     this.hourplan_day35 = hourplan_day35;
/*     */   }
/*     */   public BigDecimal getHourplan_day36() {
/* 398 */     return this.hourplan_day36;
/*     */   }
/*     */   public void setHourplan_day36(BigDecimal hourplan_day36) {
/* 401 */     this.hourplan_day36 = hourplan_day36;
/*     */   }
/*     */   public BigDecimal getHourplan_day37() {
/* 404 */     return this.hourplan_day37;
/*     */   }
/*     */   public void setHourplan_day37(BigDecimal hourplan_day37) {
/* 407 */     this.hourplan_day37 = hourplan_day37;
/*     */   }
/*     */   public BigDecimal getHourplan_day38() {
/* 410 */     return this.hourplan_day38;
/*     */   }
/*     */   public void setHourplan_day38(BigDecimal hourplan_day38) {
/* 413 */     this.hourplan_day38 = hourplan_day38;
/*     */   }
/*     */   public BigDecimal getHourplan_day39() {
/* 416 */     return this.hourplan_day39;
/*     */   }
/*     */   public void setHourplan_day39(BigDecimal hourplan_day39) {
/* 419 */     this.hourplan_day39 = hourplan_day39;
/*     */   }
/*     */   public BigDecimal getHourplan_day40() {
/* 422 */     return this.hourplan_day40;
/*     */   }
/*     */   public void setHourplan_day40(BigDecimal hourplan_day40) {
/* 425 */     this.hourplan_day40 = hourplan_day40;
/*     */   }
/*     */   public BigDecimal getHourplan_day41() {
/* 428 */     return this.hourplan_day41;
/*     */   }
/*     */   public void setHourplan_day41(BigDecimal hourplan_day41) {
/* 431 */     this.hourplan_day41 = hourplan_day41;
/*     */   }
/*     */   public BigDecimal getHourplan_day42() {
/* 434 */     return this.hourplan_day42;
/*     */   }
/*     */   public void setHourplan_day42(BigDecimal hourplan_day42) {
/* 437 */     this.hourplan_day42 = hourplan_day42;
/*     */   }
/*     */   public BigDecimal getHourplan_day43() {
/* 440 */     return this.hourplan_day43;
/*     */   }
/*     */   public void setHourplan_day43(BigDecimal hourplan_day43) {
/* 443 */     this.hourplan_day43 = hourplan_day43;
/*     */   }
/*     */   public BigDecimal getHourplan_day44() {
/* 446 */     return this.hourplan_day44;
/*     */   }
/*     */   public void setHourplan_day44(BigDecimal hourplan_day44) {
/* 449 */     this.hourplan_day44 = hourplan_day44;
/*     */   }
/*     */   public BigDecimal getHourplan_day45() {
/* 452 */     return this.hourplan_day45;
/*     */   }
/*     */   public void setHourplan_day45(BigDecimal hourplan_day45) {
/* 455 */     this.hourplan_day45 = hourplan_day45;
/*     */   }
/*     */   public BigDecimal getHourplan_day46() {
/* 458 */     return this.hourplan_day46;
/*     */   }
/*     */   public void setHourplan_day46(BigDecimal hourplan_day46) {
/* 461 */     this.hourplan_day46 = hourplan_day46;
/*     */   }
/*     */   public BigDecimal getHourplan_day47() {
/* 464 */     return this.hourplan_day47;
/*     */   }
/*     */   public void setHourplan_day47(BigDecimal hourplan_day47) {
/* 467 */     this.hourplan_day47 = hourplan_day47;
/*     */   }
/*     */   public BigDecimal getHourplan_day48() {
/* 470 */     return this.hourplan_day48;
/*     */   }
/*     */   public void setHourplan_day48(BigDecimal hourplan_day48) {
/* 473 */     this.hourplan_day48 = hourplan_day48;
/*     */   }
/*     */   public BigDecimal getHourplan_day49() {
/* 476 */     return this.hourplan_day49;
/*     */   }
/*     */   public void setHourplan_day49(BigDecimal hourplan_day49) {
/* 479 */     this.hourplan_day49 = hourplan_day49;
/*     */   }
/*     */   public BigDecimal getHourplan_day50() {
/* 482 */     return this.hourplan_day50;
/*     */   }
/*     */   public void setHourplan_day50(BigDecimal hourplan_day50) {
/* 485 */     this.hourplan_day50 = hourplan_day50;
/*     */   }
/*     */   public BigDecimal getHourplan_day51() {
/* 488 */     return this.hourplan_day51;
/*     */   }
/*     */   public void setHourplan_day51(BigDecimal hourplan_day51) {
/* 491 */     this.hourplan_day51 = hourplan_day51;
/*     */   }
/*     */   public BigDecimal getHourplan_day52() {
/* 494 */     return this.hourplan_day52;
/*     */   }
/*     */   public void setHourplan_day52(BigDecimal hourplan_day52) {
/* 497 */     this.hourplan_day52 = hourplan_day52;
/*     */   }
/*     */   public BigDecimal getHourplan_day53() {
/* 500 */     return this.hourplan_day53;
/*     */   }
/*     */   public void setHourplan_day53(BigDecimal hourplan_day53) {
/* 503 */     this.hourplan_day53 = hourplan_day53;
/*     */   }
/*     */   public BigDecimal getHourplan_day54() {
/* 506 */     return this.hourplan_day54;
/*     */   }
/*     */   public void setHourplan_day54(BigDecimal hourplan_day54) {
/* 509 */     this.hourplan_day54 = hourplan_day54;
/*     */   }
/*     */   public BigDecimal getHourplan_day55() {
/* 512 */     return this.hourplan_day55;
/*     */   }
/*     */   public void setHourplan_day55(BigDecimal hourplan_day55) {
/* 515 */     this.hourplan_day55 = hourplan_day55;
/*     */   }
/*     */   public BigDecimal getHourplan_day56() {
/* 518 */     return this.hourplan_day56;
/*     */   }
/*     */   public void setHourplan_day56(BigDecimal hourplan_day56) {
/* 521 */     this.hourplan_day56 = hourplan_day56;
/*     */   }
/*     */   public BigDecimal getHourplan_day57() {
/* 524 */     return this.hourplan_day57;
/*     */   }
/*     */   public void setHourplan_day57(BigDecimal hourplan_day57) {
/* 527 */     this.hourplan_day57 = hourplan_day57;
/*     */   }
/*     */   public BigDecimal getHourplan_day58() {
/* 530 */     return this.hourplan_day58;
/*     */   }
/*     */   public void setHourplan_day58(BigDecimal hourplan_day58) {
/* 533 */     this.hourplan_day58 = hourplan_day58;
/*     */   }
/*     */   public BigDecimal getHourplan_day59() {
/* 536 */     return this.hourplan_day59;
/*     */   }
/*     */   public void setHourplan_day59(BigDecimal hourplan_day59) {
/* 539 */     this.hourplan_day59 = hourplan_day59;
/*     */   }
/*     */   public BigDecimal getHourplan_day60() {
/* 542 */     return this.hourplan_day60;
/*     */   }
/*     */   public void setHourplan_day60(BigDecimal hourplan_day60) {
/* 545 */     this.hourplan_day60 = hourplan_day60;
/*     */   }
/*     */   public BigDecimal getHourplan_day61() {
/* 548 */     return this.hourplan_day61;
/*     */   }
/*     */   public void setHourplan_day61(BigDecimal hourplan_day61) {
/* 551 */     this.hourplan_day61 = hourplan_day61;
/*     */   }
/*     */   public BigDecimal getHourplan_day62() {
/* 554 */     return this.hourplan_day62;
/*     */   }
/*     */   public void setHourplan_day62(BigDecimal hourplan_day62) {
/* 557 */     this.hourplan_day62 = hourplan_day62;
/*     */   }
/*     */   public BigDecimal getHourplan_day63() {
/* 560 */     return this.hourplan_day63;
/*     */   }
/*     */   public void setHourplan_day63(BigDecimal hourplan_day63) {
/* 563 */     this.hourplan_day63 = hourplan_day63;
/*     */   }
/*     */   public BigDecimal getHourplan_day64() {
/* 566 */     return this.hourplan_day64;
/*     */   }
/*     */   public void setHourplan_day64(BigDecimal hourplan_day64) {
/* 569 */     this.hourplan_day64 = hourplan_day64;
/*     */   }
/*     */   public BigDecimal getHourplan_day65() {
/* 572 */     return this.hourplan_day65;
/*     */   }
/*     */   public void setHourplan_day65(BigDecimal hourplan_day65) {
/* 575 */     this.hourplan_day65 = hourplan_day65;
/*     */   }
/*     */   public BigDecimal getRem() {
/* 578 */     return this.rem;
/*     */   }
/*     */   public void setRem(BigDecimal rem) {
/* 581 */     this.rem = rem;
/*     */   }
/*     */   public BigDecimal getSum() {
/* 584 */     return this.sum;
/*     */   }
/*     */   public void setSum(BigDecimal sum) {
/* 587 */     this.sum = sum;
/*     */   }
/*     */   public BigDecimal getWeitou() {
/* 590 */     return this.weitou;
/*     */   }
/*     */   public void setWeitou(BigDecimal weitou) {
/* 593 */     this.weitou = weitou;
/*     */   }
/*     */   public BigDecimal getLco() {
/* 596 */     return this.lco;
/*     */   }
/*     */   public void setLco(BigDecimal lco) {
/* 599 */     this.lco = lco;
/*     */   }
/*     */   public BigDecimal getLcx() {
/* 602 */     return this.lcx;
/*     */   }
/*     */   public void setLcx(BigDecimal lcx) {
/* 605 */     this.lcx = lcx;
/*     */   }
/*     */   
/*     */   public BigDecimal getHourAll() {
/* 609 */     return this.hourAll;
/*     */   }
/*     */   public void setHourAll(BigDecimal hourAll) {
/* 612 */     this.hourAll = hourAll;
/*     */   }
/*     */   public Date getXxqad_hourdate() {
/* 615 */     return this.xxqad_hourdate;
/*     */   }
/*     */   public void setXxqad_hourdate(Date xxqad_hourdate) {
/* 618 */     this.xxqad_hourdate = xxqad_hourdate;
/*     */   }
/*     */   public String getQadread() {
/* 621 */     return this.qadread;
/*     */   }
/*     */   public void setQadread(String qadread) {
/* 624 */     this.qadread = qadread;
/*     */   }
/*     */   public String getMesread() {
/* 627 */     return this.mesread;
/*     */   }
/*     */   public void setMesread(String mesread) {
/* 630 */     this.mesread = mesread;
/*     */   }
/*     */   public String getPortalread() {
/* 633 */     return this.portalread;
/*     */   }
/*     */   public void setPortalread(String portalread) {
/* 636 */     this.portalread = portalread;
/*     */   }
/*     */   public String getEdiread() {
/* 639 */     return this.ediread;
/*     */   }
/*     */   public void setEdiread(String ediread) {
/* 642 */     this.ediread = ediread;
/*     */   }
/*     */   public String getRmks() {
/* 645 */     return this.rmks;
/*     */   }
/*     */   public void setRmks(String rmks) {
/* 648 */     this.rmks = rmks;
/*     */   }
/*     */   public Date getCreatedt() {
/* 651 */     return this.createdt;
/*     */   }
/*     */   public void setCreatedt(Date createdt) {
/* 654 */     this.createdt = createdt;
/*     */   }
/*     */   public String getCreateur() {
/* 657 */     return this.createur;
/*     */   }
/*     */   public void setCreateur(String createur) {
/* 660 */     this.createur = createur;
/*     */   }
/*     */   public Date getUpdatedt() {
/* 663 */     return this.updatedt;
/*     */   }
/*     */   public void setUpdatedt(Date updatedt) {
/* 666 */     this.updatedt = updatedt;
/*     */   }
/*     */   public Date getUpdateur() {
/* 669 */     return this.updateur;
/*     */   }
/*     */   public void setUpdateur(Date updateur) {
/* 672 */     this.updateur = updateur;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractProduction72HourPlanHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */