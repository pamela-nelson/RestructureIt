

package uk.co.jezuk.mango.unarypredicates;


public class Nor<T> implements uk.co.jezuk.mango.Predicate<T> {
    public Nor(uk.co.jezuk.mango.Predicate<T> pred1, uk.co.jezuk.mango.Predicate<T> pred2) {
        p1_ = pred1;
        p2_ = pred2;
    }

    public boolean test(T x) {
        return !((p1_.test(x)) || (p2_.test(x)));
    }

    private uk.co.jezuk.mango.Predicate<T> p1_;

    private uk.co.jezuk.mango.Predicate<T> p2_;
}

