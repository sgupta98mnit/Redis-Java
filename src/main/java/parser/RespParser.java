package parser;

import org.apache.commons.lang3.StringUtils;
import redis.RedisClientContext;

import java.io.BufferedReader;
import java.io.IOException;

public class RespParser {
    public static void parse(BufferedReader reader, RedisClientContext context) throws IOException {
        try {
            String line = reader.readLine();
            System.out.println("RespParser line: " + line);
            if(line == null || !StringUtils.startsWith(line, "*")) {
                throw new IllegalArgumentException("Invalid input");
            }

            int length = Integer.parseInt(StringUtils.substring(line, 1));
            for(int i = 0; i < length; ++i) {
                //Might need to add error handling later
                line = reader.readLine();
                System.out.println("RespParser line: " + line);
                int argumentLength = Integer.parseInt(line.substring(1));
                String value = reader.readLine();
                System.out.println("RespParser value: " + value);
                if(i == 0) {
                    context.setCommand(value);
                } else if (i == 1) {
                    context.setKey(value);
                } else if (i == 2) {
                    context.setValue(value);
                } else if(i == 3){
                    if(StringUtils.equalsIgnoreCase(value, "px")) {
                        // Expiry length. Can skip. Not needed
                        reader.readLine();
                        long expiry = Long.parseLong(reader.readLine());
                        System.out.println("RespParser expiry: " + expiry);
                        context.setExpiry(expiry);
                        break;
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            throw e;
        }

    }
}
