package ge.tbc.itacademy.util;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Util {

        public static Number parseToNumericGeneral(String numStr) {
        if (numStr == null) {
            throw new IllegalArgumentException("null value was received");
        }
        try {
            if (!numStr.contains(".")) {
                return Integer.parseInt(numStr.replaceAll("[^0-9]", ""));
            } else {
                return Float.parseFloat(numStr.replaceAll("[^0-9.]", ""));
            }
        } catch (NumberFormatException e) {
            System.out.println("--------------EXCEPTION---------------");
            throw e;
        }
    }
    public static Float parseToNumeric(String numStr) {
        if (numStr == null) {
            throw new IllegalArgumentException("null value was received");
        }
        return Float.parseFloat(numStr.replaceAll("[^0-9.]", ""));
    }

    public static Boolean isInRange(String offerPrice, String minPrice, String maxPrice) {
        Float price =parseToNumeric(offerPrice);
        Float min = parseToNumeric(minPrice);
        Float max = parseToNumeric(maxPrice);
        return ((price.compareTo(min)>=0 ) && (price.compareTo(max)<=0));
    }

    public static String[] generatePrices() {
        Random random = new Random();
        int num1 = random.nextInt(1001);
        int num2 = random.nextInt(1001);
        return new String[]{String.valueOf(Math.min(num1, num2)), String.valueOf(Math.max(num1, num2))};
    }

    public static boolean isNotFull(String percentageValue) {
        Double numericValue = Double.parseDouble(percentageValue.replaceAll("[^\\d.]", ""));
        return numericValue.compareTo(100d) < 0;
    }

    public static boolean isEmpty(String percentageValue) {
        Double numericValue = Double.parseDouble(percentageValue.replaceAll("[^\\d.]", ""));
        return numericValue.compareTo(0d) == 0;
    }

    public static ChromeOptions myOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("safebrowsing.enabled", false);
        options.setExperimentalOption("prefs", preferences);
        return options;
    }
    // Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, Util.myOptions());
}
