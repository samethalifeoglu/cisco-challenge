package util;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil {

    public static String createRandomPostText() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}
