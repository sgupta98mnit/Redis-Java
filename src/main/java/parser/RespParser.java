package parser;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class RespParser {
    public static String[] parse(BufferedReader reader) throws IOException {
        try {
            String line = reader.readLine();
            System.out.println("RespParser line: " + line);
            if(line == null || !StringUtils.startsWith(line, "*")) {
                throw new IllegalArgumentException("Invalid input");
            }

            int length = Integer.parseInt(StringUtils.substring(line, 1));
            String[] args = new String[length];
            for(int i = 0; i < length; ++i) {
                //Might need to add error handling later
                line = reader.readLine();
                System.out.println("RespParser line: " + line);
                int argumentLength = Integer.parseInt(line.substring(1));
                args[i] = reader.readLine();
                System.out.println("RespParser args: " + args[i]);
            }
            return args;
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            throw e;
        }

    }
}
