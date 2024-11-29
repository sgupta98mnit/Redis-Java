package utility;

public class RedisUtility {
    public static String transform(String response) {
        return "$" + response.length() + "\r\n" + response + "\r\n";
    }
}
