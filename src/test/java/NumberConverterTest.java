import java.util.stream.IntStream;

/**
 * Created by Administrator on 7/24/2016.
 */
public class NumberConverterTest {
    public void testSingle() {

        IntStream.range(1111111111, 1111111113).forEach(
                i -> System.out.println(NumberConverter.toText(String.valueOf(i))));
    }

    public static void main(String[] args) {
        NumberConverterTest test = new NumberConverterTest();
        test.testSingle();
    }
}
