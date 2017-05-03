

package uk.co.jezuk.mango.binarypredicates;


public class NotEqualTo<T1, T2> implements uk.co.jezuk.mango.BinaryPredicate<T1, T2> {
    public boolean test(T1 x, T2 y) {
        if ((x == null) && (y == null))
            return false;
        
        if ((x == null) || (y == null))
            return true;
        
        return !(x.equals(y));
    }
}

