package com.aof.utils;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorUtils {
    private static final Color DEFAULT_COLOR = new Color(0, 0, 0);

    private static Map colorMap = new HashMap<Object, Object>();

    static {
        colorMap.put("aliceblue", "#F0F8FF");
        colorMap.put("antiquewhite", "#FAEBD7");
        colorMap.put("aqua", "#00FFFF");
        colorMap.put("aquamarine", "#7FFFD4");
        colorMap.put("azure", "#F0FFFF");
        colorMap.put("beige", "#F5F5DC");
        colorMap.put("bisque", "#FFE4C4");
        colorMap.put("black", "#000000");
        colorMap.put("blanchedalmond", "#FFEBCD");
        colorMap.put("blue", "#0000FF");
        colorMap.put("blueviolet", "#8A2BE2");
        colorMap.put("brown", "#A52A2A");
        colorMap.put("burlywood", "#DEB887");
        colorMap.put("cadetblue", "#5F9EA0");
        colorMap.put("chartreuse", "#7FFF00");
        colorMap.put("chocolate", "#D2691E");
        colorMap.put("coral", "#FF7F50");
        colorMap.put("cornflowerblue", "#6495ED");
        colorMap.put("cornsilk", "#FFF8DC");
        colorMap.put("crimson", "#DC143C");
        colorMap.put("cyan", "#00FFFF");
        colorMap.put("darkblue", "#00008B");
        colorMap.put("darkcyan", "#008B8B");
        colorMap.put("darkgoldenrod", "#B8860B");
        colorMap.put("darkgray", "#A9A9A9");
        colorMap.put("darkgreen", "#006400");
        colorMap.put("darkkhaki", "#BDB76B");
        colorMap.put("darkmagenta", "#8B008B");
        colorMap.put("darkolivegreen", "#556B2F");
        colorMap.put("darkorange", "#FF8C00");
        colorMap.put("darkorchid", "#9932CC");
        colorMap.put("darkred", "#8B0000");
        colorMap.put("darksalmon", "#E9967A");
        colorMap.put("darkseagreen", "#8FBC8B");
        colorMap.put("darkslateblue", "#483D8B");
        colorMap.put("darkslategray", "#2F4F4F");
        colorMap.put("darkturquoise", "#00CED1");
        colorMap.put("darkviolet", "#9400D3");
        colorMap.put("deeppink", "#FF1493");
        colorMap.put("deepskyblue", "#00BFFF");
        colorMap.put("dimgray", "#696969");
        colorMap.put("dodgerblue", "#1E90FF");
        colorMap.put("firebrick", "#B22222");
        colorMap.put("floralwhite", "#FFFAF0");
        colorMap.put("forestgreen", "#228B22");
        colorMap.put("fuchsia", "#FF00FF");
        colorMap.put("gainsboro", "#DCDCDC");
        colorMap.put("ghostwhite", "#F8F8FF");
        colorMap.put("gold", "#FFD700");
        colorMap.put("goldenrod", "#DAA520");
        colorMap.put("gray", "#808080");
        colorMap.put("green", "#008000");
        colorMap.put("greenyellow", "#ADFF2F");
        colorMap.put("honeydew", "#F0FFF0");
        colorMap.put("hotpink", "#FF69B4");
        colorMap.put("indianred", "#CD5C5C");
        colorMap.put("indigo", "#4B0082");
        colorMap.put("ivory", "#FFFFF0");
        colorMap.put("khaki", "#F0E68C");
        colorMap.put("lavender", "#E6E6FA");
        colorMap.put("lavenderblush", "#FFF0F5");
        colorMap.put("lawngreen", "#7CFC00");
        colorMap.put("lemonchiffon", "#FFFACD");
        colorMap.put("lightblue", "#ADD8E6");
        colorMap.put("lightcoral", "#F08080");
        colorMap.put("lightcyan", "#E0FFFF");
        colorMap.put("lightgoldenrodyellow", "#FAFAD2");
        colorMap.put("lightgreen", "#90EE90");
        colorMap.put("lightgrey", "#D3D3D3");
        colorMap.put("lightpink", "#FFB6C1");
        colorMap.put("lightsalmon", "#FFA07A");
        colorMap.put("lightseagreen", "#20B2AA");
        colorMap.put("lightskyblue", "#87CEFA");
        colorMap.put("lightslategray", "#778899");
        colorMap.put("lightsteelblue", "#B0C4DE");
        colorMap.put("lightyellow", "#FFFFE0");
        colorMap.put("lime", "#00FF00");
        colorMap.put("limegreen", "#32CD32");
        colorMap.put("linen", "#FAF0E6");
        colorMap.put("magenta", "#FF00FF");
        colorMap.put("maroon", "#800000");
        colorMap.put("mediumaquamarine", "#66CDAA");
        colorMap.put("mediumblue", "#0000CD");
        colorMap.put("mediumorchid", "#BA55D3");
        colorMap.put("mediumpurple", "#9370DB");
        colorMap.put("mediumseagreen", "#3CB371");
        colorMap.put("mediumslateblue", "#7B68EE");
        colorMap.put("mediumspringgreen", "#00FA9A");
        colorMap.put("mediumturquoise", "#48D1CC");
        colorMap.put("mediumvioletred", "#C71585");
        colorMap.put("midnightblue", "#191970");
        colorMap.put("mintcream", "#F5FFFA");
        colorMap.put("mistyrose", "#FFE4E");
        colorMap.put("moccasin", "#FFE4B5");
        colorMap.put("navajowhite", "#FFDEAD");
        colorMap.put("navy", "#000080");
        colorMap.put("oldlace", "#FDF5E6");
        colorMap.put("olive", "#808000");
        colorMap.put("olivedrab", "#6B8E23");
        colorMap.put("orange", "#FFA500");
        colorMap.put("orangered", "#FF4500");
        colorMap.put("orchid", "#DA70D6");
        colorMap.put("palegoldenrod", "#EEE8AA");
        colorMap.put("palegreen", "#98FB98");
        colorMap.put("paleturquoise", "#AFEEEE");
        colorMap.put("palevioletred", "#DB7093");
        colorMap.put("papayawhip", "#FFEFD5");
        colorMap.put("peachpuff", "#FFDAB9");
        colorMap.put("peru", "#CD853F");
        colorMap.put("pink", "#FFC0CB");
        colorMap.put("plum", "#DDA0DD");
        colorMap.put("powderblue", "#B0E0E6");
        colorMap.put("purple", "#800080");
        colorMap.put("red", "#FF0000");
        colorMap.put("rosybrown", "#BC8F8F");
        colorMap.put("royalblue", "#4169E1");
        colorMap.put("saddlebrown", "#8B4513");
        colorMap.put("salmon", "#FA8072");
        colorMap.put("sandybrown", "#F4A460");
        colorMap.put("seagreen", "#2E8B57");
        colorMap.put("seashell", "#FFF5EE");
        colorMap.put("sienna", "#A0522D");
        colorMap.put("silver", "#C0C0C0");
        colorMap.put("skyblue", "#87CEEB");
        colorMap.put("slateblue", "#6A5ACD");
        colorMap.put("slategray", "#708090");
        colorMap.put("snow", "#FFFAFA");
        colorMap.put("springgreen", "#00FF7F");
        colorMap.put("steelblue", "#4682B4");
        colorMap.put("tan", "#D2B48C");
        colorMap.put("teal", "#008080");
        colorMap.put("thistle", "#D8BFD8");
        colorMap.put("tomato", "#FF6347");
        colorMap.put("turquoise", "#40E0D0");
        colorMap.put("violet", "#EE82EE");
        colorMap.put("wheat", "#F5DEB3");
        colorMap.put("white", "#FFFFFF");
        colorMap.put("whitesmoke", "#F5F5F5");
        colorMap.put("yellow", "#FFFF00");
        colorMap.put("yellowgreen", "#9ACD32");
    }

    public static Color getColor(String strColor) {
        if (strColor == null)
            return DEFAULT_COLOR;
        if (strColor.length() < 1)
            return DEFAULT_COLOR;
        if (strColor.charAt(0) == '#' && strColor.length() == 7)
            return new Color(getR(strColor), getG(strColor), getB(strColor));
        String strConvertColor = (String)colorMap.get(strColor);
        if (strConvertColor != null)
            return new Color(getR(strConvertColor), getG(strConvertColor), getB(strConvertColor));
        return DEFAULT_COLOR;
    }

    private static int getR(String color) {
        return getIntColor(color.substring(1, 3));
    }

    private static int getG(String color) {
        return getIntColor(color.substring(3, 5));
    }

    private static int getB(String color) {
        return getIntColor(color.substring(5, 7));
    }

    private static int getIntColor(String c) {
        return Integer.parseInt(c, 16);
    }
}
