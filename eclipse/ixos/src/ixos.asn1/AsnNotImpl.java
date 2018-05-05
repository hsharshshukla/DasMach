/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnException;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;

public class AsnNotImpl
extends AsnType {
    public byte[] arr;

    public AsnNotImpl(AsnType asnType) throws AsnException {
        super(asnType);
        this.arr = new byte[this.len];
        for (int i = 0; i < this.len; ++i) {
            this.arr[i] = this.buf.nextByte();
        }
    }

    public String toString() {
        return "NotImpl@" + this.idx + " length=" + this.arr.length + "[" + this.arr + "]";
    }
}

