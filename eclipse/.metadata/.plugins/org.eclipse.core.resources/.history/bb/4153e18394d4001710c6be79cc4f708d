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
import ixos.xsrpc.ReplyMsg;
import ixos.xsrpc.RpcException;
import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrMem;
import ixos.xsrpc.XdrProc;
import ixos.xsrpc.XsRpc;
import ixos.xsrpc.accepted_reply;
import ixos.xsrpc.opaque_auth;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient
extends Client {
    private static final int UDPMSGSIZE = 8800;
    private int ivRetryTimeout = 1000;
    private DatagramSocket cu_sock;
    private InetAddress cu_raddr;
    private int cu_rport;
    private int cu_wait;
    private Xdr cu_outxdrs;
    private int cu_xdrpos;
    private byte[] cu_outbuf;
    private int cu_recvsz;
    private byte[] cu_inbuf;

    public UdpClient(InetAddress inetAddress, int n, int n2, int n3, int n4, int n5) throws IOException, RpcException {
        n4 = (n4 + 3) / 4 * 4;
        n5 = (n5 + 3) / 4 * 4;
        this.cu_outbuf = new byte[n4];
        this.cu_recvsz = n5;
        this.cu_inbuf = new byte[this.cu_recvsz];
        this.cu_raddr = inetAddress;
        this.cu_rport = n;
        this.setTimeout(120000);
        CallMsg callMsg = new CallMsg();
        callMsg.rm_xid = 0;
        callMsg.rm_direction = 0;
        callMsg.rm_rpcvers = 2;
        callMsg.rm_prog = n2;
        callMsg.rm_vers = n3;
        this.cu_outxdrs = new XdrMem(this.cu_outbuf, n4, 0);
        if (!callMsg.xdr_callhdr(this.cu_outxdrs)) {
            throw new RpcException(16);
        }
        this.cu_xdrpos = this.cu_outxdrs.x_getpos();
        this.cu_sock = new DatagramSocket();
    }

    public UdpClient(InetAddress inetAddress, int n, int n2, int n3) throws IOException, RpcException {
        this(inetAddress, n, n2, n3, 8800, 8800);
    }

    public UdpClient(InetAddress inetAddress, int n, int n2, int n3, int n4) throws IOException, RpcException {
        int n5 = 8800;
        int n6 = 8800;
        this.cu_outbuf = new byte[n5];
        this.cu_recvsz = n6;
        this.cu_inbuf = new byte[this.cu_recvsz];
        this.cu_raddr = inetAddress;
        this.cu_rport = n;
        this.setTimeout(120000);
        CallMsg callMsg = new CallMsg();
        callMsg.rm_xid = 0;
        callMsg.rm_direction = 0;
        callMsg.rm_rpcvers = 2;
        callMsg.rm_prog = n3;
        callMsg.rm_vers = n4;
        this.cu_outxdrs = new XdrMem(this.cu_outbuf, n5, 0);
        if (!callMsg.xdr_callhdr(this.cu_outxdrs)) {
            throw new RpcException(16);
        }
        this.cu_xdrpos = this.cu_outxdrs.x_getpos();
        this.cu_sock = n2 == 0 ? new DatagramSocket() : new DatagramSocket(n2);
    }

    @Override
    public void setTimeout(int n) {
        this.cu_wait = n;
    }

    public void setRetryTimeout(int n) {
        this.ivRetryTimeout = n;
    }

    @Override
    public synchronized void cl_call(int n, XdrProc xdrProc, XdrData xdrData, XdrProc xdrProc2, XdrData xdrData2, int n2) throws RpcException {
        Log log = new Log("UpdClient", "cl_call");
        if (n2 < 0) {
            n2 = this.cu_wait;
        }
        try {
            this.cu_sock.setSoTimeout(this.ivRetryTimeout);
        }
        catch (SocketException socketException) {
            log.msg(13, socketException.toString());
            throw new RpcException(16);
        }
        Xdr xdr = this.cu_outxdrs;
        xdr.x_op = 0;
        xdr.x_setpos(this.cu_xdrpos);
        int n3 = XsRpc.ntohl(this.cu_outbuf);
        XsRpc.htonl(this.cu_outbuf, 0, ++n3);
        NoneAuth noneAuth = new NoneAuth();
        XdrData xdrData3 = new XdrData(noneAuth);
        XdrData xdrData4 = new XdrData(noneAuth);
        if (!(xdr.x_putlong(new XdrData(n)) && xdr.xdr_opaque_auth(xdrData3) && xdr.xdr_opaque_auth(xdrData4) && xdrProc.xdrproc(xdr, xdrData))) {
            throw new RpcException(1);
        }
        int n4 = xdr.x_getpos();
        log.msg(13, "*** UdpClient.cl_call: send " + n4 + " bytes");
        XsRpc.dump(this.cu_outbuf, 0, n4);
        DatagramPacket datagramPacket = new DatagramPacket(this.cu_inbuf, this.cu_recvsz);
        long l = System.currentTimeMillis();
        long l2 = l + (long)this._blockingCallbackInterval;
        block7 : do {
            try {
                this.cu_sock.send(new DatagramPacket(this.cu_outbuf, n4, this.cu_raddr, this.cu_rport));
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                log.msg(13, iOException.toString());
                throw new RpcException(3);
            }
            if (n2 <= 0) {
                return;
            }
            do {
                try {
                    log.msg(13, "*** UdpClient.cl_call: receive");
                    this.cu_sock.receive(datagramPacket);
                    XsRpc.dump(this.cu_inbuf, 0, datagramPacket.getLength());
                }
                catch (InterruptedIOException interruptedIOException) {
                    long l3 = System.currentTimeMillis();
                    boolean bl = false;
                    if (l3 - l < (long)n2) {
                        if (this._blockingCallback == null || l3 < l2) {
                            bl = true;
                        } else {
                            bl = this._blockingCallback.callback();
                            l2 = l3 + (long)this._blockingCallbackInterval;
                        }
                    }
                    if (bl) {
                        log.msg(13, "*** UdpClient.cl_call: timeout on receive, SEND_AGAIN");
                        continue block7;
                    }
                    interruptedIOException.printStackTrace();
                    log.msg(13, interruptedIOException.toString());
                    throw new RpcException(5);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                    log.msg(13, iOException.toString());
                    throw new RpcException(4);
                }
            } while (XsRpc.ntohl(this.cu_inbuf) != n3);
            break;
        } while (true);
        ReplyMsg replyMsg = new ReplyMsg();
        replyMsg.rp_acpt.ar_verf = new NoneAuth();
        replyMsg.rp_acpt.ar_results.where = xdrData2;
        replyMsg.rp_acpt.ar_results.proc = xdrProc2;
        XdrMem xdrMem = new XdrMem(this.cu_inbuf, datagramPacket.getLength(), 1);
        if (!replyMsg.xdr_replymsg(xdrMem)) {
            throw new RpcException(2);
        }
    }

    @Override
    public void cl_destroy() {
        if (this.cu_sock != null) {
            this.cu_sock.close();
        }
    }
}

