

package uk.co.jezuk.mango.generators;


public class ConstantSequence<R> implements uk.co.jezuk.mango.Generator<R> {
    private final R seed_;

    public ConstantSequence(final R seed) {
        seed_ = seed;
    }

    public R fn() {
        return seed_;
    }
}

