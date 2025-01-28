package TP.restaurant.Fake;

import org.junit.jupiter.api.Test;

import static TP.restaurant.Fake.FakeClass.isEven;
import static TP.restaurant.Fake.FakeClass.isPalindrome;
import static org.junit.jupiter.api.Assertions.*;

class FakeClassTest {
    @Test
    void testAdd() {
        // Préparation
        int firstNumber = 1;
        int secondNumber = 1;
        int expected = 2;
        //Execution
        int actual = FakeClass.add(firstNumber, secondNumber);
        //Verification
        assertEquals(expected, actual);
    }

    @Test
    void testIsEven() {
        int evenNumber = 2;
        int oddNumber = 3;
        assertEquals(true, isEven(evenNumber));
        assertEquals(false, isEven(oddNumber));
    }

    @Test
    void testIsPalindrome() {
        String palindromeWord = "radar";
        assertTrue(isPalindrome(palindromeWord));
    }

    @Test
    void testAverage() {
        int[] numbers = {16, 18, 20};
        double expected = 18;
        double actual = FakeClass.average(numbers);
        assertEquals(expected, actual);
    }

}