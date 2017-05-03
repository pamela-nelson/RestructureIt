

package uk.co.jezuk.mango.algorithms;


public class Find {
    public static <T> T execute(java.util.Iterator<? extends T> iterator, T value) {
        if ((iterator == null) || (value == null))
            return null;
        
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (value.equals(obj))
                return obj;
            
        } 
        return null;
    }

    private Find() {
    }
}

