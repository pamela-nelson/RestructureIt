

package uk.co.jezuk.mango.algorithms;


public class Intersection {
    public static <T, C extends java.util.Collection<? super T>> C execute(java.util.Iterator<? extends T> iter, java.util.Collection<? extends T> coll, C results) {
        while (iter.hasNext()) {
            T o = iter.next();
            if (coll.contains(o))
                results.add(o);
            
        } 
        return results;
    }

    public static <T, C extends java.util.Collection<? super T>> C execute(java.util.Iterator<? extends T> iter, java.util.Iterator<? extends T> iter2, C results) {
        java.util.Collection<T> coll = new java.util.ArrayList<T>();
        while (iter2.hasNext())
            coll.add(iter2.next());
        
        return uk.co.jezuk.mango.algorithms.Intersection.execute(iter, coll, results);
    }

    private Intersection() {
    }
}

