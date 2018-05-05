/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.NoneAuth;
import ixos.xsrpc.RpcMsg;
import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;

class CallMsg
extends RpcMsg {
    int rm_rpcvers;
    int rm_prog;
    int rm_vers;
    int rm_proc;

    CallMsg() {
    }

    final boolean xdr_callmsg(Xdr xdr) {
        if (!this.xdr_rpcmsg(xdr)) {
            return false;
        }
        if (this.rm_direction != 0) {
            return false;
        }
        XdrData xdrData = new XdrData();
        xdrData.intValue = this.rm_rpcvers;
        if (!xdr.xdr_int(xdrData)) {
            return false;
        }
        this.rm_rpcvers = xdrData.intValue;
        if (this.rm_rpcvers != 2) {
            return false;
        }
        xdrData.intValue = this.rm_prog;
        if (!xdr.xdr_int(xdrData)) {
            return false;
        }
        this.rm_prog = xdrData.intValue;
        xdrData.intValue = this.rm_vers;
        if (!xdr.xdr_int(xdrData)) {
            return false;
        }
        this.rm_vers = xdrData.intValue;
        xdrData.intValue = this.rm_proc;
        if (!xdr.xdr_int(xdrData)) {
            return false;
        }
        this.rm_proc = xdrData.intValue;
        NoneAuth noneAuth = new NoneAuth();
        xdrData.objValue = noneAuth;
        if (!xdr.xdr_opaque_auth(xdrData)) {
            return false;
        }
        xdrData.objValue = noneAuth;
        if (!xdr.xdr_opaque_auth(xdrData)) {
            return false;
        }
        return true;
    }

    boolean xdr_callhdr(Xdr xdr) {
        this.rm_direction = 0;
        this.rm_rpcvers = 2;
        if (xdr.x_op == 0 && xdr.xdr_int(new XdrData(this.rm_xid)) && xdr.xdr_int(new XdrData(this.rm_direction)) && xdr.xdr_int(new XdrData(this.rm_rpcvers)) && xdr.xdr_int(new XdrData(this.rm_prog))) {
            return xdr.xdr_int(new XdrData(this.rm_vers));
        }
        return false;
    }
}

