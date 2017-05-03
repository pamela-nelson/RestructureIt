

package uk.co.jezuk.mango.iterators;


public class SingletonIterator<T> implements java.util.Iterator<T> {
    public SingletonIterator(T object) {
        object_ = object;
        spent_ = false;
    }

    public boolean hasNext() {
        return (spent_) == false;
    }

    public T next() {
        if (spent_)
            throw new java.util.NoSuchElementException();
        
        spent_ = true;
        return object_;
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("uk.co.jezuk.mango.SingletonIterator does not support the remove method");
    }

    private final T object_;

    private boolean spent_;
}

