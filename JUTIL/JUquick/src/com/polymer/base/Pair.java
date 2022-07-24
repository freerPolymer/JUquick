package com.polymer.base;

/**
 * 键值对的KEY,VALUE数据模型
 * @param <K>
 * @param <V>
 */
public class Pair<K,V> {

    private K k;
    private V v;

    public Pair() {}

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }
}
