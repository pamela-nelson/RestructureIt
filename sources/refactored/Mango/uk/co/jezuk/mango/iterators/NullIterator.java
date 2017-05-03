

package uk.co.jezuk.mango.iterators;


public enum NullIterator implements java.util.Iterator<java.lang.Object> {
INSTANCE;
    public boolean hasNext() {
        return false;
    }

    public java.lang.Object next() {
        throw new java.util.NoSuchElementException("uk.co.jezuk.mango.NullIterator does not support the next method.  In fact it's probably a logic error that you called it at all.");
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("uk.co.jezuk.mango.NullIterator does not support the remove method.  In fact it's probably a logic error that you called it at all.");
    }
}

