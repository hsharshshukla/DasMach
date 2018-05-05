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
import sun.security.provider.DSAPublicKey;

public class DSAPKFactory
extends PKFactory {
    private static int[] oidiv1 = new int[]{1, 3, 14, 3, 2, 12};
    private static int[] oidiv2 = new int[]{1, 2, 840, 10040, 4, 1};
    private static int[] oidiv3 = new int[]{1, 3, 14, 3, 2, 27};

    private DSAPKFactory() {
    }

    public static void init() {
        DSAPKFactory dSAPKFactory = new DSAPKFactory();
        ObjMap.addNode(oidiv1, "DSA", dSAPKFactory);
        ObjMap.addNode(oidiv2, "DSA", dSAPKFactory);
        ObjMap.addNode(oidiv3, "DSA", dSAPKFactory);
    }

    public PublicKey makeKey(AsnSequence asnSequence, AsnBitString asnBitString) throws AsnException {
        AsnType asnType;
        AsnType asnType2 = (AsnType)asnSequence.getSubs().elementAt(1);
        if (!(asnType2 instanceof AsnSequence)) {
            throw new AsnException("cert subjAlg parameters@" + asnSequence.getIdx() + " not a SEQUENCE");
        }
        AsnSequence asnSequence2 = (AsnSequence)asnType2;
        Vector vector = asnSequence2.getSubs();
        if (vector.size() != 3) {
            throw new AsnException("cert subjAlg parameters@" + asnSequence.getIdx() + " not 3 components");
        }
        asnType2 = (AsnType)vector.elementAt(0);
        if (!(asnType2 instanceof AsnInteger)) {
            throw new AsnException("prime p@" + asnType2.getIdx() + " not an INTEGER");
        }
        byte[] arrby = ((AsnInteger)asnType2).abuf.toByteArray();
        asnType2 = (AsnType)vector.elementAt(1);
        if (!(asnType2 instanceof AsnInteger)) {
            throw new AsnException("prime q@" + asnType2.getIdx() + " not an INTEGER");
        }
        byte[] arrby2 = ((AsnInteger)asnType2).abuf.toByteArray();
        asnType2 = (AsnType)vector.elementAt(2);
        if (!(asnType2 instanceof AsnInteger)) {
            throw new AsnException("base q@" + asnType2.getIdx() + " not an INTEGER");
        }
        byte[] arrby3 = ((AsnInteger)asnType2).abuf.toByteArray();
        asnType2 = new AsnType(asnBitString.abuf);
        try {
            asnType = asnType2.parse();
        }
        catch (Exception exception) {
            throw new AsnException("BITSTRING@" + asnBitString.getIdx() + " not parsable: " + exception);
        }
        if (!(asnType instanceof AsnInteger)) {
            throw new AsnException("cert subjPubKey@" + asnType.getIdx() + " not an INTEGER");
        }
        byte[] arrby4 = ((AsnInteger)asnType).abuf.toByteArray();
        BigInteger bigInteger = new BigInteger(1, arrby);
        BigInteger bigInteger2 = new BigInteger(1, arrby2);
        BigInteger bigInteger3 = new BigInteger(1, arrby3);
        BigInteger bigInteger4 = new BigInteger(1, arrby4);
        DSAPublicKey dSAPublicKey = null;
        try {
            dSAPublicKey = new DSAPublicKey(bigInteger4, bigInteger, bigInteger2, bigInteger3);
        }
        catch (Exception exception) {
            throw new AsnException("can't make a DSA Public Key: " + exception);
        }
        return dSAPublicKey;
    }
}

