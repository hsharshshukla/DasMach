/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;

class Xdr_string
extends XdrProc {
    Xdr_string() {
    }

    @Override
    public boolean xdrproc(Xdr xdr, XdrData xdrData, int n) {
        return xdr.xdr_string(xdrData, n);
    }
}

