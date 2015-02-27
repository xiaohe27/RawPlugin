package preprocess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by hx312 on 26/02/2015.
 */
public class ExtractRaw {
    public static String getRawMonitoringCode(String rawSpec) throws IOException {
        String rawCodeUtilLast = rawSpec.substring(rawSpec.indexOf("raw:") + 4);
        int balanceOfParenthesis = 0, i = 0;
        for (; i < rawCodeUtilLast.length(); i++) {
            char curC = rawCodeUtilLast.charAt(i);
            if (curC == '{')
                balanceOfParenthesis++;
            else if (curC == '}')
                balanceOfParenthesis--;
            else {}

            if (balanceOfParenthesis == -1) {
                return rawCodeUtilLast.substring(0,i);
            }
        }
        throw new IOException("Unexpected end of file while extracting raw monitoring code.");
    }

    public static void main(String[] args) throws IOException {
        String rvmSpec = new String(Files.readAllBytes(Paths.get("HasNext.rvm")));
        System.out.println("The raw code is :");
        System.out.println(getRawMonitoringCode(rvmSpec));
    }
}
