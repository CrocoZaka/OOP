import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {

    private static class BadKey {
        @Override
        public int hashCode() {
            return 42;
        }
    }

    private int countEntries(HashTable<String, Integer> map) {
        int count = 0;
        for (HashTable.Entry<String, Integer> ignored : map) {
            count++;
        }
        return count;
    }

    private HashTable<String, Integer> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
        table.put("A", 1);
        table.put("B", 2);
        table.put("C", 3);
    }

    @Test
    void testPutAndGet() {
        assertEquals(1, table.get("A"));
        assertEquals(2, table.get("B"));
        assertEquals(3, table.get("C"));
        assertNull(table.get("D"));
    }

    @Test
    void testPutUpdatesExistingKey() {
        table.put("A", 100);
        assertEquals(100, table.get("A"));
    }

    @Test
    void testUpdateExistingKey() {
        table.update("B", 20);
        assertEquals(20, table.get("B"));
    }

    @Test
    void testUpdateNonExistingKeyDoesNothing() {
        table.update("Z", 100);
        assertNull(table.get("Z"));
        assertEquals(3, countEntries(table));
    }

    @Test
    void testUpdateKeyInCollisionChain() {
        HashTable<BadKey, Integer> badMap = new HashTable<>();
        BadKey k1 = new BadKey();
        BadKey k2 = new BadKey();

        badMap.put(k1, 5);
        badMap.put(k2, 10);

        badMap.update(k2, 99);
        assertEquals(99, badMap.get(k2));
        assertEquals(5, badMap.get(k1));
    }

    @Test
    void testPutNullKey() {
        table.put(null, 99);
        assertEquals(99, table.get(null));
        table.put(null, 123);
        assertEquals(123, table.get(null));
    }

    @Test
    void testContainsKey() {
        assertTrue(table.containsKey("A"));
        assertTrue(table.containsKey("C"));
        assertFalse(table.containsKey("Z"));
    }

    @Test
    void testRemove() {
        table.remove("B");
        assertNull(table.get("B"));
        assertFalse(table.containsKey("B"));
        assertEquals(2, countEntries(table));
    }

    @Test
    void testRemoveNonExistingKeyDoesNothing() {
        table.remove("Z");
        assertEquals(3, countEntries(table));
    }

    @Test
    void testRemoveKeyInCollisionChain() {
        HashTable<BadKey, Integer> badMap = new HashTable<>();
        BadKey k1 = new BadKey();
        BadKey k2 = new BadKey();
        BadKey k3 = new BadKey();

        badMap.put(k1, 10);
        badMap.put(k2, 20);
        badMap.put(k3, 30);

        badMap.remove(k2);
        assertNull(badMap.get(k2));
        assertEquals(10, badMap.get(k1));
        assertEquals(30, badMap.get(k3));
    }

    @Test
    void testResizeAndPreserveValues() {
        for (int i = 0; i < 20; i++) {
            table.put("Key" + i, i);
        }
        assertEquals(1, table.get("A"));
        assertEquals(19, table.get("Key19"));
        assertTrue(table.containsKey("Key15"));
    }

    @Test
    void testIteratorTraversal() {
        Iterator<HashTable.Entry<String, Integer>> it = table.iterator();
        int count = 0;
        while (it.hasNext()) {
            HashTable.Entry<String, Integer> e = it.next();
            assertNotNull(e.key);
            assertNotNull(e.value);
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    void testIteratorThrowsConcurrentModificationException() {
        Iterator<HashTable.Entry<String, Integer>> it = table.iterator();
        table.put("D", 4);
        assertThrows(ConcurrentModificationException.class, it::next);
    }

    @Test
    void testIteratorThrowsNoSuchElementException() {
        Iterator<HashTable.Entry<String, Integer>> it = table.iterator();
        while (it.hasNext()) {
            it.next();
        }
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void testForEachLoopIteration() {
        int count = 0;
        boolean hasA = false;
        boolean hasB = false;
        boolean hasC = false;

        for (HashTable.Entry<String, Integer> entry : table) {
            assertNotNull(entry);
            assertNotNull(entry.key);
            assertNotNull(entry.value);

            switch (entry.key) {
                case "A" -> {
                    assertEquals(1, entry.value);
                    hasA = true;
                }
                case "B" -> {
                    assertEquals(2, entry.value);
                    hasB = true;
                }
                case "C" -> {
                    assertEquals(3, entry.value);
                    hasC = true;
                }
                default -> fail("Unexpected key: " + entry.key);
            }
            count++;
        }

        assertEquals(3, count);
        assertTrue(hasA);
        assertTrue(hasB);
        assertTrue(hasC);
    }


    @Test
    void testEqualsAndHashTableIndependence() {
        HashTable<String, Integer> other = new HashTable<>();
        other.put("A", 1);
        other.put("B", 2);
        other.put("C", 3);
        assertEquals(table, other);
        assertEquals(table.hashCode(), other.hashCode());
        assertEquals(table, table);

        other.update("B", 99);
        assertNotEquals(table, other);
    }

    @Test
    void testEqualsWithIncompatibleType() {
        assertNotEquals(table, "NotAHashTable");
        assertNotEquals(table, null);
        HashTable<Integer, String> other = new HashTable<>();
        assertNotEquals(table, other);
    }

    @Test
    void testToStringFormat() {
        String str = table.toString();
        assertTrue(str.startsWith("{"));
        assertTrue(str.endsWith("}"));
        assertTrue(str.contains("A=1"));
        assertTrue(str.contains("B=2"));
        assertTrue(str.contains("C=3"));
    }
}
