

package uk.co.jezuk.mango.algorithms;


public class SymmetricDifference {
    public static <T, C extends java.util.Collection<? super T>> C execute(java.util.Iterator<? extends T> iter, java.util.Collection<? extends T> coll, C results) {
        java.util.Collection<T> intersection = new java.util.ArrayList<T>();
        while (iter.hasNext()) {
            T o = iter.next();
            if (!(coll.contains(o)))
                results.add(o);
            else
                intersection.add(o);
            
        } 
        iter = coll.iterator();
        while (iter.hasNext()) {
            T o = iter.next();
            if (!(intersection.contains(o)))
                results.add(o);
            
        } 
        return results;
    }

    public static <T, C extends java.util.Collection<? super T>> C execute(java.util.Iterator<? extends T> iter, java.util.Iterator<? extends T> iter2, C results) {
        java.util.Collection<T> coll = new java.util.ArrayList<T>();
        while (iter2.hasNext())
            coll.add(iter2.next());
        
        return uk.co.jezuk.mango.algorithms.SymmetricDifference.execute(iter, coll, results);
    }

    private SymmetricDifference() {
    }
}

