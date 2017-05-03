

package uk.co.jezuk.mango.iterators;


class Helpers {
    public static <T> java.util.List<java.util.Iterator<T>> toIterators(final java.lang.Object... iterables) {
        final java.util.List<java.util.Iterator<T>> list = new java.util.ArrayList<java.util.Iterator<T>>();
        for (final java.lang.Object o : iterables) {
            final java.util.Iterator<T> i = uk.co.jezuk.mango.iterators.Helpers.toIterator(o);
            list.add(i);
        }
        return list;
    }

    @java.lang.SuppressWarnings(value = "unchecked")
    public static <T> java.util.Iterator<T> toIterator(final java.lang.Object o) {
        if (o instanceof java.lang.Iterable)
            return ((java.lang.Iterable<T>) (o)).iterator();
        
        if (o instanceof java.util.Iterator)
            return ((java.util.Iterator<T>) (o));
        
        if ((o != null) && (o.getClass().isArray()))
            return new uk.co.jezuk.mango.iterators.ArrayIterator<T>(((T[]) (o)));
        
        return new uk.co.jezuk.mango.iterators.SingletonIterator<T>(((T) (o)));
    }
}

