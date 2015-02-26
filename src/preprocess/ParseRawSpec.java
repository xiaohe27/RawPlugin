package preprocess;

import com.runtimeverification.rvmonitor.core.ast.Event;
import regex.Raw_Syntax;

import java.util.HashMap;
import java.util.regex.Matcher;

public class ParseRawSpec {

    private HashMap<String,String> eventNameToActionCode;
    private HashMap<String,Event> eventNameToEventAST;

    private String rawSpec;

    public ParseRawSpec(String rawSpec) {
        this.eventNameToActionCode = new HashMap<>();
        this.eventNameToEventAST = new HashMap<>();
        this.rawSpec = rawSpec;

        this.parseRawSpec();
    }

    private void parseRawSpec() {
        Matcher matcher = Raw_Syntax.condCodePair.matcher(rawSpec);

        while (matcher.find()) {
            String cond = matcher.group(1);
            String code = matcher.group(2);

            System.out.println("Cond is " + cond);
            System.out.println("Code is " + code);
        }
    }


    public HashMap<String, String> getEventNameToActionCode() {
        return eventNameToActionCode;
    }

    public HashMap<String, Event> getEventNameToEventAST() {
        return eventNameToEventAST;
    }

    public static void main(String[] args) {
        String input = "method1(String a1, int b2) \\/ m2 (Integer i1) \\/ t3() {//this is the code for all the 3 methods}";
        input += "w1() {} w2 \\/ c3() {haha}";

        // write your code here
        input = "m(int a) <| g{f} |>";
        ParseRawSpec rawParser = new ParseRawSpec(input);




    }
}
