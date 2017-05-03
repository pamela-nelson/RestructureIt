

package uk.co.jezuk.mango.iterators;


public class SkippingIterator<T> implements java.util.Iterator<T> {
    public SkippingIterator(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> predicate) {
        iter_ = iterator;
        pred_ = predicate;
        findNext();
    }

    public boolean hasNext() {
        return (next_) != null;
    }

    public T next() {
        T current = next_;
        findNext();
        return current;
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("SkippingIterator does not support the remove method");
    }

    private void findNext() {
        next_ = null;
        while ((iter_.hasNext()) && ((next_) == null)) {
            T candidate = iter_.next();
            if (!(pred_.test(candidate)))
                next_ = candidate;
            
        } 
    }

    private final java.util.Iterator<T> iter_;

    private final uk.co.jezuk.mango.Predicate<? super T> pred_;

    private T next_;
}

