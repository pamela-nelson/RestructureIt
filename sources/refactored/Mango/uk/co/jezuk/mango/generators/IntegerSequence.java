

package uk.co.jezuk.mango.generators;


public class IntegerSequence implements uk.co.jezuk.mango.Generator<java.lang.Integer> {
    public IntegerSequence() {
        seed_ = 0;
    }

    public IntegerSequence(int seed) {
        seed_ = seed;
    }

    public IntegerSequence(java.lang.Integer seed) {
        seed_ = seed.intValue();
    }

    public java.lang.Integer fn() {
        return java.lang.Integer.valueOf(((seed_)++));
    }

    private int seed_;
}

