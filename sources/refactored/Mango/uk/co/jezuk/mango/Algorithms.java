

package uk.co.jezuk.mango;


public class Algorithms {
    public static <T> java.util.List<T> intersection(java.util.Collection<? extends T> coll1, java.util.Collection<? extends T> coll2) {
        return uk.co.jezuk.mango.algorithms.Intersection.execute(coll1.iterator(), coll2, new java.util.ArrayList<T>());
    }

    public static <T, C extends java.util.Collection<? super T>> C intersection(java.util.Collection<? extends T> coll1, java.util.Collection<? extends T> coll2, C results) {
        return uk.co.jezuk.mango.algorithms.Intersection.execute(coll1.iterator(), coll2, results);
    }

    public static <T> java.util.List<T> intersection(java.util.Iterator<? extends T> iter1, java.util.Collection<? extends T> coll2) {
        return uk.co.jezuk.mango.algorithms.Intersection.execute(iter1, coll2, new java.util.ArrayList<T>());
    }

    public static <T, C extends java.util.Collection<? super T>> C intersection(java.util.Iterator<? extends T> iter1, java.util.Collection<? extends T> coll2, C results) {
        return uk.co.jezuk.mango.algorithms.Intersection.execute(iter1, coll2, results);
    }

    public static <T> java.util.List<T> intersection(java.util.Iterator<? extends T> iter1, java.util.Iterator<? extends T> iter2) {
        return uk.co.jezuk.mango.algorithms.Intersection.execute(iter1, iter2, new java.util.ArrayList<T>());
    }

    public static <T, C extends java.util.Collection<? super T>> C intersection(java.util.Iterator<? extends T> iter1, java.util.Iterator<? extends T> iter2, C results) {
        return uk.co.jezuk.mango.algorithms.Intersection.execute(iter1, iter2, results);
    }

    public static <T> java.util.List<T> symmetricDifference(java.util.Collection<? extends T> coll1, java.util.Collection<? extends T> coll2) {
        return uk.co.jezuk.mango.algorithms.SymmetricDifference.execute(coll1.iterator(), coll2, new java.util.ArrayList<T>());
    }

    public static <T, C extends java.util.Collection<? super T>> C symmetricDifference(java.util.Collection<? extends T> coll1, java.util.Collection<? extends T> coll2, C results) {
        return uk.co.jezuk.mango.algorithms.SymmetricDifference.execute(coll1.iterator(), coll2, results);
    }

    public static <T> java.util.List<T> symmetricDifference(java.util.Iterator<? extends T> iter1, java.util.Collection<? extends T> coll2) {
        return uk.co.jezuk.mango.algorithms.SymmetricDifference.execute(iter1, coll2, new java.util.ArrayList<T>());
    }

    public static <T, C extends java.util.Collection<? super T>> C symmetricDifference(java.util.Iterator<? extends T> iter1, java.util.Collection<? extends T> coll2, C results) {
        return uk.co.jezuk.mango.algorithms.SymmetricDifference.execute(iter1, coll2, results);
    }

    public static <T> java.util.List<T> symmetricDifference(java.util.Iterator<? extends T> iter1, java.util.Iterator<? extends T> iter2) {
        return uk.co.jezuk.mango.algorithms.SymmetricDifference.execute(iter1, iter2, new java.util.ArrayList<T>());
    }

    public static <T, R> void forEach(java.util.Collection<T> collection, uk.co.jezuk.mango.Function<? super T, R> fn) {
        uk.co.jezuk.mango.algorithms.ForEach.execute(collection.iterator(), fn);
    }

    public static <T, R> void forEach(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Function<? super T, R> fn) {
        uk.co.jezuk.mango.algorithms.ForEach.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), fn);
    }

    public static <T, R> void forEach(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Function<? super T, R> fn) {
        uk.co.jezuk.mango.algorithms.ForEach.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), fn);
    }

    public static <T, R> void forEach(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Function<? super T, R> fn) {
        uk.co.jezuk.mango.algorithms.ForEach.execute(iterator, fn);
    }

    public static <T, R> java.util.List<R> transform(java.util.Collection<T> collection, uk.co.jezuk.mango.Function<? super T, R> fn) {
        return uk.co.jezuk.mango.algorithms.Transform.execute(collection.iterator(), fn, new java.util.ArrayList<R>());
    }

    public static <T, R, C extends java.util.Collection<? super R>> C transform(java.util.Collection<T> collection, uk.co.jezuk.mango.Function<? super T, R> fn, C results) {
        return uk.co.jezuk.mango.algorithms.Transform.execute(collection.iterator(), fn, results);
    }

    public static <T, R> java.util.List<R> transform(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Function<? super T, R> fn) {
        return uk.co.jezuk.mango.algorithms.Transform.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), fn, new java.util.ArrayList<R>());
    }

    public static <T, R, C extends java.util.Collection<? super R>> C transform(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Function<? super T, R> fn, C results) {
        return uk.co.jezuk.mango.algorithms.Transform.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), fn, results);
    }

    public static <T, R> java.util.List<R> transform(java.util.List<T> collection, int start, int end, uk.co.jezuk.mango.Function<? super T, R> fn) {
        return uk.co.jezuk.mango.algorithms.Transform.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection, start, end), fn, new java.util.ArrayList<R>());
    }

    public static <T, R, C extends java.util.Collection<? super R>> C transform(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Function<? super T, R> fn, C results) {
        return uk.co.jezuk.mango.algorithms.Transform.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), fn, results);
    }

    public static <T, R> java.util.List<R> transform(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Function<? super T, R> fn) {
        return uk.co.jezuk.mango.algorithms.Transform.execute(iterator, fn, new java.util.ArrayList<R>());
    }

    public static <T, R, C extends java.util.Collection<? super R>> C transform(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Function<? super T, R> fn, C results) {
        return uk.co.jezuk.mango.algorithms.Transform.execute(iterator, fn, results);
    }

    public static <T> int count(java.util.Collection<? extends T> collection, T value) {
        return uk.co.jezuk.mango.algorithms.Count.execute(collection.iterator(), value);
    }

    public static <T> int count(java.util.Collection<? extends T> collection, int start, int end, T value) {
        return uk.co.jezuk.mango.algorithms.Count.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), value);
    }

    public static <T> int count(java.util.List<? extends T> list, int start, int end, T value) {
        return uk.co.jezuk.mango.algorithms.Count.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), value);
    }

    public static <T> int count(java.util.Iterator<? extends T> iterator, T value) {
        return uk.co.jezuk.mango.algorithms.Count.execute(iterator, value);
    }

    public static <T> int countIf(java.util.Collection<T> collection, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.CountIf.execute(collection.iterator(), test);
    }

    public static <T> int countIf(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.CountIf.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), test);
    }

    public static <T> int countIf(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.CountIf.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), test);
    }

    public static <T> int countIf(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.CountIf.execute(iterator, test);
    }

    public static <T> int countIfNot(java.util.Collection<T> collection, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.CountIfNot.execute(collection.iterator(), test);
    }

    public static <T> int countIfNot(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.CountIfNot.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), test);
    }

    public static <T> int countIfNot(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.CountIfNot.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), test);
    }

    public static <T> int countIfNot(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.CountIfNot.execute(iterator, test);
    }

    public static <T> T find(java.util.Collection<? extends T> collection, T value) {
        return uk.co.jezuk.mango.algorithms.Find.execute(collection.iterator(), value);
    }

    public static <T> T find(java.util.Collection<? extends T> collection, int start, int end, T value) {
        return uk.co.jezuk.mango.algorithms.Find.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), value);
    }

    public static <T> T find(java.util.List<? extends T> list, int start, int end, T value) {
        return uk.co.jezuk.mango.algorithms.Find.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), value);
    }

    public static <T> T find(java.util.Iterator<? extends T> iterator, T value) {
        return uk.co.jezuk.mango.algorithms.Find.execute(iterator, value);
    }

    public static <T> int findPosition(java.util.Collection<? extends T> collection, T value) {
        return uk.co.jezuk.mango.algorithms.FindPosition.execute(collection.iterator(), value);
    }

    public static <T> int findPosition(java.util.Collection<? extends T> collection, int start, int end, T value) {
        return uk.co.jezuk.mango.algorithms.FindPosition.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), value);
    }

    public static <T> int findPosition(java.util.List<? extends T> list, int start, int end, T value) {
        return uk.co.jezuk.mango.algorithms.FindPosition.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), value);
    }

    public static <T> int findPosition(java.util.Iterator<? extends T> iterator, T value) {
        return uk.co.jezuk.mango.algorithms.FindPosition.execute(iterator, value);
    }

    public static <T> int findPositionIf(java.util.Collection<T> collection, uk.co.jezuk.mango.Predicate<? super T> pred) {
        return uk.co.jezuk.mango.algorithms.FindPositionIf.execute(collection.iterator(), pred);
    }

    public static <T> int findPositionIf(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Predicate<? super T> pred) {
        return uk.co.jezuk.mango.algorithms.FindPositionIf.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), pred);
    }

    public static <T> int findPositionIf(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Predicate<? super T> pred) {
        return uk.co.jezuk.mango.algorithms.FindPositionIf.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), pred);
    }

    public static <T> int findPositionIf(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> pred) {
        return uk.co.jezuk.mango.algorithms.FindPositionIf.execute(iterator, pred);
    }

    public static <T> T findIf(java.util.Collection<T> collection, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.FindIf.execute(collection.iterator(), test);
    }

    public static <T> T findIf(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.FindIf.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), test);
    }

    public static <T> T findIf(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.FindIf.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), test);
    }

    public static <T> T findIf(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.FindIf.execute(iterator, test);
    }

    public static <T> T findIfNot(java.util.Collection<T> collection, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.FindIfNot.execute(collection.iterator(), test);
    }

    public static <T> T findIfNot(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.FindIfNot.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), test);
    }

    public static <T> T findIfNot(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.FindIfNot.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), test);
    }

    public static <T> T findIfNot(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> test) {
        return uk.co.jezuk.mango.algorithms.FindIfNot.execute(iterator, test);
    }

    public static <T> void remove(java.util.Collection<? extends T> collection, T value) {
        uk.co.jezuk.mango.algorithms.Remove.execute(collection.iterator(), value);
    }

    public static <T> void remove(java.util.Collection<? extends T> collection, int start, int end, T value) {
        uk.co.jezuk.mango.algorithms.Remove.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), value);
    }

    public static <T> void remove(java.util.List<? extends T> list, int start, int end, T value) {
        uk.co.jezuk.mango.algorithms.Remove.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), value);
    }

    public static <T> void remove(java.util.Iterator<? extends T> iterator, T value) {
        uk.co.jezuk.mango.algorithms.Remove.execute(iterator, value);
    }

    public static <T> void removeIf(java.util.Collection<T> collection, uk.co.jezuk.mango.Predicate<? super T> pred) {
        uk.co.jezuk.mango.algorithms.RemoveIf.execute(collection.iterator(), pred);
    }

    public static <T> void removeIf(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Predicate<? super T> pred) {
        uk.co.jezuk.mango.algorithms.RemoveIf.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), pred);
    }

    public static <T> void removeIf(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Predicate<? super T> pred) {
        uk.co.jezuk.mango.algorithms.RemoveIf.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), pred);
    }

    public static <T> void removeIf(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> pred) {
        uk.co.jezuk.mango.algorithms.RemoveIf.execute(iterator, pred);
    }

    public static <T> java.util.List<T> partition(java.util.Collection<T> collection, uk.co.jezuk.mango.Predicate<? super T> pred) {
        return uk.co.jezuk.mango.algorithms.Partition.execute(collection.iterator(), pred, new java.util.ArrayList<T>());
    }

    public static <T, C extends java.util.Collection<? super T>> C partition(java.util.Collection<T> collection, uk.co.jezuk.mango.Predicate<? super T> pred, C results) {
        return uk.co.jezuk.mango.algorithms.Partition.execute(collection.iterator(), pred, results);
    }

    public static <T> java.util.List<T> partition(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Predicate<? super T> pred) {
        return uk.co.jezuk.mango.algorithms.Partition.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), pred, new java.util.ArrayList<T>());
    }

    public static <T, C extends java.util.Collection<? super T>> C partition(java.util.Collection<T> collection, int start, int end, uk.co.jezuk.mango.Predicate<? super T> pred, C results) {
        return uk.co.jezuk.mango.algorithms.Partition.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), pred, results);
    }

    public static <T> java.util.List<T> partition(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Predicate<? super T> pred) {
        return uk.co.jezuk.mango.algorithms.Partition.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), pred, new java.util.ArrayList<T>());
    }

    public static <T, C extends java.util.Collection<? super T>> C partition(java.util.List<T> list, int start, int end, uk.co.jezuk.mango.Predicate<? super T> pred, C results) {
        return uk.co.jezuk.mango.algorithms.Partition.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), pred, results);
    }

    public static <T> java.util.List<T> partition(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> pred) {
        return uk.co.jezuk.mango.algorithms.Partition.execute(iterator, pred, new java.util.ArrayList<T>());
    }

    public static <T, C extends java.util.Collection<? super T>> C partition(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> pred, C results) {
        return uk.co.jezuk.mango.algorithms.Partition.execute(iterator, pred, results);
    }

    public static <T> void unique(java.util.Collection<T> collection) {
        uk.co.jezuk.mango.algorithms.Unique.execute(collection.iterator(), null);
    }

    public static <T> void unique(java.util.Collection<T> collection, int start, int end) {
        uk.co.jezuk.mango.algorithms.Unique.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), null);
    }

    public static <T> void unique(java.util.List<T> list, int start, int end) {
        uk.co.jezuk.mango.algorithms.Unique.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), null);
    }

    public static <T> void unique(java.util.Iterator<T> iterator) {
        uk.co.jezuk.mango.algorithms.Unique.execute(iterator, null);
    }

    public static <T> void unique(java.util.Collection<T> collection, java.util.Comparator<? super T> comparator) {
        uk.co.jezuk.mango.algorithms.Unique.execute(collection.iterator(), comparator);
    }

    public static <T> void unique(java.util.Collection<T> collection, int start, int end, java.util.Comparator<? super T> comparator) {
        uk.co.jezuk.mango.algorithms.Unique.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(collection.iterator(), start, end), comparator);
    }

    public static <T> void unique(java.util.List<T> list, int start, int end, java.util.Comparator<? super T> comparator) {
        uk.co.jezuk.mango.algorithms.Unique.execute(uk.co.jezuk.mango.Iterators.BoundedIterator(list, start, end), comparator);
    }

    public static <T> void unique(java.util.Iterator<T> iterator, java.util.Comparator<? super T> comparator) {
        uk.co.jezuk.mango.algorithms.Unique.execute(iterator, comparator);
    }

    private Algorithms() {
    }
}

