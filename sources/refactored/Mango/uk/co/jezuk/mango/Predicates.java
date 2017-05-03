

package uk.co.jezuk.mango;


public class Predicates {
    public static <T> uk.co.jezuk.mango.Predicate<T> True() {
        return ((uk.co.jezuk.mango.Predicate<T>) (uk.co.jezuk.mango.unarypredicates.True.INSTANCE));
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> False() {
        return ((uk.co.jezuk.mango.Predicate<T>) (uk.co.jezuk.mango.unarypredicates.False.INSTANCE));
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Constant(boolean constant) {
        return constant ? ((uk.co.jezuk.mango.Predicate<T>) (uk.co.jezuk.mango.unarypredicates.True.INSTANCE)) : ((uk.co.jezuk.mango.Predicate<T>) (uk.co.jezuk.mango.unarypredicates.False.INSTANCE));
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Not(uk.co.jezuk.mango.Predicate<T> pred) {
        return new uk.co.jezuk.mango.unarypredicates.Not<T>(pred);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> And(uk.co.jezuk.mango.Predicate<T> pred1, uk.co.jezuk.mango.Predicate<T> pred2) {
        return new uk.co.jezuk.mango.unarypredicates.And<T>(pred1, pred2);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Or(uk.co.jezuk.mango.Predicate<T> pred1, uk.co.jezuk.mango.Predicate<T> pred2) {
        return new uk.co.jezuk.mango.unarypredicates.Or<T>(pred1, pred2);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Xor(uk.co.jezuk.mango.Predicate<T> pred1, uk.co.jezuk.mango.Predicate<T> pred2) {
        return new uk.co.jezuk.mango.unarypredicates.Xor<T>(pred1, pred2);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Nand(uk.co.jezuk.mango.Predicate<T> pred1, uk.co.jezuk.mango.Predicate<T> pred2) {
        return new uk.co.jezuk.mango.unarypredicates.Nand<T>(pred1, pred2);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Nor(uk.co.jezuk.mango.Predicate<T> pred1, uk.co.jezuk.mango.Predicate<T> pred2) {
        return new uk.co.jezuk.mango.unarypredicates.Nor<T>(pred1, pred2);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Xnor(uk.co.jezuk.mango.Predicate<T> pred1, uk.co.jezuk.mango.Predicate<T> pred2) {
        return new uk.co.jezuk.mango.unarypredicates.Xnor<T>(pred1, pred2);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> All(java.lang.Object... preds) {
        return uk.co.jezuk.mango.Predicates.All(new uk.co.jezuk.mango.iterators.ArrayIterator<uk.co.jezuk.mango.Predicate<T>>(preds));
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> All(java.util.Collection<uk.co.jezuk.mango.Predicate<T>> preds) {
        return uk.co.jezuk.mango.Predicates.All(preds.iterator());
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> All(java.util.Iterator<uk.co.jezuk.mango.Predicate<T>> preds) {
        return new uk.co.jezuk.mango.unarypredicates.All<T>(preds);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Any(java.lang.Object... preds) {
        return uk.co.jezuk.mango.Predicates.Any(new uk.co.jezuk.mango.iterators.ArrayIterator<uk.co.jezuk.mango.Predicate<T>>(preds));
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Any(java.util.Collection<uk.co.jezuk.mango.Predicate<T>> preds) {
        return uk.co.jezuk.mango.Predicates.Any(preds.iterator());
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> Any(java.util.Iterator<uk.co.jezuk.mango.Predicate<T>> preds) {
        return new uk.co.jezuk.mango.unarypredicates.Any<T>(preds);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> OneOf(java.lang.Object... preds) {
        return uk.co.jezuk.mango.Predicates.OneOf(new uk.co.jezuk.mango.iterators.ArrayIterator<uk.co.jezuk.mango.Predicate<T>>(preds));
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> OneOf(java.util.Collection<uk.co.jezuk.mango.Predicate<T>> preds) {
        return uk.co.jezuk.mango.Predicates.OneOf(preds.iterator());
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> OneOf(java.util.Iterator<uk.co.jezuk.mango.Predicate<T>> preds) {
        return new uk.co.jezuk.mango.unarypredicates.OneOf<T>(preds);
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> IsNull() {
        return ((uk.co.jezuk.mango.Predicate<T>) (uk.co.jezuk.mango.unarypredicates.IsNull.INSTANCE));
    }

    public static <T> uk.co.jezuk.mango.Predicate<T> NotNull() {
        return ((uk.co.jezuk.mango.Predicate<T>) (uk.co.jezuk.mango.unarypredicates.NotNull.INSTANCE));
    }

    private Predicates() {
    }
}

