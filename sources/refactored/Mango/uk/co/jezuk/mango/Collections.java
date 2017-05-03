

package uk.co.jezuk.mango;


public class Collections {
    public static interface MapBuilder<K, V> extends java.util.Map<K, V> {
        public uk.co.jezuk.mango.Collections.MapBuilder<K, V> map(K key, V v);
    }

    public static <K, V> uk.co.jezuk.mango.Collections.MapBuilder<K, V> map(K key, V value) {
        return uk.co.jezuk.mango.collections.MapFactory.map(key, value);
    }

    public static <T> java.util.List<T> list(final T... values) {
        return uk.co.jezuk.mango.collections.ListFactory.list(values);
    }

    public static <T> java.util.List<T> list(final java.util.Iterator<T> values) {
        return uk.co.jezuk.mango.collections.ListFactory.list(values);
    }

    public static <T> java.util.List<T> list(final java.util.Collection<T> values) {
        return uk.co.jezuk.mango.Collections.list(values.iterator());
    }

    private Collections() {
    }
}

