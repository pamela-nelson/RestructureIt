

package uk.co.jezuk.mango;


public class BinaryPredicates {
    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> True() {
        return ((uk.co.jezuk.mango.BinaryPredicate<T1, T2>) (uk.co.jezuk.mango.binarypredicates.True.INSTANCE));
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> False() {
        return ((uk.co.jezuk.mango.BinaryPredicate<T1, T2>) (uk.co.jezuk.mango.binarypredicates.False.INSTANCE));
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> Constant(boolean constant) {
        return constant ? ((uk.co.jezuk.mango.BinaryPredicate<T1, T2>) (uk.co.jezuk.mango.binarypredicates.True.INSTANCE)) : ((uk.co.jezuk.mango.BinaryPredicate<T1, T2>) (uk.co.jezuk.mango.binarypredicates.False.INSTANCE));
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> EqualTo() {
        return new uk.co.jezuk.mango.binarypredicates.EqualTo<T1, T2>();
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> GreaterThan() {
        return new uk.co.jezuk.mango.binarypredicates.GreaterThan<T1, T2>();
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> GreaterThanEquals() {
        return new uk.co.jezuk.mango.binarypredicates.GreaterThanEquals<T1, T2>();
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> LessThan() {
        return new uk.co.jezuk.mango.binarypredicates.LessThan<T1, T2>();
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> LessThanEquals() {
        return new uk.co.jezuk.mango.binarypredicates.LessThanEquals<T1, T2>();
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> NotEqualTo() {
        return new uk.co.jezuk.mango.binarypredicates.NotEqualTo<T1, T2>();
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> Not(uk.co.jezuk.mango.BinaryPredicate<T1, T2> pred) {
        return new uk.co.jezuk.mango.binarypredicates.Not<T1, T2>(pred);
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> And(uk.co.jezuk.mango.BinaryPredicate<T1, T2> pred1, uk.co.jezuk.mango.BinaryPredicate<T1, T2> pred2) {
        return new uk.co.jezuk.mango.binarypredicates.And<T1, T2>(pred1, pred2);
    }

    public static <T1, T2> uk.co.jezuk.mango.BinaryPredicate<T1, T2> Or(uk.co.jezuk.mango.BinaryPredicate<T1, T2> pred1, uk.co.jezuk.mango.BinaryPredicate<T1, T2> pred2) {
        return new uk.co.jezuk.mango.binarypredicates.Or<T1, T2>(pred1, pred2);
    }

    private BinaryPredicates() {
    }
}

