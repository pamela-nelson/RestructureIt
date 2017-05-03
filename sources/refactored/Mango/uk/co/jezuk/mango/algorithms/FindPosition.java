

package uk.co.jezuk.mango.algorithms;


public class FindPosition {
    public static <T> int execute(java.util.Iterator<? extends T> iterator, T value) {
        if (iterator == null)
            return -1;
        
        int count = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (value.equals(obj))
                return count;
            
            ++count;
        } 
        return -1;
    }

    private FindPosition() {
    }
}

