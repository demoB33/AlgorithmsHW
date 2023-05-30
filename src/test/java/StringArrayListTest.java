import org.junit.jupiter.api.Test;
import org.example.ElementNotFoundException;
import org.example.InvalidArgumentException;
import org.example.StringArrayList;
import org.example.StringListIndexOutBoundsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;


public class StringArrayListTest {

    private StringArrayList stringArrayList = new StringArrayList(5);


    @BeforeEach
    public void fillList() {
        stringArrayList.add("0");
        stringArrayList.add("1");
        stringArrayList.add("2");
        stringArrayList.add("3");
        stringArrayList.add("4");
    }

    @AfterEach
    public void clearList() {
        stringArrayList.clear();
    }

    @Test
    public void addPositiveTest() {
        int size = stringArrayList.size();
        Assertions.assertEquals("5", stringArrayList.add("5"));
        Assertions.assertEquals(size + 1, stringArrayList.size());

    }

    @Test
    public void indexAddPositiveTest() {
        int size = stringArrayList.size();
        int index = 1;
        Assertions.assertEquals("5", stringArrayList.add(index, "5"));
        Assertions.assertEquals(index, stringArrayList.indexOf("5"));
        Assertions.assertEquals(size + 1, stringArrayList.size());

    }

    @Test
    public void indexAddNegativeTest() {
        Assertions.assertThrows(StringListIndexOutBoundsException.class, () -> stringArrayList.add(5, "5"));

    }

    @Test
    public void setPositiveTest() {
        int size = stringArrayList.size();
        int index = 1;
        Assertions.assertEquals("5", stringArrayList.set(index, "5"));
        Assertions.assertEquals(index, stringArrayList.indexOf("5"));
        Assertions.assertEquals(size, stringArrayList.size());
    }

    @Test
    public void setNegativeTest() {
        Assertions.assertThrows(StringListIndexOutBoundsException.class, () -> stringArrayList.add(5, "5"));

    }

    @Test
    public void removePositiveTest() {
        int size = stringArrayList.size();
        Assertions.assertEquals("1", stringArrayList.remove("1"));
        Assertions.assertEquals(size - 1, stringArrayList.size());
    }

    @Test
    public void removeNegativeTest() {
        Assertions.assertThrows(ElementNotFoundException.class, () -> stringArrayList.remove("5"));

    }

    @Test
    public void removeIndexPositiveTest() {
        int size = stringArrayList.size();
        Assertions.assertEquals("0", stringArrayList.remove(0));
        Assertions.assertEquals(size - 1, stringArrayList.size());
    }

    @Test
    public void removeIndexNegativeTest() {
        Assertions.assertThrows(StringListIndexOutBoundsException.class, () -> stringArrayList.remove(5));

    }

    public static Stream<Arguments> containsParamPositiveTest() {
        return Stream.of(
                Arguments.of("0"),
                Arguments.of("3"),
                Arguments.of("2")
        );
    }

   @ParameterizedTest
    @MethodSource("containsParamPositiveTest")
    public void containsParamPositiveTest(String str) {
        Assertions.assertTrue(stringArrayList.contains(str));
    }

    public static Stream<Arguments> containsParamNegativeTest() {
        return Stream.of(
                Arguments.of("9"),
                Arguments.of("8"),
                Arguments.of("7")
        );
    }


    @ParameterizedTest
    @MethodSource("containsParamNegativeTest")
    public void containsParamNegativeTest(String str) {
        Assertions.assertFalse(stringArrayList.contains(str));
    }

    public static Stream<Arguments> indexOfParamPositiveTest() {
        return Stream.of(
                Arguments.of("0", 0),
                Arguments.of("1", 1),
                Arguments.of("2", 2),
                Arguments.of("3", 3),
                Arguments.of("4", 4)
        );
    }


    @ParameterizedTest
    @MethodSource("indexOfParamPositiveTest")
    public void indexOfPositiveTest(String str, int index) {
        Assertions.assertEquals(index, stringArrayList.indexOf(str));
    }

    public static Stream<Arguments> indexOfParamNegativeTest() {
        return Stream.of(
                Arguments.of("gf", -1),
                Arguments.of("10", -1)
        );
    }


    @ParameterizedTest
    @MethodSource("indexOfParamNegativeTest")
    public void indexOfNegativeTest(String str, int index) {
        Assertions.assertEquals(index, stringArrayList.indexOf(str));
    }

    public static Stream<Arguments> lastIndexOfTestParamTest() {
        return Stream.of(
                Arguments.of("1", 5),
                Arguments.of("4", 6)
        );
    }


    @ParameterizedTest
    @MethodSource("lastIndexOfTestParamTest")
    public void lastIndexOfTest(String str, int index) {
        stringArrayList.add("1");
        stringArrayList.add("4");
        Assertions.assertEquals(index, stringArrayList.lastIndexOf(str));
    }

    public static Stream<Arguments> getParamPositiveTest() {
        return Stream.of(
                Arguments.of("0", 0),
                Arguments.of("1", 1),
                Arguments.of("2", 2),
                Arguments.of("3", 3),
                Arguments.of("4", 4)
        );
    }


    @ParameterizedTest
    @MethodSource("getParamPositiveTest")
    public void getPositiveTest(String str, int index) {
        Assertions.assertEquals(str, stringArrayList.get(index));
    }

    @Test
    public void getNegativeTest() {
        Assertions.assertThrows(StringListIndexOutBoundsException.class, () -> stringArrayList.get(5));
    }

    @Test
    public void equalsPositiveTest() {
        StringArrayList test = new StringArrayList(5);
        test.add("0");
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        Assertions.assertTrue(stringArrayList.equals(test));
    }

    @Test
    public void equalsNegativeTest() {
        Assertions.assertThrows(InvalidArgumentException.class, () -> stringArrayList.equals(null));
    }

    public static Stream<Arguments> equalsParamNegativeTest() {
        return Stream.of(
                Arguments.of((new StringArrayList("1", "2", "3"))),
                Arguments.of((new StringArrayList("1", "u2", "3"))),
                Arguments.of((new StringArrayList("1", "2")))
        );
    }


    @ParameterizedTest
    @MethodSource("equalsParamNegativeTest")
    public void equalsNegativeTest(StringArrayList arg) {
        Assertions.assertFalse(stringArrayList.equals(arg));
    }

    @Test
    public void emptyPositiveTest() {
        StringArrayList test1 = new StringArrayList(5);
        Assertions.assertTrue(test1.isEmpty());
    }


    @Test
    public void emptyNegativeTest() {
        Assertions.assertFalse(stringArrayList.isEmpty());
    }

    @Test
    public void toArrayTest() {
        String[] test2 = {"0", "1", "2", "3", "4"};
        Assertions.assertArrayEquals(test2, stringArrayList.toArray());
    }
}

