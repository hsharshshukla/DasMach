/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnException;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;

public class AsnBoolean
extends AsnType {
    public boolean val;

    public AsnBoolean(AsnType asnType) throws AsnException {
        super(asnType);
        if (this.len != 1) {
            throw new AsnException("Boolean length != 1 at " + this.idx);
        }
        byte by = this.buf.nextByte();
        this.val = by != 0;
    }

    public String toString() {
        return "BOOLEAN@" + this.idx + (this.val ? " TRUE" : " FALSE");
    }
}

