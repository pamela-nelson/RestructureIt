

package uk.co.jezuk.mango.iterators;


public class ChainIterator<T> implements java.util.Iterator<T> {
    private final java.util.Iterator<java.util.Iterator<T>> chain_;

    private java.util.Iterator<T> current_;

    public ChainIterator(final java.lang.Object... iterables) {
        final java.util.List<java.util.Iterator<T>> list = uk.co.jezuk.mango.iterators.Helpers.toIterators(iterables);
        chain_ = list.iterator();
        current_ = (chain_.hasNext()) ? chain_.next() : null;
    }

    public boolean hasNext() {
        if ((current_) == null)
            return false;
        
        if (current_.hasNext())
            return true;
        
        current_ = (chain_.hasNext()) ? chain_.next() : null;
        return hasNext();
    }

    public T next() {
        return current_.next();
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("uk.co.jezuk.mango.ChainIterator does not support the remove method");
    }
}

