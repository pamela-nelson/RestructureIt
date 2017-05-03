

package uk.co.jezuk.mango.collections;


public class ListFactory<T> {
    public static <T> java.util.List<T> list(final T... values) {
        final java.util.List<T> l = new java.util.ArrayList<T>();
        for (final T o : values)
            l.add(o);
        
        return l;
    }

    public static <T> java.util.List<T> list(final java.util.Iterator<T> values) {
        final java.util.List<T> l = new java.util.ArrayList<T>();
        while (values.hasNext())
            l.add(values.next());
        
        return l;
    }
}

