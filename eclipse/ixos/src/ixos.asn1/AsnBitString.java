/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnException;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import java.io.PrintWriter;

public class AsnBitString
extends AsnType {
    public int rem;
    public Buf abuf;

    public AsnBitString(AsnType asnType) throws AsnException {
        super(asnType);
        if (this.len == 0) {
            this.abuf = new Buf(this.buf, this.len);
            this.rem = 0;
            return;
        }
        if (this.len < 1) {
            throw new AsnException("BitString length < 1 at " + this.idx);
        }
        this.rem = this.buf.nextByte();
        --this.len;
        this.abuf = new Buf(this.buf, this.len);
    }

    public String toString() {
        return "BITSTRING@" + this.idx + " numBits=" + (this.len * 8 - this.rem);
    }

    public void dump(String string, PrintWriter printWriter) {
        printWriter.print(string + this.toString());
        this.abuf.prBuf(string, true, printWriter);
        try {
            AsnType asnType = new AsnType(this.abuf);
            AsnType asnType2 = asnType.parse();
            this.abuf.rewind();
            printWriter.println(string + "BitString itself is in BER/DER format:");
            asnType2.dump(string + "    ", printWriter);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

