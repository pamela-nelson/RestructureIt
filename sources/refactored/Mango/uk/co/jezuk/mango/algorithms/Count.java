

package uk.co.jezuk.mango.algorithms;


public class Count {
    public static <T> int execute(java.util.Iterator<? extends T> iterator, T value) {
        if (iterator == null)
            return 0;
        
        if (value == null)
            return uk.co.jezuk.mango.algorithms.Count.execute_null(iterator);
        
        int c = 0;
        while (iterator.hasNext())
            if (value.equals(iterator.next()))
                ++c;
            
        
        return c;
    }

    private static <T> int execute_null(java.util.Iterator<T> iterator) {
        int c = 0;
        while (iterator.hasNext())
            if ((iterator.next()) == null)
                ++c;
            
        
        return c;
    }

    private Count() {
    }
}

