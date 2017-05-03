

package uk.co.jezuk.mango.algorithms;


public class Partition {
    public static <T, C extends java.util.Collection<? super T>> C execute(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> test, C results) {
        if ((iterator == null) || (test == null))
            return results;
        
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (test.test(obj)) {
                iterator.remove();
                if (results != null)
                    results.add(obj);
                
            }
        } 
        return results;
    }

    private Partition() {
    }
}

