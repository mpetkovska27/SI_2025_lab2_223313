import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class SILab2Test {
    @Test
    void everyStatementTest() {
        RuntimeException ex;
        List<Item> items = new ArrayList<>();

        //Test 1: AllItems = Null
        ex = assertThrows(RuntimeException.class,
                () -> {SILab2.checkCart(null, "1234567890123456");});
        assertEquals("allItems list can't be null!", ex.getMessage());

        //Test 2:Item with null name
        items.add(new Item(null, 3, 100, 0));
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items, "1234567890123456"));
        assertEquals("Invalid item!", ex.getMessage());

        //Test 3: Invalid character in card number
        items.clear();
        items.add(new Item("item1", 3, 100, 0));
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items, "123456789012345A"));
        assertEquals("Invalid character in card number!", ex.getMessage());

        //Test 4: Invalid card number
        items.clear();
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items, "1234"));
        assertEquals("Invalid card number!", ex.getMessage());


        //Test 5: Valid cart and items
        items.clear();
        items.add(new Item("item1", 3, 100, 0));
        items.add(new Item("item2", 10, 300, 0.1));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(2970, result, 0.01);
    }

    @Test
    void MultipleConditionTest() {
        List<Item> items = new ArrayList<>();


        //Test 1: TXX  - price > 300
        items.clear();
        items.add(new Item("item1", 1, 350, 0));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(320.0, result);

        //Test 2: FTX - discount > 0
        items.clear();
        items.add(new Item("item2", 1, 200, 0.1));
        double result1 = SILab2.checkCart(items, "1234567890123456");
        assertEquals(150.0, result1);

        //Test 3: FFT quantity > 10
        items.clear();
        items.add(new Item("item3", 12, 200, 0));
        double result2 = SILab2.checkCart(items, "1234567890123456");
        assertEquals(2370.0, result2);

        //Test 4: FFF
        items.clear();
        items.add(new Item("item4", 1, 200, 0));
        double result3 = SILab2.checkCart(items, "1234567890123456");
        assertEquals(200.0, result3);

    }
}