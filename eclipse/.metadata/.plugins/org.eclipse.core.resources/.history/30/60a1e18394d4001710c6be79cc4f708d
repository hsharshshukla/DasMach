/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;

abstract class RpcMsg {
    static final int RPC_MSG_VERSION = 2;
    static final int CALL = 0;
    static final int REPLY = 1;
    static final int MSG_ACCEPTED = 0;
    static final int MSG_DENIED = 1;
    static final int SUCCESS = 0;
    static final int PROG_UNAVAIL = 1;
    static final int PROG_MISMATCH = 2;
    static final int PROC_UNAVAIL = 3;
    static final int GARBAGE_ARGS = 4;
    static final int SYSTEM_ERR = 5;
    static final int RPC_MISMATCH = 0;
    static final int AUTH_ERROR = 1;
    int rm_xid;
    int rm_direction;

    RpcMsg() {
    }

    protected final boolean xdr_rpcmsg(Xdr xdr) {
        XdrData xdrData = new XdrData();
        xdrData.intValue = this.rm_xid;
        if (!xdr.xdr_int(xdrData)) {
            return false;
        }
        this.rm_xid = xdrData.intValue;
        xdrData.intValue = this.rm_direction;
        if (!xdr.xdr_int(xdrData)) {
            return false;
        }
        this.rm_direction = xdrData.intValue;
        return true;
    }
}

