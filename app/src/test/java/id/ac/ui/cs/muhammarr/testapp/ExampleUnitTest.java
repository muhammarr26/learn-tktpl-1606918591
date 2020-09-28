package id.ac.ui.cs.muhammarr.testapp;

import org.junit.Test;

import static org.junit.Assert.*;
import id.ac.ui.cs.muhammarr.testapp.MainActivity;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testIncrementCounter() {
        MainActivity.incrementCounter();
        int result = MainActivity.getCounter();
        assertEquals( 1, result);
    }
}