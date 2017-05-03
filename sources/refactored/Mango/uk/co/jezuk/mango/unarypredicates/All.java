

package uk.co.jezuk.mango.unarypredicates;


public class All<T> implements uk.co.jezuk.mango.Predicate<T> {
    public All(java.util.Iterator<uk.co.jezuk.mango.Predicate<T>> preds) {
        preds_ = uk.co.jezuk.mango.Collections.list(preds);
    }

    public boolean test(T x) {
        for (java.util.Iterator<uk.co.jezuk.mango.Predicate<T>> i = preds_.iterator(); i.hasNext();)
            if (!(i.next().test(x)))
                return false;
            
        
        return true;
    }

    private final java.util.List<uk.co.jezuk.mango.Predicate<T>> preds_;
}

