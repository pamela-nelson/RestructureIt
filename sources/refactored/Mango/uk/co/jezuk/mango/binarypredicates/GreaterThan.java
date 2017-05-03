

package uk.co.jezuk.mango.binarypredicates;


public class GreaterThan<T1, T2> implements uk.co.jezuk.mango.BinaryPredicate<T1, T2> {
    @java.lang.SuppressWarnings(value = "unchecked")
    public boolean test(T1 x, T2 y) {
        if (x == null)
            return false;
        
        if (y == null)
            return true;
        
        return (((java.lang.Comparable<T2>) (x)).compareTo(y)) > 0;
    }
}

