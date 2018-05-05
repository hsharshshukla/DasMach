/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnException;
import ixos.asn1.AsnSequence;
import ixos.asn1.AsnSet;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import java.io.PrintWriter;
import java.util.Vector;

public class AsnConstructed
extends AsnType {
    protected Vector subs;
    protected Buf cbuf;

    protected AsnConstructed(AsnType asnType) throws AsnException {
        super(asnType);
        this.cbuf = new Buf(this.buf.barr(), this.idx, this.buf.currIdx() - this.idx + this.len);
    }

    public AsnType parse() throws AsnException {
        if (this.aclass == 128) {
            this.parseSubs();
            return this;
        }
        if (this.aclass == 0) {
            switch (this.tag) {
                case 16: {
                    return new AsnSequence(this);
                }
                case 17: {
                    return new AsnSet(this);
                }
            }
            this.parseSubs();
            return this;
        }
        throw new AsnException("constructed class at " + this.idx + " not implemented");
    }

    private void parseSubs() throws AsnException {
        AsnType asnType;
        int n = this.len == -1 ? this.buf.lastIdx() : this.buf.currIdx() + this.len;
        this.subs = new Vector();
        while (this.buf.currIdx() < n && (asnType = super.parse()) != null) {
            this.subs.addElement(asnType);
        }
    }

    public Vector getSubs() {
        return this.subs;
    }

    public String toString() {
        String string = this.aclass == 128 ? "[" + this.tag + "]" : (this.aclass == 0 ? AsnConstructed.tag2Type(this.tag) : "Class=" + this.aclass + ",Tag=" + this.tag);
        string = string + "@" + this.idx + " constructed length=";
        string = this.len == -1 ? string + "indefinite" : string + "" + this.len;
        return string;
    }

    public void dump(String string, PrintWriter printWriter) {
        printWriter.println(string + this.toString() + "{");
        for (int i = 0; i < this.subs.size(); ++i) {
            AsnType asnType = (AsnType)this.subs.elementAt(i);
            asnType.dump(string + "    ", printWriter);
        }
        printWriter.println(string + "}");
    }
}

