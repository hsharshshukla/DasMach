/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XsRpc;

class XdrMem
extends Xdr {
    private byte[] x_buffer;
    private int x_size;
    private int x_offset;

    public XdrMem(byte[] arrby, int n, int n2) {
        this.x_op = n2;
        this.x_buffer = arrby;
        this.x_size = n;
        this.x_offset = 0;
    }

    @Override
    boolean x_getlong(XdrData xdrData) {
        if ((this.x_size -= 4) < 0) {
            return false;
        }
        xdrData.intValue = XsRpc.ntohl(this.x_buffer, this.x_offset);
        this.x_offset += 4;
        return true;
    }

    @Override
    boolean x_putlong(XdrData xdrData) {
        if ((this.x_size -= 4) < 0) {
            return false;
        }
        XsRpc.htonl(this.x_buffer, this.x_offset, xdrData.intValue);
        this.x_offset += 4;
        return true;
    }

    @Override
    boolean x_getbytes(XdrData xdrData, int n) {
        if ((this.x_size -= n) < 0) {
            return false;
        }
        byte[] arrby = new byte[n];
        for (int i = 0; i < n; ++i) {
            arrby[i] = this.x_buffer[this.x_offset++];
        }
        xdrData.objValue = arrby;
        return true;
    }

    @Override
    boolean x_putbytes(XdrData xdrData, int n) {
        if ((this.x_size -= n) < 0) {
            return false;
        }
        byte[] arrby = (byte[])xdrData.objValue;
        for (int i = 0; i < n; ++i) {
            this.x_buffer[this.x_offset++] = arrby[i];
        }
        return true;
    }

    @Override
    int x_getpos() {
        return this.x_offset;
    }

    @Override
    boolean x_setpos(int n) {
        if (n > this.x_offset + this.x_size) {
            return false;
        }
        this.x_size -= n - this.x_offset;
        this.x_offset = n;
        return true;
    }

    @Override
    void x_destroy() {
    }
}

