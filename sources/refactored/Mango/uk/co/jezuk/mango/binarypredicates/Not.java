

package uk.co.jezuk.mango.binarypredicates;


public class Not<T1, T2> implements uk.co.jezuk.mango.BinaryPredicate<T1, T2> {
    public Not(uk.co.jezuk.mango.BinaryPredicate<T1, T2> p) {
        p_ = p;
    }

    public boolean test(T1 x, T2 y) {
        return !(p_.test(x, y));
    }

    private uk.co.jezuk.mango.BinaryPredicate<T1, T2> p_;
}

