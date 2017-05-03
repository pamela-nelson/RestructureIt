

package uk.co.jezuk.mango.functions;


public class Constant<R> implements uk.co.jezuk.mango.Function<java.lang.Object, R> {
    private final R value_;

    public Constant(R value) {
        value_ = value;
    }

    public R fn(final java.lang.Object o) {
        return value_;
    }
}

