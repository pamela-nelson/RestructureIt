

package uk.co.jezuk.mango.iterators;


public class ReverseIterator<T> implements java.util.Iterator<T> {
    @java.lang.SuppressWarnings(value = "unchecked")
    public ReverseIterator(java.util.List<? extends T> list) {
        iter_ = ((java.util.ListIterator<T>) (list.listIterator(list.size())));
    }

    public boolean hasNext() {
        return getIter_().hasPrevious();
    }

    public T next() {
        return getIter_().previous();
    }

    public void remove() {
        getIter_().remove();
    }

    private java.util.ListIterator<T> iter_;

    public java.util.List getIter_() {
        return java.util.Collections.unmodifiableList(iter_);
    }

    public void addIter(T iter) {
        iter_.add(iter);
    }

    public void removeIter(T iter) {
        iter_.remove(iter);
    }
}

