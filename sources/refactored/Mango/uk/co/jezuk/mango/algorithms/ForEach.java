

package uk.co.jezuk.mango.algorithms;


public class ForEach {
    public static <T, R> void execute(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Function<? super T, R> fn) {
        if ((iterator == null) || (fn == null))
            return ;
        
        while (iterator.hasNext())
            fn.fn(iterator.next());
        
    }

    private ForEach() {
    }
}

