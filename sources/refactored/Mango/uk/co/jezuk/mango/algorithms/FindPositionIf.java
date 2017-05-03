

package uk.co.jezuk.mango.algorithms;


public class FindPositionIf {
    public static <T> int execute(java.util.Iterator<T> iterator, uk.co.jezuk.mango.Predicate<? super T> test) {
        if (iterator == null)
            return -1;
        
        int count = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (test.test(obj))
                return count;
            
            ++count;
        } 
        return -1;
    }

    private FindPositionIf() {
    }
}

