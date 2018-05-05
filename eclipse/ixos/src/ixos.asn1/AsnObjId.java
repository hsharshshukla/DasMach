/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnException;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import ixos.asn1.ObjMap;

public class AsnObjId
extends AsnType {
    public int[] ids;

    public AsnObjId(AsnType asnType) throws AsnException {
        super(asnType);
        if (this.len < 1) {
            throw new AsnException("ObjId length < 1 at " + this.idx);
        }
        int[] arrn = new int[100];
        int n = 0;
        int n2 = this.buf.nextByte() & 255;
        int n3 = n2 / 40;
        arrn[n++] = n3;
        n3 = n2 - n3 * 40;
        arrn[n++] = n3;
        n3 = 0;
        for (int i = 1; i < this.len; ++i) {
            n2 = this.buf.nextByte();
            n3 = n3 * 128 + (n2 & 127);
            if ((n2 & 128) != 0) continue;
            arrn[n++] = n3;
            n3 = 0;
        }
        this.ids = new int[n];
        System.arraycopy(arrn, 0, this.ids, 0, n);
    }

    public String toString() {
        String string = "OBJID@" + this.idx;
        int n = this.ids.length;
        for (int i = 0; i < n; ++i) {
            string = i == 0 ? string + " " + this.ids[i] : string + "." + this.ids[i];
        }
        String string2 = ObjMap.getName(this.ids);
        if (string2 != null) {
            string = string + " (" + string2 + ")";
        }
        return string;
    }

    public AsnObjId(int[] arrn) {
        super(6, 0);
        this.ids = arrn;
    }

    public boolean equals(Object object) {
        if (!(object instanceof AsnObjId)) {
            return false;
        }
        AsnObjId asnObjId = (AsnObjId)object;
        if (this.ids.length != asnObjId.ids.length) {
            return false;
        }
        for (int i = 0; i < this.ids.length; ++i) {
            if (this.ids[i] == asnObjId.ids[i]) continue;
            return false;
        }
        return true;
    }
}

