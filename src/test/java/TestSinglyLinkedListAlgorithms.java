import com.github.hd0a.algo.SinglyLinkedListAlgorithms;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSinglyLinkedListAlgorithms {
    @Test
    public void testCreate() {
        String x = SinglyLinkedListAlgorithms.fromString("1 2 3 4").toString();
        Assert.assertEquals(x, "1 -> 2 -> 3 -> 4 -> null");

        x = String.valueOf(SinglyLinkedListAlgorithms.fromString(""));
        Assert.assertEquals(x, "null");

        x = SinglyLinkedListAlgorithms.fromString("1").toString();
        Assert.assertEquals(x, "1 -> null");
    }

    @Test
    public void testFindHalf() {
        String nodes = "1 2 3 4 5";
        SinglyLinkedListAlgorithms.Node x = SinglyLinkedListAlgorithms.fromString(nodes);
        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.findHalf(x)), "4 -> 5 -> null");


        nodes = "1 2 4 5";
        x = SinglyLinkedListAlgorithms.fromString(nodes);
        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.findHalf(x)), "4 -> 5 -> null");

        nodes = "1 2 4";
        x = SinglyLinkedListAlgorithms.fromString(nodes);
        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.findHalf(x)), "4 -> null");

        nodes = "1 2";
        x = SinglyLinkedListAlgorithms.fromString(nodes);
        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.findHalf(x)), "2 -> null");

        nodes = "1";
        x = SinglyLinkedListAlgorithms.fromString(nodes);
        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.findHalf(x)), "null");

        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.findHalf(null)), "null");
    }

    @Test
    public void testReverse() {
        String nodes = "1 2 3 4";
        SinglyLinkedListAlgorithms.Node x;
        x = SinglyLinkedListAlgorithms.fromString(nodes);
        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.reverse(x)), "4 -> 3 -> 2 -> 1 -> null");

        nodes = "1 3";
        x = SinglyLinkedListAlgorithms.fromString(nodes);
        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.reverse(x)), "3 -> 1 -> null");

        nodes = "1";
        x = SinglyLinkedListAlgorithms.fromString(nodes);
        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.reverse(x)), "1 -> null");

        x = null;
        Assert.assertEquals(String.valueOf(SinglyLinkedListAlgorithms.reverse(x)), "null");
    }

    @Test
    public void testIsPalindrome() {
        SinglyLinkedListAlgorithms.Node x;
        String orig;

        x = li("1 2 3 4 5 6 5 4 3 2 1");
        orig = x.toString();
        Assert.assertTrue(SinglyLinkedListAlgorithms.isPalindrome(x));
        Assert.assertEquals(String.valueOf(x), orig); // doesn't eat the array list

        x = li("1 2 3 2 1");
        orig = x.toString();
        Assert.assertTrue(SinglyLinkedListAlgorithms.isPalindrome(x));
        Assert.assertEquals(String.valueOf(x), orig); // doesn't eat the array list

        x = li("1 4 3 2 1");
        orig = x.toString();
        Assert.assertFalse(SinglyLinkedListAlgorithms.isPalindrome(x));
        Assert.assertEquals(String.valueOf(x), orig); // doesn't eat the array list

        x = li("1 1");
        orig = x.toString();
        Assert.assertTrue(SinglyLinkedListAlgorithms.isPalindrome(x));
        Assert.assertEquals(String.valueOf(x), orig); // doesn't eat the array list

        x = li("1 3 1");
        orig = x.toString();
        Assert.assertTrue(SinglyLinkedListAlgorithms.isPalindrome(x));
        Assert.assertEquals(String.valueOf(x), orig); // doesn't eat the array list
    }

    private static SinglyLinkedListAlgorithms.Node li(String items) {
        return SinglyLinkedListAlgorithms.fromString(items);
    }
}
