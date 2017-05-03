

package uk.co.jezuk.mango.algorithms;


public class CountIfNot {
    public static <T> int execute(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> test) {
        if ((iterator == null) || (test == null))
            return 0;
        
        int c = 0;
        for (java.util.Iterator<T> filter = new uk.co.jezuk.mango.iterators.SkippingIterator<T>(iterator, test); filter.hasNext(); filter.next() , ++c);
        return c;
    }

    private CountIfNot() {
    }
}

