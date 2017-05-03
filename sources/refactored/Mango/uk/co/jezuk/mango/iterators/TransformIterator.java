

package uk.co.jezuk.mango.iterators;


public class TransformIterator<T, R> implements java.util.Iterator<R> {
    public TransformIterator(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Function<? super T, R> transform) {
        iter_ = iterator;
        transform_ = transform;
    }

    public boolean hasNext() {
        return iter_.hasNext();
    }

    public R next() {
        return transform_.fn(iter_.next());
    }

    public void remove() {
        iter_.remove();
    }

    private java.util.Iterator<T> iter_;

    private uk.co.jezuk.mango.Function<? super T, R> transform_;
}

