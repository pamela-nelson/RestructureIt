

package uk.co.jezuk.mango;


public class Adapt {
    public static uk.co.jezuk.mango.Function Method(final java.lang.Object obj, final java.lang.String methodName) {
        return uk.co.jezuk.mango.Adapt.wrapMethod(obj.getClass(), obj, methodName, null, null);
    }

    @java.lang.SuppressWarnings(value = "unchecked")
    public static <T, Void> uk.co.jezuk.mango.Function<T, Void> Method(final java.lang.Object obj, final java.lang.String methodName, final java.lang.Class<T> argType) {
        return ((uk.co.jezuk.mango.Function<T, Void>) (uk.co.jezuk.mango.Adapt.wrapMethod(obj.getClass(), obj, methodName, argType, null)));
    }

    @java.lang.SuppressWarnings(value = "unchecked")
    public static <T, R> uk.co.jezuk.mango.Function<T, R> Method(final java.lang.Object obj, final java.lang.String methodName, final java.lang.Class<T> argType, final java.lang.Class<R> returnType) {
        return ((uk.co.jezuk.mango.Function<T, R>) (uk.co.jezuk.mango.Adapt.wrapMethod(obj.getClass(), obj, methodName, argType, returnType)));
    }

    public static uk.co.jezuk.mango.Function Method(final java.lang.Class klass, final java.lang.String methodName) {
        return uk.co.jezuk.mango.Adapt.wrapMethod(klass, null, methodName, null, null);
    }

    @java.lang.SuppressWarnings(value = "unchecked")
    public static <T, Void> uk.co.jezuk.mango.Function<T, Void> Method(final java.lang.Class klass, final java.lang.String methodName, final java.lang.Class<T> argType) {
        return uk.co.jezuk.mango.Adapt.wrapMethod(klass, null, methodName, argType, null);
    }

    @java.lang.SuppressWarnings(value = "unchecked")
    public static <T, R> uk.co.jezuk.mango.Function<T, R> Method(final java.lang.Class klass, final java.lang.String methodName, final java.lang.Class<T> argType, final java.lang.Class<R> returnType) {
        return uk.co.jezuk.mango.Adapt.wrapMethod(klass, null, methodName, argType, returnType);
    }

    private static uk.co.jezuk.mango.Function wrapMethod(final java.lang.Class<?> klass, final java.lang.Object obj, final java.lang.String methodName, final java.lang.Class<?> argType, final java.lang.Class<?> returnType) {
        final java.util.List<java.lang.reflect.Method> methods = java.util.Arrays.asList(klass.getMethods());
        uk.co.jezuk.mango.Predicate<java.lang.reflect.Method> methodTest = new uk.co.jezuk.mango.Adapt.UnaryMethodNamed(methodName, (argType != null ? argType : java.lang.Object.class));
        java.lang.reflect.Method m = uk.co.jezuk.mango.Algorithms.findIf(methods, methodTest);
        if ((m == null) && (argType == null))
            m = uk.co.jezuk.mango.Algorithms.findIf(methods, new uk.co.jezuk.mango.Adapt.AnyUnaryMethodNamed(methodName));
        
        if (m == null)
            throw new java.lang.RuntimeException(new java.lang.NoSuchMethodException((((methodName + "(") + argType) + ")")));
        
        uk.co.jezuk.mango.Adapt.verifyReturnType(m, returnType);
        final java.lang.reflect.Method method = m;
        return new uk.co.jezuk.mango.Function() {
            private final java.lang.Object obj_;

            private final java.lang.reflect.Method method_;

            {
                obj_ = obj;
                method_ = method;
            }

            public java.lang.Object fn(java.lang.Object arg) {
                java.lang.Object[] args = new java.lang.Object[]{ arg };
                try {
                    return method_.invoke(obj_, args);
                } catch (java.lang.IllegalArgumentException e) {
                    throw new java.lang.RuntimeException((((((("Passed " + (arg.getClass())) + " to ") + (method_.getName())) + "(") + (method_.getParameterTypes()[0])) + ")"), e);
                } catch (java.lang.Exception e) {
                    throw new java.lang.RuntimeException(e);
                }
            }
        };
    }

    private static void verifyReturnType(final java.lang.reflect.Method m, final java.lang.Class<?> returnType) {
        if (((returnType == null) || (returnType.equals(void.class))) || (returnType.equals(java.lang.Void.class)))
            return ;
        
        if (!(returnType.isAssignableFrom(m.getReturnType())))
            throw new java.lang.RuntimeException(new java.lang.NoSuchMethodException((((((m.getName()) + " has return type ") + (m.getReturnType())) + ", but wanted ") + returnType)));
        
    }

    private static class NullaryMethodNamed implements uk.co.jezuk.mango.Predicate<java.lang.reflect.Method> {
        NullaryMethodNamed(final java.lang.String name) {
            name_ = name;
        }

        public boolean test(final java.lang.reflect.Method m) {
            return (m.getName().equals(name_)) && ((m.getParameterTypes().length) == 0);
        }

        private final java.lang.String name_;
    }

    private static class UnaryMethodNamed implements uk.co.jezuk.mango.Predicate<java.lang.reflect.Method> {
        UnaryMethodNamed(final java.lang.String name, final java.lang.Class<?> argType) {
            name_ = name;
            argType_ = argType;
        }

        public boolean test(final java.lang.reflect.Method m) {
            if (!(m.getName().equals(name_)))
                return false;
            
            if ((m.getParameterTypes().length) != 1)
                return false;
            
            if (!(m.getParameterTypes()[0].equals(argType_)))
                return false;
            
            return true;
        }

        private final java.lang.String name_;

        private final java.lang.Class<?> argType_;
    }

    private static class AnyUnaryMethodNamed implements uk.co.jezuk.mango.Predicate<java.lang.reflect.Method> {
        AnyUnaryMethodNamed(final java.lang.String name) {
            name_ = name;
        }

        public boolean test(final java.lang.reflect.Method m) {
            if (!(m.getName().equals(name_)))
                return false;
            
            if ((m.getParameterTypes().length) != 1)
                return false;
            
            return true;
        }

        private final java.lang.String name_;
    }

    public static uk.co.jezuk.mango.Function ArgumentMethod(final java.lang.String methodName) {
        return new uk.co.jezuk.mango.Function() {
            private java.lang.Class lastClass_;

            private java.lang.reflect.Method method_;

            public java.lang.Object fn(java.lang.Object arg) {
                if (!(arg.getClass().equals(lastClass_))) {
                    lastClass_ = arg.getClass();
                    java.util.List<java.lang.reflect.Method> methods = java.util.Arrays.asList(lastClass_.getMethods());
                    method_ = uk.co.jezuk.mango.Algorithms.findIf(methods, new uk.co.jezuk.mango.Adapt.NullaryMethodNamed(methodName));
                    if ((method_) == null)
                        throw new java.lang.RuntimeException(new java.lang.NoSuchMethodException((methodName + "()")));
                    
                }
                try {
                    return method_.invoke(arg, ((java.lang.Object[]) (null)));
                } catch (java.lang.Exception e) {
                    throw new java.lang.RuntimeException(e);
                }
            }
        };
    }

    @java.lang.SuppressWarnings(value = "unchecked")
    public static <T, Void> uk.co.jezuk.mango.Function<T, Void> ArgumentMethod(final java.lang.String methodName, final java.lang.Class<T> argType) {
        return ((uk.co.jezuk.mango.Function<T, Void>) (uk.co.jezuk.mango.Adapt.wrapArgumentMethod(methodName, argType, null)));
    }

    @java.lang.SuppressWarnings(value = "unchecked")
    public static <T, R> uk.co.jezuk.mango.Function<T, R> ArgumentMethod(final java.lang.String methodName, final java.lang.Class<T> argType, final java.lang.Class<R> returnType) {
        return ((uk.co.jezuk.mango.Function<T, R>) (uk.co.jezuk.mango.Adapt.wrapArgumentMethod(methodName, argType, returnType)));
    }

    private static uk.co.jezuk.mango.Function wrapArgumentMethod(final java.lang.String methodName, final java.lang.Class<?> argType, final java.lang.Class<?> returnType) {
        final java.util.List<java.lang.reflect.Method> methods = java.util.Arrays.asList(argType.getMethods());
        final java.lang.reflect.Method method = uk.co.jezuk.mango.Algorithms.findIf(methods, new uk.co.jezuk.mango.Adapt.NullaryMethodNamed(methodName));
        if (method == null)
            throw new java.lang.RuntimeException(new java.lang.NoSuchMethodException((methodName + "()")));
        
        uk.co.jezuk.mango.Adapt.verifyReturnType(method, returnType);
        return new uk.co.jezuk.mango.Function() {
            private final java.lang.reflect.Method method_;

            {
                method_ = method;
            }

            public java.lang.Object fn(java.lang.Object arg) {
                try {
                    return method_.invoke(arg, ((java.lang.Object[]) (null)));
                } catch (java.lang.Exception e) {
                    throw new java.lang.RuntimeException(e);
                }
            }
        };
    }

    private Adapt() {
    }
}

