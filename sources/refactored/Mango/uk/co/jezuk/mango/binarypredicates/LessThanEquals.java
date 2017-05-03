

package uk.co.jezuk.mango.binarypredicates;


public class LessThanEquals<T1, T2> implements uk.co.jezuk.mango.BinaryPredicate<T1, T2> {
    @java.lang.SuppressWarnings(value = "unchecked")
    public boolean test(T1 x, T2 y) {
        if ((x == null) && (y == null))
            return true;
        
        if (x == null)
            return true;
        
        if (y == null)
            return false;
        
        return (((java.lang.Comparable<T2>) (x)).compareTo(y)) <= 0;
    }
}

