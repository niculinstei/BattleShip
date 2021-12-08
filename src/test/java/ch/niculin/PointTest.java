package ch.niculin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {



    @Test
    public void testToString() {
        Point point= new Point('A', 3);
        var actual = point.toString();
        assertEquals("Point{x=A, y=3}", actual);
    }

    @Test
    public void testEquals(){
        Point point1= new Point('A', 3);
        Point point2= new Point('A', 3);
        boolean isEqual = point1.equals(point2);
        assertTrue(isEqual);
    }

    @Test
    public void testNotEquals(){
        Point point1= new Point('A', 3);
        Point point2= new Point('A', 4);
        boolean isEqual = point1.equals(point2);
        assertFalse(isEqual);
    }

    @Test
    public void testHashCodeTrue(){
        Point point1 = new Point('A', 3);
        Point point2 = new Point('A', 3);
        assertEquals(point1.hashCode(), point2.hashCode());
    }

    @Test
    public void testHashCodeFalse(){
        Point point1 = new Point('A', 3);
        Point point2 = new Point('A', 4);
        assertNotEquals(point1.hashCode(), point2.hashCode());

    }

}
