/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnBitString;
import ixos.asn1.AsnException;
import ixos.asn1.AsnObjId;
import ixos.asn1.AsnSequence;
import ixos.asn1.DSAPKFactory;
import ixos.asn1.ObjMap;
import ixos.asn1.RSAPKFactory;
import java.security.PublicKey;

public abstract class PKFactory {
    private static boolean inited;

    public static void init() {
        DSAPKFactory.init();
        RSAPKFactory.init();
    }

    public static PKFactory getInstance(AsnObjId asnObjId) {
        ObjMap objMap;
        if (!inited) {
            PKFactory.init();
            inited = true;
        }
        if ((objMap = ObjMap.getNode(asnObjId.ids)) == null) {
            return null;
        }
        if (objMap.info == null) {
            return null;
        }
        if (!(objMap.info instanceof PKFactory)) {
            return null;
        }
        return (PKFactory)objMap.info;
    }

    public abstract PublicKey makeKey(AsnSequence var1, AsnBitString var2) throws AsnException;
}

