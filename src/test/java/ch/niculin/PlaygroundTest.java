package ch.niculin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlaygroundTest {
    Playground playground;

    @BeforeEach
    void init() {
        playground = new Playground(10);
    }

    @Test
    void testGetSizeAfterPlaygroundCreation() {
        int size = playground.getSize();
        assertEquals(10, size);
    }

    @Test
    void testTotalSizeOfPlayground() {
        List<Point> playgroundPoints = playground.getPlayground();
        int totalSize = playgroundPoints.size();
        assertEquals(100, totalSize);
    }

    @Test
    void testValidPointWithinPlayground() {
        Point point = new Point('A', 4);
        Optional<Point> point1 = playground.getPlaygroundPoint(point);
        assertNotSame(point1.get(), point);
        assertEquals(point1.get(), point);
        assertSame(playground.getPlayground().get(30), point1.get());
    }

    @Test
    void testInvalidPointOutsidePlayground() {
        Point point = new Point('Z', 100);
        Optional<Point> point1 = playground.getPlaygroundPoint(point);
        assertTrue(point1.isEmpty());
    }

    @Test
    void testValidPointA1WithinPlayground() {
        Point point = new Point('A', 1);
        Optional<Point> point1 = playground.getPlaygroundPoint(point);
        assertNotSame(point1.get(), point);
        assertEquals(point1.get(), point);
        assertSame(playground.getPlayground().get(0), point1.get());
    }

    @Test
    void testValidPointA10WithinPlayground() {
        Point point = new Point('A', 10);
        Optional<Point> point1 = playground.getPlaygroundPoint(point);
        assertNotSame(point1.get(), point);
        assertEquals(point1.get(), point);
        assertSame(playground.getPlayground().get(90), point1.get());
    }

    @Test
    void testValidPointJ1WithinPlayground() {
        Point point = new Point('J', 1);
        Optional<Point> point1 = playground.getPlaygroundPoint(point);
        assertNotSame(point1.get(), point);
        assertEquals(point1.get(), point);
        assertSame(playground.getPlayground().get(9), point1.get());
    }

    @Test
    void testValidPointJ10WithinPlayground() {
        Point point = new Point('J', 10);
        Optional<Point> point1 = playground.getPlaygroundPoint(point);
        assertNotSame(point1.get(), point);
        assertEquals(point1.get(), point);
        assertSame(playground.getPlayground().get(99), point1.get());
    }

    @Test
    void testValidLineNumber() {
        int lineNumber = 6;
        List<Point> line = playground.getLine(lineNumber);
        boolean condition3 = line.size() == 10;
        assertTrue(condition3);
    }



    @Test
    void testInvalidNegativeLineNumber() {
        int lineNumber = 23;
        assertThrows(IllegalArgumentException.class, () -> playground.getLine(lineNumber));
    }

    @Test
    void testInvalidPositiveLineNumber() {
        int lineNumber = -12;
        assertThrows(IllegalArgumentException.class, () -> playground.getLine(lineNumber));
    }

    @Test
    void testInvalidStartPoint() {
        Point point = new Point('X', 12);
        boolean result = playground.arePointsInField(point, Direction.HORIZONTAL, 1);
        assertFalse(result);
    }

    @Test
    void testIsValidOnePointHorizontal() {
        Point point = new Point('A', 3);
        boolean result = playground.arePointsInField(point, Direction.HORIZONTAL, 1);
        assertTrue(result);
    }

    @Test
    void testIsValidOnePointVertical() {
        Point point = new Point('A', 3);
        boolean result = playground.arePointsInField(point, Direction.VERTICAL, 1);
        assertTrue(result);
    }

    @Test
    void testIsValidThreePointHorizontal() {
        Point point = new Point('A', 3);
        boolean result = playground.arePointsInField(point, Direction.HORIZONTAL, 3);
        assertTrue(result);
    }

    @Test
    void testIsValidThreePointVertical() {
        Point point = new Point('A', 3);
        boolean result = playground.arePointsInField(point, Direction.VERTICAL, 3);
        assertTrue(result);
    }

    @Test
    void testIsInvalidThreePointHorizontal() {
        Point point = new Point('I', 3);
        boolean result = playground.arePointsInField(point, Direction.HORIZONTAL, 3);
        assertFalse(result);
    }

    @Test
    void testIsInvalidThreePointVertical() {
        Point point = new Point('A', 9);
        boolean result = playground.arePointsInField(point, Direction.VERTICAL, 3);
        assertFalse(result);
    }

    @Test
    void testNegativeAmount() {
        Point point = new Point('A', 3);
        boolean result = playground.arePointsInField(point, Direction.VERTICAL, -37);
        assertFalse(result);
    }

    @Test
    void testTooLargeAmount() {
        Point point = new Point('A', 3);
        boolean result = playground.arePointsInField(point, Direction.VERTICAL, 11);
        assertFalse(result);
    }
}
