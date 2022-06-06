import com.liftdgame.Encoder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void basicTests() {
        test("descending from positive to negative 2 by two",new int[] {1,-1,-3,-5},"1--5/2");
        test("identical + consecutive",new int[] {1,1,2,3,4,5,7,9},"1*2,2-5,7,9");
        test("identical + consecutive + same interval",new int[] {1,1,2,3,4,5,7,9,11},"1*2,2-5,7-11/2");
        test("test negative identical",new int[] {-2,-2,-2,-2},"-2*4");
        test("test negative identical",new int[] {-2,-2},"-2*2");
        test("2 identical numbers",new int[] {1,2,2,3},"1,2*2,3");
        test("3 consecutive numbers, ascending",new int[] {1,3,4,5,7},"1,3-5,7");
        test("3 consecutive numbers, descending",new int[] {1,5,4,3,7},"1,5-3,7");
        test("3 numbers with same interval, descending",new int[] {1,10,8,6,7},"1,10-6/2,7");
        test("test 1",new int[] {1,10,8,6,2,2,2,2,2,7,-7},"1,10-6/2,2*5,7,-7");
        test("test 2",new int[] {1,10,8,6,2,2,-2,2,2,7,-7},"1,10-6/2,2*2,-2,2*2,7,-7");
        test("test 3",new int[] {30,27,138,60,46,2,5,8,11,14,140,140,148,11},"30,27,138,60,46,2-14/3,140*2,148,11");
    }

    private void test(String description,int[] raw,String encoded) {
        assertEquals(description,encoded,new Encoder().compress(raw));
    }
}
