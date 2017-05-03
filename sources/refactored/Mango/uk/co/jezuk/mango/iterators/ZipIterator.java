

package uk.co.jezuk.mango.iterators;


public class ZipIterator<T> implements java.util.Iterator<java.util.List<T>> {
    private final java.util.List<java.util.Iterator<T>> zip_;

    public ZipIterator(final java.lang.Object... iterables) {
        zip_ = uk.co.jezuk.mango.iterators.Helpers.toIterators(iterables);
    }

    public java.util.List<T> next() {
        java.util.List<T> l = new java.util.ArrayList<T>();
        for (java.util.Iterator<T> iter : zip_)
            l.add(iter.next());
        
        return l;
    }

    public boolean hasNext() {
        for (java.util.Iterator<T> iter : zip_)
            if (!(iter.hasNext()))
                return false;
            
        
        return true;
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("uk.co.jezuk.mango.ZipIterator does not support the remove method");
    }
}

