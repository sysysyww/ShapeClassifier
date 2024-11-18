import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class ShapeClassifierTest {

    @Test
    public void testCorrectCircleLargeEven() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Large,Yes,50,50");
        assertEquals("Yes", result);
    }

    @Test
    public void testIncorrectShapeGuessWithSuggestion() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Ellipse,Large,Yes,50,50");
        assertEquals("No: Suggestion=Circle", result);
    }

    @Test
    public void testIncorrectSizeGuess() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Small,Yes,50,50");
        assertEquals("Yes: Wrong Size", result);
    }

    @Test
    public void testIncorrectEvenOddGuess() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Large,No,50,50");
        assertEquals("Yes: Wrong Even/Odd", result);
    }

    @Test
    public void testAllGuessesIncorrect() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Ellipse,Small,No,50,50");
        assertEquals("No: Suggestion=Circle", result);
    }

    @Test
    public void testBadGuessesLimitExceeded() {
        ShapeClassifier classifier = new ShapeClassifier();
        classifier.evaluateGuess("Ellipse,Small,Yes,50,50");
        classifier.evaluateGuess("Rectangle,Small,Yes,50,50");
        // The third incorrect guess should cause the program to exit
        // We need to handle System.exit(1) in tests; here we just check the output
        String result = classifier.evaluateGuess("Line,Small,Yes,50,50");
        // Since System.exit(1) is called, this line may not be reached
        // Depending on the test framework, you might need to catch the exit call
    }

    @Test
    public void testEquilateralTriangle() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Equilateral,Small,Yes,10,10,10");
        assertEquals("Yes", result);
    }

    @Test
    public void testIsoscelesTriangle() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Isosceles,Small,Yes,10,10,5");
        assertEquals("Yes", result);
    }

    @Test
    public void testScaleneTriangle() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Scalene,Small,Yes,10,11,12");
        assertEquals("Yes", result);
    }

    @Test
    public void testNotATriangle() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Scalene,Small,Yes,1,2,3");
        assertEquals("No: Suggestion=Not A Triangle", result);
    }

    @Test
    public void testSquare() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Square,Large,Yes,20,20,20,20");
        assertEquals("Yes", result);
    }

    @Test
    public void testRectangle() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Rectangle,Large,Yes,20,10,20,10");
        assertEquals("Yes", result);
    }

    @Test
    public void testNegativeParameters() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Large,Yes,-50,-50");
        assertEquals("Yes", result);
    }

    @Test
    public void testPerimeterExactly100() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Large,Yes,16,16");
        assertEquals("Yes: Wrong Size", result);
    }

    @Test
    public void testParametersOverMaxValue() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Large,Yes,5000,5000");
        assertEquals("Yes", result);
    }

    @Test
    public void testLineShape() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Line,Small,Yes,5");
        assertEquals("Yes", result);
    }

    @Test
    public void testEllipseShape() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Ellipse,Small,Yes,10,5");
        assertEquals("Yes", result);
    }

    @Test
    public void testCircleShapeWithEqualParameters() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Small,Yes,10,10");
        assertEquals("Yes", result);
    }

    @Test
    public void testZeroParameters() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Small,Yes");
        assertEquals("No", result);
    }

    @Test
    public void testInvalidParameters() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Small,Yes,a,b");
        assertEquals("No", result);
    }
}
