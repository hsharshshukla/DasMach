/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.XdrData;
import ixos.xsrpc.opaque_auth;

public abstract class Xdr {
    public static final int XDR_ENCODE = 0;
    public static final int XDR_DECODE = 1;
    public int x_op;
    static final int BYTES_PER_XDR_UNIT = 4;
    private static final int LASTUNSIGNED = Integer.MAX_VALUE;
    private static final int XDR_FALSE = 0;
    private static final int XDR_TRUE = 1;

    abstract boolean x_getlong(XdrData var1);

    abstract boolean x_putlong(XdrData var1);

    abstract boolean x_getbytes(XdrData var1, int var2);

    abstract boolean x_putbytes(XdrData var1, int var2);

    abstract int x_getpos();

    abstract boolean x_setpos(int var1);

    abstract void x_destroy();

    public boolean xdr_void(XdrData xdrData) {
        return true;
    }

    public boolean xdr_int(XdrData xdrData) {
        switch (this.x_op) {
            case 0: {
                return this.x_putlong(xdrData);
            }
            case 1: {
                return this.x_getlong(xdrData);
            }
        }
        return false;
    }

    public boolean xdr_char(XdrData xdrData) {
        xdrData.intValue = xdrData.charValue;
        if (!this.xdr_int(xdrData)) {
            return false;
        }
        xdrData.charValue = (char)xdrData.intValue;
        return true;
    }

    public boolean xdr_bool(XdrData xdrData) {
        int n = xdrData.intValue = xdrData.boolValue ? 1 : 0;
        if (!this.xdr_int(xdrData)) {
            return false;
        }
        xdrData.boolValue = xdrData.intValue != 0;
        return true;
    }

    public boolean xdr_opaque(XdrData xdrData, int n) {
        if (n == 0) {
            return true;
        }
        XdrData xdrData2 = new XdrData();
        int n2 = n % 4;
        if (n2 > 0) {
            n2 = 4 - n2;
        }
        switch (this.x_op) {
            case 1: {
                if (xdrData.objValue == null) {
                    xdrData.objValue = new byte[n];
                }
                if (!this.x_getbytes(xdrData, n)) {
                    return false;
                }
                if (n2 == 0) {
                    return true;
                }
                xdrData2.objValue = new byte[n2];
                return this.x_getbytes(xdrData2, n2);
            }
            case 0: {
                if (!this.x_putbytes(xdrData, n)) {
                    return false;
                }
                if (n2 == 0) {
                    return true;
                }
                byte[] arrby = new byte[n2];
                for (int i = 0; i < n2; ++i) {
                    arrby[i] = 0;
                }
                return this.x_putbytes(new XdrData(arrby), n2);
            }
        }
        return false;
    }

    public boolean xdr_bytes(XdrData xdrData, int n, int n2) {
        xdrData.intValue = n;
        if (!this.xdr_int(xdrData)) {
            return false;
        }
        n = xdrData.intValue;
        if (n > n2) {
            return false;
        }
        switch (this.x_op) {
            case 1: {
                if (n == 0) {
                    return true;
                }
                if (xdrData.objValue == null) {
                    xdrData.objValue = new byte[n];
                }
            }
            case 0: {
                return this.xdr_opaque(xdrData, n);
            }
        }
        return false;
    }

    public boolean xdr_string(XdrData xdrData, int n) {
        XdrData xdrData2 = new XdrData();
        if (this.x_op != 1) {
            xdrData2.intValue = xdrData.strValue.length();
        }
        if (!this.xdr_int(xdrData2)) {
            return false;
        }
        if (xdrData2.intValue > n) {
            return false;
        }
        switch (this.x_op) {
            case 1: {
                if (xdrData2.intValue > 0) {
                    if (!this.xdr_opaque(xdrData, xdrData2.intValue)) {
                        return false;
                    }
                    xdrData.strValue = new String((byte[])xdrData.objValue);
                    xdrData.byteValue = (byte[])xdrData.objValue;
                } else {
                    xdrData.strValue = "";
                    xdrData.byteValue = null;
                }
                xdrData.objValue = null;
                return true;
            }
            case 0: {
                xdrData.objValue = xdrData.strValue.getBytes();
                if (!this.xdr_opaque(xdrData, xdrData2.intValue)) {
                    return false;
                }
                xdrData.objValue = null;
                return true;
            }
        }
        return false;
    }

    public boolean xdr_wrapstring(XdrData xdrData) {
        return this.xdr_string(xdrData, Integer.MAX_VALUE);
    }

    boolean xdr_opaque_auth(XdrData xdrData) {
        opaque_auth opaque_auth2 = (opaque_auth)xdrData.objValue;
        if (this.x_op == 1 && opaque_auth2 == null) {
            opaque_auth2 = new opaque_auth();
        }
        xdrData.intValue = opaque_auth2.oa_flavor;
        if (!this.xdr_int(xdrData)) {
            return false;
        }
        opaque_auth2.oa_flavor = xdrData.intValue;
        XdrData xdrData2 = new XdrData(opaque_auth2.oa_base);
        if (!this.xdr_bytes(xdrData2, opaque_auth2.oa_length, 400)) {
            return false;
        }
        opaque_auth2.oa_base = (byte[])xdrData2.objValue;
        opaque_auth2.oa_length = opaque_auth2.oa_base == null ? 0 : opaque_auth2.oa_base.length;
        xdrData.objValue = opaque_auth2;
        return true;
    }

    static final int RNDUP(int n) {
        return (n + 4 - 1) / 4 * 4;
    }
}

