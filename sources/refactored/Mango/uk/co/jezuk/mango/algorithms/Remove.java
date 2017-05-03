

package uk.co.jezuk.mango.algorithms;


public class Remove {
    public static <T> void execute(java.util.Iterator<? extends T> iterator, T value) {
        if (iterator == null)
            return ;
        
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (((value == null) && (obj == null)) || ((value != null) && (value.equals(obj))))
                iterator.remove();
            
        } 
    }

    private Remove() {
    }
}

