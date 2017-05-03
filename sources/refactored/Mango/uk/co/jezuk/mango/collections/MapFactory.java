

package uk.co.jezuk.mango.collections;


public class MapFactory<K, V> {
    public static <K, V> uk.co.jezuk.mango.Collections.MapBuilder<K, V> map(K key, V value) {
        uk.co.jezuk.mango.collections.MapFactory.Builder<K, V> builder = new uk.co.jezuk.mango.collections.MapFactory.Builder<K, V>();
        return builder.map(key, value);
    }

    public static class Builder<K, V> implements uk.co.jezuk.mango.Collections.MapBuilder<K, V> {
        private final java.util.Map<K, V> backing_;

        private Builder() {
            backing_ = new java.util.HashMap<K, V>();
        }

        public uk.co.jezuk.mango.Collections.MapBuilder<K, V> map(K key, V value) {
            backing_.put(key, value);
            return this;
        }

        public void clear() {
            backing_.clear();
        }

        public boolean containsKey(java.lang.Object key) {
            return backing_.containsKey(key);
        }

        public boolean containsValue(java.lang.Object value) {
            return backing_.containsKey(value);
        }

        public java.util.Set<java.util.Map.Entry<K, V>> entrySet() {
            return backing_.entrySet();
        }

        public boolean equals(java.lang.Object o) {
            return backing_.equals(o);
        }

        public V get(java.lang.Object key) {
            return backing_.get(key);
        }

        public int hashCode() {
            return backing_.hashCode();
        }

        public boolean isEmpty() {
            return backing_.isEmpty();
        }

        public java.util.Set<K> keySet() {
            return backing_.keySet();
        }

        public V put(K key, V value) {
            return backing_.put(key, value);
        }

        public void putAll(java.util.Map<? extends K, ? extends V> m) {
            backing_.putAll(m);
        }

        public V remove(java.lang.Object key) {
            return backing_.remove(key);
        }

        public int size() {
            return backing_.size();
        }

        public java.util.Collection<V> values() {
            return backing_.values();
        }
    }
}

