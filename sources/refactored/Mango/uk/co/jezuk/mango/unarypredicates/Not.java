

package uk.co.jezuk.mango.unarypredicates;


public class Not<T> implements uk.co.jezuk.mango.Predicate<T> {
    public Not(uk.co.jezuk.mango.Predicate<T> p) {
        p_ = p;
    }

    public boolean test(T x) {
        return !(p_.test(x));
    }

    private uk.co.jezuk.mango.Predicate<T> p_;
}

