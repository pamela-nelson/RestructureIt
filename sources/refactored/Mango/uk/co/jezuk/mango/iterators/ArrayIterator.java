

package uk.co.jezuk.mango.iterators;


public class ArrayIterator<T> implements java.util.Iterator<T> {
    @java.lang.SuppressWarnings(value = "unchecked")
    public ArrayIterator(final java.lang.Object... array) {
        if (array != null)
            for (int i = 0; i != (array.length); ++i) {
                java.lang.Object c = ((T) (array[i]));
            }
        
        array_ = ((T[]) (array));
        index_ = 0;
    }

    public boolean hasNext() {
        return ((array_) != null) && ((index_) != (array_.length));
    }

    public T next() {
        if (((array_) == null) || ((index_) >= (array_.length)))
            throw new java.util.NoSuchElementException();
        
        return array_[((index_)++)];
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("uk.co.jezuk.mango.ArrayIterator does not support the remove method");
    }

    private final T[] array_;

    private int index_;
}

