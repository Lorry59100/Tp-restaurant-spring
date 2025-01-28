package TP.restaurant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FakeTests {
    @Test
    void testEqualsTrue() {
        assertTrue(true);
    }

    @Test
    void testEqualsFalse() {
        assertFalse(false);
    }

    @Test
    void testEquality() {
        assertEquals(1,1);
    }

    @Test
    void testNonEquality() {
        assertNotEquals(1, 2);
    }

    @Test
    void testNull() {
        assertNull(null);
    }

    @Test
    void testNotNull() {
        assertNotNull(new Object());
    }
}
