

package uk.co.jezuk.mango.algorithms;


public class Transform {
    public static <T, R, C extends java.util.Collection<? super R>> C execute(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Function<? super T, R> fn, C results) {
        if (((iterator == null) || (fn == null)) || (results == null))
            return results;
        
        while (iterator.hasNext()) {
            R o = fn.fn(iterator.next());
            results.add(o);
        } 
        return results;
    }

    private Transform() {
    }
}

