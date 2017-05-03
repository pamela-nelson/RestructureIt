

package uk.co.jezuk.mango.iterators;


public class StringIterator implements java.util.Iterator<java.lang.String> {
    public StringIterator(java.lang.String s) {
        s_ = s;
        if (((s_) != null) && ((s_.length()) > 0))
            pos_ = 0;
        
    }

    public boolean hasNext() {
        return (pos_) != (-1);
    }

    public java.lang.String next() {
        java.lang.String c = s_.substring(pos_, ((pos_) + 1));
        if ((++(pos_)) == (s_.length()))
            pos_ = -1;
        
        return c;
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("uk.co.jezuk.mango.StringIterator does not support the remove method.  In fact it's probably a logic error that you called it at all.  Strings are immutable");
    }

    private java.lang.String s_;

    private int pos_ = -1;
}

