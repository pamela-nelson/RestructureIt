

package uk.co.jezuk.mango;


public class Functions {
    public static <T, T2, R> uk.co.jezuk.mango.Function<T, R> Compose(final uk.co.jezuk.mango.Function<T2, R> f, final uk.co.jezuk.mango.Function<T, T2> g) {
        return new uk.co.jezuk.mango.Function<T, R>() {
            public R fn(final T x) {
                return f.fn(g.fn(x));
            }
        };
    }

    public static <T> uk.co.jezuk.mango.Function<T, T> Identity() {
        return ((uk.co.jezuk.mango.Function<T, T>) (uk.co.jezuk.mango.functions.Identity.INSTANCE));
    }

    public static <T, R> uk.co.jezuk.mango.Function<T, R> Constant(R value) {
        return ((uk.co.jezuk.mango.Function<T, R>) (new uk.co.jezuk.mango.functions.Constant<R>(value)));
    }
}

