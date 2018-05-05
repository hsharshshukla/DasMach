/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnBitString;
import ixos.asn1.AsnException;
import ixos.asn1.AsnInteger;
import ixos.asn1.AsnSequence;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import ixos.asn1.ObjMap;
import ixos.asn1.PKFactory;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Vector;

public class RSAPKFactory
extends PKFactory {
    private static int[] oidiv1 = new int[]{2, 5, 8, 1, 1};
    private static int[] oidiv2 = new int[]{1, 2, 840, 113549, 1, 1, 1};

    private RSAPKFactory() {
    }

    public static void init() {
        RSAPKFactory rSAPKFactory = new RSAPKFactory();
        ObjMap.addNode(oidiv1, "RSA", rSAPKFactory);
        ObjMap.addNode(oidiv2, "RSA", rSAPKFactory);
    }

    public PublicKey makeKey(AsnSequence asnSequence, AsnBitString asnBitString) throws AsnException {
        AsnType asnType;
        AsnType asnType2 = new AsnType(asnBitString.abuf);
        try {
            asnType = asnType2.parse();
        }
        catch (Exception exception) {
            throw new AsnException("BITSTRING@" + asnBitString.getIdx() + " not parsable: " + exception);
        }
        if (!(asnType instanceof AsnSequence)) {
            throw new AsnException("cert@" + asnType.getIdx() + " not a SEQUENCE");
        }
        asnType2 = (AsnType)((AsnSequence)asnType).getSubs().elementAt(0);
        if (!(asnType2 instanceof AsnInteger)) {
            throw new AsnException("pubkey pubN@" + asnType2.getIdx() + " not an INTEGER");
        }
        byte[] arrby = ((AsnInteger)asnType2).abuf.toByteArray();
        asnType2 = (AsnType)((AsnSequence)asnType).getSubs().elementAt(1);
        if (!(asnType2 instanceof AsnInteger)) {
            throw new AsnException("pubkey pubE@" + asnType2.getIdx() + " not an INTEGER");
        }
        byte[] arrby2 = ((AsnInteger)asnType2).abuf.toByteArray();
        BigInteger bigInteger = new BigInteger(1, arrby);
        BigInteger bigInteger2 = new BigInteger(1, arrby2);
        throw new AsnException("can't make a RSA Public Key yet");
    }
}

