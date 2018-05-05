/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.Xdr_bool;
import ixos.xsrpc.Xdr_char;
import ixos.xsrpc.Xdr_int;
import ixos.xsrpc.Xdr_opaque;
import ixos.xsrpc.Xdr_string;
import ixos.xsrpc.Xdr_void;
import ixos.xsrpc.Xdr_wrapstring;

public abstract class XdrProc {
    public static final XdrProc xdr_void = new Xdr_void();
    public static final XdrProc xdr_int = new Xdr_int();
    public static final XdrProc xdr_char = new Xdr_char();
    public static final XdrProc xdr_bool = new Xdr_bool();
    public static final XdrProc xdr_opaque = new Xdr_opaque();
    public static final XdrProc xdr_string = new Xdr_string();
    public static final XdrProc xdr_wrapstring = new Xdr_wrapstring();

    public boolean xdrproc(Xdr xdr, XdrData xdrData) {
        return false;
    }

    public boolean xdrproc(Xdr xdr, XdrData xdrData, int n) {
        return false;
    }
}

