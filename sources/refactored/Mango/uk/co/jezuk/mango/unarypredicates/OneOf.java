

package uk.co.jezuk.mango.unarypredicates;


public class OneOf<T> implements uk.co.jezuk.mango.Predicate<T> {
    public OneOf(java.util.Iterator<uk.co.jezuk.mango.Predicate<T>> preds) {
        preds_ = uk.co.jezuk.mango.Collections.list(preds);
    }

    public boolean test(T x) {
        int t = 0;
        for (java.util.Iterator<uk.co.jezuk.mango.Predicate<T>> i = preds_.iterator(); (i.hasNext()) && (t <= 2);)
            if (i.next().test(x))
                ++t;
            
        
        return t == 1;
    }

    private final java.util.List<uk.co.jezuk.mango.Predicate<T>> preds_;
}

