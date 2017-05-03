

package uk.co.jezuk.mango.iterators;


public class BoundedIterator<T> implements java.util.Iterator<T> {
    public BoundedIterator(java.util.Iterator<? extends T> iterator, int start, int end) {
        iter_ = new uk.co.jezuk.mango.iterators.BoundedIterator.iteratorWrapper<T>(iterator, start, end);
    }

    public BoundedIterator(java.util.List<? extends T> list, int start, int end) {
        iter_ = new uk.co.jezuk.mango.iterators.BoundedIterator.listIterator<T>(list, start, end);
    }

    public boolean hasNext() {
        return iter_.hasNext();
    }

    public T next() {
        return iter_.next();
    }

    public void remove() {
        iter_.remove();
    }

    private java.util.Iterator<T> iter_;

    private static void checkConstraints(int start, int end) {
        if (start < 0)
            throw new java.lang.IndexOutOfBoundsException("start < 0");
        
        if (end < 0)
            throw new java.lang.IndexOutOfBoundsException("end < 0");
        
        if (start > end)
            throw new java.lang.IndexOutOfBoundsException("start > end");
        
    }

    private static class iteratorWrapper<T> implements java.util.Iterator<T> {
        iteratorWrapper(java.util.Iterator<? extends T> iterator, int start, int end) {
            uk.co.jezuk.mango.iterators.BoundedIterator.checkConstraints(start, end);
            iter_ = iterator;
            for (index_ = 0; (iter_.hasNext()) && ((index_) < start); ++(index_) , iter_.next());
            end_ = (iter_.hasNext()) ? end : index_;
        }

        public boolean hasNext() {
            end_ = (iter_.hasNext()) ? end_ : index_;
            return (index_) < (end_);
        }

        public T next() {
            ++(index_);
            return ((T) (iter_.next()));
        }

        public void remove() {
            iter_.remove();
        }

        private java.util.Iterator<? extends T> iter_;

        private int index_;

        private int end_;
    }

    private static class listIterator<T> implements java.util.Iterator<T> {
        listIterator(java.util.List<? extends T> list, int start, int end) {
            uk.co.jezuk.mango.iterators.BoundedIterator.checkConstraints(start, end);
            list_ = list;
            index_ = start;
            end_ = end;
            if ((end_) > (getList_().size()))
                end_ = list.size();
            
        }

        public boolean hasNext() {
            return (index_) < (end_);
        }

        public T next() {
            return ((T) (getList_().get(((index_)++))));
        }

        public void remove() {
            getList_().remove(((index_) - 1));
        }

        private java.util.List<? extends T> list_;

        private int index_;

        private int end_;

        public java.util.List getList_() {
            return java.util.Collections.unmodifiableList(list_);
        }

        public void addList(? extends T list) {
            list_.add(list);
        }

        public void removeList(? extends T list) {
            list_.remove(list);
        }
    }
}

