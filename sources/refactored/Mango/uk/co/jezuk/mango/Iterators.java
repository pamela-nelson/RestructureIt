

package uk.co.jezuk.mango;


public class Iterators {
    public static java.util.Iterator<java.lang.String> StringIterator(java.lang.String s) {
        return new uk.co.jezuk.mango.iterators.StringIterator(s);
    }

    public static <T> java.util.Iterator<T> NullIterator() {
        return ((java.util.Iterator<T>) (uk.co.jezuk.mango.iterators.NullIterator.INSTANCE));
    }

    public static <T> java.util.Iterator<T> BoundedIterator(java.util.Iterator<? extends T> iterator, int start, int end) {
        return new uk.co.jezuk.mango.iterators.BoundedIterator<T>(iterator, start, end);
    }

    public static <T> java.util.Iterator<T> BoundedIterator(java.util.List<? extends T> list, int start, int end) {
        return new uk.co.jezuk.mango.iterators.BoundedIterator<T>(list, start, end);
    }

    public static <T> java.util.Iterator<T> SelectingIterator(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> predicate) {
        return new uk.co.jezuk.mango.iterators.SelectingIterator<T>(iterator, predicate);
    }

    public static <T> java.util.Iterator<T> SkippingIterator(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> predicate) {
        return new uk.co.jezuk.mango.iterators.SkippingIterator<T>(iterator, predicate);
    }

    public static <T> java.util.Iterator<T> ArrayIterator(T[] array) {
        return new uk.co.jezuk.mango.iterators.ArrayIterator<T>(array);
    }

    public static <T> java.util.Iterator<T> SingletonIterator(T object) {
        return new uk.co.jezuk.mango.iterators.SingletonIterator<T>(object);
    }

    public static <T, R> java.util.Iterator<R> TransformIterator(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Function<? super T, R> transform) {
        return new uk.co.jezuk.mango.iterators.TransformIterator<T, R>(iterator, transform);
    }

    public static <T> java.util.Iterator<T> ReverseIterator(java.util.List<? extends T> list) {
        return new uk.co.jezuk.mango.iterators.ReverseIterator<T>(list);
    }

    public static <T> java.util.Iterator<T> ChainIterator(final java.lang.Object... iterables) {
        return new uk.co.jezuk.mango.iterators.ChainIterator<T>(iterables);
    }

    public static <T> java.util.Iterator<java.util.List<T>> ZipIterator(final java.lang.Object... iterables) {
        return new uk.co.jezuk.mango.iterators.ZipIterator<T>(iterables);
    }

    public static <T> java.util.List<java.util.Iterator<T>> TeeIterator(final java.util.Iterator<T> iterator, final int count) {
        return uk.co.jezuk.mango.iterators.TeeIterator.wrap(iterator, count);
    }

    public static <T> java.util.Iterator<T> GeneratorIterator(final uk.co.jezuk.mango.Generator<? extends T> generator) {
        return new uk.co.jezuk.mango.iterators.GeneratorIterator<T>(generator);
    }

    private Iterators() {
    }
}

