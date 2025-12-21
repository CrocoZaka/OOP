import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Generic hash table implementation that stores key-value pairs.
 * Collisions are resolved using separate chaining (linked lists in buckets).
 */
public class HashTable<K, V> implements Iterable<HashTable.Entry<K, V>> {

    /**
     * Represents a key-value pair stored inside the hash table.
     */
    public static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] table;
    private int size = 0;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
    }

    private int hash(Object key) {
        return key == null ? 0 : key.hashCode() & (table.length - 1);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[oldTable.length * 2];
        size = 0;
        for (Entry<K, V> head : oldTable) {
            for (Entry<K, V> n = head; n != null; n = n.next) {
                put(n.key, n.value);
            }
        }
    }

    /**
     * Inserts a key-value pair into the hash table.
     */
    public void put(K key, V value) {
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
        int index = hash(key);
        Entry<K, V> node = table[index];
        for (Entry<K, V> n = node; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) {
                n.value = value;
                return;
            }
        }
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = node;
        table[index] = newEntry;
        size++;
        modCount++;
    }

    /**
     * Removes the entry associated with the key from the hash table.
     */
    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> node = table[index];
        Entry<K, V> prev = null;
        for (Entry<K, V> n = node; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) {
                if (prev == null) {
                    table[index] = n.next;
                } else {
                    prev.next = n.next;
                }
                size--;
                modCount++;
                return;
            }
            prev = n;
        }
    }

    /**
     * Returns the value associated with the key.
     */
    public V get(Object key) {
        int index = hash(key);
        Entry<K, V> node = table[index];
        for (Entry<K, V> n = node; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) {
                return n.value;
            }
        }
        return null;
    }

    /**
     * Updates the value associated with the key.
     */
    public void update(K key, V newValue) {
        int index = hash(key);
        Entry<K, V> node = table[index];
        for (Entry<K, V> n = node; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) {
                n.value = newValue;
                modCount++;
            }
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index = 0;
            private Entry<K, V> current = null;
            private Entry<K, V> nextEntry = advance();

            private Entry<K, V> advance() {
                if (current != null && current.next != null) {
                    return current.next;
                }
                while (index < table.length) {
                    if (table[index] != null) {
                        return table[index++];
                    }
                    index++;
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                return nextEntry != null;
            }

            @Override
            public Entry<K, V> next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (nextEntry == null) {
                    throw new NoSuchElementException();
                }
                current = nextEntry;
                nextEntry = advance();
                return new Entry<>(current.key, current.value);
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HashTable)) {
            return false;
        }

        HashTable<?, ?> other = (HashTable<?, ?>) o;
        if (size != other.size) {
            return false;
        }

        for (Entry<K, V> head : table) {
            for (Entry<K, V> n = head; n != null; n = n.next) {
                if (!Objects.equals(n.value, other.get(n.key))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Entry<K, V> head : table) {
            for (Entry<K, V> n = head; n != null; n = n.next) {
                hash += Objects.hashCode(n.key) ^ Objects.hashCode(n.value);
            }
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Entry<K, V> head : table) {
            for (Entry<K, V> n = head; n != null; n = n.next) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(n.key).append("=").append(n.value);
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }

}
