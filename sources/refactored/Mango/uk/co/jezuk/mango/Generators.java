

package uk.co.jezuk.mango;


public class Generators {
    public static uk.co.jezuk.mango.Generator<java.lang.Integer> IntegerSequence() {
        return new uk.co.jezuk.mango.generators.IntegerSequence();
    }

    public static uk.co.jezuk.mango.Generator<java.lang.Integer> IntegerSequence(int seed) {
        return new uk.co.jezuk.mango.generators.IntegerSequence(seed);
    }

    public static uk.co.jezuk.mango.Generator<java.lang.Integer> IntegerSequence(java.lang.Integer seed) {
        return new uk.co.jezuk.mango.generators.IntegerSequence(seed);
    }

    public static <T> uk.co.jezuk.mango.Generator<T> ConstantSequence(T constant) {
        return new uk.co.jezuk.mango.generators.ConstantSequence<T>(constant);
    }

    public static <T> uk.co.jezuk.mango.Generator<T> NullSequence() {
        return ((uk.co.jezuk.mango.Generator<T>) (uk.co.jezuk.mango.generators.NullSequence.INSTANCE));
    }
}

