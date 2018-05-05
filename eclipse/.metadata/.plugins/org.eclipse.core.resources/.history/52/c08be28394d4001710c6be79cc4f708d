/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.RpcException;
import ixos.xsrpc.RpcMsg;
import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;
import ixos.xsrpc.accepted_reply;
import ixos.xsrpc.opaque_auth;
import ixos.xsrpc.rejected_reply;

class ReplyMsg
extends RpcMsg {
    int rp_stat;
    accepted_reply rp_acpt = new accepted_reply();
    rejected_reply rp_rjct = new rejected_reply();

    ReplyMsg() {
    }

    final boolean xdr_replymsg(Xdr xdr) {
        if (!this.xdr_rpcmsg(xdr)) {
            return false;
        }
        XdrData xdrData = new XdrData();
        if (this.rm_direction != 1) {
            return false;
        }
        xdrData.intValue = this.rp_stat;
        if (!xdr.xdr_int(xdrData)) {
            return false;
        }
        this.rp_stat = xdrData.intValue;
        switch (this.rp_stat) {
            case 0: {
                return this.xdr_accepted_reply(xdr);
            }
            case 1: {
                return this.xdr_rejected_reply(xdr);
            }
        }
        return false;
    }

    private final boolean xdr_accepted_reply(Xdr xdr) {
        XdrData xdrData = new XdrData();
        xdrData.objValue = this.rp_acpt.ar_verf;
        if (!xdr.xdr_opaque_auth(xdrData)) {
            return false;
        }
        this.rp_acpt.ar_verf = (opaque_auth)xdrData.objValue;
        xdrData.intValue = this.rp_acpt.ar_stat;
        if (!xdr.xdr_int(xdrData)) {
            return false;
        }
        this.rp_acpt.ar_stat = xdrData.intValue;
        switch (this.rp_acpt.ar_stat) {
            case 0: {
                xdrData = (XdrData)this.rp_acpt.ar_results.where;
                return this.rp_acpt.ar_results.proc.xdrproc(xdr, xdrData);
            }
            case 2: {
                xdrData.intValue = this.rp_acpt.ar_vers.low;
                if (!xdr.xdr_int(xdrData)) {
                    return false;
                }
                this.rp_acpt.ar_vers.low = xdrData.intValue;
                xdrData.intValue = this.rp_acpt.ar_vers.high;
                if (!xdr.xdr_int(xdrData)) {
                    return false;
                }
                this.rp_acpt.ar_vers.high = xdrData.intValue;
                return true;
            }
        }
        return true;
    }

    private final boolean xdr_rejected_reply(Xdr xdr) {
        XdrData xdrData = new XdrData();
        xdrData.intValue = this.rp_rjct.rj_stat;
        if (!xdr.xdr_int(xdrData)) {
            return false;
        }
        this.rp_rjct.rj_stat = xdrData.intValue;
        switch (this.rp_rjct.rj_stat) {
            case 0: {
                xdrData.intValue = this.rp_rjct.rj_vers.low;
                if (!xdr.xdr_int(xdrData)) {
                    return false;
                }
                this.rp_rjct.rj_vers.low = xdrData.intValue;
                xdrData.intValue = this.rp_rjct.rj_vers.high;
                if (!xdr.xdr_int(xdrData)) {
                    return false;
                }
                this.rp_rjct.rj_vers.high = xdrData.intValue;
                break;
            }
            case 1: {
                xdrData.intValue = this.rp_rjct.rj_why;
                if (!xdr.xdr_int(xdrData)) {
                    return false;
                }
                this.rp_rjct.rj_why = xdrData.intValue;
            }
        }
        return true;
    }

    final RpcException seterr_reply() {
        RpcException rpcException;
        switch (this.rp_stat) {
            case 0: {
                if (this.rp_acpt.ar_stat == 0) {
                    return null;
                }
                rpcException = ReplyMsg.accepted(this.rp_acpt.ar_stat);
                break;
            }
            case 1: {
                rpcException = ReplyMsg.rejected(this.rp_rjct.rj_stat);
                break;
            }
            default: {
                rpcException = new RpcException(16);
            }
        }
        return rpcException;
    }

    private static RpcException accepted(int n) {
        int n2;
        switch (n) {
            case 0: {
                return null;
            }
            case 1: {
                n2 = 8;
                break;
            }
            case 2: {
                n2 = 9;
                break;
            }
            case 3: {
                n2 = 10;
                break;
            }
            case 4: {
                n2 = 11;
                break;
            }
            case 5: {
                n2 = 12;
                break;
            }
            default: {
                n2 = 16;
            }
        }
        return new RpcException(n2);
    }

    private static RpcException rejected(int n) {
        return new RpcException(16);
    }
}

