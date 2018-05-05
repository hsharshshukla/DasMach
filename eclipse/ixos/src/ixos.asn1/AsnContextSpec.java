/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnException;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import java.io.PrintWriter;

public class AsnContextSpec
extends AsnType {
    public Buf abuf;

    public AsnContextSpec(AsnType asnType) throws AsnException {
        super(asnType);
        this.abuf = new Buf(this.buf, this.len);
    }

    public String toString() {
        String string = "[" + this.tag + "]";
        string = string + "@" + this.idx + " unconstructed length=";
        string = this.len == -1 ? string + "indefinite" : string + "" + this.len;
        return string;
    }

    public void dump(String string, PrintWriter printWriter) {
        printWriter.print(string + this.toString());
        this.abuf.prBuf(string, true, printWriter);
        try {
            AsnType asnType = new AsnType(this.abuf);
            AsnType asnType2 = asnType.parse();
            this.abuf.rewind();
            printWriter.println(string + "String itself is in BER/DER format:");
            asnType2.dump(string + "    ", printWriter);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

