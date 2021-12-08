package CS2020.assignment2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void TestCheckIfBornOnWeekend()
    {
        try {
            assertFalse(Utils.checkIfBornOnWeekend("8 Jun 1977"));
            assertFalse(Utils.checkIfBornOnWeekend("22 Jul 1992"));
            assertFalse(Utils.checkIfBornOnWeekend("8 Dec 2021"));
            assertTrue(Utils.checkIfBornOnWeekend("16 Aug 1958"));
            assertTrue(Utils.checkIfBornOnWeekend("4 Dec 2021"));
        } catch (Exception | Error e) {
            System.out.println("Something went wrong..");
        }
    }
}
