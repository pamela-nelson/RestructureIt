

package uk.co.jezuk.mango.algorithms;


public class FindIfNot {
    public static <T> T execute(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> test) {
        if ((iterator == null) || (test == null))
            return null;
        
        java.util.Iterator<T> filter = new uk.co.jezuk.mango.iterators.SkippingIterator<T>(iterator, test);
        return filter.hasNext() ? filter.next() : null;
    }

    private FindIfNot() {
    }
}

