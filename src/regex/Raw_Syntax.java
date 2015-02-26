package regex;

import java.util.regex.Pattern;

/**
 * Created by xiaohe on 2/25/15.
 */
public class Raw_Syntax {
    public static final byte lp = (byte) '{';
    public static final byte rp = (byte) '}';


    private static String id_str = "\\p{Alpha}\\p{Alnum}*";
    private static String typedField_str = id_str + "\\s+" + id_str;

    private static Pattern id = Pattern.compile(id_str);
    private static Pattern typedField = Pattern.compile(typedField_str);
    private static Pattern argList = Pattern.compile("(" + typedField_str + "(\\s*,\\s*" + typedField_str + ")*" + ")?");

    public static boolean isId(String str) {
        return id.matcher(str).matches();
    }

    public static boolean isTypedField(String str) {
        return typedField.matcher(str).matches();
    }

    public static boolean isArgList(String str) {
        return argList.matcher(str).matches();
    }


    public static void main(String[] args) {
        System.out.println(isId("String"));
        System.out.println(isId("int"));
        System.out.println(isTypedField("String     st"));
        String argList = "String  x, int      y";
        System.out.println(argList + " is argList? " + isArgList(argList));
    }

}
