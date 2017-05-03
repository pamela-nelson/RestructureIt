

package uk.co.jezuk.mango;


public class BinaryFunctions {
    public static <T1, R1, T2, R2, R> uk.co.jezuk.mango.BinaryFunction<T1, T2, R> Compose(final uk.co.jezuk.mango.BinaryFunction<R1, R2, R> f, final uk.co.jezuk.mango.Function<T1, R1> g1, final uk.co.jezuk.mango.Function<T2, R2> g2) {
        return new uk.co.jezuk.mango.BinaryFunction<T1, T2, R>() {
            public R fn(final T1 x, final T2 y) {
                return f.fn(g1.fn(x), g2.fn(y));
            }
        };
    }

    private BinaryFunctions() {
    }
}

