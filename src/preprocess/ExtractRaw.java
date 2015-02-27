package preprocess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by hx312 on 26/02/2015.
 */
public class ExtractRaw {
           /**
     * Get the data structure which maps the position in file to raw monitoring code.
     * There might be multiple specs in single file, as a result, there might be
     * more than one raw monitoring block in one spec file.
     * @param rawSpecFile which may contain multiple spec.
     * @return The map between the position and the monitoring code.
     * @throws IOException If the monitoring code is not extracted successfully.
     */
    private static HashMap<Integer,String> getRawMonitoringCode(String rawSpecFile) throws IOException {
        HashMap<Integer,String> map = new HashMap<>();
        int offset = 0;

        while (offset <= rawSpecFile.length()){
            int balanceOfParenthesis = 0;

            int indexOfRawKeyword = rawSpecFile.indexOf("raw:", offset);
            if (indexOfRawKeyword == -1)
                return map;

            offset = indexOfRawKeyword + 4;
            String rawCodeUtilLast = rawSpecFile.substring(offset);
            for (int j = 0; j < rawCodeUtilLast.length(); j++, offset++) {
                char curC = rawCodeUtilLast.charAt(j);
                if (curC == '{')
                    balanceOfParenthesis++;
                else if (curC == '}')
                    balanceOfParenthesis--;
                else {
                }

                if (balanceOfParenthesis == -1) {
                    map.put(indexOfRawKeyword, rawCodeUtilLast.substring(0, j));
                    break;
                }
            }

            if (offset >= rawSpecFile.length()) {
                if (balanceOfParenthesis == -1) {
                    return map;
                }
                else {throw new IOException("Unexpected end of file while reading" +
                        " raw monitoring code.");}
            }
        }

        return map;
    }
   


    public static void main(String[] args) throws IOException {
        String rvmSpec = new String(Files.readAllBytes(Paths.get("test/HasNext.rvm")));
        HashMap<Integer,String> rawCodes = getRawMonitoringCode(rvmSpec);
        for (Integer pos : rawCodes.keySet()) {
            String rawCode = rawCodes.get(pos);
            System.out.println("Pos "+pos+";\nRaw Code:\n"+rawCode);
            rvmSpec = rvmSpec.replace("raw:"+rawCode, "");
        }
        System.out.println("After removing raw monitoring code:\n");
        System.out.println(rvmSpec);
    }
}
