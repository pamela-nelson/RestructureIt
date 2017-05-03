

package uk.co.jezuk.mango.algorithms;


public class RemoveIf {
    public static <T> void execute(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> test) {
        if ((iterator == null) || (test == null))
            return ;
        
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (test.test(obj))
                iterator.remove();
            
        } 
    }

    private RemoveIf() {
    }
}

