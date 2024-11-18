import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapeClassifierTest {

    @Test
    void testCorrectShapeGuess() {
        ShapeClassifier sc = new ShapeClassifier();
        assertEquals("Yes", sc.evaluateGuess("Circle,Small,Yes,10,10"));
    }

    @Test
    void testWrongSizeGuess() {
        ShapeClassifier sc = new ShapeClassifier();
        assertEquals("Wrong Size", sc.evaluateGuess("Circle,Large,Yes,5,5"));
    }

    @Test
    void testWrongEvenOddGuess() {
        ShapeClassifier sc = new ShapeClassifier();
        assertEquals("Wrong Even/Odd", sc.evaluateGuess("Rectangle,Large,No,10,20,10,20"));
    }

    @Test
    void testMultipleWrongGuesses() {
        ShapeClassifier sc = new ShapeClassifier();
        assertEquals("Wrong Size,Wrong Even/Odd", sc.evaluateGuess("Square,Small,No,10,10,10,10"));
    }

    @Test
    void testInvalidShapeGuess() {
        ShapeClassifier sc = new ShapeClassifier();
        assertEquals("No: Suggestion=Equilateral", sc.evaluateGuess("Triangle,Small,Yes,10,10,10"));
    }

    @Test
    void testBadGuessesLimit() {
        ShapeClassifier sc = new ShapeClassifier();
        sc.evaluateGuess("Triangle,Large,Yes,10,20,30");
        sc.evaluateGuess("Hexagon,Small,Yes,5,5,5,5,5,5");
        assertThrows(SystemExitException.class, () -> {
            sc.evaluateGuess("Pentagon,Small,No,5,5,5,5,5");
        });
    }

    @Test
    void testEdgeCases() {
        ShapeClassifier sc = new ShapeClassifier();
        assertEquals("Wrong Size,Wrong Even/Odd", sc.evaluateGuess("Line,Small,No,0"));
        assertEquals("No", sc.evaluateGuess("Circle,Small,Yes,10000,10000"));
    }
}
