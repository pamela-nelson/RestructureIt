

package uk.co.jezuk.mango.iterators;


public class GeneratorIterator<T> implements java.util.Iterator<T> {
    private final uk.co.jezuk.mango.Generator<? extends T> generator_;

    public GeneratorIterator(final uk.co.jezuk.mango.Generator<? extends T> generator) {
        generator_ = generator;
    }

    public T next() {
        return generator_.fn();
    }

    public boolean hasNext() {
        return true;
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("uk.co.jezuk.mango.GeneratorIterator does not support the remove method");
    }
}

