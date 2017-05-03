

package uk.co.jezuk.mango.algorithms;


public class Unique {
    @java.lang.SuppressWarnings(value = "unchecked")
    public static <T> void execute(java.util.Iterator<T> iterator, java.util.Comparator<? super T> comparator) {
        if (!(iterator.hasNext()))
            return ;
        
        T prev = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (uk.co.jezuk.mango.algorithms.Unique.match(comparator, prev, next))
                iterator.remove();
            else
                prev = next;
            
        } 
    }

    public static <T> boolean match(java.util.Comparator<T> c, T o1, T o2) {
        if (c != null)
            return (c.compare(o1, o2)) == 0;
        
        if (o1 == null)
            return o2 == null;
        
        return o1.equals(o2);
    }

    private Unique() {
    }
}

