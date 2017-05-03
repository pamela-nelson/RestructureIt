

package uk.co.jezuk.mango.unarypredicates;


public class Xor<T> implements uk.co.jezuk.mango.Predicate<T> {
    public Xor(final uk.co.jezuk.mango.Predicate<T> pred1, final uk.co.jezuk.mango.Predicate<T> pred2) {
        p1_ = pred1;
        p2_ = pred2;
    }

    public boolean test(final T x) {
        return (p1_.test(x)) != (p2_.test(x));
    }

    private final uk.co.jezuk.mango.Predicate<T> p1_;

    private final uk.co.jezuk.mango.Predicate<T> p2_;
}

