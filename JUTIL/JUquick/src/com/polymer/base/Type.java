package com.polymer.base;

import java.util.Optional;

/**
 * 泛型类型的定义
 * @param <T>
 */
public final class Type<T> {

    private static final Type<?> EMPTY=new Type<>();

    private T t;

    public Type() { }

    public Type(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public static<T> Type<T> empty() {
        @SuppressWarnings("unchecked")
        Type<T> t = (Type<T>) EMPTY;
        return t;
    }

    public static<T> Type<T> init(T t){
        return new Type<>(t);
    }
}
