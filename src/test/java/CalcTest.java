import com.revature.amazon.controller.Calc;

import org.junit.Test;
import org.junit.Assert;

public class CalcTest {

    @Test
    public void testMultiply() {
        Calc c = new Calc();
        int i = c.multiply(3, 5);
        Assert.assertEquals(i, 15);
        
    }
}