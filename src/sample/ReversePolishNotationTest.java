package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationTest {

    ReversePolishNotation controller = new ReversePolishNotation();

    private static Stream<Arguments> provideValid() {
        return Stream.of(
                Arguments.of("34+", 7),
                Arguments.of("34+62+*", 56),
                Arguments.of("53-96-*", 6),
                Arguments.of("12345++++", 15),
                Arguments.of("13+4/", 1),
                Arguments.of("1 3 + 4 /", 1),
                Arguments.of("12345****", 120),
                Arguments.of("82/", 4),
                Arguments.of("93/", 3),
                Arguments.of("53-", 2),
                Arguments.of("34*", 12)
                );
    }

    private static Stream<Arguments> provideInvalid() {
        return Stream.of(
                Arguments.of("123++123++"),
                Arguments.of("8*"),
                Arguments.of("1251461854-*"),
                Arguments.of("555"),
                Arguments.of("55+55+"),
                Arguments.of("95/54-8"),
                Arguments.of("50/"),
                Arguments.of("*99")
        );
    }

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @MethodSource("provideValid")
    void test(String input, int expected) throws Exception {
        assertEquals(expected, controller.calculate(input));
    }

    @ParameterizedTest
    @MethodSource("provideInvalid")
    void testNegativ(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.calculate(input);
        });
    }
}
