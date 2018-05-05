/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import ixos.asn1.AsnOflowException;
import java.io.PrintWriter;

public class Buf {
    private byte[] _barr;
    private int _idx;
    private int _firstIdx;
    private int _lastIdx;
    static String hexArr = "0123456789ABCDEF";

    public Buf(byte[] arrby, int n, int n2) {
        this._barr = arrby;
        this._idx = this._firstIdx = n;
        this._lastIdx = n2 == -1 ? this._barr.length : this._firstIdx + n2;
    }

    public Buf(byte[] arrby) {
        this(arrby, 0, arrby.length);
    }

    public Buf(Buf buf, int n) throws AsnOflowException {
        if (buf._idx + n > buf._lastIdx) {
            throw new AsnOflowException("can't get " + n + " bytes from idx=" + buf._idx + ",lastIdx=" + buf._lastIdx);
        }
        this._barr = buf._barr;
        this._idx = this._firstIdx = buf._idx;
        this._lastIdx = n == -1 ? this._barr.length : this._firstIdx + n;
        buf._idx += n;
    }

    public byte[] barr() {
        return this._barr;
    }

    public int currIdx() {
        return this._idx;
    }

    public int firstIdx() {
        return this._firstIdx;
    }

    public int lastIdx() {
        return this._lastIdx;
    }

    public int length() {
        return this._lastIdx - this._firstIdx;
    }

    public boolean hasByte() {
        return this._idx < this._lastIdx;
    }

    public void rewind() {
        this._idx = this._firstIdx;
    }

    public byte[] toByteArray() {
        if (this._firstIdx == 0 && this._lastIdx == this._barr.length) {
            return this._barr;
        }
        byte[] arrby = new byte[this._lastIdx - this._firstIdx];
        System.arraycopy(this._barr, this._firstIdx, arrby, 0, arrby.length);
        return arrby;
    }

    public byte curByte() throws AsnOflowException {
        if (this._idx >= this._lastIdx) {
            throw new AsnOflowException("idx=" + this._idx + ",lastIdx=" + this._lastIdx);
        }
        return this._barr[this._idx];
    }

    public byte nextByte() throws AsnOflowException {
        if (this._idx >= this._lastIdx) {
            throw new AsnOflowException("idx=" + this._idx + ",lastIdx=" + this._lastIdx);
        }
        return this._barr[this._idx++];
    }

    public void prBuf(String string, boolean bl, PrintWriter printWriter) {
        int n;
        int n2 = this._lastIdx - this._firstIdx;
        int n3 = n = bl ? 16 : 32;
        if (n2 <= n) {
            printWriter.print(" <<");
            for (int i = this._firstIdx; i < this._lastIdx; ++i) {
                this.prByte(this._barr[i], bl, printWriter);
            }
            printWriter.println(">>");
            return;
        }
        string = string + "    ";
        for (int i = 0; i < n2; ++i) {
            if (i % n == 0) {
                printWriter.println();
                printWriter.print(string);
            }
            this.prByte(this._barr[this._firstIdx + i], bl, printWriter);
        }
        printWriter.println();
    }

    public void prByte(byte by, boolean bl, PrintWriter printWriter) {
        if (bl) {
            printWriter.print(hexArr.charAt(by >> 4 & 15));
            printWriter.print(hexArr.charAt(by & 15));
        } else {
            char c = (char)by;
            if (by >= 32 && c < '') {
                printWriter.print(c);
            } else {
                printWriter.print('.');
            }
        }
    }

    public String toString() {
        return new String(this._barr, this._firstIdx, this._lastIdx - this._firstIdx);
    }
}

