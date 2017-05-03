

package uk.co.jezuk.mango.iterators;


public class SelectingIterator<T> implements java.util.Iterator<T> {
    public SelectingIterator(final java.util.Iterator<T> iterator, final uk.co.jezuk.mango.Predicate<? super T> predicate) {
        iter_ = iterator;
        pred_ = predicate;
    }

    public boolean hasNext() {
        valid_ = false;
        while ((iter_.hasNext()) && ((valid_) == false)) {
            T candidate = iter_.next();
            if (pred_.test(candidate)) {
                next_ = candidate;
                valid_ = true;
            }
        } 
        return valid_;
    }

    public T next() {
        if (!(valid_))
            throw new java.util.NoSuchElementException();
        
        return next_;
    }

    public void remove() {
        iter_.remove();
    }

    private final java.util.Iterator<T> iter_;

    private final uk.co.jezuk.mango.Predicate<? super T> pred_;

    private boolean valid_;

    private T next_;
}

