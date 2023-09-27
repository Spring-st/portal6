/*     */ package com.aof.utils;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class ColorUtils
/*     */ {
/*  20 */   private static final Color DEFAULT_COLOR = new Color(0, 0, 0);
/*     */   
/*  22 */   private static Map colorMap = new HashMap<Object, Object>();
/*     */   
/*     */   static {
/*  25 */     colorMap.put("aliceblue", "#F0F8FF");
/*  26 */     colorMap.put("antiquewhite", "#FAEBD7");
/*  27 */     colorMap.put("aqua", "#00FFFF");
/*  28 */     colorMap.put("aquamarine", "#7FFFD4");
/*  29 */     colorMap.put("azure", "#F0FFFF");
/*  30 */     colorMap.put("beige", "#F5F5DC");
/*  31 */     colorMap.put("bisque", "#FFE4C4");
/*  32 */     colorMap.put("black", "#000000");
/*  33 */     colorMap.put("blanchedalmond", "#FFEBCD");
/*  34 */     colorMap.put("blue", "#0000FF");
/*  35 */     colorMap.put("blueviolet", "#8A2BE2");
/*  36 */     colorMap.put("brown", "#A52A2A");
/*  37 */     colorMap.put("burlywood", "#DEB887");
/*  38 */     colorMap.put("cadetblue", "#5F9EA0");
/*  39 */     colorMap.put("chartreuse", "#7FFF00");
/*  40 */     colorMap.put("chocolate", "#D2691E");
/*  41 */     colorMap.put("coral", "#FF7F50");
/*  42 */     colorMap.put("cornflowerblue", "#6495ED");
/*  43 */     colorMap.put("cornsilk", "#FFF8DC");
/*  44 */     colorMap.put("crimson", "#DC143C");
/*  45 */     colorMap.put("cyan", "#00FFFF");
/*  46 */     colorMap.put("darkblue", "#00008B");
/*  47 */     colorMap.put("darkcyan", "#008B8B");
/*  48 */     colorMap.put("darkgoldenrod", "#B8860B");
/*  49 */     colorMap.put("darkgray", "#A9A9A9");
/*  50 */     colorMap.put("darkgreen", "#006400");
/*  51 */     colorMap.put("darkkhaki", "#BDB76B");
/*  52 */     colorMap.put("darkmagenta", "#8B008B");
/*  53 */     colorMap.put("darkolivegreen", "#556B2F");
/*  54 */     colorMap.put("darkorange", "#FF8C00");
/*  55 */     colorMap.put("darkorchid", "#9932CC");
/*  56 */     colorMap.put("darkred", "#8B0000");
/*  57 */     colorMap.put("darksalmon", "#E9967A");
/*  58 */     colorMap.put("darkseagreen", "#8FBC8B");
/*  59 */     colorMap.put("darkslateblue", "#483D8B");
/*  60 */     colorMap.put("darkslategray", "#2F4F4F");
/*  61 */     colorMap.put("darkturquoise", "#00CED1");
/*  62 */     colorMap.put("darkviolet", "#9400D3");
/*  63 */     colorMap.put("deeppink", "#FF1493");
/*  64 */     colorMap.put("deepskyblue", "#00BFFF");
/*  65 */     colorMap.put("dimgray", "#696969");
/*  66 */     colorMap.put("dodgerblue", "#1E90FF");
/*  67 */     colorMap.put("firebrick", "#B22222");
/*  68 */     colorMap.put("floralwhite", "#FFFAF0");
/*  69 */     colorMap.put("forestgreen", "#228B22");
/*  70 */     colorMap.put("fuchsia", "#FF00FF");
/*  71 */     colorMap.put("gainsboro", "#DCDCDC");
/*  72 */     colorMap.put("ghostwhite", "#F8F8FF");
/*  73 */     colorMap.put("gold", "#FFD700");
/*  74 */     colorMap.put("goldenrod", "#DAA520");
/*  75 */     colorMap.put("gray", "#808080");
/*  76 */     colorMap.put("green", "#008000");
/*  77 */     colorMap.put("greenyellow", "#ADFF2F");
/*  78 */     colorMap.put("honeydew", "#F0FFF0");
/*  79 */     colorMap.put("hotpink", "#FF69B4");
/*  80 */     colorMap.put("indianred", "#CD5C5C");
/*  81 */     colorMap.put("indigo", "#4B0082");
/*  82 */     colorMap.put("ivory", "#FFFFF0");
/*  83 */     colorMap.put("khaki", "#F0E68C");
/*  84 */     colorMap.put("lavender", "#E6E6FA");
/*  85 */     colorMap.put("lavenderblush", "#FFF0F5");
/*  86 */     colorMap.put("lawngreen", "#7CFC00");
/*  87 */     colorMap.put("lemonchiffon", "#FFFACD");
/*  88 */     colorMap.put("lightblue", "#ADD8E6");
/*  89 */     colorMap.put("lightcoral", "#F08080");
/*  90 */     colorMap.put("lightcyan", "#E0FFFF");
/*  91 */     colorMap.put("lightgoldenrodyellow", "#FAFAD2");
/*  92 */     colorMap.put("lightgreen", "#90EE90");
/*  93 */     colorMap.put("lightgrey", "#D3D3D3");
/*  94 */     colorMap.put("lightpink", "#FFB6C1");
/*  95 */     colorMap.put("lightsalmon", "#FFA07A");
/*  96 */     colorMap.put("lightseagreen", "#20B2AA");
/*  97 */     colorMap.put("lightskyblue", "#87CEFA");
/*  98 */     colorMap.put("lightslategray", "#778899");
/*  99 */     colorMap.put("lightsteelblue", "#B0C4DE");
/* 100 */     colorMap.put("lightyellow", "#FFFFE0");
/* 101 */     colorMap.put("lime", "#00FF00");
/* 102 */     colorMap.put("limegreen", "#32CD32");
/* 103 */     colorMap.put("linen", "#FAF0E6");
/* 104 */     colorMap.put("magenta", "#FF00FF");
/* 105 */     colorMap.put("maroon", "#800000");
/* 106 */     colorMap.put("mediumaquamarine", "#66CDAA");
/* 107 */     colorMap.put("mediumblue", "#0000CD");
/* 108 */     colorMap.put("mediumorchid", "#BA55D3");
/* 109 */     colorMap.put("mediumpurple", "#9370DB");
/* 110 */     colorMap.put("mediumseagreen", "#3CB371");
/* 111 */     colorMap.put("mediumslateblue", "#7B68EE");
/* 112 */     colorMap.put("mediumspringgreen", "#00FA9A");
/* 113 */     colorMap.put("mediumturquoise", "#48D1CC");
/* 114 */     colorMap.put("mediumvioletred", "#C71585");
/* 115 */     colorMap.put("midnightblue", "#191970");
/* 116 */     colorMap.put("mintcream", "#F5FFFA");
/* 117 */     colorMap.put("mistyrose", "#FFE4E");
/* 118 */     colorMap.put("moccasin", "#FFE4B5");
/* 119 */     colorMap.put("navajowhite", "#FFDEAD");
/* 120 */     colorMap.put("navy", "#000080");
/* 121 */     colorMap.put("oldlace", "#FDF5E6");
/* 122 */     colorMap.put("olive", "#808000");
/* 123 */     colorMap.put("olivedrab", "#6B8E23");
/* 124 */     colorMap.put("orange", "#FFA500");
/* 125 */     colorMap.put("orangered", "#FF4500");
/* 126 */     colorMap.put("orchid", "#DA70D6");
/* 127 */     colorMap.put("palegoldenrod", "#EEE8AA");
/* 128 */     colorMap.put("palegreen", "#98FB98");
/* 129 */     colorMap.put("paleturquoise", "#AFEEEE");
/* 130 */     colorMap.put("palevioletred", "#DB7093");
/* 131 */     colorMap.put("papayawhip", "#FFEFD5");
/* 132 */     colorMap.put("peachpuff", "#FFDAB9");
/* 133 */     colorMap.put("peru", "#CD853F");
/* 134 */     colorMap.put("pink", "#FFC0CB");
/* 135 */     colorMap.put("plum", "#DDA0DD");
/* 136 */     colorMap.put("powderblue", "#B0E0E6");
/* 137 */     colorMap.put("purple", "#800080");
/* 138 */     colorMap.put("red", "#FF0000");
/* 139 */     colorMap.put("rosybrown", "#BC8F8F");
/* 140 */     colorMap.put("royalblue", "#4169E1");
/* 141 */     colorMap.put("saddlebrown", "#8B4513");
/* 142 */     colorMap.put("salmon", "#FA8072");
/* 143 */     colorMap.put("sandybrown", "#F4A460");
/* 144 */     colorMap.put("seagreen", "#2E8B57");
/* 145 */     colorMap.put("seashell", "#FFF5EE");
/* 146 */     colorMap.put("sienna", "#A0522D");
/* 147 */     colorMap.put("silver", "#C0C0C0");
/* 148 */     colorMap.put("skyblue", "#87CEEB");
/* 149 */     colorMap.put("slateblue", "#6A5ACD");
/* 150 */     colorMap.put("slategray", "#708090");
/* 151 */     colorMap.put("snow", "#FFFAFA");
/* 152 */     colorMap.put("springgreen", "#00FF7F");
/* 153 */     colorMap.put("steelblue", "#4682B4");
/* 154 */     colorMap.put("tan", "#D2B48C");
/* 155 */     colorMap.put("teal", "#008080");
/* 156 */     colorMap.put("thistle", "#D8BFD8");
/* 157 */     colorMap.put("tomato", "#FF6347");
/* 158 */     colorMap.put("turquoise", "#40E0D0");
/* 159 */     colorMap.put("violet", "#EE82EE");
/* 160 */     colorMap.put("wheat", "#F5DEB3");
/* 161 */     colorMap.put("white", "#FFFFFF");
/* 162 */     colorMap.put("whitesmoke", "#F5F5F5");
/* 163 */     colorMap.put("yellow", "#FFFF00");
/* 164 */     colorMap.put("yellowgreen", "#9ACD32");
/*     */   }
/*     */   
/*     */   public static Color getColor(String strColor) {
/* 168 */     if (strColor == null) return DEFAULT_COLOR; 
/* 169 */     if (strColor.length() < 1) return DEFAULT_COLOR; 
/* 170 */     if (strColor.charAt(0) == '#' && strColor.length() == 7) {
/* 171 */       return new Color(getR(strColor), getG(strColor), getB(strColor));
/*     */     }
/* 173 */     String strConvertColor = (String)colorMap.get(strColor);
/* 174 */     if (strConvertColor != null) {
/* 175 */       return new Color(getR(strConvertColor), getG(strConvertColor), getB(strConvertColor));
/*     */     }
/* 177 */     return DEFAULT_COLOR;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getR(String color) {
/* 183 */     return getIntColor(color.substring(1, 3));
/*     */   }
/*     */   
/*     */   private static int getG(String color) {
/* 187 */     return getIntColor(color.substring(3, 5));
/*     */   }
/*     */   
/*     */   private static int getB(String color) {
/* 191 */     return getIntColor(color.substring(5, 7));
/*     */   }
/*     */   
/*     */   private static int getIntColor(String c) {
/* 195 */     return Integer.parseInt(c, 16);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/utils/ColorUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */