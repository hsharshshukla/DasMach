/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnException;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class AsnString
extends AsnType {
    public Buf abuf;

    public AsnString(AsnType asnType) throws AsnException {
        super(asnType);
        this.abuf = new Buf(this.buf, this.len);
    }

    public String toString() {
        String string;
        switch (this.tag) {
            case 23: {
                string = "UTCTIME";
                break;
            }
            case 4: {
                string = "OCTETSTRING";
                break;
            }
            case 19: {
                string = "PRINTSTRING";
                break;
            }
            case 20: {
                string = "T61STRING";
                break;
            }
            case 22: {
                string = "IA5STRING";
                break;
            }
            case 24: {
                string = "GENTIME";
                break;
            }
            case 12: {
                string = "UTF8STRING";
                break;
            }
            default: {
                string = "unknown tag " + this.tag;
            }
        }
        return string + "@" + this.idx + " length=" + this.len;
    }

    public void dump(String string, PrintWriter printWriter) {
        printWriter.print(string + this.toString());
        if (this.tag == 12) {
            try {
                String string2 = new String(this.abuf.toByteArray(), "UTF-8");
                printWriter.println(" <<" + string2 + ">>");
                return;
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                // empty catch block
            }
        }
        boolean bl = this.tag == 4;
        this.abuf.prBuf(string, bl, printWriter);
        if (bl) {
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
}

