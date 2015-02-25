package preprocess;

import com.runtimeverification.rvmonitor.core.ast.Event;

import java.util.HashMap;

public class ParseRawSpec {

    private HashMap<String,String> eventNameToActionCode;
    private HashMap<String,Event> eventNameToEventAST;

    public ParseRawSpec() {
        this.eventNameToActionCode = new HashMap<>();
        this.eventNameToEventAST = new HashMap<>();
    }

    public void parseRawSpec(String rawSpec) {
        
    }

    public static void main(String[] args) {
        // write your code here
    }
}
