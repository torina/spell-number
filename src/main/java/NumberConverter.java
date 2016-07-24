import java.util.Arrays;
import java.util.List;

public class NumberConverter {

    private StringBuilder sb = new StringBuilder();
    private static String[] SINGLE_AND_TEEN = new String[]{
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", " nineteen"
    };

    static private final String[] DECADES = new String[]{
            "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };
    private static List<String> CLASSES = Arrays.asList("hundred", "thousand", "million", "billion");

    public static String toText(String number) {
        String result = new String();
        if (number.length() >= 3) {
            result = splitBig(number);
        } else if (number.length() == 2) {
            result = splitDecade(number);
        } else {
            result = getSpelling(number);
        }

        return result;
    }

    private static String splitBig(String number) {
        System.out.println("\nGot number: " + number);
        StringBuilder sb = new StringBuilder();

        if (number.length() == 3) {
            return splitHundred(number);
        }
        int notInClass = (number.length()) % 3;

        String classOfNums = new String();
        int k = 0;

        for (int i = number.length(); i > 2; i -= 3) {
            k++;
            classOfNums = String.valueOf(number.subSequence(i - 3, i));
            sb.insert(0, splitHundred(classOfNums));
            if (k > 0 && (number.charAt(i - 4) != '0' || number.charAt(i - 4) != '0' || number.charAt(i - 5) != '0'))
                sb.insert(0, CLASSES.get(k) + " ");



        }
        if (notInClass == 2) {
            sb.insert(0, splitDecade(String.valueOf(number.subSequence(0, notInClass))));
        } else if (notInClass == 1) {

            sb.insert(0, getSpelling(String.valueOf(number.charAt(0))));
        }
        return sb.toString();
    }

    private static String splitHundred(String number) {
        if (number.length() != 3) throw new IllegalArgumentException("Something went wrong with hundreds splitting");

        StringBuilder sb = new StringBuilder();
        sb.append(getSpelling(String.valueOf(number.charAt(0))) + " ");
        if (number.charAt(0) != '0') sb.append(CLASSES.get(0) + " and ");
        sb.append(splitDecade(number.substring(1, 3)));
        return sb.toString();
    }

    private static String splitDecade(String number) {
        if (number.length() != 2) throw new IllegalArgumentException("Something went wrong with decades splitting");
        StringBuilder sb = new StringBuilder();
        if (number.charAt(0) == '1') {
            return getSpelling(number);
        }
        if (number.charAt(0) == '0') {
//            sb.append(" and ");
            sb.append(getSpelling(String.valueOf(number.charAt(1))));
            return sb.toString();
        }

        //getting number of 20+: 2,3,4
        sb.append(DECADES[Integer.valueOf(String.valueOf(number.charAt(0))) - 2]);
        sb.append(getSpelling(String.valueOf(number.charAt(1))));
        return sb.toString();
    }

    private static String getSpelling(String number) {
        // 0-9 in the end.
        return SINGLE_AND_TEEN[Integer.valueOf(number)] + " ";
    }

    public NumberConverter() {

    }


}
