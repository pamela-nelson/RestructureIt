

package uk.co.jezuk.mango.unarypredicates;


public class Any<T> implements uk.co.jezuk.mango.Predicate<T> {
    public Any(java.util.Iterator<uk.co.jezuk.mango.Predicate<T>> preds) {
        preds_ = uk.co.jezuk.mango.Collections.list(preds);
    }

    public boolean test(T x) {
        for (java.util.Iterator<uk.co.jezuk.mango.Predicate<T>> i = preds_.iterator(); i.hasNext();)
            if (i.next().test(x))
                return true;
            
        
        return false;
    }

    private final java.util.List<uk.co.jezuk.mango.Predicate<T>> preds_;
}

