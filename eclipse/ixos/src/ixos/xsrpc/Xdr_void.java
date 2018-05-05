/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;

class Xdr_void
extends XdrProc {
    Xdr_void() {
    }

    @Override
    public boolean xdrproc(Xdr xdr, XdrData xdrData) {
        return xdr.xdr_void(xdrData);
    }
}

