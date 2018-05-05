/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnException;
import ixos.asn1.AsnType;
import ixos.asn1.Buf;
import java.io.PrintWriter;
import java.math.BigInteger;

public class AsnInteger
extends AsnType {
    public int val;
    public Buf abuf;

    public AsnInteger(AsnType asnType) throws AsnException {
        super(asnType);
        this.abuf = new Buf(this.buf, this.len);
        if (this.len == 4) {
            long l = 0;
            for (int i = 0; i < 4; ++i) {
                l = l * 256 + (long)(this.abuf.nextByte() & 255);
            }
            this.abuf.rewind();
            if (l >= 0x80000000L) {
                l -= 0x100000000L;
            }
            this.val = (int)l;
        } else if (this.len < 4) {
            this.val = 0;
            for (int i = 0; i < this.len; ++i) {
                this.val = this.val * 256 + (this.abuf.nextByte() & 255);
            }
            this.abuf.rewind();
            switch (this.len) {
                case 1: {
                    if (this.val <= 128) break;
                    this.val -= 256;
                    break;
                }
                case 2: {
                    if (this.val <= 32768) break;
                    this.val -= 65536;
                    break;
                }
                case 3: {
                    if (this.val <= 8388608) break;
                    this.val -= 16777216;
                }
            }
        }
    }

    public String toString() {
        String string = "INTEGER@" + this.idx + " length=" + this.len;
        string = this.len <= 4 ? string + " val=" + this.val : string + " longint";
        return string;
    }

    public BigInteger toBigInt() {
        BigInteger bigInteger = new BigInteger(this.abuf.toByteArray());
        return bigInteger;
    }

    public void dump(String string, PrintWriter printWriter) {
        printWriter.print(string + this.toString());
        this.abuf.prBuf(string, true, printWriter);
    }
}

