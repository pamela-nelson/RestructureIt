

package uk.co.jezuk.mango.unarypredicates;


public enum IsNull implements uk.co.jezuk.mango.Predicate<java.lang.Object> {
INSTANCE;
    public boolean test(java.lang.Object x) {
        return x == null;
    }
}

