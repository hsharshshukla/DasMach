/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnType;

public class AsnNull
extends AsnType {
    public AsnNull(AsnType asnType) {
        super(asnType);
    }

    public String toString() {
        return "NULL@" + this.idx;
    }
}

