package util;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil {

    public static String createRandomPostMessage() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}
