/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.RecordStream;
import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XsRpc;

class XdrRec
extends Xdr {
    static final int LAST_FRAG = Integer.MIN_VALUE;
    private int hackLen = 0;
    byte[] the_buffer;
    int out_base;
    int out_finger;
    int out_boundry;
    int frag_header;
    boolean frag_sent;
    int in_size;
    int in_base;
    int in_finger;
    int in_boundry;
    int fbtbc;
    boolean last_frag;
    int sendsize;
    int recvsize;
    RecordStream x_rstrm;

    XdrRec(int n, int n2, RecordStream recordStream) {
        n = XdrRec.fix_buf_size(n);
        n2 = XdrRec.fix_buf_size(n2);
        this.the_buffer = new byte[n + n2 + 4];
        this.out_base = 0;
        this.in_base = this.out_base + n;
        this.x_rstrm = recordStream;
        this.out_finger = this.out_boundry = this.out_base;
        this.frag_header = 0;
        this.out_finger += 4;
        this.out_boundry += n;
        this.frag_sent = false;
        this.in_size = n2;
        this.in_boundry = this.in_base;
        this.in_finger = this.in_boundry += n2;
        this.fbtbc = 0;
        this.last_frag = true;
    }

    @Override
    boolean x_getlong(XdrData xdrData) {
        xdrData.objValue = new byte[4];
        if (!this.x_getbytes(xdrData, 4)) {
            return false;
        }
        xdrData.intValue = XsRpc.ntohl((byte[])xdrData.objValue);
        xdrData.objValue = null;
        return true;
    }

    @Override
    boolean x_putlong(XdrData xdrData) {
        int n = this.out_finger;
        if ((this.out_finger += 4) > this.out_boundry) {
            this.out_finger -= 4;
            this.frag_sent = true;
            if (!this.flush_out(false)) {
                return false;
            }
            n = this.out_finger;
            this.out_finger += 4;
        }
        XsRpc.htonl(this.the_buffer, n, xdrData.intValue);
        return true;
    }

    @Override
    boolean x_getbytes(XdrData xdrData, int n) {
        int n2 = 0;
        while (n > 0) {
            int n3 = this.fbtbc;
            if (n3 == 0) {
                if (this.last_frag) {
                    return false;
                }
                if (this.set_input_fragment()) continue;
                return false;
            }
            int n4 = n3 = n < n3 ? n : n3;
            if (!this.get_input_bytes((byte[])xdrData.objValue, n2, n3)) {
                return false;
            }
            n2 += n3;
            this.fbtbc -= n3;
            n -= n3;
        }
        return true;
    }

    @Override
    boolean x_putbytes(XdrData xdrData, int n) {
        int n2 = 0;
        while (n > 0) {
            int n3 = this.out_boundry - this.out_finger;
            n3 = n < n3 ? n : n3;
            XsRpc.bcopy((byte[])xdrData.objValue, n2, this.the_buffer, this.out_finger, n3);
            this.out_finger += n3;
            n2 += n3;
            n -= n3;
            if (this.out_finger != this.out_boundry) continue;
            this.frag_sent = true;
            if (this.flush_out(false)) continue;
            return false;
        }
        return true;
    }

    @Override
    int x_getpos() {
        return -1;
    }

    @Override
    boolean x_setpos(int n) {
        return false;
    }

    @Override
    void x_destroy() {
    }

    boolean xdrrec_skiprecord() {
        while (this.fbtbc > 0 || !this.last_frag) {
            if (!this.skip_input_bytes(this.fbtbc)) {
                return false;
            }
            this.fbtbc = 0;
            if (this.last_frag || this.set_input_fragment()) continue;
            return false;
        }
        this.last_frag = false;
        return true;
    }

    boolean xdrrec_eof() {
        while (this.fbtbc > 0 || !this.last_frag) {
            if (!this.skip_input_bytes(this.fbtbc)) {
                return true;
            }
            this.fbtbc = 0;
            if (this.last_frag || this.set_input_fragment()) continue;
            return true;
        }
        if (this.in_finger == this.in_boundry) {
            return true;
        }
        return false;
    }

    boolean xdrrec_endofrecord(boolean bl) {
        if (bl || this.frag_sent || this.out_finger + 4 >= this.out_boundry) {
            this.frag_sent = false;
            return this.flush_out(true);
        }
        int n = this.out_finger - this.frag_header - 4;
        XsRpc.htonl(this.the_buffer, this.frag_header, n | Integer.MIN_VALUE);
        this.frag_header = this.out_finger;
        this.out_finger += 4;
        return true;
    }

    private boolean flush_out(boolean bl) {
        int n = bl ? Integer.MIN_VALUE : 0;
        int n2 = this.out_finger - this.frag_header - 4;
        XsRpc.htonl(this.the_buffer, this.out_base + this.frag_header, n2 | n);
        n2 = this.out_finger - this.out_base;
        if (this.x_rstrm.writeit(this.the_buffer, this.out_base, n2) != n2) {
            return false;
        }
        this.frag_header = this.out_base;
        this.out_finger = this.out_base + 4;
        return true;
    }

    private boolean fill_input_buf() {
        int n = this.in_base;
        int n2 = this.in_boundry % 4;
        n += n2;
        int n3 = this.in_size - n2;
        if (this.hackLen > 0) {
            n3 = this.hackLen;
        }
        if ((n3 = this.x_rstrm.readit(this.the_buffer, n, n3)) == -1) {
            return false;
        }
        this.in_finger = n;
        this.in_boundry = n += n3;
        return true;
    }

    private boolean get_input_bytes(byte[] arrby, int n, int n2) {
        while (n2 > 0) {
            int n3 = this.in_boundry - this.in_finger;
            if (n3 == 0) {
                if (this.fill_input_buf()) continue;
                return false;
            }
            n3 = n2 < n3 ? n2 : n3;
            for (int i = 0; i < n3; ++i) {
                arrby[n + i] = this.the_buffer[this.in_finger++];
            }
            n += n3;
            n2 -= n3;
        }
        return true;
    }

    private boolean set_input_fragment() {
        byte[] arrby = new byte[4];
        this.hackLen = 4;
        if (!this.get_input_bytes(arrby, 0, 4)) {
            this.hackLen = 0;
            return false;
        }
        this.hackLen = 0;
        int n = XsRpc.ntohl(arrby);
        this.last_frag = (n & Integer.MIN_VALUE) != 0;
        this.fbtbc = n & Integer.MAX_VALUE;
        return true;
    }

    private boolean skip_input_bytes(int n) {
        while (n > 0) {
            int n2 = this.in_boundry - this.in_finger;
            if (n2 == 0) {
                if (this.fill_input_buf()) continue;
                return false;
            }
            n2 = n < n2 ? n : n2;
            this.in_finger += n2;
            n -= n2;
        }
        return true;
    }

    static int fix_buf_size(int n) {
        if (n < 100) {
            n = 4000;
        }
        return XdrRec.RNDUP(n);
    }
}

