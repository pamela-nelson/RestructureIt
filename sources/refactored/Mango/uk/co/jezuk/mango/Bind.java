

package uk.co.jezuk.mango;


public class Bind {
    public static <T1, T2, R> uk.co.jezuk.mango.Function<T2, R> First(final uk.co.jezuk.mango.BinaryFunction<T1, T2, R> f, final T1 c) {
        return new uk.co.jezuk.mango.Function<T2, R>() {
            private final uk.co.jezuk.mango.BinaryFunction<T1, T2, R> fn_;

            private final T1 c_;

            {
                fn_ = f;
                c_ = c;
            }

            public R fn(final T2 arg) {
                return fn_.fn(c_, arg);
            }
        };
    }

    public static <T1, T2> uk.co.jezuk.mango.Predicate<T2> First(final uk.co.jezuk.mango.BinaryPredicate<T1, T2> p, final T1 c) {
        return new uk.co.jezuk.mango.Predicate<T2>() {
            private uk.co.jezuk.mango.BinaryPredicate<T1, T2> test_;

            private T1 c_;

            {
                test_ = p;
                c_ = c;
            }

            public boolean test(final T2 arg) {
                return test_.test(c_, arg);
            }
        };
    }

    public static <T1, T2, R> uk.co.jezuk.mango.Function<T1, R> Second(final uk.co.jezuk.mango.BinaryFunction<T1, T2, R> f, final T2 c) {
        return new uk.co.jezuk.mango.Function<T1, R>() {
            private final uk.co.jezuk.mango.BinaryFunction<T1, T2, R> fn_;

            private final T2 c_;

            {
                fn_ = f;
                c_ = c;
            }

            public R fn(T1 arg) {
                return fn_.fn(arg, c_);
            }
        };
    }

    public static <T1, T2> uk.co.jezuk.mango.Predicate<T1> Second(final uk.co.jezuk.mango.BinaryPredicate<T1, T2> p, final T2 c) {
        return new uk.co.jezuk.mango.Predicate<T1>() {
            private final uk.co.jezuk.mango.BinaryPredicate<T1, T2> test_;

            private final T2 c_;

            {
                test_ = p;
                c_ = c;
            }

            public boolean test(final T1 arg) {
                return test_.test(arg, c_);
            }
        };
    }

    private Bind() {
    }
}

