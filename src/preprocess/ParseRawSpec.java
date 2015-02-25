package preprocess;

import com.runtimeverification.rvmonitor.core.ast.Event;

import java.util.HashMap;

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
        input = "m(int a){g}";
        ParseRawSpec rawParser = new ParseRawSpec(input);




    }
}
