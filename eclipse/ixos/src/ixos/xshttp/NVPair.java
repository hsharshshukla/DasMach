/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

public final class NVPair {
    private String name;
    private String value;

    public NVPair(String string, String string2) {
        this.name = string;
        this.value = string2;
    }

    public NVPair(NVPair nVPair) {
        this(nVPair.name, nVPair.value);
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    public String toString() {
        return this.getClass().getName() + "[name=" + this.name + ",value=" + this.value + "]";
    }
}

