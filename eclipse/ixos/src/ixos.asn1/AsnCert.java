/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnBitString;
import ixos.asn1.AsnConstructed;
import ixos.asn1.AsnException;
import ixos.asn1.AsnInteger;
import ixos.asn1.AsnName;
import ixos.asn1.AsnObjId;
import ixos.asn1.AsnSequence;
import ixos.asn1.AsnString;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import ixos.asn1.PKCS7Signature;
import ixos.asn1.PKFactory;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Vector;

public class AsnCert {
    private AsnType t;
    public int version;
    public AsnInteger serialNumber;
    public AsnSequence sigAlg;
    public AsnSequence issuer;
    public AsnString notBefore;
    public AsnString notAfter;
    public AsnSequence subject;
    public AsnSequence subjAlg;
    public AsnBitString subjPubKey;
    public AsnSequence signAlg;
    public AsnBitString signature;

    public AsnCert(AsnType asnType) {
        this.t = asnType;
    }

    public AsnCert(byte[] arrby, int n) throws AsnException {
        Buf buf = new Buf(arrby, 0, n);
        AsnType asnType = new AsnType(buf);
        this.t = asnType.parse();
    }

    public void decompose() throws AsnException {
        Object object;
        try {
            object = new PKCS7Signature(this.t);
            object.decompose();
            if (object.certificate != null) {
                this.t = object.certificate;
            }
        }
        catch (AsnException asnException) {
            // empty catch block
        }
        if (!(this.t instanceof AsnSequence)) {
            throw new AsnException("root@" + this.t.idx + " not a SEQUENCE");
        }
        object = (AsnSequence)this.t;
        if (object.subs.size() != 3) {
            throw new AsnException("root@" + this.t.idx + " SEQUENCE has not 3 components");
        }
        AsnType asnType = (AsnType)object.subs.elementAt(0);
        if (!(asnType instanceof AsnSequence)) {
            throw new AsnException("certificateInfo@" + asnType.idx + " not a SEQUENCE");
        }
        AsnSequence asnSequence = (AsnSequence)asnType;
        if (asnSequence.subs.size() < 6) {
            throw new AsnException("certificateInfo@" + asnType.idx + " has less than 6 components");
        }
        int n = 0;
        asnType = (AsnType)asnSequence.subs.elementAt(0);
        if (asnType instanceof AsnConstructed) {
            ++n;
            AsnConstructed asnConstructed = (AsnConstructed)asnType;
            asnType = (AsnType)asnConstructed.subs.elementAt(0);
            if (!(asnType instanceof AsnInteger)) {
                throw new AsnException("version@" + asnType.idx + " not an INTEGER");
            }
            this.version = ((AsnInteger)asnType).val;
        } else {
            this.version = 0;
        }
        asnType = (AsnType)asnSequence.subs.elementAt(n++);
        if (!(asnType instanceof AsnInteger)) {
            throw new AsnException("serialNumber@" + asnType.idx + " not an INTEGER");
        }
        this.serialNumber = (AsnInteger)asnType;
        if (!((asnType = (AsnType)asnSequence.subs.elementAt(n++)) instanceof AsnSequence)) {
            throw new AsnException("signature@" + asnType.idx + " not a SEQUENCE");
        }
        this.sigAlg = (AsnSequence)asnType;
        if (!((asnType = (AsnType)asnSequence.subs.elementAt(n++)) instanceof AsnSequence)) {
            throw new AsnException("issuer@" + asnType.idx + " not a SEQUENCE");
        }
        this.issuer = (AsnSequence)asnType;
        if (!((asnType = (AsnType)asnSequence.subs.elementAt(n++)) instanceof AsnSequence)) {
            throw new AsnException("validity@" + asnType.idx + " not a SEQUENCE");
        }
        AsnSequence asnSequence2 = (AsnSequence)asnType;
        if (asnSequence2.subs.size() != 2) {
            throw new AsnException("validity@" + asnType.idx + " has not 2 components");
        }
        asnType = (AsnType)asnSequence2.subs.elementAt(0);
        if (!(asnType instanceof AsnString)) {
            throw new AsnException("notBefore@" + asnType.idx + " not a STRING");
        }
        this.notBefore = (AsnString)asnType;
        AsnType asnType2 = (AsnType)asnSequence2.subs.elementAt(1);
        if (!(asnType2 instanceof AsnString)) {
            throw new AsnException("notAfter@" + asnType2.idx + " not a STRING");
        }
        this.notAfter = (AsnString)asnType2;
        if (!((asnType = (AsnType)asnSequence.subs.elementAt(n++)) instanceof AsnSequence)) {
            throw new AsnException("subject@" + asnType.idx + " not a SEQUENCE");
        }
        this.subject = (AsnSequence)asnType;
        if (!((asnType = (AsnType)asnSequence.subs.elementAt(n++)) instanceof AsnSequence)) {
            throw new AsnException("subjectPublicKeyInfo@" + asnType.idx + " not a SEQUENCE");
        }
        AsnSequence asnSequence3 = (AsnSequence)asnType;
        if (asnSequence3.subs.size() != 2) {
            throw new AsnException("subjectPublicKeyInfo@" + asnType.idx + " has not 2 components");
        }
        asnType = (AsnType)asnSequence3.subs.elementAt(0);
        if (!(asnType instanceof AsnSequence)) {
            throw new AsnException("subjectPublicKeyInfo algorithm@" + asnType.idx + " not a SEQUENCE");
        }
        this.subjAlg = (AsnSequence)asnType;
        asnType = (AsnType)asnSequence3.subs.elementAt(1);
        if (!(asnType instanceof AsnBitString)) {
            throw new AsnException("subjectPublicKeyInfo subjectPublicKey@" + asnType.idx + " not a BITSTRING");
        }
        this.subjPubKey = (AsnBitString)asnType;
        asnType = (AsnType)object.subs.elementAt(1);
        if (!(asnType instanceof AsnSequence)) {
            throw new AsnException("signatureAlgorithm@" + asnType.idx + " not a SEQUENCE");
        }
        this.signAlg = (AsnSequence)asnType;
        asnType = (AsnType)object.subs.elementAt(2);
        if (!(asnType instanceof AsnBitString)) {
            throw new AsnException("signature@" + asnType.idx + " not a BITSTRING");
        }
        this.signature = (AsnBitString)asnType;
    }

    public PublicKey getPublicKey() throws AsnException {
        AsnType asnType = (AsnType)this.subjAlg.subs.elementAt(0);
        if (!(asnType instanceof AsnObjId)) {
            throw new AsnException("subjAlg@" + asnType.idx + " not an OBJID");
        }
        AsnObjId asnObjId = (AsnObjId)asnType;
        PKFactory pKFactory = PKFactory.getInstance(asnObjId);
        if (pKFactory == null) {
            throw new AsnException("can't get a public key factory for " + asnObjId);
        }
        PublicKey publicKey = pKFactory.makeKey(this.subjAlg, this.subjPubKey);
        return publicKey;
    }

    public String getIssuerSerno() {
        AsnName asnName = new AsnName(this.issuer);
        return asnName.toName() + ";" + this.serialNumber.toBigInt();
    }

    public void checkValidity() throws AsnException {
    }

    protected void debPrint(String string, PrintWriter printWriter) {
        printWriter.println(string + "version " + this.version);
        this.serialNumber.dump(string + "serialNumber ", printWriter);
        this.sigAlg.dump(string + "sigAlg ", printWriter);
        this.issuer.dump(string + "issuer ", printWriter);
        printWriter.println(string + "issuer/serno = " + this.getIssuerSerno());
        this.notBefore.dump(string + "notBefore ", printWriter);
        this.notAfter.dump(string + "notAfter ", printWriter);
        this.subject.dump(string + "subject ", printWriter);
        AsnName asnName = new AsnName(this.subject);
        printWriter.println(string + "subject name = " + asnName.toName());
        this.subjAlg.dump(string + "subjAlg ", printWriter);
        this.subjPubKey.dump(string + "subjPubKey ", printWriter);
        this.signAlg.dump(string + "signAlg ", printWriter);
        this.signature.dump(string + "signature ", printWriter);
    }
}

