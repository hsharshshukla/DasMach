/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnCert;
import ixos.asn1.AsnConstructed;
import ixos.asn1.AsnException;
import ixos.asn1.AsnInteger;
import ixos.asn1.AsnName;
import ixos.asn1.AsnObjId;
import ixos.asn1.AsnSequence;
import ixos.asn1.AsnSet;
import ixos.asn1.AsnString;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import ixos.asn1.ObjMap;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Vector;

public class PKCS7Signature {
    private AsnType t;
    public AsnInteger pkcs7Version;
    public AsnSequence contentInfo;
    public AsnSequence certificate;
    public AsnInteger signerVersion;
    public AsnSequence issuer;
    public AsnInteger serialNumber;
    public AsnSequence digestAlg;
    public AsnConstructed authAttrs;
    public AsnString signTime;
    public AsnString messDigest;
    public AsnSequence digestEncAlg;
    public AsnString encDigest;
    public AsnConstructed unAuthAttrs;
    private static int[] ipkcs9_signTime = new int[]{1, 2, 840, 113549, 1, 9, 5};
    private static int[] ipkcs9_messDigest = new int[]{1, 2, 840, 113549, 1, 9, 4};
    private AsnObjId apkcs9_signTime;
    private AsnObjId apkcs9_messDigest;
    private boolean sunbug = false;

    public PKCS7Signature(AsnType asnType) {
        this.t = asnType;
    }

    public PKCS7Signature(byte[] arrby, int n) throws AsnException {
        Buf buf = new Buf(arrby, 0, n);
        AsnType asnType = new AsnType(buf);
        this.t = asnType.parse();
    }

    public void decompose() throws AsnException {
        int n;
        int[] arrn;
        AsnType asnType;
        if (!(this.t instanceof AsnSequence)) {
            throw new AsnException("root@" + this.t.idx + " not a SEQUENCE");
        }
        AsnSequence asnSequence = (AsnSequence)this.t;
        if (asnSequence.subs.size() != 2) {
            throw new AsnException("root@" + this.t.idx + " SEQUENCE has not 2 components");
        }
        AsnType asnType2 = (AsnType)asnSequence.subs.elementAt(0);
        if (!(asnType2 instanceof AsnObjId)) {
            throw new AsnException("PKCS7 comp0@" + asnType2.idx + " not an OBJECT IDENTIFIER");
        }
        AsnObjId asnObjId = (AsnObjId)asnType2;
        int[] arrn2 = new int[]{1, 2, 840, 113549, 1, 7, 2};
        AsnObjId asnObjId2 = new AsnObjId(arrn2);
        if (!asnObjId.equals(asnObjId2)) {
            arrn = new int[]{1, 2, 840, 1113549, 1, 7, 2};
            AsnObjId asnObjId3 = new AsnObjId(arrn);
            if (asnObjId.equals(asnObjId3)) {
                this.sunbug = true;
            } else {
                throw new AsnException("PKCS7 OBJID@" + asnType2.idx + " not PKCS7-signedData");
            }
        }
        if ((asnType = (AsnType)asnSequence.subs.elementAt(1)) instanceof AsnConstructed) {
            arrn = (AsnConstructed)asnType;
            if (arrn.aclass != 128 || arrn.tag != 0) {
                throw new AsnException("PKCS7 comp1@" + asnType.idx + " is not tag [0]");
            }
            if (arrn.subs.size() != 1) {
                throw new AsnException("constructed [0]@" + asnType.idx + " has not just one component");
            }
            asnType2 = (AsnType)arrn.subs.elementAt(0);
        } else if (this.sunbug) {
            asnType2 = asnType;
        } else {
            throw new AsnException("PKCS7 comp1@" + asnType.idx + " not constructed");
        }
        if (!(asnType2 instanceof AsnSequence)) {
            throw new AsnException("signedData@" + asnType2.idx + " not a SEQUENCE");
        }
        arrn = (AsnSequence)asnType2;
        if (arrn.subs.size() < 3) {
            throw new AsnException("signedData@" + asnType2.idx + " has less than 3 components");
        }
        asnType2 = (AsnType)arrn.subs.elementAt(0);
        if (!(asnType2 instanceof AsnInteger)) {
            throw new AsnException("signedData version@" + asnType2.idx + " not an INTEGER");
        }
        this.pkcs7Version = (AsnInteger)asnType2;
        asnType2 = (AsnType)arrn.subs.elementAt(2);
        if (!(asnType2 instanceof AsnSequence)) {
            throw new AsnException("signedData contentInfo@" + asnType2.idx + " not a SEQUENCE");
        }
        this.contentInfo = (AsnSequence)asnType2;
        int n2 = arrn.subs.size();
        for (n = 3; n < n2 && (asnType2 = (AsnType)arrn.subs.elementAt(n)) instanceof AsnConstructed; ++n) {
            AsnConstructed asnConstructed = (AsnConstructed)asnType2;
            if (asnConstructed.aclass != 128) continue;
            asnType2 = (AsnType)asnConstructed.subs.elementAt(0);
            if (asnConstructed.tag != 0 || !(asnType2 instanceof AsnSequence)) continue;
            this.certificate = (AsnSequence)asnType2;
        }
        if (n == 3 && n < n2 && this.sunbug && (asnType2 = (AsnType)arrn.subs.elementAt(n)) instanceof AsnSet) {
            ++n;
            AsnSet asnSet = (AsnSet)asnType2;
            asnType2 = (AsnType)asnSet.subs.elementAt(0);
            if (asnType2 instanceof AsnSequence) {
                this.certificate = (AsnSequence)asnType2;
            }
        }
        if (n == 4 && n < n2 && this.sunbug && (asnType2 = (AsnType)arrn.subs.elementAt(n)) instanceof AsnSet && ((AsnSet)asnType2).subs.size() == 0) {
            ++n;
        }
        if (n == n2) {
            throw new AsnException("signedData@" + arrn.idx + " has no signerInfos");
        }
        asnType2 = (AsnType)arrn.subs.elementAt(n);
        if (!(asnType2 instanceof AsnSet)) {
            throw new AsnException("signedData signerInfos@" + asnType2.idx + " not a SET");
        }
        AsnSet asnSet = (AsnSet)asnType2;
        if (asnSet.subs.size() == 0) {
            return;
        }
        if (asnSet.subs.size() != 1) {
            throw new AsnException("only one signer implemented");
        }
        asnType2 = (AsnType)asnSet.subs.elementAt(0);
        if (!(asnType2 instanceof AsnSequence)) {
            throw new AsnException("signerInfo@" + asnType2.idx + " not a SEQUENCE");
        }
        AsnSequence asnSequence2 = (AsnSequence)asnType2;
        if (asnSequence2.subs.size() < 5) {
            throw new AsnException("signerInfo@" + asnType2.idx + " has less than 5 components");
        }
        asnType2 = (AsnType)asnSequence2.subs.elementAt(0);
        if (!(asnType2 instanceof AsnInteger)) {
            throw new AsnException("signerInfo version@" + asnType2.idx + " not an INTEGER");
        }
        this.signerVersion = (AsnInteger)asnType2;
        asnType2 = (AsnType)asnSequence2.subs.elementAt(1);
        if (!(asnType2 instanceof AsnSequence)) {
            throw new AsnException("signerInfo issuerAndSerialNumber@" + asnType2.idx + " not a SEQUENCE");
        }
        AsnSequence asnSequence3 = (AsnSequence)asnType2;
        if (asnSequence3.subs.size() != 2) {
            throw new AsnException("issuerAndSerialNumber@" + asnType2.idx + " has not 2 components");
        }
        asnType2 = (AsnType)asnSequence3.subs.elementAt(0);
        asnType = (AsnType)asnSequence3.subs.elementAt(1);
        if (!(asnType2 instanceof AsnSequence)) {
            throw new AsnException("issuer@" + asnType2.idx + " not a SEQUENCE");
        }
        if (!(asnType instanceof AsnInteger)) {
            throw new AsnException("serialNumber@" + asnType.idx + " not an INTEGER");
        }
        this.issuer = (AsnSequence)asnType2;
        this.serialNumber = (AsnInteger)asnType;
        asnType2 = (AsnType)asnSequence2.subs.elementAt(2);
        if (!(asnType2 instanceof AsnSequence)) {
            throw new AsnException("signerInfo@" + asnType2.idx + " digestAlgorithm not a SEQUENCE");
        }
        this.digestAlg = (AsnSequence)asnType2;
        n = 3;
        asnType2 = (AsnType)asnSequence2.subs.elementAt(n);
        if (asnType2 instanceof AsnConstructed) {
            ++n;
            this.authAttrs = (AsnConstructed)asnType2;
            if (this.authAttrs.tag != 0) {
                throw new AsnException("expected tag [0] @ " + asnType2.idx + " for authenticatedAttributes");
            }
            this.decompAuthAttr();
        } else if (this.sunbug && asnType2 instanceof AsnSet && ((AsnSet)asnType2).subs.size() == 0) {
            ++n;
        }
        asnType2 = (AsnType)asnSequence2.subs.elementAt(n);
        if (!(asnType2 instanceof AsnSequence)) {
            throw new AsnException("signerInfo@" + asnType2.idx + " digestEncryptionAlgorithm not a SEQUENCE");
        }
        this.digestEncAlg = (AsnSequence)asnType2;
        if (!((asnType2 = (AsnType)asnSequence2.subs.elementAt(++n)) instanceof AsnString)) {
            throw new AsnException("signerInfo@" + asnType2.idx + " encryptedDigest not a STRING");
        }
        this.encDigest = (AsnString)asnType2;
        if (++n < asnSequence2.subs.size() && (asnType2 = (AsnType)asnSequence2.subs.elementAt(n)) instanceof AsnConstructed) {
            ++n;
            this.unAuthAttrs = (AsnConstructed)asnType2;
        }
    }

    private void decompAuthAttr() throws AsnException {
        int n = this.authAttrs.subs.size();
        if (this.apkcs9_signTime == null) {
            this.apkcs9_signTime = new AsnObjId(ipkcs9_signTime);
            this.apkcs9_messDigest = new AsnObjId(ipkcs9_messDigest);
        }
        for (int i = 0; i < n; ++i) {
            AsnType asnType = (AsnType)this.authAttrs.subs.elementAt(i);
            if (!(asnType instanceof AsnSequence)) {
                throw new AsnException("authAttr component@" + asnType.idx + " not a SEQUENCE");
            }
            AsnSequence asnSequence = (AsnSequence)asnType;
            if (asnSequence.subs.size() != 2) {
                throw new AsnException("authAttr SEQUENCE@" + asnType.idx + " has not 2 components");
            }
            asnType = (AsnType)asnSequence.subs.elementAt(0);
            if (!(asnType instanceof AsnObjId)) {
                throw new AsnException("authAttr SEQUENCE comp0@" + asnType.idx + " not an OBJID");
            }
            AsnObjId asnObjId = (AsnObjId)asnType;
            if (asnObjId.equals(this.apkcs9_signTime)) {
                asnType = (AsnType)asnSequence.subs.elementAt(1);
                if (!(asnType instanceof AsnSet)) {
                    throw new AsnException("signTime@" + asnType.idx + " not a SET");
                }
                this.signTime = (AsnString)((AsnSet)asnType).subs.elementAt(0);
                continue;
            }
            if (!asnObjId.equals(this.apkcs9_messDigest)) continue;
            asnType = (AsnType)asnSequence.subs.elementAt(1);
            if (!(asnType instanceof AsnSet)) {
                throw new AsnException("messDigest@" + asnType.idx + " not a SET");
            }
            this.messDigest = (AsnString)((AsnSet)asnType).subs.elementAt(0);
        }
    }

    public byte[] getAuthAttrs() {
        if (this.authAttrs == null) {
            return null;
        }
        byte[] arrby = this.authAttrs.cbuf.toByteArray();
        arrby[0] = 49;
        return arrby;
    }

    public String getIssuerSerno() {
        AsnName asnName = new AsnName(this.issuer);
        return asnName.toName() + ";" + this.serialNumber.toBigInt();
    }

    public byte[] getContentInfo() throws AsnException {
        if (this.contentInfo == null) {
            return null;
        }
        AsnType asnType = (AsnType)this.contentInfo.subs.elementAt(1);
        if (asnType instanceof AsnConstructed) {
            AsnConstructed asnConstructed = (AsnConstructed)asnType;
            asnType = (AsnType)asnConstructed.subs.elementAt(0);
        } else if (!this.sunbug) {
            throw new AsnException("PKCS7 contentInfo comp1@" + asnType.idx + " not constructed");
        }
        if (!(asnType instanceof AsnString)) {
            throw new AsnException("PKCS7 contentInfo@" + asnType.idx + " not a STRING");
        }
        AsnString asnString = (AsnString)asnType;
        return asnString.abuf.toByteArray();
    }

    public byte[] getEncDigest() {
        byte[] arrby = this.encDigest.abuf.toByteArray();
        if (arrby[0] < 0) {
            byte[] arrby2 = arrby;
            arrby = new byte[arrby2.length + 1];
            arrby[0] = 0;
            System.arraycopy(arrby2, 0, arrby, 1, arrby2.length);
        }
        return arrby;
    }

    public String getDigestAlg() throws AsnException {
        AsnType asnType = (AsnType)this.digestAlg.subs.elementAt(0);
        if (!(asnType instanceof AsnObjId)) {
            throw new AsnException("digestAlg comp0@" + asnType.idx + " not an OBJID");
        }
        AsnObjId asnObjId = (AsnObjId)asnType;
        ObjMap objMap = ObjMap.getNode(asnObjId.ids);
        if (objMap == null) {
            return null;
        }
        return objMap.name;
    }

    public String getDigestEncAlg() throws AsnException {
        AsnType asnType = (AsnType)this.digestEncAlg.subs.elementAt(0);
        if (!(asnType instanceof AsnObjId)) {
            throw new AsnException("digestEncAlg comp0@" + asnType.idx + " not an OBJID");
        }
        AsnObjId asnObjId = (AsnObjId)asnType;
        ObjMap objMap = ObjMap.getNode(asnObjId.ids);
        if (objMap == null) {
            return "";
        }
        return objMap.name;
    }

    public String getAlg() throws AsnException {
        return this.getDigestAlg() + "/" + this.getDigestEncAlg();
    }

    protected void debPrint(String string, PrintWriter printWriter) {
        this.pkcs7Version.dump(string + "pkcs7Version ", printWriter);
        this.contentInfo.dump(string + "contentInfo ", printWriter);
        if (this.certificate != null) {
            try {
                AsnCert asnCert = new AsnCert(this.certificate);
                asnCert.decompose();
                asnCert.debPrint(string + "certificate ", printWriter);
            }
            catch (Exception exception) {
                printWriter.println("cannot parse certificate: " + exception);
            }
        }
        if (this.issuer != null) {
            this.signerVersion.dump(string + "signerVersion ", printWriter);
            this.issuer.dump(string + "issuer ", printWriter);
            this.serialNumber.dump(string + "serialNumber ", printWriter);
            printWriter.println(string + "issuer/serno = " + this.getIssuerSerno());
            this.digestAlg.dump(string + "digestAlg ", printWriter);
            if (this.authAttrs != null) {
                this.authAttrs.dump(string + "authAttrs ", printWriter);
            }
            this.digestEncAlg.dump(string + "digestEncAlg ", printWriter);
            try {
                printWriter.println(string + "alg = " + this.getAlg());
            }
            catch (Exception exception) {
                printWriter.println("cannot determine alg: " + exception);
            }
            this.encDigest.dump(string + "encDigest ", printWriter);
            if (this.unAuthAttrs != null) {
                this.unAuthAttrs.dump(string + "unAuthAttrs ", printWriter);
            }
        }
    }
}

