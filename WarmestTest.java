package task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarmestTest {

//In each test we will check the object Warmest using 2 different objects K and V:


    @Test
    void put() {
        Warmest<Integer, Integer> warmest = new Warmest<>();

        warmest.put(1, 1);
        assertEquals(1, warmest.getLast());
        System.out.println(warmest.toString());

        System.out.println("Print Warmest after adding (1, test2): \n");

        warmest.put(1, 2);
        assertEquals(2, warmest.getLast());
        System.out.println(warmest.toString());

    }

    @Test
    void get() {
        Warmest<String, String> warmest = new Warmest<>();

        assertEquals(null, warmest.get("one")); //  Check that when the key is not found return null

        warmest.put("one", "test1");
        warmest.put("two", "test2");
        warmest.put("three", "test3");
        warmest.put("four", "test4");

        assertEquals("test1", warmest.get("one"));
        assertEquals("test1", warmest.getLast()); // Check that after get() the last = get.value

        assertEquals("test2", warmest.get("two"));
        assertEquals("test2", warmest.getLast());

        assertEquals("test3", warmest.get("three"));
        assertEquals("test3", warmest.getLast());

        assertEquals("test4", warmest.get("four"));
        assertEquals("test4", warmest.getLast());

    }

    @Test
    void remove() {
        Warmest<Integer, String> warmest = new Warmest<>();

        System.out.println("Print Warmest after adding:  ");
        warmest.put(1, "hello1");
        warmest.put(2, "hello2");
        warmest.put(3, "hello3");
        warmest.put(4, "hello4");
        warmest.put(5, "hello5");
        warmest.put(6, "hello6");
        warmest.put(7, "hello7");
        warmest.put(8, "hello8");
        warmest.put(9, "hello9");
        warmest.put(33, "hello10");
        warmest.put(34, "hello11");
        warmest.put(35, "hello12");
        warmest.put(36, "hello13");
        warmest.put(14, "hello14");
        warmest.put(15, "hello15");
        warmest.put(16, "hello16");
        warmest.put(17, "hello17");
        warmest.put(18, "hello18");
        warmest.put(19, "hello19");
        warmest.put(20, "hello20");
        warmest.put(21, "hello21");
        warmest.put(22, "hello22");
        warmest.put(23, "hello23");
        warmest.put(24, "hello24");
        warmest.put(25, "hello25");
        System.out.println(warmest.toString());

        System.out.println("\n print Wrmest after delete only key-36 and key-24 : ");
        assertEquals("hello13", warmest.remove(36));
        assertEquals("hello24", warmest.remove(24));
        System.out.println(warmest.toString());

        System.out.println("\n print Wrmest after delete all: ");
        assertEquals("hello1", warmest.remove(1));
        assertEquals("hello2", warmest.remove(2));
        assertEquals("hello3", warmest.remove(3));
        assertEquals("hello4", warmest.remove(4));
        assertEquals("hello5", warmest.remove(5));
        assertEquals("hello6", warmest.remove(6));
        assertEquals("hello7", warmest.remove(7));
        assertEquals("hello8", warmest.remove(8));
        assertEquals("hello9", warmest.remove(9));
        assertEquals("hello10", warmest.remove(33));
        assertEquals("hello11", warmest.remove(34));
        assertEquals("hello12", warmest.remove(35));
        assertEquals("hello14", warmest.remove(14));
        assertEquals("hello15", warmest.remove(15));
        assertEquals("hello16", warmest.remove(16));
        assertEquals("hello17", warmest.remove(17));
        assertEquals("hello18", warmest.remove(18));
        assertEquals("hello19", warmest.remove(19));
        assertEquals("hello20", warmest.remove(20));
        assertEquals("hello21", warmest.remove(21));
        assertEquals("hello22", warmest.remove(22));
        assertEquals("hello23", warmest.remove(23));
        assertEquals("hello25", warmest.remove(25));
        System.out.println(warmest.toString());


    }

    @Test
    void getWarmest() {
        Warmest<Integer, Integer> warmest = new Warmest<>();
        System.out.println("When Warmest is empty:");
        assertEquals(null, warmest.getLast());
        System.out.println(warmest.getLast());

        System.out.println("\n after adding to Warmest:");
        warmest.put(1, 1);
        assertEquals(1, warmest.getLast());
        System.out.println(warmest.getLast());

        warmest.put(2, 2);
        assertEquals(2, warmest.getLast());
        System.out.println(warmest.getLast());

        warmest.put(3, 3);
        assertEquals(3, warmest.getLast());
        System.out.println(warmest.getLast());

        warmest.put(4, 4);
        assertEquals(4, warmest.getLast());
        System.out.println(warmest.getLast());

        System.out.println("\n after  deleting key-4 and key-3 from Warmest:");
        warmest.remove(4);
        warmest.remove(3);
        assertEquals(2, warmest.getLast());
        System.out.println(warmest.getLast());

        System.out.println("\n after  deleting all keys from Warmest:");
        warmest.remove(2);
        warmest.remove(1);
        System.out.println(warmest.getLast());
        assertEquals(null, warmest.getLast());

    }


}