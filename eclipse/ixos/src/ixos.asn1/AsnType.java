/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnBitString;
import ixos.asn1.AsnBoolean;
import ixos.asn1.AsnConstructed;
import ixos.asn1.AsnContextSpec;
import ixos.asn1.AsnException;
import ixos.asn1.AsnInteger;
import ixos.asn1.AsnNotImpl;
import ixos.asn1.AsnNull;
import ixos.asn1.AsnObjId;
import ixos.asn1.AsnString;
import ixos.asn1.Buf;
import java.io.PrintWriter;

public class AsnType {
    public static final int ASN1_BOOLEAN = 1;
    public static final int ASN1_INTEGER = 2;
    public static final int ASN1_BITSTRING = 3;
    public static final int ASN1_OCTETSTRING = 4;
    public static final int ASN1_NULL = 5;
    public static final int ASN1_OBJID = 6;
    public static final int ASN1_OBJDESCR = 7;
    public static final int ASN1_EXTERNAL = 8;
    public static final int ASN1_REAL = 9;
    public static final int ASN1_ENUMERATED = 10;
    public static final int ASN1_UTF8STRING = 12;
    public static final int ASN1_SEQUENCE = 16;
    public static final int ASN1_SET = 17;
    public static final int ASN1_NUMSTRING = 18;
    public static final int ASN1_PRINTSTRING = 19;
    public static final int ASN1_T61STRING = 20;
    public static final int ASN1_VTXSTRING = 21;
    public static final int ASN1_IA5STRING = 22;
    public static final int ASN1_UTCTIME = 23;
    public static final int ASN1_GENTIME = 24;
    public static final int ASN1_GRASTRING = 25;
    public static final int ASN1_VISSTRING = 26;
    public static final int ASN1_GENSTRING = 27;
    public static final int ASN1_CHARSTRING = 28;
    private static final String[] _tag2Type = new String[]{"?", "BOOLEAN", "INTEGER", "BITSTRING", "OCTETSTRING", "NULL", "OBJID", "OBJDESCR", "EXTERNAL", "REAL", "ENUMERATED", "?", "UTF8STRING", "?", "?", "?", "SEQUENCE", "SET", "NUMSTRING", "PRINTSTRING", "T61STRING", "VTXSTRING", "IA5STRING", "UTCTIME", "GENTIME", "GRASTRING", "VISSTRING", "GENSTRING", "CHARSTRING"};
    public static final int ASN1_UNIVERSAL = 0;
    public static final int ASN1_APPLICATION = 64;
    public static final int ASN1_CONTEXTSPEC = 128;
    public static final int ASN1_PRIVATE = 192;
    public static final int ASN1_CLASS = 192;
    public static final int ASN1_CONSTRUCTED = 32;
    public static final int ASN1_TAG = 31;
    public static final int ASN1_INDEFINITE = -1;
    protected int tag;
    protected int aclass;
    protected int len;
    protected int idx;
    protected boolean constructed;
    protected Buf buf;
    protected AsnType parent;

    public static String tag2Type(int n) {
        if (n > 0 && n < _tag2Type.length) {
            return _tag2Type[n];
        }
        return "?";
    }

    protected AsnType(int n, int n2) {
        this.tag = n;
        this.aclass = n2;
    }

    protected AsnType(AsnType asnType) {
        this.parent = asnType;
        this.tag = asnType.tag;
        this.aclass = asnType.aclass;
        this.len = asnType.len;
        this.buf = asnType.buf;
        this.idx = asnType.idx;
    }

    public AsnType(Buf buf) {
        this.parent = null;
        this.buf = buf;
    }

    public AsnType parse() throws AsnException {
        AsnType asnType3;
        int n = this.idx;
        int n2 = this.len;
        int n3 = this.aclass;
        int n4 = this.tag;
        boolean bl = this.constructed;
        this.getTagClassLen();
        if (this.constructed) {
            AsnType asnType2 = new AsnConstructed(this);
            asnType2 = asnType2.parse();
            this.len = n2;
            this.idx = n;
            this.aclass = n3;
            this.tag = n4;
            this.constructed = bl;
            return asnType2;
        }
        if (this.aclass == 128) {
            AsnContextSpec asnContextSpec = new AsnContextSpec(this);
            this.len = n2;
            this.idx = n;
            this.aclass = n3;
            this.tag = n4;
            this.constructed = bl;
            return asnContextSpec;
        }
        if (this.aclass != 0) {
            throw new AsnException("unconstructed class at " + this.idx + " not implemented");
        }
        switch (this.tag) {
            AsnType asnType3;
            case 0: {
                if (this.len == 0) {
                    asnType3 = null;
                    break;
                }
                throw new AsnException("tag=0, len!=0 at " + this.idx);
            }
            case 1: {
                asnType3 = new AsnBoolean(this);
                break;
            }
            case 2: {
                asnType3 = new AsnInteger(this);
                break;
            }
            case 3: {
                asnType3 = new AsnBitString(this);
                break;
            }
            case 5: {
                asnType3 = new AsnNull(this);
                break;
            }
            case 6: {
                asnType3 = new AsnObjId(this);
                break;
            }
            case 4: 
            case 12: 
            case 19: 
            case 20: 
            case 22: 
            case 23: 
            case 24: {
                asnType3 = new AsnString(this);
                break;
            }
            default: {
                asnType3 = new AsnNotImpl(this);
            }
        }
        this.len = n2;
        this.idx = n;
        this.aclass = n3;
        this.tag = n4;
        this.constructed = bl;
        return asnType3;
    }

    protected void getTagClassLen() throws AsnException {
        this.idx = this.buf.currIdx();
        byte by = this.buf.nextByte();
        this.aclass = by & 192;
        this.constructed = (by & 32) != 0;
        this.tag = by & 31;
        if (this.tag == 31) {
            this.tag = 0;
            do {
                by = this.buf.nextByte();
                this.tag = this.tag * 128 + (by & 127);
            } while ((by & 128) != 0);
        }
        if ((by = this.buf.nextByte()) == -128) {
            this.len = -1;
        } else if ((by & 128) != 0) {
            int n = by & 127;
            if (n > 4) {
                throw new AsnException("length > 4 at " + this.idx);
            }
            this.len = 0;
            while (--n >= 0) {
                by = this.buf.nextByte();
                this.len = this.len * 256 + (by & 255);
            }
        } else {
            this.len = by;
        }
    }

    public int getIdx() {
        return this.idx;
    }

    public String toString() {
        return "Tag=" + this.tag + ",Len=" + this.len + "@" + this.idx;
    }

    public void dump(String string, PrintWriter printWriter) {
        printWriter.println(string + this.toString());
    }
}

