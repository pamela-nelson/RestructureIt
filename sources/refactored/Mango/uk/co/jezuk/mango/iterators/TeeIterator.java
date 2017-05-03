

package uk.co.jezuk.mango.iterators;


public class TeeIterator<T> {
    public static <T> java.util.List<java.util.Iterator<T>> wrap(final java.util.Iterator<T> iterator, final int count) {
        final uk.co.jezuk.mango.iterators.TeeIterator.Buffer<T> buffer = new uk.co.jezuk.mango.iterators.TeeIterator.Buffer<T>(iterator, count);
        final java.util.List<java.util.Iterator<T>> iterators = new java.util.ArrayList<java.util.Iterator<T>>();
        for (int i = 0; i != count; ++i)
            iterators.add(new uk.co.jezuk.mango.iterators.TeeIterator.WrapIterator<T>(buffer));
        
        return iterators;
    }

    private static class Buffer<T> {
        private static class Item<T> {
            public Item(final int i, final T t) {
                index = i;
                useCount = 0;
                item = t;
            }

            public int index;

            public int useCount;

            public T item;
        }

        private int index_ = 0;

        private final int useCount_;

        private final java.util.Iterator<T> iterator_;

        private final java.util.List<uk.co.jezuk.mango.iterators.TeeIterator.Buffer.Item<T>> buffer_;

        public Buffer(final java.util.Iterator<T> iterator, final int count) {
            useCount_ = count;
            iterator_ = iterator;
            buffer_ = new java.util.ArrayList<uk.co.jezuk.mango.iterators.TeeIterator.Buffer.Item<T>>();
        }

        public T get(final int index) {
            if (!(has(index)))
                throw new java.util.NoSuchElementException();
            
            T result = null;
            for (uk.co.jezuk.mango.iterators.TeeIterator.Buffer.Item<T> i : buffer_) {
                if ((i.index) != index)
                    continue;
                
                result = i.item;
                ++(i.useCount);
                if ((i.useCount) == (useCount_))
                    buffer_.remove(i);
                
                break;
            }
            return result;
        }

        public boolean has(final int index) {
            if (index < (index_))
                return true;
            
            while ((iterator_.hasNext()) && (index >= (index_)))
                buffer_.add(new uk.co.jezuk.mango.iterators.TeeIterator.Buffer.Item<T>(((index_)++), iterator_.next()));
            
            return index < (index_);
        }
    }

    private static class WrapIterator<T> implements java.util.Iterator<T> {
        private final uk.co.jezuk.mango.iterators.TeeIterator.Buffer<T> buffer_;

        private int index_;

        public WrapIterator(final uk.co.jezuk.mango.iterators.TeeIterator.Buffer<T> buffer) {
            buffer_ = buffer;
            index_ = 0;
        }

        public T next() {
            return buffer_.get(((index_)++));
        }

        public boolean hasNext() {
            return buffer_.has(index_);
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("uk.co.jezuk.mango.TeeIterator doesn't do remove.  Sorry, pal");
        }
    }

    private TeeIterator() {
    }
}

