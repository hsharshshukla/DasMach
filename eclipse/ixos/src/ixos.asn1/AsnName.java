/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnObjId;
import ixos.asn1.AsnSequence;
import ixos.asn1.AsnSet;
import ixos.asn1.AsnString;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import ixos.asn1.ObjMap;
import java.util.Vector;

public class AsnName {
    private AsnType t;
    private String nam;

    public AsnName(AsnType asnType) {
        this.t = asnType;
    }

    public String toName() {
        if (this.nam != null) {
            return this.nam;
        }
        if (!(this.t instanceof AsnSequence)) {
            return null;
        }
        AsnSequence asnSequence = (AsnSequence)this.t;
        String string = "";
        for (int i = 0; i < asnSequence.subs.size(); ++i) {
            AsnType asnType = (AsnType)asnSequence.subs.elementAt(i);
            if (!(asnType instanceof AsnSet)) {
                return null;
            }
            AsnSet asnSet = (AsnSet)asnType;
            for (int j = 0; j < asnSet.subs.size(); ++j) {
                AsnType asnType2;
                if (string.length() != 0) {
                    string = string + " ";
                }
                if (!((asnType2 = (AsnType)asnSet.subs.elementAt(j)) instanceof AsnSequence)) {
                    return null;
                }
                AsnSequence asnSequence2 = (AsnSequence)asnType2;
                int n = asnSequence2.subs.size();
                if (n % 2 != 0) {
                    return null;
                }
                for (int k = 0; k < n; k += 2) {
                    asnType = (AsnType)asnSequence2.subs.elementAt(k);
                    asnType2 = (AsnType)asnSequence2.subs.elementAt(k + 1);
                    if (!(asnType instanceof AsnObjId) || !(asnType2 instanceof AsnString)) continue;
                    AsnObjId asnObjId = (AsnObjId)asnType;
                    AsnString asnString = (AsnString)asnType2;
                    ObjMap objMap = ObjMap.getNode(asnObjId.ids);
                    if (objMap == null) continue;
                    string = string + objMap.name + asnString.abuf.toString();
                }
            }
        }
        this.nam = string;
        return string;
    }

    public int hashCode() {
        return this.toName().hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof AsnName)) {
            return false;
        }
        return this.toName().equals(((AsnName)object).toName());
    }
}

