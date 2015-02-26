package preprocess;

import com.runtimeverification.rvmonitor.core.ast.Event;
import regex.Raw_Syntax;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class ParseRawSpec {

    private HashMap<String,String> eventNameToActionCode;
    private HashMap<String,Event> eventNameToEventAST;

    private String rawSpec;
    private ByteBuffer byteBuffer;

    private int balance = 0;
    //balance 0 means that the num of { and } are equivalent.
    //balance greater than 0 means inside some block
    //balance less than 0 means ERROR!

    public ParseRawSpec(String rawSpec) throws IOException {
        this.eventNameToActionCode = new HashMap<>();
        this.eventNameToEventAST = new HashMap<>();
        this.rawSpec = rawSpec;

        this.byteBuffer = ByteBuffer.allocate(this.rawSpec.length());
        this.byteBuffer.put(this.rawSpec.getBytes());
        this.byteBuffer.flip();

        this.parseRawSpec();
    }

    private void parseRawSpec() throws IOException {
        String condPart = null;
        String codePart = null;


        while (this.byteBuffer.hasRemaining()) {
            condPart = this.getCondPart();

            System.out.println("Cond is "+condPart);

            codePart = this.getCodePart();

            System.out.println("Code is "+codePart);
        }

    }

    private String getCodePart() throws IOException {
        StringBuilder sb = new StringBuilder();

        while (this.byteBuffer.hasRemaining()) {
            byte b = this.byteBuffer.get();

            if (b == Raw_Syntax.lp) {
                balance++;
            } else if (b == Raw_Syntax.rp) {
                balance--;
                if (balance == 0) {
                    return sb.toString();
                }
            }   else {
                sb.append((char)b);
            }

        }

        throw new IOException("Unexpected end of input while reading the event action method");
    }

    private String getCondPart() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (this.byteBuffer.hasRemaining()) {
            byte b = this.byteBuffer.get();

            if (b == Raw_Syntax.lp) {
                balance++;
                return sb.toString();
            }   else {
                sb.append((char)b);
            }

        }

        throw new IOException("Unexpected end of input while reading the condition");
    }


    public HashMap<String, String> getEventNameToActionCode() {
        return eventNameToActionCode;
    }

    public HashMap<String, Event> getEventNameToEventAST() {
        return eventNameToEventAST;
    }

    public static void main(String[] args) throws IOException {
        String input = "method1(String a1, int b2) \\/ m2 (Integer i1) \\/ t3() {//this is the code for all the 3 methods}";
        input += "w1() {} w2 \\/ c3() {haha}";

        // write your code here
        ParseRawSpec rawParser = new ParseRawSpec(input);




    }
}
