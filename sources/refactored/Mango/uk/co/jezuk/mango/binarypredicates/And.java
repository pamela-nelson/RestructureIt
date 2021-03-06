

package uk.co.jezuk.mango.binarypredicates;


public class And<T1, T2> implements uk.co.jezuk.mango.BinaryPredicate<T1, T2> {
    public And(uk.co.jezuk.mango.BinaryPredicate<T1, T2> pred1, uk.co.jezuk.mango.BinaryPredicate<T1, T2> pred2) {
        p1_ = pred1;
        p2_ = pred2;
    }

    public boolean test(T1 x, T2 y) {
        return (p1_.test(x, y)) && (p2_.test(x, y));
    }

    private uk.co.jezuk.mango.BinaryPredicate<T1, T2> p1_;

    private uk.co.jezuk.mango.BinaryPredicate<T1, T2> p2_;
}

