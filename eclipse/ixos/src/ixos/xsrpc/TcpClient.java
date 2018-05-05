/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  ixos.base.Log
 */
package ixos.xsrpc;

import ixos.base.Log;
import ixos.xsrpc.BlockingCallback;
import ixos.xsrpc.CallMsg;
import ixos.xsrpc.Client;
import ixos.xsrpc.NoneAuth;
import ixos.xsrpc.RecordStream;
import ixos.xsrpc.ReplyMsg;
import ixos.xsrpc.RpcException;
import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrMem;
import ixos.xsrpc.XdrProc;
import ixos.xsrpc.XdrRec;
import ixos.xsrpc.XsRpc;
import ixos.xsrpc.accepted_reply;
import ixos.xsrpc.opaque_auth;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient
extends Client
implements RecordStream {
    private static final int MCALL_MSG_SIZE = 24;
    private Socket ct_sock;
    private int ct_wait;
    private byte[] ct_mcall = new byte[24];
    private int ct_mpos;
    private Xdr ct_xdrs;
    private RpcException ct_except;

    public TcpClient(InetAddress inetAddress, int n, int n2, int n3, int n4, int n5) throws IOException, RpcException {
        CallMsg callMsg = new CallMsg();
        this.ct_sock = new Socket(inetAddress, n);
        this.ct_sock.setSoTimeout(this._blockingCallbackInterval);
        this.setTimeout(120000);
        callMsg.rm_xid = 0;
        callMsg.rm_direction = 0;
        callMsg.rm_rpcvers = 2;
        callMsg.rm_prog = n2;
        callMsg.rm_vers = n3;
        this.ct_xdrs = new XdrMem(this.ct_mcall, 24, 0);
        if (!callMsg.xdr_callhdr(this.ct_xdrs)) {
            this.ct_sock.close();
            this.ct_sock = null;
            throw new RpcException(16);
        }
        this.ct_mpos = this.ct_xdrs.x_getpos();
        this.ct_xdrs.x_destroy();
        this.ct_xdrs = new XdrRec(n4, n5, this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void cl_destroy() {
        block6 : {
            if (this.ct_sock != null) {
                try {
                    this.ct_sock.close();
                    break block6;
                }
                catch (IOException iOException) {}
                break block6;
                finally {
                    this.ct_sock = null;
                }
            }
        }
    }

    @Override
    public void setTimeout(int n) {
        this.ct_wait = n;
    }

    @Override
    public void cl_call(int n, XdrProc xdrProc, XdrData xdrData, XdrProc xdrProc2, XdrData xdrData2, int n2) throws RpcException {
        XdrRec xdrRec = (XdrRec)this.ct_xdrs;
        ReplyMsg replyMsg = new ReplyMsg();
        if (n2 <= 0) {
            n2 = this.ct_wait;
        }
        xdrRec.x_op = 0;
        this.ct_except = null;
        int n3 = XsRpc.ntohl(this.ct_mcall);
        XsRpc.htonl(this.ct_mcall, 0, ++n3);
        NoneAuth noneAuth = new NoneAuth();
        XdrData xdrData3 = new XdrData(noneAuth);
        XdrData xdrData4 = new XdrData(noneAuth);
        if (!(xdrRec.x_putbytes(new XdrData(this.ct_mcall), this.ct_mpos) && xdrRec.x_putlong(new XdrData(n)) && xdrRec.xdr_opaque_auth(xdrData3) && xdrRec.xdr_opaque_auth(xdrData4) && xdrProc.xdrproc(xdrRec, xdrData))) {
            if (this.ct_except == null) {
                this.ct_except = new RpcException(1);
            }
            xdrRec.xdrrec_endofrecord(true);
            throw this.ct_except;
        }
        if (!xdrRec.xdrrec_endofrecord(true)) {
            throw new RpcException(3);
        }
        xdrRec.x_op = 1;
        do {
            replyMsg.rp_acpt.ar_verf = null;
            replyMsg.rp_acpt.ar_results.where = null;
            replyMsg.rp_acpt.ar_results.proc = XdrProc.xdr_void;
            if (!xdrRec.xdrrec_skiprecord()) {
                if (this.ct_except == null) {
                    this.ct_except = new RpcException(16);
                }
                throw this.ct_except;
            }
            if (replyMsg.xdr_replymsg(xdrRec)) continue;
            if (this.ct_except == null) {
                this.ct_except = replyMsg.seterr_reply();
            }
            if (this.ct_except == null) {
                this.ct_except = new RpcException(16);
            }
            throw this.ct_except;
        } while (replyMsg.rm_xid != n3);
        if (this.ct_except == null && !xdrProc2.xdrproc(xdrRec, xdrData2)) {
            if (this.ct_except == null) {
                this.ct_except = new RpcException(2);
            }
            throw this.ct_except;
        }
    }

    @Override
    public int readit(byte[] arrby, int n, int n2) {
        Log log = new Log("TcpClient", "readit");
        log.msg(13, "*** TcpClient.readit: len = " + n2);
        try {
            InputStream inputStream = this.ct_sock.getInputStream();
            long l = System.currentTimeMillis();
            do {
                try {
                    n2 = inputStream.read(arrby, n, n2);
                    XsRpc.dump(arrby, n, n2);
                }
                catch (InterruptedIOException interruptedIOException) {
                    if (!(this.ct_wait != 0 && System.currentTimeMillis() - l > (long)this.ct_wait || this._blockingCallback != null && !this._blockingCallback.callback())) continue;
                    this.ct_except = new RpcException(5);
                    interruptedIOException.printStackTrace();
                    log.msg(13, interruptedIOException.toString());
                    n2 = -1;
                }
                break;
            } while (true);
        }
        catch (IOException iOException) {
            this.ct_except = new RpcException(4);
            iOException.printStackTrace();
            log.msg(13, iOException.toString());
            n2 = -1;
        }
        return n2;
    }

    @Override
    public int writeit(byte[] arrby, int n, int n2) {
        Log log = new Log("TcpClient", "writeit");
        log.msg(13, "*** TcpClient.writeit: len = " + n2);
        XsRpc.dump(arrby, n, n2);
        try {
            OutputStream outputStream = this.ct_sock.getOutputStream();
            outputStream.write(arrby, n, n2);
            outputStream.flush();
        }
        catch (InterruptedIOException interruptedIOException) {
            this.ct_except = new RpcException(5);
            interruptedIOException.printStackTrace();
            log.msg(13, interruptedIOException.toString());
            n2 = -1;
        }
        catch (IOException iOException) {
            this.ct_except = new RpcException(3);
            iOException.printStackTrace();
            log.msg(13, iOException.toString());
            n2 = -1;
        }
        return n2;
    }
}

