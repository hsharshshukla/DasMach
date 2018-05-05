/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnException;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import java.io.PrintWriter;
import java.util.Vector;

public class AsnSet
extends AsnType {
    protected Vector subs = new Vector();

    protected AsnSet(AsnType asnType) throws AsnException {
        AsnType asnType2;
        int n;
        super(asnType);
        int n2 = n = this.len == -1 ? this.buf.lastIdx() : this.buf.currIdx() + this.len;
        while (this.buf.currIdx() < n && (asnType2 = this.parse()) != null) {
            this.subs.addElement(asnType2);
        }
    }

    public Vector getSubs() {
        return this.subs;
    }

    public String toString() {
        String string = "SET@" + this.idx + " length=";
        string = this.len == -1 ? string + "indefinite" : string + "" + this.len;
        return string;
    }

    public void dump(String string, PrintWriter printWriter) {
        printWriter.println(string + this.toString() + " {");
        for (int i = 0; i < this.subs.size(); ++i) {
            AsnType asnType = (AsnType)this.subs.elementAt(i);
            asnType.dump(string + "    ", printWriter);
        }
        printWriter.println(string + "}");
    }
}

